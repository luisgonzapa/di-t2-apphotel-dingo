package di_t2_apphotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


public class SalonHabanaController implements Initializable 
{
    @FXML
    private AnchorPane rootSecundaria;
    @FXML
    private TextField textFieldDNI;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldDireccion;
    @FXML
    private ComboBox<?> comboBoxTipo;
    @FXML
    private TextField textFieldTelefono;
    @FXML
    private RadioButton radioButtonBanquete;
    @FXML
    private RadioButton radioButtonJornada;
    @FXML
    private RadioButton radioButtonCongreso;
    @FXML
    private TextField textFieldNumPer;
    @FXML
    private CheckBox checkBoxHabi;
    @FXML
    private TextField textFieldNumHab;
    @FXML
    private DatePicker datePickerEvento;
    @FXML
    private TextField TextFieldNumDias;
    @FXML
    private Label labelTipo;
    @FXML
    private Label labelPersonas;
    @FXML
    private Label labelTipoCocina;
    @FXML
    private Label labelNumHab;
    @FXML
    private Label labelNumDias;
    @FXML
    private Label labelOpcion;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    @FXML
    private void onActionButtonLimpiar(ActionEvent event) {
        textFieldDNI.setText("");
        textFieldNombre.setText("");
        textFieldDireccion.setText("");
        textFieldTelefono.setText("");
        
    }

    @FXML
    private void onActionButtonAceptar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        StackPane rootMain =(StackPane) rootSecundaria.getScene().getRoot();
        rootMain.getChildren().remove(rootSecundaria);    
    }

    @FXML
    private void isSelectedBanquete(ActionEvent event) {
        radioButtonJornada.setDisable(true);
        radioButtonCongreso.setDisable(true);
        labelPersonas.setDisable(false);
        textFieldNumPer.setDisable(false);
        labelTipoCocina.setDisable(false);
        comboBoxTipo.setDisable(false);
        labelTipo.setVisible(true);
        labelOpcion.setText("Banquete");
        labelOpcion.setVisible(true);
    }

    @FXML
    private void isSelectedJornada(ActionEvent event) {
        radioButtonBanquete.setDisable(true);
        radioButtonCongreso.setDisable(true);
        labelPersonas.setDisable(false);
        textFieldNumPer.setDisable(false);
        labelTipo.setVisible(true);
        labelOpcion.setText("Jornada");
        labelOpcion.setVisible(true);
    }

    @FXML
    private void isSelectedCongreso(ActionEvent event) {
        radioButtonBanquete.setDisable(true);
        radioButtonJornada.setDisable(true);
        labelPersonas.setDisable(false);
        textFieldNumPer.setDisable(false);
        checkBoxHabi.setDisable(false);
        labelNumHab.setDisable(false);
        textFieldNumHab.setDisable(false);
        labelNumDias.setDisable(false);
        TextFieldNumDias.setDisable(false);
        labelTipo.setVisible(true);
        labelOpcion.setText("Congreso");
        labelOpcion.setVisible(true);
    }
    
    
    
}
