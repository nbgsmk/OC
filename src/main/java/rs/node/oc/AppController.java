package rs.node.oc;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import rs.node.oc.data.DemoData;
import rs.node.oc.data.Snimac;
import rs.node.oc.gui.ListCell_Combo;
import rs.node.oc.gui.ListCell_Leg;
import rs.node.oc.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AppController implements Initializable {

	@FXML
	public ToggleGroup comboTip;
	@FXML
	public RadioButton butterfly;
	@FXML
	public RadioButton unbal;
	@FXML
	public RadioButton vertical;
	@FXML
	public RadioButton condor;
	@FXML
	public RadioButton calendar;
	@FXML
	public Button randomData;
	@FXML
	public Button saveToHistory;
	
	
	private Combo combo;

	@FXML
	public LineChart<String, Double> grafikoncic;
	
	@FXML
	public ListView<Leg> lv_legs;
	ObservableList<Leg> obs_legs;
	
	@FXML
	public ListView<Combo> lv_combo;
	ObservableList<Combo> obs_combo;
	
	@FXML
	public Label lbl_comboInfo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		obs_combo = FXCollections.observableArrayList();
		lv_combo.setItems(obs_combo);
		lv_combo.setCellFactory(new Callback<ListView<Combo>, ListCell<Combo>>() {
			@Override
			public ListCell<Combo> call(ListView<Combo> param) {
				return new ListCell_Combo();
			}
		});
		lv_combo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				combo = obs_combo.get((Integer) newValue);
				updatujGui();
			}
		});
		
		
		obs_legs = FXCollections.observableArrayList();
		lv_legs.setItems(obs_legs);
		lv_legs.setCellFactory(new Callback<ListView<Leg>, ListCell<Leg>>() {
			@Override
			public ListCell<Leg> call(ListView<Leg> param) {
				return new ListCell_Leg();
			}
		});
		
		
		comboTip.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (newValue == vertical) {
					combo = new Combo("Vertical Spread");
					combo.add(new Leg(1, new Put(397), 0.68));
					combo.add(new Leg(-1, new Put(399), 1.12));
					
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
					combo.add(new Leg(1, new Put(409), 1.8));
					combo.add(new Leg(-1, new Put(410), 2.74));
					combo.add(new Leg(-1, new Call(415), 0.96));
					combo.add(new Leg(1, new Call(417), 0.5));

				}
				
				updatujGui();
				
				
				// // Legs - popuniti tabelu
				// dole.getChildren().clear();
				// for (Leg leg : combo.getLegs()) {
				// 	ContractRowController ctrl = dodajRow(null);
				//
				// 	Integer amt = leg.getAmount();
				// 	ctrl.amount.getValueFactory().setValue(amt);
				//
				// 	Contract contract = leg.getContract();
				// 	ctrl.call_put.setText(contract.getSkr());
				//
				// 	Double strajk = leg.getContract().getStrajk();
				// 	ctrl.strajk.getValueFactory().setValue(strajk);
				//
				// 	Double avgpx = leg.getOpenPrice();
				// 	ctrl.avg_px.getValueFactory().setValue(avgpx);
				//
				// 	Double delta = leg.getDelta();
				// 	ctrl.delta.getValueFactory().setValue(delta);
				//
				//
				// }
				//
				// snimi trenutni default
				snimiToXML("default.combo", combo);
				
			}
			
		});
	}
	

	
	
	
    protected void updatujGui() {
	
	    // popuni grafikon sa extended tackama (vise tacaka nego sto ima Leg-ova)
	    TreeMap<Double, Double> pl = combo.getExtendedPnLPoints();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
	    series.setName(combo.getComboName());
	    for (Map.Entry<Double, Double> tacka : pl.entrySet()) {
		    series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
	    }
	    grafikoncic.getData().clear();
		grafikoncic.getData().add(series);
		obs_legs.setAll(combo.getLegs());
	
	    StringBuilder sb = new StringBuilder();
	    sb.append("probability " + String.format("%.2f", combo.getDelta()) + "\n");
	    sb.append("max profit  " + String.format("%.2f", Math.abs(combo.getMaxProfit())) + "\n");
	    sb.append("max loss    " + String.format("%.2f", Math.abs(combo.getMaxLoss())) + "\n");
	    sb.append("min invest  " + String.format("%.2f", combo.getComboOpenPrice()) + "\n") ;
		lbl_comboInfo.setText(sb.toString());
    }
	
	@FXML
	protected void dodajRandomDataPaStaBude() {
		Map<Integer, Double> data = DemoData.getRandomData();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("Шта се деси, деси");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		grafikoncic.getData().add(series);
	}
	
	
	public void saveToHistory(ActionEvent actionEvent) {
		if ( obs_combo.contains(combo)) {
			obs_combo.remove(combo);
			obs_combo.add(combo);
			System.out.println("vec sadrzi, samo updatujem");
		} else if (combo != null) {
			obs_combo.add(combo);
		} else {
			System.out.println("null je, nikom nista");
		}
		if ((obs_combo.size() > 0) && (obs_combo != null)) {
			for (int i = 0; i < obs_combo.size(); i++) {
				snimiToXML(String.valueOf(i), obs_combo.get(i));
			}
			// for (Combo c : obs_combo) {
			// 	snimiToXML(c.getComboName(), c);
			// }
		}
	}
	
	
	private void snimiToXML(String fileName, Object o){
		Snimac snimac = new Snimac();
		snimac.writeXml(fileName, o);
	}
	
}