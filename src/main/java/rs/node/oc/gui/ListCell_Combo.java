package rs.node.oc.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import rs.node.oc.model.Combo;

public class ListCell_Combo extends ListCell<Combo> {
	private final VBox content;
	private final Text naziv;
	private final Text detail;
	
	
	public ListCell_Combo() {
		super();
		naziv = new Text();
		detail = new Text();
		content = new VBox(naziv, detail);
		content.setSpacing(1);
	}
	
	@Override
	protected void updateItem(Combo combo, boolean empty) {
		super.updateItem(combo, empty);
		if (combo == null || empty) {       // <== test for null combo and empty parameter
			setGraphic(null);
			setText(null);
		} else {
			naziv.setText(combo.getComboName());
			detail.setText(combo.getComboDescription());
			setGraphic(content);
		}
	}
}
