package view;

import java.util.ArrayList;

import view.ExternalSearch;
import view.ExternalSearchFactory;
import controller.CashierViewController;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomerBox extends HBox implements View {

	private CashierViewController cvc;
	
	protected HBox CustBox;
	protected HBox[] HLabelBox;
		protected VBox ButtonBox;
			protected Button custCButton;
			protected Button custRButton;
		protected VBox[] VLabelBox;
			protected Label[] cLabels;
			
	private ArrayList<String> returnedRow = null;
	private ExternalSearchFactory esf;
	private ExternalSearch es;
			
	public CustomerBox (CashierViewController cvc) {
		super ();
		this.cvc = cvc;
		
		//attach the db to this view (db.attach(this));
		
		initCB ();
		initHandlers ();
		
		getChildren().addAll(HLabelBox[1]);
	}
	
	
	private void initCB() {
		HLabelBox = new HBox[2];
		VLabelBox = new VBox[3];
		cLabels = new Label[5];
		
		HLabelBox[1] = new HBox(10);
		HLabelBox[1].setMinWidth(Control.USE_PREF_SIZE);
		
			ButtonBox = new VBox(10);
			
				custCButton = new Button("CHANGE");
				custCButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				custCButton.setStyle("-fx-focus-color: transparent; "
						+ "-fx-base: Green; -fx-background-radius: 0%;");
				
				custRButton = new Button("REMOVE");
				custRButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				custRButton.setStyle("-fx-focus-color: transparent; "
						+ "-fx-base: Green; -fx-background-radius: 0%;");
				
			ButtonBox.getChildren().addAll(custCButton, custRButton);
					
			VLabelBox[2] = new VBox(10);
			
				cLabels[0] = new Label("Current Customer");
				cLabels[0].setFont(Font.font("Arial", FontWeight.BLACK, 15));
						
				HLabelBox[0] = new HBox(10);
				
					VLabelBox[0] = new VBox(10);
					
						cLabels[1] = new Label("Customer: None");
						cLabels[1].setFont(Font.font("Arial", FontWeight.BLACK, 10));
						
						cLabels[2] = new Label("Address : N/A");
						cLabels[2].setFont(Font.font("Arial", FontWeight.BLACK, 10));
						
					VLabelBox[0].getChildren().addAll(cLabels[1], cLabels[2]);
					
					VLabelBox[1] = new VBox(10);
					
						cLabels[3] = new Label("Debt : 0.0");
						cLabels[3].setFont(Font.font("Arial", FontWeight.BLACK, 10));
						
						cLabels[4] = new Label("Limit : 0.0");
						cLabels[4].setFont(Font.font("Arial", FontWeight.BLACK, 10));
						
					VLabelBox[1].getChildren().addAll(cLabels[3], cLabels[4]);
				
				HLabelBox[0].getChildren().addAll(VLabelBox[0], VLabelBox[1]);
			
			VLabelBox[2].getChildren().addAll(cLabels[0], HLabelBox[0]);
		
		HLabelBox[1].getChildren().addAll(ButtonBox, VLabelBox[2]);
	}

	public void initHandlers () {
		esf = new ExternalSearchFactory();
		
		custCButton.setOnAction(e -> {
			System.out.println(custCButton.getText());
			es = esf.getExternalSearch(custCButton.getText());
			returnedRow = es.runWindow();
			if (returnedRow != null)
				setCustomerLabels(returnedRow.get(1), returnedRow.get(2), Double.parseDouble(returnedRow.get(3)), Double.parseDouble(returnedRow.get(4)));
		});
		
		custRButton.setOnAction(e -> {
			if(!cLabels[1].getText().equals("Customer: None"))
				setCustomerLabels("None", "N/A", 0, 0);
		});
	}
	
	public void setCustomerLabels(String name, String address, double debt, double limit){
		cLabels[1].setText("Customer: " + name);
		cLabels[2].setText("Address: " + address);
		cLabels[3].setText("Debt: " + debt);
		cLabels[4].setText("Limit: " + limit);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
