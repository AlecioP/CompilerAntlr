package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentTypes;


public abstract class SPElementBase {
	
	public abstract void checkSemantics(EnvironmentTypes e);
	
	public abstract void checkEffects(EnvironmentEffects e);
	
	public abstract void codeGen(EnvironmentCodeGen e);
	
}
