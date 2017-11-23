package view;

import view.TableMaker;
import view.AlertBox;
import view.CashierPrompts;
import view.CheckoutPrompt;
import controller.CashierViewController;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CartPane extends TabPane implements View{

	private CashierViewController cvc;
	
	private TableMaker ongoingWTable, ongoingRTable, holdTable;
	private Tab ongoingTab, holdTab, WoRTab;
	
	private Label cartCost;
	
	private VBox TranBox;
	
	public CartPane (CashierViewController cvc) {
		super();
		this.cvc = cvc;
		
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		initCP ();
	}
	
	private void initCP() {
		TranBox = new VBox(10);
		
		cartCost = new Label("TOTAL : P0");
		cartCost.setFont(Font.font("Arial", FontWeight.BLACK, 15));
		cartCost.setMinWidth(Control.USE_PREF_SIZE);
		
		ongoingTab = new Tab("ONGOING");
		ongoingWTable = new TableMaker("Ongoing");
		ongoingRTable = new TableMaker("Ongoing");
		ongoingTab.setStyle("-fx-focus-color: transparent; "
				          + "-fx-font-weight: bold");
		getTabs().add(ongoingTab);
		
		ongoingTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (ongoingTab.isSelected()) 
                	if(!getTabs().contains(WoRTab))
                		getTabs().add(WoRTab);
            }
        });
		
		initCartRemover();
		//TranBox.getChildren().add(ongoingWTable.getTable());
		TranBox.getChildren().add(ongoingRTable.getTable());
		ongoingTab.setContent(TranBox);
		
		initHCart(); //Hold 
		initWholeRetailButton();
		initTranButtons();
		initItemSearch();
	}

	private void initCartRemover() {
		ongoingWTable.getRawTable().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	ObservableList<String> selected = ongoingWTable.getRawTable().getSelectionModel().getSelectedItem();
		        	if(selected != null){
		        		CashierPrompts cp = new CashierPrompts("Remove", selected);
		        		
			        	int qtyToRemove = cp.runWindow(),
			        	    qty = Integer.parseInt(selected.get(3));
			        	
			        	//XXX
			        	if(qtyToRemove != 0){
			        		moveToSearch(selected.get(1), selected.get(2), qtyToRemove, Double.parseDouble(selected.get(4).substring(1)), "WHOLE SALE");
			        	}
			        	if(qty == qtyToRemove){
			        		ongoingWTable.getRawTable().getItems().remove(selected);
			        	}
			        	else {
			        		int index = ongoingWTable.getRawTable().getItems().indexOf(selected) ;
			        		qty -= qtyToRemove;
			        		String itemCode = selected.get(1),
			        			   desc = selected.get(2);
			        		double price = Double.parseDouble(selected.get(4).substring(1));
			        		ongoingWTable.updateCart(index,index+ 1, itemCode, desc, qty, price);
			        		
			        	}
		        	}
		        }
		        changeCartCost();
		    }
		});
		
		ongoingRTable.getRawTable().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	ObservableList<String> selected = ongoingRTable.getRawTable().getSelectionModel().getSelectedItem();
		        	if(selected != null){
		        		CashierPrompts cp = new CashierPrompts("Remove", selected);
		        		
			        	int qtyToRemove = cp.runWindow(),
			        	    qty = Integer.parseInt(selected.get(3));
			        	
			        	//XXX
			        	if(qtyToRemove != 0){
			        		moveToSearch(selected.get(1), selected.get(2), qtyToRemove, Double.parseDouble(selected.get(4).substring(1)), "RETAIL SALE");
			        	}
			        	if(qty == qtyToRemove){
			        		ongoingRTable.getRawTable().getItems().remove(selected);
			        	}
			        	else {
			        		int index = ongoingRTable.getRawTable().getItems().indexOf(selected);
			        		qty -= qtyToRemove;
			        		String itemCode = selected.get(1),
			        			   desc = selected.get(2);
			        		double price = Double.parseDouble(selected.get(4).substring(1));
			        		ongoingRTable.updateCart(index, index + 1, itemCode, desc, qty, price);
			        		
			        	}
		        	}
		        }
		        changeCartCost();
		    }
		});
	}
	
	private void initHCart() {
		holdTab = new Tab("HOLD");
		holdTable = new TableMaker("Hold");
		holdTab.setStyle("-fx-focus-color: transparent; "
					   + "-fx-font-weight: bold");
		getTabs().add(holdTab);
		
		holdTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (holdTab.isSelected()) 
                	getTabs().remove(2);
            }
        });
		
		holdTab.setContent(holdTable.getTable());
	}
	
	private void initWholeRetailButton() {
		WoRTab = new Tab("RETAIL SALE");
		getTabs().add(WoRTab);
		
		WoRTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (WoRTab.isSelected()) {
                	if(WoRTab.getText().equals("WHOLE SALE")){
                		WoRTab.setText("RETAIL SALE");
                		TranBox.getChildren().set(0, ongoingRTable.getTable());
                	}
                	else {
                		WoRTab.setText("WHOLE SALE");
                		TranBox.getChildren().set(0, ongoingWTable.getTable());
                	}
                	changeCartCost();
                	getSelectionModel().select(0);
                }
                
            }
        });
		
		WoRTab.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%;"
				+ "-fx-font-weight: bold");
	}
	
	//for setting the transaction type when restoring a held cart - anj
	/*public void setWoR(String transactionType) {
		if (transactionType.equals("WHOLE SALE")) {
			WoRTab.setText("WHOLE SALE");
    		TranBox.getChildren().set(0, ongoingWTable.getTable());
		} else {
			WoRTab.setText("RETAIL SALE");
    		TranBox.getChildren().set(0, ongoingRTable.getTable());
		}
		changeCartCost();
    	getSelectionModel().select(0);	
	}*/
	
	public void clearAllTables(boolean logOut){
		ongoingWTable.clearCart(logOut);
		ongoingRTable.clearCart(logOut);
		holdTable.clearCart(logOut);
	}
	
	//XXX
	public void moveToCart(String itemCode, String desc, int qty, double price){
		ObservableList<String> selected;
		boolean found = false;
		if(WoRTab.getText().equals("WHOLE SALE")){
			for(int x = 0; x<ongoingWTable.getRawTable().getItems().size(); x++){
				selected = ongoingWTable.getRawTable().getItems().get(x);
				if(selected.get(1).equals(itemCode)){
					int oldQty = Integer.parseInt(selected.get(3));
					qty += oldQty;
					 ongoingWTable.updateCart(x, x + 1, itemCode, desc, qty, price);
					found = true;
				}
			}
			if(!found)
				ongoingWTable.addToCart(itemCode, desc, qty, price);
    	}
    	else if(WoRTab.getText().equals("RETAIL SALE")){
    		for(int x = 0; x<ongoingRTable.getRawTable().getItems().size(); x++){
				selected = ongoingRTable.getRawTable().getItems().get(x);
				if(selected.get(1).equals(itemCode)){
					int oldQty = Integer.parseInt(selected.get(3));
					qty += oldQty;
					ongoingRTable.updateCart(x, x + 1, itemCode, desc, qty, price);
					found = true;
				}
			}
			if(!found)
				ongoingRTable.addToCart(itemCode, desc, qty, price);
    	}
		
		changeCartCost();
	}
	
	//XXX
	public void moveToSearch(String itemCode, String desc, int qty, double price, String type){
		ObservableList<String> selected;
		boolean found = false;
		for(int x = 0; x<searchTable.getRawTable().getItems().size(); x++){
			selected = searchTable.getRawTable().getItems().get(x);
			if(selected.get(0).equals(itemCode)){
				int oldQty = Integer.parseInt(selected.get(2));
				qty += oldQty;
				searchTable.updateSearch(x, itemCode, desc, qty, price);
				found = true;
			}
		}
		if(!found)
			searchTable.addToSearch(itemCode, desc, qty, price, type);
		
		changeCartCost();	
	}
	
	public void addToHold(String date, String time, double total, String type){
		holdTable.addToHold(date, time, total, type);
	}
	
	public double getCartCost() {
		ObservableList<String> selected;
		double totalPrice = 0;
		if(WoRTab.getText().equals("WHOLE SALE")){
			for(int x = 0; x<ongoingWTable.getRawTable().getItems().size(); x++){
				selected = ongoingWTable.getRawTable().getItems().get(x);
				totalPrice += Double.parseDouble(selected.get(4).substring(1)) * Integer.parseInt(selected.get(3));
			}
    	}
    	else {
    		for(int x = 0; x<ongoingRTable.getRawTable().getItems().size(); x++){
				selected = ongoingRTable.getRawTable().getItems().get(x);
				totalPrice += Double.parseDouble(selected.get(4).substring(1)) * Integer.parseInt(selected.get(3));
			}
    	}
		return totalPrice;
	}
	
	private Button holdButton = new Button("HOLD"),
				   COutButton = new Button("CHECKOUT"),
				   clearCButton = new Button("CLEAR CART");
	
	private void initTranButtons() {
		HBox tranButtBox = new HBox(10);
		
		holdButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		holdButton.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%;");
		
		COutButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		COutButton.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%;");
		
		clearCButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		clearCButton.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: Green; -fx-background-radius: 0%;");
		
		tranButtBox.getChildren().addAll(holdButton, COutButton, clearCButton, cartCost);
		initTranOnActionButtons();
		TranBox.getChildren().add(tranButtBox);
	}
	
	private void initTranOnActionButtons(){
		//XXX
		clearCButton.setOnAction(e ->  {
			ObservableList<String> selected; 
			if(!ongoingWTable.getRawTable().getItems().isEmpty() || !ongoingRTable.getRawTable().getItems().isEmpty()){
				if(WoRTab.getText().equals("WHOLE SALE")){
					for(int x = 0; x<ongoingWTable.getRawTable().getItems().size(); x++){
						selected = ongoingWTable.getRawTable().getItems().get(x);
						moveToSearch(selected.get(1), selected.get(2), Integer.parseInt(selected.get(3)), Double.parseDouble(selected.get(4).substring(1)), "WHOLE SALE");
					}
	        		ongoingWTable.clearCart(false);
	        	}
	        	else {
	        		for(int x = 0; x<ongoingRTable.getRawTable().getItems().size(); x++){
	        			selected = ongoingRTable.getRawTable().getItems().get(x);
						moveToSearch(selected.get(1), selected.get(2), Integer.parseInt(selected.get(3)), Double.parseDouble(selected.get(4).substring(1)), "RETAIL SALE");
					}
	        		ongoingRTable.clearCart(false);
	        	}
			}else{
				AlertBox ab = new AlertBox("Warning");
				ab.initAlertContents("Cart is Already Empty");
				ab.showBox();
			}
			changeCartCost();
		});
		
		// checkout button
		// GET CURRENT CUSTOMER ID
		// to check if there is a user or not
		// userID = 0 means no customer
		COutButton.setOnAction(e -> {
			double totalPrice = getCartCost();
			int userID = 1;
			CheckoutPrompt cp = new CheckoutPrompt("Checkout", totalPrice, userID);
			cp.showBox();
		});
	}
	
	protected Image searchIcon = new Image(("search-icon.png"));
	protected TextField searchField = new TextField();
	protected Button searchButton = new Button();
	protected ToggleGroup searchToggle = new ToggleGroup();
	protected TableMaker searchTable = new TableMaker("Search");
	
	private void initItemSearch() {
		HBox searchBox = new HBox(25),
			 searchInput = new HBox();
		searchField.setPromptText("Search...");
		
		ImageView searchView = new ImageView(searchIcon);
		searchView.setFitHeight(15);
		searchView.setFitWidth(15);	
		searchButton.setGraphic(searchView);
		
		RadioButton itemRadio = new RadioButton("Item Code"),
					descRadio = new RadioButton("Description"),
					servRadio = new RadioButton("Service");
		itemRadio.setToggleGroup(searchToggle);
		descRadio.setToggleGroup(searchToggle);
		servRadio.setToggleGroup(searchToggle);
		searchToggle.selectToggle(itemRadio);
		
		searchInput.getChildren().addAll(searchButton, searchField);
		searchBox.getChildren().addAll(searchInput, itemRadio, descRadio, servRadio);
		
		initCartAdder();
		
		TranBox.getChildren().addAll(searchBox, searchTable.getTable());
	}
	
	private void initCartAdder() {
		//XXX
		searchTable.getRawTable().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
		    public void handle(MouseEvent event) {
				
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	ObservableList<String> selected = searchTable.getRawTable().getSelectionModel().getSelectedItem();
		        	if(selected != null){
		        		CashierPrompts cp = new CashierPrompts("Add", selected);
			        	int qty = cp.runWindow(),
			        		qtyActual = Integer.parseInt(selected.get(2));
			        	
			        	if(qty != 0){
			        		moveToCart(selected.get(0), selected.get(1), qty, Double.parseDouble(selected.get(3).substring(1)));
			        	}
			        	if(qtyActual == qty){
			        		searchTable.getRawTable().getItems().remove(selected);
			        	}
			        	else if(qty != 0){
			        		int index = searchTable.getRawTable().getItems().indexOf(selected);
			        		qtyActual -= qty;
			        		String itemCode = selected.get(0),
				        			   desc = selected.get(1);
				        		double price = Double.parseDouble(selected.get(3).substring(1));
			        		searchTable.updateSearch(index, itemCode, desc, qtyActual, price);
			        	}
		        	}
		        }
			}
		});
	}
	
	//XXX
	public void changeCartCost(){
		ObservableList<String> selected;
		double totalPrice = 0;
		if(WoRTab.getText().equals("WHOLE SALE")){
			for(int x = 0; x<ongoingWTable.getRawTable().getItems().size(); x++){
				selected = ongoingWTable.getRawTable().getItems().get(x);
				totalPrice += Double.parseDouble(selected.get(4).substring(1)) * Integer.parseInt(selected.get(3));
			}
    	}
    	else {
    		for(int x = 0; x<ongoingRTable.getRawTable().getItems().size(); x++){
				selected = ongoingRTable.getRawTable().getItems().get(x);
				totalPrice += Double.parseDouble(selected.get(4).substring(1)) * Integer.parseInt(selected.get(3));
			}
    	}
		cartCost.setText("TOTAL : P" + totalPrice);
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	

}
