package conecta4;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;

import model.*;

/**
 * @author Alex & Sento
 */
public class Login_amigoController implements Initializable {

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
    private boolean perfil;
    private Menu_principalController controller;
    @FXML
    private JFXButton inv_bot;

    private boolean partida;
    @FXML
    private JFXTextField text_vpass;
    @FXML
    private JFXButton vb;
    @FXML
    private ImageView v;
    @FXML
    private ImageView nv;

    private int from;
    @FXML
    private JFXButton logb;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text_vpass.textProperty().bindBidirectional(text_pass.textProperty());
        logb.disableProperty().bind(Bindings.isEmpty(text_user.textProperty()).or(Bindings.isEmpty(text_pass.textProperty())));

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

    public void initData(Connect4 con4, Player mainPlayer, Stage st, Menu_principalController controller) {
        cn4 = con4;
        player1 = mainPlayer;
        oldStage = st;
        this.controller = controller;
        invitado = cn4.getPlayer("invitado");
        invitado2 = cn4.getPlayer("invitado2");
        perfil = false;
        this.partida = true;
        from = 1;
    }

    public void initData(Connect4 con4, Player mainPlayer, Menu_principalController controller) {
        cn4 = con4;
        player1 = mainPlayer;
        this.controller = controller;
        invitado = cn4.getPlayer("invitado");
        invitado2 = cn4.getPlayer("invitado2");
        perfil = true;
        inv_bot.setDisable(true);
        this.partida = false;
        from = 2;
    }

    private MediaPlayer mediaPlayer;

    public void initMusic(MediaPlayer mp, boolean b) {
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
    private void log(MouseEvent event) {
        try {
            player2 = cn4.loginPlayer(text_user.getText(), text_pass.getText());
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            if (!perfil) {
                if (player2 != null) {
                    if (player1.equals(player2)) {
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
                            menu.initMusic(mediaPlayer, music_check.isSelected());
                            Scene scene = new Scene(newRoot);
                            Stage newStage = new Stage();
                            newStage.setMinWidth(875);
                            newStage.setMinHeight(865);
                            newStage.setScene(scene);

                            // 3. Mostrar la nueva ventana
                            newStage.show();

                            // 4. Cerrar la antigua ventana
                            oldStage.close();
                            stage.close();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                } else {
                    warning_player1.setText("Usuario y/o contraseña no coinciden");
                }
            } else {
                if (player1.equals(player2)) {
                    warning_player1.setText(player1.getNickName() + " ya ha iniciado sesión.");
                } else {
                    controller.initData(cn4, player1, player2);
                    controller.initMusic(mediaPlayer, music_check.isSelected());
                    controller.initPerfil(true);
                    stage.close();
                }
            }
        } catch (Exception e) {
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
        controller.initMusic(mediaPlayer, music_check.isSelected());
        controller.initTema(tema);
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
        doble.initMusic(mediaPlayer, music_check.isSelected());
        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();
        newStage.setMinWidth(875);
        newStage.setMinHeight(865);

        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);

        newStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        oldStage.close();
        stage.close();
    }

    @FXML
    private void reg(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registro.fxml"));
        Parent newRoot = loader.load();

        RegistroController registro = loader.getController();
        registro.initController(registro);
        if (from == 1) {
            registro.initData(cn4, player1, oldStage, controller, from);
        }
        if (from == 2) {
            registro.initData(cn4, player1, controller, from);
        }
        registro.initMusic(mediaPlayer, music_check.isSelected());
        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();

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
}
