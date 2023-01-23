package rs.node.oc.model;

public class Contract {
	
	private String ticker;
	private final double strajk;
	private String shortName;
	private String description;
	private double multiplier = 100;

	
	public Contract(double strajk) {
		this.strajk = strajk;
	}
	
	public Contract(String ticker, double strajk) {
		this.ticker = ticker;
		this.strajk = strajk;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public double getStrajk() {
		return strajk;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getMultiplier() {
		return multiplier;
	}
	
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	
	
	public double getTimeValue(double underl){
		if (this instanceof Call) {
			// return px - Math.max((underl - strajk), 0);
			return Math.max((underl - strajk), 0);
		} else {
			// return px - Math.max((strajk - underl), 0);
			return Math.max((strajk - underl), 0);
		}
	}
	
	public double getExpirationPriceAt(double underl){
		if (this instanceof Call) {
			return Math.max(strajk - underl, 0);
		} else {
			// return Math.max(underl - strajk, 0);
			return Math.max(strajk - underl, 0);
		}
	}
	

	

	
	/*
	public double getIntrinsic(double last){
		// return Math.abs(last - avgPx);
		if (this instanceof Call) {
			return (last - avgPx);
		} else {
			return (avgPx - last);
		}
	};

	public double getExtrinsic(double last){
		if (this instanceof Call) {
			return Math.min((last - avgPx), 0);
		} else {
			return Math.max((avgPx - last), 0);
		}
	};
	*/
}
