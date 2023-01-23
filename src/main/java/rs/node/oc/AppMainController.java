package rs.node.oc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import rs.node.oc.data.DemoCombo;
import rs.node.oc.data.DemoData;
import rs.node.oc.model.Call;
import rs.node.oc.model.Combo;
import rs.node.oc.model.Pozicija;
import rs.node.oc.model.Put;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AppMainController implements Initializable {

	@FXML
	public LineChart<String, Double> grafikoncic;
	@FXML
	public Button prikaziKombo;
	@FXML
	public VBox dole;
	
	@FXML
	public Button dodajRow;
	public ToggleGroup comboTip;
	public RadioButton butterfly;
	public RadioButton unbal;
	public RadioButton vertical;
	public RadioButton condor;
	public RadioButton calendar;
	public Button randomData;
	
	private Combo combo;
	private List<Combo> history;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> series2 = new XYChart.Series<>();
		series2.setName("inišalajz рандом дата");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series2.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncic.getData().add(series2);
		
		comboTip.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (newValue == butterfly) {
					combo = new Combo();
					combo.add(new Pozicija(1, new Put(397), 1.5));
					combo.add(new Pozicija(-2, new Put(398), 1));
					combo.add(new Pozicija(1, new Put(402), 1.5));
				
				} else if (newValue == unbal) {
					combo = new Combo();
					combo.add(new Pozicija(3, new Put(397), 1.5));
					combo.add(new Pozicija(-2, new Put(398), 1));
					combo.add(new Pozicija(1, new Put(402), 1.5));
				
				} else if (newValue == vertical) {
					combo = new Combo();
					combo.add(new Pozicija(1, new Put(397), 1.5));
					combo.add(new Pozicija(-1, new Put(398), 1));
				
				} else if (newValue == condor) {
					combo = new Combo();
					combo.add(new Pozicija(1, new Put(397), 1.5));
					combo.add(new Pozicija(-1, new Put(398), 1));
					combo.add(new Pozicija(-1, new Call(402), 1.5));
					combo.add(new Pozicija(1, new Call(403), 1));
					
				} else if (newValue == calendar) {
				
				}
				
				
			}
		});

	}
	
	
    @FXML
    protected void onPrikaziKomboClick() {
	
	    if (combo == null) {
		    DemoCombo dc = new DemoCombo();
		    combo = dc.getDemoCombo();
	    }
	    TreeMap<Double, Double> pl = combo.getPnLPoints();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
	    series.setName("kombic");
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
		series.setName("dabl trabl");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncic.getData().add(series);
		
	}


	public void dodajRow(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(AppMain.class.getResource("contract-row.fxml"));
		dole.getChildren().add(loader.load());
		
	}
}