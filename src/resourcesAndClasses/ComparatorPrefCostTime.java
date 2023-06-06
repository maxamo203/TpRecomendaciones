package resourcesAndClasses;

import java.util.Comparator;

public class ComparatorPrefCostTime implements Comparator<OfferdableItem>{

	Preferency myPreferency;
	
	public ComparatorPrefCostTime(Preferency myPref) {
		this.myPreferency=myPref;
	}
	
	@Override
	public int compare(OfferdableItem o1, OfferdableItem o2) {
		if(o1.type == this.myPreferency && o2.type !=this.myPreferency) {
			return -1;
		}
		if(o1.type != this.myPreferency && o2.type == this.myPreferency) {
			return 1;
		}
		
		if(o1 instanceof Promotion && o2 instanceof Sight) {
			return -1;
		}
		if(o1 instanceof Sight && o2 instanceof Promotion) {
			return 1;
		}
		
		int cmp = Double.compare(o2.cost, o1.cost);
		
		if(cmp == 0) {
			cmp = Double.compare(o2.time, o1.time);
		}
		
		
		return cmp;
		
	}

}
