package resourcesAndClasses;

public class PromotionPercentual extends Promotion {
	
	private double percentual;
	
	public PromotionPercentual(Preferency type, double discount) {
		super(type);
		this.percentual = discount;
	}

	@Override
	public double getDiscount() {
		return this.getCost() * (this.percentual/100);
	}	
	
	private double getRawDiscount() {
		return percentual;
	}
	@Override
	public String toString() {
		return "Promocion\n*Tipo = " + type +"\n*Atracciones Incluidas = "+this.getStrMySights()+ 
				"\n*Precio original = $" + cost +
				"\n*Descuento = %" + this.getRawDiscount() + 
				"\n*Precio con descuento = $"+ (this.cost-this.getDiscount()) +
				"\n*Duracion = " + time+" horas";
	}
}
