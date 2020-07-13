package ast;



import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public class SPReturn extends SPStmt {
	
	SPExp value;

	public SPReturn(SPExp value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		value.checkSemantics(e);
		String type = value.getType(e);
		if(type != e.getCurrentReturnType())
			throw new RuntimeException("Type mismatch of return expression");
		
	}

	@Override
	public void checkEffects(EnvironmentEffects e) {
		value.checkEffects(e);
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
