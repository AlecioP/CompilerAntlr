package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPIfelse extends SPStmt {
	
	SPExp guard;
	SPStmt then_;
	SPStmt else_;
	

	public SPIfelse(SPExp guard, SPStmt then_, SPStmt else_) {
		this.guard = guard;
		this.then_ = then_;
		this.else_ = else_;
	}


	@Override
	public void checkSemantics(EnvironmentTypes e) {
		guard.checkSemantics(e);
		String t1 = guard.getType(e);
		if(t1 != "bool")
			throw new RuntimeException("Guard clause is not of bool type");
		
		then_.checkSemantics(e);
		
		if(else_ != null)
			else_.checkSemantics(e);
	}


	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		
		guard.checkEffects(e, ef);
		
		EnvironmentEffects e1 = null;
		try {
			e1 = new EnvironmentEffects(e.cloneEnv());
		} catch (CloneNotSupportedException ex) {ex.printStackTrace();}
		
		then_.checkEffects(e, ef);
		else_.checkEffects(e1, ef);
		
		//Merge environments saves in e
		EnvironmentEffects.mergeEnvs(e, e1);
		
	}


	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw) throws IOException{
		String labeln = EnvironmentCodeGen.getNewLabelN();
		
		String endl = System.lineSeparator();
		fw.write("# IF GUARD"+endl);
		guard.codeGen(e, fw);
		
		fw.write("# IF CHECK"+endl);
		fw.write("beq $a0 1 THEN"+labeln+endl);
		
		fw.write("# ELSE BRANCH"+endl);
		else_.codeGen(e, fw);
		fw.write("b END"+labeln+endl);
		
		fw.write("THEN"+labeln+" :"+endl);
		then_.codeGen(e, fw);
		fw.write("END"+labeln+" :"+endl);
		
		
	}

}
