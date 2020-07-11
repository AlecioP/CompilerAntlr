package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;

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
	public void checkSemantics(EnvironmentTypes e) {
		
		if (e.containsTop(name)) {
			throw new RuntimeException("Variable "+name+" already declared in this scope");
		}
		
		value.checkSemantics(e);
		
		String ro= value.getType(e);
		if(!ro.equals(type)){
			throw new RuntimeException("Type mismatch between left and right operands");
		}
		e.addVariable(name, type);
		
		
	}

	@Override
	public void checkEffects(EnvironmentEffects e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
