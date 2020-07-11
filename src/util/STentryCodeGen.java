package util;

public class STentryCodeGen {

	boolean isFun;
	
	int offset;
	int nl;
	
	public STentryCodeGen(boolean isFun, int offset,int nl) {
		this.isFun = isFun;
		this.nl = nl;
		this.offset = offset;
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
