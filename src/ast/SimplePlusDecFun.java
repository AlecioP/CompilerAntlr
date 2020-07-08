package ast;

import java.util.List;

import util.Environment;
import util.SemanticError;

public class SimplePlusDecFun extends SimplePlusStmt {
	
	String retType;
	String name;
	List<SimplePlusArg> args;
	SimplePlusBlock body;
	
	public SimplePlusDecFun(String r,String n, List<SimplePlusArg> a,SimplePlusBlock b){
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
