package ast;

import util.Environment;


public class SPPrint extends SPStmt {
	
	SPExp value;

	public SPPrint(SPExp value) {
		this.value = value;
	}



	@Override
	public void checkSemantics(Environment e) {
		value.checkSemantics(e);
		String type = value.getType(e);
		if(!(type == "int" || type =="bool"))
			throw new RuntimeException("Cannot print expression");
	}

}
