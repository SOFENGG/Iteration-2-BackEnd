package view;

import view.AlertBox;
import controller.CashierViewController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CheckoutCashout extends AlertBox {
	
	private VBox promptBox;
	private double totalPrice;
	private CashierViewController cvc;
	
	private String transactionType; 
	
	private CallbackListener callbackListener;
	
	public CheckoutCashout(String title, String transactionType, double totalPrice, CashierViewController cvc) {
		super(title);
		this.transactionType = transactionType;
		this.totalPrice = totalPrice;
		this.cvc = cvc;
		initInputAmt();
		initButtons();
		initGridConstraints();
		addToGrid();
	}
	
	public void runWindow() {
		showBox();
	}
	
	protected Label amtLbl = new Label("Enter amount given (in PHP):");
	protected TextField amtField = new TextField();
	protected Button okButton = new Button("OK");
	protected Button cancelButton = new Button("CANCEL");
	
	private void initInputAmt() {
		promptBox = new VBox(10);
		VBox amtBox = new VBox(15);
		amtField.setPromptText("000.00");
		
		amtBox.getChildren().addAll(amtLbl, amtField);
		promptBox.getChildren().add(amtBox);
	}
	
	private void initButtons() {
		okButton.setOnAction(e -> {
			double cashReceived = 0.0;
			boolean isValidNumber = isDouble(amtField.getText());
			
			if (isValidNumber)
				cashReceived = Double.parseDouble(amtField.getText());
			
			if (isValidNumber) {
				double change = cashReceived - totalPrice;
				if (change >= 0) {
					Alert a = new Alert(AlertType.INFORMATION);
					a.setTitle("Success");
					a.setContentText("Received PHP " + cashReceived + ". Change is PHP " + change + ".");
					a.showAndWait();
					closeBox();
					// TRANSACT
					cvc.buyItems(transactionType, false);
					
					// CLEAR THE CART
					callbackListener.checkout();
					
				} else {
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Error");
					a.setContentText("Insufficient amount entered. Please try again.");
					a.showAndWait();
				}
			} else {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setContentText("Wrong input. Try again.");
				a.showAndWait();				
			}
		});
		
		cancelButton.setOnAction(e -> {
			closeBox();
		});
		
		HBox buttonBox = new HBox(20);
		buttonBox.getChildren().addAll(okButton, cancelButton);
		promptBox.getChildren().add(buttonBox);
	}
	
	private void initGridConstraints() {
		GridPane.setConstraints(promptBox, 0, 0);
	}

	private void addToGrid() {
		getGrid().getChildren().addAll(promptBox);
	}
	
	private boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setCheckoutListener(CallbackListener callbackListener){
		this.callbackListener = callbackListener;
	}
	
}