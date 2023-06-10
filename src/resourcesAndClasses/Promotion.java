package resourcesAndClasses;

import java.util.ArrayList;
import java.util.HashSet;

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
	
	
	
	@Override
	public int getQuota() {
		int min = 0;
		boolean flag = false;
		for(Sight s: mySights) {
			if(!flag)
				min = s.getQuota();
			else {
				if(s.getQuota()< min)
					min = s.getQuota();
			}
		}
		return min;
	}
	
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
	
	protected boolean sightValidation(User u) { //validates quota and alreadyBought parameters
		for(OfferdableItem s: this.mySights) {
			if(s.getQuota()==0 || u.alreadyBought(s)) {
				return false;
			}
		}
		return true;
	}
	@Override
	public void boughtBy(User u) {
		for(Sight s: this.mySights) {
			s.decreaseQuota();
		}
		u.update(this);
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
	public String stringParaUsuario() {
		
		return this.toString() + "\n---------";
	}
	@Override
	public HashSet<Sight> getSights(){
		HashSet<Sight> sight = new HashSet<Sight>();
		sight.addAll(mySights);
		return sight;
	}
}

