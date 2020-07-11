package util;

import java.util.HashMap;
import java.util.LinkedList;

public class EnvironmentEffects {

	LinkedList<HashMap<String, STentryEffects>> scopes = new LinkedList<HashMap<String,STentryEffects>>();

	public STentryEffects getEntry(String id) {
		for(HashMap<String, STentryEffects> scope:scopes){
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
