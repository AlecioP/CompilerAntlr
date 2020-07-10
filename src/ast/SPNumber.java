package ast;



import util.Environment;


public class SPNumber extends SPElementBase {
	
	Integer value;

	public SPNumber(Integer value) {
		this.value = value;
	}

	@Override
	public void checkSemantics(Environment e) {
	
	}

}
