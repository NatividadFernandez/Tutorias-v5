package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Sesion implements Serializable {

	private Tutoria tutoria;
	private static final LocalTime HORA_COMIENZO_CLASES = LocalTime.of(16, 00);
	private static final LocalTime HORA_FIN_CLASES = LocalTime.of(22, 15);
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalDate fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private int minutosDuracion;

	// Constructor con parámetros
	public Sesion(Tutoria tutoria, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int minutosDuracion) {
		setTutoria(tutoria);
		setFecha(fecha);
		setHoraInicio(horaInicio);
		setHoraFin(horaFin);
		setMinutosDuracion(minutosDuracion);
		comprobarValidezSesion();

	}

	// Constructor de copia
	public Sesion(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una sesión nula.");
		}

		setTutoria(sesion.getTutoria());
		setFecha(sesion.getFecha());
		setHoraInicio(sesion.getHoraInicio());
		setHoraFin(sesion.getHoraFin());
		setMinutosDuracion(sesion.getMinutosDuracion());
	}

	// Getters y Setters
	public Tutoria getTutoria() {
		return new Tutoria(tutoria);
	}

	private void setTutoria(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}

		this.tutoria = new Tutoria(tutoria);
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}

		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	private void setHoraInicio(LocalTime horaInicio) {
		if (horaInicio == null) {
			throw new NullPointerException("ERROR: La hora de inicio no puede ser nula.");
		}

		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	private void setHoraFin(LocalTime horaFin) {
		if (horaFin == null) {
			throw new NullPointerException("ERROR: La hora de fin no puede ser nula.");
		}

		this.horaFin = horaFin;
	}

	public int getMinutosDuracion() {
		return minutosDuracion;
	}

	private void setMinutosDuracion(int minutosDuracion) {
		if (minutosDuracion == 0) {
			throw new IllegalArgumentException("ERROR: Los minutos de duración no son válidos.");
		}

		this.minutosDuracion = minutosDuracion;

	}

	// Validez Sesion
	private void comprobarValidezSesion() {

		if (fecha.equals(LocalDate.now()) || fecha.getYear() < LocalDate.now().getYear()) {
			throw new IllegalArgumentException("ERROR: Las sesiones de deben planificar para fechas futuras.");
		}

		if (this.horaInicio.isBefore(HORA_COMIENZO_CLASES) || this.horaInicio.equals(HORA_FIN_CLASES)
				|| this.horaInicio.isAfter(HORA_FIN_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
		}

		if (this.horaFin.isBefore(HORA_COMIENZO_CLASES) || this.horaFin.equals(HORA_COMIENZO_CLASES)
				|| this.horaFin.isAfter(HORA_FIN_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de fin no es válida.");
		}

		if (this.horaFin.equals(this.horaInicio) || this.horaFin.isBefore(this.horaInicio)) {
			throw new IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas.");
		}
		if (((this.horaFin.toSecondOfDay() - this.horaInicio.toSecondOfDay()) / 60) % this.minutosDuracion != 0) {
			throw new IllegalArgumentException(
					"ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión.");
		}

	}

	// Sesion ficticia
	public static Sesion getSesionFicticia(Tutoria tutoria, LocalDate fecha) {
		return new Sesion(tutoria, fecha, HORA_COMIENZO_CLASES, HORA_FIN_CLASES, 1);
	}

	// hasCode y Equals
	@Override
	public int hashCode() {
		return Objects.hash(fecha, tutoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sesion)) {
			return false;
		}
		Sesion other = (Sesion) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(tutoria, other.tutoria);
	}

	// toString
	@Override
	public String toString() {
		return String.format("tutoria=%s, fecha=%s, horaInicio=%s, horaFin=%s, minutosDuracion=%d", tutoria,
				fecha.format(Sesion.FORMATO_FECHA), horaInicio.format(Sesion.FORMATO_HORA),
				horaFin.format(Sesion.FORMATO_HORA), minutosDuracion);
	}

}
