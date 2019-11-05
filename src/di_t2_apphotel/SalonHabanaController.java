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

        radioButtonBanquete.setSelected(false);
        radioButtonJornada.setSelected(false);
        radioButtonCongreso.setSelected(false);
        limpiarDatosReserva();
        datePickerEvento.setValue(null);
        
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
        limpiarDatosReserva();
        radioButtonJornada.setSelected(false);
        radioButtonCongreso.setSelected(false);
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
        limpiarDatosReserva();
        radioButtonBanquete.setSelected(false);
        radioButtonCongreso.setSelected(false);
        labelPersonas.setDisable(false);
        textFieldNumPer.setDisable(false);
        labelTipo.setVisible(true);
        labelOpcion.setText("Jornada");
        labelOpcion.setVisible(true);
    }

    @FXML
    private void isSelectedCongreso(ActionEvent event) {
        limpiarDatosReserva();
        radioButtonJornada.setSelected(false);
        radioButtonBanquete.setSelected(false);
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
    
    private void limpiarDatosReserva(){
        labelPersonas.setDisable(true);
        labelTipoCocina.setDisable(true);
        checkBoxHabi.setSelected(false);
        checkBoxHabi.setDisable(true);
        textFieldNumPer.setText("");
        textFieldNumPer.setDisable(true);
        labelNumHab.setDisable(true);
        textFieldNumHab.setText("");
        textFieldNumHab.setDisable(true);
        labelNumDias.setDisable(true);
        TextFieldNumDias.setText("");
        TextFieldNumDias.setDisable(true);
        labelTipo.setVisible(false);
        labelOpcion.setVisible(false);
        comboBoxTipo.setDisable(true);
    }
    
    
}
