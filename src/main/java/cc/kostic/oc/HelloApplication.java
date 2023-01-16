package cc.kostic.oc;

import cc.kostic.oc.model.Contract;
import cc.kostic.oc.model.TIP;
import cc.kostic.oc.model.Underl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        Contract c = new Contract(TIP.CALL, 25);
        Underl u = new Underl(27);

        double a = c.getIntrinsic(27.1f);
        a = c.getIntrinsic(17.4f);
        System.out.println("patka zec");


    }
}