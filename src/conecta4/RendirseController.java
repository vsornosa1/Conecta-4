package conecta4;

import DBAccess.Connect4DAOException;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
 * @author Alex & Sento
 */
public class RendirseController {

    @FXML
    private ImageView flecha;

    private Connect4 cn4;
    private Player player1, player2, invitado;
    private Stage stage;
    private boolean turno_player1;
    private boolean modo_2jugadores;
    @FXML
    private JFXButton aceptar_derrota;
    @FXML
    private Text invalid_mail1;
    @FXML
    private Text invalid_mail11;

    private boolean existsPlayer2;
    

    // 1Jugador -> Rendirse
    public void initData(Connect4 con4, Player p1, Stage st) {
        cn4 = con4;
        player1 = p1;
        stage = st;
        modo_2jugadores = false;
        turno_player1 = true;
        existsPlayer2 = false;
    }
    private boolean b;
    private MediaPlayer mediaPlayer;
    public void initMusic(MediaPlayer mp,boolean b) {
        mediaPlayer = mp;
        this.b=b;
    }

    // 1Jugador con J2 en la sombra -> Rendirse
    public void initData(Connect4 con4, Player p1, Player p2, Stage st) {
        cn4 = con4;
        player1 = p1;
        player2 = p2;
        stage = st;
        modo_2jugadores = false;
        existsPlayer2 = true;
    }

    // 2Jugadores -> Rendirse
    public void initData(Connect4 con4, Player p1, Player p2, Stage st, boolean turno_p1) {
        cn4 = con4;
        player1 = p1;
        player2 = p2;
        invitado = cn4.getPlayer("invitado");
        stage = st;
        turno_player1 = turno_p1;
        modo_2jugadores = true;
        existsPlayer2 = true;
    }

    @FXML
    private void volver_menu(MouseEvent event) throws IOException, Connect4DAOException {
        if (turno_player1) {
            if (modo_2jugadores) {
                player2.plusPoints(cn4.getPointsRound());
                cn4.regiterRound(LocalDate.now().atTime(LocalTime.MIN), player2, player1);
            }
        } else {
            player1.plusPoints(cn4.getPointsRound());
            cn4.regiterRound(LocalDate.now().atTime(LocalTime.MIN), player1, player2);
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_principal.fxml"));
        Parent newRoot = loader.load();
        Menu_principalController menu = loader.getController();

        Scene scene = new Scene(newRoot);
        Stage newStage = new Stage();
        
        if (modo_2jugadores) {
            if(player1.equals(invitado)) {
                menu.initData(cn4, player2);
            }
            else if (player2.equals(invitado)) {
                menu.initData(cn4, player1);
            }
            else {
                menu.initData(cn4, player1, player2);
            }

        } else {
            if (existsPlayer2) {
                menu.initData(cn4, player1, player2);
            } else {
                menu.initData(cn4, player1);
            }
        }
        menu.initMusic(mediaPlayer,b);
        menu.initController(menu);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.show();
        final Node sr = (Node) event.getSource();
        final Stage st = (Stage) sr.getScene().getWindow();
        this.stage.close();
        st.close();
    }

    @FXML
    private void atras(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
