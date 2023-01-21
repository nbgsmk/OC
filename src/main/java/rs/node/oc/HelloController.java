package rs.node.oc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeLejbel;

    @FXML
    protected void onHelloButtonClick() {
	    welcomeLejbel.setText("Welcome to JavaFX Application!");
    }
}