package com.hotelAlura.controller;

import java.util.List;

import com.hotelAlura.dao.ReservasDAO;
import com.hotelAlura.factory.ConnectionFactory;
import com.hotelAlura.modelo.Huespedes;
import com.hotelAlura.modelo.Reservas;

public class ReservasController {
	
	private ReservasDAO reservasDAO;
	
	public ReservasController() {
		this.reservasDAO = new ReservasDAO(new ConnectionFactory().
				recuperaConexion());
		
	}
	
	public Integer guardar(Reservas reserva, Huespedes huesped) {
		return reservasDAO.guardar(reserva, huesped);
	}
	
	public List<Reservas> listarTodo(){
		return reservasDAO.listarTodo();
	}
	
	public List<Reservas> busquedaPorIdHuesped(List<Huespedes> huespedes){
		return reservasDAO.busquedaPorIdHuesped(huespedes);
	}
	
	public void editar(Reservas reserva) {
		reservasDAO.editar(reserva);
	}
	
	
}
