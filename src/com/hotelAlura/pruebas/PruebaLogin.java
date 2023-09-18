package com.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.hotelAlura.factory.ConnectionFactory;

public class PruebaLogin {

	public static void main(String[] args) throws SQLException {
			
		String Usuario = JOptionPane.showInputDialog("Ingresa nombre de usuario:");
		
		String pass = JOptionPane.showInputDialog("Ingresa la contraseña:");

		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try(con) {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT contrasena FROM acceso_empleados WHERE"
					+ " nombre_de_usuario = ?");
			
			try(statement) {
				statement.setString(1, Usuario);
				
				boolean result = statement.execute();
				
				if (result) {
					
					ResultSet resultSet = statement.getResultSet();
					
					if (resultSet.next() && pass.equals(resultSet.getString("contrasena"))) {
						System.out.println("Bienvenido");

					}else {
						System.out.println("Usuario o contraseña invalidos");
					}
					
					resultSet.close();
				
				}else {
					System.out.println("La consulta no arrojo resultados");
				}
			}
		}		
		
	}

}

