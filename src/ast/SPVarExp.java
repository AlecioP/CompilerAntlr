package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPVarExp extends SPExp {

	public SPVarExp(SPVar value) {
		super(value);
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
