package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public abstract class SPElementBase {
	
	public abstract void checkSemantics(EnvironmentTypes e);
	
	public abstract void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef);
	
	public abstract void codeGen(EnvironmentCodeGen e);
	
}
