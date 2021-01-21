package ast;

import java.io.FileWriter;
import java.io.IOException;

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
	public void codeGen(EnvironmentCodeGen e, FileWriter fw) throws IOException {
		String endl = System.lineSeparator();
		String labeln = EnvironmentCodeGen.getNewLabelN();
		right.codeGen(e, fw);
		fw.write("sw $a0 0($sp)"+endl);
		fw.write("addi $sp -4"+endl);
		left.codeGen(e, fw);
		fw.write("addi $sp 4"+endl);
		fw.write("lw $t0 0($sp)"+endl);
		switch (operator) {
		case "+":{
			fw.write("add $a0 $a0 $t0"+endl);
			break;
		}
		case "-":{
			fw.write("neg $t0"+endl);
			fw.write("add $a0 $a0 $t0"+endl);
			break;
		}
		case "*":{
			fw.write("mult $a0 $a0 $t0"+endl);
			break;
		}
		case "/":{
			fw.write("div $a0 $a0 $t0"+endl);
			break;
		}
		case "==":{
			fw.write("beq $a0 $t0 RET_T"+labeln+endl);
			fw.write("li $a0 0"+endl);
			fw.write("b END"+labeln+endl);
			fw.write("RET_T"+labeln+" :"+endl);
			fw.write("li $a0 1"+endl);
			fw.write("END"+labeln+" :"+endl);
			
			break;
		}
		case "!=":{
			fw.write("beq $a0 $t0 RET_F"+labeln+endl);
			fw.write("li $a0 1"+endl);
			fw.write("b END"+labeln+endl);
			fw.write("RET_F"+labeln+" :"+endl);
			fw.write("li $a0 0"+endl);
			fw.write("END"+labeln+" :"+endl);
			
			break;
		}
		case ">=":{
			// A0 >= T0
			fw.write("bge $a0 $t0 RET_T"+labeln+endl);
			fw.write("li $a0 0"+endl);
			fw.write("b END"+labeln+endl);
			fw.write("RET_T"+labeln+" :"+endl);
			fw.write("li $a0 1"+endl);
			fw.write("END"+labeln+" :"+endl);
			
			break;
			
		}
		case "<":{
			// A0 < T0
			fw.write("bge $a0 $t0 RET_F"+labeln+endl);
			fw.write("li $a0 1"+endl);
			fw.write("b END"+labeln+endl);
			fw.write("RET_F"+labeln+" :"+endl);
			fw.write("li $a0 0"+endl);
			fw.write("END"+labeln+" :"+endl);
			
			break;
			
		}
		case "<=":{
			// A0 <= T0
			fw.write("ble $a0 $t0 RET_T"+labeln+endl);
			fw.write("li $a0 0"+endl);
			fw.write("b END"+labeln+endl);
			fw.write("RET_T"+labeln+" :"+endl);
			fw.write("li $a0 1"+endl);
			fw.write("END"+labeln+" :"+endl);
			
			break;
			
		}
		case ">":{
			// A0 > T0
			fw.write("ble $a0 $t0 RET_F"+labeln+endl);
			fw.write("li $a0 1"+endl);
			fw.write("b END"+labeln+endl);
			fw.write("RET_F"+labeln+" :"+endl);
			fw.write("li $a0 0"+endl);
			fw.write("END"+labeln+" :"+endl);
			
			break;
			
		}
		case "&&":{
			fw.write("beq $a0 0 RET_F"+labeln+endl);
			fw.write("beq $a0 0 RET_F"+labeln+endl);
			fw.write("li $a0 1"+endl);
			fw.write("b END"+labeln+endl);
			fw.write("RET_F"+labeln+" :"+endl);
			fw.write("li $a0 0"+endl);
			fw.write("END"+labeln+" :"+endl);
			break;
		}
		case "||":{
			fw.write("beq $a0 1 RET_T"+labeln+endl);
			fw.write("beq $a0 1 RET_T"+labeln+endl);
			fw.write("li $a0 0"+endl);
			fw.write("b END"+labeln+endl);
			fw.write("RET_T"+labeln+" :"+endl);
			fw.write("li $a0 1"+endl);
			fw.write("END"+labeln+" :"+endl);
			break;
		}
		default:
			throw new RuntimeException("Unknown operator for binary expression");
		}
		
	}
	
	

}
