package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;
import util.STentryEffects;
import util.STentryEffects.Effect;


public class SPDelete extends SPStmt {
	
	String name;
	
	

	public SPDelete(String name) {
		this.name = name;
	}



	@Override
	public void checkSemantics(EnvironmentTypes e) {
		if(!e.containsVariable(name))
			throw new RuntimeException("Deleting undeclared variable");
		//e.remove(name);

	}



	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		Effect status=e.getEntry(name).getEffect();
		Effect val=STentryEffects.sequence(status, Effect.DELETE);
		if(val.equals(Effect.TOP)){
			throw new RuntimeException("The variable "+ name +" is already deleted");
		}
		e.update(name, val);
		
	}



	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException {
		// TODO Auto-generated method stub
		
	}

}
