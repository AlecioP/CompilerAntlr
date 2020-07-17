package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPArg extends SPElementBase {
	
	String name;
	SPType type;
	boolean isref;
	

	public SPArg(String name, SPType type, boolean isref) {
		this.name = name;
		this.type = type;
		this.isref = isref;
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
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException {
		// TODO Auto-generated method stub
		
	}

}
