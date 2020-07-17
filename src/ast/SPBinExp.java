package ast;

import java.io.FileWriter;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;


public class SPBinExp extends SPExp {
	
	SPExp right;
	SPExp left;
	String operator;

	public SPBinExp(SPExp right, SPExp left, String operator) {
		this.right = right;
		this.left = left;
		this.operator = operator;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		switch (operator) {
		case "+":
		case "-":
		case "*":
		case "/":
		case "<":
		case "<=":
		case ">":
		case ">=":{
			if(left.getType(e).equals("int")==false)
				throw new RuntimeException("Left operand of arithmentic operator must be an integer");
			if(right.getType(e).equals("int")==false)
				throw new RuntimeException("Right operand of arithmentic operator must be an integer");
			break;
		}
		case "==":
		case "!=":{
			if(left.getType(e).equals(right.getType(e))==false)
				throw new RuntimeException("Operands of a boolean operator must be of the same type");
			break;
		}
		default:// operator&& operator||
			if(left.getType(e).equals("bool")==false)
				throw new RuntimeException("Left operand of boolean operator must be of type boolean");
			if(right.getType(e).equals("Bool")==false)
				throw new RuntimeException("Right operand of boolean operator must be of type boolean");
			break;
		}
		
	}

	@Override
	public String getType(EnvironmentTypes e) {
		
		switch (operator) {
		case "+":
		case "-":
		case "*":
		case "/":
			return "int";
		case "<":
		case "<=":
		case ">":
		case ">=":
		case "==":
		case "!=":
		case "&&":
		case "||":
			return "bool";
		}
		return null;
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		right.checkEffects(e, ef);
		left.checkEffects(e, ef);
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw) {
		// TODO Auto-generated method stub
		
	}
	
	

}
