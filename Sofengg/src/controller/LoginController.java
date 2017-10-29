package controller;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import util.Query;
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
	
	//login view services
	
	//assume logging in will only return one user 
	public User logIn(String user, String pass){
		ArrayList<User> users = Query.getInstance().userQuery("select * from users where username = '"+user+"' and password = '"+pass+"';");
		
		if(users.size() > 0)
			//log in success
			return users.get(0);
		else
			return null;
	}

}
