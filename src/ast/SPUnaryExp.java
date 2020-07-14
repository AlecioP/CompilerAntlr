package ast;

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
	public void codeGen(EnvironmentCodeGen e) {
		// TODO Auto-generated method stub
		
	}

}
