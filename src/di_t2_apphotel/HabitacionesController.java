/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DI_T2_AppHotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaquin
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
    @FXML
    private Button cleanbtn;
    @FXML
    private Button savebtn;
    @FXML
    private Button cancelbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionButtonLimpiar(ActionEvent event) {
        
        Stage stage = (Stage) cleanbtn.getScene().getWindow();
      
    }

    @FXML
    private void onActionButtonAceptar(ActionEvent event) {
        
        Stage stage = (Stage) savebtn.getScene().getWindow();
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {

        Stage stage = (Stage) cancelbtn.getScene().getWindow();
        stage.close();
        
        
    }
    
}
