package resourcesAndClasses;

public abstract class OfferdableItem {
	protected Preferency type;
	protected double cost;
	protected double time;
	
	public OfferdableItem(Preferency type) {
		this.type = type;
	}
	
	public Preferency getType() {
		return this.type;
	}
	
	public double getCost() { // when the offerdableItem is a promotion, returns the original price
		return this.cost;
	}
	
	public double getTime() {
		return this.time;
	}
	
	

	public abstract boolean canBeBoughtBy(User u);
	
	public abstract void boughtBy(User u);
	
}
