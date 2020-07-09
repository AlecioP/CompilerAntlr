package ast;

public abstract class SPExp extends SPElementBase {
	SPElementBase value;
	SPExp(SPElementBase value){
		this.value=value;
	}


}
