module cc.kostic.oc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens rs.node.oc to javafx.fxml;
    exports rs.node.oc;
}