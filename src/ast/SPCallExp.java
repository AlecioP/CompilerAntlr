package ast;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
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
	public void checkEffects(EnvironmentEffects e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
