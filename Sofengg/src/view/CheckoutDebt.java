package view;

import view.AlertBox;
import controller.CashierViewController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CheckoutDebt extends AlertBox {
	
	private VBox promptBox;
	private double totalPrice;
	private int userID;
	
	private CashierViewController cvc;
	
	private String transactionType;
	private CallbackListener callbackListener;
	
	public CheckoutDebt(String title, String transactionType, double totalPrice, int userID, CashierViewController cvc) {
		super(title);
		this.transactionType = transactionType;
		this.totalPrice = totalPrice;
		this.userID = userID;
		this.cvc = cvc;
		initPasswordField();
		initButtons();
		initGridConstraints();
		addToGrid();
	}
	
	public void runWindow() {
		showBox();
	}
	
	protected Label header = new Label("Enter Manager password to continue:");
	protected PasswordField passField = new PasswordField();
	protected Button okButton = new Button("OK");
	protected Button cancelButton = new Button("CANCEL");
	
	private void initPasswordField() {
		promptBox = new VBox(20);
		VBox passBox = new VBox(15);
		passField.setPromptText("Enter Manager password here");
		
		passBox.getChildren().addAll(header, passField);
		promptBox.getChildren().add(passBox);
	};
	
	private void initButtons() {
		// GET managerPass AND Customer info FROM DB
		// for now, dummy values
		String managerPass = "hello";
		double userDebt = 1000.0;
		double debtLimit = 3000.0;
		String customerName = "Skraa";
		HBox buttonBox = new HBox(20);
		Alert a = new Alert(null);
		
		okButton.setOnAction(e -> {
			if (managerPass.equals(passField.getText())) {
				if (debtLimit >= userDebt + totalPrice) {
					a.setAlertType(AlertType.INFORMATION);
					a.setTitle("Debt Success");
					a.setContentText("PHP " + totalPrice + " successfuly charged to " + customerName + ".");
					a.showAndWait();
					closeBox();
					// TRANSACT
					cvc.buyItems(transactionType, true);
					
					// CLEAR CART
					callbackListener.checkout();
					
					// PROCESS USER DEBT
				} else {
					a.setAlertType(AlertType.ERROR);
					a.setTitle("Error");
					a.setContentText("Debt limit for " + customerName + "has been reached. Cannot proceed.");
					a.showAndWait();
					closeBox();
				}
			} else {
				a.setAlertType(AlertType.ERROR);
				a.setTitle("Error");
				a.setContentText("Invalid password. Please try again.");
				a.showAndWait();
			}
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
	
	public void setCheckoutListener(CallbackListener callbackListener){
		this.callbackListener = callbackListener;
	}
}
	