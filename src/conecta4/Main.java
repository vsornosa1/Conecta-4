package conecta4;

import java.io.File;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 * @author Alex & Sento
 */
public class Main extends Application {
    
    private MediaPlayer mediaPlayer;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent newRoot = loader.load();
//        newRoot.getStylesheets().add("modo_dia.css");
        Scene scene = new Scene(newRoot);


        stage.setScene(scene);
                
        
        stage.setResizable(false);
        LoginController lg=loader.getController();
        initMusic();
        lg.initMusic(mediaPlayer, false);
        stage.show();
    }
    
    private void initMusic() {
        String musicFile = "src/music/layton.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
