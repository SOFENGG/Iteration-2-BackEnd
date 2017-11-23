package view;

import java.util.ArrayList;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import model.Customer;
import util.CommonQuery;

public class SelectCustomer extends ExternalSearch {

	public SelectCustomer(String title) {
		super(title);
		initSearchToggles();
<<<<<<< HEAD
		initCustomers(CommonQuery.getAllCustomer());
=======
		initCustomers();
>>>>>>> 2733030c2567a0a9e849f46560e86099438740d1
	}
	
	@Override
	public void initSearchToggles() {
		RadioButton idRadio = new RadioButton("ID"),
					nameRadio = new RadioButton("Name"),
					addressRadio = new RadioButton("Address");
					//debtRadio = new RadioButton("Debt");
		idRadio.setToggleGroup(searchToggle);
		idRadio.setUserData("ID");
		nameRadio.setToggleGroup(searchToggle);
		nameRadio.setUserData("Name");
		addressRadio.setToggleGroup(searchToggle);
		addressRadio.setUserData("Address");
		//debtRadio.setToggleGroup(searchToggle);
		searchToggle.selectToggle(idRadio);
		
		/* index 0 is searchBox
		 * index 1 is searchTable
		 */
		HBox searchBox = (HBox) promptBox.getChildren().get(0);			
		searchBox.getChildren().addAll(idRadio, nameRadio, addressRadio);
		
		searchButton.setOnAction(e -> {
			switch(searchToggle.getSelectedToggle().getUserData().toString()){
			case "ID": searchTable.reset();
				initCustomers(CommonQuery.getCustomerWithId(Integer.parseInt(searchField.getText())));
				break;
			case "Name": searchTable.reset();
				initCustomers(CommonQuery.getCustomerWithName(searchField.getText()));
				break;
			case "Address": searchTable.reset();
				initCustomers(CommonQuery.getCustomerWithAddress(searchField.getText()));
				break;
			}
		});
		
	}
	
	public void initCustomers(ArrayList<Customer> customers){
		for(Customer c : customers){
			addToCustomerSearch(c.getAccount_id(), c.getName(), c.getAddress(), c.getContactNumber(), c.getTotalVisits(), c.getDebt(), c.getDebt_limit());
		}
	}
	
	public void initCustomers(Customer c){
		addToCustomerSearch(c.getAccount_id(), c.getName(), c.getAddress(), c.getContactNumber(), c.getTotalVisits(), c.getDebt(), c.getDebt_limit());
	}
	
	public void initCustomers(){
		for(Customer c : CommonQuery.getAllCustomer()){
			addToCustomerSearch(c.getAccount_id(), c.getName(), c.getAddress(), c.getContactNumber(), c.getTotalVisits(), c.getDebt(), c.getDebt_limit());
		}
	}

}