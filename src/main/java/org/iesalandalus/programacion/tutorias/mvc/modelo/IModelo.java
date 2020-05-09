package org.iesalandalus.programacion.tutorias.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface IModelo {

	void comenzar();

	void terminar();

	// Alumno
	void insertar(Alumno alumno) throws OperationNotSupportedException;

	Alumno buscar(Alumno alumno);

	void borrar(Alumno alumno) throws OperationNotSupportedException;

	List<Alumno> getAlumnos();

	// Profesor
	void insertar(Profesor profesor) throws OperationNotSupportedException;

	Profesor buscar(Profesor profesor);

	void borrar(Profesor profesor) throws OperationNotSupportedException;

	List<Profesor> getProfesores();

	// Tutoría
	void insertar(Tutoria tutoria) throws OperationNotSupportedException;

	Tutoria buscar(Tutoria tutoria);

	void borrar(Tutoria tutoria) throws OperationNotSupportedException;

	List<Tutoria> getTutorias();

	List<Tutoria> getTutorias(Profesor profesor);

	// Sesion
	void insertar(Sesion sesion) throws OperationNotSupportedException;

	Sesion buscar(Sesion sesion);

	void borrar(Sesion sesion) throws OperationNotSupportedException;

	List<Sesion> getSesiones();

	List<Sesion> getSesiones(Tutoria tutoria);

	// Cita
	void insertar(Cita cita) throws OperationNotSupportedException;

	Cita buscar(Cita cita);

	void borrar(Cita cita) throws OperationNotSupportedException;

	List<Cita> getCitas();

	List<Cita> getCitas(Sesion sesion);

	List<Cita> getCitas(Alumno alumno);

}