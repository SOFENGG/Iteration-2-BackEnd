package view;

import controller.CashierViewController;
import javafx.scene.layout.VBox;

public class TransactionBox extends VBox implements View {
	private CashierViewController cvc;
	
	public TransactionBox (CashierViewController cvc) {
		super ();
		this.cvc = cvc;
		
		initTB ();
		initHandlers ();
		
		//attach the db to this view (db.attach(this));
		
		getChildren().addAll();
	}

	private void initTB() {
		
	}

	private void initHandlers() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
