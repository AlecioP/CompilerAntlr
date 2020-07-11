package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public class SPVarExp extends SPExp {
	
	String name;

	public SPVarExp(String name) {
		this.name = name;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		
	}

	@Override
	public String getType(EnvironmentTypes e) {
		return e.getEntry(name).getType();
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
