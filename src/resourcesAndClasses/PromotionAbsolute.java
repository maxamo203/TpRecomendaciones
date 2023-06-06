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
}
