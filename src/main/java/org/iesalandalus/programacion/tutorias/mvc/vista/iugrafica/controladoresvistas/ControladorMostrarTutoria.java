package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorMostrarTutoria {

	private IControlador controladorMVC;
	private Tutoria tutoria;

	@FXML
	private Label lbCorreo;

	@FXML
	private Label lbNombreProfesor;

	@FXML
	private Button btAceptar;

	@FXML
	private Label lbDni;

	@FXML
	private Label lbNombreTutoria;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setTutoria(Tutoria tutoria) {
		this.tutoria = tutoria;
		lbNombreProfesor.setText(tutoria.getProfesor().getNombre());
		lbDni.setText(tutoria.getProfesor().getDni());
		lbCorreo.setText(tutoria.getProfesor().getCorreo());
		lbNombreTutoria.setText(tutoria.getNombre());
	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
