package scenes;

import application.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Menu extends Scene{
	Button start,exit;
	Main m;
	public Menu(Parent root,Main main) {
		super(root);
		m = main;
		start = (Button) lookup("#start");
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				m.toGame();
			}
		});
		exit = (Button) lookup("#exit");
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Platform.exit();
			}
		});
		
	}
}
