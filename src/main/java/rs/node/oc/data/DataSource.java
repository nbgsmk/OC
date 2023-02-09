package rs.node.oc.data;

import rs.node.oc.io.Snimac;
import rs.node.oc.model.Combo;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
	
	public List<Combo> loadPresets(){
		Snimac sn = new Snimac();
		Object tmp = sn.readXml(Snimac.presetList);
		if (tmp instanceof List<?>) {
			return (List<Combo>) tmp;
		} else {
			return null;
		}
	}
	
	public Combo loadLastUsed(){
		Snimac sn = new Snimac();
		return (Combo) sn.readXml(Snimac.defCombo);
	}
	
	public void savePresets(List<Combo> comboPresets) {
		Snimac sn = new Snimac();
		if ((comboPresets != null) && (comboPresets.size() > 0)) {
			List<Combo> presetList = new ArrayList<>(comboPresets);
			sn.writeXml(Snimac.presetList, presetList);
		}
	}
	
	public void saveCurrent(Combo combo){
		Snimac sn = new Snimac();
		sn.writeXml(Snimac.defCombo, combo);
	}
}
