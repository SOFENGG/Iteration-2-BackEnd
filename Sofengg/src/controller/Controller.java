package controller;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.*;

public abstract class Controller {

	protected Stage mainStage;
	protected Scene scene;
	protected int currentView;
	
	public Controller (Stage stage) {
		this.mainStage = stage;
		
		scene = new Scene (new Group (), mainStage.getWidth (), mainStage.getHeight ());
		initControllers ();
		
		setScene (0,0);
		
		stage.setScene (scene);
		stage.setResizable(false);
		stage.show ();
	}
	
	protected abstract void initControllers ();
	
	public abstract void setScene (int requestCode, int view);
	
	public Stage getStage () {
		return mainStage;
	}
	
	public void resetStage(){
		mainStage.hide();
		mainStage.show();
	}
	
	public void closeStage(){
		mainStage.close();
	}
	
}
