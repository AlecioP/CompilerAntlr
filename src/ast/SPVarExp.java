package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;
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
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}
	
}
