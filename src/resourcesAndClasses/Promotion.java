package resourcesAndClasses;

import java.util.ArrayList;

public class Promotion implements Offerdable,Comparable<Promotion> {
	
	
	private ArrayList<Sight>mySights;
	private Preferency type;
	
	private int totalCost;
	private double totalTime;
	private boolean costFlag;
	private boolean timeFlag;
	
	// en vez de heredar podemos añadir estos atributos a la clase promotion
	private double percentual;
	private int absolute;
	private ArrayList<Sight>free;
	
	
	public Promotion(Preferency type) {
		this.type= type;
		this.mySights = new ArrayList<Sight>();
		this.percentual=0;
		this.absolute=0;
		this.free=new ArrayList<Sight>();
		this.totalCost=0;
		this.totalTime=0;
		this.costFlag = false;
		this.timeFlag = false;
	}
	
	
	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}
	public void setAbsolute(int abs) {
		this.absolute=abs;
	}
	public void addFreeSight(Sight s) {
		this.free.add(s);
	}
	
	public void loadSight(String sightName, ArrayList<Sight> sights) {
		for(Sight s : sights) {
			if(s.getName() == sightName) {
				this.mySights.add(s);
				break;
			}
		}
	}
	
	
	public int getTotalCost() {
		if(this.costFlag == false) {
			this.calculateTotalCost();
			this.costFlag = true;
		}
		return this.totalCost;
		
	}
	
	private void calculateTotalCost() {
		int totalCost = 0;
		for(Sight s: this.mySights) {
			totalCost+=s.getCost();
		}
		
		this.totalCost = totalCost - this.getDiscount();
		
	}
	
	public double getTotalTime() {
		if(this.timeFlag == false) {
			this.calculateTotalTime();
			this.timeFlag = true;
		}
		return this.totalTime;
	}
	
	private void calculateTotalTime() {
		double totalTime=0;
		for(Sight s:  this.mySights) {
			totalTime+= s.getTime();
		}
		this.totalTime = totalTime;
	}

	public int getDiscount() {
		if(this.absolute != 0) {
			return this.absolute;
		}
		if(this.percentual != 0) {
			return (int) (this.getTotalCost() *(this.percentual/10));
		}
		
		int freeSightsCost = 0;
		for(Sight s : this.free) {
			freeSightsCost += s.getCost();
		}
		return freeSightsCost;
		
	}
	
	public boolean canBuy(User u) {
		boolean ret = false;
		if(this.getTotalCost()<=u.getMoney() && this.getTotalTime()<=u.getTime() && this.quotaValidation()) {
			ret = true;
		}
		return ret;
	}
	
	private boolean quotaValidation() {
		for(Sight s: this.mySights) {
			if(s.getQuota()==0) {
				return false;
			}
		}
		return true;
	}
	
	public void buy(User u) {
		for(Sight s: this.mySights) {
			s.buy(u);
		}
		u.makeDiscount(this.getDiscount());
	}
	
	public Preferency getType() {
		return this.type;
	}


	@Override
	
	public int compareTo(Promotion o) {
		int cmp = this.getTotalCost() - o.getTotalCost();
		if(cmp == 0) {
			cmp = Double.compare(this.getTotalTime(), o.getTotalTime());
		}
		return cmp;
	}
	
	
}
