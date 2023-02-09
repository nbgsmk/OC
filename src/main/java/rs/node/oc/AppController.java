package rs.node.oc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.util.Callback;
import rs.node.oc.data.DataSource;
import rs.node.oc.data.DemoData;
import rs.node.oc.gui.DesniKlik;
import rs.node.oc.gui.ListCell_Combo;
import rs.node.oc.model.*;

import java.net.URL;
import java.util.*;

public class AppController implements Initializable {

	@FXML
	public ToggleGroup rg_comboTip;

	@FXML
	public RadioButton rb_vert_call;
	@FXML
	public RadioButton rb_vert_put;

	@FXML
	public RadioButton rb_butterfly_call;
	@FXML
	public RadioButton rb_butterfly_put;

	@FXML
	public RadioButton rb_unbal_call;
	@FXML
	public RadioButton rb_unbal_put;

	@FXML
	public RadioButton rb_ondor;
	@FXML
	public RadioButton rb_calendar;

	@FXML
	public Button b_randomData;
	@FXML
	public Button b_saveTmpToPreset;
	
	
	private Combo comboTmp;

	@FXML
	public LineChart<String, Double> grafikoncic;
	
	@FXML
	public ListView<Combo> lv_comboPresets;
	// ObservableList<Combo> obs_comboPresets;
	
	@FXML
	public ListView<Leg> lv_legs;
	// public ListView<ListCell_Leg> lv_legs;
	// ObservableList<ListCell_Leg> obs_legs;
	
	@FXML
	public Label lbl_comboInfo;
	
	private DataModel model;
	private final DataSource dataSource = new DataSource();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		model = new DataModel();
		
		lv_comboPresets.setItems(model.getComboPresets());
		// lv_comboPresets.setSelectionModel();
		lv_comboPresets.setCellFactory(new Callback<ListView<Combo>, ListCell<Combo>>() {
			@Override
			public ListCell<Combo> call(ListView<Combo> param) {
				return new ListCell_Combo();
			}
		});
		lv_comboPresets.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("lv_ " + newValue);
				model.setCurrentCombo( model.getComboPresets().get((Integer) newValue) );
			}
		});
		lv_comboPresets.setContextMenu(new DesniKlik(model));

		
		// obs_legs = FXCollections.observableArrayList();
		// lv_legs.setItems(model.getCurrentCombo().getLegs());
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
		
		
		rg_comboTip.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (newValue == rb_vert_call) {
					comboTmp = new Combo("Vertical Call Spread");
					comboTmp.add(new Leg(1, new Call(397), 0.68));
					comboTmp.add(new Leg(-1, new Call(399), 1.12));
				} else if (newValue == rb_vert_put) {
					comboTmp = new Combo("Vertical Put Spread");
					comboTmp.add(new Leg(1, new Put(397), 0.68));
					comboTmp.add(new Leg(-1, new Put(399), 1.12));
					
				} else if (newValue == rb_butterfly_call) {
					comboTmp = new Combo("Butterfly Call");
					comboTmp.add(new Leg(1, new Call(397), 1));
					comboTmp.add(new Leg(-2, new Call(398), 1.5));
					comboTmp.add(new Leg(1, new Call(402), 2));
				} else if (newValue == rb_butterfly_put) {
					comboTmp = new Combo("Butterfly Put");
					comboTmp.add(new Leg(1, new Put(397), 1));
					comboTmp.add(new Leg(-2, new Put(398), 1.5));
					comboTmp.add(new Leg(1, new Put(402), 2));
					
				} else if (newValue == rb_unbal_call) {
					comboTmp = new Combo("Unbal. Call Butterfly");
					comboTmp.add(new Leg(2, new Call(398), 0.89));
					comboTmp.add(new Leg(-3, new Call(401), 1.81));
					comboTmp.add(new Leg(1, new Call(403), 2.81));
				} else if (newValue == rb_unbal_put) {
					comboTmp = new Combo("Unbal. Put Butterfly");
					comboTmp.add(new Leg(2, new Put(398), 0.89));
					comboTmp.add(new Leg(-3, new Put(401), 1.81));
					comboTmp.add(new Leg(1, new Put(403), 2.81));
					
				} else if (newValue == rb_ondor) {
					comboTmp = new Combo("Iron Condor");
					comboTmp.add(new Leg(1, new Put(401), 1.8));
					comboTmp.add(new Leg(-1, new Put(403), 2.74));
					comboTmp.add(new Leg(-1, new Call(405), 0.96));
					comboTmp.add(new Leg(1, new Call(407), 0.5));
					
				} else if (newValue == rb_calendar) {
					comboTmp = new Combo("Iron Condor");
					comboTmp.add(new Leg(1, new Put(409), 1.8));
					comboTmp.add(new Leg(-1, new Put(410), 2.74));
					comboTmp.add(new Leg(-1, new Call(415), 0.96));
					comboTmp.add(new Leg(1, new Call(417), 0.5));

				}
				model.setCurrentCombo(comboTmp);
				dataSource.saveCurrent(model.getCurrentCombo());
				
			}
			
		});
		

		model.currentComboProperty().addListener(new ChangeListener<Combo>() {
			@Override
			public void changed(ObservableValue<? extends Combo> observable, Combo oldValue, Combo newValue) {
				grafikoncic.getData().clear();
				grafikoncic.getData().add(model.getExtendedPnLpoints());
				
				lbl_comboInfo.setText(model.getComboStats());
			}
		});
		
		
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
	
	
	public void saveTmpToPreset(ActionEvent actionEvent) {
		model.saveTmpToPresets();
	}
}