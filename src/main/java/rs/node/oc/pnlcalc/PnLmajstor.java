package rs.node.oc.pnlcalc;


import rs.node.oc.model.Leg;

import java.util.*;

public class PnLmajstor {

	private List<Leg> legs;

	private PnLmajstor(){
		// no instance
	}

	public PnLmajstor(List<Leg> legs) {
		this.legs = legs;
	}

	public List<Double> getExactStrajkovi(){
		List<Double> strajkovi = new ArrayList<>();
		for (Leg leg : this.legs){
			strajkovi.add(leg.getContract().getStrajk());
		}
		return strajkovi;
	}


	public List<Double> getExpandedStrajkovi(){
		double min = this.getMinStrajk();
		double max = this.getMaxStrajk();
		double procenat = 2;

		double bottom = (int) (min * (1-procenat/100));
		double top = (int) (max * (1+procenat/100));
		
		List<Double> tmp = new ArrayList<>(getExactStrajkovi());
		tmp.add(bottom);
		tmp.add(top);
		return tmp;
	}


	public double getMinStrajk(){
		double min = Double.MAX_VALUE;
		for (Leg leg : this.legs){
			min = Math.min(min, leg.getContract().getStrajk());
		}
		return min;
	}

	public double getMaxStrajk(){
		double max = Double.MIN_VALUE;
		for (Leg leg : this.legs){
			max = Math.max(max, leg.getContract().getStrajk());
		}
		return max;
	}

}
