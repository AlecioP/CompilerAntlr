package ast;

import util.Environment;


public class SPType extends SPElementBase {
	public enum spType {
		INT,BOOL,VOID;
	}
	spType value;
	
	public SPType(spType value) {
		this.value = value;
	}


	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		
	}

}
