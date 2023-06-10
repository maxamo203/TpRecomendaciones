package resourcesAndClasses;

import java.util.Comparator;

public class ComparatorPrefCostTime implements Comparator<OfferdableItem>{

	Preferency myPreferency;
	
	public ComparatorPrefCostTime(Preferency myPref) {
		this.myPreferency=myPref;
	}
	
	public int compare(OfferdableItem o1, OfferdableItem o2) {
		if(o1.getType() == this.myPreferency && o2.getType() !=this.myPreferency) {
			return -1;
		}
		if(o1.getType() != this.myPreferency && o2.getType() == this.myPreferency) {
			return 1;
		}
		
		if(o1 instanceof Promotion && o2 instanceof Sight) {
			return -1;
		}
		if(o1 instanceof Sight && o2 instanceof Promotion) {
			return 1;
		}
		
		int cmp = Double.compare(o2.getCost(), o1.getCost());
		
		if(cmp == 0) {
			cmp = Double.compare(o2.getTime(), o1.getTime());
		}		
		return cmp;
	}
}
