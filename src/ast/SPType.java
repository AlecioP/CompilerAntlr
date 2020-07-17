package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPType extends SPElementBase {
	public enum spType {
		INT,BOOL,VOID;
	}
	spType value;
	
	public SPType(spType value) {
		this.value = value;
	}


	@Override
	public void checkSemantics(EnvironmentTypes e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw) throws IOException{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		if(value.equals(spType.INT))
			return "int";
		else if(value.equals(spType.BOOL))
			return "bool";
		else
			return "void";
	}

}
