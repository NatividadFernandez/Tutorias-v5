package org.iesalandalus.programacion.tutorias.mvc.modelo;

import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.FactoriaFuenteDatosMemoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.FactoriaFuenteDatosMongoDB;

public enum FactoriaFuenteDatos {

	FICHEROS {
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosMemoria();
		}
	},
	MONGODB {
		@Override
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosMongoDB();
		}

	};

	public abstract IFuenteDatos crear();
}
