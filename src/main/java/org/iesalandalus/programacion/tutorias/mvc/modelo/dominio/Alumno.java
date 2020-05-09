package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Alumnos;

public class Alumno implements Serializable {

	private static final String ER_NOMBRE = "(?=.*\\s.+)(?![a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]$).[a-zA-Zñáéíóúü\\s]+";
	private static final String PREFIJO_EXPEDIENTE = "SP_";
	private static final String ER_CORREO = "\\w+[\\.\\w]*@\\w+[\\.\\w]*\\.\\w{2,5}\\b\\s?";
	private static int ultimoIdentificador;
	private String nombre;
	private String correo;
	private String expediente;

	// Constructor con parámetros
	public Alumno(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
		incremientaUltimoIdentificador();
		setExpediente();

	}

	// Constructor copia
	public Alumno(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
		}
		setNombre(alumno.getNombre());
		setCorreo(alumno.getCorreo());
		this.expediente = alumno.getExpediente();

	}

	public void comprobarUltimoIdentificador() {
		incremientaUltimoIdentificador();
	}

	// Alumno ficticio
	public static Alumno getAlumnoFicticio(String correo) {
		return new Alumno("Nombre Propio", correo);
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		String nombreCorrecto;
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}

		nombreCorrecto = formateaNombre(nombre);

		if (!nombreCorrecto.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}

		this.nombre = nombreCorrecto;
	}

	public String getCorreo() {
		return correo;
	}

	private void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo no puede ser nulo.");
		} else if (correo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}

		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}

		this.correo = correo;
	}

	public String getExpediente() {
		return expediente;
	}

	private void setExpediente() {
		this.expediente = PREFIJO_EXPEDIENTE + getIniciales() + "_" + ultimoIdentificador;
	}

	// Formateamos el nombre
	private String formateaNombre(String nombre) {
		String nuevoNombre;
		// Pasamos todo el nombre a misnuscula
		nuevoNombre = nombre.toLowerCase();

		char[] caracteres = nuevoNombre.toCharArray();
		caracteres[0] = Character.toUpperCase(caracteres[0]);

		for (int i = 0; i < nuevoNombre.length() - 2; i++) {
			// Es 'palabra'
			if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',') {
				// Reemplazamos
				caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
			}

		}
		nuevoNombre = String.valueOf(caracteres).trim().replace("  ", "");

		return nuevoNombre;
	}

	// Recogemos las iniciales del nombre
	private String getIniciales() {

		String inicialesNombre = "";
		// Vamos dividiendo el nombre conforme se encuentra un espacio
		String[] nombreCompleto = this.nombre.split(" ");

		// Recorremos el nombre ya dividido
		for (int i = 0; i < nombreCompleto.length; i++) {

			// Si no se encuentra un espacio vacio
			if (!nombreCompleto[i].equals("")) {
				// Se va almacenando la primera posicion de la cadena dividida
				inicialesNombre += nombreCompleto[i].charAt(0);
			}
		}
		return inicialesNombre;
	}

	// Incrementamos el identificador
	private static void incremientaUltimoIdentificador() {
		ultimoIdentificador++;
	}

	// hasCode y Equals
	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno other = (Alumno) obj;
		return Objects.equals(correo, other.correo);
	}

	public static void comprobarUltimoIdentificador(int numeroIdentificador) {
		ultimoIdentificador = numeroIdentificador;
	}

	// to String
	@Override
	public String toString() {
		return String.format("nombre=%s (%s), correo=%s, expediente=%s", nombre, getIniciales(), correo, expediente);
	}

}
