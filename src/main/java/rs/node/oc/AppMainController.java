package rs.node.oc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import rs.node.oc.data.DemoCombo;
import rs.node.oc.data.DemoData;
import rs.node.oc.model.*;

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
	public Label comboInfo;
	
	private Combo combo;
	private List<Combo> history;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Map<Integer, Double> data = DemoData.getDemoData();
		XYChart.Series<String, Double> bzvz = new XYChart.Series<>();
		bzvz.setName("inišalajz рандом дата");
		for (Map.Entry<Integer, Double> tacka : data.entrySet()) {
			bzvz.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		
		grafikoncic.getData().add(bzvz);
		
		comboTip.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (newValue == vertical) {
					combo = new Combo();
					combo.add(new Leg(1, new Put(397), 1));
					combo.add(new Leg(-1, new Put(398), 1.5));
					
				} else if (newValue == butterfly) {
					combo = new Combo();
					combo.add(new Leg(1, new Put(397), 1));
					combo.add(new Leg(-2, new Put(398), 1.5));
					combo.add(new Leg(1, new Put(402), 2));
				
				} else if (newValue == unbal) {
					combo = new Combo();
					combo.add(new Leg(3, new Put(397), 1));
					combo.add(new Leg(-2, new Put(398), 1.5));
					combo.add(new Leg(1, new Put(402), 2));
				
				
				} else if (newValue == condor) {
					combo = new Combo();
					combo.add(new Leg(1, new Put(397), 1));
					combo.add(new Leg(-1, new Put(398), 1.5));
					combo.add(new Leg(1, new Call(403), 1));
					combo.add(new Leg(-1, new Call(402), 1.5));
					
				} else if (newValue == calendar) {
					combo = new Combo();
					combo.add(new Leg(1, new Call(398), 1.5));
					combo.add(new Leg(-2, new Call(401), 0.5));
					combo.add(new Leg(1, new Call(402), 0.35));
				
				}
				
				dole.getChildren().clear();
				
				for (int i = 0; i < combo.getPnLPoints().size(); i++) {
					ContractRowController ctrl = dodajRow(null);
					
					Integer amt = combo.getLegs().get(i).getAmount();
					ctrl.amount.getValueFactory().setValue(amt);
					
					Contract contract = combo.getLegs().get(i).getContract();
					ctrl.call_put.setText(contract.getShortName());
					
					Double strajk = combo.getLegs().get(i).getContract().getStrajk();
					ctrl.strajk.getValueFactory().setValue(strajk);
					
					Double avgpx = combo.getLegs().get(i).getOpenPrice();
					ctrl.avg_px.getValueFactory().setValue(avgpx);
					
					Double delta = combo.getLegs().get(i).getDelta();
					ctrl.delta.getValueFactory().setValue(delta);
					
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append("open  " + combo.getComboOpenPrice() + "\n") ;
				sb.append("delta " + combo.getDelta() + "\n");
				sb.append("max prof " + combo.getMaxProfit() + "\n");
				sb.append("max loss " + combo.getMaxLoss() + "\n");
				comboInfo.setText(sb.toString());
				
				TreeMap<Double, Double> pl = combo.getPnLPoints();
				XYChart.Series<String, Double> series = new XYChart.Series<>();
				series.setName("legenda si");
				for (Map.Entry<Double, Double> tacka : pl.entrySet()) {
					series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
				}
				
				onPrikaziKomboClick();
				
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
	    series.setName("legenda si");
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


	public ContractRowController dodajRow(ActionEvent actionEvent){
		try {
			FXMLLoader loader = new FXMLLoader(AppMain.class.getResource("contract-row.fxml"));
			dole.getChildren().add(loader.load());
			return loader.getController();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}