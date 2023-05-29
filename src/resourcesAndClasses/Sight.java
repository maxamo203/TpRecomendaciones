package resourcesAndClasses;

import java.util.Objects;

public class Sight implements Offerdable,Comparable<Sight> {
	private String name;
	private int cost;
	private double time;
	private int quota;
	private Preferency type;
	
	
	public Sight(String name, int cost, double time, int quota, Preferency type) {
		this.name =name;
		this.cost = cost;
		this.time = time;
		this.quota= quota;
		this.type=type;
	}

	@Override
	public boolean canBuy(User u) {
		boolean ret = false;
		
		if(u.getMoney()>=this.cost && u.getTime()>=this.time && this.quota>0 && u.alreadyBought(this) == false){
			ret = true;
		}
		
		 
		return ret;
	}

	@Override
	public void buy(User u) {
		this.quota--;
		u.update(this);
		// for now this is not defined but its very probably that User will have an ArrayList of OfferdableEntitys inside
	}
	
	
	public int getCost() {
		return this.cost;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getQuota() {
		return this.quota;
	}

	@Override

	
	public int compareTo(Sight o) {
		int cmp = this.cost - o.cost;
		if(cmp == 0) {
			cmp = Double.compare(this.time, o.time);
		}
		return cmp;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cost, name, quota, time, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sight other = (Sight) obj;
		return cost == other.cost && Objects.equals(name, other.name) && quota == other.quota
				&& Double.doubleToLongBits(time) == Double.doubleToLongBits(other.time) && type == other.type;
	}

	public Preferency getType() {
		return this.type;
	}
	

}
