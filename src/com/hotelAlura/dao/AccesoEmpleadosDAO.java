package com.hotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.hotelAlura.factory.ConnectionFactory;
import com.hotelAlura.views.MenuUsuario;

public class AccesoEmpleadosDAO {
	
	final private Connection con;
	
	public AccesoEmpleadosDAO(Connection con) {
		this.con = con;
	}
	
	public boolean login(String usuario,String contrasena) {
		
		try(con) {
			
			final PreparedStatement statement = con.prepareStatement(
					"SELECT contrasena FROM acceso_empleados WHERE "
					+ "nombre_de_usuario = ?");
			
			try(statement){
				
				statement.setString(1, usuario);
				
				if (statement.execute()) {
					
					final ResultSet resultSet = statement.getResultSet();
					
					try(resultSet) {
						
						resultSet.next();
						
						if (contrasena.equals(resultSet.getString("contrasena"))) {
							return true;
				            
						}else {return false;}
						
					}
										
				}else {
					return false;
				}
				
			}
			
		} catch (Exception e) {return false;}
	}
	
	
}
