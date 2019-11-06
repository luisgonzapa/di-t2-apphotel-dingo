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
    private TextField textFieldDNI;
    private TextField textFieldNombre;
    private TextField textFieldDireccion;
    private ComboBox<?> comboBoxTipo;
    private TextField textFieldTelefono;
    private RadioButton radioButtonBanquete;
    private RadioButton radioButtonJornada;
    private RadioButton radioButtonCongreso;
    private TextField textFieldNumPer;
    private CheckBox checkBoxHabi;
    private TextField textFieldNumHab;
    private TextField TextFieldNumDias;
    private Label labelTipo;
    private Label labelPersonas;
    private Label labelTipoCocina;
    private Label labelNumHab;
    private Label labelNumDias;
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

    private void isSelectedJornada(ActionEvent event) {
        radioButtonBanquete.setDisable(true);
        radioButtonCongreso.setDisable(true);
        labelPersonas.setDisable(false);
        textFieldNumPer.setDisable(false);
        labelTipo.setVisible(true);
        labelOpcion.setText("Jornada");
        labelOpcion.setVisible(true);
    }

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
