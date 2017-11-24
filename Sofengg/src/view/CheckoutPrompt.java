package view;

import view.AlertBox;
import view.CheckoutCashout;
import view.CheckoutDebt;
import controller.CashierViewController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CheckoutPrompt extends AlertBox {
	
	private VBox buttonBox;
	private double totalPrice;
	private int userID;
	
	private CashierViewController cvc;
	
	private String transactionType;
	
	private CallbackListener callbackListener;
	
	public CheckoutPrompt(String title, String transactionType, double totalPrice, int userID, CashierViewController cvc) {
		super(title);
		this.totalPrice = totalPrice;
		this.transactionType = transactionType;
		this.userID = userID;
		this.cvc = cvc;
		initButtons();
		initGridConstraints();
		addToGrid();
	}
	
	public void runWindow() {
		showBox();
	}
	
	protected Label header = new Label("Select payment method:");
	protected Button cashOutButton = new Button("CASH OUT");
	protected Button debtButton = new Button("DEBT");
	protected Button cancelButton = new Button("CANCEL");
	
	private void initButtons() {
		buttonBox = new VBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		debtButton.setDisable(true);
		
		// button listeners
		// cash out button, always selectable
		cashOutButton.setOnAction(e -> {
			CheckoutCashout cc = new CheckoutCashout("Checkout", transactionType, totalPrice, cvc);
			cc.setCheckoutListener(callbackListener);
			cc.showBox();
			closeBox();
		});
		
		// debt button, if no user (id = -1), disabled
		if (userID != -1) {
			debtButton.setDisable(false);
			debtButton.setOnAction(e -> {
				CheckoutDebt cd = new CheckoutDebt("Debt", transactionType, totalPrice, userID, cvc);
				cd.setCheckoutListener(callbackListener);
				cd.showBox();
				closeBox();
			});
		}
		
		cancelButton.setOnAction(e -> {
			closeBox();
		});
		
		buttonBox.getChildren().addAll(header, cashOutButton, debtButton, cancelButton);
	}
	
	
	private void initGridConstraints() {
		GridPane.setConstraints(buttonBox, 0, 0);
	}
	
	private void addToGrid() {
		getGrid().getChildren().addAll(buttonBox);
	}
	
	public void setCheckoutListener(CallbackListener callbackListener){
		this.callbackListener = callbackListener;
	}
}