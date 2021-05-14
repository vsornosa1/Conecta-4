package conecta4;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;

import model.*;

/**
 * @author Alex & Sento
 */
public class Menu_principalController implements Initializable {

    private Connect4 cn4;
    private Player player1, player2, invitado;

    @FXML
    private Text aplauso;

    File f_avatar1 = new File("src/avatars/avatar1.png");
    File f_avatar2 = new File("src/avatars/avatar2.png");
    File f_avatar3 = new File("src/avatars/avatar3.png");
    File f_avatar4 = new File("src/avatars/avatar4.png");
    File f_avatardef = new File("src/avatars/default.png");
    private ImageView avatar;

    @FXML
    private ImageView avatar11, avatar111;
    private Text puntos_player;
    @FXML
    private ImageView avatar_player1;
    @FXML
    private ImageView avatar_player2;
    @FXML
    private Hyperlink link_cerrar_sesion;
    @FXML
    private AnchorPane music;

    @FXML
    private JFXToggleButton music_check;

    private MediaPlayer mediaPlayer;

    private Menu_principalController thisController;
    @FXML
    private Text user_perfil;
    @FXML
    private ImageView avatar_perfil;
    @FXML
    private ImageView cambiarAvatar2_perfil;
    @FXML
    private Text cambiarJ2_perfil;
    @FXML
    private Text contraseña_perfil;
    @FXML
    private Text mail_perfil;
    @FXML
    private Text cumpleaños_perfil;
    @FXML
    private Line lineaJ2_perfil;
    private Player playerAux;
    private boolean perf;
    @FXML
    private JFXButton ed_bot;
    @FXML
    private JFXButton change_bot;
    @FXML
    private JFXToggleButton music_check1;
    @FXML
    private TableView<Player> table;
    @FXML
    private TableColumn<Player, String> name;
    @FXML
    private TableColumn<Player, Integer> punt;

    ObservableList<Player> observablePlayers;
    @FXML
    private JFXToggleButton music_check2;
    @FXML
    private JFXToggleButton music_check3;
    @FXML
    private JFXDatePicker date_ini;
    @FXML
    private JFXDatePicker date_fin;
    @FXML
    private TableView<Player> historial;
    @FXML
    private Hyperlink avanzado;
    @FXML
    private Hyperlink basicas;
    @FXML
    private TableColumn<Player, String> name_2;
    @FXML
    private TableColumn<Player, Integer> punt_2;
    @FXML
    private JFXRadioButton ambas;
    @FXML
    private JFXRadioButton vic;
    @FXML
    private JFXRadioButton lose;
    @FXML
    private TextField filtro_nombre;
    @FXML
    private JFXButton ed_bot1;
    @FXML
    private Text text_v;
    @FXML
    private Text text_d;
    @FXML
    private Text text_a;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date_ini.setPromptText(LocalDate.now().toString());
        date_fin.setPromptText(LocalDate.now().toString());
    }

    public void initController(Menu_principalController controller) {
        thisController = controller;
    }

    public void initMusic(MediaPlayer mp, boolean b) {
        mediaPlayer = mp;
        music_check.setSelected(b);
        music_check.selectedProperty().addListener(changeListener);
        music_check1.selectedProperty().bindBidirectional(music_check.selectedProperty());
        music_check2.selectedProperty().bindBidirectional(music_check.selectedProperty());
        music_check3.selectedProperty().bindBidirectional(music_check.selectedProperty());

    }

    // Login/1 Jugador -> Menu principal
    public void initData(Connect4 con4, Player p1) {
        cn4 = con4;
        player1 = p1;
        perf = true;
        aplauso.setText("Bienvenido/a " + player1.getNickName());
        avatar_player1.setImage(player1.getAvatar());
        avatar11.setImage(player1.getAvatar());
        avatar111.setImage(player1.getAvatar());
        invitado = cn4.getPlayer("invitado");

        if (player1.equals(invitado)) {
            initPerfil(true);

            link_cerrar_sesion.setText("Iniciar sesión");
        } else {
            initPerfil(true);
            link_cerrar_sesion.setText("Cerrar sesión");
        }
        ArrayList<Player> jugadores = cn4.getConnect4Ranking();
        observablePlayers = FXCollections.observableList(cn4.getConnect4Ranking());
        name.setCellValueFactory(new PropertyValueFactory<Player, String>("nickName"));
        punt.setCellValueFactory(new PropertyValueFactory<Player, Integer>("points"));
        observablePlayers.remove(cn4.getPlayer("invitado"));
        table.setItems(observablePlayers);

    }

    // 2 Jugadores -> Menu principal
    public void initData(Connect4 con4, Player p1, Player p2) {
        cn4 = con4;
        player1 = p1;
        player2 = p2;
        perf = true;
        aplauso.setText("Bienvenidos/as " + player1.getNickName() + ", " + player2.getNickName());
        avatar_player1.setImage(player1.getAvatar());
        avatar_player2.setImage(player2.getAvatar());
        avatar11.setImage(player1.getAvatar());
        avatar111.setImage(player1.getAvatar());
        initPerfil(true);

    }

    private void initPerfil(boolean p) {

        if (player1 == invitado) {
            ed_bot.setDisable(true);
            avatar_perfil.setImage(player1.getAvatar());
            user_perfil.setText(player1.getNickName());
            contraseña_perfil.setText("¡Inicia sesión para ver tu perfil!");
            mail_perfil.setText("");
            cumpleaños_perfil.setText("");
            change_bot.setOpacity(1);
        } else if (player2 == null) {
            change_bot.setOpacity(1);
            ed_bot.setDisable(false);
            avatar_perfil.setImage(player1.getAvatar());
            user_perfil.setText("@" + player1.getNickName());
            contraseña_perfil.setText("Contraseña: " + player1.getPassword());
            mail_perfil.setText("Correo electrónico: " + player1.getEmail());
            cumpleaños_perfil.setText("Fecha de Nacimiento: " + player1.getBirthdate());
        } else {
            ed_bot.setDisable(false);
            change_bot.setOpacity(0);
            if (p) {
                avatar_perfil.setImage(player1.getAvatar());
                user_perfil.setText("@" + player1.getNickName());
                contraseña_perfil.setText("Contraseña: " + player1.getPassword());
                mail_perfil.setText("Correo electrónico: " + player1.getEmail());
                cumpleaños_perfil.setText("Fecha de Nacimiento: " + player1.getBirthdate());
                lineaJ2_perfil.setOpacity(1);
                cambiarAvatar2_perfil.setImage(player2.getAvatar());
                cambiarJ2_perfil.setText("Ver perfil de " + player2.getNickName());
            } else {
                avatar_perfil.setImage(player2.getAvatar());
                user_perfil.setText("@" + player2.getNickName());
                contraseña_perfil.setText("Contraseña: " + player2.getPassword());
                mail_perfil.setText("Correo electrónico: " + player2.getEmail());
                cumpleaños_perfil.setText("Fecha de Nacimiento: " + player2.getBirthdate());
                lineaJ2_perfil.setOpacity(1);
                cambiarAvatar2_perfil.setImage(player1.getAvatar());
                cambiarJ2_perfil.setText("Ver perfil de " + player1.getNickName());
            }

        }

    }

    @FXML
    private void cambiar_perfil(MouseEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        final Stage st = (Stage) source.getScene().getWindow();
        if (player1 == invitado) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent newRoot = loader.load();

            LoginController ld = loader.getController();
            ld.initData(cn4, st, thisController);
            ld.initMusic(mediaPlayer, music_check.isSelected());
            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();
        } else if (player2 == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login_amigo.fxml"));
            Parent newRoot = loader.load();
            Login_amigoController loginAmigo = loader.getController();

            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();

            loginAmigo.initData(cn4, player1, thisController);
            loginAmigo.initMusic(mediaPlayer, music_check.isSelected());
        } else {
            perf = !perf;
            initPerfil(perf);
        }
    }

    @FXML
    private void editar_perfil(MouseEvent event) {
        //que aparezca el nuevo controller tipo register para actualizar los datos
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
    private void partida_solo(MouseEvent event
    ) {
        final Node sr = (Node) event.getSource();
        final Stage st = (Stage) sr.getScene().getWindow();
        if (player2 == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_cpu.fxml"));
                Parent newRoot = loader.load();
                Partida_cpuController solo = loader.getController();

                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                solo.initData(cn4, player1);
                solo.initMusic(mediaPlayer, music_check.isSelected());

                newStage.setMinWidth(875);
                newStage.setMinHeight(865);
                newStage.setScene(scene);
                newStage.show();

                st.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("selec_player.fxml"));
                Parent newRoot = loader.load();
                Selec_playerController selec = loader.getController();

                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                selec.initData(cn4, player1, player2, st);
                selec.initMusic(mediaPlayer, music_check.isSelected());
                newStage.setScene(scene);
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setResizable(false);
                newStage.show();

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void partida_doble(MouseEvent event
    ) {
        final Node source = (Node) event.getSource();
        final Stage st = (Stage) source.getScene().getWindow();
        if (player2 == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login_amigo.fxml"));
                Parent newRoot = loader.load();
                Login_amigoController loginAmigo = loader.getController();

                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                newStage.setScene(scene);
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setResizable(false);
                newStage.show();

                loginAmigo.initData(cn4, player1, st);
                loginAmigo.initMusic(mediaPlayer, music_check.isSelected());
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                // 1. Loader
                FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_doble.fxml"));
                Parent newRoot = loader.load();

                // 2. Controller, scene & stage
                Partida_dobleController menu = loader.getController();
                Scene scene = new Scene(newRoot);
                Stage newStage2 = new Stage();

                menu.initData(cn4, player1, player2);
                menu.initMusic(mediaPlayer, music_check.isSelected());

                newStage2.setMinWidth(876);
                newStage2.setMinHeight(866);
                newStage2.setScene(scene);

                // 3. Mostrar la nueva ventana
                newStage2.show();

                // 4. Cerrar la antigua ventana
                st.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void cerrar_sesion(MouseEvent event) throws IOException {
        if (player2 == null) {
            if (player1.equals(cn4.getPlayer("invitado"))) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent newRoot = loader.load();

                final Node sr = (Node) event.getSource();
                final Stage st = (Stage) sr.getScene().getWindow();

                LoginController ld = loader.getController();
                ld.initData(cn4, st, thisController);
                ld.initMusic(mediaPlayer, music_check.isSelected());
                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                newStage.setScene(scene);
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setResizable(false);
                newStage.show();

                //st.close(); 
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmar_cierre.fxml"));
                Parent newRoot = loader.load();

                Confirmar_cierreController confirmCierre = loader.getController();
                confirmCierre.initMusic(mediaPlayer, music_check.isSelected());
                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                newStage.setScene(scene);
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setResizable(false);
                newStage.show();
                final Node sr = (Node) event.getSource();
                final Stage st = (Stage) sr.getScene().getWindow();
                confirmCierre.initData(cn4, player1, st);
            }
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cerrar_sesion.fxml"));
            Parent newRoot = loader.load();

            Cerrar_sesionController cr = loader.getController();

            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();
            final Node sr = (Node) event.getSource();
            final Stage st = (Stage) sr.getScene().getWindow();
            cr.initData(cn4, player1, player2, st);
        }
    }

    @FXML
    private void avanzado(MouseEvent event) {
        ambas.setSelected(true);
        vic.setSelected(false);
        lose.setSelected(false);
        text_a.setVisible(true);
        text_d.setVisible(true);
        text_v.setVisible(true);
        ambas.setVisible(true);
        vic.setVisible(true);
        lose.setVisible(true);
        ed_bot1.setLayoutY(260);
        avanzado.setVisible(false);
        basicas.setVisible(true);
        filtro_nombre.setVisible(true);
    }

    @FXML
    private void basicas(MouseEvent event) {
        ambas.setSelected(true);
        vic.setSelected(false);
        lose.setSelected(false);
        text_a.setVisible(false);
        text_d.setVisible(false);
        text_v.setVisible(false);
        ambas.setVisible(false);
        vic.setVisible(false);
        lose.setVisible(false);
        ed_bot1.setLayoutY(150);
        avanzado.setVisible(true);
        basicas.setVisible(false);
        filtro_nombre.setVisible(false);
    }

    @FXML
    private void ambas(MouseEvent event) {
        ambas.setSelected(true);
        vic.setSelected(false);
        lose.setSelected(false);
    }

    @FXML
    private void vic(MouseEvent event) {
        vic.setSelected(true);
        ambas.setSelected(false);
        lose.setSelected(false);
    }

    @FXML
    private void lose(MouseEvent event) {
        lose.setSelected(true);
        ambas.setSelected(false);
        vic.setSelected(false);
    }

    @FXML
    private void aplicar_filtro(MouseEvent event) {
    }

}
