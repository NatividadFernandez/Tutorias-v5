package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.io.IOException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {

	private IControlador controladorMVC;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private Button btListarAlumnos;
	@FXML
	private Button btBorrarAlumno;
	@FXML
	private Button btBuscarAlumno;
	@FXML
	private Button btAnadirAlumno;
	@FXML
	private Button btBorrarProfesor;
	@FXML
	private Button btBuscarProfesor;
	@FXML
	private Button btListarProfesores;
	@FXML
	private Button btAnadirProfesor;
	@FXML
	private Button btBorrarTutoria;
	@FXML
	private Button btBuscarTutoria;
	@FXML
	private Button btListarTutorias;
	@FXML
	private Button btAnadirTutoria;
	@FXML
	private Button btBorrarSesion;
	@FXML
	private Button btBuscarSesion;
	@FXML
	private Button btListarSesiones;
	@FXML
	private Button btAnadirSesion;
	@FXML
	private Button btBorrarCita;
	@FXML
	private Button btBuscarCita;
	@FXML
	private Button btListarCitas;
	@FXML
	private Button btAnadirCita;

	private Stage anadirProfesor;
	private ControladorAnadirProfesor cAnadirProfesor;
	private Stage listarProfesores;
	private ControladorListarProfesores cListarProfesores;
	private Stage mostrarProfesor;
	private ControladorMostrarProfesor cMostrarProfesor;
	private Stage borrarProfesor;
	private ControladorBorrarProfesor cBorrarProfesor;
	private Stage anadirAlumno;
	private ControladorAnadirAlumno cAnadirAlumno;
	private Stage listarAlumnos;
	private ControladorListarAlumnos cListarAlumnos;
	private Stage mostrarAlumno;
	private ControladorMostrarAlumno cMostrarAlumno;
	private Stage borrarAlumno;
	private ControladorBorrarAlumno cBorrarAlumno;
	private Stage anadirTutoria;
	private ControladorAnadirTutoria cAnadirTutoria;
	private Stage listarTutorias;
	private ControladorListarTutorias cListarTutorias;
	private Stage buscarTutoria;
	private ControladorBuscarTutoria cBuscarTutoria;
	private Stage mostrarTutoria;
	private ControladorMostrarTutoria cMostrarTutoria;
	private Stage borrarTutoria;
	private ControladorBorrarTutoria cBorrarTutoria;
	private Stage anadirSesion;
	private ControladorAnadirSesion cAnadirSesion;
	private Stage listarSesiones;
	private ControladorListarSesiones cListarSesiones;
	private Stage mostrarSesion;
	private ControladorMostrarSesion cMostrarSesion;
	private Stage buscarSesion;
	private ControladorBuscarSesion cBuscarSesion;
	private Stage borrarSesion;
	private ControladorBorrarSesion cBorrarSesion;
	private Stage anadirCita;
	private ControladorAnadirCita cAnadirCita;
	private Stage listarCitas;
	private ControladorListarCitas cListarCitas;
	private Stage buscarCita;
	private ControladorBuscarCita cBuscarCita;
	private Stage mostrarCita;
	private ControladorMostrarCita cMostrarCita;
	private Stage borrarCita;
	private ControladorBorrarCita cBorrarCita;

	@FXML
	private void salir(ActionEvent event) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
	}

	@FXML
	private void acercaDe(ActionEvent event) throws IOException {
		VBox contenido = FXMLLoader.load(getClass().getResource("../vistas/AcercaDe.fxml"));
		Dialogos.mostrarDialogoInformacionPersonalizado("Tutorias", contenido);
	}

	@FXML
	private void listarProfesores() throws IOException {
		crearListarProfesores();
		listarProfesores.showAndWait();
	}

	@FXML
	private void anadirProfesor() throws IOException {
		crearAnadirProfesor();
		anadirProfesor.showAndWait();
	}

	@FXML
	private void buscarProfesor() {
		String dni = Dialogos.mostrarDialogoTexto("Buscar Profesor", "DNI:");
		if (dni != null) {
			Profesor profesor = null;
			try {
				profesor = controladorMVC.buscarProfesor(Profesor.getProfesorFicticio(dni));
				if (profesor != null) {
					crearMostrarProfesor(profesor);
					mostrarProfesor.showAndWait();
				} else {
					Dialogos.mostrarDialogoError("Profesor no encontrado", "No existe ningún profesor con ese dni");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("DNI no válido", e.getMessage());
			}
		}
	}

	@FXML
	private void borrarProfesor() {
		String dni = Dialogos.mostrarDialogoTexto("Buscar Profesor", "DNI:");
		if (dni != null) {
			Profesor profesor = null;
			try {
				profesor = controladorMVC.buscarProfesor(Profesor.getProfesorFicticio(dni));
				if (profesor != null) {
					crearBorrarProfesor(profesor);
					borrarProfesor.showAndWait();
				} else {
					Dialogos.mostrarDialogoError("Profesor no encontrado", "No existe ningún profesor con ese dni");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("DNI no válido", e.getMessage());
			}
		}

	}

	@FXML
	private void listarAlumnos() throws IOException {
		crearListarAlumnos();
		listarAlumnos.showAndWait();
	}

	@FXML
	private void anadirAlumno() throws IOException {
		crearAnadirAlumno();
		anadirAlumno.showAndWait();
	}

	@FXML
	void buscarAlumno() {
		String correo = Dialogos.mostrarDialogoTexto("Buscar Alumno", "Correo:");
		if (correo != null) {
			Alumno alumno = null;
			try {
				alumno = controladorMVC.buscarAlumno(Alumno.getAlumnoFicticio(correo));
				if (alumno != null) {
					crearMostrarAlumno(alumno);
					mostrarAlumno.showAndWait();
				} else {
					Dialogos.mostrarDialogoError("Alumno no encontrado", "No existe ningún alumno con ese correo.");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("Correo no válido", e.getMessage());
			}
		}
	}

	@FXML
	private void borrarAlumno() {
		String correo = Dialogos.mostrarDialogoTexto("Buscar Alumno", "Correo:");
		if (correo != null) {
			Alumno alumno = null;
			try {
				alumno = controladorMVC.buscarAlumno(Alumno.getAlumnoFicticio(correo));
				if (alumno != null) {
					crearBorrarAlumno(alumno);
					borrarAlumno.showAndWait();
				} else {
					Dialogos.mostrarDialogoError("Alumno no encontrado", "No existe ningún alumno con ese correo.");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("Correo no válido", e.getMessage());
			}
		}
	}

	@FXML
	private void listarTutorias() throws IOException {
		crearListarTutorias();
		listarTutorias.showAndWait();
	}

	@FXML
	private void anadirTutoria() throws IOException {
		crearAnadirTutoria();
		anadirTutoria.showAndWait();
	}

	@FXML
	private void buscarTutoria() throws IOException {
		crearBuscarTutoria();
		buscarTutoria.showAndWait();
		Tutoria tutoria = cBuscarTutoria.getTutoria();
		if (tutoria != null) {
			crearMostrarTutoria(tutoria);
			mostrarTutoria.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Tutoria no encontrada",
					"No existe ninguna tutoria con ese nombre y para ese profesor.");
		}
	}

	@FXML
	private void borrarTutoria() throws IOException {
		crearBuscarTutoria();
		buscarTutoria.showAndWait();
		Tutoria tutoria = cBuscarTutoria.getTutoria();
		if (tutoria != null) {
			crearBorrarTutoria(tutoria);
			borrarTutoria.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Tutoria no encontrada",
					"No existe ninguna tutoria con ese nombre y para ese profesor.");
		}

	}

	@FXML
	private void listarSesiones() throws IOException {
		crearListarSesiones();
		listarSesiones.showAndWait();
	}

	@FXML
	private void anadirSesion() throws IOException {
		crearAnadirSesion();
		anadirSesion.showAndWait();
	}

	@FXML
	private void buscarSesion() throws IOException {
		crearBuscarSesion();
		buscarSesion.showAndWait();
		Sesion sesion = cBuscarSesion.getSesion();
		if (sesion != null) {
			crearMostrarSesion(sesion);
			mostrarSesion.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Sesión no encontrada",
					"No existe ninguna sesión con ese profesor, tutoría y fecha.");
		}
	}

	@FXML
	private void borrarSesion() throws IOException {
		crearBuscarSesion();
		buscarSesion.showAndWait();
		Sesion sesion = cBuscarSesion.getSesion();
		if (sesion != null) {
			crearBorrarSesion(sesion);
			borrarSesion.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Sesión no encontrada",
					"No existe ninguna sesión con ese profesor, tutoría y fecha.");
		}
	}

	@FXML
	private void listarCitas() throws IOException {
		crearListarCitas();
		listarCitas.showAndWait();
	}

	@FXML
	private void anadirCita() throws IOException {
		crearAnadirCita();
		anadirCita.showAndWait();
	}

	@FXML
	private void buscarCita() throws IOException {
		crearBuscarCita();
		buscarCita.showAndWait();
		Cita cita = cBuscarCita.getCita();
		if (cita != null) {
			crearMostrarCita(cita);
			mostrarCita.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Cita no encontrada", "No existe ninguna cita con ese alumno, sesión y hora.");
		}
	}

	@FXML
	private void borrarCita() throws IOException {
		crearBuscarCita();
		buscarCita.showAndWait();
		Cita cita = cBuscarCita.getCita();
		if (cita != null) {
			crearBorrarCita(cita);
			borrarCita.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Sesión no encontrada",
					"No existe ninguna sesión con ese profesor, tutoría y fecha.");
		}
	}

	private void crearListarProfesores() throws IOException {
		if (listarProfesores == null) {
			listarProfesores = new Stage();
			FXMLLoader cargadorListarProfesores = new FXMLLoader(
					getClass().getResource("../vistas/ListarProfesores.fxml"));
			VBox raizListarProfesores = cargadorListarProfesores.load();
			cListarProfesores = cargadorListarProfesores.getController();
			cListarProfesores.setControladorMVC(controladorMVC);
			cListarProfesores.inicializa();
			Scene escenaListarProfesores = new Scene(raizListarProfesores);
			listarProfesores.setTitle("Listar Profesores");
			listarProfesores.initModality(Modality.APPLICATION_MODAL);
			listarProfesores.setScene(escenaListarProfesores);
		} else {
			cListarProfesores.inicializa();
		}
	}

	private void crearAnadirProfesor() throws IOException {
		if (anadirProfesor == null) {
			anadirProfesor = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirProfesor.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirProfesor = cargadorAnadirProfesor.getController();
			cAnadirProfesor.setControladorMVC(controladorMVC);
			cAnadirProfesor.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirProfesor.setTitle("Añadir Profesor");
			anadirProfesor.initModality(Modality.APPLICATION_MODAL);
			anadirProfesor.setScene(escenaAnadirProfesor);
		} else {
			cAnadirProfesor.inicializa();
		}
	}

	private void crearMostrarProfesor(Profesor profesor) throws IOException {
		if (mostrarProfesor == null) {
			mostrarProfesor = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(
					getClass().getResource("../vistas/MostrarProfesor.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cMostrarProfesor = cargadorMostrarProfesor.getController();
			cMostrarProfesor.setControladorMVC(controladorMVC);
			cMostrarProfesor.setProfesor(profesor);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			mostrarProfesor.setTitle("Mostrar Profesor");
			mostrarProfesor.initModality(Modality.APPLICATION_MODAL);
			mostrarProfesor.setScene(escenaMostrarProfesor);
		} else {
			cMostrarProfesor.setProfesor(profesor);
		}
	}

	private void crearBorrarProfesor(Profesor profesor) throws IOException {
		if (borrarProfesor == null) {
			borrarProfesor = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(
					getClass().getResource("../vistas/BorrarProfesor.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cBorrarProfesor = cargadorMostrarProfesor.getController();
			cBorrarProfesor.setControladorMVC(controladorMVC);
			cBorrarProfesor.setProfesor(profesor);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			borrarProfesor.setTitle("Mostrar Profesor");
			borrarProfesor.initModality(Modality.APPLICATION_MODAL);
			borrarProfesor.setScene(escenaMostrarProfesor);
		} else {
			cBorrarProfesor.setProfesor(profesor);
		}
	}

	private void crearListarAlumnos() throws IOException {
		if (listarAlumnos == null) {
			listarAlumnos = new Stage();
			FXMLLoader cargadorListarProfesores = new FXMLLoader(
					getClass().getResource("../vistas/ListarAlumnos.fxml"));
			VBox raizListarProfesores = cargadorListarProfesores.load();
			cListarAlumnos = cargadorListarProfesores.getController();
			cListarAlumnos.setControladorMVC(controladorMVC);
			cListarAlumnos.inicializa();
			Scene escenaListarProfesores = new Scene(raizListarProfesores);
			listarAlumnos.setTitle("Listar Alumnos");
			listarAlumnos.initModality(Modality.APPLICATION_MODAL);
			listarAlumnos.setScene(escenaListarProfesores);
		} else {
			cListarAlumnos.inicializa();
		}
	}

	private void crearAnadirAlumno() throws IOException {
		if (anadirAlumno == null) {
			anadirAlumno = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirAlumno.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirAlumno = cargadorAnadirProfesor.getController();
			cAnadirAlumno.setControladorMVC(controladorMVC);
			cAnadirAlumno.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirAlumno.setTitle("Añadir Alumno");
			anadirAlumno.initModality(Modality.APPLICATION_MODAL);
			anadirAlumno.setScene(escenaAnadirProfesor);
		} else {
			cAnadirAlumno.inicializa();
		}
	}

	private void crearMostrarAlumno(Alumno alumno) throws IOException {
		if (mostrarAlumno == null) {
			mostrarAlumno = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/MostrarAlumno.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cMostrarAlumno = cargadorMostrarProfesor.getController();
			cMostrarAlumno.setControladorMVC(controladorMVC);
			cMostrarAlumno.setAlumno(alumno);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			mostrarAlumno.setTitle("Mostrar Alumno");
			mostrarAlumno.initModality(Modality.APPLICATION_MODAL);
			mostrarAlumno.setScene(escenaMostrarProfesor);
		} else {
			cMostrarAlumno.setAlumno(alumno);
		}
	}

	private void crearBorrarAlumno(Alumno alumno) throws IOException {
		if (borrarAlumno == null) {
			borrarAlumno = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/BorrarAlumno.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cBorrarAlumno = cargadorMostrarProfesor.getController();
			cBorrarAlumno.setControladorMVC(controladorMVC);
			cBorrarAlumno.setAlumno(alumno);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			borrarAlumno.setTitle("Mostrar Alumno");
			borrarAlumno.initModality(Modality.APPLICATION_MODAL);
			borrarAlumno.setScene(escenaMostrarProfesor);
		} else {
			cBorrarAlumno.setAlumno(alumno);
		}
	}

	private void crearListarTutorias() throws IOException {
		if (listarTutorias == null) {
			listarTutorias = new Stage();
			FXMLLoader cargadorListarProfesores = new FXMLLoader(
					getClass().getResource("../vistas/ListarTutorias.fxml"));
			VBox raizListarProfesores = cargadorListarProfesores.load();
			cListarTutorias = cargadorListarProfesores.getController();
			cListarTutorias.setControladorMVC(controladorMVC);
			cListarTutorias.inicializa();
			Scene escenaListarProfesores = new Scene(raizListarProfesores);
			listarTutorias.setTitle("Listar Tutorías");
			listarTutorias.initModality(Modality.APPLICATION_MODAL);
			listarTutorias.setScene(escenaListarProfesores);
		} else {
			cListarTutorias.inicializa();
		}
	}

	private void crearAnadirTutoria() throws IOException {
		if (anadirTutoria == null) {
			anadirTutoria = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirTutoria.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirTutoria = cargadorAnadirProfesor.getController();
			cAnadirTutoria.setControladorMVC(controladorMVC);
			cAnadirTutoria.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirTutoria.setTitle("Añadir Tutoría");
			anadirTutoria.initModality(Modality.APPLICATION_MODAL);
			anadirTutoria.setScene(escenaAnadirProfesor);
		} else {
			cAnadirTutoria.inicializa();
		}
	}

	private void crearBuscarTutoria() throws IOException {
		if (buscarTutoria == null) {
			buscarTutoria = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/BuscarTutoria.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cBuscarTutoria = cargadorMostrarProfesor.getController();
			cBuscarTutoria.setControladorMVC(controladorMVC);
			cBuscarTutoria.inicializa();
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			buscarTutoria.setTitle("Buscar Tutoría");
			buscarTutoria.initModality(Modality.APPLICATION_MODAL);
			buscarTutoria.setScene(escenaMostrarProfesor);
		} else {
			cBuscarTutoria.inicializa();
		}
	}

	private void crearMostrarTutoria(Tutoria tutoria) throws IOException {
		if (mostrarTutoria == null) {
			mostrarTutoria = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(
					getClass().getResource("../vistas/MostrarTutoria.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cMostrarTutoria = cargadorMostrarProfesor.getController();
			cMostrarTutoria.setControladorMVC(controladorMVC);
			cMostrarTutoria.setTutoria(tutoria);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			mostrarTutoria.setTitle("Mostrar Tutoría");
			mostrarTutoria.initModality(Modality.APPLICATION_MODAL);
			mostrarTutoria.setScene(escenaMostrarProfesor);
		} else {
			cMostrarTutoria.setTutoria(tutoria);
		}
	}

	private void crearBorrarTutoria(Tutoria tutoria) throws IOException {
		if (borrarTutoria == null) {
			borrarTutoria = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/BorrarTutoria.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cBorrarTutoria = cargadorMostrarProfesor.getController();
			cBorrarTutoria.setControladorMVC(controladorMVC);
			cBorrarTutoria.setTutoria(tutoria);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			borrarTutoria.setTitle("Mostrar Tutoría");
			borrarTutoria.initModality(Modality.APPLICATION_MODAL);
			borrarTutoria.setScene(escenaMostrarProfesor);
		} else {
			cBorrarTutoria.setTutoria(tutoria);
		}
	}

	private void crearListarSesiones() throws IOException {
		if (listarSesiones == null) {
			listarSesiones = new Stage();
			FXMLLoader cargadorListarProfesores = new FXMLLoader(
					getClass().getResource("../vistas/ListarSesiones.fxml"));
			VBox raizListarProfesores = cargadorListarProfesores.load();
			cListarSesiones = cargadorListarProfesores.getController();
			cListarSesiones.setControladorMVC(controladorMVC);
			cListarSesiones.inicializa();
			Scene escenaListarProfesores = new Scene(raizListarProfesores);
			listarSesiones.setTitle("Listar Sesiones");
			listarSesiones.initModality(Modality.APPLICATION_MODAL);
			listarSesiones.setScene(escenaListarProfesores);
		} else {
			cListarSesiones.inicializa();
		}
	}

	private void crearAnadirSesion() throws IOException {
		if (anadirSesion == null) {
			anadirSesion = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirSesion.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirSesion = cargadorAnadirProfesor.getController();
			cAnadirSesion.setControladorMVC(controladorMVC);
			cAnadirSesion.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirSesion.setTitle("Añadir Sesión");
			anadirSesion.initModality(Modality.APPLICATION_MODAL);
			anadirSesion.setScene(escenaAnadirProfesor);
		} else {
			cAnadirSesion.inicializa();
		}
	}

	private void crearBuscarSesion() throws IOException {
		if (buscarSesion == null) {
			buscarSesion = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/BuscarSesion.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cBuscarSesion = cargadorMostrarProfesor.getController();
			cBuscarSesion.setControladorMVC(controladorMVC);
			cBuscarSesion.inicializa();
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			buscarSesion.setTitle("Buscar Sesión");
			buscarSesion.initModality(Modality.APPLICATION_MODAL);
			buscarSesion.setScene(escenaMostrarProfesor);
		} else {
			cBuscarSesion.inicializa();
		}
	}

	private void crearMostrarSesion(Sesion sesion) throws IOException {
		if (mostrarSesion == null) {
			mostrarSesion = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/MostrarSesion.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cMostrarSesion = cargadorMostrarProfesor.getController();
			cMostrarSesion.setControladorMVC(controladorMVC);
			cMostrarSesion.setSesion(sesion);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			mostrarSesion.setTitle("Mostrar Sesión");
			mostrarSesion.initModality(Modality.APPLICATION_MODAL);
			mostrarSesion.setScene(escenaMostrarProfesor);
		} else {
			cMostrarSesion.setSesion(sesion);
		}
	}

	private void crearBorrarSesion(Sesion sesion) throws IOException {
		if (borrarSesion == null) {
			borrarSesion = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/BorrarSesion.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cBorrarSesion = cargadorMostrarProfesor.getController();
			cBorrarSesion.setControladorMVC(controladorMVC);
			cBorrarSesion.setSesion(sesion);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			borrarSesion.setTitle("Mostrar Sesión");
			borrarSesion.initModality(Modality.APPLICATION_MODAL);
			borrarSesion.setScene(escenaMostrarProfesor);
		} else {
			cBorrarSesion.setSesion(sesion);
		}
	}

	private void crearListarCitas() throws IOException {
		if (listarCitas == null) {
			listarCitas = new Stage();
			FXMLLoader cargadorListarProfesores = new FXMLLoader(getClass().getResource("../vistas/ListarCitas.fxml"));
			VBox raizListarProfesores = cargadorListarProfesores.load();
			cListarCitas = cargadorListarProfesores.getController();
			cListarCitas.setControladorMVC(controladorMVC);
			cListarCitas.inicializa();
			Scene escenaListarProfesores = new Scene(raizListarProfesores);
			listarCitas.setTitle("Listar Citas");
			listarCitas.initModality(Modality.APPLICATION_MODAL);
			listarCitas.setScene(escenaListarProfesores);
		} else {
			cListarCitas.inicializa();
		}
	}

	private void crearAnadirCita() throws IOException {
		if (anadirCita == null) {
			anadirCita = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirCita.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirCita = cargadorAnadirProfesor.getController();
			cAnadirCita.setControladorMVC(controladorMVC);
			cAnadirCita.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirCita.setTitle("Añadir Cita");
			anadirCita.initModality(Modality.APPLICATION_MODAL);
			anadirCita.setScene(escenaAnadirProfesor);
		} else {
			cAnadirCita.inicializa();
		}
	}

	private void crearMostrarCita(Cita cita) throws IOException {
		if (mostrarCita == null) {
			mostrarCita = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/MostrarCita.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cMostrarCita = cargadorMostrarProfesor.getController();
			cMostrarCita.setControladorMVC(controladorMVC);
			cMostrarCita.setCita(cita);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			mostrarCita.setTitle("Mostrar Cita");
			mostrarCita.initModality(Modality.APPLICATION_MODAL);
			mostrarCita.setScene(escenaMostrarProfesor);
		} else {
			cMostrarCita.setCita(cita);
		}
	}

	private void crearBuscarCita() throws IOException {
		if (buscarCita == null) {
			buscarCita = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/BuscarCita.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cBuscarCita = cargadorMostrarProfesor.getController();
			cBuscarCita.setControladorMVC(controladorMVC);
			cBuscarCita.inicializa();
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			buscarCita.setTitle("Buscar Cita");
			buscarCita.initModality(Modality.APPLICATION_MODAL);
			buscarCita.setScene(escenaMostrarProfesor);
		} else {
			cBuscarCita.inicializa();
		}
	}

	private void crearBorrarCita(Cita cita) throws IOException {
		if (borrarCita == null) {
			borrarCita = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(getClass().getResource("../vistas/BorrarCita.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cBorrarCita = cargadorMostrarProfesor.getController();
			cBorrarCita.setControladorMVC(controladorMVC);
			cBorrarCita.setCita(cita);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			borrarCita.setTitle("Mostrar Cita");
			borrarCita.initModality(Modality.APPLICATION_MODAL);
			borrarCita.setScene(escenaMostrarProfesor);
		} else {
			cBorrarCita.setCita(cita);
		}
	}

}
