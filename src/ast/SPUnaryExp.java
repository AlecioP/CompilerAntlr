package ast;

import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPUnaryExp extends SPExp {
	

	String operator;
	SPExp value;

	public SPUnaryExp(String operator, SPExp value) {
		this.operator = operator;
		this.value = value;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		switch(operator) {
		case"-":{
			if(value.getType(e).equals("int")==false)
				throw new RuntimeException("Cannot compute negative of non integer value");
			break;
		}
		case"!":{
			if(value.getType(e).equals("bool")==false)
				throw new RuntimeException("Cannot compute NOT of non boolean value");
			break;
		}
			
		}
	}

	@Override
	public String getType(EnvironmentTypes e) {
		switch(operator) {
		case"-":
			return "int";
		case "!":
			return "bool";
		}
		return null;
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		value.checkEffects(e, ef);
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException {
		value.codeGen(e, fw);
		switch(operator) {
		case"-":{
			fw.write("neg $a0");
			break;
		}
		case "!":{
			String nl = System.lineSeparator();
			String labeln = EnvironmentCodeGen.getNewLabelN();
			fw.write("beq $a0 1 "+"RET_F"+labeln+nl);
			fw.write("li $a0 1"+nl);
			fw.write("b END"+labeln+nl);
			fw.write("RET_F"+labeln+" :"+nl);
			fw.write("li $a0 0"+nl);
			fw.write("END"+labeln+" :"+nl);
			
		}
		}
		
	}

}
