package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorBorrarTutoria {

	private IControlador controladorMVC;
	private Tutoria tutoria;

	@FXML
	private Label lbNombreProfesor;

	@FXML
	private Label lbCorreo;

	@FXML
	private Button btAceptar;

	@FXML
	private Label lbDni;

	@FXML
	private Button btBorrar;

	@FXML
	private Label lbNombreTutoria;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setTutoria(Tutoria tutoria) {
		this.tutoria = tutoria;
		lbNombreProfesor.setText(tutoria.getProfesor().getNombre());
		lbCorreo.setText(tutoria.getProfesor().getCorreo());
		lbDni.setText(tutoria.getProfesor().getDni());
		lbNombreTutoria.setText(tutoria.getNombre());
	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

	@FXML
	private void borrar() {
		Stage propietario = (Stage) btBorrar.getScene().getWindow();
		try {
			controladorMVC.borrarTutoria(tutoria);
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Borrar Tutoría", e.getMessage(), propietario);
		}
		Dialogos.mostrarDialogoInformacion("Borrar Tutoría", "Tutoría borrada satisfactoriamente", propietario);

	}

}
