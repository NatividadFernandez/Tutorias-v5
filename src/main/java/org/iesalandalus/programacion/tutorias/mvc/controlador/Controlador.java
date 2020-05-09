package org.iesalandalus.programacion.tutorias.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Alumnos;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;

public class Controlador implements IControlador {

	private IModelo modelo;
	private IVista vista;

	public Controlador(IModelo modelo, IVista vista) {
		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}

		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}

		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}

	@Override
	public void comenzar() {
		modelo.comenzar();
		vista.comenzar();
	}

	@Override
	public void terminar() {
		modelo.terminar();
		System.out.println("¡Hasta Luego!");
	}

	// Alumno
	@Override
	public void insertarAlumno(Alumno alumno) throws OperationNotSupportedException {
		modelo.insertar(alumno);
	}

	@Override
	public Alumno buscarAlumno(Alumno alumno) {
		return modelo.buscar(alumno);
	}

	@Override
	public void borrarAlumno(Alumno alumno) throws OperationNotSupportedException {
		modelo.borrar(alumno);
	}

	@Override
	public List<Alumno> getAlumnos() {
		return modelo.getAlumnos();
	}

	// Profesor
	@Override
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertar(profesor);
	}

	@Override
	public Profesor buscarProfesor(Profesor profesor) {
		return modelo.buscar(profesor);
	}

	@Override
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrar(profesor);
	}

	@Override
	public List<Profesor> getProfesores() {
		return modelo.getProfesores();
	}

	// Tutorias
	@Override
	public void insertarTutoria(Tutoria tutoria) throws OperationNotSupportedException {
		modelo.insertar(tutoria);
	}

	@Override
	public Tutoria buscarTutoria(Tutoria tutoria) {
		return modelo.buscar(tutoria);
	}

	@Override
	public void borrarTutoria(Tutoria tutoria) throws OperationNotSupportedException {
		modelo.borrar(tutoria);
	}

	@Override
	public List<Tutoria> getTutorias() {
		return modelo.getTutorias();
	}

	@Override
	public List<Tutoria> getTutorias(Profesor profesor) {
		return modelo.getTutorias(profesor);
	}

	// Sesion
	@Override
	public void insertarSesion(Sesion sesion) throws OperationNotSupportedException {
		modelo.insertar(sesion);
	}

	@Override
	public Sesion buscarSesion(Sesion sesion) {
		return modelo.buscar(sesion);
	}

	@Override
	public void borrarSesion(Sesion sesion) throws OperationNotSupportedException {
		modelo.borrar(sesion);
	}

	@Override
	public List<Sesion> getSesiones() {
		return modelo.getSesiones();
	}

	@Override
	public List<Sesion> getSesiones(Tutoria tutoria) {
		return modelo.getSesiones(tutoria);
	}

	// Cita
	@Override
	public void insertarCita(Cita cita) throws OperationNotSupportedException {
		modelo.insertar(cita);
	}

	@Override
	public Cita buscarCita(Cita cita) {
		return modelo.buscar(cita);
	}

	@Override
	public void borrarCita(Cita cita) throws OperationNotSupportedException {
		modelo.borrar(cita);
	}

	@Override
	public List<Cita> getCitas() {
		return modelo.getCitas();
	}

	@Override
	public List<Cita> getCitas(Sesion sesion) {
		return modelo.getCitas(sesion);
	}

	@Override
	public List<Cita> getCitas(Alumno alumno) {
		return modelo.getCitas(alumno);
	}

}
