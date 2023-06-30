package resourcesAndClasses;

public class PromotionPercentual extends Promotion {

	private double percentual;

	public PromotionPercentual(Preferency type, double discount) {
		super(type);
		this.percentual = discount;
	}

	@Override
	public double getDiscount() {
		return this.getCostWithoutDiscount() * (this.percentual / 100);
	}

}
