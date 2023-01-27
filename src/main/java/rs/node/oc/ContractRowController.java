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
import java.util.List;
import java.util.ResourceBundle;

public class ContractRowController extends ListCell<Leg> implements Initializable {
	@FXML
	public CheckBox enable;
	
	@FXML
	public Spinner<Integer> amount;
	@FXML
	public Spinner<Double> strajk;
	@FXML
	public Spinner<Double> avg_px;
	@FXML
	public Spinner<Double> delta;
	@FXML
	public Label call_put;
	
	public ContractRowController() {
		// amount = new Spinner<>();
		// amount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-3, 3, 0, 1));
		// amount.getValueFactory().setWrapAround(false);
		//
		// strajk = new Spinner<>();
		// strajk.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10000, 400, 1));
		// strajk.getValueFactory().setWrapAround(false);
		//
		// avg_px = new Spinner<>();
		// avg_px.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 5, 0, 0.05));
		// avg_px.getValueFactory().setWrapAround(true);
		//
		// delta = new Spinner<>();
		// delta.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-1, 1, 0.5, 0.02));
		// delta.getValueFactory().setWrapAround(true);
		
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
	
	
	public void setAmount(int amount) {
		this.amount.getValueFactory().setValue(amount);
	}
	
	public void setStrajk(Double strajk) {
		this.strajk.getValueFactory().setValue(strajk);
	}
	
	public void setAvg_px(Spinner<Double> avg_px) {
		this.avg_px = avg_px;
	}
	
	public void setDelta(Spinner<Double> delta) {
		this.delta = delta;
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
