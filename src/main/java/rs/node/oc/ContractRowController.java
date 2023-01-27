package rs.node.oc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import rs.node.oc.model.Combo;
import rs.node.oc.model.Leg;

import javax.swing.plaf.PanelUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContractRowController implements Initializable {
	public CheckBox enable;
	
	public Spinner<Integer> amount;
	public Spinner<Double> strajk;
	public Spinner<Double> avg_px;
	public Spinner<Double> delta;
	public Label call_put;
	
	Combo combo;
	
	
	public ContractRowController() {
	}

	

	
	public void initialize(URL location, ResourceBundle resources) {
		amount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-3, 3, 0, 1));
		amount.getValueFactory().setWrapAround(false);

		strajk.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10000, 400, 1));
		strajk.getValueFactory().setWrapAround(false);

		avg_px.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 5, 0, 0.05));
		avg_px.getValueFactory().setWrapAround(true);

		delta.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-1, 1, 0.5, 0.02));
		delta.getValueFactory().setWrapAround(true);
		
	}
	
	
	public void amountWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			amount.increment();
		} else {
			amount.decrement();
		}
	}
	
	public void strajkWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			strajk.increment();
		} else {
			strajk.decrement();
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
	


}
