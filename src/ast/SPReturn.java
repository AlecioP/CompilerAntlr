package ast;



import util.Environment;


public class SPReturn extends SPStmt {
	
	SPExp value;

	public SPReturn(SPExp value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(Environment e) {
		value.checkSemantics(e);
		String type = value.getType(e);
		if(type != e.getCurrentReturnType())
			throw new RuntimeException("Type mismatch of return expression");
		
	}

}
