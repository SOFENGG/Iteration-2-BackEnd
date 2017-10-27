package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox extends Stage {
	
	private String title;
	private String message;
	
	private Scene scene;
		private VBox layout;
			private Label label;
			private Button closeButton;
	
	public AlertBox (String title, String message) {
		super();
		
		this.title = title;
		this.message = message;
		
		initStage();
		initAlert();
		
		setScene(scene);
		showAndWait();
	}

	private void initStage() {
		initModality(Modality.APPLICATION_MODAL); //Blocks access to other windows until this is closed
		setTitle(title);
		setMinWidth(250);
		setResizable(false);
	}
	
	private void initAlert() {
		label = new Label();
		label.setFont(Font.font("Arial", FontWeight.BLACK, 11));
		label.setText(message);
		
		closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> close());
		
		layout = new VBox(10);
			layout.getChildren().addAll(label, closeButton);
			layout.setAlignment(Pos.CENTER);
		
		scene = new Scene(layout);
		
	}
}
