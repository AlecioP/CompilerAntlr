package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public class SPType extends SPElementBase {
	public enum spType {
		INT,BOOL,VOID;
	}
	spType value;
	
	public SPType(spType value) {
		this.value = value;
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
