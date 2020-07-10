package ast;

import util.Environment;


public class SPDelete extends SPStmt {
	
	String name;
	
	

	public SPDelete(String name) {
		this.name = name;
	}



	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub

	}

}
