package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
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
		if(value!=null)
			value.checkSemantics(e);

		String ro= value.getType(e);
		if(!ro.equals(type)){
			throw new RuntimeException("Type mismatch between left and right operands");
		}
		e.addVariable(name, type);


	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		if(e.getEntry(name)!=null){
			Effect status =e.getEntry(name).getEffect();
			if(!status.equals(Effect.DELETE)){
				throw new RuntimeException("Variable "+name+" already declared in this scope");
			}
			if(!e.getEntry(name).getType().equals(type))
				throw new RuntimeException("Can't change type of variable "+name);
		}
		if(value==null)
			e.addVariable(name, Effect.BOTTOM,type);

		else {
			value.checkEffects(e, ef);
			e.addVariable(name, Effect.RW,type);
		}


	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException {
		e.addVariable(name);
		if(value != null){
			SPAssignment a = new SPAssignment(name, value);
			a.codeGen(e, fw);
		}

	}

}
