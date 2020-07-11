package ast;

import util.Environment;


public class SPCallExp extends SPExp {
	
	SPCall value;

	public SPCallExp(SPCall value) {
		this.value=value;
	}

	@Override
	public void  checkSemantics(Environment e) {
		value.checkSemantics(e);
	}

	@Override
	public String getType(Environment e) {
		// "T1,T2..Tn->T"
		String output_t = e.getEntry(value.name).getType().split("->")[1]; 
		return output_t;
	}

}
