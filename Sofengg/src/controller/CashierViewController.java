package controller;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Item;
import util.Query;
import view.CashierView;

public class CashierViewController {

	private MainController mc;
	private CashierView cv;
	
	public CashierViewController (MainController mc) {
		this.mc = mc;
		cv = new CashierView (this);
	}
	
	public Pane getView (int view) {
		switch(view) {
			case Code.CASHER_VIEW: return cv;
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
	
	//cashier view services
	
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
