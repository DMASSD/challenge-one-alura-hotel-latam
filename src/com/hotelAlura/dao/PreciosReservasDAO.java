package com.hotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class PreciosReservasDAO {
	
	final private Connection con;
	
	public PreciosReservasDAO(Connection con) {
		this.con = con;
	}
	
	public double consultaPrecio() {
				
		try(this.con) {
    		
    		final PreparedStatement statement = con.prepareStatement(
    				"SELECT precio FROM precios_reservas WHERE id = 1");
    		
    		try(statement) {
    			        		
    			statement.execute();
        			
        		final ResultSet resulset = statement.getResultSet();
        			
        		try(resulset) {
        			
        			resulset.next();
        			
        			return Double.parseDouble(resulset.getString("precio"));		
				}
						
			}

		} catch (SQLException e) {
            JOptionPane.showMessageDialog(
            		null,
            		"Error al revisar la base de dato, contactar con soporte",
            		"Advertencia",
            		JOptionPane.WARNING_MESSAGE);
			throw new RuntimeException(e);
		}
				
	}

}
