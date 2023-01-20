package rs.node.oc.model;

import java.util.*;

public class Combo {

	private final List<Pozicija> lista = new ArrayList<>();
	private TreeMap<Double, Double> pnlPoints = new TreeMap<>();
	
	public void add(Pozicija p){
		lista.add(p);
	}
	
	public void add(int amount, Contract contract, double px){
		lista.add(new Pozicija(amount, contract, px));
	}
	
	public double getOpenPrice(){
		double val = 0;
		for (Pozicija p : lista){
			val += p.getOpenPrice();
		}
		return val;
	}
	
	public double getExpirationPriceAt(double underl){
		double val = 0;
		for (Pozicija p : lista){
			val += p.getExpirationPriceAt(underl);
		}
		return val;
	}
	
	public double getPnLAt(double underl){
		double pnl = 0;
		for (Pozicija p : lista) {
			pnl += p.getPnLat(underl);
		}
		return pnl;
	}
	
	public TreeMap<Double, Double> getPnLPoints() {
		TreeMap<Double, Double> tm = new TreeMap<>();
		for (Pozicija p : lista) {
			double strajk = p.getContract().getStrajk();
			double pnl = p.getPnLat(strajk);
			if (tm.containsKey(strajk)) {
				pnl += p.getPnLat(strajk);
			}
			tm.put(strajk, pnl);
		}
		return tm;
	}
}
