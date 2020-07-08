package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SimplePlusDecVar extends SimplePlusStmt {
	String type;
	String name;
	SimplePlusExp value;
	
	public SimplePlusDecVar(String type, String name, SimplePlusExp value) {
		this.type = type;
		this.name = name;
		this.value = value;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
