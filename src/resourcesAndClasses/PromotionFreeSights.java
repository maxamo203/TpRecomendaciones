package resourcesAndClasses;
import java.util.List;
import java.util.LinkedList;

public class PromotionFreeSights extends Promotion {
	private LinkedList<Sight> freeSights;

	private double discount;

	public PromotionFreeSights(Preferency type) {
		super(type);
		this.freeSights = new LinkedList<Sight>();
		this.discount = 0;
	}

	public void addFreeSight(String s, List<Sight> sights) {
		for (Sight s2 : sights) {
			if (s.equals(s2.getName())) {
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
		String s = "";
		for (Sight s2 : this.freeSights) {
			s += s2.getName() + " ";
		}
		return s;
	}

	@Override
	public String toString() {
		return super.toString() + "\n*Atracciones gratuitas: " + this.getStrMyFreeSights();
	}
}
