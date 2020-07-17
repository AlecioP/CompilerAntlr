package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public abstract class SPElementBase {
	
	public abstract void checkSemantics(EnvironmentTypes e);
	
	public abstract void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef);
	
	public abstract void codeGen(EnvironmentCodeGen e, FileWriter fw) throws IOException;
	
}
