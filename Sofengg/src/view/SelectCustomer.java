package view;

import view.ExternalSearch;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import model.Customer;
import util.CommonQuery;

public class SelectCustomer extends ExternalSearch {

	public SelectCustomer(String title) {
		super(title);
		initSearchToggles();
		initCustomers();
	}
	
	@Override
	public void initSearchToggles() {
		RadioButton idRadio = new RadioButton("ID"),
					nameRadio = new RadioButton("Name"),
					addressRadio = new RadioButton("Address"),
					debtRadio = new RadioButton("Debt");
		idRadio.setToggleGroup(searchToggle);
		nameRadio.setToggleGroup(searchToggle);
		addressRadio.setToggleGroup(searchToggle);
		debtRadio.setToggleGroup(searchToggle);
		searchToggle.selectToggle(idRadio);
		
		/* index 0 is searchBox
		 * index 1 is searchTable
		 */
		HBox searchBox = (HBox) promptBox.getChildren().get(0);			
		searchBox.getChildren().addAll(idRadio, nameRadio, addressRadio, debtRadio);
	}
	
	public void initCustomers(){
		for(Customer c : CommonQuery.getAllCustomer()){
			addToCustomerSearch(c.getAccount_id(), c.getName(), c.getAddress(), c.getContactNumber(), c.getTotalVisits(), c.getDebt(), c.getDebt_limit());
		}
	}

}