package com.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.hotelAlura.factory.ConnectionFactory;

public class PruebaPoolDeConexiones {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		for (int i = 0; i < 20; i++) {
			Connection recuperaConexion = connectionFactory.recuperaConexion();
			
			System.out.println("Se abre la conexion " + (i+1));
		}
		
		

	}

}
