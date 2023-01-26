module cc.kostic.oc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
	requires java.desktop;
	
	opens rs.node.oc to javafx.fxml;
    exports rs.node.oc;
	
	exports rs.node.oc.gui;
	opens rs.node.oc.gui to javafx.fxml;
	
	exports rs.node.oc.data;
	opens rs.node.oc.data to javafx.fxml;
	
	exports rs.node.oc.model;
}