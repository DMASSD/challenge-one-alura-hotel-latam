package com.hotelAlura.controller;

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
	
	
	
}
