package resourcesAndClasses;

public enum Preferency {
	AVENTURA,
	BANQUETE,
	COMBATE,
	ACADEMICO,
	CONQUISTA;
	
	
	@Override
	public String toString() {
	    switch (this) {
	        case AVENTURA:
	            return "Aventura!";
	        case BANQUETE:
	            return "Banquete!";
	        case COMBATE:
	            return "Combate!";
	        case ACADEMICO:
	            return "Academico!";
	        case CONQUISTA:
	            return "Conquista!";
	        default:
	            return null;
	    }
	}
}
