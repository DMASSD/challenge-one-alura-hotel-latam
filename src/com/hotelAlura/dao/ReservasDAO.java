package com.hotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hotelAlura.modelo.Huespedes;
import com.hotelAlura.modelo.Reservas;

public class ReservasDAO {
	
	final private Connection con;
	
	public ReservasDAO(Connection con) {
		this.con = con;
	}
	
	public Integer guardar(Reservas reserva, Huespedes huesped){
		
		try(con) {
			
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas (fecha_entrada, fecha_salida, valor, formato_de_pago,id_huesped) "
					+ "VALUES(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS); 
			
			try(statement) {
				
				statement.setDate(1, reserva.getFecha_entradaSQL());
				statement.setDate(2, reserva.getFecha_salidaSQL());
				statement.setDouble(3,reserva.getValor());
				statement.setString(4, reserva.getFormato_de_pago());	
				statement.setInt(5, huesped.getId());	
				
				statement.execute();
				
				try (ResultSet resulset = statement.getGeneratedKeys()) {
					resulset.next();
					return resulset.getInt(1);
			    
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
		
	}
	
	public List<Reservas> listarTodo() {
		
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
		
		return resultado;
		
	}

}
