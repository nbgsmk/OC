package rs.node.oc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import rs.node.oc.data.DemoData;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AppMainController implements Initializable {
	
	@FXML
	public LineChart<String, Double> grafikoncicNum;
	
	@FXML
	private Label welcomeLejbel;
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> series2 = new XYChart.Series<>();
		series2.setName("dabl trabl");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series2.getData().add(new XYChart.Data(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncicNum.getData().add(series2);
	}
	
	
    @FXML
    protected void onHelloButtonClick() {
	    welcomeLejbel.setText("Welcome to JavaFX Application!");
	
		
    }
	
	@FXML
	protected void dajRandomData() {
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("dabl trabl");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series.getData().add(new XYChart.Data(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncicNum.getData().add(series);
		
	}
}