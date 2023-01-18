package rs.node.oc.model;

import java.util.*;

public class Combo {

	private final List<Pozicija> lista = new ArrayList<>();
	private TreeMap<Double, Double> characteristic = new TreeMap<>();
	
	public void add(Pozicija p){
		lista.add(p);
	}
	
	public void add(int amount, Contract contract, double px){
		lista.add(new Pozicija(amount, contract, px));
	}
	
	public double getPx(){
		double val = 0;
		for (Pozicija p : lista){
			val += p.getPx();
		}
		return val;
	}
	
	public double getExpirationPxAt(double underl){
		double val = 0;
		for (Pozicija p : lista){
			val += p.getPxAtExpiration(underl);
		}
		return val;
	}
	
	public TreeMap<Double, Double> getCharacteristic() {
		TreeMap<Double, Double> tmp = new TreeMap<>();
		for (Pozicija p : lista) {
			double strajk = p.getContract().getStrajk();
			double pnl = p.getPnL(strajk);
			tmp.put(strajk, pnl);
		}
		return tmp;
	}
}
