package com.hotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.hotelAlura.modelo.Huespedes;

public class HuespedesDAO {
	
	final private Connection con;
	
	public HuespedesDAO(Connection con){
		this.con = con;
	}
	
	public Integer guardar(Huespedes huesped) {
		
		try(this.con) {
			
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes(nombre,apellido,fecha_de_nacimiento,nacionalidad,telefono) "
					+ "VALUES(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			try(statement) {
				
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3,huesped.getFecha_de_nacimientoSQL());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());						
				
				statement.execute();
				
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					generatedKeys.next();
			        return generatedKeys.getInt(1);
	
			    }
				
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
            		null,
            		"Error al ingresar huesped a la base de datos, contactar con soporte",
            		"Advertencia",
            		JOptionPane.WARNING_MESSAGE);
			throw new RuntimeException(e);
		}
		
	}

}
