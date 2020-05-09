package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirProfesor {

	private static final String ER_NOMBRE = "(?=.*\\s.+)(?![a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]$).[a-zA-Zñáéíóúü\\s]+";
	private static final String ER_DNI = "[0-9]{8,8}[a-zA-Z]$";
	private static final String ER_CORREO = "\\w+[\\.\\w]*@\\w+[\\.\\w]*\\.\\w{2,5}\\b\\s?";
	private static final String ER_OBLIGATORIO = ".+";

	private IControlador controladorMVC;

	@FXML
	private TextField tfCorreo;
	@FXML
	private Button btAnadir;
	@FXML
	private TextField tfNombre;
	@FXML
	private TextField tfDni;
	@FXML
	private Button btCancelar;

	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_NOMBRE, tfNombre));
		tfDni.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_DNI, tfDni));
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void anadirProfesor() {
		Profesor profesor = null;
		try {
			profesor = getProfesor();
			controladorMVC.insertarProfesor(profesor);
			Stage propietario = ((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Profesor", "Profesor añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Profesor", e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

	public void inicializa() {
		tfNombre.setText("");
		compruebaCampoTexto(ER_NOMBRE, tfNombre);
		tfDni.setText("");
		compruebaCampoTexto(ER_DNI, tfDni);
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

	private Profesor getProfesor() {
		String nombre = tfNombre.getText();
		String dni = tfDni.getText();
		String correo = tfCorreo.getText();
		return new Profesor(nombre, dni, correo);
	}

}
