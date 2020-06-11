package ast;

import java.util.List;

import behaviour.BTBase;
import util.Environment;
import util.SemanticError;

public class SimplePlusBlock extends SimplePlusStmt {
	
	List<SimplePlusStmt> children;
	
	

	public SimplePlusBlock(List<SimplePlusStmt> children) {
		this.children = children;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
