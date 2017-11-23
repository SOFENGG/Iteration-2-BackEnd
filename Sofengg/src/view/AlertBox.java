package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	private Stage window = new Stage();
	private Scene scene;
	protected GridPane grid = new GridPane();
	
	public AlertBox(String title){
		window.initModality(Modality.APPLICATION_MODAL); //Blocks access to other windows until this is closed
		window.setTitle(title);
		window.setResizable(false);
		initGrid();
	}
	
	private Label label = new Label();
	private Button closeButton = new Button("Close the window");
	
	public void initAlertContents(String message){
		label.setFont(Font.font("Arial", FontWeight.BLACK, 15));
		label.setText(message);
		
		closeButton.setOnAction(e -> window.close());
		closeButton.setMaxWidth(Double.MAX_VALUE);
		closeButton.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%; "
				+ "-fx-font-weight: bold");
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		grid.add(layout, 0, 0);
	}
	
	public void closeBox(){
		window.close();
	}
	
	public void showBox(){
		scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public void initGrid(){
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(12);
		grid.setHgap(10);
	}
	
	public GridPane getGrid(){
		return grid;
	}
	
	public Scene getScene(){
		return scene;
	}
	
	public Stage getStage(){
		return window;
	}
	
	public void resetStage(){
		window.hide();
		window.show();
	}
}
