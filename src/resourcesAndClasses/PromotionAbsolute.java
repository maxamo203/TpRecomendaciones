package resourcesAndClasses;

public class PromotionAbsolute extends Promotion {

	private double discount;
	
	public PromotionAbsolute(Preferency type, double discount) {
		super(type);
		this.discount =discount;
	}

	@Override
	public double getDiscount() {
		return this.discount;
	}
	
	@Override
	public String toString() {
		return "Promocion\n*Tipo = " + type +"\n*Atracciones Incluidas = "+
				this.getStrMySights()+ "\n*Precio original = $" + cost +
				"\nSe descuenta: $" + this.getDiscount() +
				"\n*Precio con descuento = $"+ 
				(this.cost-this.getDiscount()) +"\n*Duracion = " + time+" horas";
	}

}
