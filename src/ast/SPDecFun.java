package ast;

import java.util.List;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkEffects(EnvironmentEffects e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
