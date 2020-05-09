package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirAlumno {

	private static final String ER_NOMBRE = "(?=.*\\s.+)(?![a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]$).[a-zA-Zñáéíóúü\\s]+";
	private static final String ER_CORREO = "\\w+[\\.\\w]*@\\w+[\\.\\w]*\\.\\w{2,5}\\b\\s?";
	private static final String ER_OBLIGATORIO = ".+";

	private IControlador controladorMVC;

	@FXML
	private TextField tfCorreo;

	@FXML
	private TextField tfNombre;

	@FXML
	private Button btAnadir;

	@FXML
	private Button btCancelar;

	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_NOMBRE, tfNombre));
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void anadirAlumno() {
		Alumno alumno = null;
		try {
			alumno = getAlumno();
			controladorMVC.insertarAlumno(alumno);
			Stage propietario = ((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Alumno", "Alumno añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Alumno", e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

	public void inicializa() {
		tfNombre.setText("");
		compruebaCampoTexto(ER_NOMBRE, tfNombre);
		tfCorreo.setText("");
		compruebaCampoTexto(ER_CORREO, tfCorreo);
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else if (texto.matches(ER_OBLIGATORIO)) {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	private Alumno getAlumno() {
		String nombre = tfNombre.getText();
		String correo = tfCorreo.getText();
		return new Alumno(nombre, correo);
	}

}
