package rs.node.oc.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contract {
	
	private final StringProperty ticker = new SimpleStringProperty();
	private final DoubleProperty strajk = new SimpleDoubleProperty();
	private final StringProperty skr = new SimpleStringProperty();
	private final StringProperty description = new SimpleStringProperty();
	private final DoubleProperty multiplier = new SimpleDoubleProperty(100);
	
	public Contract() {
	}
	
	public Contract(double strajk) {
		this.strajk.set(strajk);
	}
	
	public Contract(String ticker, double strajk) {
		this.ticker.set(ticker);
		this.strajk.set(strajk);
	}
	
	public void setTicker(String ticker) {
		this.ticker.set(ticker);
	}
	
	public final String getTicker() {
		return ticker.get();
	}
	
	public StringProperty tickerProperty() {
		return ticker;
	}
	
	public double getStrajk() {
		return strajk.get();
	}
	
	public DoubleProperty strajkProperty() {
		return strajk;
	}
	
	public void setStrajk(double strajk) {
		this.strajk.set(strajk);
	}
	
	public String getSkr() {
		return skr.get();
	}
	
	public StringProperty skrProperty() {
		return skr;
	}
	
	public void setSkr(String skr) {
		this.skr.set(skr);
	}
	
	public StringProperty descriptionProperty() {
		return description;
	}
	
	public DoubleProperty multiplierProperty() {
		return multiplier;
	}
	

	/**/
	/**/
	
	public double getTimeValue(double underl){
		if (this instanceof Call) {
			// return px - Math.max((underl - strajk), 0d);
			return Math.max((underl - strajk.get()), 0d);
		} else {
			// return px - Math.max((strajk - underl), 0d);
			return Math.max((strajk.get() - underl), 0d);
		}
	}
	
	public double getExpirationPriceAt(double underl){
		if (this.isITMaAt(underl)) {
			return Math.abs(strajk.get() - underl);
		} else {
			return 0;
		}
	}
	
	public boolean isITMaAt(double underl){
		if ((this instanceof Call) && (strajk.get() < underl)) {
			return true;
		} else if ((this instanceof Put) && (strajk.get() > underl)){
			return true;
		} else {
			return false;
		}
	}

	
	
	//
	// @Override
	// public String toString() {
	// 	// String s = super.toString();
	// 	String s = getClass().getSimpleName();
	// 	s += " " + Double.toString(strajk);
	// 	return s;
	// }
	//
	
}
