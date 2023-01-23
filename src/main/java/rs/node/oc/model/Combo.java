package rs.node.oc.model;

import java.util.*;

public class Combo {

	private final List<Leg> legs = new ArrayList<>();
	private TreeMap<Double, Double> pnlPoints = new TreeMap<>();
	
	public void add(Leg p){
		legs.add(p);
	}
	
	public void add(int amount, Contract contract, double px){
		legs.add(new Leg(amount, contract, px));
	}
	
	public double getComboOpenPrice(){
		double val = 0;
		for (Leg leg : legs){
			val += leg.getAmount() * leg.getOpenPrice();
		}
		return val;
	}
	
	public double getExpirationPriceAt(double underl){
		double val = 0;
		for (Leg leg : legs){
			val += leg.getAmount() * leg.getExpirationPriceAt(underl);
		}
		return val;
	}
	
	public double getPnlAt(double underl){
		double pnl = 0;
		for (Leg leg : legs) {
			pnl += leg.getAmount() * leg.getPnlAt(underl);
		}
		return pnl;
	}
	
	public TreeMap<Double, Double> getPnLPoints() {
		TreeMap<Double, Double> tm = new TreeMap<>();
		for (Leg leg : legs) {
			double strajk = leg.getContract().getStrajk();
			double pnl = leg.getPnlAt(strajk);
			if (tm.containsKey(strajk)) {
				pnl += leg.getAmount() * leg.getPnlAt(strajk);
			}
			tm.put(strajk, pnl);
		}
		return tm;
	}
	
	public List<Leg> getLegs() {
		return legs;
	}
	
	public double getDelta(){
		double delta = 0;
		for (Leg leg : legs) {
			delta += leg.getDelta();
		}
		return delta;
	}
	
	public double getMaxProfit(){
		double maxP = 0;
		for (Map.Entry<Double, Double> point : pnlPoints.entrySet()) {
			maxP += Math.max(maxP, point.getValue());
		}
		return maxP;
	}
	
	public double getMaxLoss(){
		double maxL = 0;
		for (Map.Entry<Double, Double> point : pnlPoints.entrySet()) {
			maxL += Math.min(maxL, point.getValue());
		}
		return maxL;
	}
	
}
