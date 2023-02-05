package rs.node.oc.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import rs.node.oc.App;
import rs.node.oc.model.Leg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListCell_Leg extends HBox implements Initializable {

	@FXML
	public HBox leg_row_content;
	
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
	
	private Leg leg;
	
	public ListCell_Leg(Leg leg) {
		App.LOG.log(System.Logger.Level.INFO, getClass().getSimpleName());

		this.leg = leg;

		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("leg-row.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
	}
	
	// @Override
	// protected void updateItem(Leg leg, boolean empty) {
	// 	super.updateItem(leg, empty);
	// 	App.LOG.log(System.Logger.Level.INFO, getClass().getSimpleName());
	// 	if (leg == null || empty) {     // <== test for null leg and empty parameter
	// 		setText(null);
	// 		setGraphic(null);
	// 	} else {
	// 		this.leg = leg;
	// 		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leg-row.fxml"));
	// 		fxmlLoader.setController(this);
	// 		fxmlLoader.setRoot(this);
	//
	// 		setAmount(leg.getAmount());
	// 		call_put.setText(leg.getContract().getSkr());
	// 		setStrajk(leg.getContract().getStrajk());
	// 		setAvg_px(leg.getOpenPrice());
	// 		setDelta(leg.getDelta());
	// 		setGraphic(leg_row_content);
	// 	}
	// }
	
	
	public void setAmount(int amount) {
		this.amount.getValueFactory().setValue(amount);
	}
	
	public void setStrajk(Double strajk) {
		this.strajk.getValueFactory().setValue(strajk);
	}
	
	public void setAvg_px(double avg_px) {
		this.avg_px.getValueFactory().setValue(avg_px);
	}
	
	public void setDelta(double delta) {
		this.delta.getValueFactory().setValue(delta);
	}
	
	@FXML
	public void amountWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			amount.increment();
		} else {
			amount.decrement();
		}
		leg.setAmount(amount.getValue());
	}
	
	@FXML
	public void strajkWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			strajk.increment();
		} else {
			strajk.decrement();
		}
		leg.getContract().setStrajk(strajk.getValue());
	}
	
	@FXML
	public void avgPxWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			avg_px.increment();
		} else {
			avg_px.decrement();
		}
		leg.setOpenPrice(avg_px.getValue());
	}
	
	@FXML
	public void deltaWheel(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			delta.increment();
		} else {
			delta.decrement();
		}
		leg.setDelta(delta.getValue());
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
		App.LOG.log(System.Logger.Level.INFO, getClass().getSimpleName());
		
		amount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-3, 3, 0, 1));
		amount.getValueFactory().setWrapAround(false);
		setAmount(leg.getAmount());
		
		call_put.setText(leg.getContract().getSkr());
		
		strajk.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10000, 400, 1));
		strajk.getValueFactory().setWrapAround(false);
		setStrajk(leg.getContract().getStrajk());
		strajk.getEditor().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				strajk.getValueFactory().setValue(Double.valueOf(newValue));
			}
		});
		
		avg_px.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-20, 20, 0, 0.02));
		avg_px.getValueFactory().setWrapAround(false);
		setAvg_px(leg.getOpenPrice());
		avg_px.getEditor().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				App.LOG.log(System.Logger.Level.INFO, getClass().getSimpleName());
				avg_px.getValueFactory().setValue(Double.valueOf(newValue));
			}
		});
		

		delta.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-1, 1, 0.5, 0.02));
		delta.getValueFactory().setWrapAround(true);
		delta.getEditor().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				delta.getValueFactory().setValue(Double.valueOf(newValue));
			}
		});
		
	}
}
