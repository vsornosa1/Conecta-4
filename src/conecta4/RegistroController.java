package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import model.Connect4;
import model.Player;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Alex & Sento
 */
public class RegistroController {
    private MediaPlayer mediaPlayer;
    private Connect4 cn4;
    private Player newPlayer;
    
    @FXML
    private JFXToggleButton music_check;
    @FXML
    private JFXTextField text_user;
    @FXML
    private JFXPasswordField text_pass;
    @FXML
    private JFXTextField text_mail;
    @FXML
    private JFXDatePicker fecha_nacimiento;
    private Image newAvatar;
    @FXML
    private ImageView flecha;
    @FXML
    private ImageView suAvatar;
    private Image avatarImg;
    @FXML
    private Text msj_alerta;

    
    public void initData(Connect4 con4) {
        cn4 = con4;
    }
    
    void initAvatar(Image avatarImg) {
        suAvatar.setImage(avatarImg);
        this.avatarImg = avatarImg;
    }
    
    
    public void initMusic(MediaPlayer mp, boolean check) {
        mediaPlayer = mp;
        music_check.setSelected(check);
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
    private void seleccionarAvatar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("seleccionar_avatar.fxml"));
        Parent newRoot = loader.load();

        Seleccionar_avatarController selec_avatar = loader.getController();
        
        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();
        selec_avatar.initStage(newStage);
        
        newStage.setScene(scene);
        newStage.setResizable(false);

        newStage.show();
        
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        selec_avatar.initStage(stage);
    }

    File f_avatard = new File("src/avatars/avatar1.png"); 
    private final Image avatard = new Image(f_avatard.toURI().toString());
    
    @FXML
    private void comprobarRegistro(MouseEvent event) throws IOException, Connect4DAOException {
        //newPlayer = new Player(text_user.getText(), text_pass.getText(), text_mail.getText(), avatarImg, fecha_nacimiento.getValue(), 0);

        if(!cn4.exitsNickName(text_user.getText())) {
            if(newPlayer.checkNickName(text_user.getText())) {
                
            } else {
                msj_alerta.setText("¡Formato de usuario no valido!");
            }
        } else {
            msj_alerta.setText("¡Usuario ya existente!");
        }
        
        if(newPlayer.checkEmail(text_mail.getText())) {
                    if (newPlayer.checkPassword(text_pass.getText())) {
//                        if () {
//                        
//                        } else {
//                            msj_alerta.setText("¡Formato de contraseña no valido!");
//                        }
                        msj_alerta.setText("");
                        //cn4.registerPlayer(text_user.getText(), text_pass.getText(), text_mail.getText(), avatarImg, fecha_nacimiento.getValue(), 0);
                        System.out.println("guvubvu");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
                        Parent newRoot = loader.load();

                        Menu_principalController menu_p = loader.getController();

                        menu_p.initData(cn4, newPlayer);
                        menu_p.initMusic(mediaPlayer, music_check.isSelected());
                        Scene scene = new Scene(newRoot);
                        Stage newStage = new Stage();
                        newStage.setScene(scene);
                        newStage.setResizable(false);

                        newStage.show();

                        final Node source = (Node) event.getSource();
                        final Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();
                    } else {
                        msj_alerta.setText("¡Formato de contraseña no valido!");
                    }
                } else {
                    msj_alerta.setText("¡Formato de correo no valido!");
                }
    }

    @FXML
    private void atras(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent newRoot = loader.load();
        LoginController lg = loader.getController();
        lg.initMusic(mediaPlayer, music_check.isSelected());
        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
