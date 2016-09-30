package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ElizaController {
	final private ElizaModel model;
	final private ElizaView view;

	protected ElizaController(ElizaModel model, ElizaView view) {
		this.model = model;
		this.view = view;

		view.btnSend.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			model.sendToServer();
			}
		});

	}

}
