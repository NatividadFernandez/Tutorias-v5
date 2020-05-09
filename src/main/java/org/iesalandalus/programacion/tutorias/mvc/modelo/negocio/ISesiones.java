package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface ISesiones {

	void comenzar();

	void terminar();

	// Getters
	List<Sesion> get();

	// Sesion tutoria
	List<Sesion> get(Tutoria tutoria);

	int getTamano();

	// Insertar sesion
	void insertar(Sesion sesion) throws OperationNotSupportedException;

	// Buscar sesiones
	Sesion buscar(Sesion sesion);

	// Borrar sesiones
	void borrar(Sesion sesion) throws OperationNotSupportedException;

}