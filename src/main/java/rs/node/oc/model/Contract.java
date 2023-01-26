package rs.node.oc.model;


public class Contract {
	
	private String ticker;
	private double strajk;
	private String shortName;
	private String description;
	private double multiplier = 100;
	
	public Contract() {
	}
	
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
		this.multiplier = (double) multiplier;
	}
	
	
	public double getTimeValue(double underl){
		if (this instanceof Call) {
			// return px - Math.max((underl - strajk), 0d);
			return Math.max((underl - strajk), 0d);
		} else {
			// return px - Math.max((strajk - underl), 0d);
			return Math.max((strajk - underl), 0d);
		}
	}
	
	public double getExpirationPriceAt(double underl){
		if (this.isITMaAt(underl)) {
			return Math.abs(strajk - underl);
		} else {
			return 0;
		}
	}
	
	public boolean isITMaAt(double underl){
		if ((this instanceof Call) && (strajk < underl)) {
			return true;
		} else if ((this instanceof Put) && (strajk > underl)){
			return true;
		} else {
			return false;
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

	@Override
	public String toString() {
		// String s = super.toString();
		String s = getClass().getSimpleName();
		s += " " + Double.toString(strajk);
		return s;
	}
	
	
	
	/*
	 * NE KORISTITI ovo ispod. Sluzi samo za XMLEncoder / XMLDecoder
	 * */
	
	
	public String getTicker() {
		return ticker;
	}
	
	public void setStrajk(double strajk) {
		this.strajk = strajk;
	}
}
