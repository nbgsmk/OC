package rs.node.oc.model;

public class Pozicija {
	
	private final Contract contract;
	private final int amount;
	private final double openPrice;
	
	
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
		return openPrice * amount;
	}
	
	public double getExpirationPriceAt(double underl){
		return amount * getContract().getExpirationPriceAt(underl);
	}
	
	public double getPnLat(double underl){
		return getExpirationPriceAt(underl) - getOpenPrice();
	}
}
