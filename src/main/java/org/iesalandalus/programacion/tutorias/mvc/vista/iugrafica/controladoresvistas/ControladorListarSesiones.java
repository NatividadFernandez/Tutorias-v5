package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.format.DateTimeFormatter;
import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarSesiones {

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private IControlador controladorMVC;

	@FXML
	private TableView<Tutoria> tvTutorias;

	@FXML
	private TableColumn<Tutoria, String> tcTutoria;

	@FXML
	private TableColumn<Tutoria, String> tcDni;

	@FXML
	private TableColumn<Tutoria, String> tcCorreo;

	@FXML
	private TableColumn<Tutoria, String> tcNombreProfesor;

	@FXML
	private TableColumn<Sesion, String> tcFecha;

	@FXML
	private TableView<Sesion> tvSesiones;

	@FXML
	private Button btAceptar;

	@FXML
	private TableColumn<Sesion, String> tcFinSesion;

	@FXML
	private TableColumn<Sesion, String> tcMinutos;

	@FXML
	private TableColumn<Sesion, String> tcInicioSesion;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvTutorias.setItems(FXCollections.observableArrayList(controladorMVC.getTutorias()));
		tvSesiones.setItems(FXCollections.observableList(controladorMVC.getSesiones()));
	}

	@FXML
	private void initialize() {
		tcNombreProfesor
				.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getNombre()));
		tcCorreo.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getCorreo()));
		tcDni.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getDni()));
		tcTutoria.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getNombre()));
		tvTutorias.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarSesionesTutoria(nv));
		tcFecha.setCellValueFactory(
				sesion -> new SimpleStringProperty(FORMATO_FECHA.format(sesion.getValue().getFecha())));
		tcInicioSesion
				.setCellValueFactory(sesion -> new SimpleStringProperty(sesion.getValue().getHoraInicio().toString()));
		tcFinSesion.setCellValueFactory(sesion -> new SimpleStringProperty(sesion.getValue().getHoraFin().toString()));
		tcMinutos.setCellValueFactory(sesion -> new SimpleStringProperty(getMinutosString(sesion.getValue())));
	}

	private void mostrarSesionesTutoria(Tutoria tutoria) {
		try {
			if (tutoria != null) {
				tvSesiones.setItems(FXCollections.observableArrayList(controladorMVC.getSesiones(tutoria)));
			}
		} catch (IllegalArgumentException e) {
			tvSesiones.setItems(FXCollections.observableArrayList(controladorMVC.getSesiones()));
		}
	}

	private String getMinutosString(Sesion sesion) {
		String minutos = String.valueOf(sesion.getMinutosDuracion());
		return minutos;
	}

	@FXML
	private void mostrarSesiones() {
		tvSesiones.setItems(FXCollections.observableArrayList(controladorMVC.getSesiones()));
	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
