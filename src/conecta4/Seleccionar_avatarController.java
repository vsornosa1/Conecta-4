package conecta4;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Alex & Sento
 */
public class Seleccionar_avatarController {

    @FXML
    private ImageView flecha;
    @FXML
    private Text invalid_mail;
    @FXML
    private ImageView avatar1;
    @FXML
    private ImageView avatar2;
    @FXML
    private ImageView avatar3;
    @FXML
    private ImageView avatar4;
    @FXML
    private ImageView avatar5;
    @FXML
    private ImageView avatar6;
    @FXML
    private ImageView avatar7;
    @FXML
    private ImageView avatar8;
    @FXML
    private ImageView avatar9;
    @FXML
    private ImageView avatar10;

    private File avatarFile;
    private Image avatarImg;

    private Stage stg;
    private Stage oldStage;
    private RegistroController registro;
    private Editar_perfilController editar;
    @FXML
    private JFXButton boton_log;
    @FXML
    private ImageView tupc;

    public void initController(RegistroController registro) {
        this.registro = registro;
    }
    public void initController(Editar_perfilController editar) {
        this.editar = editar;
    }

    @FXML
    private void atras(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    void initStage(Stage s, Stage os) {
        stg = s;
        oldStage = os;
    }

    @FXML
    private void ampliar1(MouseEvent event) {
        avatar1.setScaleX(1.18);
        avatar1.setScaleY(1.18);
    }

    @FXML
    private void reducir1(MouseEvent event) {
        avatar1.setScaleX(1);
        avatar1.setScaleY(1);
    }

    @FXML
    private void ampliar2(MouseEvent event) {
        avatar2.setScaleX(1.18);
        avatar2.setScaleY(1.18);
    }

    @FXML
    private void reducir2(MouseEvent event) {
        avatar2.setScaleX(1);
        avatar2.setScaleY(1);
    }

    @FXML
    private void ampliar3(MouseEvent event) {
        avatar3.setScaleX(1.18);
        avatar3.setScaleY(1.18);
    }

    @FXML
    private void reducir3(MouseEvent event) {
        avatar3.setScaleX(1);
        avatar3.setScaleY(1);
    }

    @FXML
    private void ampliar4(MouseEvent event) {
        avatar4.setScaleX(1.18);
        avatar4.setScaleY(1.18);
    }

    @FXML
    private void reducir4(MouseEvent event) {
        avatar4.setScaleX(1);
        avatar4.setScaleY(1);
    }

    @FXML
    private void ampliar5(MouseEvent event) {
        avatar5.setScaleX(1.18);
        avatar5.setScaleY(1.18);
    }

    @FXML
    private void reducir5(MouseEvent event) {
        avatar5.setScaleX(1);
        avatar5.setScaleY(1);
    }

    @FXML
    private void ampliar6(MouseEvent event) {
        avatar6.setScaleX(1.18);
        avatar6.setScaleY(1.18);
    }

    @FXML
    private void reducir6(MouseEvent event) {
        avatar6.setScaleX(1);
        avatar6.setScaleY(1);
    }

    @FXML
    private void ampliar7(MouseEvent event) {
        avatar7.setScaleX(1.18);
        avatar7.setScaleY(1.18);
    }

    @FXML
    private void reducir7(MouseEvent event) {
        avatar7.setScaleX(1);
        avatar7.setScaleY(1);
    }

    @FXML
    private void ampliar8(MouseEvent event) {
        avatar8.setScaleX(1.18);
        avatar8.setScaleY(1.18);
    }

    @FXML
    private void reducir8(MouseEvent event) {
        avatar8.setScaleX(1);
        avatar8.setScaleY(1);
    }

    @FXML
    private void ampliar9(MouseEvent event) {
        avatar9.setScaleX(1.18);
        avatar9.setScaleY(1.18);
    }

    @FXML
    private void reducir9(MouseEvent event) {
        avatar9.setScaleX(1);
        avatar9.setScaleY(1);
    }

    @FXML
    private void ampliar10(MouseEvent event) {
        avatar10.setScaleX(1.18);
        avatar10.setScaleY(1.18);
    }

    @FXML
    private void reducir10(MouseEvent event) {
        avatar10.setScaleX(1);
        avatar10.setScaleY(1);
    }

    @FXML
    private void selectAvatar1(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar1.png");
        cerrarVentana(event);
    }

    @FXML
    private void selecAvatar2(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar2.png");
        cerrarVentana(event);
    }

    @FXML
    private void selectAvatar3(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar3.png");
        cerrarVentana(event);
    }

    @FXML
    private void selectAvatar4(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar4.png");
        cerrarVentana(event);
    }

    @FXML
    private void selectAvatar5(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar5.png");
        cerrarVentana(event);
    }

    @FXML
    private void selectAvatar6(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar6.png");
        cerrarVentana(event);
    }

    @FXML
    private void selectAvatar7(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar7.png");
        cerrarVentana(event);
    }

    @FXML
    private void selectAvatar8(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar8.png");
        cerrarVentana(event);
    }

    @FXML
    private void selectAvatar9(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar9.png");
        cerrarVentana(event);
    }

    @FXML
    private void selectAvatar10(MouseEvent event) throws IOException {
        avatarFile = new File("src/images/avatares/avatar10.png");
        cerrarVentana(event);
    }

    private void cerrarVentana(MouseEvent event) throws IOException {
        avatarImg = new Image(avatarFile.toURI().toString());
        //if(registro != null) registro.initAvatar(avatarImg);
        editar.initAvatar(avatarImg);

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void cerrarVentana(Image miAvatar) throws IOException {
        avatarImg = miAvatar;
        if(registro != null) registro.initAvatar(avatarImg);
        if(editar != null) editar.initAvatar(avatarImg);

        stg.close();
    }

    private final File src = new File("src/images/avatares/avatar_personalizado.png");

    @FXML
    private void file(MouseEvent event) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
            File pngImage = fileChooser.showOpenDialog(new Stage());
            Image miAvatar = new Image(pngImage.toURI().toString());
            try {
                Files.copy(pngImage.toPath(), src.toPath(), StandardCopyOption.REPLACE_EXISTING);
                cerrarVentana(miAvatar);

            } catch (IOException ex) {}
        } catch (Exception e) {}
    }
}
