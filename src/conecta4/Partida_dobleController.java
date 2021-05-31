package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;

/**
 * @author Alex & Sento
 */
public class Partida_dobleController implements Initializable {

    @FXML
    private GridPane grid;

    private Connect4 cn4;
    private Player player1, player2, invitado;

    private int[][] tablero;

    private boolean turno_player1 = true;

    @FXML
    private ImageView ficha;
    @FXML
    private ImageView ficha1;
    @FXML
    private ImageView ficha2;
    @FXML
    private ImageView avatar;
    @FXML
    private Text user;
    @FXML
    private Text puntos_obtenidos;
    @FXML
    private Text msj_ganador;

    private StackPane stack_pane;

    File f_red = new File("src/images/red.png");
    private final Image red = new Image(f_red.toURI().toString());
    File f_yellow = new File("src/images/yellow.png");
    private final Image yellow = new Image(f_yellow.toURI().toString());

    private int cont0, cont1, cont2, cont3, cont4, cont5, cont6, cont7;
    private boolean player1Gana, player2Gana = false;
    @FXML
    private JFXButton volver_menu;
    @FXML
    private ImageView avatar2;
    @FXML
    private Button c0;
    @FXML
    private Button c1;
    @FXML
    private Button c2;
    @FXML
    private Button c3;
    @FXML
    private Button c4;
    @FXML
    private Button c5;
    @FXML
    private Button c6;
    @FXML
    private Button c7;
    @FXML
    private Text text_pl1;

    @FXML
    private Text user2;

    private Random randomInt;
    private int random01;
    @FXML
    private JFXButton boton_rendirse;
    @FXML
    private JFXToggleButton music_check;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView banner;
    @FXML
    private Text vs;
    @FXML
    private AnchorPane pane2;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView imagen_rendirse;

    private Paint paint_turno1, paint_turno2;
    private MediaPlayer mediaPlayer;

    /**
     * Inicializa los parámetros del controlador
     *
     * @param con4
     * @param p1
     * @param p2
     */
    public void initData(Connect4 con4, Player p1, Player p2) {
        cn4 = con4;
        player1 = p1;
        player2 = p2;
        invitado = cn4.getPlayer("invitado");
        user.setText(player1.getNickName());
        user2.setText(player2.getNickName());
        avatar.setImage(player1.getAvatar());
        avatar2.setImage(player2.getAvatar());
        ficha1.setImage(player1.getAvatar());
        if (!player2.equals(invitado)) {
            ficha2.setImage(player2.getAvatar());
        } else {
            ficha2.setImage(yellow);
            ficha.setImage(yellow);
        }
    }

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

    private ChangeListener changeSize1 = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            avatar2.setLayoutX(pane.getWidth() * 0.9);
            banner.fitWidthProperty().bind(pane.widthProperty());
            vs.setLayoutX(pane.getWidth() * 0.48);
            ficha1.setLayoutX(pane.getWidth() * 0.1);
            ficha2.setLayoutX(pane.getWidth() * 0.55);
            hbox.scaleShapeProperty().bind(pane.scaleShapeProperty());
            user.setLayoutX(pane.getWidth() * 0.18);
            user2.setLayoutX(pane.getWidth() * 0.65);
            volver_menu.setLayoutX(pane.getWidth() * 0.42);
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cont0 = cont1 = cont2 = cont3 = cont4 = cont5 = cont6 = cont7 = 6;
        tablero = new int[7][8];
        volver_menu.setDisable(true);
        pane.widthProperty().addListener(changeSize1);
        turno_player1 = true;
        paint_turno1 = user.getFill();
        paint_turno2 = user2.getFill();
    }

    @FXML
    private void poner(MouseEvent e) throws Connect4DAOException {
        if (player1Gana == true || player2Gana == true) {
        } else {
            String id = ((Button) e.getSource()).getId();
            switch (id) {
                case "c0":
                    poner_player(0, cont0);
                    cont0--;
                    if (cont0 < 0) {
                        c0.setDisable(true);
                    }
                    break;
                case "c1":
                    poner_player(1, cont1);
                    cont1--;
                    if (cont1 < 0) {
                        c1.setDisable(true);
                    }
                    break;
                case "c2":
                    poner_player(2, cont2);
                    cont2--;
                    if (cont2 < 0) {
                        c2.setDisable(true);
                    }
                    break;
                case "c3":
                    poner_player(3, cont3);
                    cont3--;
                    if (cont3 < 0) {
                        c3.setDisable(true);
                    }
                    break;
                case "c4":
                    poner_player(4, cont4);
                    cont4--;
                    if (cont4 < 0) {
                        c4.setDisable(true);
                    }
                    break;
                case "c5":
                    poner_player(5, cont5);
                    cont5--;
                    if (cont5 < 0) {
                        c5.setDisable(true);
                    }
                    break;
                case "c6":
                    poner_player(6, cont6);
                    cont6--;
                    if (cont6 < 0) {
                        c6.setDisable(true);
                    }
                    break;
                case "c7":
                    poner_player(7, cont7);
                    cont7--;
                    if (cont7 < 0) {
                        c7.setDisable(true);
                    }
                    break;
            }
        }
    }

    private void poner_player(int columna, int cont) throws Connect4DAOException {
        ImageView f;
        if (turno_player1) {
            tablero[cont][columna] = 1;
            if (player1.getAvatar() == player2.getAvatar()) {
                if (random01 == 0) {
                    f = new ImageView(player1.getAvatar());
                } else {
                    f = new ImageView(yellow);
                }
            } else {
                f = new ImageView(player1.getAvatar());
            }
        } else {
            tablero[cont][columna] = -1;
            if (player1.getAvatar() == player2.getAvatar()) {
                if (random01 == 1) {
                    f = new ImageView(player2.getAvatar());
                } else {
                    f = new ImageView(yellow);
                }
            } else {
                f = new ImageView(player2.getAvatar());
            }
        }

        f.setFitWidth(ficha.getFitWidth() + 4);
        f.setFitHeight(ficha.getFitHeight() + 4);
        System.out.println("\nFicha colocada en columna: " + columna);
        grid.add(f, columna, cont);

        comprobar(columna, cont);
        turno_player1 = !turno_player1;
    }

    private void comprobar(int columna, int fila) throws Connect4DAOException {
        int conth = 0;
        int contv = 0;
        int contdd1 = 0;
        int contdd2 = 0;
        int contdi1 = 0;
        int contdi2 = 0;
        if (turno_player1) {
            for (int h = 0; h < 8; h++) {
                if (tablero[fila][h] == 1) {
                    conth++;
                } else {
                    conth = 0;
                }
                if (conth == 4) {
                    System.out.println(player1.getNickName() + " win tipo horizontal\n");
                    player1Gana = true;
                }

            }
            if (fila < 4) {
                for (int v = fila; v < fila + 4; v++) {
                    if (tablero[v][columna] == 1) {
                        contv++;
                    } else {
                        contv = 0;
                    }
                    if (contv == 4) {
                        System.out.println(player1.getNickName() + " win tipo vertical\n");
                        player1Gana = true;
                    }
                }
            }
            if (columna + fila < 11 && columna + fila > 2) { //si la suma de columna y fila es 11 o mayor o 2 o menor significa que esta en las esquinas y la diagonal es <4
                if (columna + fila <= 5) { // Si la suma de columna y fila es <= 5 entonces la diagonal esta en la columna 0
                    int dd1_aux = 0;// La variable columna que empieza en 0 y va decrementando
                    for (int dd1 = (columna + fila) % 6; dd1 >= 0; dd1--) { //dd1 tiene que ser menor que fila +1 porque son el numero de filas que puede subir sin salirse
                        if (tablero[dd1][dd1_aux] == 1) {
                            contdd1++;
                        } else {
                            contdd1 = 0;
                        }
                        if (contdd1 == 4) {
                            System.out.println(player1.getNickName() + " win tipo diagonal1\n");
                            player1Gana = true;
                        }
                        dd1_aux++;
                    }
                } else {
                    int dd2_aux = 6; // La variable fila que empieza en 6 y va decrementando
                    for (int dd2 = (columna + fila) % 6; dd2 < 7; dd2++) {
                        if (tablero[dd2_aux][dd2] == 1) {
                            contdd2++;
                        } else {
                            contdd2 = 0;
                        }
                        if (contdd2 == 4) {
                            System.out.println(player1.getNickName() + " win tipo diagonal2\n");
                            player1Gana = true;
                        }
                        dd2_aux--;
                    }
                }

            }

            if (columna - fila > -4 && columna - fila < 5) { // Si columna - fila es -4 o menor o 5 o mayor entonces esta en las esquinas y no hay diagonal

                if (columna - fila > 0) { // Si la resta de fila - columna es igual o menor que -2 entonces esta en la columna 7
                    int di1_aux = 7;//columna  7
                    System.out.println("mod" + (fila - columna) % 7);
                    for (int di1 = (((fila - columna) % 7) + 7) % 7; di1 <= 6; di1++) {
                        if (tablero[di1][di1_aux] == 1) {
                            contdi1++;
                        } else {
                            contdi1 = 0;
                        }
                        if (contdi1 == 4) {
                            System.out.println(player1.getNickName() + " win tipo diagonal3\n");
                            player1Gana = true;
                        }
                        di1_aux--;
                    }
                } else {
                    int di2_aux = 0;//columna 0
                    System.out.println((columna - fila) % 6);
                    for (int di2 = Math.abs((columna - fila) % 6); di2 <= 6; di2++) {
                        if (tablero[di2][di2_aux] == 1) {
                            contdi2++;
                            System.out.println("col" + di2 + " fila " + di2_aux + " contador " + contdi2 + " tablero " + tablero[di2_aux][di2]);
                        } else {
                            contdi2 = 0;
                        }
                        if (contdi2 == 4) {
                            System.out.println(player1.getNickName() + " win tipo diagonal4\n");
                            player1Gana = true;
                        }
                        di2_aux++;
                    }
                }
            }
            if (player1Gana) {
                msj_ganador.setText("¡" + player1.getNickName() + " gana!");
                if (player1.equals(invitado)) {
                    puntos_obtenidos.setText("-> Inicia sesión para obtener puntos!");
                } else {
                    puntos_obtenidos.setText("-> Puntos obtenidos: 50p");
                    player1.plusPoints(cn4.getPointsRound());

                }
                cn4.regiterRound(LocalDate.now().atTime(LocalTime.now()), player1, player2);

                volver_menu.setOpacity(1);
                volver_menu.setDisable(false);
                boton_rendirse.setDisable(true);
                imagen_rendirse.setDisable(true);
                c0.setDisable(true);
                c1.setDisable(true);
                c2.setDisable(true);
                c3.setDisable(true);
                c4.setDisable(true);
                c5.setDisable(true);
                c6.setDisable(true);
                c7.setDisable(true);
            }
            user.setFill(paint_turno2);
            user2.setFill(paint_turno1);

            // Comprobaciones Jugador2
        } else {
            for (int h = 0; h < 8; h++) {
                if (tablero[fila][h] == -1) {
                    conth++;
                } else {
                    conth = 0;
                }
                if (conth == 4) {
                    System.out.println(player2.getNickName() + " win tipo horizontal1\n");
                    player2Gana = true;
                }
            }

            if (fila < 4) {
                for (int v = fila; v < fila + 4; v++) {
                    if (tablero[v][columna] == -1) {
                        contv++;
                    } else {
                        contv = 0;
                    }
                    if (contv == 4) {
                        System.out.println(player2.getNickName() + " win tipo vertical2\n");
                        player2Gana = true;
                    }
                }
            }
            if (columna + fila < 11 && columna + fila > 2) { //si la suma de columna y fila es 11 o mayor o 2 o menor significa que esta en las esquinas y la diagonal es <4
                if (columna + fila <= 5) { // Si la suma de columna y fila es <= 5 entonces la diagonal esta en la columna 0
                    int dd1_aux = 0;// La variable columna que empieza en 0 y va decrementando
                    for (int dd1 = (columna + fila) % 6; dd1 >= 0; dd1--) { //dd1 tiene que ser menor que fila +1 porque son el numero de filas que puede subir sin salirse
                        if (tablero[dd1][dd1_aux] == -1) {
                            contdd1++;
                        } else {
                            contdd1 = 0;
                        }
                        if (contdd1 == 4) {
                            System.out.println(player2.getNickName() + " win tipo diagonal1\n");
                            player2Gana = true;
                        }
                        dd1_aux++;
                    }
                } else {
                    int dd2_aux = 6; // La variable fila que empieza en 6 y va decrementando
                    for (int dd2 = (columna + fila) % 6; dd2 < 7; dd2++) {
                        if (tablero[dd2_aux][dd2] == -1) {
                            contdd2++;
                        } else {
                            contdd2 = 0;
                        }
                        if (contdd2 == 4) {
                            System.out.println(player2.getNickName() + " win tipo diagonal2\n");
                            player2Gana = true;
                        }
                        dd2_aux--;
                    }
                }

            }

            if (columna - fila > -4 && columna - fila < 5) { // Si columna - fila es -4 o menor o 5 o mayor entonces esta en las esquinas y no hay diagonal

                if (columna - fila > 0) { // Si la resta de fila - columna es igual o menor que -2 entonces esta en la columna 7
                    int di1_aux = 7; // Columna  7
                    System.out.println("mod" + (columna - fila) % 7);
                    for (int di1 = (((fila - columna) % 7) + 7) % 7; di1 >= 0; di1--) {
                        if (tablero[di1][di1_aux] == -1) {
                            contdi1++;
                        } else {
                            contdi1 = 0;
                        }
                        if (contdi1 == 4) {
                            System.out.println(player2.getNickName() + " win tipo diagonal3\n");
                            player2Gana = true;
                        }
                        di1_aux--;
                    }
                } else {
                    int di2_aux = 0; // Columna 0
                    System.out.println((columna - fila) % 6);
                    for (int di2 = Math.abs((columna - fila) % 6); di2 <= 6; di2++) {
                        if (tablero[di2][di2_aux] == -1) {
                            contdi2++;
                            System.out.println("col" + di2 + " fila " + di2_aux + " contador " + contdi2 + " tablero " + tablero[di2_aux][di2]);
                        } else {
                            contdi2 = 0;
                        }
                        if (contdi2 == 4) {
                            System.out.println(player2.getNickName() + " win tipo diagonal4\n");
                            player2Gana = true;
                        }
                        di2_aux++;
                    }
                }
            }
            if (player2Gana) {
                msj_ganador.setText("¡" + player2.getNickName() + " gana!");
                if (player2.equals(invitado)) {
                    puntos_obtenidos.setText("-> Inicia sesión para obtener puntos!");

                } else {
                    if (!player1.equals(invitado)) {
                        puntos_obtenidos.setText("-> Puntos obtenidos: 50p");
                        player2.plusPoints(cn4.getPointsRound());

                    }
                    cn4.regiterRound(LocalDate.now().atTime(LocalTime.now()), player2, player1);
                }
                volver_menu.setOpacity(1);
                volver_menu.setDisable(false);
                boton_rendirse.setDisable(true);
                imagen_rendirse.setDisable(true);

                c0.setDisable(true);
                c1.setDisable(true);
                c2.setDisable(true);
                c3.setDisable(true);
                c4.setDisable(true);
                c5.setDisable(true);
                c6.setDisable(true);
                c7.setDisable(true);
            }
            user.setFill(paint_turno1);
            user2.setFill(paint_turno2);
        }
        if (cont0 < 0 && cont1 < 0 && cont2 < 0 && cont3 < 0 && cont4 < 0 && cont5 < 0 && cont6 < 0 && cont7 < 0) {
            msj_ganador.setText("¡EMPATE!");
            puntos_obtenidos.setText("No se han asignado puntos... ¡Suerte a la próxima " + player1.getNickName() + ", " + player2.getNickName() + "!");
            boton_rendirse.setDisable(true);
            imagen_rendirse.setDisable(true);
            volver_menu.setOpacity(1);
            volver_menu.setDisable(false);
            c0.setDisable(true);
            c1.setDisable(true);
            c2.setDisable(true);
            c3.setDisable(true);
            c4.setDisable(true);
            c5.setDisable(true);
            c6.setDisable(true);
            c7.setDisable(true);
        }
    }

    @FXML
    private void volver_menu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
        Parent newRoot = loader.load();
        Menu_principalController menu = loader.getController();

        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();

        if (player1 == invitado) {
            if (player2 != invitado) {
                menu.initData(cn4, player2);
            } else {
                menu.initData(cn4, invitado);
            }

        } else {
            if (player2 == invitado) {
                menu.initData(cn4, player1);
            } else {
                menu.initData(cn4, player1, player2);
            }
        }
        menu.initMusic(mediaPlayer, music_check.isSelected());
        menu.initController(menu);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.show();
        final Node sr = (Node) event.getSource();
        final Stage st = (Stage) sr.getScene().getWindow();
        st.close();

    }

    @FXML
    private void rendirse(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rendirse.fxml"));
        Parent newRoot = loader.load();
        RendirseController rendicion = loader.getController();

        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        rendicion.initData(cn4, player1, player2, stage, turno_player1);
        rendicion.initMusic(mediaPlayer, music_check.isSelected());
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.show();
    }
}
