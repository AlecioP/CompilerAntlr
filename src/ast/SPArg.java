package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPArg extends SPElementBase {
	
	String name;
	SPType type;
	boolean isref;
	

	public SPArg(String name, SPType type, boolean isref) {
		this.name = name;
		this.type = type;
		this.isref = isref;
	}


	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
