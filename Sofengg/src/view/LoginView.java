package view;

import controller.Code;
import controller.LoginController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.User;

public class LoginView extends GridPane implements View{
	private LoginController lc;
	
	private TextField usernameField;
	private PasswordField passwordField;
	private Label LabelUser;
	private Label LabelPass;
	private Button LogInButton;
	private User user;
	
	public LoginView (LoginController lc) {
		super();
		this.lc = lc;
		
		setPadding(new Insets(10,10,10,10));
		setVgap(10);
		setHgap(8);
		
		initLogin();
		initHandlers();
		
		GridPane.setConstraints(LabelUser, 0, 0);
		GridPane.setConstraints(usernameField, 1, 0);
		
		GridPane.setConstraints(LabelPass, 0, 1);
		GridPane.setConstraints(passwordField, 1, 1);
		GridPane.setConstraints(LogInButton, 2, 1);
	}

	private void initLogin() {
		LogInButton = new Button ("Log in");
			
		usernameField = new TextField();
		passwordField = new PasswordField();
		
		LabelUser = new Label();
		LabelUser.setText("Username");
		
		LabelPass = new Label();
		LabelPass = new Label("Password");
		
		getChildren().addAll(LabelUser, LabelPass, usernameField, passwordField, LogInButton);
	}
	
	private void initHandlers() {
		LogInButton.setOnAction(e ->  {
			user = lc.logIn(usernameField.getText(), passwordField.getText());
			if(user != null){
				lc.changeControl (Code.CVC_CODE, Code.CASHER_VIEW);
				System.out.println("LOGGED IN");
			} else 
				new AlertBox("yo", "Wrong Password/and or Username");
		});
		
	}

	@Override
	public void update() {
		
	}
	
	public Parent getView() {
		return this;
	}
}
