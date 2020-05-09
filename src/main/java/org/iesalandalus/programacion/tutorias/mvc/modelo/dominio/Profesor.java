package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Profesor implements Serializable {

	private static final String ER_NOMBRE = "(?=.*\\s.+)(?![a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]$).[a-zA-Zñáéíóúü\\s]+";
	private static final String ER_DNI = "[0-9]{8,8}[a-zA-Z]$";
	private static final String ER_CORREO = "\\w+[\\.\\w]*@\\w+[\\.\\w]*\\.\\w{2,5}\\b\\s?";
	private String nombre;
	private String dni;
	private String correo;

	// Constructor con parámetros
	public Profesor(String nombre, String dni, String correo) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
	}

	// Constructor de copia
	public Profesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No es posible copiar un profesor nulo.");
		}
		setNombre(profesor.getNombre());
		setDni(profesor.getDni());
		setCorreo(profesor.getCorreo());

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

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		} else if (dni.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}

		if (dni.matches(ER_DNI)) {
			if (!(comprobarLetraDNI(dni) == false)) {
				this.dni = dni.toUpperCase();
			} else {

				throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
			}
		} else {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
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

	// Profesor fictio
	public static Profesor getProfesorFicticio(String DNI) {
		return new Profesor("Nombre Propio", DNI, "correo@gmail.com");
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

	// Comprobamos la letra del dni
	private boolean comprobarLetraDNI(String dni) {

		// setDni(dni);

		// Separamos los numeros de la letra del DNI
		String numerosDni = dni.substring(0, dni.length() - 1);
		String letraDni = dni.substring(dni.length() - 1);

		// Pasamos a entero la cadena de numeros del dni para poder dividir
		int enteroDni = Integer.parseInt(numerosDni);

		// Realizamos la operacion para saber el numero de letra
		int numero = enteroDni % 23;

		char[] caracteres = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };

		String letraMayuscula = letraDni.toUpperCase();
		// Pasamos a char letraDni para poder comprarlo con el array de caracteres
		char letraDniCaracter = letraMayuscula.charAt(0);

		// Comprobamos la letra que ha introducido el usuario con la letra que hemos
		// obtenido nosotros

		if (caracteres[numero] == letraDniCaracter) {
			return true;
		} else {

			return false;
		}
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

	// hasCode y Equals
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Profesor)) {
			return false;
		}
		Profesor other = (Profesor) obj;
		return Objects.equals(dni, other.dni);
	}

	// to String
	@Override
	public String toString() {
		return String.format("nombre=%s (%s), DNI=%s, correo=%s", nombre, getIniciales(), dni, correo);
	}

}
