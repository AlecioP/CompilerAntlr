package util;

import java.util.ArrayList;
import java.util.List;

public class STentryCodeGen {

	boolean isFun;
	String funLabel;
	int offset;
	int nl;
	String funRetLabel;
	ArrayList<String> paramNames;
	
	public String getFunRetLabel() {
		return funRetLabel;
	}
	public void setFunRetLabel(String funRetLabel) {
		this.funRetLabel = funRetLabel;
	}
	public STentryCodeGen(boolean isFun, int offset,int nl,String funLabel,String funRetLabel,ArrayList<String> paramNames) {
		this.funRetLabel=funRetLabel;
		this.funLabel=funLabel;
		this.isFun = isFun;
		this.nl = nl;
		this.offset = offset;
	}
	public String getFunLabel() {
		return funLabel;
	}
	public void setFunLabel(String funLabel) {
		this.funLabel = funLabel;
	}
	public boolean isFun() {
		return isFun;
	}
	public void setFun(boolean isFun) {
		this.isFun = isFun;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getNl() {
		return nl;
	}
	public void setNl(int nl) {
		this.nl = nl;
	}
	public List<String> getParamNames() {
		return paramNames;
	}
	
	@Override
	public String toString() {
		String funIs;
		if(isFun)
			funIs = "FUN";
		else
			funIs= "VAR";
		
		return funIs + " ("+funLabel+" ... "+funRetLabel+") "+paramNames+" NL "+nl;
	}
	
	
}
