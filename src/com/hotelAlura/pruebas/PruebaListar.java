package com.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hotelAlura.factory.ConnectionFactory;
import com.hotelAlura.modelo.Huespedes;
import com.hotelAlura.modelo.Reservas;

public class PruebaListar {

	public static void main(String[] args) {
		
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		List<Huespedes> resultado = new ArrayList<>();
		
		try(con) {
			
			final PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM huespedes"
					);
			
			try(statement) {
				
				statement.execute();
				
				ResultSet resultset = statement.getResultSet();
				
				try(resultset) {
										
					while (resultset.next()) {
						
						Huespedes huesped = new Huespedes(
								resultset.getInt("id"),
								resultset.getString("nombre"),
								resultset.getString("apellido"),
								resultset.getDate("fecha_de_nacimiento"),
								resultset.getString("nacionalidad"),
								resultset.getString("telefono"),
								resultset.getInt("reserva_actual")
								);						
						
						resultado.add(huesped);
					}
				}
				
			} 
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
            		null,
            		"Error al ingresar reserva a la base de datos, contactar con soporte",
            		"Advertencia",
            		JOptionPane.WARNING_MESSAGE);			
			throw new RuntimeException(e);
		}
		
		resultado.forEach(elemento -> System.out.println(elemento.toString()));
	}

}
