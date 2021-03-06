package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;

/**
 * @author Alex & Sento
 */
public class Editar_perfilController implements Initializable {

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
    private Text error_mail;
    @FXML
    private Text error_pass;
    @FXML
    private Text error_fecha;

    private MediaPlayer mediaPlayer;
    private Connect4 cn4;

    private Editar_perfilController editar;

    private Image avatarImg;

    private Stage stage;
    private Menu_principalController menu;
    @FXML
    private Text nombreP1;
    private Player player1;
    private Player player2;
    @FXML
    private JFXButton vb;
    @FXML
    private ImageView v;
    @FXML
    private ImageView nv;
    @FXML
    private JFXTextField text_vpass;
    private boolean sec;
    @FXML
    private JFXButton modb;
    @FXML
    private AnchorPane pane;
    @FXML
    private Hyperlink sel;

    public void initialize(URL url, ResourceBundle rb) {
        text_vpass.textProperty().bindBidirectional(text_pass.textProperty());
        modb.disableProperty().bind((Bindings.isEmpty(text_pass.textProperty())).or(Bindings.isEmpty(text_mail.textProperty())).or(Bindings.isNull(fecha_nacimiento.valueProperty())));
    }
    private boolean tema;

    public void initTema(boolean b) {
        tema = b;
        if (!b) {
            pane.setStyle(" -fx-background-color: #14213c;");
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setContrast(0.2);
            colorAdjust.setHue(0);
            colorAdjust.setBrightness(0.85);
            colorAdjust.setSaturation(0);
            text_mail.setEffect(colorAdjust);
            text_pass.setEffect(colorAdjust);
            nombreP1.setEffect(colorAdjust);
            text_vpass.setEffect(colorAdjust);
            fecha_nacimiento.setEffect(colorAdjust);
            sel.setEffect(colorAdjust);

        } else {
            pane.setStyle("-fx-background-color: #EBBCE1;");
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setContrast(1);
            colorAdjust.setHue(1);
            colorAdjust.setBrightness(-0.85);
            colorAdjust.setSaturation(1);
            text_mail.setEffect(colorAdjust);
            text_pass.setEffect(colorAdjust);
            nombreP1.setEffect(colorAdjust);
            text_vpass.setEffect(colorAdjust);
            fecha_nacimiento.setEffect(colorAdjust);
            sel.setEffect(colorAdjust);

        }
    }

    public void initData(Connect4 con4, Stage st, Player p1, Menu_principalController controladorMenu, Editar_perfilController editar) {
        cn4 = con4;
        stage = st;
        player1 = p1;
        menu = controladorMenu;
        nombreP1.setText(player1.getNickName());
        initAvatar(p1.getAvatar());
        this.editar = editar;
        text_mail.setText(p1.getEmail());
        fecha_nacimiento.setValue(p1.getBirthdate());
        text_pass.setText(p1.getPassword());
        sec = true;
    }

    public void initData(Connect4 con4, Stage st, Player p1, Player pl2, Menu_principalController controladorMenu, Editar_perfilController editar, boolean sec) {
        cn4 = con4;
        stage = st;
        player1 = p1;
        player2 = pl2;
        menu = controladorMenu;
        if (sec) {
            nombreP1.setText(player1.getNickName());
            initAvatar(p1.getAvatar());
            text_mail.setText(p1.getEmail());
            fecha_nacimiento.setValue(p1.getBirthdate());
            text_pass.setText(p1.getPassword());
        } else {
            nombreP1.setText(player2.getNickName());
            initAvatar(pl2.getAvatar());
            text_mail.setText(player2.getEmail());
            fecha_nacimiento.setValue(player2.getBirthdate());
            text_pass.setText(player2.getPassword());
        }

        this.editar = editar;

        this.sec = sec;
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
        if (!Player.checkEmail(text_mail.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error al registrarse");
            alert.setHeaderText("Error en el correo electr??nico");
            alert.setContentText("El correo electr??nico no se corresponde con ningun correo existente");

            alert.showAndWait();
            text_mail.setText(null);

        } else {
            if (!Player.checkPassword(text_pass.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error al registrarse");
                alert.setHeaderText("Error en la contrase??a");
                alert.setContentText(
                        "La contrase??a debe contener: \n -entre 8 y 20 caracteres\n -al menos una letra mayuscula\n -al menos una letra min??scula\n -al menos un d??gito\n -un car??cter especial del conjunto:\n"
                        + "!@#$%&*()-+=\n y no debe contener espacios en blanco");

                alert.showAndWait();
                text_pass.setText(null);
            } else {
                if (fecha_nacimiento.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error al registrarse");
                    alert.setHeaderText("Error en la fecha de nacimiento");
                    alert.setContentText("Debes tener m??s de 12 a??os para poder crear una cuenta de Conecta4");

                    alert.showAndWait();
                } else {
                    if (LocalDate.now().minusYears(12).getYear() < fecha_nacimiento.getValue().getYear()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error al registrarse");
                        alert.setHeaderText("Error en la fecha de nacimiento");
                        alert.setContentText("Debes tener m??s de 12 a??os para poder crear una cuenta de Conecta4");

                        alert.showAndWait();
                    }
                }
            }
        }
        try {
            if (Player.checkPassword(text_pass.getText())
                    && Player.checkEmail(text_mail.getText())
                    && fecha_nacimiento != null && LocalDate.now().minusYears(12).getYear() >= fecha_nacimiento.getValue().getYear()) {
                if (sec) {
                    player1.setPassword(text_pass.getText());
                    player1.setEmail(text_mail.getText());
                    player1.setBirthdate(fecha_nacimiento.getValue());
                    player1.setAvatar(avatarImg);
                } else {
                    player2.setPassword(text_pass.getText());
                    player2.setEmail(text_mail.getText());
                    player2.setBirthdate(fecha_nacimiento.getValue());
                    player2.setAvatar(avatarImg);
                }
                if (player2 == null) {
                    menu.initData(cn4, player1);
                } else {
                    menu.initData(cn4, player1, player2);
                }
                menu.initMusic(mediaPlayer, music_check.isSelected());
                menu.initPerfil(sec);

                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        } catch (Connect4DAOException ex) {
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
        selec_avatar.setEdit();
        newStage.setScene(scene);
        newStage.setResizable(false);

        newStage.show();

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        selec_avatar.initStage(newStage, stage);
        selec_avatar.initTema(tema);
    }

    @FXML
    private void atras(MouseEvent event) throws IOException {
        menu.initMusic(mediaPlayer, music_check.isSelected());
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void show(MouseEvent event) {
        if (text_pass.isVisible()) {
            text_pass.setVisible(false);
            text_vpass.setVisible(true);
            v.setVisible(false);
            nv.setVisible(true);
        } else {
            text_pass.setVisible(true);
            text_vpass.setVisible(false);
            v.setVisible(true);
            nv.setVisible(false);
        }
    }

    @FXML
    private void comprobarRegistrok(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (!Player.checkEmail(text_mail.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error al registrarse");
                alert.setHeaderText("Error en el correo electr??nico");
                alert.setContentText("El correo electr??nico no se corresponde con ningun correo existente");

                alert.showAndWait();
                text_mail.setText(null);

            } else {
                if (!Player.checkPassword(text_pass.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error al registrarse");
                    alert.setHeaderText("Error en la contrase??a");
                    alert.setContentText(
                            "La contrase??a debe contener: \n -entre 8 y 20 caracteres\n -al menos una letra mayuscula\n -al menos una letra min??scula\n -al menos un d??gito\n -un car??cter especial del conjunto:\n"
                            + "!@#$%&*()-+=\n y no debe contener espacios en blanco");

                    alert.showAndWait();
                    text_pass.setText(null);
                } else {
                    if (fecha_nacimiento.getValue() == null) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error al registrarse");
                        alert.setHeaderText("Error en la fecha de nacimiento");
                        alert.setContentText("Debes tener m??s de 12 a??os para poder crear una cuenta de Conecta4");

                        alert.showAndWait();
                    } else {
                        if (LocalDate.now().minusYears(12).getYear() < fecha_nacimiento.getValue().getYear()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error al registrarse");
                            alert.setHeaderText("Error en la fecha de nacimiento");
                            alert.setContentText("Debes tener m??s de 12 a??os para poder crear una cuenta de Conecta4");

                            alert.showAndWait();
                        }
                    }
                }
            }
            try {
                if (Player.checkPassword(text_pass.getText())
                        && Player.checkEmail(text_mail.getText())
                        && fecha_nacimiento != null && LocalDate.now().minusYears(12).getYear() >= fecha_nacimiento.getValue().getYear()) {
                    if (sec) {
                        player1.setPassword(text_pass.getText());
                        player1.setEmail(text_mail.getText());
                        player1.setBirthdate(fecha_nacimiento.getValue());
                        player1.setAvatar(avatarImg);
                    } else {
                        player2.setPassword(text_pass.getText());
                        player2.setEmail(text_mail.getText());
                        player2.setBirthdate(fecha_nacimiento.getValue());
                        player2.setAvatar(avatarImg);
                    }
                    if (player2 == null) {
                        menu.initData(cn4, player1);
                    } else {
                        menu.initData(cn4, player1, player2);
                    }
                    menu.initMusic(mediaPlayer, music_check.isSelected());
                    menu.initPerfil(sec);

                    final Node source = (Node) event.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                }

            } catch (Connect4DAOException ex) {
            }

        }

    }
}
