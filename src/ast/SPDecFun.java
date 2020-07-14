package ast;

import java.util.LinkedList;
import java.util.List;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


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
		
		EnvironmentEffects env1 = new EnvironmentEffects();
		EnvironmentEffects env2 = new EnvironmentEffects();
		
		List<SPDecVar> tmp = new LinkedList<SPDecVar>();
		for(SPArg arg : args) {
			SPDecVar alias = new SPDecVar(arg.type.toString(), arg.name, null);
			tmp.add(alias);
		}
		body.children.addAll(tmp);
		
		while(true) {
			body.checkEffects(env2, null);
			if(! EnvironmentEffects.mergeEnvs(env1, env2))//REACHED A FIXED POINT
				break;
		}
		
		
		
		
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
