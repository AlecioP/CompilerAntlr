package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SPDecFun extends SPStmt {
	
	String retType;
	String name;
	List<SPArg> args;
	SPBlock body;
	
	public SPDecFun(String r,String n, List<SPArg> a,SPBlock b){
		retType = r;
		name = n;
		args = a;
		body = b;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
