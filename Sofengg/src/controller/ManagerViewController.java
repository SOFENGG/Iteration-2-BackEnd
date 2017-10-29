package controller;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Item;
import util.Query;
import view.CashierView;
import view.ManagerView;

public class ManagerViewController {

	private MainController mc;
	private ManagerView mv;
	
	public ManagerViewController (MainController mc) {
		this.mc = mc;
		mv = new ManagerView (this);
	}
	
	public Pane getView (int view) {
		switch(view) {
			case Code.MANAGER_VIEW: return mv;
		}
		return null;
	}
	
	public Stage getMainStage () {
		return mc.getStage ();
	}
	
	public MainController getMainController() {
		return mc;
	}
	
	public void changeControl (int requestCode, int view) {
		mc.setScene(requestCode, view);
	}
	
	//manager view services
	
	//no filter/search
	public ArrayList<Item> allItems(){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from items;");
		return items;
	}
	
	//search
	public ArrayList<Item> searchItems(String search){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from items where concat(name, description, category, manufacturer) like '%" + search + "%';");
		if (items.size() == 0)
			//no matches
			return null;
		return items;
	}
	
}
