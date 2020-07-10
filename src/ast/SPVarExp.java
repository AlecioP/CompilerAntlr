package ast;

import util.Environment;


public class SPVarExp extends SPExp {

	public SPVarExp(SPVar value) {
		super(value);
	}

	@Override
	public void checkSemantics(Environment e) {
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
