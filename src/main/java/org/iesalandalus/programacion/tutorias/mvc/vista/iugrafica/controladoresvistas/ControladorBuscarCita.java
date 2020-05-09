package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorBuscarCita {

	private static final String ER_OBLIGATORIO = ".+";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	private IControlador controladorMVC;
	private Cita cita = null;

	@FXML
	private TableView<Sesion> tvSesiones;
	@FXML
	private TableColumn<Sesion, String> tcTutoria;
	@FXML
	private TableColumn<Sesion, String> tcNombreProfesor;
	@FXML
	private TableColumn<Sesion, String> tcDni;
	@FXML
	private TableColumn<Sesion, String> tcCorreoProfesor;
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
	private TextField tfHoraCita;
	@FXML
	private Button btAceptar;
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
		tcCorreoProfesor.setCellValueFactory(
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
		tfHoraCita.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfHoraCita));
	}

	public Cita getCita() {
		return cita;
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

	@FXML
	private void buscarCita() {
		try {
			Sesion sesion = tvSesiones.getSelectionModel().getSelectedItem();
			Alumno alumno = tvAlumnos.getSelectionModel().getSelectedItem();
			LocalTime horaCita = LocalTime.parse(tfHoraCita.getText(), FORMATO_HORA);
			cita = controladorMVC.buscarCita(new Cita(alumno, sesion, horaCita));
			((Stage) btAceptar.getScene().getWindow()).close();
		} catch (DateTimeParseException d) {
			Dialogos.mostrarDialogoError("Buscar Cita", "Deben de rellenarse todos los campos.");
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Buscar Cita", e.getMessage());
		}
	}

}
