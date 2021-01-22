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


public class SPVarExp extends SPExp {
	
	String name;

	public SPVarExp(String name) {
		this.name = name;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		
	}

	@Override
	public String getType(EnvironmentTypes e) {
		return e.getEntry(name).getType();
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		Effect status=e.getEntry(name).getEffect();
		Effect val=STentryEffects.sequence(status, Effect.RW);
		if(val.equals(Effect.TOP)){
			throw new RuntimeException("The variable "+ name +" is already deleted");
		}
		e.update(name, val);
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException {
		String endl = System.lineSeparator();
		STentryCodeGen entry = e.getEntry(name);
		fw.write("move $al $fp"+endl);
		for(int i=e.getCurrentLevel(); i>entry.getNl();i--){
			fw.write("lw $al 0($al)"+endl);
		}
		int OFFSET= (-1)*(entry.getOffset()+2)*EnvironmentCodeGen.WORDDIM;
		fw.write("lw $a0 "+OFFSET+"($al)"+endl);
	}
	
}
