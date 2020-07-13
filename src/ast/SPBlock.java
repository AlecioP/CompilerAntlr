package ast;

import java.util.List;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public class SPBlock extends SPStmt {
	
	List<SPStmt> children;
	
	

	public SPBlock(List<SPStmt> children) {
		this.children = children;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		e.openScope();
		for(SPStmt el : children){
			el.checkSemantics(e);
		}
		e.closeScope();
		
	}

	@Override
	public void checkEffects(EnvironmentEffects e) {
		e.openScope();
		for(SPStmt el : children){
			el.checkEffects(e);
		}
		e.closeScope();
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
