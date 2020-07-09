package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPReturn extends SPStmt {
	
	SPExp value;

	public SPReturn(SPExp value) {
		this.value = value;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
