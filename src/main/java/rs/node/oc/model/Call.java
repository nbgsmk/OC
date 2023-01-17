package rs.node.oc.model;

public class Call extends Contract{
	
	
	public Call(double strajk) {
		super(strajk);
	}
	
	@Override
	public double getIntrinsic(double cena) {
		return Math.abs(Math.max(cena - strajk, 0));
	}
}
