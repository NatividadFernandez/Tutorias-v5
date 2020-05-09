package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorBorrarCita {

	private IControlador controladorMVC;
	private Cita cita;

	@FXML
	private Label lbFecha;

	@FXML
	private Label lbFinSesion;

	@FXML
	private Label lbNombreProfesor;

	@FXML
	private Label lbCorreo;

	@FXML
	private Button btAceptar;

	@FXML
	private Button btBorrar;

	@FXML
	private Label lbNombreAlumno;

	@FXML
	private Label lbHoraCita;

	@FXML
	private Label lbNombreTutoria;

	@FXML
	private Label lbCorreoProfesor;

	@FXML
	private Label lbInicioSesion;

	@FXML
	private Label lbDni;

	@FXML
	private Label lbMinutos;

	@FXML
	private Label lbCorreoAlumno;

	@FXML
	private Label lbExpediente;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
		lbNombreTutoria.setText(cita.getSesion().getTutoria().getNombre());
		lbNombreProfesor.setText(cita.getSesion().getTutoria().getProfesor().getNombre());
		lbCorreo.setText(cita.getSesion().getTutoria().getProfesor().getCorreo());
		lbDni.setText(cita.getSesion().getTutoria().getProfesor().getDni());
		lbFecha.setText(cita.getSesion().getFecha().toString());
		lbInicioSesion.setText(cita.getSesion().getHoraInicio().toString());
		lbFinSesion.setText(cita.getSesion().getHoraFin().toString());
		lbMinutos.setText(Integer.toString(cita.getSesion().getMinutosDuracion()));
		lbNombreAlumno.setText(cita.getAlumno().getNombre());
		lbCorreoAlumno.setText(cita.getAlumno().getCorreo());
		lbExpediente.setText(cita.getAlumno().getExpediente());
		lbHoraCita.setText(cita.getHora().toString());
	}

	@FXML
	private void borrar() {
		Stage propietario = (Stage) btBorrar.getScene().getWindow();
		try {
			controladorMVC.borrarCita(cita);
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Borrar Cita", e.getMessage(), propietario);
		}
		Dialogos.mostrarDialogoInformacion("Borrar Cita", "Cita borrada satisfactoriamente", propietario);

	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
