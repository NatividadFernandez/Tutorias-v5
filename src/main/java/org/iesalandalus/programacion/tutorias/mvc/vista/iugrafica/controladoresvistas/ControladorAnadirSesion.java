package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirSesion {

	private static final String ER_OBLIGATORIO = ".+";
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	private IControlador controladorMVC;

	@FXML
	private TableView<Tutoria> tvTutorias;

	@FXML
	private TableColumn<Tutoria, String> tcDni;

	@FXML
	private TableColumn<Tutoria, String> tcCorreo;

	@FXML
	private TableColumn<Tutoria, String> tcNombreTutoria;

	@FXML
	private TableColumn<Tutoria, String> tcNombreProfesor;

	@FXML
	private TextField tfInicioSesion;

	@FXML
	private DatePicker dpFecha;

	@FXML
	private Button btAnadir;

	@FXML
	private TextField tfMinutos;

	@FXML
	private TextField tfFinSesion;

	@FXML
	private Button btCancelar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvTutorias.setItems(FXCollections.observableArrayList(controladorMVC.getTutorias()));
		dpFecha.setValue(null);
		tfInicioSesion.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfInicioSesion);
		tfFinSesion.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfFinSesion);
		tfMinutos.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfMinutos);
	}

	@FXML
	private void initialize() {
		tcNombreTutoria.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getNombre()));
		tcNombreProfesor
				.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getNombre()));
		tcDni.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getDni()));
		tcCorreo.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getCorreo()));
		tfInicioSesion.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfInicioSesion));
		tfFinSesion.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfFinSesion));
		tfMinutos.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfMinutos));

	}

	@FXML
	private void anadirSesion() {
		try {
			Tutoria tutoria = tvTutorias.getSelectionModel().getSelectedItem();
			LocalDate fecha = dpFecha.getValue();
			LocalTime Inicio = LocalTime.parse(tfInicioSesion.getText(), FORMATO_HORA);
			LocalTime Fin = LocalTime.parse(tfFinSesion.getText(), FORMATO_HORA);
			int minutos = Integer.parseInt(tfMinutos.getText());

			Sesion sesion = new Sesion(tutoria, fecha, Inicio, Fin, minutos);
			controladorMVC.insertarSesion(sesion);
			Stage propietario = ((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Sesión", "Sesión añadida satisfactoriamente", propietario);
		} catch (DateTimeParseException d) {
			Dialogos.mostrarDialogoError("Añadir Sesión", "Deben de rellenarse todos los campos.");
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Sesión", e.getMessage());
		}
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

}
