package util;

import java.util.HashMap;
import java.util.LinkedList;

public class EnvironmentCodeGen {

	LinkedList<HashMap<String, STentryCodeGen>> scopes = new LinkedList<HashMap<String,STentryCodeGen>>();

	public STentryCodeGen getEntry(String id) {
		for(HashMap<String, STentryCodeGen> scope:scopes){
			if(scope.containsKey(id)) {
				return scope.get(id);
			}
		}
		return null;
	}

	public void openScope(){
		//TODO 
	}
	
	public void closeScope(){
		//TODO
	}
	
}
