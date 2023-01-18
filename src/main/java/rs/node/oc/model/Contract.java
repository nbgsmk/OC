package rs.node.oc.model;

public class Contract {
	
	protected int amount;
	protected final double strajk;
	
	protected double bid;
	protected double ask;
	private double intrinsic;
	private double extrinsic;

	
	public Contract(double strajk) {
		this.strajk = strajk;
	}

	public double getStrajk() {
		return strajk;
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
