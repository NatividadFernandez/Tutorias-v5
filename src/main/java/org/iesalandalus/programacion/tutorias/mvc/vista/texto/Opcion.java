package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

public enum Opcion {

	INSERTAR_ALUMNO("Insertar Alumno") {

		@Override
		public void ejecutar() {
			vista.insertarAlumno();
		}

	},

	BUSCAR_ALUMNO("Buscar Alumno") {

		@Override
		public void ejecutar() {
			vista.buscarAlumno();
		}

	},

	BORRAR_ALUMNO("Borrar Alumno") {

		@Override
		public void ejecutar() {
			vista.borrarAlumno();
		}

	},

	LISTAR_ALUMNOS("Listar Alumnos") {

		@Override
		public void ejecutar() {
			vista.listarAlumnos();
		}

	},

	INSERTAR_PROFESOR("Insertar Profesor") {

		@Override
		public void ejecutar() {
			vista.insertarProfesor();
		}

	},

	BUSCAR_PROFESOR("Buscar Profesor") {

		@Override
		public void ejecutar() {
			vista.buscarProfesor();
		}

	},

	BORRAR_PROFESOR("Borrar Profesor") {

		@Override
		public void ejecutar() {
			vista.borrarProfesor();
		}

	},

	LISTAR_PROFESORES("Listar Profesores") {

		@Override
		public void ejecutar() {
			vista.listarProfesores();
		}

	},

	INSERTAR_TUTORIA("Insertar Tutoría") {

		@Override
		public void ejecutar() {
			vista.insertarTutoria();
		}

	},

	BUSCAR_TUTORIA("Buscar Tutoría") {

		@Override
		public void ejecutar() {
			vista.buscarTutoria();
		}

	},

	BORRAR_TUTORIA("Borrar Tutoría") {

		@Override
		public void ejecutar() {
			vista.borrarTutoria();
		}

	},

	LISTAR_TUTORIAS("Listar Tutorías") {

		@Override
		public void ejecutar() {
			vista.listarTutorias();
		}

	},

	LISTAR_TUTORIAS_PROFESOR("Listar Tutorías Profesor") {

		@Override
		public void ejecutar() {
			vista.listarTutoriasProfesor();
		}

	},

	INSERTAR_SESION("Insertar Sesión") {

		@Override
		public void ejecutar() {
			vista.insertarSesion();
		}

	},

	BUSCAR_SESION("Buscar Sesión") {

		@Override
		public void ejecutar() {
			vista.buscarSesion();
		}

	},

	BORRAR_SESION("Borrar Sesión") {

		@Override
		public void ejecutar() {
			vista.borrarSesion();
		}

	},

	LISTAR_SESIONES("Listar Sesiones") {

		@Override
		public void ejecutar() {
			vista.listarSesiones();
		}

	},

	LISTAR_SESIONES_TUTORIA("Listar Sesiones Tutoría") {

		@Override
		public void ejecutar() {
			vista.listarSesionesTutoria();
		}

	},

	INSERTAR_CITA("Insertar Cita") {

		@Override
		public void ejecutar() {
			vista.insertarCita();
		}

	},

	BUSCAR_CITA("Buscar Cita") {

		@Override
		public void ejecutar() {
			vista.buscarCita();
		}

	},

	BORRAR_CITA("Borrar Cita") {

		@Override
		public void ejecutar() {
			vista.borrarCita();
		}

	},

	LISTAR_CITAS("Listar Cita") {

		@Override
		public void ejecutar() {
			vista.listarCitas();
		}

	},

	LISTAR_CITAS_SESION("Listar Citas Sesiones") {

		@Override
		public void ejecutar() {
			vista.listarCitasSesion();

		}

	},

	LISTAR_CITAS_ALUMNO("Listar Citas Alumnos") {

		@Override
		public void ejecutar() {
			vista.listarCitasAlumno();
		}

	},

	SALIR("Salir") {

		@Override
		public void ejecutar() {
			vista.terminar();
		}

	};

	private String mensaje;
	private static VistaTexto vista;

	private Opcion(String opcion) {
		this.mensaje = opcion;
	}

	public abstract void ejecutar();

	protected static void setVista(VistaTexto vista) {
		Opcion.vista = vista;
	}

	public static Opcion getOpcionSegunOrdinal(int opcion) {

		if (esOrdinalValido(opcion)) {
			return values()[opcion];
		} else {
			throw new IllegalArgumentException("La opción introducida es incorrecta.");
		}

	}

	public static boolean esOrdinalValido(int opcion) {
		return (opcion >= 0 && opcion <= values().length - 1);
	}

	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensaje);
	}

}
