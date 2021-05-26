package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;

/**
 * @author Alex & Sento
 */
public class Editar_perfilController {

    @FXML
    private JFXToggleButton music_check;
    @FXML
    private JFXPasswordField text_pass;
    @FXML
    private JFXTextField text_mail;
    @FXML
    private JFXDatePicker fecha_nacimiento;
    @FXML
    private ImageView flecha;
    @FXML
    private ImageView suAvatar;
    @FXML
    private Text msj_alerta;
    @FXML
    private Text error_name;
    @FXML
    private Text error_mail;
    @FXML
    private Text error_pass;
    @FXML
    private Text error_fecha;
    
    private MediaPlayer mediaPlayer;
    private Connect4 cn4;
    private Player newPlayer;
    
    private Editar_perfilController editar;
    
    private Image avatarImg;

    private Stage stage;
    private Menu_principalController menu;
    @FXML
    private Text nombreP1;
    private Player player1;
    
    
    public void initData(Connect4 con4, Stage st, Player p1, Menu_principalController controladorMenu) {
        cn4 = con4;
        stage = st;
        player1 = p1;
        menu = controladorMenu;
        nombreP1.setText(player1.getNickName());
        suAvatar.setImage(player1.getAvatar());
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
    private void comprobarRegistro(MouseEvent event) throws IOException {
        if (!Player.checkPassword(text_pass.getText())) {
            error_pass.setVisible(true);
        } else error_pass.setVisible(false);

        if (!Player.checkEmail(text_mail.getText())) {
            error_mail.setVisible(true);
        } else error_mail.setVisible(false);

        if (fecha_nacimiento.getValue() == null) {
            error_fecha.setText("Campo obligatorio");
            error_fecha.setVisible(true);
        } else error_fecha.setVisible(true);

        if (fecha_nacimiento.getValue().getYear() > 2009) {
            error_fecha.setText("Debes ser mayor de 12 años");
            error_fecha.setVisible(true);
        }
        if (Player.checkPassword(text_pass.getText())
            && Player.checkEmail(text_mail.getText())
            && fecha_nacimiento != null) {
            if (avatarImg != null) {
                try {
                    newPlayer.setPassword(text_pass.getText());
                    newPlayer.setEmail(text_mail.getText());
                    newPlayer.setBirthdate(fecha_nacimiento.getValue());
                } catch (Connect4DAOException ex) {}
            } else {
                try {
                    newPlayer.setPassword(text_pass.getText());
                    newPlayer.setEmail(text_mail.getText());
                    newPlayer.setBirthdate(fecha_nacimiento.getValue());
                    newPlayer.setAvatar(avatarImg);
                } catch (Connect4DAOException ex) {}
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
            Parent newRoot = loader.load();
            Menu_principalController menu_p = loader.getController();

            menu_p.initData(cn4, newPlayer);
            menu_p.initMusic(mediaPlayer, music_check.isSelected());
            menu_p.initController(menu_p);
            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setResizable(false);

            newStage.show();

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void seleccionarAvatar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("seleccionar_avatar.fxml"));
        Parent newRoot = loader.load();

        Seleccionar_avatarController selec_avatar = loader.getController();

        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();
        
        selec_avatar.initController(editar);
        newStage.setScene(scene);
        newStage.setResizable(false);

        newStage.show();

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        selec_avatar.initStage(newStage, stage);
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

