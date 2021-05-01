package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.media.Media;
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

    /* DATOS PEPE:
    String name = "Pepe";
    String pass = "1234";
    String email = "email@domain.es";
    LocalDate birthdate = LocalDate.now().minusYears(18);
    int points = 10; 
     */
 /* DATOS jose:
    String name = "jose";
    String pass = "123";
    String email = "email2@domain.es";
    LocalDate birthdate = LocalDate.now().minusYears(19);
    int points = 10; 
     */
 /* DATOS invitado:
    String name = "invitado";
    String pass = "invitado";
    String email = "invitado@domain.es";
    LocalDate birthdate = LocalDate.now().minusYears(19);
    int points = 10; 
    File f_avatar1 = new File("src/avatars/avatar1.png"); 
    private final Image avatarInvitado = new Image(f_avatar1.toURI().toString());
     */
    private Player player1, invitado;
    private Connect4 cn4;
    private MediaPlayer mediaPlayer;
    @FXML
    private JFXToggleButton music;

    private boolean log_guest;
    private Stage oldStage;
    @FXML
    private Text error;
    
    File f_avatar1 = new File("src/images/avatares/avatar3.png"); 
    private final Image avatar1 = new Image(f_avatar1.toURI().toString());
    File f_avatar2 = new File("src/images/avatares/avatar6.png"); 
    private final Image avatar2 = new Image(f_avatar2.toURI().toString());
    File f_avatar3 = new File("src/images/avatares/avatar5.png"); 
    private final Image avatar3 = new Image(f_avatar3.toURI().toString());
    File f_avatar4 = new File("src/images/avatares/avatar2.png"); 
    private final Image avatar4 = new Image(f_avatar4.toURI().toString());
    File f_avatard = new File("src/avatars/avatar1.png"); 
    private final Image avatard = new Image(f_avatard.toURI().toString());
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cn4 = Connect4.getSingletonConnect4();
            
//            cn4.removeAllData();
//            cn4.registerPlayer("PlayfulPaco", "email1@domain.es", "Aa-123456789",avatar1,LocalDate.now().minusYears(18), 0);
//            cn4.registerPlayer("PepeGaming", "email2@domain.es", "Aa-123456789",avatar2,LocalDate.now().minusYears(18), 0);
//            cn4.registerPlayer("JoseGaming", "email3@domain.es", "Aa-123456789",avatar3,LocalDate.now().minusYears(18), 0);
//            cn4.registerPlayer("a", "a", "a",avatar4,LocalDate.now().minusYears(18), 0);
//            cn4.registerPlayer("invitado", "", "invitado",avatard,LocalDate.MIN, 0);
                
                invitado = cn4.getPlayer("invitado");
                
        } catch (Connect4DAOException e) {
        }
    }

    public void initData(Stage st) {
        log_guest = true;
        oldStage = st;
    }

    public void initMusic(MediaPlayer mp,boolean b) {
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
            try {
                // 1. Loader
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
                Parent newRoot = loader.load();

                // 2. Controller, scene & stage
                Menu_principalController menu_p = loader.getController();

                menu_p.initData(cn4, player1);
                menu_p.initMusic(mediaPlayer,music.isSelected());
                Scene scene = new Scene(newRoot);
                Stage newStage = new Stage();
                newStage.setScene(scene);
                newStage.setResizable(false);

                if (log_guest) {
                    oldStage.close();
                }

                // 3. Mostrar nueva ventana
                newStage.show();

                // 4. Cierre de la ventana
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            } catch (IOException e) {
                System.out.println(e);
            }
        } else error.setVisible(true);
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
        try {
            // 1. Loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
            Parent newRoot = loader.load();

            // 2. Controller, scene & stage
            Menu_principalController menu_p = loader.getController();
            menu_p.initData(cn4, invitado);
            menu_p.initMusic(mediaPlayer,music.isSelected());
            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();
            
            newStage.setScene(scene);
            newStage.setResizable(false);
            if (log_guest) {
                oldStage.close();
            }
            // 3. Mostrar nueva ventana
            newStage.show();

            // 4. Cierre de la ventana
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
