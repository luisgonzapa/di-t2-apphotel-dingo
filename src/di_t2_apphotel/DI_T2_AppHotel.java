/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t2_apphotel;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Ailla
 */
public class DI_T2_AppHotel extends Application {
       
    @Override
    public void start(Stage primaryStage) 
    {
        Scene escena = new Scene(new VBox(), 600, 400);
        escena.setFill(Color.OLDLACE);   
        
        // Menu principal ( instancio MenuBar )
        MenuBar menuPrincipal = new MenuBar();
        
        // Menu
        Menu menu1 = new Menu("Clientes");
        Menu menu2 = new Menu("Reservas");
        Menu menu3 = new Menu("Ayuda");
        
        
        // Menu de Encuestas
        Menu habitaciones = new Menu("Habitaciones"); 
        Menu salon = new Menu("Salón Habana"); 

        
        // Añadimos los submenus al menu2 ( Reservas )
        menu2.getItems().add(habitaciones);
        menu2.getItems().add(salon);
     
        // Aniade a MenuBar ( "Clientes", "Reservas" y "Ayuda" )
        menuPrincipal.getMenus().addAll(menu1,menu2,menu3);
        // Añade menuPrincipal a VBox
        ((VBox) escena.getRoot()).getChildren().addAll(menuPrincipal);
        
/*       
        // Imagen de fondo
        Image img = new Image(getClass().getResourceAsStream("Recursos/imagenPrincipal.jpg"));
        ImageView im = new ImageView(img);
        ((VBox) escena.getRoot()).getChildren().addAll(im);
 */       
       
       
        // Muestra todos los componentes
        primaryStage.setTitle("AppHotel");
        primaryStage.setScene(escena);
        primaryStage.show();
        
        
        // Si pulsa en habitaciones
        habitaciones.setOnAction((ActionEvent e) -> {
        
            // Léeme el archivo fxml
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Habitaciones.fxml"));
            Parent root1 = null;
            try 
            {
                root1 = (Parent)fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(DI_T2_AppHotel.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacía
            Stage stage= new Stage();

            //Y ahora dentro del Stage me metes la escena que anteriormente hemos leído y metido en root1
            stage.setScene(new Scene(root1));

            // Y ahora le digo que me muestres el stage
            stage.show();

           
        });
        
        
        // Si pulsa salón
         // Si pulsa en habitaciones
        salon.setOnAction((ActionEvent e) -> {
        
            // Léeme el archivo fxml
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("SalonHabana.fxml"));
            Parent root1 = null;
            try 
            {
                root1 = (Parent)fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(DI_T2_AppHotel.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacía
            Stage stage= new Stage();

            //Y ahora dentro del Stage me metes la escena que anteriormente hemos leído y metido en root1
            stage.setScene(new Scene(root1));

            // Y ahora le digo que me muestres el stage
            stage.show();

           
        });
        
     
    }  // Fin de metodo start

   
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}  // Fin de clase
