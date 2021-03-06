package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPPrint extends SPStmt {
	
	SPExp value;

	public SPPrint(SPExp value) {
		this.value = value;
	}



	@Override
	public void checkSemantics(EnvironmentTypes e) {
		value.checkSemantics(e);
		String type = value.getType(e);
		if(!(type.equals("int") || type.equals("bool")))
			throw new RuntimeException("Cannot print expression");
	}



	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		value.checkEffects(e, ef);
		
	}



	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException{
		value.codeGen(e, fw);
		fw.write("print $a0"+System.lineSeparator());
	}

}
