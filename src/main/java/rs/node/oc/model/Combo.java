package rs.node.oc.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rs.node.oc.pnlcalc.PnLmajstor;

import java.io.Serializable;
import java.util.*;

public class Combo implements Serializable {
	
	private final StringProperty comboName = new SimpleStringProperty("");
	private final StringProperty comboDescription = new SimpleStringProperty("");
	private ObservableList<Leg> obs_legs = FXCollections.observableArrayList();
	
	
	public Combo() {
	}
	
	public Combo(String comboName) {
		this.comboName.set(comboName);
	}
	
	public Combo(String comboName, String comboDescription) {
		this.comboName.set(comboName);
		this.comboDescription.set(comboDescription);
	}
	
	
	public String getComboName() {
		return comboName.get();
	}
	
	public StringProperty comboNameProperty() {
		return comboName;
	}
	
	public void setComboName(String comboName) {
		this.comboName.set(comboName);
	}
	
	public void setComboDescription(String comboDescription) {
		this.comboDescription.set(comboDescription);
	}
	
	public StringProperty comboDescriptionProperty() {
		return comboDescription;
	}
	
	public String getComboDescription() {
		StringJoiner sj = new StringJoiner(", ", "", "");
		for (Leg l : obs_legs){
			String skr = l.getContract().getSkr();
			String strk = String.valueOf(l.getContract().getStrajk());
			strk = strk.replace(".0", "");
			
			sj.add(skr + " " + strk);
		}
		return sj.toString();
	}
	
	
	public void setLegs(List<Leg> legs) {
		this.obs_legs.addAll(legs);
	}
	
	public void add(Leg l) {
		this.obs_legs.add(l);
	}
	
	public void add(int amount, Contract contract, double px) {
		this.obs_legs.add(new Leg(amount, contract, px));
	}
	
	public double getComboOpenPrice() {
		double val = 0;
		for (Leg leg : this.obs_legs) {
			val += leg.getAmount() * leg.getAvgPx();
		}
		return val;
	}
	
	public double getExpiredPriceAt(double underl) {
		double val = 0d;
		for (Leg leg : this.obs_legs) {
			val += leg.getAmount() * leg.getExpirationPriceAt(underl);
		}
		return val;
	}
	
	public double getPnlAt(double underl) {
		double pnl = 0d;
		for (Leg leg : this.obs_legs) {
			pnl += leg.getAmount() * leg.getPnlAt(underl);
		}
		return pnl;
	}
	

	
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
			for (Leg leg : obs_legs) {
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
		return obs_legs;
	}
	
	public double getDelta() {
		double delta = 0;
		for (Leg leg : obs_legs) {
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
