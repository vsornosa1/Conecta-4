/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conecta4;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;

/**
 * @author Alex & Sento
 */
public class Confirmar_cierreController {
    
    @FXML
    private ImageView flecha;
    @FXML
    private JFXButton boton_cerrar;
    @FXML
    private Text pregunta;
    
    private Stage stage;
    private Player player;
    private Connect4 cn4;
    private MediaPlayer mediaPlayer;
    private boolean check;
    @FXML
    private AnchorPane pane;
    
    public void initData(Connect4 con4, Player p, Stage st) throws IOException {
        cn4 = con4;
        player = p;
        stage = st;
        pregunta.setText("¿Seguro que quieres cerrar sesión, " + player.getNickName() + "?");
    }
    private boolean tema;
    
    public void initTema(boolean b) {
        tema = b;
        if (!b) {
            pane.setStyle(" -fx-background-color: #14213c;");
        } else {
            pane.setStyle("-fx-background-color: #EBBCE1;");
        }
    }
    
    public void initMusic(MediaPlayer mp, boolean check) {
        mediaPlayer = mp;
        this.check = check;
    }
    
    @FXML
    private void cerrar_sesion(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent newRoot = loader.load();
        LoginController lg = loader.getController();
        lg.initMusic(mediaPlayer, check);
        lg.initTema(tema);
        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();
        
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        this.stage.close();
        stage.close();
    }
    
    @FXML
    private void atras(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
