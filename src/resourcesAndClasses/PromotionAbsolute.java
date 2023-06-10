package resourcesAndClasses;

import java.util.Objects;

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
				"\n*Se descuenta: $" + this.getDiscount() +
				"\n*Precio con descuento = $"+ 
				(this.cost-this.getDiscount()) +"\n*Duracion = " + time+" horas";
	}

	@Override
	public int hashCode() {
		return Objects.hash(discount, mySights);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionAbsolute other = (PromotionAbsolute) obj;
		return Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Objects.equals(other.mySights, mySights);
	}
	

}
