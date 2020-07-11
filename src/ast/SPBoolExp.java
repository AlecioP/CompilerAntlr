package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public class SPBoolExp extends SPExp {

	Boolean value;

	public SPBoolExp(Boolean value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
	
	}

	@Override
	public String getType(EnvironmentTypes e) {
		return "bool";
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
