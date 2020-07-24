package util;

public class STentryCodeGen {

	boolean isFun;
	String funLabel;
	int offset;
	int nl;
	
	public STentryCodeGen(boolean isFun, int offset,int nl,String funLabel) {
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
	
}
