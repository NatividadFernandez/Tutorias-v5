package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas.ControladorVentanaPrincipal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VistaIUGrafica extends Application implements IVista {

	private static IControlador controladorMVC = null;

	@Override
	public void setControlador(IControlador controlador) {
		controladorMVC = controlador;
	}

	@Override
	public void comenzar() {
		launch(this.getClass());
	}

	@Override
	public void terminar() {
		controladorMVC.terminar();
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			FXMLLoader cargadorVentanaPrincipal = new FXMLLoader(
					getClass().getResource("vistas/VentanaPrincipal.fxml"));
			VBox raiz = cargadorVentanaPrincipal.load();
			ControladorVentanaPrincipal cVentanaPrincipal = cargadorVentanaPrincipal.getController();
			cVentanaPrincipal.setControladorMVC(controladorMVC);

			Scene escena = new Scene(raiz);
			escenarioPrincipal.setOnCloseRequest(e -> confirmarSalida(escenarioPrincipal, e));
			escenarioPrincipal.setTitle("Tutorías");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.setResizable(false);
			escenarioPrincipal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				escenarioPrincipal)) {
			controladorMVC.terminar();
			escenarioPrincipal.close();
		} else {
			e.consume();
		}
	}

}
