package util;

public class STentryEffects {

	public enum Effect{
		BOTTOM,RW,DELETE,TOP
	}
	
	Effect effect;

	public STentryEffects(Effect effect) {
		this.effect = effect;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}
}
