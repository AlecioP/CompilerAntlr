package ast;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;
import util.STentryEffects;
import util.STentryEffects.Effect;
import util.STentryEffectsFun;

public class SPCall extends SPStmt {

	String name;
	List<SPExp> args;

	public SPCall(String name, List<SPExp> args) {
		this.name = name;
		this.args = args;
	}

	@Override
	public void checkSemantics(EnvironmentTypes e) {


		// "T1,T2..Tn->T"


		String[] in_t = e.getEntry(name).getType().split("->")[0].split(",");

		if(args.size()!=in_t.length)
			throw new RuntimeException("Wrong number of arguments for function "+name);

		int it = 0;
		for(SPExp arg : args) {
			arg.checkSemantics(e);
			String curr = arg.getType(e);
			if(!curr.equals( in_t[it]))
				throw new RuntimeException("Type mismatch for argument "+it+" of function "+name);

			if(
					arg.getClass().getSimpleName().equals("SPVarExp")==false 
					&& 
					e.getEntry(name).getFunRefArgs()[it]
					) {
				throw new RuntimeException("Argument "+it+" of function "+name+" must be passed by reference");
			}

			it++;
		}


	}

	@Override
	public void checkEffects(EnvironmentEffects e, EnvironmentEffectsFun ef) {
		HashMap<String,Effect> aliasingMap = new HashMap<String,Effect>();
		STentryEffectsFun entry = ef.getEntry(name);
		Iterator<SPExp> it = args.iterator();
		for(int i=0;i<entry.getFunRefArgs().length;i++) {
			SPExp argument = it.next();
			if(entry.getFunRefArgs()[i]) {
				SPVarExp arg = (SPVarExp) argument;
				Effect funOut = entry.getSigma()[i];
				if(aliasingMap.containsKey(arg.name)) {//ALIASING
					Effect curr = e.getEntry(arg.name).getEffect();
					Effect seq1 = STentryEffects.sequence(curr, funOut);
					Effect seq2 = aliasingMap.get(arg.name);
					Effect par = STentryEffects.parallel(seq1, seq2);
					aliasingMap.replace(arg.name, par);
				}else {

					Effect seq =STentryEffects.sequence(e.getEntry(arg.name).getEffect(), funOut);
					aliasingMap.put(arg.name, seq);
				}

			}
		}
		Iterator<Entry<String,Effect>> mapit = aliasingMap.entrySet().iterator();
		while(mapit.hasNext()) {
			Entry<String,Effect> curr = mapit.next();
			if(curr.getValue().equals(Effect.TOP))
				throw new RuntimeException("Aliasing error in call of function "+name);
			e.update(curr.getKey(), curr.getValue());
		}

		Iterator<SPExp> it1 = args.iterator();
		for(int i=0;i<entry.getFunRefArgs().length;i++) {
			SPExp curr = it1.next();
			if(!entry.getFunRefArgs()[i])
				curr.checkEffects(e, ef);
		}

	}

	@Override
	public void codeGen(EnvironmentCodeGen e,FileWriter fw) throws IOException{
		String endl = System.lineSeparator();
		e.offsetOpenScope();
		e.getCallStack().add(name);
		fw.write("# CALL OF FUNCTION"+endl);
		fw.write("sw $fp 0($sp)"+endl);
		fw.write("move $fp $sp"+endl);
		fw.write("li $a0 -4"+endl);
		fw.write("add $sp $sp $a0"+endl);
		for(SPExp arg : args) {
			arg.codeGen(e, fw);
			fw.write("sw $a0 0($sp)"+endl);
			fw.write("li $a0 -4"+endl);
			fw.write("add $sp $sp $a0"+endl);
		}
		String fEntry=e.getFunctionLabel(name);
		fw.write("jal "+fEntry+endl);
		e.getCallStack().remove();
		e.offsetCloseScope();

	}

}
