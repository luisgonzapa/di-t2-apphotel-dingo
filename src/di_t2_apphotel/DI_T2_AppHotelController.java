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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class DI_T2_AppHotelController implements Initializable 
{

    @FXML
    private MenuBar MenuBarPrincipal;
    @FXML
    private Menu MenuClientes;
    @FXML
    private Menu MenuReservas;
    @FXML
    private MenuItem MenuHabitaciones;
    @FXML
    private MenuItem MenuSalon;
    @FXML
    private Menu menuAyuda;
    @FXML
    private ImageView ImageViewPrincipal;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
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
            } catch (IOException ex) {
                Logger.getLogger(DI_T2_AppHotelController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacía
            Stage stage= new Stage();

            //Y ahora dentro del Stage me metes la escena que anteriormente hemos leído y metido en root1
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
            } catch (IOException ex) {
                Logger.getLogger(DI_T2_AppHotelController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacía
            Stage stage= new Stage();

            //Y ahora dentro del Stage me metes la escena que anteriormente hemos leído y metido en root1
            stage.setScene(new Scene(root1));

            // Y ahora le digo que me muestres el stage
            stage.show();
    }
    
}
