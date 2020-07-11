package ast;

import util.Environment;


public class SPVarExp extends SPExp {
	
	String name;

	public SPVarExp(String name) {
		this.name = name;
	}

	@Override
	public void checkSemantics(Environment e) {
		
	}

	@Override
	public String getType(Environment e) {
		return e.getEntry(name).getType();
	}
	
}
