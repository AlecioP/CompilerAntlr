package util;

import java.util.HashMap;
import java.util.LinkedList;

public class EnvironmentTypes {

	//contains the stack of scopes. the last one is always the current active scope
	//this linked list is used as a stack with LIFO behavior
	LinkedList<HashMap<String, STentryTypes>> scopes = new LinkedList<HashMap<String,STentryTypes>>();
	
	final String MAIN_RETURN_TYPE = "int";
	
	LinkedList<String> return_type_stack;
	
	
	public EnvironmentTypes() {
		return_type_stack = new LinkedList<String>();
		return_type_stack.add(MAIN_RETURN_TYPE);
	}
	
	int nestingLevel =-1;
	int offset =0;
	public void addVariable(String id,String t) {
		STentryTypes entry= new STentryTypes(nestingLevel,t,offset);
		scopes.peek().put(id,entry);
		offset++;
	}


	public void openScope(){
		nestingLevel++;
		offset=0;
		scopes.push(new HashMap<String, STentryTypes>());

	}


	public void closeScope(){
		nestingLevel--;
		scopes.pop();
		offset=scopes.peek().size();
	}


	public boolean containsVariable(String id){

		for(HashMap<String, STentryTypes> scope:scopes){
			if(scope.containsKey(id))
				return true;
		}

		return false;
	}
	public boolean containsTop(String id) {
		return scopes.peek().containsKey(id);
	}
	
	public STentryTypes getEntry(String id) {
		for(HashMap<String, STentryTypes> scope:scopes){
			if(scope.containsKey(id)) {
				return scope.get(id);
			}
		}
		return null;
	}
	
	public String getCurrentReturnType() {
		return return_type_stack.peek();
	}


}
