package util;

import java.util.HashMap;
import java.util.LinkedList;

import util.STentryEffects.Effect;

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
	public void update(String id,Effect e) {
		for(HashMap<String, STentryEffects> scope:scopes){
			if(scope.containsKey(id)) {
				scope.get(id).setEffect(e);
			}
		}
	}
	public void addVariable(String id,Effect e) {
		STentryEffects entry= new STentryEffects(e);
		scopes.peek().put(id,entry);
		
	}

	public void openScope(){
		//TODO 
	}

	public void closeScope(){
		//TODO
	}
}
