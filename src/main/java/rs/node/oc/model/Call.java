package rs.node.oc.model;

import java.io.Serializable;

public class Call extends Contract implements Serializable {
	
	public Call() {
	}
	
	public Call(double strajk) {
		super(strajk);
		setSkr("C");
	}
	
	
}
