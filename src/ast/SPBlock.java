package ast;

import java.util.List;

import util.Environment;


public class SPBlock extends SPStmt {
	
	List<SPStmt> children;
	
	

	public SPBlock(List<SPStmt> children) {
		this.children = children;
	}

	@Override
	public void checkSemantics(Environment e) {
		e.openScope();
		for(SPStmt el : children){
			el.checkSemantics(e);
		}
		e.closeScope();
		
	}

}
