package com.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotelAlura.factory.ConnectionFactory;

public class PruebaPreciosReservas {
	
	public static void main(String[] args) throws SQLException {
		
		final Connection con = new ConnectionFactory().
				recuperaConexion();
		
		double resultado = 0;
		
		try(con) {
    		
    		final PreparedStatement statement = con.prepareStatement(
    				"SELECT precio FROM precios_reservas WHERE id = 1");
    		
    		try(statement) {
    			        		
    			statement.execute();
        			
        		final ResultSet resulset = statement.getResultSet();
        			
        		try(resulset) {
        			
        			resulset.next();
        			
        			resultado =  Double.parseDouble(resulset.getString("precio"));		
				}
						
			}

		} catch (SQLException e) {
			
		}
		
		System.out.println(resultado);
		
	}

}
