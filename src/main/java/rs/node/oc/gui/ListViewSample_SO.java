package rs.node.oc.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


/*
 *
 * https://stackoverflow.com/questions/27438629/listview-with-custom-content-in-javafx
 *
 */
public class ListViewSample_SO extends Application {
	private static class CustomThing {
		private String name;
		private int price;
		
		public String getName() {
			return name;
		}
		
		public int getPrice() {
			return price;
		}
		
		public CustomThing(String name, int price) {
			super();
			this.name = name;
			this.price = price;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		ObservableList<CustomThing> data = FXCollections.observableArrayList();
		data.addAll(new CustomThing("Cheese", 123), new CustomThing("Horse", 456), new CustomThing("Jam", 789));
		
		final ListView<CustomThing> listView = new ListView<CustomThing>(data);
		listView.setCellFactory(new Callback<ListView<CustomThing>, ListCell<CustomThing>>() {
			@Override
			public ListCell<CustomThing> call(ListView<CustomThing> listView) {
				return new CustomListCell();
			}
		});
		
		StackPane root = new StackPane();
		root.getChildren().add(listView);
		primaryStage.setTitle("ListViewSample_StackOverflow");
		primaryStage.setScene(new Scene(root, 200, 250));
		primaryStage.show();
	}
	
	private class CustomListCell extends ListCell<CustomThing> {
		private HBox content;
		private Text name;
		private Text price;
		
		public CustomListCell() {
			super();
			name = new Text();
			price = new Text();
			VBox vBox = new VBox(name, price);
			content = new HBox(new Label("[Graphic]"), vBox);
			content.setSpacing(10);
		}
		
		@Override
		protected void updateItem(CustomThing item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null && !empty) { // <== test for null item and empty parameter
				name.setText(item.getName());
				price.setText(String.format("%d $", item.getPrice()));
				setGraphic(content);
			} else {
				setGraphic(null);
			}
		}
	}
	
}