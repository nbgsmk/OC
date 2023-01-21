package rs.node.oc.gui;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class LayoutDemo_GridPane extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		GridPane root = new GridPane();
		FlowPane leftbanner = new FlowPane();
		leftbanner.setPrefWidth(200);
		String bgStyle = "-fx-background-color: lightblue;"
				+ "-fx-background-radius: 0%;"
				+ "-fx-background-inset: 5px;";
		leftbanner.setStyle(bgStyle);
		
		root.add(leftbanner, 0, 0, 1, 1);
		root.add(createGridPane(), 1, 0, 1, 1);
		Scene scene = new Scene(root, 550, 300);
		stage.setTitle("Layout Demo");
		stage.setScene(scene);
		stage.show();
	}
	
	public GridPane createGridPane() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setHgap(10);
		grid.setVgap(10);
		
		Text txt = new Text("Name and Location");
		txt.setFont(Font.font("Dialog", FontWeight.BOLD, 12));
		
		grid.add(txt, 0, 0, 1, 1);
		grid.add(new Separator(), 0, 1, 3, 1);
		
		grid.add(new Label("Project Name:"), 0, 2, 1, 1);
		grid.add(new TextField(), 1, 2, 1, 1);
		
		grid.add(new Label("Project Location:"), 0, 3, 1, 1);
		grid.add(new TextField(), 1, 3, 1, 1);
		grid.add(new Button("Browse..."), 2, 3, 1, 1);
		
		grid.add(new Label("Project Folder:"), 0, 4, 1, 1);
		grid.add(new TextField(), 1, 4, 1, 1);
		
		grid.add(new Separator(), 0, 5, 3, 1);
		
		grid.add(new Label("JavaFX Platform:"), 0, 6, 1, 1);
		ComboBox<String> cb = new ComboBox<>();
		cb.setPrefWidth(400);
		grid.add(cb, 1, 6, 1, 1);
		grid.add(new Button("Manage Platforms..."), 2, 6, 1, 1);
		
		grid.add(new CheckBox("Create Custom Preloader"), 0, 7, 3, 1);
		
		grid.add(new Label("tProject Name:"), 0, 8, 1, 1);
		grid.add(new TextField(), 1, 8, 1, 1);
		
		grid.add(new Separator(), 0, 9, 3, 1);
		
		grid.add(new CheckBox("Use Dedicated Foldr for storing "
				+ "Libraries"), 0, 10, 3, 1);
		
		grid.add(new Label("tLibraries Folder:"), 0, 11, 1, 1);
		grid.add(new TextField(), 1, 11, 1, 1);
		grid.add(new Button("Browse..."), 2, 11, 1, 1);
		
		grid.add(new Label("Different users and projects can "
				+ "share the same compilation librariesn"
				+ "(see Help for details)"),1, 12, 1, 1);
		
		grid.add(new CheckBox("Create Application Class"), 0, 13, 1, 1);
		grid.add(new TextField(), 1, 13, 1, 1);
		
		grid.add(new CheckBox("Set as Main Project"), 0, 14, 1, 1);
		
		grid.add(new Separator(), 0, 15, 3, 1);
		
		FlowPane fp = new FlowPane(Orientation.HORIZONTAL, 10, 10);
		fp.setAlignment(Pos.CENTER_RIGHT);
		fp.getChildren().addAll(
				new Button("< Back"),
				new Button("Next >"),
				new Button("Finish"),
				new Button("Cancel"),
				new Button("Help"));
		grid.add(fp, 0, 16, 3, 1);
		
		return grid;
	}
}