package controller;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.CashierView;

public class CashierViewController {

	private MainController mc;
	private CashierView cv;
	
	public CashierViewController (MainController mc) {
		this.mc = mc;
		cv = new CashierView (this);
	}
	
	public Pane getView (int view) {
		switch(view) {
			case Code.CASHER_VIEW: return cv;
		}
		return null;
	}
	
	public Stage getMainStage () {
		return mc.getStage ();
	}
	
	public MainController getMainController() {
		return mc;
	}
	
	public void changeControl (int requestCode, int view) {
		mc.setScene(requestCode, view);
	}
	
}
