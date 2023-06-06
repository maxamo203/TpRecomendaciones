package resourcesAndClasses;

import java.util.ArrayList;

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
				return;
			}
		}
	}

	@Override
	public double getDiscount() {		
		return discount;
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

	
	

}
