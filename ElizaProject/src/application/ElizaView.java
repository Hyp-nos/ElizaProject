package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ElizaView {
	//final private ElizaModel model;
	final private Stage stage;
	Button btnSend= new Button("Send");
	TextArea txtArea= new TextArea();
	TextField txtInput = new TextField();
	
	
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
		
		BorderPane root = new BorderPane();
		
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
		stage.show();
	}
	public void stop(){
		stage.hide();
	}
}
