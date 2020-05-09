package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorBuscarTutoria {

	private static final String ER_OBLIGATORIO = ".+";
	private IControlador controladorMVC;
	private Tutoria tutoria;

	@FXML
	private TextField tfNombreTutoria;

	@FXML
	private TableColumn<Profesor, String> tcDni;

	@FXML
	private TableColumn<Profesor, String> tcCorreo;

	@FXML
	private Button btAceptar;

	@FXML
	private TableColumn<Profesor, String> tcNombreProfesor;

	@FXML
	private TableView<Profesor> tvProfesores;

	@FXML
	private Button btCancelar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvProfesores.setItems(FXCollections.observableArrayList(controladorMVC.getProfesores()));
		tfNombreTutoria.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfNombreTutoria);
	}

	@FXML
	private void initialize() {
		tcNombreProfesor.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getNombre()));
		tcDni.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getDni()));
		tcCorreo.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getCorreo()));
		tfNombreTutoria.textProperty()
				.addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfNombreTutoria));
	}

	@FXML
	private void buscarTutoria() {
		try {
			Profesor profesor = tvProfesores.getSelectionModel().getSelectedItem();
			String dni = profesor.getDni().toString();
			String nombre = tfNombreTutoria.getText();
			tutoria = controladorMVC.buscarTutoria(new Tutoria(profesor.getProfesorFicticio(dni), nombre));

			((Stage) btAceptar.getScene().getWindow()).close();
		} catch (NullPointerException n) {
			Dialogos.mostrarDialogoError("Buscar Tutoría",
					"Debes seleccionar un profesor y rellenar el campo de tutoría.");
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Buscar Tutoría", e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

	public Tutoria getTutoria() {
		return tutoria;
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

}
