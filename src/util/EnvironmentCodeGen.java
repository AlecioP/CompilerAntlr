package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class EnvironmentCodeGen {
	int offset=0;
	int nestingLevel=0;
	public final static int  WORDDIM=4;
	LinkedList<String> callStack;
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
	public void setFunctionLabel(String ID,String label,String retLabel,ArrayList<String> pN) {
		Iterator<HashMap<String, STentryCodeGen>> it =scopes.descendingIterator();
		HashMap<String, STentryCodeGen> map = it.next();
		map.put(ID, new STentryCodeGen(true, 0, 0, label,retLabel,pN));
	}

	public void addVariable(String id){
		STentryCodeGen entry = new STentryCodeGen(false,offset,nestingLevel,null,null,null);
		offset++;
		scopes.peek().put(id, entry);
	}
	public void offsetOpenScope(){
		offset=0;
	}
	public void offsetCloseScope(){
		int counter = 0;
		for(STentryCodeGen en : scopes.peek().values())
			if(en.isFun==false)
				counter++;
		offset=counter;
	}
	public void openScope(boolean isNewFrame){
		scopes.push(new HashMap<String, STentryCodeGen>());
		if(isNewFrame)
			nestingLevel++;

	}

	public void closeScope(boolean closeFrame){
		scopes.pop();
		if(closeFrame)
			nestingLevel--;
	}
	
	public static String getNewLabelN() {
		try {
			return String.valueOf(LABEL_N);
		}finally {
			LABEL_N++;
		}
	}
	
	public EnvironmentCodeGen(){
		callStack=new LinkedList<String>();
		callStack.add("main");
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public LinkedList<String> getCallStack() {
		return callStack;
	}
	public void setNestingLevel(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}
	public int getNestingLevel() {
		return nestingLevel;
	}
	
	public String scopesToStr() {
		return scopes.toString();
	}
}
