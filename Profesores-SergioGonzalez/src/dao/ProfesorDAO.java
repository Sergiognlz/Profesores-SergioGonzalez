package dao;

import java.sql.Connection;

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
	 * @param conexion con bbdd
	 */
	public ProfesorDAO(Connection conexion) {
		
		this.conexion = conexion;
	}

	/**
	 * muestra la conexión
	 * @return conexión
	 */
	public Connection getConexion() {
		return conexion;
	}
	
	
	
	
	
}
