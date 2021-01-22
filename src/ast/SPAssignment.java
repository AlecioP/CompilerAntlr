package ast;


import java.io.FileWriter;
import java.io.IOException;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;
import util.STentryCodeGen;
import util.STentryEffects;
import util.STentryEffects.Effect;


public class SPAssignment extends SPStmt {

	String name;
	SPExp value;

	public SPAssignment(String name, SPExp value) {
		this.name = name;
		this.value = value;
	}



	@Override
	public void checkSemantics(EnvironmentTypes e) {
		if(!e.containsVariable(name)) {
			throw new RuntimeException("Variable "+name+" is not declared in this scope");
		}

		value.checkSemantics(e);

		String ro= value.getType(e);
		if(!ro.equals(e.getEntry(name).getType())){
			throw new RuntimeException("Type mismatch between left and right operands");
		}

	}



	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		value.checkEffects(e, ef);
		Effect status=e.getEntry(name).getEffect();
		Effect val=STentryEffects.sequence(status, Effect.RW);
		if(val.equals(Effect.TOP)){
			throw new RuntimeException("The variable "+ name +" is already deleted");
		}
		e.update(name, val);
	}



	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw) throws IOException {
		String endl = System.lineSeparator();
		STentryCodeGen entry = e.getEntry(name);
		
		String labelN = EnvironmentCodeGen.getNewLabelN();
		
		fw.write("move $al $fp"+endl);
		
		//READ CURRENT NL
		fw.write("lw $a0 -8($fp)"+endl);
		fw.write("ACCESS_LOOP_"+labelN+" :"+endl);
		fw.write("beq $a0 "+entry.getNl()+" ACCESS_"+labelN+endl);
				
		//READ OLD_FP TO EXIT FRAME
		fw.write("lw $al 0($al)"+endl);
				
		fw.write("addi $a0 -1"+endl);
		fw.write("b ACCESS_LOOP_"+labelN+endl);
		
		//OFFSET + 3 because the first 3 cells of the frame are occupied by OLD_FP OLD_RA NESTING_LEVEL
		int OFFSET= (-1)*(entry.getOffset()+3)*EnvironmentCodeGen.WORDDIM;
		
		fw.write("ACCESS_"+labelN+" :"+endl);
		
		value.codeGen(e, fw);
		
		fw.write("sw $a0 "+OFFSET+"($al)"+endl);
	}

}
