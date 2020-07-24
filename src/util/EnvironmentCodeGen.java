package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class EnvironmentCodeGen {
	int offset=0;
	int nestingLevel=0;
	public final static int  WORDDIM=4;
	
	static int LABEL_N = 0;
	LinkedList<HashMap<String, STentryCodeGen>> scopes = new LinkedList<HashMap<String,STentryCodeGen>>();

	public STentryCodeGen getEntry(String id) {
		for(HashMap<String, STentryCodeGen> scope:scopes){
			if(scope.containsKey(id)) {
				return scope.get(id);
			}
		}
		return null;
	}
	public int getCurrentLevel(){
		return nestingLevel;
	}
	public String getFunctionLabel(String ID) {
		Iterator<HashMap<String, STentryCodeGen>> it =scopes.descendingIterator();
		HashMap<String, STentryCodeGen> map = it.next();
		return map.get(ID).funLabel;
		
		
	}

	public void addVariable(String id){
		STentryCodeGen entry = new STentryCodeGen(false,offset,nestingLevel,null);
		offset++;
		scopes.peek().put(id, entry);
	}

	public void openScope(){
		scopes.push(new HashMap<String, STentryCodeGen>());
		offset=0;
		nestingLevel++;

	}

	public void closeScope(){
		scopes.pop();
		offset= scopes.peek().size();
		nestingLevel--;
	}
	
	public static String getNewLabelN() {
		try {
			return String.valueOf(LABEL_N);
		}finally {
			LABEL_N++;
		}
	}

}
