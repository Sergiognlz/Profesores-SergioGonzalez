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
	public ProfesorDAO(Connection conexion) {
		try {
			conexion = DriverManager.getConnection(Constantes.URL, Constantes.CONTRASEÑA, Constantes.CONTRASEÑA);
		} catch (SQLException e) {
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
		boolean exito = false;
		String sql = "INSERT INTO profesores (nombre, apellido, especialidad, email)" + "VALUES (?, ?, ?, ?)";

		PreparedStatement ps;
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
	 * resultados, luego crea objetos profesor con los datos y los añade a un conjunto de profesores
	 * 
	 * @return conjunto de profesores
	 */
	public Set listado() {
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

			profEnc = new Profesor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("especialidad"),
					rs.getString("email"));

		} catch (SQLException e) {

			System.out.println("Error al acceder a la base de datos");
		}

		return profEnc;
	}

}
