package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Genshin;


public class Main extends Application{
	
	private GenshinGameGUI genshinG;
	private Genshin g;
	
	public Main() {
		g = new Genshin();
		genshinG = new GenshinGameGUI(g);
	}
	
	public static void main(String []args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
		fxmlLoader.setController(genshinG);
		
		Parent root = fxmlLoader.load();
		genshinG.loadInterface();
		
		Scene scene = new Scene(root,630,380);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Eula Impact");
		primaryStage.setResizable(false);
		primaryStage.show();
		genshinG.startMethod();
	}
}
	
