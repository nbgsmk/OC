package rs.node.oc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import rs.node.oc.data.DemoCombo;
import rs.node.oc.data.DemoData;
import rs.node.oc.model.Combo;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AppMainController implements Initializable {

	@FXML
	public LineChart<String, Double> grafikoncic;
	@FXML
	public Button prikaziKombo;
	@FXML
	public VBox levo;
	@FXML
	public VBox dole;
	@FXML
	public Button dodajRow;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> series2 = new XYChart.Series<>();
		series2.setName("inišalajz рандом дата");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series2.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncic.getData().add(series2);

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


	public void dodajRow(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(AppMain.class.getResource("contract-row.fxml"));
		try {
			GridPane a = loader.load();
			dole.getChildren().add(a);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}