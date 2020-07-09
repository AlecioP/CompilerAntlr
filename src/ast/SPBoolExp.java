package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPBoolExp extends SPExp {

	public SPBoolExp(SPBoolean value) {
		super(value);
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
