package controller;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.CashierView;
import view.ManagerView;

public class ManagerViewController {

	private MainController mc;
	private ManagerView mv;
	
	public ManagerViewController (MainController mc) {
		this.mc = mc;
		mv = new ManagerView (this);
	}
	
	public Pane getView (int view) {
		switch(view) {
			case Code.MANAGER_VIEW: return mv;
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
