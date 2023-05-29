package resourcesAndClasses;

import java.util.ArrayList;

public class User {
	private String name;
	private int money;
	private double time;
	private Preferency myPref;
	
	private ArrayList<Sight>myList;
	
	private int spentMoney;
	private double spentTime;
	
	public User(String name, int money, double time, Preferency myPref) {
		this.name = name;
		this.money = money;
		this.time = time;
		this.myPref = myPref;
		
		this.spentMoney =0; 
		this.spentTime = 0;
		this.myList = new ArrayList<Sight>();
	}
	
	public void update(Sight ent) {
		this.money -= ent.getCost();
		this.spentMoney+=ent.getCost();
		this.time-=ent.getTime();
		this.spentTime+=ent.getTime();
		this.myList.add(ent);
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public Preferency getPref() {
		return this.myPref;
	}
	
	public int getSpentMoney() {
		return this.spentMoney;
	}
	
	public double getSpentTime() {
		return this.spentTime;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void makeDiscount(int discount) {
		this.money += discount;
		this.spentMoney -= discount;
	}
	
	public String getMySights() {
		String myListNames="Atracciones Compradas: ";
		for(Sight s:this.myList) {
			myListNames+=s.getName()+" ";
		}
		return myListNames;
	}
	
	public boolean alreadyBought(Sight s) {
		for(Sight s2: this.myList) {
			if(s.equals(s2)) {
				return true;
			}
		}
		return false;
	}
	
}
