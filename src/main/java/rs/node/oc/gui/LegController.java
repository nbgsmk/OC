package rs.node.oc.gui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import rs.node.oc.model.DataModel;

public class LegController {
	
	private DataModel model;
	
	@FXML
	public CheckBox enable;
	@FXML
	public Spinner<Integer> amount;
	@FXML
	public Label call_put;
	@FXML
	public Spinner<Double> strajk;
	@FXML
	public Spinner<Double> avg_px;
	@FXML
	public Spinner<Double> delta;
	
	
	public void initModel(DataModel model) {
		if (this.model != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		this.model = model ;
		model.currentComboProperty().addListener((obs, oldCombo, newCombo) -> {
			if (oldCombo != null) {
				// firstNameField.textProperty().unbindBidirectional(oldCombo.firstNameProperty());
				// lastNameField.textProperty().unbindBidirectional(oldCombo.lastNameProperty());
				// emailField.textProperty().unbindBidirectional(oldCombo.emailProperty());
				
			}
			if (newCombo == null) {
				// firstNameField.setText("");
				// lastNameField.setText("");
				// emailField.setText("");
			} else {
				// firstNameField.textProperty().bindBidirectional(newCombo.firstNameProperty());
				// lastNameField.textProperty().bindBidirectional(newCombo.lastNameProperty());
				// emailField.textProperty().bindBidirectional(newCombo.emailProperty());
			}
		});
	}
}
