package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPType extends SPElementBase {
	public enum spType {
		INT,BOOL,VOID;
	}
	spType value;
	
	public SPType(spType value) {
		this.value = value;
	}


	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
