package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import model.Connect4;
import model.Player;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Alex & Sento
 */
public class RegistroController implements Initializable {

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

    private boolean partida;
    private Player pl1;
    @FXML
    private JFXButton vb;
    @FXML
    private ImageView v;
    @FXML
    private ImageView nv;
    @FXML
    private JFXTextField text_vpass;

    private int from;
    private Stage st;
    private Menu_principalController thisController;
    @FXML
    private JFXButton regb;
    @FXML
    private AnchorPane pane;
    @FXML
    private Hyperlink sel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text_vpass.textProperty().bindBidirectional(text_pass.textProperty());
        regb.disableProperty().bind(Bindings.isEmpty(text_user.textProperty()).or(Bindings.isEmpty(text_pass.textProperty())).or(Bindings.isEmpty(text_mail.textProperty())).or(Bindings.isNull(fecha_nacimiento.valueProperty())));

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
            text_user.setEffect(colorAdjust);
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
            text_user.setEffect(colorAdjust);
            text_vpass.setEffect(colorAdjust);
            fecha_nacimiento.setEffect(colorAdjust);
            sel.setEffect(colorAdjust);

        }
    }

    public void initController(RegistroController registro) {
        this.registro = registro;
    }

    public void initData(Connect4 con4, int from) {
        cn4 = con4;
        this.from = from;
    }

    public void initData(Connect4 con4, Menu_principalController thisController, int from) {
        cn4 = con4;
        this.from = from;
        this.thisController = thisController;
    }

    public void initData(Connect4 con4, Player pl1, Stage st, Menu_principalController thisController, int fr) {
        cn4 = con4;
        this.pl1 = pl1;
        from = fr;
        this.st = st;
        this.thisController = thisController;
    }

    public void initData(Connect4 con4, Player pl1, Menu_principalController thisController, int fr) {
        cn4 = con4;
        this.pl1 = pl1;
        from = fr;
        this.thisController = thisController;
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
        selec_avatar.initTema(tema);
        selec_avatar.initController(registro);
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        selec_avatar.initStage(newStage, stage);
    }

    File f_avatard = new File("src/images/avatares/avatar10.png");
    private final Image avatard = new Image(f_avatard.toURI().toString());

    @FXML
    private void comprobarRegistro(MouseEvent event) throws IOException, Connect4DAOException {
        if (!cn4.exitsNickName(text_user.getText())) {
            msj_alerta.setText("");
        } else {
            msj_alerta.setText("??Usuario ya existente!");
        }

        if (!Player.checkNickName(text_user.getText())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error al registrarse");
            alert.setHeaderText("Error en el nombre de usuario");
            alert.setContentText("El nombre debe contener entre 6 y 15 caracteres, mayusculas y minusculas o los caracteres '-' '_'");

            alert.showAndWait();
            text_user.setText(null);
        } else {
            if (!Player.checkEmail(text_mail.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error al registrarse");
                alert.setHeaderText("Error en el correo electr??nico");
                alert.setContentText("El correo electr??nico no se corresponde con ningun correo existente");

                alert.showAndWait();
                text_mail.setText(null);

            } else {
                if (!Player.checkPassword(text_pass.getText())) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Error al registrarse");
                    alert.setHeaderText("Error en la contrase??a");
                    alert.setContentText(
                            "La contrase??a debe contener: \n -entre 8 y 20 caracteres\n -al menos una letra mayuscula\n -al menos una letra min??scula\n -al menos un d??gito\n -un car??cter especial del conjunto:\n"
                            + "!@#$%&*()-+=\n y no debe contener espacios en blanco");

                    alert.showAndWait();
                    text_pass.setText(null);
                } else {
                    if (fecha_nacimiento.getValue() == null) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Error al registrarse");
                        alert.setHeaderText("Error en la fecha de nacimiento");
                        alert.setContentText("Debes tener m??s de 12 a??os para poder crear una cuenta de Conecta4");

                        alert.showAndWait();
                    } else {
                        if (LocalDate.now().minusYears(12).getYear() < fecha_nacimiento.getValue().getYear()) {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Error al registrarse");
                            alert.setHeaderText("Error en la fecha de nacimiento");
                            alert.setContentText("Debes tener m??s de 12 a??os para poder crear una cuenta de Conecta4");

                            alert.showAndWait();
                        }
                    }
                }
            }
        }

        try {
            if (Player.checkNickName(text_user.getText())
                    && Player.checkPassword(text_pass.getText())
                    && Player.checkEmail(text_mail.getText())
                    && fecha_nacimiento != null && LocalDate.now().minusYears(12).getYear() >= fecha_nacimiento.getValue().getYear()) {
                if (avatarImg != null) {
                    cn4.registerPlayer(text_user.getText(), text_mail.getText(), text_pass.getText(), avatarImg, fecha_nacimiento.getValue(), 0);
                } else {
                    cn4.registerPlayer(text_user.getText(), text_mail.getText(), text_pass.getText(), avatard, fecha_nacimiento.getValue(), 0);
                }

                newPlayer = cn4.getPlayer(text_user.getText());
                if (newPlayer == null) {
                }
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                if (from == 1) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_doble.fxml"));
                    Parent newRoot = loader.load();

                    Partida_dobleController menu = loader.getController();
                    menu.initData(cn4, pl1, newPlayer);
                    menu.initMusic(mediaPlayer, music_check.isSelected());
                    Scene scene = new Scene(newRoot);
                    Stage newStage = new Stage();
                    newStage.setMinWidth(875);
                    newStage.setMinHeight(865);
                    newStage.setScene(scene);

                    newStage.show();
                    st.close();
                    stage.close();
                }
                if (from == 0) {
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

                    stage.close();
                }
                if (from == 2) {
                    thisController.initData(cn4, pl1, newPlayer);
                    thisController.initMusic(mediaPlayer, music_check.isSelected());
                    thisController.initPerfil(true);
                    stage.close();
                }
                if (from == 3) {
                    thisController.initData(cn4, newPlayer);
                    thisController.initMusic(mediaPlayer, music_check.isSelected());
                    thisController.initPerfil(true);
                    stage.close();
                }

            }
        } catch (Connect4DAOException connect4DAOException) {
        } catch (IOException iOException) {
        } catch (NullPointerException npe) {
        }
    }

    @FXML
    private void atras(MouseEvent event) throws IOException {
        if (from == 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent newRoot = loader.load();
            LoginController lg = loader.getController();
            lg.initMusic(mediaPlayer, music_check.isSelected());
            lg.initTema(tema);
            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();

        }
        if (from == 1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login_amigo.fxml"));
            Parent newRoot = loader.load();
            Login_amigoController loginAmigo = loader.getController();

            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();

            loginAmigo.initData(cn4, pl1, st, thisController);
            loginAmigo.initMusic(mediaPlayer, music_check.isSelected());
            loginAmigo.initTema(tema);
        }
        if (from == 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login_amigo.fxml"));
            Parent newRoot = loader.load();
            Login_amigoController loginAmigo = loader.getController();

            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();

            loginAmigo.initData(cn4, pl1, thisController);
            loginAmigo.initMusic(mediaPlayer, music_check.isSelected());
            loginAmigo.initTema(tema);

        }
        if (from == 3) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent newRoot = loader.load();

            LoginController ld = loader.getController();
            ld.initData(cn4, st, thisController);
            ld.initMusic(mediaPlayer, music_check.isSelected());
            ld.initTema(tema);
            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();
        }
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
            if (!cn4.exitsNickName(text_user.getText())) {
                msj_alerta.setText("");
            } else {
                msj_alerta.setText("??Usuario ya existente!");
            }

            if (!Player.checkNickName(text_user.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error al registrarse");
                alert.setHeaderText("Error en el nombre de usuario");
                alert.setContentText("El nombre debe contener entre 6 y 15 caracteres, mayusculas y minusculas o los caracteres '-' '_'");

                alert.showAndWait();
                text_user.setText(null);
            } else {
                if (!Player.checkEmail(text_mail.getText())) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Error al registrarse");
                    alert.setHeaderText("Error en el correo electr??nico");
                    alert.setContentText("El correo electr??nico no se corresponde con ningun correo existente");

                    alert.showAndWait();
                    text_mail.setText(null);

                } else {
                    if (!Player.checkPassword(text_pass.getText())) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Error al registrarse");
                        alert.setHeaderText("Error en la contrase??a");
                        alert.setContentText(
                                "La contrase??a debe contener: \n -entre 8 y 20 caracteres\n -al menos una letra mayuscula\n -al menos una letra min??scula\n -al menos un d??gito\n -un car??cter especial del conjunto:\n"
                                + "!@#$%&*()-+=\n y no debe contener espacios en blanco");

                        alert.showAndWait();
                        text_pass.setText(null);
                    } else {
                        if (fecha_nacimiento.getValue() == null) {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Error al registrarse");
                            alert.setHeaderText("Error en la fecha de nacimiento");
                            alert.setContentText("Debes tener m??s de 12 a??os para poder crear una cuenta de Conecta4");

                            alert.showAndWait();
                        } else {
                            if (LocalDate.now().minusYears(12).getYear() < fecha_nacimiento.getValue().getYear()) {
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Error al registrarse");
                                alert.setHeaderText("Error en la fecha de nacimiento");
                                alert.setContentText("Debes tener m??s de 12 a??os para poder crear una cuenta de Conecta4");

                                alert.showAndWait();
                            }
                        }
                    }
                }
            }

            try {
                if (Player.checkNickName(text_user.getText())
                        && Player.checkPassword(text_pass.getText())
                        && Player.checkEmail(text_mail.getText())
                        && fecha_nacimiento != null && LocalDate.now().minusYears(12).getYear() >= fecha_nacimiento.getValue().getYear()) {
                    if (avatarImg != null) {
                        cn4.registerPlayer(text_user.getText(), text_mail.getText(), text_pass.getText(), avatarImg, fecha_nacimiento.getValue(), 0);
                    } else {
                        cn4.registerPlayer(text_user.getText(), text_mail.getText(), text_pass.getText(), avatard, fecha_nacimiento.getValue(), 0);
                    }

                    newPlayer = cn4.getPlayer(text_user.getText());
                    if (newPlayer == null) {
                    }
                    final Node source = (Node) event.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();
                    if (from == 1) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_doble.fxml"));
                        Parent newRoot = loader.load();

                        Partida_dobleController menu = loader.getController();
                        menu.initData(cn4, pl1, newPlayer);
                        menu.initMusic(mediaPlayer, music_check.isSelected());
                        Scene scene = new Scene(newRoot);
                        Stage newStage = new Stage();
                        newStage.setMinWidth(875);
                        newStage.setMinHeight(865);
                        newStage.setScene(scene);

                        newStage.show();
                        st.close();
                        stage.close();
                    }
                    if (from == 0) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
                        Parent newRoot = loader.load();
                        Menu_principalController menu_p = loader.getController();

                        System.out.println(newPlayer);
                        menu_p.initData(cn4, newPlayer);
                        menu_p.initMusic(mediaPlayer, music_check.isSelected());
                        menu_p.initController(menu_p);
                        Scene scene = new Scene(newRoot);
                        Stage newStage = new Stage();
                        newStage.setScene(scene);
                        newStage.setResizable(false);

                        newStage.show();

                        stage.close();
                    }
                    if (from == 2) {
                        thisController.initData(cn4, pl1, newPlayer);
                        thisController.initMusic(mediaPlayer, music_check.isSelected());
                        thisController.initPerfil(true);
                        stage.close();
                    }
                    if (from == 3) {
                        thisController.initData(cn4, newPlayer);
                        thisController.initMusic(mediaPlayer, music_check.isSelected());
                        thisController.initPerfil(true);
                        stage.close();
                    }

                }
            } catch (Connect4DAOException connect4DAOException) {
            } catch (IOException iOException) {
            } catch (NullPointerException npe) {
            }
        }
    }

}
