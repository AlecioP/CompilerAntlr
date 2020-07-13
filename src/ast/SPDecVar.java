package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;
import util.STentryEffects.Effect;

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
		if(value==null)
			e.addVariable(name, Effect.BOTTOM);
		
		else {
			value.checkEffects(e);
			e.addVariable(name, Effect.RW);
		}
			
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
