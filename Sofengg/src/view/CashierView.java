package view;

import controller.CashierViewController;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class CashierView extends BorderPane implements View{

	private CashierViewController cvc;
	
	private Label label;
	
	public CashierView (CashierViewController cvc) {
		this.cvc = cvc;
		
		initCV ();
		
		setCenter (label);
	}

	private void initCV() {
		label = new Label ("Test");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
