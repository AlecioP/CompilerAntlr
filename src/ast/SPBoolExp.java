package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPBoolExp extends SPExp {

	Boolean value;

	public SPBoolExp(Boolean value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		return;
	}

	@Override
	public String getType(EnvironmentTypes e) {
		return "bool";
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		return;
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException {
		// TODO Auto-generated method stub
		
	}

}
