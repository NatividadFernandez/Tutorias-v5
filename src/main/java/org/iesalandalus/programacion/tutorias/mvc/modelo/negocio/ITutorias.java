package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface ITutorias {

	void comenzar();

	void terminar();

	// Getters
	List<Tutoria> get();

	// Tutoria profesor
	List<Tutoria> get(Profesor profesor);

	int getTamano();

	// Insertar tutoria
	void insertar(Tutoria tutoria) throws OperationNotSupportedException;

	// Buscar tutorias
	Tutoria buscar(Tutoria tutoria);

	// Borrar tutorias
	void borrar(Tutoria tutoria) throws OperationNotSupportedException;

}