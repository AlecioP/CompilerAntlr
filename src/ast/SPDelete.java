package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPDelete extends SPStmt {
	
	String name;
	
	

	public SPDelete(String name) {
		this.name = name;
	}



	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
