package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

public interface IAlumnos {

	void comenzar();

	void terminar();

	// Getters
	List<Alumno> get();

	int getTamano();

	// Insertar alumno
	void insertar(Alumno alumno) throws OperationNotSupportedException;

	// Buscar alumnos
	Alumno buscar(Alumno alumno);

	// Borrar alumnos
	void borrar(Alumno alumno) throws OperationNotSupportedException;

}