package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public class SPNumExp extends SPExp {
	
	
	Integer value;

	public SPNumExp(Integer value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		
	}

	@Override
	public String getType(EnvironmentTypes e) {
		return "int";
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
