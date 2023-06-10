package resourcesAndClasses;

import java.util.Objects;

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
	
	public double getRawDiscount() {
		return this.percentual;
	}
	
	@Override
	public String toString() {
		return "Promocion\n*Tipo = " + type +"\n*Atracciones Incluidas = "+
				this.getStrMySights()+ "\n*Precio original = $" + cost +
				"\n*Se descuenta: %" + this.getRawDiscount() + 
				"\n*Precio con descuento = $"+ 
				(this.cost-this.getDiscount()) +"\n*Duracion = " + time+" horas";
	}
	@Override
	public int hashCode() {
		return Objects.hash(percentual, mySights);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionPercentual other = (PromotionPercentual) obj;
		return Double.doubleToLongBits(percentual) == Double.doubleToLongBits(other.percentual)
				&& Objects.equals(other.mySights, mySights);
	}

}
