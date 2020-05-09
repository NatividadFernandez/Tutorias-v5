package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorBuscarSesion {

	private IControlador controladorMVC;
	private Sesion sesion = null;
	@FXML
	private TableColumn<Tutoria, String> tcDni;

	@FXML
	private TableColumn<Tutoria, String> tcCorreo;

	@FXML
	private DatePicker dpSesion;

	@FXML
	private Button btAceptar;

	@FXML
	private TableView<Tutoria> tvTutorias;

	@FXML
	private TableColumn<Tutoria, String> tcNombreProfesor;

	@FXML
	private TableColumn<Tutoria, String> tcNombreTutoria;

	@FXML
	private Button btCancelar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvTutorias.setItems(FXCollections.observableArrayList(controladorMVC.getTutorias()));
		dpSesion.setValue(null);
	}

	@FXML
	private void initialize() {
		tcNombreTutoria.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getNombre()));
		tcNombreProfesor
				.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getNombre()));
		tcDni.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getDni()));
		tcCorreo.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getCorreo()));
	}

	public Sesion getSesion() {
		return sesion;
	}

	@FXML
	private void buscarSesion() {
		try {
			Tutoria tutoria = tvTutorias.getSelectionModel().getSelectedItem();
			LocalDate fecha = dpSesion.getValue();
			sesion = controladorMVC.buscarSesion(Sesion.getSesionFicticia(tutoria, fecha));
			((Stage) btAceptar.getScene().getWindow()).close();

		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Buscar Sesión", e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

}
