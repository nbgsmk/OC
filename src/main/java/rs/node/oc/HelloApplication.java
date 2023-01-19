package rs.node.oc;

import rs.node.oc.gui.DemoData;
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
        stage.show();
	
		
    }

    public static void main(String[] args) {
        // launch();
	    Combo combo = new Combo();
	    TreeMap<Double, Double> karakteristicneTacke;
	    double a;

	    Pozicija pcall = new Pozicija(1, new Call(398), 1.5);
	    Pozicija pput = new Pozicija(-1, new Put(401), 0.5);
	    combo.add(pcall);
	    combo.add(pput);
	    combo.add(new Pozicija(-1, new Put(399), 4.3));
	    karakteristicneTacke = combo.getCharacteristicPoints();
		
	    combo = new Combo();
		// combo.add(new Pozicija(1, new Call(398), 1.5));
		// combo.add(new Pozicija(-2, new Call(401), 0.5));
		// combo.add(new Pozicija(1, new Call(402), 0.35));
		// combo.add(new Pozicija(1, new Call(400), 1.3));
		combo.add(new Pozicija(1, new Put(400), 1.3));
	    karakteristicneTacke = combo.getCharacteristicPoints();
		
		a = combo.getOpenPrice();
		a = combo.getExpirationPriceAt(390);
		a = combo.getExpirationPriceAt(400);
		a = combo.getExpirationPriceAt(410);
		
		a = combo.getPnLAt(390);
		a = combo.getPnLAt(398);
		a = combo.getPnLAt(400);
		a = combo.getPnLAt(401);
		a = combo.getPnLAt(402);
		a = combo.getPnLAt(410);
	
		System.out.println("do yaya");
		
		
	
	    SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			    kreni();
			    
		    }
	    });
    }
	
	
	
	
	private static void kreni(){
		
		Map<Integer, Double> data = DemoData.getDemoData();
		System.out.println(data);
		
		GP2 mainPanel = new GP2(data);
		mainPanel.setPreferredSize(new Dimension(800, 600));
		int w = mainPanel.getWidth();
		int h = mainPanel.getHeight();
		
		JFrame frame = new JFrame("OC 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		
		frame.setVisible(true);
		
		
		
		
	}
}