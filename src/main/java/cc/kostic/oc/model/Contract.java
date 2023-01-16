package cc.kostic.oc.model;

public class Contract {
	private TIP tip;
	private Double strajk;

	private Double bid = null;
	private Double ask = null;
	private Double intrinsic = null;
	private final Double extrinsic = null;

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
		return Math.max(0,Math.abs(cena - strajk));
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
