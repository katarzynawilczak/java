package mediaplayerclient;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MediaPlayerClient extends Application {

    private static FXMLDocumentController myControllerHandle;
    
    @Override
    public void start(Stage stage) throws Exception {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        myControllerHandle = (FXMLDocumentController)loader.getController();

        Scene scene = new Scene(root);
        
        scene.addEventFilter(KeyEvent.KEY_PRESSED,
            new EventHandler<KeyEvent>() {
                public void handle(final KeyEvent keyEvent) {   
                    if (keyEvent.getCode() == KeyCode.RIGHT) {
                        myControllerHandle.keyEventAction(keyEvent);
                        keyEvent.consume();
                    }
                    else if (keyEvent.getCode() == KeyCode.SPACE) {
                        myControllerHandle.keyEventAction(keyEvent);
                        keyEvent.consume();
                    }
                    else if (keyEvent.getCode() == KeyCode.LEFT) {
                       myControllerHandle.keyEventAction(keyEvent);
                       keyEvent.consume();
                    }
                };
            });

        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
