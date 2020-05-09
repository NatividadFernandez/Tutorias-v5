package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorMostrarSesion {

	private IControlador controladorMVC;
	private Sesion sesion;
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@FXML
	private Label lbInicioSesion;

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
	private Label lbDni;

	@FXML
	private Label lbMinutos;

	@FXML
	private Label lbNombreTutoria;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
		lbNombreProfesor.setText(sesion.getTutoria().getProfesor().getNombre());
		lbDni.setText(sesion.getTutoria().getProfesor().getDni());
		lbCorreo.setText(sesion.getTutoria().getProfesor().getCorreo());
		lbNombreTutoria.setText(sesion.getTutoria().getNombre());
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

}
