package thread;

import javafx.application.Platform;
import model.LoadingBar;
import ui.GenshinGameGUI;

public class ProgressBarThread extends Thread{
	
	private LoadingBar loadBar;
	private GenshinGameGUI genshinG;
	
	public ProgressBarThread(LoadingBar loadB, GenshinGameGUI genshinGame) {
		loadBar = loadB;
		genshinG = genshinGame;
	}
	
	public void run() {
		while (loadBar.isActive()) {
			loadBar.advance();
			Platform.runLater(new Thread() {
				public void run() {
					genshinG.updateBar();
				}
			});
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
	}
}