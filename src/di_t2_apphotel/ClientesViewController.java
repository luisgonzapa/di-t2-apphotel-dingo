/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t2_apphotel;

import entidades.Cliente;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Jose Carlos PC
 */
public class ClientesViewController implements Initializable {

    
    @FXML
    private AnchorPane rootAgendaView;
    @FXML
    private TableColumn<Cliente, String> columnDNI;
    @FXML
    private TableColumn<Cliente, String> columnNombre;
    @FXML
    private TableColumn<Cliente, String> columnDireccion;
    @FXML
    private TableColumn<Cliente, String> columnLocalidad;
    @FXML
    private TableColumn<Cliente, String> columnProvincia;
    @FXML
    private TableColumn<Cliente, String> columnTelefono;
    
    private EntityManager entityManager;
    @FXML
    private TableView<Cliente> tableViewHotel;
    @FXML
    private Button volver;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // Asocia columnas a propiedades de la clase entidad ( en este caso Persona ) 
    // Propiedad de la clase persona se muestra en columnNombre
    columnDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
    columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    columnLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
    // cellData.getValue()obtiene el objeto Persona correspondiente a una determinada fila del TableView.
    // mÃ©todo getProvincia()para obtener el objeto Provincia
    // mÃ©todo getNombre() para obtener como String el nombre de la provincia,
    columnProvincia.setCellValueFactory( cellData-> 
    {
        SimpleStringProperty property = new SimpleStringProperty();
        // Comprueba que si una determinada persona no tiene asociada ninguna provincia
        // Evita un NullPointerException 
        if(cellData.getValue().getProvincia() != null) 
        {
            property.setValue(cellData.getValue().getProvincia().getNombre());
        }
         return property;
    });
    columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    
     
    }    
 
//================================================================================================    
    
    // consulta a la base de datos y rellena el TableView.
    // La lista al TableView con el mÃ©todo setItems, que requiere convertir la lista de tipo List al tipo ->
    // -> ObservableArrayList, lo cual se hace con el mÃ©todo FXCollections.observableArrayList.
    public void cargarTodasPersonas() 
    {
        Query queryClienteFindAll = entityManager.createNamedQuery("Cliente.findAll");
        List<Cliente> listCliente = queryClienteFindAll.getResultList();
        tableViewHotel.setItems(FXCollections.observableArrayList(listCliente));
    }  
 
//================================================================================================
    
    // Instancia EntityManager
    // El controlador obtiene acceso al objeto EntityManager atraves del metodo "set"
    public void setEntityManager(EntityManager entityManager) 
    {
        this.entityManager = entityManager;
        cargarTodasPersonas();
    }

//================================================================================================
    
    // Metodo volver a menu principal
    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) volver.getScene().getWindow();
        stage.close();
    }
}
