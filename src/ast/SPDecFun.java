package ast;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;
import util.STentryEffects;
import util.STentryEffects.Effect;
import util.STentryEffectsFun;


public class SPDecFun extends SPStmt {
	
	String retType;
	String name;
	List<SPArg> args;
	SPBlock body;
	
	public SPDecFun(String r,String n, List<SPArg> a,SPBlock b){
		retType = r;
		name = n;
		args = a;
		body = b;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		// T fun(T1 a,T2 b) ->> T fun() {T1 a; T2 b;}
		
		//In the environment are not admitted two function with the same name
		//Also are not admitted a function and a variable with the same name
		if(e.containsVariable(name))
			throw new RuntimeException("The function "+ name + " is already defined");
		
		boolean[] funref = new boolean[args.size()];
		String funType = "";
		int argn = 0;
		for(SPArg arg : args) {
			funType = funType + arg.type.toString();
			if(argn!=args.size()-1)
				funType = funType+",";
			if(arg.isref)
				funref[argn]=true;
			argn++;
		}
		funType = funType + "->"+retType;
		
		e.addFunction(name, funType, funref);
		
		//Add fun args as variables of the body block
		//So that they are in the same scope as the 
		//variables in the body
		List<SPDecVar> tmp = new LinkedList<SPDecVar>();
		for(SPArg arg : args) {
			SPDecVar alias = new SPDecVar(arg.type.toString(), arg.name, null);
			tmp.add(alias);
			
		}
		body.children.addAll(0,tmp);
		e.addReturnType(retType);
		body.checkSemantics(e);
		e.rempoveReturnType(retType);
		body.children.removeAll(tmp);
		
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		
		boolean[] funref = new boolean[args.size()];
		int argn=0;
		for(SPArg arg:args) {
			if(arg.isref)
				funref[argn]=true;
			argn++;
		}
		
		Effect[] sigma = new Effect[args.size()];
		for(int i=0;i<sigma.length;i++)
			sigma[i]=Effect.BOTTOM;
		
		STentryEffectsFun entry = new STentryEffectsFun(funref, sigma);
		
		ef.addFunction(name,entry);
		
		
		
		while(true) {
			
			
			EnvironmentEffects e1 = new EnvironmentEffects();
			e1.openScope();
			
			
			
			//At this point checkSemantic has already checked the (NON) existence 
			//of variables with the same name as the arguments of the function
			//Therefore there is no need for the names of the args to be in 
			//the same scope of the body
			for(SPArg arg:args) {
				e1.addVariable(arg.name, Effect.BOTTOM,arg.type.toString());
			}
			
			//Clone all global variables
			EnvironmentEffects GLOBALS = null;
			try {
				GLOBALS = new EnvironmentEffects(e.cloneEnv());
			} catch (CloneNotSupportedException error) {error.printStackTrace();System.exit(-1);}
			
			//Add args to global variables' scope 
			e1.getScopes().addAll(GLOBALS.getScopes());
			
			//According to the environment containing formal parameters
			//and the environment containing the function's effects
			//compute the effects on the arguments
			body.checkEffects(e1, ef);
			
			boolean modyfied = false;
			argn = 0;
			//For each argument of the function
			for(SPArg arg: args) {
				//Effect on arg[n] computed at current iteration (contained in e1)
				Effect arg_e = e1.getEntry(arg.name).getEffect();
				
				//Effect computed at the preview iteration
				Effect arg_old = ef.getScope().get(name).getSigma()[argn];
				
				//Compare old and new effects
				if(
						STentryEffects.effectVal.get(arg_e).intValue() >
						STentryEffects.effectVal.get(arg_old).intValue()
					) {
					modyfied = true;
					//update the effect on the argument, in the function's 
					//environment
					ef.getScope().get(name).getSigma()[argn]=arg_e;
				}
				argn++;
			}
			//If nothing has changed from the preview iteration
			if(!modyfied)//REACHED FIXED POINT
				break;
		}
		
		
		
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw) throws IOException{
		String endl = System.lineSeparator();
		String labelN = EnvironmentCodeGen.getNewLabelN();
		fw.write("b SAFE_"+labelN+endl);
		String label ="funEntry_"+labelN;	
		String labelReturn="funRet_"+labelN;
		fw.write(label+" :"+endl);
		

		int nlBefore = e.getNestingLevel();
		e.setNestingLevel(nlBefore+1);
		
		e.offsetOpenScope();
		
		ArrayList<String> names = new ArrayList<String>();
		
		for(SPArg carg : args) {
			names.add(carg.name);
			//SAVING PARAMS NAMES IN THE ENVIRONMENT
			e.addVariable(carg.name);
		}
		e.setFunctionLabel(name, label,labelReturn,names);
		
		body.codeGen(e, fw);
		e.setNestingLevel(nlBefore);
		
		
		
		fw.write(labelReturn+" :"+endl);
		
		e.offsetCloseScope();
		//Once we have done this instruction
		//the stack pointer is the same as it was before the invocation of codegen of the function's body
		
		
		/*
		 * Memory contains old Ret address
		 * 
		 * Situation of cells over time :
		 * 
		 * Mem		$t0		$a0		$ra
		 * 
		 * OLD_RA	-		-		NEW_RA		Execute Mem->t0
		 * 
		 * OLD_RA	OLD_RA	-		NEW_RA		Execute ra->a0
		 * 
		 * OLD_RA	OLD_RA	NEW_RA	NEW_RA		Execute t0->ra
		 * 
		 * OLD_RA	OLD_RA	NEW_RA	OLD_RA		Execute a0->t0
		 * 
		 * OLD_RA	NEW_RA	NEW_RA	OLD_RA
		 * 
		 * 
		 * Now when VM executes JR $t0 the program 
		 * jumps to the last $ra value generated but
		 * the actual $ra register contains the value of 
		 * the outer scope's return address
		 * 
		 * 
		 * */
		
		
		//Free space occupied by all variables in scope
		fw.write("move $sp $fp"+endl);
		
		fw.write("#READ OLD $RA"+endl);
		
		fw.write("lw $t0 -4($sp)"+endl);
		fw.write("move $a0 $ra"+endl);
		fw.write("move $ra $t0"+endl);
		fw.write("move $t0 $a0"+endl);
		
		//Restore old frame pointer
		fw.write("lw $fp 0($sp)"+endl);
		
		fw.write("jr $t0"+endl);
		
		
		//SAFE LABEL
		fw.write("SAFE_"+labelN+" :"+endl);
		
	}

}
