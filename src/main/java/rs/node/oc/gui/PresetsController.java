package rs.node.oc.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import rs.node.oc.model.Combo;
import rs.node.oc.model.DataModel;

public class PresetsController {

	@FXML
	private ListView<Combo> listView;
	
	private DataModel model;
	
	public void initModel(DataModel model) {
		// ensure model is only set once:
		if (this.model != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.model = model ;
		listView.setItems(model.getComboPresets());
		
		listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
				model.setCurrentCombo(newSelection));
		
		
		model.currentComboProperty().addListener((obs, oldCombo, newCombo) -> {
			if (newCombo == null) {
				listView.getSelectionModel().clearSelection();
			} else {
				listView.getSelectionModel().select(newCombo);
			}
		});
	}
}
