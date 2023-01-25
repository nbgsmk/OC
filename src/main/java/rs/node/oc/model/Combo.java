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
	
	public double getExpiredPriceAt(double underl){
		double val = 0d;
		for (Leg leg : legs){
			val += leg.getAmount() * leg.getExpirationPriceAt(underl);
		}
		return val;
	}
	
	public double getPnlAt(double underl){
		double pnl = 0d;
		for (Leg leg : legs) {
			pnl += leg.getAmount() * leg.getPnlAt(underl);
		}
		return pnl;
	}
	
	public TreeMap<Double, Double> getPnLPoints() {
		double minStrajk = Double.MAX_VALUE;
		double maxStrajk = Double.MIN_VALUE;
		
		TreeMap<Double, Double> tm = new TreeMap<>();
		for (Leg leg : legs) {
			double strajk = leg.getContract().getStrajk();
			
			minStrajk = Math.min(minStrajk, strajk);
			maxStrajk = Math.max(maxStrajk, strajk);
			
			double pnleg = leg.getPnlAt(strajk);
			if (tm.containsKey(strajk)) {
				double prev = tm.get(strajk);
				pnleg = prev + leg.getAmount() * leg.getPnlAt(strajk);
			}
			tm.put(strajk, pnleg);
		}
		minStrajk = minStrajk * 0.95;
		maxStrajk = maxStrajk * 1.05;
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
