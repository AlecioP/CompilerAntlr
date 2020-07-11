package ast;

import java.util.List;

import util.Environment;

public class SPCall extends SPStmt {
	
	String name;
	List<SPExp> args;

	public SPCall(String name, List<SPExp> args) {
		this.name = name;
		this.args = args;
	}

	@Override
	public void checkSemantics(Environment e) {
		
		
		// "T1,T2..Tn->T"
		
		
		String[] in_t = e.getEntry(name).getType().split("->")[0].split(",");
		
		if(args.size()!=in_t.length)
			throw new RuntimeException("Wrong number of arguments for function "+name);
		
		int it = 0;
		for(SPExp arg : args) {
			arg.checkSemantics(e);
			String curr = arg.getType(e);
			if(curr != in_t[it])
				throw new RuntimeException("Type mismatch for argument "+it+" of function "+name);
			it++;
		}
		
		
	}

}
