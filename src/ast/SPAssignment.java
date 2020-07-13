package ast;


import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;
import util.STentryEffects;
import util.STentryEffects.Effect;


public class SPAssignment extends SPStmt {
	
	String name;
	SPExp value;
	
	public SPAssignment(String name, SPExp value) {
		this.name = name;
		this.value = value;
	}



	@Override
	public void checkSemantics(EnvironmentTypes e) {
		if(!e.containsVariable(name)) {
			throw new RuntimeException("Variable "+name+" is not declared in this scope");
		}
		
		value.checkSemantics(e);
		
		String ro= value.getType(e);
		if(!ro.equals(e.getEntry(name).getType())){
			throw new RuntimeException("Type mismatch between left and right operands");
		}
		
	}



	@Override
	public void checkEffects(EnvironmentEffects e) {
		value.checkEffects(e);
		Effect status=e.getEntry(name).getEffect();
		Effect val=STentryEffects.sequence(status, Effect.RW);
		if(val.equals(Effect.TOP)){
			throw new RuntimeException("The variable "+ name +" is already deleted");
		}
		e.update(name, val);
	}



	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
