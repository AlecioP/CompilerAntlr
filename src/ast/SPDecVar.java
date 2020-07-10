package ast;

import util.Environment;

public class SPDecVar extends SPStmt {
	String type;
	String name;
	SPExp value;
	
	public SPDecVar(String type, String name, SPExp value) {
		this.type = type;
		this.name = name;
		this.value = value;
	}
	
	@Override
	public void checkSemantics(Environment e) {
		
		if (e.containsTop(name)) {
			throw new RuntimeException("Variable "+name+" already declared in this scope");
		}
		String ro= value.getType();
		if(!ro.equals(type)){
			throw new RuntimeException("Type mismatch between left and right operands");
		}
		e.addVariable(name, type);
		
		
	}

}
