package rs.node.oc.model;

import java.io.Serializable;

public class Put extends Contract implements Serializable {
	
	public Put() {
	}
	
	public Put(double strajk) {
		super(strajk);
		setShortName("P");
	}

}
