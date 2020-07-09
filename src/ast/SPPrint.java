package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPPrint extends SPStmt {
	
	SPExp value;

	public SPPrint(SPExp value) {
		this.value = value;
	}



	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
