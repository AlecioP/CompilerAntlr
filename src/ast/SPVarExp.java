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


public class SPVarExp extends SPExp {
	
	String name;

	public SPVarExp(String name) {
		this.name = name;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {
		
	}

	@Override
	public String getType(EnvironmentTypes e) {
		return e.getEntry(name).getType();
	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		Effect status=e.getEntry(name).getEffect();
		Effect val=STentryEffects.sequence(status, Effect.RW);
		if(val.equals(Effect.TOP)){
			throw new RuntimeException("The variable "+ name +" is already deleted");
		}
		e.update(name, val);
		
	}

	@Override
	public void codeGen(EnvironmentCodeGen e, FileWriter fw)throws IOException {
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
		fw.write("lw $a0 "+OFFSET+"($al)"+endl);
	}
	
}
