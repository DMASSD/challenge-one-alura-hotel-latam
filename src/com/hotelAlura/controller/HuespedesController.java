package com.hotelAlura.controller;

import com.hotelAlura.dao.HuespedesDAO;
import com.hotelAlura.factory.ConnectionFactory;
import com.hotelAlura.modelo.Huespedes;

public class HuespedesController {
	
	private HuespedesDAO huespedesDAO;
	
	public HuespedesController() {
		this.huespedesDAO = new HuespedesDAO(new ConnectionFactory().
				recuperaConexion());
	}
	
	public Integer guardar(Huespedes huesped){
		return this.huespedesDAO.guardar(huesped);
	}

}
