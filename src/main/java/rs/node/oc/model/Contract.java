package rs.node.oc.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Contract {
	private TIP tip;
	private double strajk;

	private double bid;
	private double ask;
	private double intrinsic;
	private double extrinsic;

	public Contract(TIP tip, double strajk) {
		this.strajk = strajk;
		this.tip = tip;
	}


	public double getStrajk() {
		return strajk;
	}

	public TIP getTip() {
		return tip;
	}

	public double getIntrinsic(double cena) {
		double val = Math.max(0,Math.abs(cena - strajk));
		return val;
		// switch (putCall) {
		// 	case CALL -> {
		// 		if (cena > strajk){
		// 			return cena - strajk;
		// 		} else {
		// 			return 0;
		// 		}
		// 	}
		//
		// 	case PUT -> {
		// 		if (cena >= strajk){
		// 			return 0;
		// 		} else {
		// 			return strajk - cena;
		// 		}
		// 	}
		//
		// }
	}

	public double getExtrinsic() {

		return extrinsic;
	}
}
