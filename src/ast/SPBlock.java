package ast;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
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
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		e.openScope();
		for(SPStmt el : children){
			el.checkEffects(e, ef);
		}
		e.closeScope();
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw) throws IOException{
		e.openScope();
		for(SPStmt el : children){
			el.codeGen(e, fw);
			if(el.getClass().getSimpleName().equals("SPReturn")) 
				break;
		}
		e.closeScope();
		
		
	}

}
