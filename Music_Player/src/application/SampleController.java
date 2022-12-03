package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class SampleController {

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private Button playBut;
    
    @FXML
    private ImageView logolight;


    @FXML
     private Label songName;
    
    @FXML
    private ImageView img;
    
    @FXML
    private Button resetBut;
    
    @FXML
    private URL location;
    
    @FXML
    private Button stopBtn;

    @FXML
    private ListView<File> playlist;
    
    @FXML
    private MediaView mediaView;

    @FXML
    private ProgressBar songProgres;

    private ArrayList<File> tracks;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File[] files; 
    private File dir;
	private int trackPos;
	private Timer timer;
	private TimerTask task;
	@FXML
    void SetFolder() {
    	
    	tracks = new ArrayList<File>();
    	
    	dir = new File("Music");
    	
    	files = dir.listFiles();
    	
    	if (files != null) {
    		for (File file: files) {
    			tracks.add(file);
    			//System.out.println(file);
    		}
    	}
    	
    	playlist.getItems().addAll(tracks);
    	
    	media = new Media(tracks.get(trackPos).toURI().toString());
    	
    	mediaPlayer = new MediaPlayer(media);
    	
    	songName.setText(tracks.get(trackPos).getName());
    	songName.setVisible(true);
    	logolight.setVisible(true);
    }
    @FXML
    void PlaySong() {
    	
    	timerStart();
    	mediaPlayer.play();
    	
    }
    
    @FXML
    void pauseSong() {
    	
    	timerStop();
    	mediaPlayer.pause();
    	
    }
    
   
    @FXML
    void playNext() {
    	if (trackPos < tracks.size() - 1) {
    		trackPos++;
    		mediaPlayer.stop();
    	
    		media = new Media(tracks.get(trackPos).toURI().toString());
        	
        	mediaPlayer = new MediaPlayer(media);
        	
        	songName.setText(tracks.get(trackPos).getName());
        	
        	PlaySong();
        	
    	} else {
    		
    		trackPos = 0;
    		mediaPlayer.stop();

    		if (true) {
    			timerStop();
    		}
    		
    		media = new Media(tracks.get(trackPos).toURI().toString());
        	
        	mediaPlayer = new MediaPlayer(media);
        	
        	songName.setText(tracks.get(trackPos).getName());
        	
    	}
    }
    	
    @FXML
    void playPrev() {
    	if (trackPos > 0)  {
    		trackPos--;
    		mediaPlayer.stop();
    	
    		if (true) {
    			timerStop();
    		}
    		
    		media = new Media(tracks.get(trackPos).toURI().toString());
        	
        	mediaPlayer = new MediaPlayer(media);
        	
        	songName.setText(tracks.get(trackPos).getName());
        	
        	PlaySong();
        	
    	} else {
    		
    		trackPos = tracks.size() - 1;
    		mediaPlayer.stop();
    		
    		if (true) {
    			timerStop();
    		}
    	
    		media = new Media(tracks.get(trackPos).toURI().toString());
        	
        	mediaPlayer = new MediaPlayer(media);
        	
        	songName.setText(tracks.get(trackPos).getName());
        	
    	}
    }

    @FXML
    void resetSong() {
    	
    	songProgres.setProgress(0);
    	mediaPlayer.seek(Duration.seconds(0));
    }
    
    public void timerStart() {
		timer = new Timer();
		
		task = new TimerTask() {

			@Override
			public void run() {
				double currenttime = mediaPlayer.getCurrentTime().toMillis();
				double endtime = media.getDuration().toMillis();
				//System.out.println(currenttime/endtime);
				songProgres.setProgress(currenttime/endtime);
				
				if (currenttime/endtime == 1){
					timerStop();
				}
			}
		};
		
		timer.scheduleAtFixedRate(task, 0, 50);
		
	}

    public void timerStop() {
		timer.cancel();
    }
    
}