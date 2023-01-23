package rs.node.oc.model;

public class Pozicija {
	
	private final Contract contract;
	private final int amount;
	private double openPrice;
	private double delta = 0;
	
	public Pozicija(int amount, Contract contract, double openPrice) {
		this.contract = contract;
		this.amount = amount;
		this.openPrice = openPrice;
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public double getOpenPrice() {
		return openPrice;
	}
	
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	
	public double getDelta() {
		return delta;
	}
	
	public void setDelta(double delta) {
		this.delta = delta;
	}
	
	public double getExpirationPriceAt(double underl){
		return amount * getContract().getExpirationPriceAt(underl);
	}
	
	public double getPnLat(double underl){
		return getExpirationPriceAt(underl) - getOpenPrice();
	}
}
