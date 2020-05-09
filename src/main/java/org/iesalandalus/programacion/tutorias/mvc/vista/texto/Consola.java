package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.naming.TimeLimitExceededException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("*** MENÚ INICIAL ***");
		for (Opcion opciones : Opcion.values()) {
			System.out.println(opciones.toString());
		}
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String formatoStr = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(formatoStr, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int opcion;
		do {
			System.out.print("Elige una opción del menú: ");
			opcion = Entrada.entero();

		} while (!Opcion.esOrdinalValido(opcion));

		return opcion;
	}

	public static Alumno leerAlumno() {
		String nombreAlumno, correoAlumno;
		System.out.println("Introduce el nombre del alumno: ");
		nombreAlumno = Entrada.cadena();
		System.out.println("Introduce el correo del alumno: ");
		correoAlumno = Entrada.cadena();
		System.out.println("");

		return new Alumno(nombreAlumno, correoAlumno);
	}

	public static Alumno leerAlumnoFicticio() {
		String correoAlumno;
		System.out.println("Introduce el correo del alumno: ");
		correoAlumno = Entrada.cadena();
		System.out.println("");

		return Alumno.getAlumnoFicticio(correoAlumno);
	}

	public static Profesor leerProfesor() {
		String nombreProfesor, dni, correoProfesor;
		System.out.println("Introduce el nombre del profesor: ");
		nombreProfesor = Entrada.cadena();
		System.out.println("Introduce el DNI del profesor: ");
		dni = Entrada.cadena();
		System.out.println("Introduce el correo del profesor: ");
		correoProfesor = Entrada.cadena();
		System.out.println("");

		return new Profesor(nombreProfesor, dni, correoProfesor);
	}

	public static Profesor leerProfesorFicticio() {
		String dniProfesor;
		System.out.println("Introduce el DNI del profesor: ");
		dniProfesor = Entrada.cadena();
		System.out.println("");

		return Profesor.getProfesorFicticio(dniProfesor);
	}

	public static Tutoria leerTutoria() {
		String nombreTutoria;
		System.out.println("Introduce el nombre de la tutoría: ");
		nombreTutoria = Entrada.cadena();
		System.out.println("");

		return new Tutoria(leerProfesorFicticio(), nombreTutoria);
	}

	public static Sesion leerSesion() {
		LocalDate fecha = null;
		LocalTime horaInicioSesion = null, horaFinSesion = null;
		String fechaUsuario, horaInicioUsuario, horaFinUsuario;
		int minDuracion;

		Tutoria tutoria = leerTutoria();

		System.out.println("** Fecha **");
		try {
			System.out.println("Introduce la fecha con el formato (dia/mes/año)");
			fechaUsuario = Entrada.cadena();
			fecha = LocalDate.parse(fechaUsuario, Sesion.FORMATO_FECHA);
		} catch (DateTimeParseException d) {
			throw new IllegalArgumentException("ERROR: La fecha no tiene un formato correcto.");
		}
		System.out.println("");

		try {
			System.out.println("** Hora Inicio Sesión **");
			System.out.println("Introduce la hora con el formato (hora:minutos)");
			horaInicioUsuario = Entrada.cadena();

			horaInicioSesion = LocalTime.parse(horaInicioUsuario, Sesion.FORMATO_HORA);
			System.out.println("");

			System.out.println("** Hora Fin Sesión **");
			System.out.println("Introduce la hora con el formato (hora:minutos)");
			horaFinUsuario = Entrada.cadena();

			horaFinSesion = LocalTime.parse(horaFinUsuario, Sesion.FORMATO_HORA);
			System.out.println("");

		} catch (DateTimeParseException d) {
			throw new IllegalArgumentException("ERROR: La hora no tiene un formato correcto.");
		}

		System.out.println("** Minutos de duración **");
		System.out.println("Introduce los minutos de duración de la sesión: ");
		minDuracion = Entrada.entero();
		System.out.println("");

		return new Sesion(tutoria, fecha, horaInicioSesion, horaFinSesion, minDuracion);

	}

	public static Sesion leerSesionFicticia() {
		LocalDate fecha;
		String fechaUsuario;

		Tutoria tutoria = leerTutoria();

		System.out.println("** Fecha **");
		try {
			System.out.println("Introduce la fecha con el formato (dia/mes/año)");
			fechaUsuario = Entrada.cadena();
			fecha = LocalDate.parse(fechaUsuario, Sesion.FORMATO_FECHA);
		} catch (DateTimeParseException d) {
			throw new IllegalArgumentException("ERROR: La fecha no tiene un formato correcto.");
		}
		fecha = LocalDate.parse(fechaUsuario, Sesion.FORMATO_FECHA);
		System.out.println("");

		return Sesion.getSesionFicticia(tutoria, fecha);
	}

	public static Cita leerCita() {
		LocalTime horaCita;
		String horaCitaUsuario;

		Alumno alumno = leerAlumnoFicticio();
		Sesion sesion = leerSesionFicticia();
		try {
			System.out.println("Introduce la hora con el formato (hora:minutos)");
			horaCitaUsuario = Entrada.cadena();
		} catch (DateTimeParseException d) {
			throw new IllegalArgumentException("ERROR: La fecha no tiene un formato correcto.");
		}

		horaCita = LocalTime.parse(horaCitaUsuario, Sesion.FORMATO_HORA);
		System.out.println("");

		return new Cita(alumno, sesion, horaCita);
	}

}
