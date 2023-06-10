package resourcesAndClasses;

import java.util.ArrayList;
import java.util.Objects;

public class User {

	private String name;
	private double money;
	private double time;
	private double discountMade;
	private Preferency myPref;
	
	private ArrayList<Sight>myList;
	
	private double spentMoney;
	private double spentTime;

	public User(String name, double money, double time, Preferency myPref) {
		this.name = name;
		this.money = money;
		this.time = time;
		this.myPref = myPref;
		
		this.discountMade=0;
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
	
	public double getMoney() {
		return this.money;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public Preferency getPref() {
		return this.myPref;
	}
	
	public double getSpentMoney() {
		return this.spentMoney;
	}
	
	public double getSpentTime() {
		return this.spentTime;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStrMySights() {
		String myListNames="Atracciones Compradas: ";
		for(Sight s:this.myList) {
			myListNames+=s.getName()+" ";
		}
		return myListNames;
	}
	
	public double getDiscountMade() {
		return this.discountMade;
	}
	
	public void makeDiscount(double discount) {
		this.money += discount;
		this.spentMoney -= discount;
		this.discountMade+=discount;
	}
	
	
	public boolean alreadyBought(Sight s) {
		for(Sight s2: this.myList) {
			if(s.equals(s2)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money, myList, myPref, name, spentMoney, spentTime, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Double.doubleToLongBits(money) == Double.doubleToLongBits(other.money)
				&& Objects.equals(myList, other.myList) && myPref == other.myPref && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(spentMoney) == Double.doubleToLongBits(other.spentMoney)
				&& Double.doubleToLongBits(spentTime) == Double.doubleToLongBits(other.spentTime)
				&& Double.doubleToLongBits(time) == Double.doubleToLongBits(other.time);
	}
	
	@Override
	public String toString() {
		return "\n(!)Datos del usuario "+this.getName()+"\n*"+this.getStrMySights() +"\n*Dinero gastado: $"+this.getSpentMoney() +"\n*Tiempo invertido: "+this.getSpentTime() +" horas" +"\n*Descuento en las compras: $"+this.getDiscountMade()+"\n----------------------------------------------------------------------------\n";
	}
	
	
}
