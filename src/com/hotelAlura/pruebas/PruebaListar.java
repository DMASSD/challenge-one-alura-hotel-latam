package com.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hotelAlura.factory.ConnectionFactory;
import com.hotelAlura.modelo.Reservas;

public class PruebaListar {

	public static void main(String[] args) {
		
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		List<Reservas> resultado = new ArrayList<>();
		
		try(con) {
			
			final PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM reservas"
					);
			
			try(statement) {
				
				statement.execute();
				
				ResultSet resultset = statement.getResultSet();
				
				try(resultset) {
										
					while (resultset.next()) {
						
						Reservas reserva = new Reservas(
								resultset.getInt("id"),
								resultset.getDate("fecha_entrada"),
								resultset.getDate("fecha_salida"),
								resultset.getDouble("valor"),
								resultset.getString("formato_de_pago"),
								resultset.getInt("id_huesped")
								);						
						
						resultado.add(reserva);
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
