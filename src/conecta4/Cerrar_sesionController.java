package conecta4;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;

/**
 * FXML Controller class
 *
 * @author Alex & Sento
 */
public class Cerrar_sesionController implements Initializable, ActionListener {

    @FXML
    private ImageView flecha;
    @FXML
    private Text invalid_mail;
    @FXML
    private ImageView avatar_player1;
    @FXML
    private ImageView avatar_player2;
    @FXML
    private JFXCheckBox check1;
    @FXML
    private JFXCheckBox check2;
    @FXML
    private JFXButton cerrar;

    private Connect4 cn4;
    private Player player1, player2;
    private Stage oldStage;
    private Menu_principalController thisController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cerrar.setDisable(true);
        check1.focusTraversableProperty();
        cerrar.setText("Selecciona al menos un jugador");
        check1.selectedProperty().addListener(changeListener);
        check2.selectedProperty().addListener(changeListener);
    }

    public void initController(Menu_principalController controller) {
        thisController = controller;
    }

    private boolean b;
    private MediaPlayer mediaPlayer;

    public void initMusic(MediaPlayer mp, boolean b) {
        mediaPlayer = mp;
        this.b = b;
    }

    public void initData(Connect4 cn4, Player pl1, Player pl2, Stage stage) {
        this.cn4 = cn4;
        player1 = pl1;
        player2 = pl2;
        avatar_player1.setImage(player1.getAvatar());
        avatar_player2.setImage(player2.getAvatar());
        check1.setText(player1.getNickName());
        check2.setText(player2.getNickName());
        oldStage = stage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cerrar.setDisable(false);
    }

    private ChangeListener changeListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            if (check1.isSelected() && check2.isSelected()) {
                cerrar.setDisable(false);
                cerrar.setText("Cerrar ambas sesiones");
            } else if (check1.isSelected() || check2.isSelected()) {
                cerrar.setDisable(false);
                if (!check1.isSelected()) {
                    cerrar.setText("Cerrar sesión de " + player2.getNickName());
                } else {
                    cerrar.setText("Cerrar sesión de " + player1.getNickName());
                }
            } else {
                cerrar.setDisable(true);
                cerrar.setText("Selecciona al menos un jugador");
            }

        }
    };

    @FXML
    private void cerrar(MouseEvent event) {
        if (check1.isSelected() && check2.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent newRoot = loader.load();
                LoginController lg = loader.getController();

                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();
                lg.initMusic(mediaPlayer, b);
                newStage.setScene(scene);
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setResizable(false);
                newStage.show();
                final Node sr = (Node) event.getSource();
                final Stage st = (Stage) sr.getScene().getWindow();
                oldStage.close();
                st.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else if (check1.isSelected() && !check2.isSelected()) {
//            try {
//                // 1. Loader
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
//                Parent newRoot = loader.load();
//                
//                // 2. Controller, scene & stage
//                Menu_principalController menu_p = loader.getController();
//                menu_p.initData(cn4, player2);
//                menu_p.initMusic(mediaPlayer, b);
//                menu_p.initController(menu_p);
//                Scene scene = new Scene(newRoot);
//                Stage newStage = new Stage();
//                newStage.setScene(scene);
//                newStage.setResizable(false);
//                // 3. Mostrar nueva ventana
//                newStage.show();
//                
//                // 4. Cierre de la ventana
            thisController.initData(cn4, player2);
            thisController.initPerfil(true);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
//                oldStage.close();
            stage.close();

//            } catch (IOException e) {
//                System.out.println(e);
//            }
        } else if (!check1.isSelected() && check2.isSelected()) {
//        try {
//                // 1. Loader
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
//                Parent newRoot = loader.load();
//                
//                // 2. Controller, scene & stage
//                Menu_principalController menu_p = loader.getController();
//                menu_p.initData(cn4, player1);
//                menu_p.initMusic(mediaPlayer, b);
//                menu_p.initController(menu_p);
//                Scene scene = new Scene(newRoot);
//                Stage newStage = new Stage();
//                newStage.setScene(scene);
//                newStage.setResizable(false);
//                // 3. Mostrar nueva ventana
//                newStage.show();
            thisController.initData(cn4, player1);
            thisController.initPerfil(true);
            // 4. Cierre de la ventana
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
//                oldStage.close();
            stage.close();
//
//            } catch (IOException e) {
//                System.out.println(e);
//            }
        }
    }

    @FXML
    private void atras(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void selec1(MouseEvent event) {
        if (check1.isSelected()) {
            check1.setSelected(false);
        } else {
            check1.setSelected(true);
        }
    }

    @FXML
    private void selec2(MouseEvent event) {
        if (check2.isSelected()) {
            check2.setSelected(false);
        } else {
            check2.setSelected(true);
        }
    }

}
