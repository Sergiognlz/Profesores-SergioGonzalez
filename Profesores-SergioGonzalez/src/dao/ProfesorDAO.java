package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entidades.Profesor;
import utiles.Constantes;

/**
 * clase objeto acceso a datos de profesor
 */
public class ProfesorDAO {

	/**
	 * atributo de clase connection
	 */
	private Connection conexion;

	/**
	 * constructor DAO
	 * 
	 * @param conexion con bbdd
	 */
	public ProfesorDAO() {
		try {
			//conexión con la base de datos
			conexion = DriverManager.getConnection(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
			//captura de excepciones
		} catch (SQLException e) {
			//mensaje error
			System.out.println("Error al crear la conexión con la base de datos: " + e.getMessage());
		}
	}

	/**
	 * muestra la conexión
	 * 
	 * @return conexión
	 */
	public Connection getConexion() {
		return conexion;
	}

	/**
	 * función que inserta un profesor en la tabla profesores
	 * 
	 * @param objeto profesor
	 * @return un booleano indicando true si tiene exito en insertar o false si no;
	 */
	public boolean insertarProfesor(Profesor prof) {
		//booleana exito a false
		boolean exito = false;
		//sentencia sql
		String sql = "INSERT INTO profesores (nombre, apellido, especialidad, email)" + "VALUES (?, ?, ?, ?)";
		//objeto para realizar la conexión
		PreparedStatement ps;
		//try catch para capturar
		try {
			ps = conexion.prepareStatement(sql);
			ps.setString(1, prof.getNombre());
			ps.setString(2, prof.getApellido());
			ps.setString(3, prof.getEspecialidad());
			ps.setString(4, prof.getEmail());

			ps.executeUpdate();
			exito = true;

		} catch (SQLException e) {
			System.out.println("Error al insertar el estudiante: " + e.getMessage());
		}
		return exito;
	}

	/**
	 * realiza un select de la tabla profesores y guarda la consulta en una lista de
	 * resultados, luego crea objetos profesor con los datos y los añade a un
	 * conjunto de profesores
	 * 
	 * @return conjunto de profesores
	 */
	public Set<Profesor> listado() {
		Set<Profesor> conjunto = new HashSet<>();
		String select = "select * from profesores";
		ResultSet rs = null;
		PreparedStatement pst;
		try {
			pst = conexion.prepareStatement(select);

			rs = pst.executeQuery(select);

			while (rs.next()) {

				conjunto.add(new Profesor(rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("especialidad"), rs.getString("email")));

			}

		} catch (SQLException e) {

			System.out.println("Error al acceder a la base de datos");
		}

		return conjunto;
	}

	/**
	 * Busca profesor por id y devuelve un objeto profesor igual al id
	 * 
	 * @param id del profesor buscado
	 * @return objeto profesor encontrado
	 */
	public Profesor buscarProfesor(int id) {
		Profesor profEnc = null;
		String select = "select * from profesores WHERE id_profesor=?";
		ResultSet rs = null;
		PreparedStatement pst;
		try {
			pst = conexion.prepareStatement(select);
			pst.setInt(1, id);

			rs = pst.executeQuery(select);

			if (rs.next()) {
				profEnc = new Profesor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("especialidad"),
						rs.getString("email"));
			}

		} catch (SQLException e) {

			System.out.println("Error con la base de datos " + e.getMessage());
		}

		return profEnc;
	}

	/**
	 * Modifica la especialidad de un profesor
	 * 
	 * @param nueva especialidad
	 * @param id    del profesor
	 * @return true si modifica algo o false si no
	 */
	public boolean modificarEspecialidad(String nEsp, int id) {
		boolean exito = false;

		// hacemos update restando 4 a la nota
		String update = "UPDATE Profesores SET especialidad=? WHERE id_profesor=?";

		// objeto PreparedStatment y le pasamos el update
		PreparedStatement pst;
		try {
			pst = conexion.prepareStatement(update);

			// aplicamos donde el tipo de la evaluación sea Examen
			pst.setString(1, nEsp);
			pst.setInt(1, id);
			// guardamos cuantas filas se han modificado
			int rs = pst.executeUpdate();

			if (rs > 0) {
				exito = true;
			}
		} catch (SQLException e) {
			System.out.println("Error con la base de datos " + e.getMessage());
		}

		return exito;
	}

	/**
	 * Modifica el email de un profesor
	 * 
	 * @param nueva email
	 * @param id    del profesor
	 * @return true si modifica algo o false si no
	 */
	public boolean modificarEmail(String nEmail, int id) {
		boolean exito = false;

		// hacemos update restando 4 a la nota
		String update = "UPDATE Profesores SET email=? WHERE id_profesor=?";

		// objeto PreparedStatment y le pasamos el update
		PreparedStatement pst;
		try {
			pst = conexion.prepareStatement(update);

			// aplicamos donde el tipo de la evaluación sea Examen
			pst.setString(1, nEmail);
			pst.setInt(1, id);
			// guardamos cuantas filas se han modificado
			int rs = pst.executeUpdate();

			if (rs > 0) {
				exito = true;
			}
		} catch (SQLException e) {
			System.out.println("Error con la base de datos " + e.getMessage());
		}

		return exito;
	}

	/**
	 * Elimina un profesor de la base de datos
	 * @param id del profesor a eliminar
	 * @return true si lo elimina, false si no
	 */
	public boolean EliminarProfesor(int id) {
		boolean exito = false;

		String delete = "delete from profesores where id=?";
		try {
			// sentencia paremetrizada creando objeto de tipo PreparedStantement
			PreparedStatement pst = conexion.prepareStatement(delete);
			// le indicamos que le primer interrogante que asignamos(el 1) y que el valor
			// que le corresponde es 2
			pst.setInt(1, id);

			// guardamos el resultado de la consulta
			int rs = pst.executeUpdate();

			if (rs > 0) {
				exito = true;
			}
		} catch (SQLException e) {
			System.out.println("Error con la base de datos " + e.getMessage());
		}

		return exito;
	}
}
