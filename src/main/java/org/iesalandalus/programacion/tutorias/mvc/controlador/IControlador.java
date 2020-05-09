package org.iesalandalus.programacion.tutorias.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface IControlador {

	void comenzar();

	void terminar();

	// Alumno
	void insertarAlumno(Alumno alumno) throws OperationNotSupportedException;

	Alumno buscarAlumno(Alumno alumno);

	void borrarAlumno(Alumno alumno) throws OperationNotSupportedException;

	List<Alumno> getAlumnos();

	// Profesor
	void insertarProfesor(Profesor profesor) throws OperationNotSupportedException;

	Profesor buscarProfesor(Profesor profesor);

	void borrarProfesor(Profesor profesor) throws OperationNotSupportedException;

	List<Profesor> getProfesores();

	// Tutorias
	void insertarTutoria(Tutoria tutoria) throws OperationNotSupportedException;

	Tutoria buscarTutoria(Tutoria tutoria);

	void borrarTutoria(Tutoria tutoria) throws OperationNotSupportedException;

	List<Tutoria> getTutorias();

	List<Tutoria> getTutorias(Profesor profesor);

	// Sesion
	void insertarSesion(Sesion sesion) throws OperationNotSupportedException;

	Sesion buscarSesion(Sesion sesion);

	void borrarSesion(Sesion sesion) throws OperationNotSupportedException;

	List<Sesion> getSesiones();

	List<Sesion> getSesiones(Tutoria tutoria);

	// Cita
	void insertarCita(Cita cita) throws OperationNotSupportedException;

	Cita buscarCita(Cita cita);

	void borrarCita(Cita cita) throws OperationNotSupportedException;

	List<Cita> getCitas();

	List<Cita> getCitas(Sesion sesion);

	List<Cita> getCitas(Alumno alumno);

}