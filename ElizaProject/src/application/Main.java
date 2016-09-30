package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private ElizaModel model;
	private ElizaView view;
	private ElizaController controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			view = new ElizaView(primaryStage);
			model = new ElizaModel(view);
			controller= new ElizaController(model, view);
			view.start();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void stop(){
		if (view !=null) view.stop();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
