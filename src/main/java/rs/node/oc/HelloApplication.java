package rs.node.oc;

import rs.node.oc.model.Call;
import rs.node.oc.model.Combo;
import rs.node.oc.model.Contract;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rs.node.oc.model.Put;

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
	
	    GraphView graphView = new GraphView();
	    graphView.setVisible(true);
	    graphView.setLayout(new GridLayout());
		
		f.add(graphView);
    }

    public static void main(String[] args) {
        // launch();
	    Call call = new Call(100);

        double a = call.getIntrinsic(112);
        a = call.getIntrinsic(99d);
        a = call.getIntrinsic(100d);
        System.out.println("patka zec");

		Put put = new Put(100d);
	    a = put.getIntrinsic(95.7);
	    a = put.getIntrinsic(103);
		a = put.getIntrinsic(100);
	
	    Combo combo = new Combo();
		combo.addContract(new Put(91));
		combo.addContract(new Call(99));
		combo.addContract(new Put(102));

		
    }
}