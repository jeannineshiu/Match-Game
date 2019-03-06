package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.Game;
import scenes.Menu;

public class Main extends Application{
	public Stage stage;
	public Scene menu,gameScene;
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			stage.setResizable(false);
			menu= new Menu(FXMLLoader.load(getClass().getResource("OAO.fxml")),this);
			gameScene = new Game(FXMLLoader.load(getClass().getResource("AOA.fxml")),this);
			primaryStage.setScene(menu);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void toGame() {
		stage.setScene(gameScene);
	}
	public void toMenu() {
		stage.setScene(menu);
		
	}
	public Scene getGameScene() {
		return gameScene;
	}
	public Scene getMenuScene() {
		return menu;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
