package rs.node.oc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.GridView;
import rs.node.oc.data.DemoCombo;
import rs.node.oc.data.DemoData;
import rs.node.oc.data.Snimac;
import rs.node.oc.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AppMainController implements Initializable {

	@FXML
	public LineChart<String, Double> grafikoncic;
	@FXML
	public Button prikaziKombo;
	// @FXML
	// public VBox dole;
	
	@FXML
	public Button dodajRow;
	public ToggleGroup comboTip;
	public RadioButton butterfly;
	public RadioButton unbal;
	public RadioButton vertical;
	public RadioButton condor;
	public RadioButton calendar;
	public Button randomData;
	public Label comboInfo;
	public VBox dole;
	
	
	private Combo combo;
	
	@FXML
	public ListView<Leg> lv_legs;
	ObservableList<Leg> obs_legs;
	
	@FXML
	public ListView<Combo> lv_comboHist;
	ObservableList<Combo> obs_comboHist;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		obs_legs = FXCollections.observableArrayList();
		lv_legs.setItems(obs_legs);
		
		obs_comboHist = FXCollections.observableArrayList();
		lv_comboHist.setItems(obs_comboHist);
		
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> bzvz = new XYChart.Series<>();
		bzvz.setName("Рандом дата он апликејшон старт!");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			bzvz.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		
		
		
		/*

		https://quotes.freerealtime.com/quotes/NDX/Options


		https://code.makery.ch/library/javafx-tutorial/part1/
		
		https://docs.oracle.com/javafx/2/fxml_get_started/fxml_tutorial_intermediate.htm
		
		
		https://stackoverflow.com/questions/36985517/how-to-download-fxml-with-custom-cell-in-a-listview
		https://stackoverflow.com/questions/34838341/javafx-custom-cell-factory-with-custom-objects
		https://stackoverflow.com/questions/62697712/javafx-listview-custom-cells
		https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
		https://stackoverflow.com/questions/47434239/tableview-observablelist-change-row-style
		
		 */
		// ++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++++++++++++++
		// ++++++++++ VERZIJA 1
		// ++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++++++++++++++
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
							FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contract-row.fxml"));
							ContractRowController crc = new ContractRowController();
							fxmlLoader.setController(crc);
							// crc.amount.increment();
						}
					}
				};
				return listCell;
			}
			
		});
		
		
		
		
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
						FXMLLoader loader = new FXMLLoader(getClass().getResource("contract-row.fxml"));
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
		
		
		lv_comboHist.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("stari " + oldValue + " novi " + newValue);

			
			}
		});
		
		
		grafikoncic.getData().add(bzvz);
		
		comboTip.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (newValue == vertical) {
					combo = new Combo("Vertical Spread");
					combo.add(new Leg(1, new Put(397), 0.68));
					combo.add(new Leg(-1, new Put(399), 1.12));
					// combo.add(new Leg(-1, new Put(397), 0.66));
					// combo.add(new Leg(1, new Put(399), 1.15));
					
				} else if (newValue == butterfly) {
					combo = new Combo("Butterfly");
					combo.add(new Leg(1, new Put(397), 1));
					combo.add(new Leg(-2, new Put(398), 1.5));
					combo.add(new Leg(1, new Put(402), 2));
				
				} else if (newValue == unbal) {
					combo = new Combo("Unbalanced Butterfly");
					combo.add(new Leg(2, new Put(398), 0.89));
					combo.add(new Leg(-3, new Put(401), 1.81));
					combo.add(new Leg(1, new Put(403), 2.81));
				
				
				} else if (newValue == condor) {
					combo = new Combo("Iron Condor");
					combo.add(new Leg(1, new Put(401), 1.8));
					combo.add(new Leg(-1, new Put(403), 2.74));
					combo.add(new Leg(-1, new Call(405), 0.96));
					combo.add(new Leg(1, new Call(407), 0.5));
					
				} else if (newValue == calendar) {
					combo = new Combo("Iron Condor");
					combo.add(new Leg(1, new Put(401), 1.8));
					combo.add(new Leg(-1, new Put(403), 2.74));
					combo.add(new Leg(-1, new Call(405), 0.96));
					combo.add(new Leg(1, new Call(407), 0.5));

				}
				
				lv_legs.getItems().clear();
				dole.getChildren().clear();
				
				
				// Legs - popuniti tabelu
				for (Leg leg : combo.getLegs()) {
					ContractRowController ctrl = dodajRow(null);
					
					// Integer amt = leg.getAmount();
					// ctrl.amount.getValueFactory().setValue(amt);
					
					Contract contract = leg.getContract();
					ctrl.call_put.setText(contract.getShortName());
					
					Double strajk = leg.getContract().getStrajk();
					ctrl.strajk.getValueFactory().setValue(strajk);
					
					Double avgpx = leg.getOpenPrice();
					ctrl.avg_px.getValueFactory().setValue(avgpx);
					
					Double delta = leg.getDelta();
					ctrl.delta.getValueFactory().setValue(delta);
					
					obs_legs.add(leg);
					
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append("probability " + String.format("%.2f", combo.getDelta()) + "\n");
				sb.append("max profit  " + String.format("%.2f", Math.abs(combo.getMaxProfit())) + "\n");
				sb.append("max loss    " + String.format("%.2f", Math.abs(combo.getMaxLoss())) + "\n");
				sb.append("min invest  " + String.format("%.2f", combo.getComboOpenPrice()) + "\n") ;
				comboInfo.setText(sb.toString());
				
				// popuni grafikon sa extended tackama (vise tacaka nego sto ima Leg-ova)
				TreeMap<Double, Double> pl = combo.getExtendedPnLPoints();
				XYChart.Series<String, Double> series = new XYChart.Series<>();
				series.setName("inišalajz рандом дата");
				for (Map.Entry<Double, Double> tacka : pl.entrySet()) {
					series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
				}
				
				onPrikaziKomboClick();
				// pre nego sto krenes dalje, snimi dosadasnji default
				Snimac snimac = new Snimac();
				snimac.writeXml("default.combo.xml", combo);
				
				
			}
			
		});
	}
	

	
	
	
    @FXML
    protected void onPrikaziKomboClick() {
	
	    if (combo == null) {
		    DemoCombo dc = new DemoCombo();
		    combo = dc.getDemoCombo();
	    }
	    TreeMap<Double, Double> pl = combo.getExtendedPnLPoints();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
	    series.setName(combo.getComboName());
	    for (Map.Entry<Double, Double> tacka : pl.entrySet()) {
		    series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
	    }
		grafikoncic.getData().clear();
	    grafikoncic.getData().add(series);
    }
	
	@FXML
	protected void dajRandomData() {
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("Шта се деси, деси");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncic.getData().add(series);
		
	}


	public ContractRowController dodajRow(ActionEvent actionEvent){
		try {
			FXMLLoader loader = new FXMLLoader(AppMain.class.getResource("contract-row.fxml"));
			dole.getChildren().add(loader.load());
			// lv_legs.getItems().add(loader.load());
			return loader.getController();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void saveToHistory(ActionEvent actionEvent) {
		if ( obs_comboHist.contains(combo)) {
			obs_comboHist.remove(combo);
			obs_comboHist.add(combo);
			System.out.println("vec sadrzi");
		} else if (! (combo== null)) {
			obs_comboHist.add(combo);
		} else {
			System.out.println("null je bre!");
		}
	}
	
}