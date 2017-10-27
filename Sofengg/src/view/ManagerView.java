package view;

import controller.CashierViewController;
import controller.ManagerViewController;
import javafx.scene.layout.BorderPane;

public class ManagerView extends BorderPane implements View{

	private ManagerViewController mvc;
	
	public ManagerView (ManagerViewController mvc) {
		this.mvc = mvc;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
