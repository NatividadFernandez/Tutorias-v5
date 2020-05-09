package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;

public class VistaTexto implements IVista {

	private IControlador controlador;

	public VistaTexto() {
		Opcion.setVista(this);
	}

	@Override
	public void setControlador(IControlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public void comenzar() {
		Consola.mostrarCabecera("Programa para la gestión de tutorías del IES Al-Ándalus");
		int opcion;
		Opcion opciones;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			opciones = Opcion.getOpcionSegunOrdinal(opcion);
			opciones.ejecutar();
		} while (opcion != Opcion.SALIR.ordinal());
	}

	@Override
	public void terminar() {
		controlador.terminar();
	}

	// Alumno
	public void insertarAlumno() {
		Consola.mostrarCabecera("Insertar Alumno");
		try {
			controlador.insertarAlumno(Consola.leerAlumno());
			System.out.println("Alumno insertado correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarAlumno() {
		Consola.mostrarCabecera("Buscar Alumno");
		Alumno alumno;
		try {
			alumno = controlador.buscarAlumno(Consola.leerAlumnoFicticio());
			String mensaje = (alumno != null) ? alumno.toString() : "No existe dicho alumno.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlumno() {
		Consola.mostrarCabecera("Borrar Alumno");
		try {
			controlador.borrarAlumno(Consola.leerAlumnoFicticio());
			System.out.println("Alumno borrado correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void listarAlumnos() {
		Consola.mostrarCabecera("Listar Alumnos");
		List<Alumno> alumnos = controlador.getAlumnos();
		if (!alumnos.isEmpty()) {
			for (Alumno alumno : alumnos) {
				System.out.println(alumno);
			}
		} else {
			System.out.println("No hay alumnos que mostrar.");
		}
	}

	// Profesor
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesorFicticio());
			String mensaje = (profesor != null) ? profesor.toString() : "No existe dicho profesor.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesorFicticio());
			System.out.println("Profesor borrado correctamente.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	// DUDAS
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar Profesores");
		List<Profesor> profesores = controlador.getProfesores();
		if (!profesores.isEmpty()) {
			for (Profesor profesor : profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que mostrar.");
		}
	}

	// Tutorias
	public void insertarTutoria() {
		Consola.mostrarCabecera("Insertar Tutoría");
		try {
			controlador.insertarTutoria(Consola.leerTutoria());
			System.out.println("Tutoría insertada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarTutoria() {
		Consola.mostrarCabecera("Buscar Tutoría");
		Tutoria tutoria;
		try {
			tutoria = controlador.buscarTutoria(Consola.leerTutoria());
			String mensaje = (tutoria != null) ? tutoria.toString() : "No existe dicha tutoría.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarTutoria() {
		Consola.mostrarCabecera("Borrar Tutoría");
		try {
			controlador.borrarTutoria(Consola.leerTutoria());
			System.out.println("Tutoría borrada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarTutorias() {
		Consola.mostrarCabecera("Listar Tutorías");
		List<Tutoria> tutorias = controlador.getTutorias();
		if (!tutorias.isEmpty()) {
			for (Tutoria tutoria : tutorias) {
				System.out.println(tutoria);
			}
		} else {
			System.out.println("No hay tutorías que mostrar.");
		}
	}

	public void listarTutoriasProfesor() {
		Consola.mostrarCabecera("Listar Tutorías Profesor");
		List<Tutoria> tutorias = controlador.getTutorias(Consola.leerProfesorFicticio());
		if (!tutorias.isEmpty()) {
			for (Tutoria tutoria : tutorias) {
				System.out.println(tutoria);
			}
		} else {
			System.out.println("No hay tutorías, para dicho profesor, que mostrar.");
		}
	}

	// Sesion
	public void insertarSesion() {
		Consola.mostrarCabecera("Insertar Sesión");
		try {
			controlador.insertarSesion(Consola.leerSesion());
			System.out.println("Sesión insertada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarSesion() {
		Consola.mostrarCabecera("Buscar Sesión");
		Sesion sesion;
		try {
			sesion = controlador.buscarSesion(Consola.leerSesionFicticia());
			String mensaje = (sesion != null) ? sesion.toString() : "No existe dicha sesión.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarSesion() {
		Consola.mostrarCabecera("Borrar Sesión");
		try {
			controlador.borrarSesion(Consola.leerSesionFicticia());
			System.out.println("Sesión borrada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarSesiones() {
		Consola.mostrarCabecera("Listar Sesiones");
		List<Sesion> sesiones = controlador.getSesiones();
		if (!sesiones.isEmpty()) {
			for (Sesion sesion : sesiones) {
				System.out.println(sesion);
			}
		} else {
			System.out.println("No hay sesiones que mostrar.");
		}
	}

	public void listarSesionesTutoria() {
		Consola.mostrarCabecera("Listar Sesiones Tutoría");
		List<Sesion> sesiones = controlador.getSesiones(Consola.leerTutoria());
		if (!sesiones.isEmpty()) {
			for (Sesion sesion : sesiones) {
				System.out.println(sesion);
			}
		} else {
			System.out.println("No hay sesiones, para dicha tutoría, que mostrar.");
		}
	}

	// Cita
	public void insertarCita() {
		Consola.mostrarCabecera("Insertar Cita");
		try {
			controlador.insertarCita(Consola.leerCita());
			System.out.println("Cita insertada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarCita() {
		Consola.mostrarCabecera("Buscar Cita");
		Cita cita;
		try {
			cita = controlador.buscarCita(Consola.leerCita());
			String mensaje = (cita != null) ? cita.toString() : "No existe dicha cita.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarCita() {
		Consola.mostrarCabecera("Borrar Cita");
		try {
			controlador.borrarCita(Consola.leerCita());
			System.out.println("Cita borrada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarCitas() {
		Consola.mostrarCabecera("Listar Citas");
		List<Cita> citas = controlador.getCitas();
		if (!citas.isEmpty()) {
			for (Cita cita : citas) {
				System.out.println(cita);
			}
		} else {
			System.out.println("No hay citas que mostrar.");
		}
	}

	public void listarCitasSesion() {
		Consola.mostrarCabecera("Listas Citas Sesiones");
		List<Cita> citas = controlador.getCitas(Consola.leerSesionFicticia());
		if (!citas.isEmpty()) {
			for (Cita cita : citas) {
				System.out.println(cita);
			}
		} else {
			System.out.println("No hay citas, para dicha sesión, que mostrar.");
		}
	}

	public void listarCitasAlumno() {
		Consola.mostrarCabecera("Listas Citas Alumnos");
		List<Cita> citas = controlador.getCitas(Consola.leerAlumnoFicticio());
		if (!citas.isEmpty()) {
			for (Cita cita : citas) {
				System.out.println(cita);
			}
		} else {
			System.out.println("No hay citas, para dicho alumno, que mostrar.");
		}
	}
}
