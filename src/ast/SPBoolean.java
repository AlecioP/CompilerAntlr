package ast;

import util.Environment;


public class SPBoolean extends SPElementBase {
	
	Boolean value;

	public SPBoolean(Boolean value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		 
	}

}
