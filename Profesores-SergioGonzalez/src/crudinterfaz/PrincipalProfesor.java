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
		// id profesor
		int id;
		// variable nuevo valor
		String nValor;
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
				if (pDAO.insertarProfesor(profesor)) {
					System.out.println("El profesor se ha añadido con éxito");
				} else {
					System.out.println("No se ha podido añadir");
				}

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
				
				if(profesor!=null) {
				// mostramos profesor por consola
				System.out.println(profesor);
				}else {
					System.out.println("El id de profesor no se corresponde con ninguno existente");
				}

			}
			// caso 4 modificar
			case 4 -> {
				// pedimos id y lo guardamos en variable id
				id = pedirID();
				// buscamos al profesor a través de su id pidiendolo con la función pedirID del
				// main y usamos la función buscarProfesor del dao. Guardamos el objeto que
				// devuelve en un objeto profesor

				profesor = pDAO.buscarProfesor(id);
				// comprobamos que exista el profesor
				if (profesor != null) {
					// mostramos minimenú
					miniMenu();
					// guardamos opción
					opcion = sc.nextInt();
					// limpiamos buffer
					sc.nextLine();
					// switch case con las operaciones de las opciones
					switch (opcion) {
					// caso 1 especialidad
					case 1 -> {
						// pedimos valor
						System.out.println("Introduce el nuevo valor");
						// guardamos valor
						nValor = sc.nextLine();
						// modificamos valor llamamando a la función del dao y le pasamos los valores.
						// Lo metemos en un if para que si modifica muestre un mensaje de éxito y si no,
						// uno de fracaso
						if (pDAO.modificarEspecialidad(nValor, id)) {
							System.out.println("El valor se ha modificado correctamente");
						} else {
							System.out.println("No se ha podido modificar");
						}
					}
					case 2 -> {
						// pedimos valor
						System.out.println("Introduce el nuevo valor");
						// guardamos valor
						nValor = sc.nextLine();
						// modificamos valor llamamando a la función del dao y le pasamos los valores.
						// Lo metemos en un if para que si modifica muestre un mensaje de éxito y si no,
						// uno de fracaso
						if (pDAO.modificarEmail(nValor, id)) {
							System.out.println("El valor se ha modificado correctamente");
						} else {
							System.out.println("No se ha podido modificar");
						}
					}
					}

					// si no existe
				} else {
					// mensaje de que no existe
					System.out.println("El id de profesor no se corresponde con ninguno existente");
				}
			}
			// caso 5 eliminar
			case 5 -> {
				// pedimos id y lo guardamos en variable id
				id = pedirID();
				// llamamos a la función eliminar del dao y la metemos en un if para que muestre
				// un mensaje afirmativo cuando se realice con éxito la operación y uno negativo
				// cuando no
				if (pDAO.EliminarProfesor(id)) {
					System.out.println("Se ha eliminado correctamente");
				} else {
					System.out.println("No se ha podido realizar la operación");
				}

			}
			//caso 0 salir
			case 0->{
				System.out.println("Saliendo del programa");
			}
			//default
			default ->{
				System.out.println("La opción elegida no existe");
			}
			}

			// repite mientras que la opción no sea 0
		} while (opcion != 0);
		// cerramos escaner
		sc.close();
	}

	/**
	 * función que muestra menú
	 */
	public static void menu() {
		System.out.println();
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
	 * función que muestra un mini-menú
	 */
	public static void miniMenu() {
		System.out.println();
		System.out.println("Indique el valor que desea modificar:");
		System.out.println("1. Especialidad");
		System.out.println("2. Email");
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

	/**
	 * función que lee un conjunto y muestra los objetos que lo componen por pantalla
	 * @param conjunto
	 */
	public static void leerConjunto(Set<Profesor> conjunto) {

		for (Profesor obj : conjunto) {

			System.out.println(obj);
			System.out.println("-----------------------------------------------");
		}

	}

}
