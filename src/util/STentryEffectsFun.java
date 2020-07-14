package util;

import util.STentryEffects.Effect;

public class STentryEffectsFun {
	private boolean[] funRefArgs;
	private Effect[] sigma;
	
	public STentryEffectsFun(boolean[] funRefArgs, Effect[] sigma) {
		this.funRefArgs = funRefArgs;
		this.sigma = sigma;
	}
	public boolean[] getFunRefArgs() {
		return funRefArgs;
	}
	public void setFunRefArgs(boolean[] funRefArgs) {
		this.funRefArgs = funRefArgs;
	}
	public Effect[] getSigma() {
		return sigma;
	}
	public void setSigma(Effect[] sigma) {
		this.sigma = sigma;
	}
	
	
}
