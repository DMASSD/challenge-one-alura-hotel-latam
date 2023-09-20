package com.hotelAlura.controller;

import java.sql.Connection;

import com.hotelAlura.dao.PreciosReservasDAO;
import com.hotelAlura.factory.ConnectionFactory;

public class PreciosReservasController {
	
	private PreciosReservasDAO preciosReservasDAO;
	
	public PreciosReservasController() {
		this.preciosReservasDAO  = new PreciosReservasDAO(
				new ConnectionFactory().recuperaConexion());
	}
    
    public double consultaPrecio() {
    	return this.preciosReservasDAO.consultaPrecio();
    }

}
