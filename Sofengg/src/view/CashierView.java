package view;

import controller.CashierViewController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CashierView extends GridPane implements View{

	private CashierViewController cvc;
	
	protected final ImageView logoView = new ImageView();
	protected final Image logo = new Image(("E&Elogo.png"));
	protected boolean isBlack = false;
	
	private CustomerBox CustBox;
	private NavigationBox NavBox;
	private CartPane CartPane;
	
	public CashierView (CashierViewController cvc) {
		super ();
		this.cvc = cvc;
		
		setPadding(new Insets(10,10,10,10));
		setVgap(12);
		setHgap(10);
		
		initCV ();
		initHandlers ();
		
		getChildren().addAll(CustBox, NavBox, CartPane);
		GridPane.setConstraints(logoView, 0, 0);
		GridPane.setConstraints(CustBox, 1, 0);
		GridPane.setConstraints(NavBox, 0, 1);
		GridPane.setConstraints(CartPane, 1, 1);
	}

	private void initCV() {
		initLogo ();
		CustBox = new CustomerBox(cvc);	
		NavBox = new NavigationBox(cvc);
		CartPane = new CartPane (cvc);
	}

	private void initLogo() {
		logoView.setImage(logo);
		logoView.setFitHeight(75);
		logoView.setPreserveRatio(true);
		
		getChildren().add(logoView);
	}
	
	private void initHandlers () {
		logoView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		         if(isBlack){
		        	 setStyle(null);
		        	 isBlack = false;
		         }
		         else {
		        	 setStyle("-fx-base: Black;");
		        	 isBlack = true;
		         }
		     }
		});
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
