package view;

import view.AlertBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EndOfDayPrompt extends AlertBox {
	
	private VBox promptBox;
	private int totalPrice;
	
	public EndOfDayPrompt(String title) {
		super(title);
		totalPrice = 0;
		initInputBoxes();
		initButtons();
		initGridConstraints();
		addToGrid();
	}
	
	public void runWindow() {
		showBox();
	}
	
	protected Label amt;
	protected Label lbl = new Label("CASH DENOMINATION");
	protected Button okButton = new Button("OK");
	protected Button cancelButton = new Button("CANCEL");
	
	public void initInputBoxes(){
		promptBox = new VBox(20);
		promptBox.setAlignment(Pos.CENTER);
		HBox input1000 = createDenomBox(1000);
		HBox input500 = createDenomBox(500);
		HBox input200 = createDenomBox(200);
		HBox input100 = createDenomBox(100);
		HBox input50 = createDenomBox(50);
		HBox input20 = createDenomBox(20);
		HBox input10 = createDenomBox(10);
		HBox input5 = createDenomBox(5);
		HBox input1 = createDenomBox(1);
		
		HBox total = new HBox(20);
		Label lblTotal = new Label("TOTAL AMOUNT: ");
		// amt should be total amount
		amt = new Label("PHP " + totalPrice);
		amt.setAlignment(Pos.BASELINE_RIGHT);
		amt.setPrefWidth(150);
		total.getChildren().addAll(lblTotal, amt);
		promptBox.getChildren().addAll(lbl, input1000, input500, input200, input100, input50, input20, input10, input5, input1, total);
	}
	
	public void initButtons() {
		HBox buttonBox = new HBox(20);
		buttonBox.setAlignment(Pos.CENTER);
		
		okButton.setOnAction(e -> {
			// backend stuff
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Done");
			a.setHeaderText("Success!");
			a.setContentText("Successfully reported PHP " + totalPrice + ".");
			a.showAndWait();
			closeBox();
		});
		
		cancelButton.setOnAction(e -> {
			closeBox();
		});
		
		buttonBox.getChildren().addAll(okButton, cancelButton);
		promptBox.getChildren().add(buttonBox);
	}
	
	private void initGridConstraints() {
		GridPane.setConstraints(promptBox, 0, 0);
	}
	
	private void addToGrid() {
		getGrid().getChildren().addAll(promptBox);
	}
	
	public HBox createDenomBox(int denom) {
		HBox box = new HBox(20);
		Label lblDenom = new Label("PHP " + denom + " x ");
		lblDenom.setPrefWidth(70);
		lblDenom.setAlignment(Pos.BASELINE_RIGHT);
		Spinner<Integer> spinner = new Spinner<Integer>();
		Label lblTotal = new Label("0");
		lblTotal.setAlignment(Pos.BASELINE_RIGHT);
		lblTotal.setPrefWidth(30);
		final int initialValue = 0;
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999, initialValue);
		spinner.setValueFactory(valueFactory);
		spinner.valueProperty().addListener(new ChangeListener<Integer>(){

			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				lblTotal.setText(Integer.toString(denom * arg2));
				totalPrice = totalPrice - (denom * arg1) + (denom * arg2);
				amt.setText("PHP " + totalPrice);
			}
			
		});
		
		box.getChildren().addAll(lblDenom, spinner, lblTotal);
		return box;
	}

}
