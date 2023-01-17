package rs.node.oc.model;

import java.util.List;

public class Combo {

	private List<Contract> combo;
	
	public Combo(List<Contract> combo) {
		this.combo = combo;
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
}
