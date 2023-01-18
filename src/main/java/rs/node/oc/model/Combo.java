package rs.node.oc.model;

import java.util.ArrayList;
import java.util.List;

public class Combo {

	private final List<Pozicija> lista = new ArrayList<>();
	
	
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
}
