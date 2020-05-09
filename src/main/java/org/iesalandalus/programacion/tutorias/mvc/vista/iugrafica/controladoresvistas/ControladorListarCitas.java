package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarCitas {

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
	private TableView<Cita> tvCitas;
	@FXML
	private TableColumn<Cita, String> tcHoraCita;
	@FXML
	private Button btAceptar;
	@FXML
	private Button btMostrarTodo;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvSesiones.setItems(FXCollections.observableList(controladorMVC.getSesiones()));
		tvAlumnos.setItems(FXCollections.observableArrayList(controladorMVC.getAlumnos()));
		tvCitas.setItems(FXCollections.observableArrayList(controladorMVC.getCitas()));
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
		tvSesiones.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarCitasSesion(nv));
		tcNombreAlumno.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getNombre()));
		tcCorreoAlumno.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getCorreo()));
		tcExpediente.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getExpediente()));
		tvAlumnos.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarCitasAlumno(nv));
		tcHoraCita.setCellValueFactory(cita -> new SimpleStringProperty(cita.getValue().getHora().toString()));
	}

	private void mostrarCitasSesion(Sesion sesion) {
		try {
			if (sesion != null) {
				tvCitas.setItems(FXCollections.observableArrayList(controladorMVC.getCitas(sesion)));
			}
		} catch (IllegalArgumentException e) {
			tvCitas.setItems(FXCollections.observableArrayList());
		}
	}

	private void mostrarCitasAlumno(Alumno alumno) {
		try {
			if (alumno != null) {
				tvCitas.setItems(FXCollections.observableArrayList(controladorMVC.getCitas(alumno)));
			}
		} catch (IllegalArgumentException e) {
			tvCitas.setItems(FXCollections.observableArrayList(controladorMVC.getCitas()));
		}
	}

	private String getMinutosString(Sesion sesion) {
		String minutos = String.valueOf(sesion.getMinutosDuracion());
		return minutos;
	}

	@FXML
	private void mostrarCitas() {
		tvCitas.setItems(FXCollections.observableArrayList(controladorMVC.getCitas()));
	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
