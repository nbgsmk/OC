package rs.node.oc.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import rs.node.oc.ContractRowController;
import rs.node.oc.model.Leg;

import java.io.IOException;

public class ListCell_Leg extends ListCell<Leg> {
	private HBox content;
	private Text t_amt;
	private Text t_tip;
	private Text t_px;
	
	public ListView<Leg> lv_legs;   // TODO brisati
	
	
	public ListCell_Leg() {
		super();
		t_amt = new Text();
		t_tip = new Text();
		t_px = new Text();
		HBox hBox = new HBox(t_amt, t_tip, t_px);
		content = new HBox(new Label("[Graphic]"), new CheckBox("izaberi"), hBox);
		content.setSpacing(10);
	}
	
	@Override
	protected void updateItem(Leg leg, boolean empty) {
		super.updateItem(leg, empty);
		if (leg != null && !empty) { // <== test for null leg and empty parameter
			t_amt.setText(String.valueOf(leg.getAmount()));
			t_tip.setText(leg.getContract().getSkr() + " " + leg.getContract().getStrajk());
			t_px.setText(String.format(" @ %.2f", leg.getOpenPrice()));
			setGraphic(content);
		} else {
			setText(null);
			setGraphic(null);
		}
		
	}
	
	
	
	private void BZVZ_VER_1(){
		// ++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++++++++++++++
		// ++++++++++ VERZIJA 1
		// ++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++++++++++++++
		ListView<Leg> lv_legs = new ListView<>();
		lv_legs.setCellFactory(new Callback<ListView<Leg>, ListCell<Leg>>() {
			@Override
			public ListCell<Leg> call(ListView<Leg> param) {
				ListCell<Leg> listCell = new ListCell<Leg>() {
					@Override
					protected void updateItem(Leg item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText(null);
							setGraphic(null);
						} else {
							//This method does not work download
							FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leg-row.fxml"));
							ContractRowController crc = new ContractRowController();
							fxmlLoader.setController(crc);
							// crc.amount.increment();
						}
					}
				};
				return listCell;
			}
			
		});
	}
	
	private void BZVZ_VER2(){
		// ++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++++++++++++++
		// ++++++++++ VERZIJA 2
		// ++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++++++++++++++
		lv_legs.setCellFactory(lv_legs -> {
			return new ListCell<Leg>() {
				private GridPane gridPane;
				private ContractRowController crc;
				
				{
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("leg-row.fxml"));
						gridPane = loader.load();
						crc = loader.getController();
					} catch (IOException exc) {
						throw new RuntimeException(exc);
					}
				}
				
				@Override
				protected void updateItem(Leg leg, boolean empty) {
					super.updateItem(leg, empty);
					if (empty) {
						setGraphic(null);
					} else {
						crc.strajk.getValueFactory().setValue(3d);
						// controller.setStatus(contact.getStatus());
						// controller.setSense(contact.getSense());
						// controller.setAvatarImage(contact.getImage());
						// setGraphic(graphic);
					}
				}
				
				
			};
		});
	}
}
