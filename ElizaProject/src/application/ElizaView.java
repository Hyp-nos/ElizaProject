package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ElizaView {
	//final private ElizaModel model;
	final private Stage stage;
	public Button btnSend= new Button("Send");
	public TextArea txtArea= new TextArea();
	public TextField txtInput = new TextField();
	public BorderPane root;
	 String chatterName=null;
	
	 public  String getChatterName() {
		
		return chatterName;
	}
	 public void setChatterName(String name){
		 this.chatterName=name;
	 }
	public TextArea getTxtArea() {
		return txtArea;
	}

	public void setTxtArea(TextArea txtArea) {
		this.txtArea = txtArea;
	}

	public TextField getTxtInput() {
		return txtInput;
	}

	public void setTxtInput(TextField txtInput) {
		this.txtInput = txtInput;
	}

	protected ElizaView (Stage stage){
		this.stage=stage;
	
		
		stage.setTitle("Eliza the phsycic");
		
		root = new BorderPane();
		
		HBox hbMenu = new HBox();
		HBox hbInput = new HBox();
		
		
	
		
		hbInput.getChildren().addAll(txtInput,btnSend);
		
		root.setCenter(txtArea);
		txtArea.setEditable(false);
		root.setBottom(hbInput);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public void start(){
		Stage nameStage = new Stage();
		HBox pane = new HBox();
		TextField nameField = new TextField("Enter your Name");
		Button submitName = new Button("Submit");
		pane.getChildren().addAll(nameField,submitName);
		Scene nameScene= new Scene(pane);
		nameStage.setScene(nameScene);
		nameStage.show();
		submitName.setOnAction((event ->{
			String s=nameField.getText();
			setChatterName(s);
			System.out.println(chatterName);
			stage.show();
		}
		));
		
	}
	

	public void stop(){
		stage.hide();
	}

	
}
