package org.iesalandalus.programacion.tutorias.mvc.modelo;

import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.FactoriaFuenteDatosMemoria;

public enum FactoriaFuenteDatos {

	FICHEROS {
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosMemoria();
		}
	};

	public abstract IFuenteDatos crear();
}
