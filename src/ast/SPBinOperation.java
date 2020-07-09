package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPBinOperation extends SPElementBase {
	
	SPExp right;
	SPExp left;
	String operator;
	
	

	public SPBinOperation(SPExp right, SPExp left, String operator) {
		this.right = right;
		this.left = left;
		this.operator = operator;
	}



	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
