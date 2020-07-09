package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPNumber extends SPElementBase {
	
	Integer value;

	public SPNumber(Integer value) {
		this.value = value;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
