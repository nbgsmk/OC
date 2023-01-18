package rs.node.oc;

import rs.node.oc.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
	
	    JFrame f = new JFrame();
	    f.setLayout(new BorderLayout());
	    final JPanel p = new JPanel();
	    p.add(new JLabel("A Panel"));
	    f.add(p, BorderLayout.CENTER);
	
	    //create a button which will hide the panel when clicked.
	    JButton b = new JButton("HIDE");
	    b.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
			    p.setVisible(false);
		    }
	    });
	
	    f.add(b,BorderLayout.SOUTH);
	    f.pack();
	    f.setVisible(true);
	
	    GraphView graphView = new GraphView();
	    graphView.setVisible(true);
	    graphView.setLayout(new GridLayout());
		
		f.add(graphView);
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
		combo.add(new Pozicija(1, new Call(402), 1.3));
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
    }
}