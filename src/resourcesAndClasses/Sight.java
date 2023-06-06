package resourcesAndClasses;

import java.util.Objects;

public class Sight extends OfferdableItem {

	private String name;
	private int quota;

	
	
	public Sight(String name, double cost, double time, int quota, Preferency type) {
		super(type);
		this.name =name;
		this.cost = cost;
		this.time = time;
		this.quota= quota;
		
	}

	@Override
	public boolean canBeBoughtBy(User u) {
		boolean ret = false;
		
		if(u.getMoney()>=this.cost && u.getTime()>=this.time && this.quota>0 && u.alreadyBought(this) == false){
			ret = true;
		}
		
		 
		return ret;
	}

	@Override
	public void boughtBy(User u) {
		this.quota--;
		u.update(this);
		// for now this is not defined but its very probably that User will have an ArrayList of OfferdableItems inside
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public int getQuota() {
		return this.quota;
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
		return Double.doubleToLongBits(cost) == Double.doubleToLongBits(other.cost) && Objects.equals(name, other.name) && quota == other.quota
				&& Double.doubleToLongBits(time) == Double.doubleToLongBits(other.time) && type == other.type;
	}

	@Override
	public String toString() {
		return "Atraccion\n*Nombre = " + name + "\n*Cupo = " + quota + "\n*Tipo = " + type + "\n*Precio = $" + cost + ",\n*Duracion = " + time+" horas";
	}

}
