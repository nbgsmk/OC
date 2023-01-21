package rs.node.oc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import rs.node.oc.data.DemoCombo;
import rs.node.oc.data.DemoData;
import rs.node.oc.model.Combo;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AppMainController implements Initializable {
	
	@FXML
	public LineChart<String, Double> grafikoncicNum;
	public Button prikaziKombo;
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> series2 = new XYChart.Series<>();
		series2.setName("inišalajz рандом дата");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series2.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncicNum.getData().add(series2);
	}
	
	
    @FXML
    protected void onPrikaziKomboClick() {
	
	    DemoCombo dc = new DemoCombo();
	    Combo combo = dc.getDemoCombo();
	    TreeMap<Double, Double> pl = combo.getPnLPoints();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
	    series.setName("kombic");
	    for (Map.Entry<Double, Double> tacka : pl.entrySet()) {
		    series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
	    }
		grafikoncicNum.getData().clear();
		grafikoncicNum.getData().add(series);
		
    }
	
	@FXML
	protected void dajRandomData() {
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("dabl trabl");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncicNum.getData().add(series);
		
	}
}