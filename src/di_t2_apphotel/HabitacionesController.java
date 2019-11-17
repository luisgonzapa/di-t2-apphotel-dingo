/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t2_apphotel;

import entidades.Cliente;
import entidades.Provincia;
import entidades.ReservasHab;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

/**
 * FXML Controller class
 *
 *
 */
public class HabitacionesController implements Initializable {

    @FXML
    private AnchorPane rootSecundaria;
    @FXML
    private DatePicker datePickerLlegada;
    @FXML
    private DatePicker datePickerSalida;
    @FXML
    private ComboBox<String> comboBoxTipo;
    @FXML
    private RadioButton radioButtonAlojamiento;
    @FXML
    private RadioButton radioButtonMedia;
    @FXML
    private RadioButton radioButtonCompleta;
    @FXML
    private CheckBox checkBoxFumador;
    @FXML
    private TextField dni;
    @FXML
    private TextField nombre;
    @FXML
    private TextField direccion;
    @FXML
    private TextField localidad;
    @FXML
    private ComboBox<Provincia> provincia;

    private Button limpiar;
    private Button aceptar;
    @FXML
    private Button cancelar;

    private Cliente cliente;
    private Provincia provinciaClase;

    private ReservasHab reserva;
    private EntityManager entityManager;
    @FXML
    private Spinner<Integer> numeroHabitaciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        deshabilitarCampos(true);

        dni.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                List<Provincia> resultListProv = entityManager.createNamedQuery("Provincia.findAll").getResultList();
                provincia.setItems(FXCollections.observableList(resultListProv));

                provincia.setCellFactory((ListView<Provincia> l) -> new ListCell<Provincia>() {
                    @Override
                    protected void updateItem(Provincia provincia, boolean empty) {
                        super.updateItem(provincia, empty);
                        if (provincia == null || empty) {
                            setText("");
                        } else {
                            setText(provincia.getCodigo() + "-" + provincia.getNombre());
                        }
                    }
                });

                provincia.setConverter(new StringConverter<Provincia>() {

                    public String toString(Provincia provincia) {
                        if (provincia == null) {
                            return null;
                        } else {
                            return provincia.getCodigo() + "-" + provincia.getNombre();
                        }
                    }

                    public Provincia fromString(String userId) {
                        return null;
                    }
                });
                
                ArrayList<String> tipolist= new ArrayList<String>();
                tipolist.add("Sencilla");
                tipolist.add("Doble uso individual");
                tipolist.add("Suite Junior");
                tipolist.add("Suite");
                tipolist.add("Presidencial");
                comboBoxTipo.setItems(FXCollections.observableList(tipolist));
                
                String cad = dni.getText();
                if (cad.length() <= 9) {
                    cliente = entityManager.find(Cliente.class, cad);
                    if (cliente == null) {
                        deshabilitarCampos(false);
                        dni.setText(cad);
                    } else {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirmar");
                        alert.setHeaderText("¿Es usted este cliente?");
                        alert.setContentText("El cliente " + cliente.getNombre() + " con Dni " + cliente.getDni());
                        Optional<ButtonType> result = alert.showAndWait();
                        deshabilitarCampos(false);

                        if (result.get() == ButtonType.OK) {
                            List<ReservasHab> resultList = entityManager.createNamedQuery("ReservasHab.findByCliente").setParameter("dniCliente", cliente).getResultList();
                            if (resultList.isEmpty()) {
                                mostrarDatosCliente();
                            } else {
                                reserva = resultList.get(0);
                                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmar");
                                alert.setHeaderText("¿Desea Modificar Su Reserva?");
                                alert.setContentText("Reserva de habitacion " + reserva.getTipoHabitacion() + " con id " + reserva.getCod());
                                Optional<ButtonType> result2 = alert.showAndWait();
                                if (result2.get() == ButtonType.OK) {
                                    mostrarDatosReserva();
                                } else {
                                    reserva = null;
                                    mostrarDatosCliente();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @FXML
    private void onActionButtonLimpiar(ActionEvent event) {
        dni.setText("");
        nombre.setText("");
        nombre.setDisable(true);
        direccion.setText("");
        direccion.setDisable(true);
        localidad.setText("");
        localidad.setDisable(true);
        provincia.setValue(null);
        provincia.setVisible(true);
        datePickerLlegada.setValue(LocalDate.now());
        datePickerLlegada.setDisable(true);
        datePickerSalida.setValue(LocalDate.now());
        datePickerSalida.setDisable(true);
        checkBoxFumador.selectedProperty().set(false);
        checkBoxFumador.setDisable(true);
        radioButtonAlojamiento.selectedProperty().set(false);
        radioButtonAlojamiento.setDisable(true);
        radioButtonCompleta.selectedProperty().set(false);
        radioButtonCompleta.setDisable(true);
        radioButtonMedia.selectedProperty().set(false);
        radioButtonMedia.setDisable(true);
        numeroHabitaciones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        numeroHabitaciones.setDisable(true);
    }

//======================================================================================    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @FXML
    private void onActionButtonAceptar(ActionEvent event) {
        boolean nuevoCliente = true;
        boolean nuevaReserva = true;

        // Comprueba que todos los campos estan introducidos 
        if (dni.getText() == null || dni.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido el DNI, revisa los campos.");
            alert.showAndWait();

        } else if (nombre == null || nombre.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido el nombre, revisa los campos.");
            alert.showAndWait();
        } else if (provincia == null || provincia.getValue() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido la provincia, revisa los campos.");
            alert.showAndWait();
        } else if (direccion == null || direccion.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido la dirección, revisa los campos.");
            alert.showAndWait();
        } else if (localidad == null || localidad.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido la localidad, revisa los campos.");
            alert.showAndWait();
        } else if (datePickerLlegada.getValue() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido la fecha de entrada, revisa los campos.");
            alert.showAndWait();
        } else if (datePickerSalida.getValue() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido la fecha de salida, revisa los campos.");
            alert.showAndWait();
        }else if(!radioButtonAlojamiento.isSelected() && !radioButtonMedia.isSelected() && !radioButtonCompleta.isSelected()) 
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has seleccionado el regimen, revisa los campos.");
            alert.showAndWait(); 
        }
        else {
            // PRIMERO COMPROBAR SI EXISTE CLIENTE Y RESERVA 
            if (cliente != null) {
                nuevoCliente = false;
            } else {
                nuevoCliente = true;
                cliente = new Cliente();
            }

            if (reserva != null) {
                nuevaReserva = false;
            } else {
                nuevaReserva = true;
                reserva = new ReservasHab();
            }

            // SI NO...................
            entityManager.getTransaction().begin();

            //   setCliente(entityManager, cliente, nuevoCliente);
            cliente.setDni(dni.getText());
            cliente.setNombre(nombre.getText());
            cliente.setDireccion(direccion.getText());
            cliente.setLocalidad(localidad.getText());
            cliente.setProvincia(provincia.getValue());

            try {//Los datos introducidos son correctos
                if (nuevoCliente) {
                    entityManager.persist(cliente);
                } else {
                    entityManager.merge(cliente);
                }
                entityManager.getTransaction().commit();
            } catch (RollbackException ex) { // Los datos introducidos no cumplen los requisitos de la BD
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("No se han podido guardar los cambios. "
                        + "Compruebe los datos y asegurese de que cumple todos los requisitos.");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            }

            entityManager.getTransaction().begin();
            // Fechas
            if (datePickerLlegada.getValue() != null) {
                LocalDate localDate = datePickerLlegada.getValue();
                ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
                Instant instant = zonedDateTime.toInstant();
                Date date = Date.from(instant);
                reserva.setFechaEntrada(date);
            } else {
                reserva.setFechaEntrada(null);
            }

            if (datePickerSalida.getValue() != null) {
                LocalDate localDate = datePickerSalida.getValue();
                ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
                Instant instant = zonedDateTime.toInstant();
                Date date = Date.from(instant);
                reserva.setFechaSalida(date);
            } else {
                reserva.setFechaSalida(null);
            }

            // Fumador
            if (checkBoxFumador.isSelected()) {
                reserva.setFumador(true);
            } else {
                reserva.setFumador(false);
            }

            // Regimen de reserva
            if (radioButtonAlojamiento.isSelected()) {
                reserva.setRegimen("Alojamiento y desayuno");
            } else if (radioButtonMedia.isSelected()) {
                reserva.setRegimen("Media pension");
            } else if (radioButtonCompleta.isSelected()) {
                reserva.setRegimen("Pension completa");
            }

            reserva.setNumHabitaciones(numeroHabitaciones.getValue());  // Falta id de spinner
            reserva.setTipoHabitacion(comboBoxTipo.getValue());
            
            reserva.setDniCliente(cliente);

            try {//Los datos introducidos son correctos
                if(nuevaReserva)
                    entityManager.persist(reserva);
                else
                    entityManager.merge(reserva);
                entityManager.getTransaction().commit();
            } catch (RollbackException ex) { // Los datos introducidos no cumplen los requisitos de la BD
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("No se han podido guardar los cambios. "
                        + "Compruebe los datos y asegurese de que cumple todos los requisitos.");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            }

            // Reserva realizada 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Se ha realizado la reserva correctamente, contactaremos brevemente con usted, gracias.");
            alert.showAndWait();
        }

    }

//======================================================================================    
    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
    } // Fin de cancelar

//======================================================================================
    public void mostrarDatosCliente() {
        dni.setText(cliente.getDni());
        nombre.setText(cliente.getNombre());
        direccion.setText(cliente.getDireccion());
        localidad.setText(cliente.getLocalidad());
        provincia.setValue(cliente.getProvincia());
    }

    public void mostrarDatosReserva() {
        mostrarDatosCliente();
        datePickerLlegada.setValue(reserva.getFechaEntrada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        datePickerSalida.setValue(reserva.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        numeroHabitaciones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, reserva.getNumHabitaciones()));
        comboBoxTipo.setValue(reserva.getTipoHabitacion());
        if (reserva.getRegimen() == null) {
            radioButtonAlojamiento.setSelected(false);
            radioButtonCompleta.setSelected(false);
            radioButtonMedia.setSelected(false);
        } else if (reserva.getRegimen().equalsIgnoreCase("Alojamiento y desayuno")) {
            radioButtonAlojamiento.setSelected(true);
            radioButtonCompleta.setSelected(false);
            radioButtonMedia.setSelected(false);
        } else if (reserva.getRegimen().equalsIgnoreCase("Media pension")) {
            radioButtonAlojamiento.setSelected(false);
            radioButtonCompleta.setSelected(false);
            radioButtonMedia.setSelected(true);
        } else {
            radioButtonAlojamiento.setSelected(false);
            radioButtonCompleta.setSelected(true);
            radioButtonMedia.setSelected(false);
        }

        if (reserva.getFumador()) {
            checkBoxFumador.setSelected(true);
        }

    }

    public void deshabilitarCampos(boolean bool) {
        onActionButtonLimpiar(new ActionEvent());
        nombre.setDisable(bool);
        direccion.setDisable(bool);
        localidad.setDisable(bool);
        provincia.setDisable(bool);
        datePickerLlegada.setDisable(bool);
        datePickerSalida.setDisable(bool);
        numeroHabitaciones.setDisable(bool);
        comboBoxTipo.setDisable(bool);
        radioButtonAlojamiento.setDisable(bool);
        radioButtonCompleta.setDisable(bool);
        radioButtonMedia.setDisable(bool);
        checkBoxFumador.setDisable(bool);
    }

    @FXML
    private void alojamientoSelected(ActionEvent event) {
        radioButtonAlojamiento.setSelected(true);
        radioButtonMedia.setSelected(false);
        radioButtonCompleta.setSelected(false);
    }

    @FXML
    private void mediaSelected(ActionEvent event) {
        radioButtonAlojamiento.setSelected(false);
        radioButtonMedia.setSelected(true);
        radioButtonCompleta.setSelected(false);
    }

    @FXML
    private void completaSelected(ActionEvent event) {
        radioButtonAlojamiento.setSelected(false);
        radioButtonMedia.setSelected(false);
        radioButtonCompleta.setSelected(true);
    }
}  // Fin de clase
