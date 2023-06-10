package resourcesAndClasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class PromotionFreeSights extends Promotion {
	private ArrayList<Sight> freeSights;
	
	private double discount;

	public PromotionFreeSights(Preferency type) {
		super(type);
		this.freeSights = new ArrayList<Sight>();
		this.discount = 0;
	}

	public void addFreeSight(String s, ArrayList<Sight>sights) {
		for(Sight s2: sights) {
			if( s.equals(s2.getName())) {
				this.freeSights.add(s2);
				this.discount += s2.getCost();
				this.time += s2.getTime();
				this.cost += s2.getCost();
				return;
			}
		}
	}

	@Override
	public double getDiscount() {		
		return discount;
	}
	@Override
	public int getQuota() {
		int min = 0;
		boolean flag = false;
		for(Sight s: freeSights) {
			if(!flag)
				min = s.getQuota();
			else {
				if(s.getQuota()< min)
					min = s.getQuota();
			}
		}
		return Integer.min(min, super.getQuota());
	}
	@Override
	public boolean canBeBoughtBy(User u) {
		return super.canBeBoughtBy(u); //llamo a super pero va a usar el sightValidation de PromotionFreeSights
	}
	@Override
	protected boolean sightValidation(User u) {
		boolean estado = true;
		for(OfferdableItem s: this.freeSights) {
			if(s.getQuota()==0 || u.alreadyBought(s)) {
				estado =  false;
			}
		}
		return super.sightValidation(u) && estado;
	}
	
	@Override
	public void boughtBy(User u) {
		for(Sight s: this.mySights) {
			s.decreaseQuota();
		}
		for(Sight s: this.freeSights) {
			s.decreaseQuota();
		}
		u.update(this);
		u.makeDiscount(this.getDiscount());
	}
	@Override
	public HashSet<Sight> getSights() {
		HashSet<Sight> sights = super.getSights();
		sights.addAll(freeSights);
		return sights;
	}
	private String getStrMyFreeSights() {
		String s="";
		for(Sight s2: this.freeSights) {
			s+= s2.getName() + " ";
		}
		return s;
	}
	
	@Override
	public String toString() {
		return "Promocion\n*Tipo = " + type +"\n*Atracciones Incluidas = "+this.getStrMySights()+"\n*Atracciones Gratuitas = "+this.getStrMyFreeSights()+ "\n*Precio original = $" + cost +"\n*Precio con descuento = $"+ (this.cost-this.getDiscount()) +"\n*Duracion = " + time + "horas";
	}

	@Override
	public int hashCode() {
		return Objects.hash(discount, freeSights, mySights);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionFreeSights other = (PromotionFreeSights) obj;
		return Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Objects.equals(freeSights, other.freeSights) 
				&& Objects.equals(mySights, other.mySights);
	}


	
	
	

}
