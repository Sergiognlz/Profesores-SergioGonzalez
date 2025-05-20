package crudinterfaz;

import java.util.Scanner;
import java.util.Set;

import dao.ProfesorDAO;
import entidades.Profesor;

public class PrincipalProfesor {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// opción del menú
		int opcion;
		// objeto profesor
		Profesor profesor;

		// conectamos con el dao
		ProfesorDAO pDAO = new ProfesorDAO();

		// do while
		do {
			// mostramos menu
			menu();
			// guardamos opción
			opcion = sc.nextInt();
			// limpiamos buffer
			sc.nextLine();

			switch (opcion) {
			// caso 1 insertar
			case 1 -> {
				// creamos objeto profesor llamando a la función del mismo nombre
				profesor = crearProfesor();

				// insertamos el profesor en la base de datos llamando a la función del dao
				pDAO.insertarProfesor(profesor);

			}
			case 2 -> {
				// mostramos por consola el cojunto pasandole el conjunto creado en la función
				// listado del DAO
				leerConjunto(pDAO.listado());

			}
			case 3 -> {
				// buscamos al profesor a través de su id pidiendolo con la función pedirID del
				// main y usamos la función buscarProfesor del dao. Guardamos el objeto que
				// devuelve en un objeto profesor
				profesor = pDAO.buscarProfesor(pedirID());
				//mostramos profesor por consola
				System.out.println(profesor);

			}
			}

			// repite mientras que la opción no sea 0
		} while (opcion != 0);

	}

	/**
	 * función que muestra menú
	 */
	public static void menu() {

		System.out.println("Bienvenido a la base de datos de Profesores");
		System.out.println();
		System.out.println("Menú:");
		System.out.println();
		System.out.println("1. Crear profesor");
		System.out.println("2. Listar todos los profesores");
		System.out.println("3. Buscar profesor por id");
		System.out.println("4. Modificar profesor");
		System.out.println("5. Eliminar profesor");
		System.out.println("0. Salir");
		System.out.println();
		System.out.println("Introduce la opción deseada");
		System.out.println();

	}

	/**
	 * función que pide datos y crea un profesor
	 * 
	 * @return objeto profesor
	 */
	public static Profesor crearProfesor() {

		Profesor obj;
		String nombre;
		String apellido;
		String especialidad;
		String email;

		System.out.println("Introduce el nombre del profesor");
		nombre = sc.nextLine();
		System.out.println("Introduce el apellido del profesor");
		apellido = sc.nextLine();
		System.out.println("Introduce la especialidad del profesor");
		especialidad = sc.nextLine();
		System.out.println("Introduce el email del profesor");
		email = sc.nextLine();

		return obj = new Profesor(nombre, apellido, especialidad, email);
	}

	/**
	 * función que pide id de profesor
	 * 
	 * @return id de profesor
	 */
	public static int pedirID() {
		int id;
		System.out.println("Introduce el id del profesor");
		id = sc.nextInt();
		return id;
	}

	public static void leerConjunto(Set<Profesor> conjunto) {

		for (Profesor obj : conjunto) {

			System.out.println(obj);
			System.out.println("-----------------------------------------------");
		}

	}
}
