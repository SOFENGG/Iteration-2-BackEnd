package controller;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.LoginView;

public class LoginController {
	private LoginView lv;
	private MainController mc;
	
	public LoginController (MainController mc) {
		this.mc = mc;
		lv = new LoginView(this);
	}
	
	public Pane getView (int view) {
		switch(view) {
			case Code.LOGIN_VIEW: return lv;
		}
		return null;
	}
	
	public Stage getMainStage () {
		return mc.getStage ();
	}
	
	public MainController getMainController() {
		return mc;
	}
	
	public void changeControl (int controllerCode, int view) {
		mc.setScene (controllerCode, view);
		
	}

}
