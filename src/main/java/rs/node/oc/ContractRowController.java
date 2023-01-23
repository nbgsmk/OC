package rs.node.oc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import rs.node.oc.model.Combo;

import javax.swing.plaf.PanelUI;
import java.net.URL;
import java.util.ResourceBundle;

public class ContractRowController implements Initializable {
	public CheckBox enable;
	
	public Spinner<Integer> amount;
	public Spinner<Double> avg_px;
	public Spinner<Double> delta;
	public CheckBox call_put;
	
	Combo combo;
	
	public ContractRowController(Combo combo) {
		this.combo = combo;
	}
	
	public ContractRowController() {
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		amount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3, 0, 1));
		amount.getValueFactory().setWrapAround(false);
		
		avg_px.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 5, 0, 0.05));
		avg_px.getValueFactory().setWrapAround(true);
		
		delta.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1, 0.5, 0.02));
		delta.getValueFactory().setWrapAround(true);

	}
	
	
	public void amountWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			amount.increment();
		} else {
			amount.decrement();
		}
	}
	
	public void avgPxWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			avg_px.increment();
		} else {
			avg_px.decrement();
		}
	}
	
	public void deltaWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			delta.increment();
		} else {
			delta.decrement();
		}
	}
	
	public void setCombo(Combo combo){
		this.combo = combo;
	}
}
