package rs.node.oc;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.util.Callback;
import rs.node.oc.data.DemoData;
import rs.node.oc.io.Snimac;
import rs.node.oc.gui.ListCell_Combo;
import rs.node.oc.gui.ListCell_Leg;
import rs.node.oc.model.*;

import java.net.URL;
import java.util.*;

public class AppController implements Initializable {

	@FXML
	public ToggleGroup comboTip;

	@FXML
	public RadioButton vert_call;
	@FXML
	public RadioButton vert_put;

	@FXML
	public RadioButton butterfly_call;
	@FXML
	public RadioButton butterfly_put;

	@FXML
	public RadioButton unbal_call;
	@FXML
	public RadioButton unbal_put;

	@FXML
	public RadioButton condor;
	@FXML
	public RadioButton calendar;

	@FXML
	public Button randomData;
	@FXML
	public Button savePreset;
	
	
	private Combo combo;

	@FXML
	public LineChart<String, Double> grafikoncic;
	
	@FXML
	public ListView<Combo> lv_comboPresets;
	ObservableList<Combo> obs_comboPresets;
	
	@FXML
	public ListView<ListCell_Leg> lv_legs;
	ObservableList<ListCell_Leg> obs_legs;
	
	@FXML
	public Label lbl_comboInfo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		obs_comboPresets = FXCollections.observableArrayList();
		lv_comboPresets.setItems(obs_comboPresets);
		lv_comboPresets.setCellFactory(new Callback<ListView<Combo>, ListCell<Combo>>() {
			@Override
			public ListCell<Combo> call(ListView<Combo> param) {
				return new ListCell_Combo();
			}
		});
		lv_comboPresets.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				combo = obs_comboPresets.get((Integer) newValue);
				updatujGui();
			}
		});
		// lv_comboPresets.setOnMouseClicked(new EventHandler<MouseEvent>() {
		// 	@Override
		// 	public void handle(MouseEvent event) {
		// 		if (combo != null) {
		// 			updatujGui();
		// 		}
		// 	}
		// });
		lv_comboPresets.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent event) {
				Combo sel = lv_comboPresets.getSelectionModel().getSelectedItem();
				lv_comboPresets.getItems().remove(sel);
				savePreset(null);
			}
		});

		
		obs_legs = FXCollections.observableArrayList();
		lv_legs.setItems(obs_legs);
		// lv_legs.setCellFactory(new Callback<ListView<Leg>, ListCell<Leg>>() {
		// 	@Override
		// 	public ListCell<Leg> call(ListView<Leg> param) {
		// 		return new ListCell_Leg();
		// 	}
		// });
		// lv_legs.setOnScroll(new EventHandler<ScrollEvent>() {
		// 	@Override
		// 	public void handle(ScrollEvent event) {
		// 		// try {
		// 		// 	Thread.sleep(20);
		// 		// } catch (InterruptedException e) {
		// 		// 	throw new RuntimeException(e);
		// 		// }
		// 		updatujGui();
		// 	}
		// });
		
		readPreset();
		
		comboTip.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (newValue == vert_call) {
					combo = new Combo("Vertical Call Spread");
					combo.add(new Leg(1, new Call(397), 0.68));
					combo.add(new Leg(-1, new Call(399), 1.12));
				} else if (newValue == vert_put) {
					combo = new Combo("Vertical Put Spread");
					combo.add(new Leg(1, new Put(397), 0.68));
					combo.add(new Leg(-1, new Put(399), 1.12));
					
				} else if (newValue == butterfly_call) {
					combo = new Combo("Butterfly Call");
					combo.add(new Leg(1, new Call(397), 1));
					combo.add(new Leg(-2, new Call(398), 1.5));
					combo.add(new Leg(1, new Call(402), 2));
				} else if (newValue == butterfly_put) {
					combo = new Combo("Butterfly Put");
					combo.add(new Leg(1, new Put(397), 1));
					combo.add(new Leg(-2, new Put(398), 1.5));
					combo.add(new Leg(1, new Put(402), 2));
					
				} else if (newValue == unbal_call) {
					combo = new Combo("Unbal. Call Butterfly");
					combo.add(new Leg(2, new Call(398), 0.89));
					combo.add(new Leg(-3, new Call(401), 1.81));
					combo.add(new Leg(1, new Call(403), 2.81));
				} else if (newValue == unbal_put) {
					combo = new Combo("Unbal. Put Butterfly");
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
				snimiToXML(Snimac.defCombo, combo);
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
		
		// obs_legs.setAll(combo.getLegs());
	    obs_legs.clear();
	    for (Leg l : combo.getLegs()) {
			obs_legs.add(new ListCell_Leg(l));
	    }
		
	
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
	
	
	public void savePreset(ActionEvent actionEvent) {
		if ( obs_comboPresets.contains(combo)) {
			obs_comboPresets.remove(combo);
			obs_comboPresets.add(combo);
			System.out.println("vec sadrzi, samo updatujem");
		} else if (combo != null) {
			obs_comboPresets.add(combo);
		} else {
			System.out.println("null je, nikom nista");
		}
		if ((obs_comboPresets.size() > 0) && (obs_comboPresets != null)) {
			List<Combo> presetList = new ArrayList<>(obs_comboPresets);
			snimiToXML(Snimac.presetList, presetList);
		}
	}
	
	public void readPreset(){
		Snimac sn = new Snimac();
		combo = (Combo) sn.readXml(Snimac.defCombo);
		Object tmp = sn.readXml(Snimac.presetList);
		if (tmp instanceof List<?>) {
			List<Combo> lc = (List<Combo>) tmp;
			obs_comboPresets.addAll(lc);
		}
	}
	
	private void snimiToXML(String fileName, Object o){
		Snimac snimac = new Snimac();
		snimac.writeXml(fileName, o);
	}
	
	
}