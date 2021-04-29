package conecta4;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

import model.Player;
import model.Connect4;

/**
 * @author Alex & Sento
 */
public class Popup1Controller implements Initializable {

    @FXML
    private JFXButton ok;
    @FXML
    private TextField enter;
    @FXML
    private Text code;
    @FXML
    private Text pass_warning;
    @FXML
    private Text pass;
    @FXML
    private Pane nuevoCodigo;
    
    private Connect4 cn4;
    private Player player1;


    private Stage stage;
    
    private boolean completado=false;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        code.setText(Integer.toString(((int)(Math.random()*Math.pow(10,6)))));
    }    
    
    public void initData(Connect4 cn4, Player player1, Stage stage){
        this.cn4 = cn4;
        this.player1 = player1;
        this.stage = stage;
    }

    @FXML
    private void obtener(MouseEvent event) {
        if (completado) {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            this.stage.close();
            stage.close();
        }
        
        if(enter.getText().equals(code.getText())) {
            pass_warning.setText("");
            pass.setText("Contraseña: " + player1.getPassword());
            completado = true;
            ok.setText("Ir a iniciar sesión");
        } else {
            pass_warning.setText("Código incorrecto");
        }
    }

    @FXML
    private void atras(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        if(!completado){
            stage.close();
        } else {
            this.stage.close();
            stage.close();
        }
    }
    
    @FXML
    public void nuevoCodigo(MouseEvent event) {
        if(!completado) {
            code.setText(Integer.toString(((int)(Math.random() * Math.pow(10,6)))));
        }
    }
    
}
