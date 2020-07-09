package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPVar extends SPElementBase {
	
	String name;

	public SPVar(String name){
		this.name=name;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
