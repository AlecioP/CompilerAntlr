package ast;

import util.Environment;


public class SPUnaryOperation extends SPElementBase {
	
	String operator;
	SPExp value;

	public SPUnaryOperation(String operator, SPExp value) {
		this.operator = operator;
		this.value = value;
	}

	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		
	}

}
