package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public interface IProfesores {

	void comenzar();

	void terminar();

	// Getters
	List<Profesor> get();

	int getTamano();

	// Insertar profesor
	void insertar(Profesor profesor) throws OperationNotSupportedException;

	// Buscar profesores
	Profesor buscar(Profesor profesor);

	// Borrar profesore
	void borrar(Profesor profesor) throws OperationNotSupportedException;

}