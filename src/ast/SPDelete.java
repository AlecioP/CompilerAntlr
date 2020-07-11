package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public class SPDelete extends SPStmt {
	
	String name;
	
	

	public SPDelete(String name) {
		this.name = name;
	}



	@Override
	public void checkSemantics(EnvironmentTypes e) {
		if(!e.containsVariable(name))
			throw new RuntimeException("Deleting undeclared variable");

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
