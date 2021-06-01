package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import thread.ProgressBarThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Genshin;
import model.LoadingBar;
import model.Player;

public class GenshinGameGUI implements Initializable{
	
	private LoadingBar loadBar;
	private Genshin genshin;
	private Player playerToMove;
	private Player rectangleToMove;

	@FXML
    private BorderPane mainBorderPane;

	  @FXML
	   private Label txtPercent;
	  @FXML
	    private ImageView imageBossDvalin;

	    @FXML
	    private ImageView imageToMove ;

	    @FXML
	    private ImageView imageBossAndrius;

	    @FXML
	    private ImageView imageCelestialDoor;

	    @FXML
	    private Rectangle shRectangleToMove = new Rectangle(10,10);
	    @FXML
	    private Rectangle loadBarShape;
	    private Media audio;
	    private MediaPlayer reproductor;
	    
	   
	public GenshinGameGUI(Genshin genshinG) {
		genshin = genshinG;
		loadBar = new LoadingBar();
		startMethod();
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    }
    
    public void startMethod() {
    	playerToMove = new Player(190,465);
    	rectangleToMove = new Player(190,465);
    }

    @FXML
    void movePlayerPrincipal(KeyEvent event) throws IOException {
    		if(event.getCode() == KeyCode.UP) {
    			playerToMove.moveUp();
    		}
    		if(event.getCode() == KeyCode.DOWN) {
    			playerToMove.moveDown();
    		}
    		if(event.getCode() == KeyCode.LEFT) {
    			playerToMove.moveLeft();
    		}
    		if(event.getCode() == KeyCode.RIGHT) {
    			playerToMove.moveRigth();
    		}
    		shRectangleToMove.setLayoutX(rectangleToMove.getX());
    		shRectangleToMove.setLayoutY(rectangleToMove.getY());
    		imageToMove.setLayoutX(playerToMove.getX());
    		imageToMove.setLayoutY(playerToMove.getY());
    		youTouchTheDvalinBoss();
    		youTouchTheAndriusBoss();
    		youTouchTheEnd();
    		youTouchTheDoor();
    }
	
    public void youTouchTheDvalinBoss() throws IOException {
    	if(genshin.getColitionWithDvalin()==0) {
    		if(imageToMove.getLayoutY() == 415) {
    			loadFigthWithDvalin();
    			genshin.noMoreColitionWithDvalin();
    		}
    	}else {
    		imageBossDvalin.setVisible(false);
    		imageBossDvalin.setImage(null);
    	}
    }
    
    public void youTouchTheAndriusBoss() throws IOException {
    	if(genshin.getColitionWithAndrius()==0) {
    		if(imageToMove.getLayoutY()==270) {
    			loadFigthWithAndrius();
    			genshin.noMoreColitionWithAndrius();
    		}
    	}else {
    		imageBossAndrius.setVisible(false);
    		imageBossAndrius.setImage(null);
    	}
    }
    
    public void youTouchTheDoor() throws IOException {
    	if(imageToMove.getLayoutY()==145) {
    		loadYouWin();
    	}
    }
    
    public void youTouchTheEnd() throws IOException {
    	if(imageToMove.getLayoutX()==150 || imageToMove.getLayoutX()==235) {
    		loadYouDied();
    	}
    }
  
	public void audioToFirstBoss(Boolean playIt) {
		if(playIt==true) {	
			final String FILE_NAME = "audio/audioDvalin.mp3";
			File archivo = new File(FILE_NAME);
			audio = new Media(archivo.toURI().toString());
			reproductor = new MediaPlayer(audio);
			reproductor.setVolume(2);
			reproductor.play();
		}
		if(playIt==false) {
			reproductor.stop();
		}
		
	}
	
	public void audioToSecondBoss(Boolean playIt) {
		if(playIt==true) {	
			final String FILE_NAME = "audio/audioAndrius.mp3";
			File archivo = new File(FILE_NAME);
			audio = new Media(archivo.toURI().toString());
			reproductor = new MediaPlayer(audio);
			reproductor.play();
		}
		if(playIt==false) {
			reproductor.stop();
		}
		
	}
	
	public void audioYouDie(Boolean playIt) {
		if(playIt==true) {	
			final String FILE_NAME = "audio/audioYouDie.mp3";
			File archivo = new File(FILE_NAME);
			audio = new Media(archivo.toURI().toString());
			reproductor = new MediaPlayer(audio);
			reproductor.play();
		}
		if(playIt==false) {
			reproductor.stop();
		}
		
	}
	
	public void audioLogin(Boolean playIt) {
		if(playIt==true) {	
			final String FILE_NAME = "audio/audioLogin.mp3";
			File archivo = new File(FILE_NAME);
			audio = new Media(archivo.toURI().toString());
			reproductor = new MediaPlayer(audio);
			reproductor.play();
		} 
		else{
			reproductor.stop();
		}

	}

	
	public void loadInterface() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loadingInterface.fxml"));
		
		fxmlLoader.setController(this);
		
		Parent progressPane = fxmlLoader.load();
		
		mainBorderPane.getChildren().clear();
    	mainBorderPane.setCenter(progressPane); 	
    	 loadBar.setActive(true);
		 new ProgressBarThread(loadBar,this).start();
		 
	}
	
	public void closeTheGame(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public void updateBar() {
		txtPercent.setText((int)(loadBar.getProgressLevel()/2.12)+"%");
		 loadBarShape.setWidth(loadBar.getProgressLevel());
		 if(loadBar.isActive()==false) {
			 try {
					 loadPlayGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }	
	}
	
	public void loadPlayGame() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playGame.fxml"));
		fxmlLoader.setController(this);

		Parent loginPane = fxmlLoader.load();

		mainBorderPane.getChildren().clear();
		mainBorderPane.setCenter(loginPane);
		Stage st = (Stage)
		loginPane.getScene().getWindow();
		st.setHeight(420);
		st.setWidth(620);
		audioLogin(true);
      }
	
	public void loadPlayingInGame() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playingInGame.fxml"));
		fxmlLoader.setController(this);

		Parent loginPane = fxmlLoader.load();

		mainBorderPane.getChildren().clear();
		mainBorderPane.setCenter(loginPane);
		Stage st = (Stage)
		loginPane.getScene().getWindow();
		st.setHeight(570);
		st.setWidth(600);
		audioLogin(false);
		audioToFirstBoss(false);
		audioToSecondBoss(false);
		audioYouDie(false);
	}
	
	public void loadFigthWithDvalin() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fightDvalin.fxml"));
		fxmlLoader.setController(this);
		
		Parent loginPane = fxmlLoader.load();
		
		mainBorderPane.getChildren().clear();
    	mainBorderPane.setCenter(loginPane);
    	Stage st = (Stage)
    	loginPane.getScene().getWindow();
    	st.setHeight(420);
    	st.setWidth(620);
    	audioToFirstBoss(true);
	}
	
	public void loadYouDied() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("youDied.fxml"));
		fxmlLoader.setController(this);
		
		Parent loginPane = fxmlLoader.load();
		
		mainBorderPane.getChildren().clear();
    	mainBorderPane.setCenter(loginPane);
    	Stage st = (Stage)
    	loginPane.getScene().getWindow();
    	st.setHeight(440);
    	st.setWidth(640);
    	audioYouDie(true);
	}
	

	public void loadYouWin() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("youWin.fxml"));
		fxmlLoader.setController(this);
		
		Parent loginPane = fxmlLoader.load();
		
		mainBorderPane.getChildren().clear();
    	mainBorderPane.setCenter(loginPane);
    	Stage st = (Stage)
    	loginPane.getScene().getWindow();
    	st.setHeight(440);
    	st.setWidth(640);
	}
	
	public void loadFigthWithAndrius() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fightAndrius.fxml"));
		fxmlLoader.setController(this);
		
		Parent loginPane = fxmlLoader.load();
		
		mainBorderPane.getChildren().clear();
    	mainBorderPane.setCenter(loginPane);
    	Stage st = (Stage)
    	loginPane.getScene().getWindow();
    	st.setHeight(420);
    	st.setWidth(620);
    	audioToSecondBoss(true);
	}
	
	 @FXML
	    public void credits(ActionEvent event) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Credits");
	        alert.setHeaderText(null);
	        alert.setContentText("Daniel Ramirez Gomez");
	        alert.showAndWait();
	    }


}
