package ast;

import util.Environment;


public class SPVar extends SPElementBase {
	
	String name;

	public SPVar(String name){
		this.name=name;
	}
	
	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		
	}

}
