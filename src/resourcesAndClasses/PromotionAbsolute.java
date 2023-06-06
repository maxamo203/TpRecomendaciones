package resourcesAndClasses;

public class PromotionAbsolute extends Promotion {

	private double discount;
	
	public PromotionAbsolute(Preferency type) {
		super(type);
	}

	@Override
	public double getDiscount() {
		return this.discount;
	}
	
	public void setDiscount(double discount) {
		this.discount =discount;
	}

}
