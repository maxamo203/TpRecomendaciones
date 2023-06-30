package resourcesAndClasses;

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
}
