package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPUnaryOperation extends SPElementBase {
	
	String operator;
	SPExp value;

	public SPUnaryOperation(String operator, SPExp value) {
		this.operator = operator;
		this.value = value;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
