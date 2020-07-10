package ast;

import util.Environment;


public class SPPrint extends SPStmt {
	
	SPExp value;

	public SPPrint(SPExp value) {
		this.value = value;
	}



	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		
	}

}
