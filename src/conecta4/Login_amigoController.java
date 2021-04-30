package conecta4;

import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;

import model.*;

/**
 * @author Alex & Sento
 */
public class Login_amigoController {
    @FXML
    private TextField text_user;
    @FXML
    private TextField text_pass;
    @FXML
    private Text warning_player1;
    
    private Connect4 cn4;
    private Player player1, player2, invitado, invitado2;
    private Stage oldStage;
    @FXML
    private ImageView flecha;
    @FXML
    private JFXToggleButton music_check;
    
    
    public void initData(Connect4 con4, Player mainPlayer, Stage st) {
        cn4 = con4;
        player1 = mainPlayer;
        oldStage = st;
        invitado = cn4.getPlayer("invitado");
        invitado2 = cn4.getPlayer("invitado2");
    }
    
    private MediaPlayer mediaPlayer;
    public void initMusic(MediaPlayer mp,boolean b) {
        mediaPlayer = mp;
        music_check.setSelected(b);
        music_check.selectedProperty().addListener(changeListener);
    }

    private ChangeListener changeListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            if (music_check.isSelected()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        }
    };
    

    @FXML
    private void log(MouseEvent event)  {
        player2 = cn4.loginPlayer(text_user.getText(), text_pass.getText());
        
        if(player2!=null){
        if(player2.equals(player1)) {
            warning_player1.setText(player1.getNickName() + " ya ha iniciado sesión.");
        }
        if (!player2.equals(player1) && !player2.equals(invitado) && !player2.equals(invitado2)) {
            try {
                // 1. Loader
                FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_doble.fxml"));
                Parent newRoot = loader.load();
                
                // 2. Controller, scene & stage
                Partida_dobleController menu = loader.getController();
                menu.initData(cn4, player1, player2);
                menu.initMusic(mediaPlayer,music_check.isSelected());
                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();
                newStage.setMinWidth(876); 
                newStage.setMinHeight(866); 
                newStage.setScene(scene);
               
                // 3. Mostrar la nueva ventana
                newStage.show();
                
                // 4. Cerrar la antigua ventana
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                oldStage.close();
                stage.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            }
        } else {
            warning_player1.setText("Usuario y/o contraseña no coinciden");
        }
        
        
    }
    
    
    @FXML
    private void recu(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recuperar.fxml"));
            Parent newRoot = loader.load();
            RecuperarController rc = loader.getController();

            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            rc.initData(cn4);

            newStage.initModality(Modality.APPLICATION_MODAL);
            
            newStage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    
    @FXML
    private void atras(MouseEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void invitado(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_doble.fxml"));
        Parent newRoot = loader.load();
        Partida_dobleController doble = loader.getController();
        doble.initData(cn4, player1, invitado);
        doble.initMusic(mediaPlayer,music_check.isSelected());
        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();
        newStage.setMinWidth(876); 
        newStage.setMinHeight(866);

        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        
        newStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        oldStage.close();
        stage.close(); 
    }
}
