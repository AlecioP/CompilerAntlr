package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPBlock extends SPStmt {
	
	List<SPStmt> children;
	
	

	public SPBlock(List<SPStmt> children) {
		this.children = children;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
