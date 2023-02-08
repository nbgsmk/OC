package rs.node.oc.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.Map;
import java.util.TreeMap;


public class DataModel {
	private ObservableList<Combo> comboPresets = FXCollections.observableArrayList();
	private final ObjectProperty<Combo> currentCombo = new SimpleObjectProperty<>(null);
	
	public DataModel() {
		DataSource ds = new DataSource();
		comboPresets.setAll(ds.loadPresets());
		currentCombo.set(ds.loadLastUsed());
	}
	
	public ObservableList<Combo> getComboPresets() {
		return comboPresets;
	}
	
	public Combo getCurrentCombo() {
		return currentCombo.get();
	}
	
	public ObjectProperty<Combo> currentComboProperty() {
		return currentCombo;
	}
	
	public void setCurrentCombo(Combo currentCombo) {
		this.currentCombo.set(currentCombo);
	}
	
	public void setComboPresets(ObservableList<Combo> comboPresets) {
		this.comboPresets = comboPresets;
	}
	
	public void saveTmpToPresets(){
		if ( getComboPresets().contains(getCurrentCombo())) {
			comboPresets.remove(getCurrentCombo());
			comboPresets.add(getCurrentCombo());
			System.out.println("vec sadrzi, samo updatujem");
		} else if (getCurrentCombo() != null) {
			comboPresets.add(getCurrentCombo());
		} else {
			System.out.println("null je, nikom nista");
		}
	}
	

	public String getComboStats(){
		StringBuilder sb = new StringBuilder();
		sb.append("probability " + String.format("%.2f", getCurrentCombo().getDelta()) + "\n");
		sb.append("max profit  " + String.format("%.2f", Math.abs(getCurrentCombo().getMaxProfit())) + "\n");
		sb.append("max loss    " + String.format("%.2f", Math.abs(getCurrentCombo().getMaxLoss())) + "\n");
		sb.append("min invest  " + String.format("%.2f", getCurrentCombo().getComboOpenPrice()) + "\n") ;
		return sb.toString();
	}
	
	
	public XYChart.Series<String, Double> getExtendedPnLpoints(){
		// popuni grafikon sa extended tackama (vise tacaka nego sto ima Leg-ova)
		TreeMap<Double, Double> pl = getCurrentCombo().getExtendedPnLPoints();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName(getCurrentCombo().getComboName());
		for (Map.Entry<Double, Double> tacka : pl.entrySet()) {
			series.getData().add(new XYChart.Data<>(tacka.getKey().toString(), tacka.getValue()));
		}
		return series;
	}

}
