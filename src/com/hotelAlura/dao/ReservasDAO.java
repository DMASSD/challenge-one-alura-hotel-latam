package com.hotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
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
	
	public List<Reservas> busquedaPorIdHuesped(List<Huespedes> huespedes) {
				
		List<Reservas> resultado = new ArrayList<>();
		
		try(con) {
			
			final PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM reservas WHERE id_huesped IN ("+
						    String.join(",", Collections.nCopies(huespedes.size(), "?")) + ")"
					);
			
			try(statement) {
				
				for (int i = 0; i < huespedes.size(); i++) {
					statement.setInt(i + 1, huespedes.get(i).getId());
			    }
								
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
            		"Usuario no ha creado ninguna reserva",
            		"Advertencia",
            		JOptionPane.WARNING_MESSAGE);			
			throw new RuntimeException(e);
		}
		
		return resultado;
		
	}

	public void editar(Reservas reserva){
			
		try(con) {
			
			final PreparedStatement statement = con.prepareStatement(
					 "UPDATE reservas "
					+"SET fecha_entrada = ?, fecha_salida = ?, "
					+"valor = ?, formato_de_pago = ? "
					+"WHERE id = ?"); 
			
			try(statement) {
				
				statement.setDate(1, reserva.getFecha_entradaSQL());
				statement.setDate(2, reserva.getFecha_salidaSQL());
				statement.setDouble(3,reserva.getValor());
				statement.setString(4, reserva.getFormato_de_pago());	
				statement.setInt(5, reserva.getId());	
				
				statement.execute();
							    
				}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
            		null,
            		"Error al editar la reserva en la base de datos, contactar con soporte",
            		"Advertencia",
            		JOptionPane.WARNING_MESSAGE);			
			throw new RuntimeException(e);
		}
		
	}
	
	public void eliminar(List<Integer> idList){
		
		String finalQuery = "";
		
		if (!idList.isEmpty()) {
		    StringBuilder queryBuilder = new StringBuilder("DELETE FROM reservas WHERE 1=1 AND ");
		    
		    idList.forEach(id -> queryBuilder.append("id = ").append(id).append(" OR "));
		    
		    queryBuilder.setLength(queryBuilder.length() - 4);
		    
		    finalQuery = queryBuilder.toString();

		} else {
			JOptionPane.showMessageDialog(
            		null,
            		"No se selecciono ninguna reserva a eliminar",
            		"Advertencia",
            		JOptionPane.WARNING_MESSAGE);
		}

		
		try(con){
						
			final PreparedStatement statement = con.prepareStatement(finalQuery);
			
			try(statement){
				
				statement.execute();
				
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(
            		null,
            		"Error al eliminar la reserva de la base de datos, contactar con soporte",
            		"Advertencia",
            		JOptionPane.WARNING_MESSAGE);			
			throw new RuntimeException(e);
		}
	}
	
}
