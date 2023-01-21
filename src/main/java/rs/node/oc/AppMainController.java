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
	
	public LineChart grafikoncicNum;
	@FXML
	private Label welcomeLejbel;
	
	@FXML
	public LineChart<String, Double> grafikoncicKat;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("serija podataka");
		series.getData().add(new XYChart.Data("patka", 33));
		series.getData().add(new XYChart.Data("zec", 45));
		series.getData().add(new XYChart.Data("mile", 12));
		series.getData().add(new XYChart.Data("kile", 22));
		series.getData().add(new XYChart.Data("zile", 71));
		
		grafikoncicKat.getData().add(series);
	}
	
	
    @FXML
    protected void onHelloButtonClick() {
	    welcomeLejbel.setText("Welcome to JavaFX Application!");
	
		
    }
	
	
	public void initialize(MouseEvent mouseEvent) {
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("dabl trabl");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			series.getData().add(new XYChart.Data(tacka.getKey(), tacka.getValue()));
		}
		
		grafikoncicNum.getData().add(series);
	}
}