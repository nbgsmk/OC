package rs.node.oc.model;

import java.util.ArrayList;
import java.util.List;

public class Combo {

	private List<Contract> combo;
	
	public Combo() {
		this.combo = new ArrayList<>();
	}
	
	public List<Contract> getCombo() {
		return combo;
	}
	
	public void setCombo(List<Contract> combo) {
		this.combo = combo;
	}
	
	public void addContract(Contract contract){
		combo.add(contract);
	}
	
	public double getPriceAt(double last){
		double val = 0;
		for (Contract c : combo){
	

			
		}
		return val;
	}
}
