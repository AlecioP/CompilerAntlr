package util;

import java.util.HashMap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class EnvironmentTypes implements Cloneable {

	//contains the stack of scopes. the last one is always the current active scope
	//this linked list is used as a stack with LIFO behavior
	LinkedList<HashMap<String, STentryTypes>> scopes = new LinkedList<HashMap<String,STentryTypes>>();
	
	final String MAIN_RETURN_TYPE = "int";
	
	LinkedList<String> return_type_stack;
	@Override
	public Object clone() throws CloneNotSupportedException {
		LinkedList<String> cloneRS=new LinkedList<String>();
		LinkedList<HashMap<String, STentryTypes>> cloneScope = new LinkedList<HashMap<String,STentryTypes>>();
		Iterator<String> it1= return_type_stack.iterator(); 
		while(it1.hasNext()) {
			cloneRS.add(new String(it1.next()));
			
		}
		Iterator<HashMap<String,STentryTypes>> it2 = this.scopes.descendingIterator();
		while(it2.hasNext()) {
			//Peek the current hashmap
			HashMap<String, STentryTypes> origin = it2.next();
			//Create a new hashmap
			HashMap<String, STentryTypes> copy = new HashMap<String, STentryTypes>();
			//Iterator of the keyset of hashmap
			Iterator<Entry<String, STentryTypes>> mapit = origin.entrySet().iterator();
			//For each key
			while(mapit.hasNext()) {
				Entry<String,STentryTypes> pair = mapit.next();
				//Add to hashmap clone the key-value pair
				copy.put(new String((String)pair.getKey()),(STentryTypes)pair.getValue().clone() );
			}
			//Add the hashmap to the list clone
			cloneScope.push(copy);
		}
		EnvironmentTypes eCopy = new EnvironmentTypes() ;
		eCopy.return_type_stack=cloneRS;
		eCopy.scopes=cloneScope;
		return eCopy;
	}
	
	public EnvironmentTypes() {
		return_type_stack = new LinkedList<String>();
		return_type_stack.add(MAIN_RETURN_TYPE);
	}
	public void addReturnType(String type) {
		return_type_stack.add(type);
	}
	public void rempoveReturnType(String type) {
		return_type_stack.remove();
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
	
	public void addFunction(String name,String type,boolean[] funref) {
		Iterator<HashMap<String, STentryTypes>> it = scopes.descendingIterator();
		STentryTypes entry = new STentryTypes(nestingLevel, type, offset);
		entry.setFunRefArgs(funref);
		it.next().put(name, entry);
	}
	public void remove(String id){
		for(HashMap<String, STentryTypes> scope:scopes){
			if(scope.containsKey(id)) {
				scope.remove(id);
			}
		}
	}


}
