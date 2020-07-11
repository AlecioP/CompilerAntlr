package ast;


import util.Environment;


public class SPAssignment extends SPStmt {
	
	String name;
	SPExp value;
	
	public SPAssignment(String name, SPExp value) {
		this.name = name;
		this.value = value;
	}



	@Override
	public void checkSemantics(Environment e) {
		if(!e.containsVariable(name)) {
			throw new RuntimeException("Variable "+name+" is not declared in this scope");
		}
		
		value.checkSemantics(e);
		
		String ro= value.getType(e);
		if(!ro.equals(e.getEntry(name).getType())){
			throw new RuntimeException("Type mismatch between left and right operands");
		}
		
	}

}
