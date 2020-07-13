package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
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
	public void checkEffects(EnvironmentEffects e) {
		
		guard.checkEffects(e);
		
		EnvironmentEffects e1 = null;
		try {
			e1 = new EnvironmentEffects(e.cloneEnv());
		} catch (CloneNotSupportedException ex) {ex.printStackTrace();}
		
		then_.checkEffects(e);
		else_.checkEffects(e1);
		
		//Merge environments saves in e
		EnvironmentEffects.mergeEnvs(e, e1);
		
	}


	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
