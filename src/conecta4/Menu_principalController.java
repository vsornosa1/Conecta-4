package conecta4;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
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
    private Text contrase??a_perfil;
    @FXML
    private Text mail_perfil;
    @FXML
    private Text cumplea??os_perfil;
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
    @FXML
    private TableColumn<Player, Image> avatar;

    private ObservableList<Player> observablePlayers;
    @FXML
    private JFXToggleButton music_check2;
    @FXML
    private JFXToggleButton music_check3;
    @FXML
    private JFXDatePicker date_ini;
    @FXML
    private JFXDatePicker date_fin;
    @FXML
    private TableView<Round> historial;
    @FXML
    private Hyperlink avanzado;
    @FXML
    private Hyperlink basicas;
    @FXML
    private JFXRadioButton ambas;
    @FXML
    private JFXRadioButton vic;
    @FXML
    private JFXRadioButton lose;

    @FXML
    private JFXButton ed_bot1;
    @FXML
    private Text text_v;
    @FXML
    private Text text_d;
    @FXML
    private Text text_a;

    private TreeMap<LocalDate, List<Round>> hist;

    private ObservableList<Round> observableRounds;
    private LocalDate[] listaTimeRondas;
    @FXML
    private TableColumn<Round, String> fechaCol;
    @FXML
    private TableColumn<Round, String> winnerCol;
    @FXML
    private TableColumn<Round, String> loserCol;
    @FXML
    private JFXTextField filtro_nombre;

    private int cont = 0;
    @FXML
    private JFXButton graf_bot;
    @FXML
    private LineChart<String, Number> graf_line;

    @FXML
    private PieChart graf_pie;

    private CategoryAxis xAxe = new CategoryAxis();

    private NumberAxis yAxe = new NumberAxis();
    @FXML
    private BarChart<String, Number> graf_bar;
    @FXML
    private StackedBarChart<String, Number> graf_sbar;
    @FXML
    private JFXButton cont_bot;
    @FXML
    private JFXButton rad_bot;
    @FXML
    private JFXTextField rank_nombre;
    @FXML
    private JFXPasswordField text_pass;
    @FXML
    private JFXTextField text_vpass;
    @FXML
    private ImageView v;
    @FXML
    private ImageView nv;
    @FXML
    private JFXButton vb;
    @FXML
    private JFXToggleButton tema_check;
    @FXML
    private JFXToggleButton tema_check1;
    @FXML
    private JFXToggleButton tema_check2;
    @FXML
    private JFXToggleButton tema_check3;
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane3;
    @FXML
    private AnchorPane pane4;
    @FXML
    private JFXTabPane tabpane;
    @FXML
    private Hyperlink ayuda;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date_ini.setValue(LocalDate.now());
        date_fin.setValue(LocalDate.now());
        filtro_nombre.setVisible(false);
        graf_line.setLegendVisible(false);
        graf_line.setAnimated(false);
        graf_line.setCreateSymbols(false);
        xAxe.setLabel("fecha");

        yAxe.setLabel("partidas");

        tema_check.selectedProperty().bindBidirectional(tema_check1.selectedProperty());
        tema_check.selectedProperty().bindBidirectional(tema_check2.selectedProperty());
        tema_check.selectedProperty().bindBidirectional(tema_check3.selectedProperty());
        graf_line.visibleProperty().addListener(selListener);
        graf_pie.visibleProperty().addListener(selListener);
        graf_sbar.setAnimated(false);
        graf_bar.setAnimated(false);
        graf_bot.layoutXProperty().addListener(posListener);
        ed_bot1.layoutXProperty().addListener(posListener);
        historial.visibleProperty().addListener(tListener);
        graf_bar.visibleProperty().addListener(ttListener);
        graf_line.getXAxis().setTickLabelFill(Paint.valueOf("#fca311"));
        graf_line.getYAxis().setTickLabelFill(Paint.valueOf("#fca311"));
        graf_bar.getXAxis().setTickLabelFill(Paint.valueOf("#fca311"));
        graf_bar.getYAxis().setTickLabelFill(Paint.valueOf("#fca311"));
        graf_sbar.getXAxis().setTickLabelFill(Paint.valueOf("#fca311"));
        graf_sbar.getYAxis().setTickLabelFill(Paint.valueOf("#fca311"));
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
        player2 = null;
        perf = true;
        aplauso.setText("Bienvenido/a " + player1.getNickName());
        avatar_player1.setImage(player1.getAvatar());
        avatar11.setImage(player1.getAvatar());
        avatar111.setImage(player1.getAvatar());
        invitado = cn4.getPlayer("invitado");
        avatar_player2.setImage(null);
        if (player1.equals(invitado)) {
            initPerfil(true);

            link_cerrar_sesion.setText("Iniciar sesi??n");
        } else {
            initPerfil(true);
            link_cerrar_sesion.setText("Cerrar sesi??n");
        }
        ArrayList<Player> jugadores = cn4.getConnect4Ranking();
        observablePlayers = FXCollections.observableList(cn4.getConnect4Ranking());
        name.setCellValueFactory(new PropertyValueFactory<Player, String>("nickName"));
        punt.setCellValueFactory(new PropertyValueFactory<Player, Integer>("points"));
        avatar.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Image>(cellData.getValue().getAvatar()));
        avatar.setCellFactory(c -> new TableCell<Player, Image>() {
            private ImageView view = new ImageView();

            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    view.setImage(item);
                    view.setFitHeight(40);
                    view.setFitWidth(40);
                    setGraphic(view);
                }
            }
        });

        observablePlayers.remove(cn4.getPlayer("invitado"));
        table.setItems(observablePlayers);
        rank_nombre.setText(null);
        FilteredList<Player> filteredData = new FilteredList<>(observablePlayers, p -> true);
        rank_nombre.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(players -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (players.getNickName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Player> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        observablePlayers.remove(cn4.getPlayer("invitado"));
        table.setItems(sortedData);

        hist = cn4.getRoundsPerDay();
        fechaCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimeStamp().getDayOfMonth() + "/"
                + cellData.getValue().getTimeStamp().getMonth() + "/" + cellData.getValue().getTimeStamp().getYear() + " - "
                + cellData.getValue().getTimeStamp().getHour() + ":" + cellData.getValue().getTimeStamp().getMinute()));
        winnerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWinner().getNickName()));
        loserCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLoser().getNickName()));
        historial.setItems(observableRounds);

        hist = cn4.getRoundsPerDay();
        int x = 1;

        List<Round> lista = hist.get(LocalDate.now());
        if (lista != null) {
            if (!lista.isEmpty()) {
                cont = lista.size();
                if (x == 1) {
                    observableRounds = FXCollections.observableList(new ArrayList<Round>());
                }
                x++;

                for (int i = 0; i < cont; i++) {
                    if (lista.get(i) != null) {

                        observableRounds.add(lista.get(i));
                    }

                }

            }

            historial.setItems(observableRounds);
        }
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

        ArrayList<Player> jugadores = cn4.getConnect4Ranking();
        observablePlayers = FXCollections.observableList(cn4.getConnect4Ranking());
        name.setCellValueFactory(new PropertyValueFactory("nickName"));
        punt.setCellValueFactory(new PropertyValueFactory("points"));
        observablePlayers.remove(cn4.getPlayer("invitado"));
        table.setItems(observablePlayers);
        avatar.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Image>(cellData.getValue().getAvatar()));
        avatar.setCellFactory(c -> new TableCell<Player, Image>() {
            private ImageView view = new ImageView();

            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {

                    view.setImage(item);
                    view.setFitHeight(40);
                    view.setFitWidth(40);
                    setGraphic(view);
                }
            }
        });
        rank_nombre.setText(null);
        FilteredList<Player> filteredData = new FilteredList<>(observablePlayers, p -> true);
        rank_nombre.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(players -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (players.getNickName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Player> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        observablePlayers.remove(cn4.getPlayer("invitado"));
        table.setItems(sortedData);

        hist = cn4.getRoundsPerDay();

        fechaCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimeStamp().getDayOfMonth() + "/"
                + cellData.getValue().getTimeStamp().getMonth() + "/" + cellData.getValue().getTimeStamp().getYear() + " - "
                + cellData.getValue().getTimeStamp().getHour() + ":" + cellData.getValue().getTimeStamp().getMinute()));
        winnerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWinner().getNickName()));
        loserCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLoser().getNickName()));

        hist = cn4.getRoundsPerDay();
        int x = 1;

        List<Round> lista = hist.get(LocalDate.now());
        if (lista != null) {
            if (!lista.isEmpty()) {
                cont = lista.size();
                if (x == 1) {
                    observableRounds = FXCollections.observableList(new ArrayList());
                }
                x++;

                for (int i = 0; i < cont; i++) {
                    if (lista.get(i) != null) {

                        observableRounds.add(lista.get(i));
                    }

                }

            }

            historial.setItems(observableRounds);
        }
    }

    public void initPerfil(boolean p) {

        if (player1 == invitado) {
            ed_bot.setDisable(true);
            avatar_perfil.setImage(player1.getAvatar());
            user_perfil.setText(player1.getNickName());
            contrase??a_perfil.setText("??Inicia sesi??n para ver tu perfil!");
            mail_perfil.setText("");
            cumplea??os_perfil.setText("");
            lineaJ2_perfil.setOpacity(0);
            cambiarAvatar2_perfil.setOpacity(0);
            cambiarJ2_perfil.setOpacity(0);
            change_bot.setOpacity(1);
            text_pass.setVisible(false);
            text_vpass.setVisible(false);
            vb.setVisible(false);
            v.setVisible(false);
            nv.setVisible(false);

        } else if (player2 == null) {
            change_bot.setOpacity(1);
            ed_bot.setDisable(false);
            avatar_perfil.setImage(player1.getAvatar());
            user_perfil.setText("@" + player1.getNickName());
            contrase??a_perfil.setText("Contrase??a: ");
            mail_perfil.setText("Correo electr??nico: " + player1.getEmail());
            cumplea??os_perfil.setText("Fecha de Nacimiento: " + player1.getBirthdate());
            lineaJ2_perfil.setOpacity(0);
            cambiarAvatar2_perfil.setOpacity(0);
            cambiarJ2_perfil.setOpacity(0);
            text_pass.setVisible(true);
            text_vpass.setVisible(false);
            text_pass.setText(player1.getPassword());
            text_vpass.setText(player1.getPassword());
            vb.setVisible(true);
            v.setVisible(true);
            nv.setVisible(false);
        } else {
            ed_bot.setDisable(false);
            change_bot.setOpacity(0);
            lineaJ2_perfil.setOpacity(1);
            cambiarAvatar2_perfil.setOpacity(1);
            cambiarJ2_perfil.setOpacity(1);
            text_pass.setVisible(true);
            text_vpass.setVisible(false);
            vb.setVisible(true);
            v.setVisible(true);
            nv.setVisible(false);

            if (p) {
                avatar_perfil.setImage(player1.getAvatar());
                user_perfil.setText("@" + player1.getNickName());
                contrase??a_perfil.setText("Contrase??a: ");
                mail_perfil.setText("Correo electr??nico: " + player1.getEmail());
                cumplea??os_perfil.setText("Fecha de Nacimiento: " + player1.getBirthdate());
                lineaJ2_perfil.setOpacity(1);
                cambiarAvatar2_perfil.setImage(player2.getAvatar());
                cambiarJ2_perfil.setText("Ver perfil de " + player2.getNickName());
                text_pass.setText(player1.getPassword());
                text_vpass.setText(player1.getPassword());
            } else {
                avatar_perfil.setImage(player2.getAvatar());
                user_perfil.setText("@" + player2.getNickName());
                contrase??a_perfil.setText("Contrase??a: ");
                mail_perfil.setText("Correo electr??nico: " + player2.getEmail());
                cumplea??os_perfil.setText("Fecha de Nacimiento: " + player2.getBirthdate());
                lineaJ2_perfil.setOpacity(1);
                cambiarAvatar2_perfil.setImage(player1.getAvatar());
                cambiarJ2_perfil.setText("Ver perfil de " + player1.getNickName());
                text_pass.setText(player2.getPassword());
                text_vpass.setText(player2.getPassword());
            }

        }

    }
    private boolean tema;

    public void initTema(boolean b) {
        tema = b;
        tema_check.selectedProperty().addListener(temaListener);
        tema_check.setSelected(b);

    }
    private ChangeListener temaListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            if (!tema_check.isSelected()) {
                pane.setStyle(" -fx-background-color: #14213c;");
                pane1.setStyle(" -fx-background-color: #14213c;");
                pane2.setStyle(" -fx-background-color: #14213c;");
                pane3.setStyle(" -fx-background-color: #14213c;");
                pane4.setStyle(" -fx-background-color: #14213c;");
                graf_line.getXAxis().setTickLabelFill(Paint.valueOf("#fca311"));
                graf_line.getYAxis().setTickLabelFill(Paint.valueOf("#fca311"));
                graf_bar.getXAxis().setTickLabelFill(Paint.valueOf("#fca311"));
                graf_bar.getYAxis().setTickLabelFill(Paint.valueOf("#fca311"));
                graf_sbar.getXAxis().setTickLabelFill(Paint.valueOf("#fca311"));
                graf_sbar.getYAxis().setTickLabelFill(Paint.valueOf("#fca311"));
                ayuda.setStyle("-fx-text-fill: #fca311;");
                avanzado.setStyle("-fx-text-fill: #fca311;");
                basicas.setStyle("-fx-text-fill: #fca311;");
                text_a.setStyle("-fx-fill: #fca311;");
                text_v.setStyle("-fx-fill: #fca311;");
                text_d.setStyle("-fx-fill: #fca311;");
                filtro_nombre.setStyle("-fx-text-fill: #fca311;");
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setContrast(0.2);
                colorAdjust.setHue(0);
                colorAdjust.setBrightness(0.85);
                colorAdjust.setSaturation(0);
                filtro_nombre.setEffect(colorAdjust);
                rank_nombre.setEffect(colorAdjust);
                date_fin.setEffect(colorAdjust);
                date_ini.setEffect(colorAdjust);
                user_perfil.setEffect(colorAdjust);
                contrase??a_perfil.setEffect(colorAdjust);
                mail_perfil.setEffect(colorAdjust);
                cumplea??os_perfil.setEffect(colorAdjust);
                text_vpass.setEffect(colorAdjust);
                text_pass.setEffect(colorAdjust);
            } else {
                pane.setStyle("-fx-background-color: #EBBCE1;");
                pane1.setStyle("-fx-background-color: #EBBCE1;");
                pane2.setStyle("-fx-background-color: #EBBCE1;");
                pane3.setStyle("-fx-background-color: #EBBCE1;");
                pane4.setStyle("-fx-background-color: #EBBCE1;");
                graf_line.getXAxis().setTickLabelFill(Paint.valueOf("#19213c"));
                graf_line.getYAxis().setTickLabelFill(Paint.valueOf("#19213c"));
                graf_bar.getXAxis().setTickLabelFill(Paint.valueOf("#19213c"));
                graf_bar.getYAxis().setTickLabelFill(Paint.valueOf("#19213c"));
                graf_sbar.getXAxis().setTickLabelFill(Paint.valueOf("#19213c"));
                graf_sbar.getYAxis().setTickLabelFill(Paint.valueOf("#19213c"));
                ayuda.setStyle("-fx-text-fill: #19213c;");
                avanzado.setStyle("-fx-text-fill: #19213c;");
                basicas.setStyle("-fx-text-fill: #19213c;");
                text_a.setStyle("-fx-fill: #19213c;");
                text_v.setStyle("-fx-fill: #19213c;");
                text_d.setStyle("-fx-fill: #19213c;");
                filtro_nombre.setStyle("-fx-text-fill: #19213c;");
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setContrast(1);
                colorAdjust.setHue(1);
                colorAdjust.setBrightness(-0.85);
                colorAdjust.setSaturation(1);
                filtro_nombre.setEffect(colorAdjust);
                rank_nombre.setEffect(colorAdjust);
                date_fin.setEffect(colorAdjust);
                date_ini.setEffect(colorAdjust);
                user_perfil.setEffect(colorAdjust);
                contrase??a_perfil.setEffect(colorAdjust);
                mail_perfil.setEffect(colorAdjust);
                cumplea??os_perfil.setEffect(colorAdjust);
                text_vpass.setEffect(colorAdjust);
                text_pass.setEffect(colorAdjust);
            }
        }
    };

    @FXML
    private void cambiar_perfil(MouseEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        final Stage st = (Stage) source.getScene().getWindow();
        if (player1 == invitado) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent newRoot = loader.load();

            LoginController ld = loader.getController();
            ld.initTema(tema_check.isSelected());
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
            loginAmigo.initTema(tema_check.isSelected());
            loginAmigo.initData(cn4, player1, thisController);
            loginAmigo.initMusic(mediaPlayer, music_check.isSelected());
        } else {
            perf = !perf;
            initPerfil(perf);
        }
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
    private ChangeListener selListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            if (graf_pie.isVisible() || graf_line.isVisible() || graf_sbar.isVisible() || graf_bar.isVisible()) {
                ambas.setVisible(false);
                vic.setVisible(false);
                lose.setVisible(false);
                ambas.setSelected(true);
                vic.setSelected(false);
                lose.setSelected(false);
                graf_bot.setText("Ver tabla");
                text_a.setVisible(false);
                text_d.setVisible(false);
                text_v.setVisible(false);
            } else {
                ambas.setDisable(false);
                vic.setDisable(false);
                lose.setDisable(false);
                graf_bot.setText("Ver gr??fica");
            }
        }
    };
    private ChangeListener posListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            if (avanzado.isVisible()) {
                ed_bot1.setLayoutY(260);
                graf_bot.setLayoutY(260);
            } else {
                ed_bot1.setLayoutY(150);
                graf_bot.setLayoutY(150);
            }
        }
    };
    private ChangeListener tListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            if (historial.isVisible()) {
                graf_bot.setText("Ver gr??fica");

            } else {
                graf_bot.setText("Ver tabla");

            }
        }
    };
    private ChangeListener ttListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            if (graf_bar.isVisible()) {
                cont_bot.setText("Ver WinRate");

            } else {
                cont_bot.setText("Contrincantes");

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
                solo.initTema(tema_check.isSelected());
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
                loginAmigo.initData(cn4, player1, st, thisController);
                loginAmigo.initMusic(mediaPlayer, music_check.isSelected());
                newStage.show();

            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_doble.fxml"));
                Parent newRoot = loader.load();

                Partida_dobleController menu = loader.getController();
                Scene scene = new Scene(newRoot);
                Stage newStage2 = new Stage();

                menu.initData(cn4, player1, player2);
                menu.initMusic(mediaPlayer, music_check.isSelected());

                newStage2.setMinWidth(876);
                newStage2.setMinHeight(866);
                newStage2.setScene(scene);

                newStage2.show();

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
                ld.initTema(tema_check.isSelected());
                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();

                newStage.setScene(scene);
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setResizable(false);
                newStage.show();

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
                confirmCierre.initTema(tema_check.isSelected());
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
            cr.initMusic(mediaPlayer, music_check.isSelected());
            cr.initController(thisController);
            cr.initTema(tema_check.isSelected());
        }
    }

    private void dis() {
        if (historial.isVisible()) {
            ambas.disableProperty().bind(Bindings.isEmpty(filtro_nombre.textProperty()));
            vic.disableProperty().bind(Bindings.isEmpty(filtro_nombre.textProperty()));
            lose.disableProperty().bind(Bindings.isEmpty(filtro_nombre.textProperty()));
            vic.disableProperty().bind(Bindings.isEmpty(filtro_nombre.textProperty()));
            lose.disableProperty().bind(Bindings.isEmpty(filtro_nombre.textProperty()));
            rad_bot.disableProperty().unbind();
            cont_bot.disableProperty().unbind();
        } else {
            rad_bot.disableProperty().bind(Bindings.isEmpty(filtro_nombre.textProperty()));
            cont_bot.disableProperty().bind(Bindings.isEmpty(filtro_nombre.textProperty()));
            ambas.disableProperty().unbind();
            vic.disableProperty().unbind();
            lose.disableProperty().unbind();
        }

    }

    @FXML
    private void avanzado(MouseEvent event) {
        dis();
        if (historial.isVisible()) {

            ambas.setSelected(true);
            vic.setSelected(false);
            lose.setSelected(false);
            text_a.setVisible(true);
            text_d.setVisible(true);
            text_v.setVisible(true);
            ambas.setVisible(true);
            vic.setVisible(true);
            lose.setVisible(true);
        }

        ed_bot1.setLayoutY(260);
        graf_bot.setLayoutY(260);
        avanzado.setVisible(false);
        basicas.setVisible(true);
        filtro_nombre.setVisible(true);
        if (!historial.isVisible()) {

            cont_bot.setVisible(true);
            rad_bot.setVisible(true);
        }

    }

    @FXML
    private void basicas(MouseEvent event) {
        ambas.disableProperty().unbind();
        vic.disableProperty().unbind();
        lose.disableProperty().unbind();
        rad_bot.disableProperty().unbind();
        cont_bot.disableProperty().unbind();
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
        graf_bot.setLayoutY(150);
        avanzado.setVisible(true);
        basicas.setVisible(false);
        filtro_nombre.setVisible(false);
        filtro_nombre.setText(null);
        cont_bot.setVisible(false);
        rad_bot.setVisible(false);
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
        filtro_nombre.setPromptText("Introduce el NickName");
        graf_line.getData().clear();
        graf_pie.getData().clear();
        graf_bar.getData().clear();
        graf_sbar.getData().clear();
        if (historial.isVisible()) {
            hist = cn4.getRoundsPerDay();
            int x = 1;
            for (LocalDate ld = (LocalDate) date_fin.getValue(); ld.compareTo(date_ini.getValue()) >= 0;) {
                List<Round> lista = hist.get(ld);
                if (lista != null) {
                    if (!lista.isEmpty()) {
                        cont = lista.size();
                        if (x == 1) {
                            observableRounds = FXCollections.observableList(new ArrayList<Round>());
                        }
                        x++;
                        try {
                            for (int i = 0; i < cont; i++) {
                                if (lista.get(i) != null) {
                                    if (!filtro_nombre.getText().isEmpty()) {
                                        if (ambas.isSelected()) {

                                            if (lista.get(i).getWinner().getNickName().equals(cn4.getPlayer(filtro_nombre.getText()).getNickName())
                                                    || lista.get(i).getLoser().getNickName().equals(cn4.getPlayer(filtro_nombre.getText()).getNickName())) {
                                                observableRounds.add(lista.get(i));
                                            } else {
                                            }
                                        }
                                        if (vic.isSelected()) {
                                            if (lista.get(i).getWinner().getNickName().equals(cn4.getPlayer(filtro_nombre.getText()).getNickName())) {
                                                observableRounds.add(lista.get(i));
                                            } else {
                                            }
                                        }
                                        if (lose.isSelected()) {
                                            if (lista.get(i).getLoser().getNickName().equals(cn4.getPlayer(filtro_nombre.getText()).getNickName())) {
                                                observableRounds.add(lista.get(i));
                                            } else {
                                            }
                                        }
                                    } else {
                                        observableRounds.add(lista.get(i));
                                    }
                                }
                            }
                        } catch (Exception e) {
                            filtro_nombre.setPromptText("NickName erroneo");
                        }
                    }
                }
                ld = ld.minusDays((long) 1.0);
            }

            historial.setItems(observableRounds);
        } else {
            try {
                if (filtro_nombre.getText().isEmpty()) {
                    graf_pie.setVisible(false);
                    graf_line.setVisible(true);
                    historial.setVisible(false);
                    graf_bar.setVisible(false);
                    graf_sbar.setVisible(false);
                    XYChart.Series serie = new XYChart.Series();

                    int x = 1;
                    lineChartData = FXCollections.observableArrayList();
                    for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                        if (filtro_nombre.getText().isEmpty()) {
                            graf_line.setVisible(true);
                            List<Round> lista = hist.get(ld);

                            if (lista != null) {
                                if (!lista.isEmpty()) {
                                    cont = lista.size();

                                    lineChartData.add(new XYChart.Data(ld.toString(), cont));

                                }
                            } else {
                                lineChartData.add(new XYChart.Data(ld.toString(), 0));
                            }
                            ld = ld.plusDays((long) 1.0);
                        }
                        serie = new Series(lineChartData);
                        graf_line.getData().addAll(serie);
                    }
                } else {
                    if (graf_pie.isVisible()) {
                        graf_pie.setVisible(true);
                        graf_line.setVisible(false);
                        graf_bar.setVisible(false);
                        graf_sbar.setVisible(false);
                        TreeMap<LocalDate, DayRank> map = cn4.getDayRanksPlayer(cn4.getPlayer(filtro_nombre.getText()));
                        int w = 0;
                        int l = 0;
                        pieChartData = FXCollections.observableArrayList();
                        for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                            if (map.containsKey(ld)) {
                                DayRank dr = map.get(ld);
                                w += dr.getWinnedGames();
                                l += dr.getLostGames();
                            }
                            ld = ld.plusDays((long) 1.0);
                        }
                        if (cn4.getPlayer(filtro_nombre.getText()) == null) {
                            filtro_nombre.setPromptText("NickName erroneo");
                        }
                        if (w == 0 && l == 0) {
                            pieChartData.add(new PieChart.Data("No hay partidas", 1));
                        } else {
                            pieChartData.add(new PieChart.Data("Victorias", w));
                            pieChartData.add(new PieChart.Data("Derrotas", l));

                        }
                        graf_pie.setData(pieChartData);
                    }
                    if (graf_bar.isVisible()) {
                        historial.setVisible(false);
                        graf_line.setVisible(false);
                        graf_bar.setVisible(true);
                        graf_sbar.setVisible(false);
                        graf_pie.setVisible(false);
                        ambas.setVisible(false);
                        vic.setVisible(false);
                        lose.setVisible(false);
                        barChartData3 = FXCollections.observableArrayList();
                        TreeMap<LocalDate, DayRank> map = cn4.getDayRanksPlayer(cn4.getPlayer(filtro_nombre.getText()));
                        int n = 0;
                        for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                            if (map.containsKey(ld)) {
                                DayRank dr = map.get(ld);
                                n = dr.getOponents();
                                barChartData3.add(new XYChart.Data(ld.toString(), n));
                            }
                            ld = ld.plusDays((long) 1.0);
                        }
                        Series s = new Series(barChartData3);
                        s.setName("N?? de contrincantes diferentes");

                        graf_bar.getData().addAll(s);
                        rad_bot.setText("Grafica radial");
                        cont_bot.setText("Ver WinRate");
                    }
                    if (graf_line.isVisible()) {
                        historial.setVisible(false);
                        graf_line.setVisible(false);
                        graf_bar.setVisible(true);
                        graf_sbar.setVisible(false);
                        graf_pie.setVisible(false);
                        ambas.setVisible(false);
                        vic.setVisible(false);
                        lose.setVisible(false);
                        barChartData3 = FXCollections.observableArrayList();
                        TreeMap<LocalDate, DayRank> map = cn4.getDayRanksPlayer(cn4.getPlayer(filtro_nombre.getText()));
                        int n = 0;
                        for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                            if (map.containsKey(ld)) {
                                DayRank dr = map.get(ld);
                                n = dr.getOponents();
                                barChartData3.add(new XYChart.Data(ld.toString(), n));
                            }
                            ld = ld.plusDays((long) 1.0);
                        }
                        Series s = new Series(barChartData3);
                        s.setName("N?? de contrincantes diferentes");

                        graf_bar.getData().addAll(s);
                        rad_bot.setText("Grafica radial");
                        cont_bot.setText("Ver WinRate");
                    }
                    if (graf_sbar.isVisible()) {
                        historial.setVisible(false);
                        graf_line.setVisible(false);
                        graf_sbar.setVisible(true);
                        graf_bar.setVisible(false);
                        graf_pie.setVisible(false);
                        ambas.setVisible(false);
                        vic.setVisible(false);
                        lose.setVisible(false);
                        TreeMap<LocalDate, DayRank> map = cn4.getDayRanksPlayer(cn4.getPlayer(filtro_nombre.getText()));
                        int w = 0;
                        int l = 0;
                        Series s1 = new Series();

                        Series s2 = new Series();

                        barChartData1 = FXCollections.observableArrayList();
                        barChartData2 = FXCollections.observableArrayList();
                        for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                            if (map.containsKey(ld)) {
                                DayRank dr = map.get(ld);
                                w += dr.getWinnedGames();
                                l += dr.getLostGames();
                                barChartData1.add(new XYChart.Data(ld.toString(), w));
                                barChartData2.add(new XYChart.Data(ld.toString(), l));
                            }
                            ld = ld.plusDays((long) 1.0);
                        }
                        if (cn4.getPlayer(filtro_nombre.getText()) == null) {
                            filtro_nombre.setPromptText("NickName erroneo");
                        }

                        s1 = new Series(barChartData1);
                        s1.setName("Victorias");
                        s2 = new Series(barChartData2);
                        s2.setName("Derrotas");
                        graf_sbar.getData().addAll(s1, s2);
                    }
                }
            } catch (Exception e) {
                graf_pie.setVisible(false);
                graf_line.setVisible(true);
                historial.setVisible(false);
                graf_bar.setVisible(false);
                graf_sbar.setVisible(false);
                XYChart.Series serie = new XYChart.Series();

                int x = 1;
                lineChartData = FXCollections.observableArrayList();
                for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                    if (filtro_nombre.getText().isEmpty()) {
                        graf_line.setVisible(true);
                        List<Round> lista = hist.get(ld);

                        if (lista != null) {
                            if (!lista.isEmpty()) {
                                cont = lista.size();

                                lineChartData.add(new XYChart.Data(ld.toString(), cont));

                            }
                        } else {
                            lineChartData.add(new XYChart.Data(ld.toString(), 0));
                        }
                        ld = ld.plusDays((long) 1.0);
                    }
                    serie = new Series(lineChartData);
                    graf_line.getData().addAll(serie);
                }
            }

        }

    }

    private ObservableList<Data<LocalDate, Number>> lineChartData;
    private ObservableList<PieChart.Data> pieChartData;
    private ObservableList<Data<LocalDate, Number>> barChartData1;
    private ObservableList<Data<LocalDate, Number>> barChartData2;
    private ObservableList<Data<LocalDate, Number>> barChartData3;

    @FXML
    private void ver_graf(MouseEvent event) {

        filtro_nombre.setPromptText("Introduce el NickName");
        graf_line.getData().clear();
        graf_pie.getData().clear();
        graf_bar.getData().clear();
        graf_sbar.getData().clear();
        text_a.setVisible(false);
        text_d.setVisible(false);
        text_v.setVisible(false);

        hist = cn4.getRoundsPerDay();
        if (!historial.isVisible()) {
            rad_bot.setVisible(false);
            cont_bot.setVisible(false);
            graf_line.setVisible(false);
            graf_pie.setVisible(false);
            graf_bar.setVisible(false);
            graf_sbar.setVisible(false);
            historial.setVisible(true);
            if (basicas.isVisible()) {
                text_a.setVisible(true);
                text_d.setVisible(true);
                text_v.setVisible(true);
                ambas.setVisible(true);
                vic.setVisible(true);
                lose.setVisible(true);
                lose.setDisable(false);
                vic.setDisable(false);
                ambas.setDisable(false);
            }
            hist = cn4.getRoundsPerDay();
            int x = 1;
            for (LocalDate ld = (LocalDate) date_fin.getValue(); ld.compareTo(date_ini.getValue()) >= 0;) {
                List<Round> lista = hist.get(ld);
                if (lista != null) {
                    if (!lista.isEmpty()) {
                        cont = lista.size();
                        if (x == 1) {
                            observableRounds = FXCollections.observableList(new ArrayList<Round>());
                        }
                        x++;
                        try {
                            for (int i = 0; i < cont; i++) {
                                if (lista.get(i) != null) {
                                    if (!filtro_nombre.getText().isEmpty()) {
                                        if (ambas.isSelected()) {

                                            if (lista.get(i).getWinner().getNickName().equals(cn4.getPlayer(filtro_nombre.getText()).getNickName())
                                                    || lista.get(i).getLoser().getNickName().equals(cn4.getPlayer(filtro_nombre.getText()).getNickName())) {
                                                observableRounds.add(lista.get(i));
                                            } else {
                                            }
                                        }
                                        if (vic.isSelected()) {
                                            if (lista.get(i).getWinner().getNickName().equals(cn4.getPlayer(filtro_nombre.getText()).getNickName())) {
                                                observableRounds.add(lista.get(i));
                                            } else {
                                            }
                                        }
                                        if (lose.isSelected()) {
                                            if (lista.get(i).getLoser().getNickName().equals(cn4.getPlayer(filtro_nombre.getText()).getNickName())) {
                                                observableRounds.add(lista.get(i));
                                            } else {
                                            }
                                        }
                                    } else {
                                        observableRounds.add(lista.get(i));
                                    }
                                }
                            }
                        } catch (Exception e) {
                            filtro_nombre.setPromptText("NickName erroneo");
                        }
                    }
                }
                ld = ld.minusDays((long) 1.0);
            }

            historial.setItems(observableRounds);
        } else {
            if (basicas.isVisible()) {
                rad_bot.setVisible(true);
                cont_bot.setVisible(true);
            }

            if (!filtro_nombre.isVisible()) {
                historial.setVisible(false);
                graf_line.setVisible(true);
                graf_bar.setVisible(false);
                graf_pie.setVisible(false);
                Series serie = new Series();

                int x = 1;
                lineChartData = FXCollections.observableArrayList();
                try {
                    for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {

                        if (filtro_nombre.getText().isEmpty()) {
                            graf_line.setVisible(true);
                            List<Round> lista = hist.get(ld);

                            if (lista != null) {
                                if (!lista.isEmpty()) {
                                    cont = lista.size();

                                    lineChartData.add(new Data(ld.toString(), cont));

                                }
                            } else {
                                lineChartData.add(new Data(ld.toString(), 0));
                            }
                            ld = ld.plusDays((long) 1.0);
                        }

                        serie = new Series(lineChartData);
                        graf_line.getData().addAll(serie);
                    }
                } catch (Exception e) {
                    for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {

                        graf_line.setVisible(true);
                        List<Round> lista = hist.get(ld);

                        if (lista != null) {
                            if (!lista.isEmpty()) {
                                cont = lista.size();

                                lineChartData.add(new Data(ld.toString(), cont));

                            }
                        } else {
                            lineChartData.add(new Data(ld.toString(), 0));
                        }
                        ld = ld.plusDays((long) 1.0);

                        serie = new Series(lineChartData);
                        graf_line.getData().addAll(serie);
                    }
                }

            }

            try {
                if (filtro_nombre.getText().isEmpty()) {
                    historial.setVisible(false);
                    graf_line.setVisible(true);
                    graf_bar.setVisible(false);
                    graf_pie.setVisible(false);
                    Series serie = new Series();

                    int x = 1;
                    lineChartData = FXCollections.observableArrayList();
                    for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                        if (filtro_nombre.getText().isEmpty()) {
                            graf_line.setVisible(true);
                            List<Round> lista = hist.get(ld);

                            if (lista != null) {
                                if (!lista.isEmpty()) {
                                    cont = lista.size();

                                    lineChartData.add(new Data(ld.toString(), cont));

                                }
                            } else {
                                lineChartData.add(new Data(ld.toString(), 0));
                            }
                            ld = ld.plusDays((long) 1.0);
                        }
                        serie = new Series(lineChartData);
                        graf_line.getData().addAll(serie);
                    }
                } else {
                    historial.setVisible(false);
                    graf_line.setVisible(false);
                    graf_sbar.setVisible(true);
                    graf_bar.setVisible(false);
                    graf_pie.setVisible(false);
                    ambas.setVisible(false);
                    vic.setVisible(false);
                    lose.setVisible(false);
                    TreeMap<LocalDate, DayRank> map = cn4.getDayRanksPlayer(cn4.getPlayer(filtro_nombre.getText()));
                    int w = 0;
                    int l = 0;
                    Series s1 = new Series();

                    Series s2 = new Series();

                    barChartData1 = FXCollections.observableArrayList();
                    barChartData2 = FXCollections.observableArrayList();
                    for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                        if (map.containsKey(ld)) {
                            DayRank dr = map.get(ld);
                            w += dr.getWinnedGames();
                            l += dr.getLostGames();
                            barChartData1.add(new XYChart.Data(ld.toString(), w));
                            barChartData2.add(new XYChart.Data(ld.toString(), l));
                        }
                        ld = ld.plusDays((long) 1.0);
                    }
                    if (cn4.getPlayer(filtro_nombre.getText()) == null) {
                        filtro_nombre.setPromptText("NickName erroneo");
                    }
                    s1 = new Series(barChartData1);
                    s1.setName("Victorias");
                    s2 = new Series(barChartData2);
                    s2.setName("Derrotas");
                    graf_sbar.getData().addAll(s1, s2);

                }
            } catch (Exception e) {
                historial.setVisible(false);
                graf_line.setVisible(true);
                graf_bar.setVisible(false);
                graf_pie.setVisible(false);
                Series serie = new Series();

                int x = 1;
                lineChartData = FXCollections.observableArrayList();
                try {
                    for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                        if (filtro_nombre.getText().isEmpty()) {
                            graf_line.setVisible(true);
                            List<Round> lista = hist.get(ld);

                            if (lista != null) {
                                if (!lista.isEmpty()) {
                                    cont = lista.size();

                                    lineChartData.add(new Data(ld.toString(), cont));

                                }
                            } else {
                                lineChartData.add(new Data(ld.toString(), 0));
                            }
                            ld = ld.plusDays((long) 1.0);
                        }
                        serie = new Series(lineChartData);
                        graf_line.getData().addAll(serie);
                    }
                } catch (Exception ex) {
                    for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {

                        graf_line.setVisible(true);
                        List<Round> lista = hist.get(ld);

                        if (lista != null) {
                            if (!lista.isEmpty()) {
                                cont = lista.size();

                                lineChartData.add(new Data(ld.toString(), cont));

                            }
                        } else {
                            lineChartData.add(new Data(ld.toString(), 0));
                        }
                        ld = ld.plusDays((long) 1.0);
                    }
                    serie = new Series(lineChartData);
                    graf_line.getData().addAll(serie);

                }
            }

        }
        dis();
    }

    @FXML
    private void contrinc(MouseEvent event) {
        if (cn4.exitsNickName(filtro_nombre.getText())) {
            graf_line.getData().clear();
            graf_pie.getData().clear();
            graf_bar.getData().clear();
            graf_sbar.getData().clear();

            vic.setVisible(false);
            lose.setVisible(false);
            ambas.setVisible(false);
            text_a.setVisible(false);
            text_d.setVisible(false);
            text_v.setVisible(false);

            TreeMap<LocalDate, DayRank> map = cn4.getDayRanksPlayer(cn4.getPlayer(filtro_nombre.getText()));
            if (!graf_bar.isVisible()) {
                historial.setVisible(false);
                graf_line.setVisible(false);
                graf_bar.setVisible(true);
                graf_sbar.setVisible(false);
                graf_pie.setVisible(false);
                ambas.setVisible(false);
                vic.setVisible(false);
                lose.setVisible(false);
                barChartData3 = FXCollections.observableArrayList();
                int n = 0;
                for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                    if (map.containsKey(ld)) {
                        DayRank dr = map.get(ld);
                        n = dr.getOponents();
                        barChartData3.add(new XYChart.Data(ld.toString(), n));
                    }
                    ld = ld.plusDays((long) 1.0);
                }
                Series s = new Series(barChartData3);
                s.setName("N?? de contrincantes diferentes");

                graf_bar.getData().addAll(s);
                rad_bot.setText("Grafica radial");
                cont_bot.setText("Ver WinRate");
            } else {
                historial.setVisible(false);
                graf_line.setVisible(false);
                graf_bar.setVisible(false);
                graf_sbar.setVisible(true);
                graf_pie.setVisible(false);
                ambas.setVisible(false);
                vic.setVisible(false);
                lose.setVisible(false);

                int w = 0;
                int l = 0;
                Series s1 = new Series();

                Series s2 = new Series();

                barChartData1 = FXCollections.observableArrayList();
                barChartData2 = FXCollections.observableArrayList();
                for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                    if (map.containsKey(ld)) {
                        DayRank dr = map.get(ld);
                        w += dr.getWinnedGames();
                        l += dr.getLostGames();
                        barChartData1.add(new XYChart.Data(ld.toString(), w));
                        barChartData2.add(new XYChart.Data(ld.toString(), l));
                    }
                    ld = ld.plusDays((long) 1.0);
                }
                if (cn4.getPlayer(filtro_nombre.getText()) == null) {
                    filtro_nombre.setPromptText("NickName erroneo");
                }

                s1 = new Series(barChartData1);
                s1.setName("Victorias");
                s2 = new Series(barChartData2);
                s2.setName("Derrotas");
                graf_sbar.getData().addAll(s1, s2);
                rad_bot.setText("Grafica radial");
                cont_bot.setText("Contrincantes");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Historial");
            alert.setHeaderText("NickName");
            alert.setContentText("No hay ning??n jugador asociado a ese NickName");

            alert.showAndWait();
        }

    }

    @FXML
    private void radial(MouseEvent event
    ) {
        if (cn4.exitsNickName(filtro_nombre.getText())) {
            graf_line.getData().clear();
            graf_pie.getData().clear();
            graf_bar.getData().clear();
            graf_sbar.getData().clear();

            vic.setVisible(false);
            lose.setVisible(false);
            ambas.setVisible(false);
            text_a.setVisible(false);
            text_d.setVisible(false);
            text_v.setVisible(false);
            cont_bot.setVisible(true);

            if (!graf_pie.isVisible()) {
                historial.setVisible(false);
                graf_line.setVisible(false);
                graf_bar.setVisible(false);
                graf_sbar.setVisible(false);
                graf_pie.setVisible(true);
                graf_bar.setVisible(false);
                TreeMap<LocalDate, DayRank> map = cn4.getDayRanksPlayer(cn4.getPlayer(filtro_nombre.getText()));
                int w = 0;
                int l = 0;
                pieChartData = FXCollections.observableArrayList();
                for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                    if (map.containsKey(ld)) {
                        DayRank dr = map.get(ld);
                        w += dr.getWinnedGames();
                        l += dr.getLostGames();
                    }
                    ld = ld.plusDays((long) 1.0);
                }
                if (cn4.getPlayer(filtro_nombre.getText()) == null) {
                    filtro_nombre.setPromptText("NickName erroneo");
                }
                if (w == 0 && l == 0) {
                    pieChartData.add(new PieChart.Data("No hay partidas", 1));
                } else {
                    pieChartData.add(new PieChart.Data("Victorias", w));
                    pieChartData.add(new PieChart.Data("Derrotas", l));

                }
                graf_pie.setData(pieChartData);
                rad_bot.setText("Ver diagrama");
            } else {
                historial.setVisible(false);
                graf_line.setVisible(false);
                graf_bar.setVisible(false);
                graf_sbar.setVisible(true);
                graf_pie.setVisible(false);
                ambas.setVisible(false);
                vic.setVisible(false);
                lose.setVisible(false);
                TreeMap<LocalDate, DayRank> map = cn4.getDayRanksPlayer(cn4.getPlayer(filtro_nombre.getText()));
                int w = 0;
                int l = 0;
                Series s1 = new Series();

                Series s2 = new Series();

                barChartData1 = FXCollections.observableArrayList();
                barChartData2 = FXCollections.observableArrayList();
                for (LocalDate ld = (LocalDate) date_ini.getValue(); ld.compareTo(date_fin.getValue()) <= 0;) {
                    if (map.containsKey(ld)) {
                        DayRank dr = map.get(ld);
                        w += dr.getWinnedGames();
                        l += dr.getLostGames();
                        barChartData1.add(new XYChart.Data(ld.toString(), w));
                        barChartData2.add(new XYChart.Data(ld.toString(), l));
                    }
                    ld = ld.plusDays((long) 1.0);
                }
                if (cn4.getPlayer(filtro_nombre.getText()) == null) {
                    filtro_nombre.setPromptText("NickName erroneo");
                }

                s1 = new Series(barChartData1);
                s1.setName("Victorias");
                s2 = new Series(barChartData2);
                s2.setName("Derrotas");
                graf_sbar.getData().addAll(s1, s2);
                rad_bot.setText("Grafica radial");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Historial");
            alert.setHeaderText("NickName");
            alert.setContentText("No hay ning??n jugador asociado a ese NickName");

            alert.showAndWait();
        }

    }

    private void buscar(MouseEvent event) {
        observablePlayers = FXCollections.observableList(cn4.getConnect4Ranking());

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
    private void help(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Como usar las opciones avanzadas del historial");
        alert.setContentText("Para poder filtrar por victoria o derrota en la tabla, hay que introducir el NickName del jugador que deseas buscar.\n"
                + "Si no introduces un nombre y accedes a los gr??ficos, se mostrar?? una gr??fica de las partidas jugadas en el periodo de tiempo indicado.\n"
                + "Si introduces un nombre podr??s ver su WinRate y la cantidad de oponentes diferentes que ha tenido ese jugador en el periodo de tiempo marcado.");

        alert.showAndWait();
    }

    @FXML
    private void editar_perfil(MouseEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        final Stage st = (Stage) source.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editar_perfil.fxml"));
        Parent newRoot = loader.load();

        Editar_perfilController editar = loader.getController();
        if (player2 == null) {
            editar.initData(cn4, st, player1, thisController, editar);
        } else {
            editar.initData(cn4, st, player1, player2, thisController, editar, perf);
        }
        editar.initMusic(mediaPlayer, music_check.isSelected());
        editar.initTema(tema_check.isSelected());
        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.show();
    }

}
