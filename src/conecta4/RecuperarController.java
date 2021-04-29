package conecta4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

import model.*;

/**
 * @author Alex & Sento
 */
public class RecuperarController {
    @FXML
    private TextField text_user;
    @FXML
    private TextField text_mail;
    @FXML
    private Text invalid_mail;
    @FXML
    private Text texto_invalidUser;
    @FXML
    private JFXButton boton_recu;
    @FXML
    private ImageView flecha;
    
    private Connect4 cn4;
    private Player player1;
    
    
    public void initData(Connect4 cn4){
        this.cn4 = cn4;
    }

    @FXML
    private void recu(MouseEvent event) {
        try {
            player1 = cn4.getPlayer(text_user.getText());
            if(player1 == null) {
                texto_invalidUser.setText("Usuario no encontrado");
            } else texto_invalidUser.setText("");
            
            if(text_mail.getText().equals(player1.getEmail())) { 
                text_mail.setText("");invalid_mail.setText("");
                try { 
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("popup1.fxml"));
                    Parent newRoot = loader.load();
                
                    Popup1Controller p1 = loader.getController();
                    
                    Scene scene = new Scene(newRoot);
                    Stage newStage = new Stage();
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    p1.initData(cn4, player1, stage);
                    newStage.setScene(scene);
                    newStage.setResizable(false);
                    newStage.show();
                    
                 } catch(IOException e){ System.out.println(e); }
            } else {
                if(!text_mail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    invalid_mail.setText("Formato de correo inválido");
                } else {
                    invalid_mail.setText("Correo incorrecto para @" + text_user.getText());
                }
                
            }
        } catch(Exception e) {
            System.out.println("Ningun jugador se corresponde con ese nombre de usuario"); 
        }
    }

    @FXML
    private void atras(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
