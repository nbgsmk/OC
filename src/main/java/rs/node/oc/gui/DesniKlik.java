package rs.node.oc.gui;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import rs.node.oc.model.Combo;
import rs.node.oc.model.DataModel;

public class DesniKlik extends ContextMenu {
	
	private DataModel model;
	
	public DesniKlik(DataModel model) {
		this.model = model;
		
		MenuItem item = new MenuItem("TBD napisi");
		item.setOnAction((event -> {
			// System.out.println("Nesto - napisi");
			// model.getComboPresets().remove(combo);
		}));
		this.getItems().add(item);
		

		item = new MenuItem("TBD obrisi");
		item.setOnAction((event -> {
			// System.out.println("Nesto - obrisi");
			// model.getComboPresets().remove(selectedItem);
		}));
		this.getItems().add(item);
	}
	
	public void obrisi(int sel){
		model.getComboPresets().remove(sel);
		System.out.println("a sad?");
	}
	

}
