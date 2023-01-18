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
	    Combo combo;
	    double a;

	    Pozicija pc = new Pozicija(1, new Call(400), 5.09);
	    combo = new Combo();
	    combo.add(pc);
        a = pc.getPx();
	    a = pc.getPxAtExpiration(399);
	    a = pc.getPxAtExpiration(400);
	    a = pc.getPxAtExpiration(403);
	    a = combo.getPx();
		a = combo.getExpirationPxAt(399);
		a = combo.getExpirationPxAt(400);
		a = combo.getExpirationPxAt(402.7);

		
		Pozicija pp = new Pozicija(1, new Put(400), 2.45);
	    combo = new Combo();
	    combo.add(pp);
	    a = pp.getPx();
	    a = pp.getPxAtExpiration(399);
	    a = pp.getPxAtExpiration(400);
	    a = pp.getPxAtExpiration(403);
	    a = combo.getPx();
	    a = combo.getExpirationPxAt(399);
	    a = combo.getExpirationPxAt(400);
	    a = combo.getExpirationPxAt(402.7);
	
	    combo.add(pc);
		a = combo.getPx();
	    a = combo.getExpirationPxAt(399);
	    a = combo.getExpirationPxAt(400);
	    a = combo.getExpirationPxAt(402.7);

		
		a = combo.getExpirationPxAt(110);
	
		combo.add(new Pozicija(-1, new Put(399), 4.3));
	    TreeMap<Double, Double> karakteristicneTacke = combo.getCharacteristic();
		
    }
}