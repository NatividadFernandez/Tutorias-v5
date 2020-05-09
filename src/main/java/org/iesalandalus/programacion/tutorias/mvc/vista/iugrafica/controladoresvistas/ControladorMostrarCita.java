package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorMostrarCita {

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private IControlador controladorMVC;
	private Cita cita;

	@FXML
	private Label lbInicioSesion;

	@FXML
	private Label lbFecha;

	@FXML
	private Label lbFinSesion;

	@FXML
	private Label lbNombreProfesor;

	@FXML
	private Label lbCorreoProfesor;

	@FXML
	private Button btAceptar;

	@FXML
	private Label lbDni;

	@FXML
	private Label lbNombreAlumno;

	@FXML
	private Label lbHoraCita;

	@FXML
	private Label lbMinutos;

	@FXML
	private Label lbCorreoAlumno;

	@FXML
	private Label lbExpediente;

	@FXML
	private Label lbNombreTutoria;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
		lbNombreTutoria.setText(cita.getSesion().getTutoria().getNombre());
		lbNombreProfesor.setText(cita.getSesion().getTutoria().getProfesor().getNombre());
		lbDni.setText(cita.getSesion().getTutoria().getProfesor().getDni());
		lbCorreoProfesor.setText(cita.getSesion().getTutoria().getProfesor().getCorreo());
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
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
