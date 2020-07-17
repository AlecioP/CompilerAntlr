package ast;


import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;
import util.STentryCodeGen;
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
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		value.checkEffects(e, ef);
		Effect status=e.getEntry(name).getEffect();
		Effect val=STentryEffects.sequence(status, Effect.RW);
		if(val.equals(Effect.TOP)){
			throw new RuntimeException("The variable "+ name +" is already deleted");
		}
		e.update(name, val);
	}



	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw) throws IOException {
		String endl = System.lineSeparator();
		STentryCodeGen entry = e.getEntry(name);
		value.codeGen(e, fw);
		fw.write("move $al $fp"+endl);
		for(int i=e.getCurrentLevel(); i<entry.getNl();i--){
			fw.write("lw $al 0($al)"+endl);
		}
		int OFFSET= (entry.getOffset()+1)*EnvironmentCodeGen.WORDDIM;
		fw.write("sw $a0 "+OFFSET+"($al)"+endl);
	}

}
