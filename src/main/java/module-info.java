module cc.kostic.oc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens cc.kostic.oc to javafx.fxml;
    exports cc.kostic.oc;
}