package view;

import view.ExternalSearch;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import model.Worker;
import util.CommonQuery;

public class ServiceWorker extends ExternalSearch {

	public ServiceWorker(String title) {
		super(title);
		initSearchToggles();
		initServiceWorkers();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initSearchToggles() {
		RadioButton idRadio = new RadioButton("ID"),
					nameRadio = new RadioButton("Name"),
					salaryRadio = new RadioButton("Salary");
		idRadio.setToggleGroup(searchToggle);
		nameRadio.setToggleGroup(searchToggle);
		salaryRadio.setToggleGroup(searchToggle);
		searchToggle.selectToggle(idRadio);
		
		/* index 0 is searchBox
		 * index 1 is searchTable
		 */
		HBox searchBox = (HBox) promptBox.getChildren().get(0);			
		searchBox.getChildren().addAll(idRadio, nameRadio, salaryRadio);
	}

	public void initServiceWorkers(){
		for(Worker w : CommonQuery.getWorkers()){
			addToServiceSearch(w.getWorkerID(), w.getName(), w.getSalary());
		}
	}
}