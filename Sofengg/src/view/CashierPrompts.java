package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.WindowEvent;

public class CashierPrompts extends AlertBox{
	
	private VBox labelBox;
	private HBox QtySelBox, ButtonBox;
	

	public CashierPrompts(String title, ObservableList<String> selected) {
		super(title);
		initQtySelector(selected, title);
		initButtons(title);
		if(title.equals("Remove"))
			initLabelsRemove(selected);
		else if(title.equals("Add"))
			initLabelsAdd(selected);
		initGridConstraints();
		AddToGrid();
	}


	public int runWindow(){
		showBox();
		return qty;
	}

	private Label codeL = new Label(),
				  descL = new Label(),
				  priceL = new Label();
	
	private void initLabelsRemove(ObservableList<String> selected) {
		labelBox = new VBox(10);
		codeL.setText("ITEM CODE : " + selected.get(1));
		codeL.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		descL.setText("DESCRIPTION : " + selected.get(2));
		descL.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		priceL.setText("PRICE PER ITEM : " + selected.get(4));
		priceL.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		labelBox.getChildren().addAll(codeL, descL, priceL);
		
	}
	
	private Label qtyLabel = new Label("Quanity"),
				  priceLabel = new Label();
	private Spinner<Integer> integerSpin = new Spinner<Integer>();
	private SpinnerValueFactory<Integer> valueFactory;
	private int qty;
	private double price;
	
	private void initQtySelector(ObservableList<String> selected, String title) {
		if(title.equals("Remove")){
			qty = Integer.parseInt(selected.get(3));
			price = Double.parseDouble(selected.get(4).substring(1));
		}
		else if(title.equals("Add")){
			qty = Integer.parseInt(selected.get(5));
			price = Double.parseDouble(selected.get(6).substring(1));
		}
		
		valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, qty, 1);
		integerSpin.setValueFactory(valueFactory);
		
		qty = 1;
		
		integerSpin.valueProperty().addListener(new ChangeListener<Integer>(){
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer oldVal, Integer newVal) {
				priceLabel.setText("Total Price: " + Double.toString(price * newVal));
				qty = newVal;
			}
		});
		
		QtySelBox = new HBox(10);
		qtyLabel.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		priceLabel.setText("Total Price: " + Double.toString(price * qty));
		priceLabel.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		QtySelBox.getChildren().addAll(qtyLabel, integerSpin, priceLabel);
	}
	
	private Button cancel = new Button("CANCEL"), 
				   ok;

	private void initButtons(String title) {
		ButtonBox = new HBox(10);
		ButtonBox.setAlignment(Pos.CENTER);
		
		cancel.setOnAction(e -> {
			qty = 0;
			closeBox();
		});
		
		
		cancel.setMaxWidth(Double.MAX_VALUE);
		cancel.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%; "
				+ "-fx-font-weight: bold");
		
		ok = new Button(title);
		
		getStage().setOnCloseRequest((WindowEvent event1) -> {
			qty = 0;
			closeBox();
		});
		
		
		ok.setOnAction(e -> {
			closeBox();
		});
		ok.setMaxWidth(Double.MAX_VALUE);
		ok.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%; "
				+ "-fx-font-weight: bold");
		
		ButtonBox.getChildren().addAll(ok, cancel);
	}
	
	private void initLabelsAdd(ObservableList<String> selected) {
		labelBox = new VBox(10);
		codeL.setText("ITEM CODE : " + selected.get(0));
		codeL.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		descL.setText("DESCRIPTION : " + selected.get(1));
		descL.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		priceL.setText("PRICE PER ITEM : " + selected.get(3));
		priceL.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		labelBox.getChildren().addAll(codeL, descL, priceL);
		
	}
	
	private void initGridConstraints() {
		GridPane.setConstraints(labelBox, 0, 0);
		GridPane.setConstraints(QtySelBox, 0, 1);
		GridPane.setConstraints(ButtonBox, 0, 2);
	}

	private void AddToGrid() {
		getGrid().getChildren().addAll(labelBox, QtySelBox, ButtonBox);
	}

}
