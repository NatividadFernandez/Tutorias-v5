package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorMostrarProfesor {

	private IControlador controladorMVC;
	private Profesor profesor;

	@FXML
	private Label lbCorreo;

	@FXML
	private Label lbNombre;

	@FXML
	private Button btAceptar;

	@FXML
	private Label lbDni;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
		lbNombre.setText(profesor.getNombre());
		lbDni.setText(profesor.getDni());
		lbCorreo.setText(profesor.getCorreo());
	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
