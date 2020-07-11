package ast;

import util.Environment;


public class SPBoolExp extends SPExp {

	Boolean value;

	public SPBoolExp(Boolean value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(Environment e) {
	
	}

	@Override
	public String getType(Environment e) {
		return "bool";
	}

}
