package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ElizaController {
	@SuppressWarnings("unused")
	final private ElizaModel model;
	
	@SuppressWarnings("unused")
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
		// to make ENTER key also apply send button
		view.root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				view.btnSend.fire();
				ev.consume();
				
			}

		}

		);

	}

}
