package resourcesAndClasses;

public interface Offerdable {

	public  boolean canBuy(User u); // if this method return true then the entity has to be show to the user
	public  void buy(User u); //quota-- and update User atributes
}
