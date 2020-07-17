package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPCallExp extends SPExp {
	
	SPCall value;

	public SPCallExp(SPCall value) {
		this.value=value;
	}

	@Override
	public void  checkSemantics(EnvironmentTypes e) {
		value.checkSemantics(e);
	}

	@Override
	public String getType(EnvironmentTypes e) {
		// "T1,T2..Tn->T"
		String output_t = e.getEntry(value.name).getType().split("->")[1]; 
		return output_t;
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		value.checkEffects(e, ef);
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException{
		// TODO Auto-generated method stub
		
	}

}
