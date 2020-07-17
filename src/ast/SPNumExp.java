package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPNumExp extends SPExp {
	
	
	Integer value;

	public SPNumExp(Integer value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		return;
	}

	@Override
	public String getType(EnvironmentTypes e) {
		return "int";
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		return;
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException{
		fw.write("li $a0 "+value.intValue());
		
	}

}
