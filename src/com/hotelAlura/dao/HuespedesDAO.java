package com.hotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hotelAlura.modelo.Huespedes;
import com.hotelAlura.modelo.Reservas;

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
	
	public void setReservacionActual(Huespedes huesped) {
		
		try(this.con) {
			
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE huespedes"
					+ " SET reserva_actual = ?"
					+ " WHERE id = ?");
			
			try(statement) {
				
				statement.setInt(1, huesped.getReservacion_actual());					
				statement.setInt(2, huesped.getId());
				
				statement.execute();
								
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
            		null,
            		"Error al asignar la reserva al huesped en la base de datos, contactar con soporte",
            		"Advertencia",
            		JOptionPane.WARNING_MESSAGE);
			throw new RuntimeException(e);
		}
		
	}

	public List<Huespedes> listarTodo() {
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
		
		return resultado;
	}

}
