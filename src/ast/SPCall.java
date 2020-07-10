package ast;

import java.util.List;

import util.Environment;

public class SPCall extends SPStmt {
	
	String name;
	List<SPExp> args;

	public SPCall(String name, List<SPExp> args) {
		this.name = name;
		this.args = args;
	}

	@Override
	public void checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		
	}

}
