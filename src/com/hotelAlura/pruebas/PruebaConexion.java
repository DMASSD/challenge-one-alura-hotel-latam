package com.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.hotelAlura.factory.ConnectionFactory;

public class PruebaConexion {

	public static void main(String[] args) throws SQLException {
		
		Connection con = new ConnectionFactory().recuperaConexion();
		
		System.out.println("Cerrando la conexion");
		
		con.close();
		
	}

}
