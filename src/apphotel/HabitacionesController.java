/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class HabitacionesController implements Initializable {

    @FXML
    private AnchorPane rootSecundaria;
    @FXML
    private DatePicker datePickerLlegada;
    @FXML
    private DatePicker datePickerSalida;
    @FXML
    private ComboBox<?> comboBoxTipo;
    @FXML
    private RadioButton radioButtonAlojamiento;
    @FXML
    private RadioButton radioButtonMedia;
    @FXML
    private RadioButton radioButtonCompleta;
    @FXML
    private CheckBox checkBoxFumador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionButtonLimpiar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonAceptar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
    }
    
}
