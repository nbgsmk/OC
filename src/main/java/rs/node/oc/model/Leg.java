package rs.node.oc.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;

public class Leg implements Serializable {
	
	private static final IntegerProperty redniBr = new SimpleIntegerProperty();
	private final IntegerProperty amount = new SimpleIntegerProperty();
	private Contract contract;
	private final DoubleProperty bid = new SimpleDoubleProperty();
	private final DoubleProperty ask = new SimpleDoubleProperty();
	private final DoubleProperty avgPx = new SimpleDoubleProperty();
	private final DoubleProperty delta = new SimpleDoubleProperty(0);
	
	
	public Leg() {
	}
	
	
	public Leg(int amount, Contract contract, double avgPx) {
		redniBr.set(redniBr.get()+1);
		this.contract = contract;
		this.amount.set(amount);
		this.avgPx.set(avgPx);
	}
	
	public static int getRedniBr() {
		return redniBr.get();
	}
	
	public static IntegerProperty redniBrProperty() {
		return redniBr;
	}
	
	public static void setRedniBr(int redniBr) {
		Leg.redniBr.set(redniBr);
	}
	
	public int getAmount() {
		return amount.get();
	}
	
	public IntegerProperty amountProperty() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount.set(amount);
	}
	
	
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public double getBid() {
		return bid.get();
	}
	
	public DoubleProperty bidProperty() {
		return bid;
	}
	
	public void setBid(double bid) {
		this.bid.set(bid);
	}
	
	public double getAsk() {
		return ask.get();
	}
	
	public DoubleProperty askProperty() {
		return ask;
	}
	
	public void setAsk(double ask) {
		this.ask.set(ask);
	}
	
	public double getAvgPx() {
		return avgPx.get();
	}
	
	public DoubleProperty avgPxProperty() {
		return avgPx;
	}
	
	public void setAvgPx(double avgPx) {
		this.avgPx.set(avgPx);
	}
	
	public double getDelta() {
		return delta.get();
	}
	
	public DoubleProperty deltaProperty() {
		return delta;
	}
	
	public void setDelta(double delta) {
		this.delta.set(delta);
	}
	
	

	
	
	public double getExpirationPriceAt(double underl){
		return getContract().getExpirationPriceAt(underl);
	}
	
	public double getPnlAt(double underl){
		return getAmount() * (getExpirationPriceAt(underl) - getAvgPx());
	}
	
	
}
