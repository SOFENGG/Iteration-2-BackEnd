package controller;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import util.Query;
import view.AlertBox;
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
	
	public void logIn(String user, String pass){
		ArrayList<User> users = Query.getInstance().userQuery("select * from "+User.TABLE+" where "+User.COLUMN_USERNAME+" = '"+user+"' and "+User.COLUMN_PASSWORD+" = '"+pass+"';");

		if(users.size() > 0){
			//log in success
			if(users.get(0).getAccessLevel() == 1){
				//cashier view
				mc.passUser(Code.CVC_CODE, users.get(0));
				changeControl (Code.CVC_CODE, Code.CASHER_VIEW);
			}else{
				//manager view
				mc.passUser(Code.MVC_CODE, users.get(0));
				changeControl (Code.MVC_CODE, Code.MANAGER_VIEW);
			}
			System.out.println("LOGGED IN");
		} else {
			AlertBox wrongInputError = new AlertBox("Authentication Error");
			wrongInputError.initAlertContents("Wrong Password or Username");
			wrongInputError.showBox();
		}
	}

}
