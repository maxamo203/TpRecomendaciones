package resourcesAndClasses;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Promotion extends OfferdableItem{
	
	protected ArrayList<Sight>mySights;
	
	public Promotion(Preferency type) {
		super(type);
		this.mySights = new ArrayList<Sight>();
		this.cost = 0;
		this.time = 0;
	}
	
	
	public void loadSight(String sightName, ArrayList<Sight> sights) {
		for(Sight s : sights) {
			if(s.getName().equals(sightName)) {
				this.mySights.add(s);
				this.cost+=s.getCost();
				this.time+=s.getTime();
				break;
			}
		}
	}
	

	public abstract double getDiscount();
	
	
	public double getCostWithDiscount() {
		return this.getCost()-this.getDiscount();
	}
	
	
	public boolean canBeBoughtBy(User u) {
		boolean ret = false;
		if(this.getCostWithDiscount()<=u.getMoney() && this.getTime()<=u.getTime() && this.sightValidation(u) ) {
			ret = true;
		}
		return ret;
	}
	
	private boolean sightValidation(User u) { //validates quota and alreadyBought parameters
		for(Sight s: this.mySights) {
			if(s.getQuota()==0 || u.alreadyBought(s)) {
				return false;
			}
		}
		return true;
	}
	
	public void boughtBy(User u) {
		for(Sight s: this.mySights) {
			s.boughtBy(u);
		}
		u.makeDiscount(this.getDiscount());
	}
	
	protected String getStrMySights() {
		String s="";
		for(Sight s2: this.mySights) {
			s+= s2.getName() + " ";
		}
		return s;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(mySights);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		return Objects.equals(mySights, other.mySights);
	}


	

}

