package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;

/**
 * @author Alex & Sento
 */
public class Partida_cpuController implements Initializable {

    @FXML
    private GridPane grid;
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
    private ImageView ficha1;
    @FXML
    private ImageView ficha;
    @FXML
    private ImageView avatar;
    @FXML
    private Text user;
    @FXML
    private Text puntos_obtenidos;
    @FXML
    private Text msj_ganador;
    @FXML
    private JFXButton volver_menu;

    private StackPane stack_pane;

    private Connect4 cn4;
    private Player player;

    private int[][] tablero;
    private boolean turno_player = true;

    File f_red = new File("src/images/red.png");
    private final Image red = new Image(f_red.toURI().toString());
    File f_yellow = new File("src/images/yellow.png");
    private final Image yellow = new Image(f_yellow.toURI().toString());

    private int cont0, cont1, cont2, cont3, cont4, cont5, cont6, cont7;
    private boolean userGana, cpuGana = false;
    private CountDownLatch barrera;
    private ReentrantLock lock = new ReentrantLock();
    @FXML
    private JFXButton boton_rendirse;
    @FXML
    private ImageView avatar1;
    @FXML
    private ImageView bubble;
    @FXML
    private Text cpu_texto_perdida;

    private Player player2;

    @FXML
    private JFXToggleButton music_check;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView banner;
    @FXML
    private Text vs;
    @FXML
    private Text cpu;
    @FXML
    private AnchorPane pane2;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView imagen_rendirse;
    private Paint paint_turno;
    private Paint paint_no;

    /**
     * Inicializa los parámetros del controlador
     *
     * @param con4
     * @param p
     * @param p2
     */
    public void initData(Connect4 con4, Player p, Player p2) {
        cn4 = con4;
        player = p;
        player2 = p2;
        user.setText(player.getNickName());
        avatar.setImage(player.getAvatar());
        ficha1.setImage(player.getAvatar());
    }

    public void initData(Connect4 con4, Player p) {
        cn4 = con4;
        player = p;
        user.setText(player.getNickName());
        avatar.setImage(player.getAvatar());
        ficha1.setImage(player.getAvatar());
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

    private ChangeListener changeSize1 = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            avatar1.setLayoutX(pane.getWidth() * 0.9);
            banner.fitWidthProperty().bind(pane.widthProperty());
            vs.setLayoutX(pane.getWidth() * 0.48);
            ficha.setLayoutX(pane.getWidth() * 0.7);
            cpu.setLayoutX(pane.getWidth() * 0.77);
            ficha1.setLayoutX(pane.getWidth() * 0.1);
            pane2.setLayoutX(pane.getWidth() * 0.3);
            hbox.scaleShapeProperty().bind(pane.scaleShapeProperty());
            user.setLayoutX(pane.getWidth() * 0.18);
            volver_menu.setLayoutX(pane.getWidth() * 0.42);
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cont0 = cont1 = cont2 = cont3 = cont4 = cont5 = cont6 = cont7 = 6;
        tablero = new int[7][8];
        volver_menu.setDisable(true);
        pane.widthProperty().addListener(changeSize1);
        paint_turno = user.getFill();
        paint_no = cpu.getFill();

    }

    @FXML
    private void poner(MouseEvent e) {
        if (!userGana || !cpuGana) {
            lock.lock();
            String id = ((Button) e.getSource()).getId();
            try {
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

            } catch (Connect4DAOException ex) {
                System.out.println(ex);
            } finally {
                if (!userGana) {
                    int cont_aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    System.out.println("Ficha de CPU colocada en columna: " + cont_aux);
                    try {
                        sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Partida_cpuController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    new Thread(new Runnable() {

                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        lock.lock();
                                        barrera.await(1000, TimeUnit.MILLISECONDS);
                                        poner_cpu(cont_aux);
                                    } catch (Exception ez) {
                                    }
                                }
                            });
                        }
                    }
                    ).start();
                }
            }
        }
    }

    private void poner_player(int columna, int cont) throws Connect4DAOException {
        barrera = new CountDownLatch(1);
        ImageView f;
        if (turno_player) {

            f = new ImageView(player.getAvatar());
            tablero[cont][columna] = 1;
        } else {
            f = new ImageView(yellow);
            tablero[cont][columna] = -1;
        }

        f.setFitWidth(ficha.getFitWidth() + 4);
        f.setFitHeight(ficha.getFitHeight() + 4);
        System.out.println("\nFicha de " + player.getNickName() + " colocada en columna: " + columna);
        f.autosize();
        ;
        grid.add(f, columna, cont);

        comprobar(columna, cont);

        turno_player = !turno_player;

    }

    private void poner_cpu(int id) throws Exception {

        switch (id) {
            case 0:
                if (cont0 < 0) {
                    int aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    poner_cpu(aux);
                    break;
                }
                poner_player(0, cont0);
                cont0--;
                break;
            case 1:
                if (cont1 < 0) {
                    int aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    poner_cpu(aux);
                    break;
                }
                poner_player(1, cont1);
                cont1--;
                break;
            case 2:
                if (cont2 < 0) {
                    int aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    poner_cpu(aux);
                    break;
                }
                poner_player(2, cont2);
                cont2--;
                break;
            case 3:
                if (cont3 < 0) {
                    int aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    poner_cpu(aux);
                    break;
                }
                poner_player(3, cont3);
                cont3--;
                break;
            case 4:
                if (cont4 < 0) {
                    int aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    poner_cpu(aux);
                    break;
                }
                poner_player(4, cont4);
                cont4--;
                break;
            case 5:
                if (cont5 < 0) {
                    int aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    poner_cpu(aux);
                    break;
                }
                poner_player(5, cont5);
                cont5--;
                break;
            case 6:
                if (cont6 < 0) {
                    int aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    poner_cpu(aux);
                    break;
                }
                poner_player(6, cont6);
                cont6--;
                break;
            case 7:
                if (cont7 < 0) {
                    int aux = ThreadLocalRandom.current().nextInt(0, 7 + 1);
                    poner_cpu(aux);
                    break;
                }
                poner_player(7, cont7);
                cont7--;
                break;
        }

    }

    private void comprobar(int columna, int fila) throws Connect4DAOException {
        int conth = 0;
        int contv = 0;
        int contdd1 = 0;
        int contdd2 = 0;
        int contdi1 = 0;
        int contdi2 = 0;
        if (turno_player) {
            for (int h = 0; h < 8; h++) {
                if (tablero[fila][h] == 1) {
                    conth++;
                } else {
                    conth = 0;
                }
                if (conth == 4) {
                    System.out.println(player.getNickName() + " win tipo horizontal\n");
                    userGana = true;
                    player.plusPoints(cn4.getPointsAlone());
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
                        System.out.println(player.getNickName() + " win tipo vertical\n");
                        userGana = true;
                        player.plusPoints(cn4.getPointsAlone());
                    }
                }
            }
            if (columna + fila < 11 && columna + fila > 2) { //Si la suma de columna y fila es 11 o mayor o 2 o menor significa que esta en las esquinas y la diagonal es <4
                if (columna + fila <= 5) { // Si la suma de columna y fila es <= 5 entonces la diagonal esta en la columna 0
                    int dd1_aux = 0; // La variable columna que empieza en 0 y va decrementando
                    for (int dd1 = (columna + fila) % 6; dd1 >= 0; dd1--) { // dd1 tiene que ser menor que fila +1 porque son el numero de filas que puede subir sin salirse
                        if (tablero[dd1][dd1_aux] == 1) {
                            contdd1++;

                        } else {
                            contdd1 = 0;
                        }
                        if (contdd1 == 4) {
                            System.out.println(player.getNickName() + " win tipo diagonal1\n");
                            userGana = true;
                            player.plusPoints(cn4.getPointsAlone());
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
                            System.out.println(player.getNickName() + " win tipo diagonal2\n");
                            userGana = true;
                            player.plusPoints(cn4.getPointsAlone());
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

                        if (tablero[di1][di1_aux] == 1) {
                            contdi1++;
                        } else {
                            contdi1 = 0;
                        }
                        if (contdi1 == 4) {
                            System.out.println(player.getNickName() + " win tipo diagonal3\n");
                            userGana = true;
                            player.plusPoints(cn4.getPointsAlone());
                        }
                        di1_aux--;
                    }
                } else {
                    int di2_aux = 0; // Columna 0
                    System.out.println((columna - fila) % 6);
                    for (int di2 = Math.abs((columna - fila) % 6); di2 <= 6; di2++) {

                        if (tablero[di2][di2_aux] == 1) {
                            contdi2++;
                            System.out.println("col" + di2 + " fila " + di2_aux + " contador " + contdi2 + " tablero " + tablero[di2_aux][di2]);
                        } else {
                            contdi2 = 0;
                        }
                        if (contdi2 == 4) {
                            System.out.println(player.getNickName() + " win tipo diagonal4\n");
                            userGana = true;
                            player.plusPoints(cn4.getPointsAlone());
                        }
                        di2_aux++;
                    }
                }
            }

            cpu.setFill(paint_turno);
            user.setFill(paint_no);
            if (userGana) {
                if (player.getNickName().equals("invitado")) {
                    msj_ganador.setText("¡" + player.getNickName() + " gana!");
                    puntos_obtenidos.setText("-> Inicia sesión para obtener puntos!");
                } else {
                    msj_ganador.setText("¡" + player.getNickName() + " gana!");
                    puntos_obtenidos.setText("-> Puntos obtenidos: 50p");
                    player.plusPoints(cn4.getPointsAlone());
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

        } else {
            for (int h = 0; h < 8; h++) {
                if (tablero[fila][h] == -1) {
                    conth++;
                } else {
                    conth = 0;
                }
                if (conth == 4) {
                    System.out.println("CPU win tipo horizontal1\n");
                    cpuGana = true;
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
                        System.out.println("CPU win tipo vertical2\n");
                        cpuGana = true;
                    }
                }
            }
            if (columna + fila < 11 && columna + fila > 2) { //si la suma de columna y fila es 11 o mayor o 2 o menor significa que esta en las esquinas y la diagonal es <4
                if (columna + fila <= 5) { // Si la suma de columna y fila es <= 5 entonces la diagonal esta en la columna 0
                    int dd1_aux = 0; // La variable columna que empieza en 0 y va decrementando
                    for (int dd1 = (columna + fila) % 6; dd1 >= 0; dd1--) { // dd1 tiene que ser menor que fila +1 porque son el numero de filas que puede subir sin salirse
                        if (tablero[dd1][dd1_aux] == -1) {
                            contdd1++;
                        } else {
                            contdd1 = 0;
                        }
                        if (contdd1 == 4) {
                            System.out.println(player.getNickName() + " win tipo diagonal1\n");
                            cpuGana = true;
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
                            System.out.println(player.getNickName() + " win tipo diagonal2\n");
                            cpuGana = true;
                        }
                        dd2_aux--;
                    }
                }

            }

            if (columna - fila > -4 && columna - fila < 5) { // Si columna - fila es -4 o menor o 5 o mayor entonces esta en las esquinas y no hay diagonal

                if (columna - fila > 0) { // Si la resta de fila - columna es igual o menor que -2 entonces esta en la columna 7
                    int di1_aux = 7; //Columna  7
                    System.out.println("mod" + (columna - fila) % 7);
                    for (int di1 = (((fila - columna) % 7) + 7) % 7; di1 >= 0; di1--) {

                        if (tablero[di1][di1_aux] == -1) {
                            contdi1++;
                        } else {
                            contdi1 = 0;
                        }
                        if (contdi1 == 4) {
                            System.out.println(player.getNickName() + " win tipo diagonal3\n");
                            cpuGana = true;
                        }
                        di1_aux--;
                    }
                } else {
                    int di2_aux = 0; //Columna 0
                    System.out.println((columna - fila) % 6);
                    for (int di2 = Math.abs((columna - fila) % 6); di2 <= 6; di2++) {

                        if (tablero[di2][di2_aux] == -1) {
                            contdi2++;
                            System.out.println("col" + di2 + " fila " + di2_aux + " contador " + contdi2 + " tablero " + tablero[di2_aux][di2]);
                        } else {
                            contdi2 = 0;
                        }
                        if (contdi2 == 4) {
                            System.out.println(player.getNickName() + " win tipo diagonal4\n");
                            cpuGana = true;
                        }
                        di2_aux++;
                    }
                }
            }

            if (cpuGana) {
                msj_ganador.setText("¡CPU gana!");
                puntos_obtenidos.setText("No se han asignado puntos... ¡Suerte a la próxima " + player.getNickName() + "!");
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
            user.setFill(paint_turno);
            cpu.setFill(paint_no);
        }
        if (cont0 == 0 && cont1 == 0 && cont2 == 0 && cont3 == 0 && cont4 == 0 && cont5 == 0 && cont6 == 0 && cont7 == 0) {
            msj_ganador.setText("¡EMPATE!");
            puntos_obtenidos.setText("No se han asignado puntos... ¡Suerte a la próxima " + player.getNickName() + "!");
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
        lock.unlock();
    }

    @FXML
    private void volver_menu(MouseEvent event) {
        if (player2 == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
                Parent newRoot = loader.load();
                Menu_principalController menu = loader.getController();

                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                menu.initData(cn4, player);
                menu.initMusic(mediaPlayer, music_check.isSelected());
                newStage.setScene(scene);
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setResizable(false);
                newStage.show();
                final Node sr = (Node) event.getSource();
                final Stage st = (Stage) sr.getScene().getWindow();
                st.close();
            } catch (IOException iOException) {
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
                Parent newRoot = loader.load();
                Menu_principalController menu = loader.getController();

                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                menu.initData(cn4, player, player2);
                menu.initMusic(mediaPlayer, music_check.isSelected());
                newStage.setScene(scene);
                newStage.initModality(Modality.APPLICATION_MODAL);

                newStage.setResizable(false);
                newStage.show();
                final Node sr = (Node) event.getSource();
                final Stage st = (Stage) sr.getScene().getWindow();
                st.close();
            } catch (IOException iOException) {
            }
        }
    }

    @FXML
    private void rendirse(MouseEvent event) {
        if (player2 == null)
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rendirse.fxml"));
            Parent newRoot = loader.load();
            RendirseController rendicion = loader.getController();

            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            rendicion.initData(cn4, player, stage);
            rendicion.initMusic(mediaPlayer, music_check.isSelected());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException iOException) {
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("rendirse.fxml"));
                Parent newRoot = loader.load();
                RendirseController rendicion = loader.getController();

                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                rendicion.initData(cn4, player, player2, stage);
                rendicion.initMusic(mediaPlayer, music_check.isSelected());
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setResizable(false);
                newStage.setScene(scene);
                newStage.show();
            } catch (IOException iOException) {
            }
        }
    }

}
