package rs.node.oc.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import rs.node.oc.model.Combo;

public class ListCell_Combo extends ListCell<Combo> {
	private final HBox content;
	private final Text txt;
	
	
	public ListCell_Combo() {
		super();
		this.txt = new Text();
		HBox hBox = new HBox(txt);
		content = new HBox(new Label("combo je: "), hBox);
		content.setSpacing(10);
	}
	
	@Override
	protected void updateItem(Combo combo, boolean empty) {
		super.updateItem(combo, empty);
		if (combo != null && !empty) { // <== test for null combo and empty parameter
			txt.setText(combo.getComboDescription());
			setGraphic(content);
		} else {
			setGraphic(null);
			setText(null);
		}
	}
}
