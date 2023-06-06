package resourcesAndClasses;

public class PromotionPercentual extends Promotion {
	
	private double percentual;
	
	public PromotionPercentual(Preferency type) {
		super(type);
	}


	@Override
	public double getDiscount() {
		return this.getCost() * (this.percentual/100);
	}
	
	public void setPercentage(double discount) {
		this.percentual = discount;
	}
	
	

}
