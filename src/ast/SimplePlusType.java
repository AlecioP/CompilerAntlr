package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SimplePlusType extends SimplePlusElementBase {
	public enum spType {
		INT,BOOL,VOID;
	}
	spType value;
	
	public SimplePlusType(spType value) {
		this.value = value;
	}


	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
