package mediaplayerserver;

import javafx.scene.input.MouseEvent; 
import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


public class MediaPlayerServer extends Application implements SocketListener{
    
    private MediaPlayer player;
    private MyServerSocket serverSocket;
    private MediaView mediaView;
    
    @Override
    public void start(Stage primaryStage) {
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 400);
          
        mediaView = new MediaView(player);
        root.getChildren().add(mediaView);
         
        primaryStage.setTitle("MediaPlayerServer");
        primaryStage.setScene(scene);
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent doubleClick) {
                if(doubleClick.getClickCount()==2){
                    primaryStage.setFullScreen(true);
                }
            }
        
        }); 
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
        
        serverSocket = new MyServerSocket(this, 6666);
        serverSocket.connect();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onMessage(String line) {
       
         System.out.println("Odebrana wiadomosc: " + line);
         if(line.equals("MovieList")){
             serverSocket.sendMessage("ServerMovieList:krolik.mp4|fireworks.mp4|star.mp4");
         }
         else if(line.startsWith("Play:")){
            String movie = line.replace("Play:","");
            playMovie(movie);
         }
         else if(line.startsWith("PlayPause")){
              if (player != null) {
                  MediaPlayer.Status currentStatus = player.getStatus();
                  if (currentStatus == MediaPlayer.Status.PAUSED){
                      player.play();
                  }
                  else{
                      player.pause();
                  }
              }
         }
         else if(line.startsWith("Forward:")){
              if (player != null) {
                   String duration = line.replace("Forward:","");
                   int durationInt = Integer.valueOf(duration);
                   durationInt = durationInt * 1000;
                   Duration time = player.getCurrentTime();
                   time = time.add(new Duration(durationInt));
                   player.seek(time);
              }
         }
         else if(line.startsWith("Backward:")){
              if (player != null) {
                   String duration = line.replace("Backward:", "");
                   int durationInt = Integer.valueOf(duration);
                   durationInt = durationInt * 1000;
                   Duration time = player.getCurrentTime();
                   time = time.add(new Duration(durationInt).negate());
                   player.seek(time);
              }
         }   
    }
        
    private void playMovie(String movieName){
        File file = new File(movieName);
        String MEDIA_URL = file.toURI().toString();
        Media media = new Media(MEDIA_URL);
        if (player != null) {
            player.stop();
            player = null;
        }
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.play();
        serverSocket.sendMessage("Playing:"+movieName);
    }
}
