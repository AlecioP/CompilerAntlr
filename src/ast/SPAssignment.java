package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPAssignment extends SPStmt {
	
	String name;
	SPExp value;
	
	public SPAssignment(String name, SPExp value) {
		this.name = name;
		this.value = value;
	}



	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
