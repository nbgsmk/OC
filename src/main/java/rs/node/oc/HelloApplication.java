package rs.node.oc;

import rs.node.oc.data.DemoCombo;
import rs.node.oc.data.DemoData;
import rs.node.oc.gui.GP2;
import rs.node.oc.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        // stage.show();

	    DemoCombo dc = new DemoCombo();
		Combo combo = dc.getDemoCombo();

		kreni(combo);
		
    }

    public static void main(String[] args) {
        launch(args);

    }
	
	
	
	
	private void kreni(Combo combo){
		
		Map<Integer, Double> data = DemoData.getDemoData();

		data = new TreeMap<>();
		for (var entry : combo.getPnLPoints().entrySet()) {
			data.put((int) Math.round(entry.getKey()), entry.getValue());
		}
		System.out.println(data);
		
		GP2 mainPanel = new GP2(data);
		mainPanel.setPreferredSize(new Dimension(800, 600));

		JFrame frame = new JFrame("OC 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		
	}
}