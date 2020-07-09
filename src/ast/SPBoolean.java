package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPBoolean extends SPElementBase {
	
	Boolean value;

	public SPBoolean(Boolean value) {
		this.value = value;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
