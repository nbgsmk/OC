package rs.node.oc.model;

public class Pozicija {
	
	private Contract contract;
	private int amount;
	private double px;
	
	
	public Pozicija(int amount, Contract contract, double px) {
		this.contract = contract;
		this.amount = amount;
		this.px = px;
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public double getPx() {
		return px * amount;
	}
	
	public double getPxAtExpiration(double underl){
		return amount * getContract().getPxAtExpiration(underl);
	}
}
