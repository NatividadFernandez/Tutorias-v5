package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

public interface ICitas {

	void comenzar();

	void terminar();

	// Getters
	List<Cita> get();

	// Cita sesion
	List<Cita> get(Sesion sesion);

	// Cita alumno
	List<Cita> get(Alumno alumno);

	int getTamano();

	// Insertar citas
	void insertar(Cita cita) throws OperationNotSupportedException;

	// Buscar citas
	Cita buscar(Cita cita);

	// Borrar citas
	void borrar(Cita cita) throws OperationNotSupportedException;

}