package ast;

import util.Environment;


public class SPUnaryExp extends SPExp {
	

	public SPUnaryExp(SPUnaryOperation value) {
		super(value);
	}

	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
