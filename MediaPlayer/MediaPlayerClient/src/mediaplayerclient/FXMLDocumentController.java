package mediaplayerclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class FXMLDocumentController implements Initializable, SocketListener {
    
    private MyClientSocket clientSocket;
    
    @FXML
    private ListView<String> movieListView;
     
    @FXML
    private Label movieInfo;
     
    private ObservableList<String> movieData;
    
    private boolean isMoviePlaying = false;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        movieData = FXCollections.observableArrayList();
        movieListView.setItems(movieData);
        movieListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        movieListView.setOnMouseClicked((Event event) -> {
            String selectedMovie
                    = movieListView.getSelectionModel().getSelectedItem();
            if (selectedMovie != null && !selectedMovie.equals("null")) {
                movieInfo.setText("Wybrano film do odtworzenia: "+selectedMovie);
                clientSocket.sendMessage("Play:"+selectedMovie);
            }
        });
        
        clientSocket = new MyClientSocket(this, 6666);
        clientSocket.connect();
    }  

    @Override
    public void onMessage(String line) {
        if(line.startsWith("ServerMovieList")){
            String movies = line.replace("ServerMovieList:","");
            String[] array = movies.split("\\|");
            movieData.clear();
            for(int i = 0; i < array.length; i++){
                movieData.add(array[i]);
            }
        }
        else  if(line.startsWith("Playing:")){
            String movie = line.replace("Playing:","");
            movieInfo.setText("Odtwarzany film: " + movie);
            isMoviePlaying = true;
        }
    }  
    
    @FXML
    private void handleReadMovieList(ActionEvent event) {
      if(clientSocket.isReady()){
          clientSocket.sendMessage("MovieList");
      }
    }
    
    public  void keyEventAction(KeyEvent keyEvent){
        System.out.println("controller: "+keyEvent.getCode());
        if(isMoviePlaying){
            if (keyEvent.getCode() == KeyCode.RIGHT) {
                clientSocket.sendMessage("Forward:5");
            }
            else if (keyEvent.getCode() == KeyCode.SPACE) {
               clientSocket.sendMessage("PlayPause");
            }
            else if (keyEvent.getCode() == KeyCode.LEFT) {
                clientSocket.sendMessage("Backward:5");
            }
        }
    }
    
   
}
