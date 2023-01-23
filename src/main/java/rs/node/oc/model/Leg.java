package rs.node.oc.model;

public class Leg {
	
	private static int redniBr;
	private int amount;
	private final Contract contract;
	private double bid;
	private double ask;
	private double openPrice;
	private double delta = 0;
	
	public Leg(int amount, Contract contract, double openPrice) {
		redniBr++;
		this.contract = contract;
		this.amount = amount;
		this.openPrice = openPrice;
	}
	
	public static int getRedniBr() {
		return redniBr;
	}
	
	public static void setRedniBr(int redniBr) {
		Leg.redniBr = redniBr;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public double getBid() {
		return bid;
	}
	
	public void setBid(double bid) {
		this.bid = bid;
	}
	
	public double getAsk() {
		return ask;
	}
	
	public void setAsk(double ask) {
		this.ask = ask;
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
		return getContract().getExpirationPriceAt(underl);
	}
	
	public double getPnlAt(double underl){
		return getExpirationPriceAt(underl) - getOpenPrice();
	}
}
