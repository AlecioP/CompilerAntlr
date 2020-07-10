package ast;

import util.Environment;


public class SPNumExp extends SPExp {
	
	

	public SPNumExp(SPNumber value) {
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
