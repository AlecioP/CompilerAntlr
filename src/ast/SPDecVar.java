package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPDecVar extends SPStmt {
	String type;
	String name;
	SPExp value;
	
	public SPDecVar(String type, String name, SPExp value) {
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
