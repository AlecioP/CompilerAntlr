package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import util.STentryEffects.Effect;

public class EnvironmentEffects {

	LinkedList<HashMap<String, STentryEffects>> scopes;

	public EnvironmentEffects() {
		scopes = new LinkedList<HashMap<String,STentryEffects>>();
	}
	
	public EnvironmentEffects(LinkedList<HashMap<String, STentryEffects>> list) {
		scopes = list;
	}
	
	
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
	public void addVariable(String id,Effect e,String type) {
		STentryEffects entry= new STentryEffects(e,type);
		scopes.peek().put(id,entry);
		
	}
	
	public LinkedList<HashMap<String, STentryEffects>> cloneEnv() throws CloneNotSupportedException{
		//List clone
		LinkedList<HashMap<String, STentryEffects>> clone;
		clone = new LinkedList<HashMap<String, STentryEffects>>();
		
		//List iterator from the back 
		Iterator<HashMap<String, STentryEffects>> it = this.scopes.descendingIterator();
		//While there are hashmaps to clone
		while(it.hasNext()) {
			//Peek the current hashmap
			HashMap<String, STentryEffects> origin = it.next();
			//Create a new hashmap
			HashMap<String, STentryEffects> copy = new HashMap<String, STentryEffects>();
			//Iterator of the keyset of hashmap
			Iterator<Entry<String, STentryEffects>> mapit = origin.entrySet().iterator();
			//For each key
			while(mapit.hasNext()) {
				Entry<String,STentryEffects> pair = mapit.next();
				//Add to hashmap clone the key-value pair
				copy.put(new String((String)pair.getKey()),pair.getValue().clone() );
			}
			//Add the hashmap to the list clone
			clone.push(copy);
		}
		//return list clone
		return clone;
	}
	
	//Merge the environments saving in e1
	public static boolean mergeEnvs(EnvironmentEffects e1,EnvironmentEffects e2) {
		boolean modyfied = false;
		Iterator<HashMap<String, STentryEffects>> it1 = e1.scopes.iterator();
		Iterator<HashMap<String, STentryEffects>> it2 = e2.scopes.iterator();
		while(it1.hasNext()) {
			HashMap<String, STentryEffects> scope1 = it1.next();
			HashMap<String, STentryEffects> scope2 = it2.next();
			
			Iterator<Entry<String, STentryEffects>> mapit1 = scope1.entrySet().iterator();
			
			while(mapit1.hasNext()) {
				String key = mapit1.next().getKey();
				
				Effect ef1 = scope1.get(key).getEffect();
				Effect ef2 = scope2.get(key).getEffect();
				
				int ef1_v = STentryEffects.effectVal.get(ef1).intValue();
				int ef2_v = STentryEffects.effectVal.get(ef2).intValue();
				
				if(ef1_v > ef2_v)
					scope1.replace(key, scope1.get(key));//No replacing
				else {
					scope1.replace(key, scope2.get(key));
					modyfied = true;
				}
			}
		}
		return modyfied;
	}

	public void openScope(){
		scopes.push(new HashMap<String, STentryEffects>());
	}

	public void closeScope(){
		scopes.pop();
	}
}
