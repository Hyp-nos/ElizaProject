package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import serverClient.User;

public class ElizaController {
	final private ElizaModel model;
	final private ElizaView view;

	protected ElizaController(ElizaModel model, ElizaView view) {
		this.model = model;
		this.view = view;

		view.btnSend.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			Thread th = new Thread(model);	
			th.start();
			}
		});

	}

}
