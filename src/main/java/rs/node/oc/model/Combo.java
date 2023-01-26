package rs.node.oc.model;

import rs.node.oc.pnlcalc.PnLmajstor;

import java.util.*;

public class Combo {
	
	private String comboName = "";
	private String comboDescription = "";
	private final List<Leg> legs = new ArrayList<>();
	// private TreeMap<Double, Double> pnlPoints = new TreeMap<>();
	
	public Combo() {
	}
	
	public Combo(String comboName) {
		this.comboName = comboName;
	}
	
	public Combo(String comboName, String comboDescription) {
		this.comboName = comboName;
		this.comboDescription = comboDescription;
	}
	
	
	public String getComboName() {
		return comboName;
	}
	
	public String getComboDescription() {
		return comboDescription;
	}
	
	public void add(Leg p) {
		this.legs.add(p);
	}
	
	public void add(int amount, Contract contract, double px) {
		this.legs.add(new Leg(amount, contract, px));
	}
	
	public double getComboOpenPrice() {
		double val = 0;
		for (Leg leg : this.legs) {
			val += leg.getAmount() * leg.getOpenPrice();
		}
		return val;
	}
	
	public double getExpiredPriceAt(double underl) {
		double val = 0d;
		for (Leg leg : this.legs) {
			val += leg.getAmount() * leg.getExpirationPriceAt(underl);
		}
		return val;
	}
	
	public double getPnlAt(double underl) {
		double pnl = 0d;
		for (Leg leg : this.legs) {
			pnl += leg.getAmount() * leg.getPnlAt(underl);
		}
		return pnl;
	}
	
	// public TreeMap<Double, Double> getExtendedPnLPoints() {
	// 	PnLmajstor pm = new PnLmajstor(this.getLegs());
	// 	List<Double> expandedStrajkovi = pm.getExpandedStrajkovi();
	//
	// 	TreeMap<Double, Double> tmp = new TreeMap<>();
	//
	// 	System.out.println("Combo " + getComboName());
	//
	// 	for (Double strajk : expandedStrajkovi){
	// 		double y = 0;
	// 		for (Leg leg : legs){
	// 			y += leg.getPnlAt(strajk);
	// 			System.out.print("\t" + leg.getAmount() + "\t" + leg.getContract().toString() + "\t" + "@ " + leg.getOpenPrice() +  " PnL at " + strajk + " = " + leg.getPnlAt(strajk));
	// 		}
	//
	// 		if (tmp.containsKey(strajk)) {
	// 			double prev = tmp.get(strajk);
	// 			y += prev;
	// 			System.out.print(" prev" + prev + " new y " + y);
	// 		}
	// 		tmp.put(strajk, y);
	// 		System.out.println("\t ->> combo total at strajk " + strajk + " pnl=" + y);
	//
	// 	}
	// 	return tmp;
	// }
	
	public TreeMap<Double, Double> getExtendedPnLPoints() {
		PnLmajstor pm = new PnLmajstor(this.getLegs());
		List<Double> tacniStrajkovi = pm.getExpandedStrajkovi();
		return privatniPnLcalc(tacniStrajkovi);
	}
	
	public TreeMap<Double, Double> getExactPnLPoints() {
		PnLmajstor pm = new PnLmajstor(this.getLegs());
		List<Double> tacniStrajkovi = pm.getExactStrajkovi();
		return privatniPnLcalc(tacniStrajkovi);
	}
	
	private TreeMap<Double, Double> privatniPnLcalc(List<Double> strajkovi) {
		TreeMap<Double, Double> tmp = new TreeMap<>();
		
		// System.out.println("Combo " + getComboName());
		
		for (Double strajk : strajkovi) {
			double y = 0;
			for (Leg leg : legs) {
				y += leg.getPnlAt(strajk);
				// System.out.print("\t" + leg.getAmount() + "\t" + leg.getContract().toString() + "\t" + "@ " + leg.getOpenPrice() + " PnL at " + strajk + " = " + leg.getPnlAt(strajk));
			}
			
			if (tmp.containsKey(strajk)) {
				double prev = tmp.get(strajk);
				y += prev;
				// System.out.print(" prev" + prev + " new y " + y);
			}
			tmp.put(strajk, y);
			// System.out.println("\t ->> combo total at strajk " + strajk + " pnl=" + y);
			
		}
		return tmp;
	}
	
	
	public List<Leg> getLegs() {
		return legs;
	}
	
	public double getDelta() {
		double delta = 0;
		for (Leg leg : legs) {
			delta += leg.getDelta();
		}
		return delta;
	}
	
	
	public double getMaxProfit() {
		double maxP = 0;
		for (Map.Entry<Double, Double> point : getExactPnLPoints().entrySet()) {
			maxP = Math.max(maxP, point.getValue());
		}
			return maxP;
	}
		
		
		public double getMaxLoss() {
			double maxL = 0;
			for (Map.Entry<Double, Double> point : getExactPnLPoints().entrySet()) {
				maxL = Math.min(maxL, point.getValue());
			}
			return maxL;
		}
}
