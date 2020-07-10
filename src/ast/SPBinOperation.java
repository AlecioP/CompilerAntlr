package ast;


import util.Environment;


public class SPBinOperation extends SPElementBase {
	
	SPExp right;
	SPExp left;
	String operator;
	
	

	public SPBinOperation(SPExp right, SPExp left, String operator) {
		this.right = right;
		this.left = left;
		this.operator = operator;
	}



	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		 
	}

}
