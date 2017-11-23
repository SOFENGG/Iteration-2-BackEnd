package view;

import view.AlertBox;
import view.CheckoutCashout;
import view.CheckoutDebt;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CheckoutPrompt extends AlertBox {
	
	private VBox buttonBox;
	private double totalPrice;
	private int userID;
	
	public CheckoutPrompt(String title, double totalPrice, int userID) {
		super(title);
		this.totalPrice = totalPrice;
		this.userID = userID;
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
			CheckoutCashout cc = new CheckoutCashout("Checkout", totalPrice);
			cc.showBox();
			closeBox();
		});
		
		// debt button, if no user (id = 0), disabled
		if (userID != 0) {
			debtButton.setDisable(false);
			debtButton.setOnAction(e -> {
				CheckoutDebt cd = new CheckoutDebt("Debt", totalPrice, userID);
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
}