package ast;



import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPReturn extends SPStmt {
	
	SPExp value;

	public SPReturn(SPExp value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		value.checkSemantics(e);
		String type = value.getType(e);
		if(type != e.getCurrentReturnType())
			throw new RuntimeException("Type mismatch of return expression");
		
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		value.checkEffects(e, ef);
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException{
		String endl= System.lineSeparator();
		if(value!=null)
			value.codeGen(e, fw);
		String nameFunction=e.getCallStack().getLast();
		if(nameFunction.equals("main")) {
			fw.write("halt"+endl);
			return;
		}
		String retPoint= e.getEntry(nameFunction).getFunRetLabel();
		fw.write("jr "+retPoint+endl);
		
	}

}
