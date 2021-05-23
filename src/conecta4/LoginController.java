package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;

import model.*;

/**
 * @author Alex & Sento
 */
public class LoginController implements Initializable {

    @FXML
    private TextField text_user;
    @FXML
    private TextField text_pass;
    @FXML
    private Hyperlink link_recu;
    @FXML
    private JFXButton boton_log;
    @FXML
    private ImageView img;

    private Player player1, invitado;
    private Connect4 cn4;
    private MediaPlayer mediaPlayer;
    @FXML
    private JFXToggleButton music;

    private boolean log_guest;
    private Stage oldStage;
    @FXML
    private Text error;

    private Menu_principalController menu;

    File f_avatar1 = new File("src/images/avatares/avatar1.png");
    private final Image avatar1 = new Image(f_avatar1.toURI().toString());
    File f_avatar5 = new File("src/images/avatares/avatar5.png");
    private final Image avatar5 = new Image(f_avatar5.toURI().toString());
    File f_avatar3 = new File("src/images/avatares/avatar3.png");
    private final Image avatar3 = new Image(f_avatar3.toURI().toString());
    File f_avatar6 = new File("src/images/avatares/avatar6.png");
    private final Image avatar6 = new Image(f_avatar6.toURI().toString());

    File f_avatarDef = new File("src/images/avatares/avatar10.png");
    private final Image avatarDef = new Image(f_avatarDef.toURI().toString());
    @FXML
    private JFXButton vb;
    @FXML
    private ImageView v;
    @FXML
    private ImageView nv;
    @FXML
    private JFXTextField text_vpass;
    
    private int from;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text_vpass.textProperty().bindBidirectional(text_pass.textProperty());
        from=0;
        try {
            cn4 = Connect4.getSingletonConnect4();
            invitado = cn4.getPlayer("invitado");
//            cn4.removeAllData();
//            cn4.registerPlayer("PlayfulPaco", "email1@domain.es", "Aa-123456789",avatar3,LocalDate.now().minusYears(18), 0);
//            cn4.registerPlayer("PepeGaming", "email2@domain.es", "Aa-123456789",avatar1,LocalDate.now().minusYears(18), 0);
//            cn4.registerPlayer("JoseGaming", "email3@domain.es", "Aa-123456789",avatar5,LocalDate.now().minusYears(18), 0);
//            cn4.registerPlayer("a", "a", "a", avatar6,LocalDate.now().minusYears(18), 0);
//            cn4.registerPlayer("invitado", "invitado@domain.es", "invitado", avatarDef, LocalDate.MIN, 0);

        } catch (Connect4DAOException e) {
        }
    }

    public void initData(Connect4 cn4, Stage st, Menu_principalController m) {
        log_guest = true;
        menu = m;
        oldStage = st;
        this.cn4 = cn4;
        from=3;
    }

    public void initMusic(MediaPlayer mp, boolean b) {
        mediaPlayer = mp;
        music.setSelected(b);
        music.selectedProperty().addListener(changeListener);
    }

    private ChangeListener changeListener = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldVal, Object newVal) {
            if (music.isSelected()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        }
    };

    @FXML
    private void log(MouseEvent event) throws Exception {
        player1 = cn4.loginPlayer(text_user.getText(), text_pass.getText());
        if (player1 != null && !player1.equals(invitado)) {
            menu_load(event, player1);
        } else {
            error.setVisible(true);
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
            newStage.setResizable(false);
            newStage.show();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @FXML
    private void invitado(MouseEvent event) {
        menu_load(event, invitado);
    }

    private void menu_load(MouseEvent event, Player pl) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        if (log_guest) {
            menu.initData(cn4, pl);
            menu.initMusic(mediaPlayer, music.isSelected());
            stage.close();
        } else {
            try {
                // 1. Loader
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
                Parent newRoot = loader.load();

                // 2. Controller, scene & stage
                Menu_principalController menu_p = loader.getController();
                menu_p.initController(menu_p);
                menu_p.initData(cn4, pl);
                menu_p.initMusic(mediaPlayer, music.isSelected());

                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();
                newStage.setScene(scene);
                newStage.setResizable(false);

                // 3. Mostrar nueva ventana
                newStage.show();

                // 4. Cierre de la ventana
                stage.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void registrarse(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registro.fxml"));
        Parent newRoot = loader.load();

        RegistroController registro = loader.getController();
        registro.initController(registro);
        registro.initData(cn4,menu,from);
        registro.initMusic(mediaPlayer, music.isSelected());
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
