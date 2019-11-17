/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t2_apphotel;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void start(Stage primaryStage) throws IOException {

        StackPane rootMain = new StackPane();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DI_T2_AppHotel.fxml"));
        Pane rootAgendaView = fxmlLoader.load();
        rootMain.getChildren().add(rootAgendaView);

        DI_T2_AppHotelController controladorPrincipal = (DI_T2_AppHotelController) fxmlLoader.getController();

        emf = Persistence.createEntityManagerFactory("DI_T2_AppHotelPU");
        em = emf.createEntityManager();

        controladorPrincipal.setEntityManager(em);

        Scene scene = new Scene(rootMain, 600, 400);
        primaryStage.setTitle("AppHotel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {

        em.close();
        emf.close();
        try {
            DriverManager.getConnection("jdbc:derby:BDHotel;shutdown=true");
        } catch (SQLException ex) {
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
