package di_t2_apphotel;

import entidades.Cliente;
import entidades.ReservasSalon;
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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public class SalonHabanaController implements Initializable {

    @FXML
    private AnchorPane rootSecundaria;
    @FXML
    private TextField textFieldDNI;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldDireccion;
    @FXML
    private ComboBox<String> comboBoxTipo;
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
    @FXML
    private DatePicker datePickerEvento;

    private Cliente cliente = null;
    private ReservasSalon reserva;
    private EntityManager entityManager;
    @FXML
    private Button bcancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limpiarDatosReserva();
        limpiardatospersonales();
        textFieldDNI.setOnAction(ev
                -> {
            String cad = textFieldDNI.getText();
            if (cad.length() <= 9) {
                cliente = entityManager.find(Cliente.class, cad);
                if (cliente == null) {
                    habilitarCampos();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmar");
                    alert.setHeaderText("¿Es usted este cliente?");
                    alert.setContentText("El cliente " + cliente.getNombre() + " con Dni " + cliente.getDni());
                    Optional<ButtonType> result = alert.showAndWait();
                    habilitarCampos();

                    if (result.get() == ButtonType.OK) {
                        List<ReservasSalon> resultList = entityManager.createNamedQuery("ReservasSalon.findByCliente").setParameter("dniCliente", cliente).getResultList();
                        if (resultList.isEmpty()) {
                            mostrarDatosCliente();
                        } else {
                            reserva = resultList.get(0);
                            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmar");
                            alert.setHeaderText("¿Desea Modificar Su Reserva?");
                            alert.setContentText("Reserva de salon " + reserva.getTipo() + " con id " + reserva.getCod());
                            Optional<ButtonType> result2 = alert.showAndWait();
                            if (result2.get() == ButtonType.OK) {
                                if (reserva.getTipo().equals("Banquete")) {
                                    isSelectedBanquete(ev);
                                } else if (reserva.getTipo().equals("Jornada")) {
                                    isSelectedJornada(ev);
                                } else if (reserva.getTipo().equals("Congreso")) {
                                    isSelectedCongreso(ev);
                                }
                                mostrarDatosReserva();

                            } else {
                                reserva = null;
                                mostrarDatosCliente();
                            }
                        }
                    }
                }
            }
            ArrayList<String> list = new ArrayList<String>();
            list.add("No precisa");
            list.add("Bufet vegetariano");
            list.add("Bufet normal");
            list.add("Carta");
            list.add("Pedir cita con chef");
            comboBoxTipo.setItems(FXCollections.observableList(list));
        });
    }

    @FXML
    private void onActionButtonLimpiar(ActionEvent event) {
        limpiarDatosReserva();
        limpiardatospersonales();
        datePickerEvento.setValue(null);

    }

    @FXML
    private void onActionButtonAceptar(ActionEvent event) {
        boolean clienten;
        boolean reservan;
        boolean guardar = true;
        // Comprueba que todos los campos estan introducidos 
        if (textFieldDNI.getText() == null || textFieldDNI.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido el DNI, revisa los campos.");
            alert.showAndWait();
        } else if (textFieldNombre == null || textFieldNombre.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido el nombre, revisa los campos.");
            alert.showAndWait();
        } else if (textFieldTelefono == null || textFieldTelefono.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido el telefono, revisa los campos.");
            alert.showAndWait();
        } else if (textFieldDireccion == null || textFieldDireccion.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido la dirección, revisa los campos.");
            alert.showAndWait();
        } else if (datePickerEvento.getValue() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has introducido la fecha, revisa los campos.");
            alert.showAndWait();
        } else if (!radioButtonBanquete.isSelected() && !radioButtonCongreso.isSelected() && !radioButtonJornada.isSelected()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No has seleccionado un tipo de reserva.");
            alert.showAndWait();
        } else {
            if (cliente == null) {
                cliente = new Cliente();
                clienten = true;
            } else {
                clienten = false;
            }
            if (reserva == null) {
                reserva = new ReservasSalon();
                reservan = true;
            } else {
                reservan = false;
            }

            entityManager.getTransaction().begin();
            cliente.setDni(textFieldDNI.getText());
            reserva.setDniCliente(cliente);
            cliente.setNombre(textFieldNombre.getText());
            cliente.setDireccion(textFieldDireccion.getText());
            try{
                cliente.setTelefono(Integer.parseInt(textFieldTelefono.getText()));
            }
            catch(NumberFormatException ex)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Telefono introducido no válido");
                alert.showAndWait();
                entityManager.getTransaction().rollback();
                return;
            }

            // Fechas
            LocalDate localDate = datePickerEvento.getValue();
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            Date date = Date.from(instant);
            reserva.setFecha(date);

            // Tipo de reserva
            if (radioButtonBanquete.isSelected()) {
                reserva.setTipo("Banquete");
            } else if (radioButtonCongreso.isSelected()) {
                reserva.setTipo("Congreso");
            } else if (radioButtonJornada.isSelected()) {
                reserva.setTipo("Jornada");
            }

            if (textFieldNumHab.getText() != "") {
                try {
                    reserva.setNumHabitaciones(Integer.parseInt(textFieldNumHab.getText()));
                } catch (NumberFormatException ex) {
                    reserva.setNumHabitaciones(0);
                }
            } else {
                reserva.setNumHabitaciones(0);
            }

            if (TextFieldNumDias.getText() != "") {
                try {
                    reserva.setNumDias(Integer.parseInt(TextFieldNumDias.getText()));
                } catch (NumberFormatException ex) {
                    reserva.setNumHabitaciones(0);
                }
            } else {
                reserva.setNumDias(0);
            }

            reserva.setTipoCocina(comboBoxTipo.getValue());
            try {

                if (reserva.getTipo() == "Jornada" || reserva.getTipo() == "Congreso" && Integer.parseInt(textFieldNumPer.getText()) < 50) {
                    reserva.setNumPersonas(Integer.parseInt(textFieldNumPer.getText()));
                }
            } catch (RollbackException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("El numero de personas para reservar este tipo debe ser menor de 50");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            } catch (NumberFormatException ex) {
                reserva.setNumPersonas(1);
            }

            try {

                if (reserva.getTipo() == "Banquete" && Integer.parseInt(textFieldNumPer.getText()) < 100) {
                    reserva.setNumPersonas(Integer.parseInt(textFieldNumPer.getText()));
                }
            } catch (RollbackException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("El numero de personas para reservar este tipo debe ser menor de 100");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            } catch (NumberFormatException ex) {
                reserva.setNumHabitaciones(1);
            }

            try {//Los datos introducidos son correctos

                if (clienten) {

                    entityManager.persist(cliente);
                } else {
                    entityManager.merge(cliente);
                }

                if (reservan) {

                    entityManager.persist(reserva);
                } else {
                    entityManager.merge(reserva);
                }

                entityManager.getTransaction().commit();
            } catch (RollbackException ex) { // Los datos introducidos no cumplen los requisitos de la BD
                guardar = false;
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("No se han podido guardar los cambios. "
                        + "Compruebe los datos y asegurese de que cumple todos los requisitos.");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            }
            if (guardar == true) {
                // Reserva realizada
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Se ha realizado la reserva correctamente, contactaremos brevemente con usted, gracias.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        Stage stage = (Stage) bcancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void isSelectedBanquete(ActionEvent event) {
        limpiarDatosReserva();
        radioButtonJornada.setSelected(false);
        radioButtonCongreso.setSelected(false);
        labelPersonas.setDisable(false);
        textFieldNumPer.setDisable(false);
        labelTipoCocina.setDisable(false);

        labelTipo.setVisible(true);
        labelOpcion.setText("Banquete");
        labelOpcion.setVisible(true);
        datePickerEvento.setDisable(false);
        comboBoxTipo.setDisable(false);
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
        datePickerEvento.setDisable(false);
        comboBoxTipo.setDisable(true);
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
        //textFieldNumHab.setDisable(false);
        labelNumDias.setDisable(false);
        TextFieldNumDias.setDisable(false);
        labelTipo.setVisible(true);
        labelOpcion.setText("Congreso");
        labelOpcion.setVisible(true);
        datePickerEvento.setDisable(false);
        comboBoxTipo.setDisable(true);
    }

    private void limpiarDatosReserva() {

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
        datePickerEvento.setDisable(true);
    }

    public void limpiardatospersonales() {
        textFieldDNI.setText("");
        textFieldNombre.setText("");
        textFieldNombre.setDisable(true);
        textFieldDireccion.setText("");
        textFieldDireccion.setDisable(true);
        textFieldTelefono.setText("");
        textFieldTelefono.setDisable(true);

        radioButtonBanquete.setSelected(false);
        radioButtonBanquete.setDisable(true);
        radioButtonCongreso.setSelected(false);
        radioButtonCongreso.setDisable(true);
        radioButtonJornada.setSelected(false);
        radioButtonJornada.setDisable(true);
    }

    public void mostrarDatosCliente() {
        textFieldNombre.setText(cliente.getNombre());
        textFieldDireccion.setText(cliente.getDireccion());
        textFieldTelefono.setText(String.valueOf(cliente.getTelefono()));
    } // Fin de mostrar dato

    public void mostrarDatosReserva() {
        // Si comprobamos reserva, nos carga los datos del cliente
        mostrarDatosCliente();
        // Tipo de evento

        if (reserva.getTipo().equals("Banquete")) {
            radioButtonBanquete.setSelected(true);
            textFieldNumHab.setText("");
            TextFieldNumDias.setText("");
        } else if (reserva.getTipo().equals("Jornada")) {
            radioButtonJornada.setSelected(true);
            textFieldNumHab.setText("");
            TextFieldNumDias.setText("");
        } else if (reserva.getTipo().equals("Congreso")) {
            radioButtonCongreso.setSelected(true);
            if (reserva.getNumHabitaciones() > 0) {
                checkBoxHabi.setSelected(true);
                onActionButtonHab(new ActionEvent());
                textFieldNumHab.setText(String.valueOf(reserva.getNumHabitaciones()));
            }
            if (reserva.getNumDias() != null) {
                TextFieldNumDias.setText(String.valueOf(reserva.getNumDias()));
            }
        }
        textFieldNumPer.setText(String.valueOf(reserva.getNumPersonas()));

        // Fecha de llegada
        if (reserva.getFecha() != null) {
            Date date = reserva.getFecha();
            Instant instant = date.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate localDate = zdt.toLocalDate();
            datePickerEvento.setValue(localDate);
        }
        // Tipo de cocina
        if (reserva.getTipoCocina() != null) {
            comboBoxTipo.setValue(reserva.getTipoCocina());

        }
    }

    public void habilitarCampos() {

        textFieldNombre.setDisable(false);
        textFieldDireccion.setDisable(false);
        textFieldTelefono.setDisable(false);
        radioButtonBanquete.setDisable(false);
        radioButtonCongreso.setDisable(false);
        radioButtonJornada.setDisable(false);

    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @FXML
    private void onActionButtonHab(ActionEvent event) {
        if (checkBoxHabi.isSelected()) {
            textFieldNumHab.setDisable(false);
        } else {
            textFieldNumHab.setDisable(true);
        }
    }
}
