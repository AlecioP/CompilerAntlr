package util;

import java.util.HashMap;

public class EnvironmentEffectsFun {

	HashMap<String, STentryEffectsFun> scope = new HashMap<String,STentryEffectsFun>();
	
	public void addFunction(String name,STentryEffectsFun entry) {
		scope.put(name, entry);
	}
	
	public HashMap<String, STentryEffectsFun> getScope() {
		return scope;
	}

	public void setScope(HashMap<String, STentryEffectsFun> scope) {
		this.scope = scope;
	}

}

