package resourcesAndClasses;

import java.util.HashMap;

public enum Preferency {
	AVENTURA, BANQUETE, COMBATE;

	@Override
	public String toString() {
		switch (this) {
		case AVENTURA:
			return "Aventura!";
		case BANQUETE:
			return "Banquete!";
		case COMBATE:
			return "Combate!";
		default:
			return null;
		}
	}
	 public final static HashMap<Integer, Preferency> asignacionPref = new HashMap<Integer,Preferency>(){/**
		 * 
		 */
		private static final long serialVersionUID = -7954379644455222607L;

	{
		 put(1,Preferency.COMBATE);
		 put(2,Preferency.BANQUETE);
		 put(3,Preferency.AVENTURA);
	 }};
}
