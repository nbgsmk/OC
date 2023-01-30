package rs.node.oc;

import rs.node.oc.data.DemoCombo;
import rs.node.oc.data.DemoData;
import rs.node.oc.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class App extends Application {
	private String appTitle = "OC";
	
	
	@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(appTitle);
        stage.setScene(scene);
        stage.show();
		
	    DemoCombo dc = new DemoCombo();
		Combo combo = dc.getDemoCombo();

		kreni(combo);
		

    }

    public static void main(String[] args) {
        // Application.launch(App.class, args);
		launch(args);

    }
	
	
	
	
	private void kreni(Combo combo){
		
		// random data on start
		Map<Integer, Double> randomData = DemoData.getDemoData();
		randomData = new TreeMap<>();
		for (var entry : combo.getExtendedPnLPoints().entrySet()) {
			randomData.put((int) Math.round(entry.getKey()), entry.getValue());
		}
		
		
	}
}
