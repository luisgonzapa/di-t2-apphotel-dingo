package di_t2_apphotel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;


public class DI_T2_AppHotelController implements Initializable 
{

    private EntityManager entityManager;
    @FXML
    private Menu clientes;
    @FXML
    private Menu reservas;
    @FXML
    private MenuItem habitaciones;
    @FXML
    private MenuItem salon;
    @FXML
    private Menu ayuda;
    private Stage primaryStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
    }

    public void setEntityManager(EntityManager entityManager) 
    {
        this.entityManager = entityManager;
    }    

    @FXML
    private void habitacion(ActionEvent event) throws IOException 
    {
        // Léeme el archivo fxml
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Habitaciones.fxml"));
        Parent root1 = null;
        try 
        {
            root1 = (Parent)fxmlLoader.load();
            // Pasamos el entityManager a controldaro de Habitaciones
            HabitacionesController habitaciones = (HabitacionesController)fxmlLoader.getController();
            habitaciones.setEntityManager(entityManager);
        } catch (IOException ex) {
            Logger.getLogger(DI_T2_AppHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacía
        Stage stage= new Stage();
        //Y ahora dentro del Stage me metes la escena que anteriormente hemos leído y metido en root1
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));

        // Y ahora le digo que me muestres el stage
        stage.show();


    }

    @FXML
    private void salon(ActionEvent event)
    {
        // Léeme el archivo fxml
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("SalonHabana.fxml"));
        Parent root1 = null;
        try 
        {
            root1 = (Parent)fxmlLoader.load();
            SalonHabanaController salon = (SalonHabanaController)fxmlLoader.getController();
            salon.setEntityManager(entityManager);
        } catch (IOException ex) {
            Logger.getLogger(DI_T2_AppHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacía
        Stage stage= new Stage();
        //Y ahora dentro del Stage me metes la escena que anteriormente hemos leído y metido en root1
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        // Y ahora le digo que me muestres el stage
        stage.show();
    }
    @FXML
    private void cliente(ActionEvent event) throws IOException 
    {
        // LÃ©eme el archivo fxml
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("ClientesView.fxml"));
        Parent root1 = null;
        try 
        {
            root1 = (Parent)fxmlLoader.load();
            // Pasamos el entityManager a controldaro de Habitaciones
            ClientesViewController clientes = (ClientesViewController)fxmlLoader.getController();
            clientes.setEntityManager(entityManager);
        } catch (IOException ex) {
            Logger.getLogger(DI_T2_AppHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacÃ­a
        Stage stage= new Stage();
        //Y ahora dentro del Stage me metes la escena que anteriormente hemos leÃ­do y metido en root1
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));

        // Y ahora le digo que me muestres el stage
        stage.show();
    }
}
