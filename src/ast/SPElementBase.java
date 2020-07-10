package ast;

import util.Environment;


public abstract class SPElementBase {
	
	public abstract void checkSemantics(Environment e);
	
	//public abstract BTBase inferBehavior(Environment e);
}
