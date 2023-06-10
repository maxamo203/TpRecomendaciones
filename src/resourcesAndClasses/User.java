package resourcesAndClasses;

//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
import java.util.Objects;
import java.util.HashSet;

public class User {

	private String name;
	private double money;
	private double time;
	private Preferency myPref;
	
	private HashSet<OfferdableItem>myList;
	
	private double spentMoney;
	private double savedMoney;
	

	private double spentTime;
	private HashSet<Sight> mySights;

	public User(String name, double money, double time, Preferency myPref) {
		this.name = name;
		this.money = money;
		this.time = time;
		this.myPref = myPref;
		
		this.spentMoney =0; 
		this.savedMoney = 0;
		this.spentTime = 0;
		this.myList = new HashSet<OfferdableItem>();
		this.mySights = new HashSet<Sight>();
	}

	public void update(OfferdableItem ent) {
		this.money -= ent.getCost();
		this.spentMoney+=ent.getCost();
		this.time-=ent.getTime();
		this.spentTime+=ent.getTime();
		this.savedMoney += ent.getDiscount();
		this.myList.add(ent);
		this.mySights.addAll(ent.getSights());
		
	}
	
	public double getMoney() {
		return this.money;
	}
	
	public double getTime() {
		return this.time;
	}
	public double getSavedMoney() {
		return savedMoney;
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
	
	public HashSet<OfferdableItem> getSights() {
//		String myListNames="Atracciones Compradas: ";
//		for(OfferdableItem s:this.myList) {
//			myListNames+=s.getName()+" ";
//		}
		//return myListNames;
		
		return myList;
	}
	
	public void makeDiscount(double discount) {
		this.money += discount;
		this.spentMoney -= discount;
	}
	
	public boolean alreadyBought(OfferdableItem s) {
//		for(Sight s2: this.myList) {
//			if(s.equals(s2)) {
//				return true;
//			}
//		}
		HashSet<OfferdableItem> aux = new HashSet<OfferdableItem>(mySights);
		aux.retainAll(s.getSights());
		return !aux.isEmpty();
//		try {
//			
//		}catch(ClassCastException e) {
//			return false;
//		}
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
	public  String toString() {
		String salida = this.getName() + " compró:\n";
		for(OfferdableItem i: this.getSights()) {
			salida += i.stringParaUsuario() + "\n";
		}
		salida += String.format("---------\nEn total gasto: $%.2f\n"
				+ "El tiempo total de las atracciones es: %.2f horas\n"
				+ "En total se ahorró $%.2f\n", this.getSpentMoney(),this.getSpentTime(),this.getSavedMoney());
		salida += "----------------------------------------------------------------------------\n";
		return salida;
		
	}
	
}
