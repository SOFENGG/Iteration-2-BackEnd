package view;

import view.ExternalSearch;

import java.math.BigDecimal;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Item;
import util.CommonQuery;

public class ReturnItem extends ExternalSearch{
	
	public ReturnItem(String title) {
		super(title);
		initSearchToggles();
		
		refreshSearchItem(CommonQuery.allItems());
		
		initSearchAdder();
	}

	@Override
	public void initSearchToggles() {
		RadioButton itemRadio = new RadioButton("Item Code"),
					descRadio = new RadioButton("Description");
		itemRadio.setToggleGroup(searchToggle);
		descRadio.setToggleGroup(searchToggle);
		searchToggle.selectToggle(itemRadio);
		
		/* index 0 is searchBox
		 * index 1 is searchTable
		 */
		HBox searchBox = (HBox) promptBox.getChildren().get(0);			
		searchBox.getChildren().addAll(itemRadio, descRadio);
		
	}
	
	public void refreshSearchItem(ArrayList<Item> items){
		for(Item item : items){	
			searchTable.addToSearch(item.getItemCode(),
					item.getName(),
					item.getDescription(),
					item.getDescription(),
					item.getManufacturer(),
					item.getStock() - item.getReserved(),
					item.getPriceCustomer());
		}
	}
	
	public void initSearchAdder() {
		searchTable.getRawTable().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	returningRow = new ArrayList<String>(searchTable.getRawTable().getSelectionModel().getSelectedItem());
		        	for (String string: returningRow)
		        		System.out.println(string);
		        	addToReturnSearch(returningRow.get(0), returningRow.get(1), returningRow.get(2), returningRow.get(3),
		        			returningRow.get(4), Integer.parseInt(returningRow.get(5)), new BigDecimal (returningRow.get(6).substring(1)));
		        	//closeBox();
		        }
			}
		});
	}
	
	@Override
	public void addToReturnSearch(String itemCode, String name, String desc, String category, String manufacturer, int qty, BigDecimal price){
		ObservableList<String> selected;
		boolean found = false;
		for(int x = 0; x<searchTable.getRawTable().getItems().size(); x++){
			selected = searchTable.getRawTable().getItems().get(x);
			if(selected.get(0).equals(itemCode)){
				int oldQty = Integer.parseInt(selected.get(5));
				qty += oldQty;
				System.out.println(qty);
				searchTable.updateSearch(x, itemCode, name, desc, category, manufacturer, qty, price);
				found = true;
				
			}
		}
		if(!found)
			searchTable.addToSearch(itemCode, name, desc, category, manufacturer, qty, price);
	}

	
	
}