package rs.node.oc.model;

public class Put extends Contract{
	
	
	public Put(double strajk) {
		super(strajk);
	}
	
	@Override
	public double getIntrinsic(double cena) {
		return Math.abs(Math.max(strajk - cena, 0));
	}
}
