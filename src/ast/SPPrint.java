package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public class SPPrint extends SPStmt {
	
	SPExp value;

	public SPPrint(SPExp value) {
		this.value = value;
	}



	@Override
	public void checkSemantics(EnvironmentTypes e) {
		value.checkSemantics(e);
		String type = value.getType(e);
		if(!(type == "int" || type =="bool"))
			throw new RuntimeException("Cannot print expression");
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
