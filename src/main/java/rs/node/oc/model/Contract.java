package rs.node.oc.model;

public abstract class Contract {
	
	protected double strajk = 0;
	
	protected double bid;
	protected double ask;
	protected double avgPx;
	private double intrinsic;
	private double extrinsic;

	
	
	public Contract(double strajk) {
		this.strajk = strajk;
	}


	public double getStrajk() {
		return strajk;
	}
	

	public abstract double getIntrinsic(double cena);

	public double getExtrinsic() {
		return extrinsic;
	}
}
