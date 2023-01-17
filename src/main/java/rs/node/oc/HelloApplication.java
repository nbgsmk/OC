package rs.node.oc;

import rs.node.oc.model.Contract;
import rs.node.oc.model.TIP;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


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

    }

    public static void main(String[] args) {
        launch();
	    Contract call = new Contract(TIP.CALL, 100);

        double a = call.getIntrinsic(112);
        a = call.getIntrinsic(99d);
        a = call.getIntrinsic(100d);
        System.out.println("patka zec");


		Contract put = new Contract(TIP.PUT, 100);
	    a = put.getIntrinsic(95.7);
	    a = put.getIntrinsic(103);
		a = put.getIntrinsic(100);
	
	    GraphView graphView = new GraphView();
	    graphView.setVisible(true);
	    graphView.setLayout(new GridLayout());
		
    }
}