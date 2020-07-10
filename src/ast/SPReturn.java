package ast;



import util.Environment;


public class SPReturn extends SPStmt {
	
	SPExp value;

	public SPReturn(SPExp value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		
	}

}
