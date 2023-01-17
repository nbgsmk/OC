package rs.node.oc.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Contract {
	private TIP putCall;
	private double strajk;
	
	private double bid;
	private double ask;
	private double intrinsic;
	private double extrinsic;

	public Contract(TIP tip, double strajk) {
		this.putCall = tip;
		this.strajk = strajk;
	}


	public double getStrajk() {
		return strajk;
	}
	

	public double getIntrinsic(double cena){
		double val = 0;
		switch (putCall) {
			case CALL -> {
				val = Math.abs(Math.max(cena - strajk, 0));
			}

			case PUT -> {
				val = Math.abs(Math.max(strajk - cena, 0));
			}
		}
		return val;
	}

	public double getExtrinsic() {

		return extrinsic;
	}
}
