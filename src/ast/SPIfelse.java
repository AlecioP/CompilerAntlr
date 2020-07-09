package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

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
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
