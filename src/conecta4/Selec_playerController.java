package conecta4;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class Selec_playerController {
    @FXML
    private ImageView flecha;
    @FXML
    private ImageView avatar_player1;
    @FXML
    private ImageView avatar_player2;
    @FXML
    private Text nombre1;
    @FXML
    private Text nombre2;
    
    private Connect4 cn4;
    private Player player1, player2;

    
    public void initData(Connect4 cn4, Player p1, Player p2){
        this.cn4 = cn4;
        player1 = p1;
        player2 = p2;
        avatar_player1.setImage(player1.getAvatar());
        avatar_player2.setImage(player2.getAvatar());
        nombre1.setText(player1.getNickName());
        nombre2.setText(player2.getNickName());
    }
    

    @FXML
    private void selec(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_cpu.fxml"));
            Parent newRoot = loader.load();
            Partida_cpuController solo = loader.getController();

            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            solo.initData(cn4, player1, player2);

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
            final Node sr = (Node) event.getSource();
            final Stage st = (Stage) sr.getScene().getWindow();
            st.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void selecc(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("partida_cpu.fxml"));
            Parent newRoot = loader.load();
            Partida_cpuController solo = loader.getController();

            Scene scene = new Scene(newRoot);
            Stage newStage = new Stage();

            solo.initData(cn4, player2, player1);

            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
            final Node sr = (Node) event.getSource();
            final Stage st = (Stage) sr.getScene().getWindow();
            st.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
   
    @FXML
    private void atras(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
