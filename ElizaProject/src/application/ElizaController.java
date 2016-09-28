package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ElizaController implements EventHandler<ActionEvent> {
	final private ElizaModel model;
	final private ElizaView view;
	
	protected ElizaController(ElizaModel model, ElizaView view){
		this.model= model;
		this.view=view;
		
		
		view.btnSend.setOnAction(this);
		
	}
	@Override
	public void handle(ActionEvent e){
		view.txtArea.appendText(">you: "+ view.txtInput.getText()+"\n");
		
	}
	
	
}
