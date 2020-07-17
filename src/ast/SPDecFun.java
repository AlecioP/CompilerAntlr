package ast;

import java.io.FileWriter;
import java.io.IOException;
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
		body.children.addAll(tmp);
		
		body.checkSemantics(e);
		
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
			
			//At this point checkSemantic has already checked the (NON) existence 
			//of variables with the same name as the arguments of the function
			//Therefore there is no need for the names of the args to be in 
			//the same scope of the body
			for(SPArg arg:args) {
				e1.addVariable(arg.name, Effect.BOTTOM);
			}
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
		// TODO Auto-generated method stub
		
	}

}
