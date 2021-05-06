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

    private RegistroController registro;
    @FXML
    private Text error_name;
    @FXML
    private Text error_mail;
    @FXML
    private Text error_pass;
    @FXML
    private Text error_fecha;

    public void initController(RegistroController registro) {
        this.registro = registro;
    }

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
        selec_avatar.initController(registro);
        newStage.setScene(scene);
        newStage.setResizable(false);

        newStage.show();

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        selec_avatar.initStage(stage);
    }

    File f_avatard = new File("src/images/avatares/avatar10.png");
    private final Image avatard = new Image(f_avatard.toURI().toString());

    @FXML
    private void comprobarRegistro(MouseEvent event) throws IOException, Connect4DAOException {
        if (!cn4.exitsNickName(text_user.getText())) {
            msj_alerta.setText("");
        } else msj_alerta.setText("¡Usuario ya existente!");
            
        if (!Player.checkNickName(text_user.getText())) {
            error_name.setVisible(true);
        } else error_name.setVisible(false);

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
        if (Player.checkNickName(text_user.getText())
            && Player.checkPassword(text_pass.getText())
            && Player.checkEmail(text_mail.getText())
            && fecha_nacimiento != null) {

            if (avatarImg != null) {
                cn4.registerPlayer(text_user.getText(), text_mail.getText(), text_pass.getText(), avatarImg, fecha_nacimiento.getValue(), 0);
            } else {
                cn4.registerPlayer(text_user.getText(), text_mail.getText(), text_pass.getText(), fecha_nacimiento.getValue(), 0);
            }

            newPlayer = cn4.getPlayer(text_user.getText());
            if(newPlayer==null)System.out.println("asfasdffdsa");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
            Parent newRoot = loader.load();
            Menu_principalController menu_p = loader.getController();

            System.out.println(newPlayer);
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
