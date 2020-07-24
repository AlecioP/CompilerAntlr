package util;

import java.util.HashMap;

public class STentryEffects  implements Cloneable{
	public static HashMap<Effect, Integer> effectVal = new HashMap<STentryEffects.Effect, Integer>();
	static {
		effectVal.put(Effect.BOTTOM,0);
		effectVal.put(Effect.RW,1);
		effectVal.put(Effect.DELETE,2);
		effectVal.put(Effect.TOP,3);

	}
	public enum Effect{
		BOTTOM,RW,DELETE,TOP
	}
	String type;

	Effect effect;

	public STentryEffects(Effect effect,String type) {
		this.effect = effect;
		this.type=type;
	}
	

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	
	public static Effect sequence(Effect e1,Effect e2){
		final int B=0;//BOTTOM
		//final int R=1;//RW
		final int D=2;//DELETE
		//final int T=3;//TOP
		int e1V =STentryEffects.effectVal.get(e1).intValue();
		int e2V = STentryEffects.effectVal.get(e2).intValue();
		if(e1V<D && e2V<D){
			if(e1V > e2V) return e1;
			return e2;
		}
		if((e1V <D && e2V==D) ||(e1V==D && e2V==B)) {
			return Effect.DELETE;
		}
		return Effect.TOP;


	}
	
	public static Effect parallel(Effect e1,Effect e2) {
		final int B=0;//BOTTOM
		final int R=1;//RW
		//final int D=2;//DELETE
		//final int T=3;//TOP
		if(STentryEffects.effectVal.get(e1).intValue() == B)
			return e2;
		if(STentryEffects.effectVal.get(e2).intValue() == B)
			return e1;
		if(
				STentryEffects.effectVal.get(e1).intValue() == R &&
				STentryEffects.effectVal.get(e2).intValue() == R 
			)
			return Effect.RW;
		return Effect.TOP;
	}
	
	@Override
	protected STentryEffects clone() throws CloneNotSupportedException {
		
		switch(effect) {
		case BOTTOM:
			return new STentryEffects(Effect.BOTTOM,new String(type));
		case DELETE:
			return new STentryEffects(Effect.DELETE,new String(type));
		case RW:
			return new STentryEffects(Effect.RW,new String(type));
		case TOP:
			return new STentryEffects(Effect.TOP,new String(type));
		
		}
		return null;
	}
	
}
