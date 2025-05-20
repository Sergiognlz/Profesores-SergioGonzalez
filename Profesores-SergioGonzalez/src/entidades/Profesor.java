package entidades;

/**
 * clase objeto profesor que representa... pues un profesor
 */
public class Profesor {
	/**
	 * atributo privado numérico id de profesor
	 */
	private int idProfesor;
	/**
	 * atributo privado cadena nombre de profesor
	 */
	private String nombre;
	/**
	 * atributo privado cadena apellido de profesor
	 */
	private String apellido;
	/**
	 * atributo privado cadena especialidad del profesor
	 */
	private String especialidad;
	/**
	 * atributo privado cadena email
	 */
	private String email;



	/**
	 * Constructor completo de objeto profesor. No tiene nombre porque es
	 * autoincrementativo
	 * 
	 * @param nombre       del profesor. No puede ser nulo, estar vacío o ser un
	 *                     espacio en blanco
	 * @param apellido     del profesor. No puede ser nulo, estar vacío o ser un
	 *                     espacio en blanco
	 * @param especialidad del profesor. No puede ser nulo, estar vacío o ser un
	 *                     espacio en blanco
	 * @param email        del profesor. No puede ser nulo, estar vacío o ser un
	 *                     espacio en blanco
	 */
	public Profesor(String nombre, String apellido, String especialidad, String email) {
		if (nombre != null && !nombre.isBlank()) {
			this.nombre = nombre;
		}
		if (apellido != null && !apellido.isBlank()) {
			this.apellido = apellido;
		}
		if (especialidad != null && !especialidad.isBlank()) {
			this.especialidad = especialidad;
		}
		if (email != null && !email.isBlank()) {
			this.email = email;
		}
	}

	/**
	 * muestra id del profesor
	 * 
	 * @return id del profesor
	 */
	public int getIdProfesor() {
		return idProfesor;
	}

	/**
	 * muestra nombre del profesor
	 * 
	 * @return id del profesor
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * muestra apellido del profesor
	 * 
	 * @return apellido del profesor
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * muestra especialidad del profesor
	 * 
	 * @return especialidad del profesor
	 */
	public String getEspecialidad() {
		return especialidad;
	}

	/**
	 * muestra email del profesor
	 * 
	 * @return email del profesor
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * sobreescritura del método equals. Dos objetos profesor serán iguales cuando
	 * tengan el mismo id.
	 * 
	 * @return true en caso de que ambos objetos sean iguales y false si no son
	 *         iguales
	 */
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		Profesor prof = (Profesor) obj;

		if (this.idProfesor == prof.idProfesor) {
			iguales = true;
		}

		return iguales;
	}

	/**
	 * sobreescritura del método string.
	 * 
	 * @return cadena con los datos del profesor
	 */
	@Override
	public String toString() {
		String cadena = this.idProfesor + " - " + this.nombre + " - " + this.apellido + " - " + this.especialidad
				+ " - " + this.email + "\n";

		return cadena;
	}

}
