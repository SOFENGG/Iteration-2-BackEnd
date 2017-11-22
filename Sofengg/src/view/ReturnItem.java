package view;

import view.ExternalSearch;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

public class ReturnItem extends ExternalSearch{

	public ReturnItem(String title) {
		super(title);
		initSearchToggles();
		addToReturnSearch("1010101", "gass and stuff", 2, 300, "RETAIL SALE");
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
	
}