package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirCita {

	private static final String ER_OBLIGATORIO = ".+";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	private IControlador controladorMVC;

	@FXML
	private TableView<Sesion> tvSesiones;
	@FXML
	private TableColumn<Sesion, String> tcTutoria;
	@FXML
	private TableColumn<Sesion, String> tcNombreProfesor;
	@FXML
	private TableColumn<Sesion, String> tcDni;
	@FXML
	private TableColumn<Sesion, String> tcCorreo;
	@FXML
	private TableColumn<Sesion, String> tcFecha;
	@FXML
	private TableColumn<Sesion, String> tcInicioSesion;
	@FXML
	private TableColumn<Sesion, String> tcFinSesion;
	@FXML
	private TableColumn<Sesion, String> tcMinutos;
	@FXML
	private TableView<Alumno> tvAlumnos;
	@FXML
	private TableColumn<Alumno, String> tcNombreAlumno;
	@FXML
	private TableColumn<Alumno, String> tcCorreoAlumno;
	@FXML
	private TableColumn<Alumno, String> tcExpediente;
	@FXML
	private TextField tfHoraCita;
	@FXML
	private Button btAnadir;
	@FXML
	private Button btCancelar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;

	}

	public void inicializa() {
		tvAlumnos.setItems(FXCollections.observableArrayList(controladorMVC.getAlumnos()));
		tvSesiones.setItems(FXCollections.observableArrayList(controladorMVC.getSesiones()));
		tfHoraCita.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfHoraCita);
	}

	@FXML
	private void initialize() {
		tcTutoria.setCellValueFactory(sesion -> new SimpleStringProperty(sesion.getValue().getTutoria().getNombre()));
		tcNombreProfesor.setCellValueFactory(
				sesion -> new SimpleStringProperty(sesion.getValue().getTutoria().getProfesor().getNombre()));
		tcCorreo.setCellValueFactory(
				sesion -> new SimpleStringProperty(sesion.getValue().getTutoria().getProfesor().getCorreo()));
		tcDni.setCellValueFactory(
				sesion -> new SimpleStringProperty(sesion.getValue().getTutoria().getProfesor().getDni()));
		tcFecha.setCellValueFactory(
				sesion -> new SimpleStringProperty(FORMATO_FECHA.format(sesion.getValue().getFecha())));
		tcInicioSesion
				.setCellValueFactory(sesion -> new SimpleStringProperty(sesion.getValue().getHoraInicio().toString()));
		tcFinSesion.setCellValueFactory(sesion -> new SimpleStringProperty(sesion.getValue().getHoraFin().toString()));
		tcMinutos.setCellValueFactory(sesion -> new SimpleStringProperty(getMinutosString(sesion.getValue())));
		tcNombreAlumno.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getNombre()));
		tcCorreoAlumno.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getCorreo()));
		tcExpediente.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getExpediente()));
		tfHoraCita.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfHoraCita));
	}

	@FXML
	private void anadirCita() {
		try {
			Alumno alumno = tvAlumnos.getSelectionModel().getSelectedItem();
			Sesion sesion = tvSesiones.getSelectionModel().getSelectedItem();
			LocalTime horaCita = LocalTime.parse(tfHoraCita.getText(), FORMATO_HORA);

			Cita cita = new Cita(alumno, sesion, horaCita);
			controladorMVC.insertarCita(cita);
			Stage propietario = ((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Cita", "Cita añadida satisfactoriamente", propietario);
		} catch (DateTimeParseException d) {
			Dialogos.mostrarDialogoError("Añadir Cita", "Deben de rellenarse todos los campos.");
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Cita", e.getMessage());
		}
	}

	private String getMinutosString(Sesion sesion) {
		String minutos = String.valueOf(sesion.getMinutosDuracion());
		return minutos;
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

}
