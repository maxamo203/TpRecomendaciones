package resourcesAndClasses;

import java.util.TreeSet;

public abstract class OfferdableItem implements Comparable<OfferdableItem>{
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
	public abstract int getQuota();
	
	
	public static int comparar(OfferdableItem o1, OfferdableItem o2, Preferency myPreferency) {
		if(o1.type == myPreferency && o2.type !=myPreferency) {
			return -1;
		}
		if(o1.type != myPreferency && o2.type == myPreferency) {
			return 1;
		}
		
		if(o1 instanceof Promotion && o2 instanceof Sight) {
			return -1;
		}
		if(o1 instanceof Sight && o2 instanceof Promotion) {
			return 1;
		}
		
		int cmp = Double.compare(o2.cost, o1.cost);
		
		if(cmp == 0) {
			cmp = Double.compare(o2.time, o1.time);
		}
		
		
		return cmp;
		
	}
	@Override
	public int compareTo(OfferdableItem o2) {
		
		int cmp = this.getType().ordinal()- o2.getType().ordinal();
		if(cmp != 0)
			return cmp;
		
		if(this instanceof Promotion && o2 instanceof Sight) {
			return -1;
		}
		if(this instanceof Sight && o2 instanceof Promotion) {
			return 1;
		}
		
		cmp = Double.compare(o2.cost, this.cost);
		
		if(cmp == 0) {
			cmp = Double.compare(o2.time, this.time);
		}
		
		
		return cmp;
	}
	
	public abstract String stringParaUsuario();
	public abstract boolean canBeBoughtBy(User u);
	
	public abstract void boughtBy(User u);
	public abstract TreeSet<Sight> getSights();
}
