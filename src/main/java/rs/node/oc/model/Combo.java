package rs.node.oc.model;

import java.util.*;

public class Combo {

	private final List<Pozicija> pozicije = new ArrayList<>();
	private TreeMap<Double, Double> pnlPoints = new TreeMap<>();
	
	public void add(Pozicija p){
		pozicije.add(p);
	}
	
	public void add(int amount, Contract contract, double px){
		pozicije.add(new Pozicija(amount, contract, px));
	}
	
	public double getComboOpenPrice(){
		double val = 0;
		for (Pozicija p : pozicije){
			val += p.getOpenPrice() * p.getAmount();
		}
		return val;
	}
	
	public double getExpirationPriceAt(double underl){
		double val = 0;
		for (Pozicija p : pozicije){
			val += p.getExpirationPriceAt(underl);
		}
		return val;
	}
	
	public double getPnLAt(double underl){
		double pnl = 0;
		for (Pozicija p : pozicije) {
			pnl += p.getPnLat(underl);
		}
		return pnl;
	}
	
	public TreeMap<Double, Double> getPnLPoints() {
		TreeMap<Double, Double> tm = new TreeMap<>();
		for (Pozicija p : pozicije) {
			double strajk = p.getContract().getStrajk();
			double pnl = p.getPnLat(strajk);
			if (tm.containsKey(strajk)) {
				pnl += p.getPnLat(strajk);
			}
			tm.put(strajk, pnl);
		}
		return tm;
	}
	
	public List<Pozicija> getPozicije() {
		return pozicije;
	}
}
