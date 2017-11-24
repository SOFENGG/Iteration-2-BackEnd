package view;

import view.EndOfDayPrompt;
import view.ExternalSearch;
import view.ExternalSearchFactory;
import view.OverridePricePrompt;
import controller.CashierViewController;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;

public class NavigationBox extends VBox implements View{

	private CashierViewController cvc;
	
	protected Button returnItemB; 
	protected Button serviceWorkerB;
	protected Button overridePriceB;
	protected Button endOfDayB; 
	protected Button logOutB;
	
	private ExternalSearchFactory esf;
	
	private CallbackListener callbackListener;
	
	public NavigationBox (CashierViewController cvc) {
		super ();
		this.cvc = cvc;
		
		setSpacing(25);
		setMinWidth(Control.USE_PREF_SIZE);
		
		//attach the db to this view (db.attach(this));
		
		initNB ();
		initHandlers ();
		
		getChildren().addAll(returnItemB, serviceWorkerB, overridePriceB, endOfDayB, logOutB);
	}

	private void initNB () {
		returnItemB = new Button("RETURN ITEM");     
		serviceWorkerB = new Button("SERVICE WORKER");
		overridePriceB = new Button("OVERRIDE PRICE");
		endOfDayB = new Button("END OF DAY");
		logOutB = new Button("LOGOUT");
		
		returnItemB.setMaxWidth(Double.MAX_VALUE);
		returnItemB.setMinHeight(70);
		returnItemB.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%; "
				+ "-fx-font-weight: bold");
		
		serviceWorkerB.setMaxWidth(Double.MAX_VALUE);
		serviceWorkerB.setMinHeight(70);
		serviceWorkerB.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%; "
				+ "-fx-font-weight: bold");
		
		overridePriceB.setMaxWidth(Double.MAX_VALUE);
		overridePriceB.setMinHeight(70);
		overridePriceB.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%; "
				+ "-fx-font-weight: bold");
		
		endOfDayB.setMaxWidth(Double.MAX_VALUE);
		endOfDayB.setMinHeight(70);
		endOfDayB.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%; "
				+ "-fx-font-weight: bold");
		
		logOutB.setMaxWidth(Double.MAX_VALUE);
		logOutB.setMinHeight(70);
		logOutB.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%; "
				+ "-fx-font-weight: bold");
	}
	
	public void initHandlers () {
		esf = new ExternalSearchFactory();
		
		returnItemB.setOnAction(e -> {
			ExternalSearch es = esf.getExternalSearch(returnItemB.getText());
			es.setCallbackListener(callbackListener);
			es.runWindow();
		});
		serviceWorkerB.setOnAction(e -> {
			ExternalSearch es = esf.getExternalSearch(serviceWorkerB.getText());
			es.runWindow();
		});
		
		endOfDayB.setOnAction(e -> {
			EndOfDayPrompt edp = new EndOfDayPrompt("Perform End of Day");
			edp.runWindow();
		});
		
		overridePriceB.setOnAction(e -> {
			OverridePricePrompt opp = new OverridePricePrompt("Override Price", cvc);
			opp.setCallbackListener(callbackListener);
			opp.runWindow();
		});
		
		logOutB.setOnAction(e -> {
			cvc.logout();
		});
	}

	@Override
	public void update () {
		
	}
	
	public void setCallbackListener(CallbackListener callbackListener){
		this.callbackListener = callbackListener;
	}
	
}
