package ast;

import util.Environment;


public class SPArg extends SPElementBase {
	
	String name;
	SPType type;
	boolean isref;
	

	public SPArg(String name, SPType type, boolean isref) {
		this.name = name;
		this.type = type;
		this.isref = isref;
	}


	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		
	}

}
