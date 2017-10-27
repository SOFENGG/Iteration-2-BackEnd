import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Database;
import model.User;

public class DriverView extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		new MainController(primaryStage);
	}

	public static void main (String args[]) {
		Database.getInstance().connect();
		launch(args);
		Database.getInstance().close();
	}
}
