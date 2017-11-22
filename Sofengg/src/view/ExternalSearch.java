package view;

import java.util.ArrayList;

import view.ExternalSearchInterface;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExternalSearch extends AlertBox implements ExternalSearchInterface {

	protected VBox promptBox;
	
	protected Image searchIcon = new Image(("search-icon.png"));
	protected TextField searchField = new TextField();
	protected Button searchButton = new Button();
	protected ToggleGroup searchToggle = new ToggleGroup();
	protected TableMaker searchTable;
	
	
	protected ArrayList<String> returningRow;
	
	public ExternalSearch(String title) {
		super(title);
		returningRow = null;
		searchTable = new TableMaker(title);
		initItemSearch();
		initGridConstraints();
		AddToGrid();
	}
	
	public ArrayList<String> runWindow(){
		showBox();
		return returningRow;
	}
	
	private void initItemSearch() {
		promptBox = new VBox(10);
		HBox searchBox = new HBox(25),
			 searchInput = new HBox();
		searchField.setPromptText("Search...");
		
		ImageView searchView = new ImageView(searchIcon);
		searchView.setFitHeight(15);
		searchView.setFitWidth(15);	
		searchButton.setGraphic(searchView);
		
		/*
		RadioButton itemRadio = new RadioButton("Item Code"),
					descRadio = new RadioButton("Description");
		itemRadio.setToggleGroup(searchToggle);
		descRadio.setToggleGroup(searchToggle);
		searchToggle.selectToggle(itemRadio);
		*/
		
		searchInput.getChildren().addAll(searchButton, searchField);
		searchBox.getChildren().addAll(searchInput);
		
		promptBox.getChildren().add(searchBox);
		
		initSearchAdder();
		
		promptBox.getChildren().add(searchTable.getTable());
	}
	

	public void initSearchAdder() {
		searchTable.getRawTable().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	returningRow = new ArrayList<String>(searchTable.getRawTable().getSelectionModel().getSelectedItem());
		        	closeBox();
		        }
			}
		});
	}
	
	public void addToReturnSearch(String itemCode, String desc, int qty, double price, String type){
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
	}
	
	public void addToServiceSearch(int id, String name, double salary) {
		searchTable.addToSearch(id, name, salary);
	}
	
	public void addToCustomerSearch(int id, String name, String address, double debt, double limit) {
		searchTable.addToSearch(id, name, address, debt, limit);
	}

	private  void initGridConstraints() {
		GridPane.setConstraints(promptBox, 0, 0);
	}

	private void AddToGrid() {
		getGrid().getChildren().addAll(promptBox);
	}

	@Override
	public void initSearchToggles() {}

}
