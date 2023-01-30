package rs.node.oc.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import rs.node.oc.model.Leg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListCell_Leg extends ListCell<Leg> {

	@FXML
	public HBox hb_content;
	
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
	
	
	private HBox content;
	private Text t_amt;
	private Text t_tip;
	private Text t_px;
	
	// public ListCell_Leg() {
	// 	super();
	// 	t_amt = new Text();
	// 	t_tip = new Text();
	// 	t_px = new Text();
	// 	HBox hBox = new HBox(t_amt, t_tip, t_px);
	// 	content = new HBox(new Label("[Graphic]"), new CheckBox("izaberi"), hBox);
	// 	content.setSpacing(10);
	// }
	
	@Override
	protected void updateItem(Leg leg, boolean empty) {
		super.updateItem(leg, empty);
		if (leg == null || empty) {     // <== test for null leg and empty parameter
			setText(null);
			setGraphic(null);
		} else {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("legr.fxml"));
				loader.setController(this);
				loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			// t_amt.setText(String.valueOf(leg.getAmount()));
			// t_tip.setText(leg.getContract().getSkr() + " " + leg.getContract().getStrajk());
			// t_px.setText(String.format(" @ %.2f", leg.getOpenPrice()));
			// call_put.setText("RADI");
			setGraphic(hb_content);
			
		}
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
	
	
	public void initialize_PATKA(URL location, ResourceBundle resources) {
		amount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-3, 3, 0, 1));
		amount.getValueFactory().setWrapAround(false);
		
		strajk.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10000, 400, 1));
		strajk.getValueFactory().setWrapAround(false);
		
		avg_px.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 5, 0, 0.05));
		avg_px.getValueFactory().setWrapAround(true);
		
		delta.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-1, 1, 0.5, 0.02));
		delta.getValueFactory().setWrapAround(true);
		
	}
}
