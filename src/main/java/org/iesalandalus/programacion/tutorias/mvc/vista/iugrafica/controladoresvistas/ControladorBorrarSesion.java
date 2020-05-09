package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorBorrarSesion {

	private IControlador controladorMVC;
	private Sesion sesion;

	@FXML
	private Label lbInicioSesion;

	@FXML
	private Label lbFecha;

	@FXML
	private Label lbNombreProfesor;

	@FXML
	private Label lbFinSesion;

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

	@FXML
	private Label lbMinutos;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
		lbNombreTutoria.setText(sesion.getTutoria().getNombre());
		lbNombreProfesor.setText(sesion.getTutoria().getProfesor().getNombre());
		lbCorreo.setText(sesion.getTutoria().getProfesor().getCorreo());
		lbDni.setText(sesion.getTutoria().getProfesor().getDni());
		lbFecha.setText(sesion.getFecha().toString());
		lbInicioSesion.setText(sesion.getHoraInicio().toString());
		lbFinSesion.setText(sesion.getHoraFin().toString());
		lbMinutos.setText(Integer.toString(sesion.getMinutosDuracion()));

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
			controladorMVC.borrarSesion(sesion);
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Borrar Sesión", e.getMessage(), propietario);
		}
		Dialogos.mostrarDialogoInformacion("Borrar Sesión", "Sesión borrada satisfactoriamente", propietario);

	}

}
