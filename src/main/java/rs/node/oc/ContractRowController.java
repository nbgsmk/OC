package rs.node.oc;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

import javax.swing.plaf.PanelUI;

public class ContractRowController extends PanelUI {
	public CheckBox enable;
	public Label call_put;
	public Label amount;
	public Spinner<Double> avg_px;
	public Spinner<Integer> editable;

	public ContractRowController(Label call_put, Label amount) {
		this.call_put = call_put;
		this.amount = amount;
	}

	public CheckBox getEnable() {
		return enable;
	}

	public void setEnable(CheckBox enable) {
		this.enable = enable;
	}

	public Label getCall_put() {
		return call_put;
	}

	public void setCall_put(Label call_put) {
		this.call_put = call_put;
	}

	public Label getAmount() {
		return amount;
	}

	public void setAmount(Label amount) {
		this.amount = amount;
	}

	public Spinner<Double> getAvg_px() {
		return avg_px;
	}

	public void setAvg_px(Spinner<Double> avg_px) {
		this.avg_px = avg_px;
	}

	public Spinner<Integer> getEditable() {
		return editable;
	}

	public void setEditable(Spinner<Integer> editable) {
		this.editable = editable;
	}
}
