package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarTutorias {

	private IControlador controladorMVC;

	@FXML
	private TableColumn<Profesor, String> tcDni;

	@FXML
	private TableColumn<Profesor, String> tcNombre;

	@FXML
	private TableColumn<Profesor, String> tcCorreo;

	@FXML
	private TableColumn<Tutoria, String> tcNombreTutorias;

	@FXML
	private Button btAceptar;

	@FXML
	private TableView<Tutoria> tvTutorias;

	@FXML
	private TableView<Profesor> tvProfesores;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvProfesores.setItems(FXCollections.observableArrayList(controladorMVC.getProfesores()));
		tvTutorias.setItems(FXCollections.observableArrayList(controladorMVC.getTutorias()));

	}

	@FXML
	private void initialize() {
		tcNombre.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getNombre()));
		tcCorreo.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getCorreo()));
		tcDni.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getDni()));
		tvProfesores.getSelectionModel().selectedItemProperty()
				.addListener((ob, ov, nv) -> mostrarTutoriasProfesor(nv));
		tcNombreTutorias.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getNombre()));
	}

	public void mostrarTutoriasProfesor(Profesor profesor) {
		try {
			if (profesor != null) {
				tvTutorias.setItems(FXCollections.observableArrayList(controladorMVC.getTutorias(profesor)));
			}
		} catch (IllegalArgumentException e) {
			tvTutorias.setItems(FXCollections.observableArrayList(controladorMVC.getTutorias()));
		}
	}

	@FXML
	void mostarTutorias() {
		tvTutorias.setItems(FXCollections.observableArrayList(controladorMVC.getTutorias()));
	}

	@FXML
	void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}
}
