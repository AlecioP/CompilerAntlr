package ast;

import util.Environment;


public class SPNumExp extends SPExp {
	
	
	Integer value;

	public SPNumExp(Integer value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(Environment e) {
		
	}

	@Override
	public String getType(Environment e) {
		return "int";
	}

}
