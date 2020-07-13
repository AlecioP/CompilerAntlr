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
		
		
	}


	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
