package com.hotelAlura.controller;

import com.hotelAlura.dao.AccesoEmpleadosDAO;
import com.hotelAlura.factory.ConnectionFactory;

public class AccesoEmpleadosController {
	
	private AccesoEmpleadosDAO accesoEmpleadosDAO;
	
	public AccesoEmpleadosController() {
		accesoEmpleadosDAO = new AccesoEmpleadosDAO(new ConnectionFactory().
				recuperaConexion());
	}
	
	public boolean login(String usuario,String contrasena) {
		return accesoEmpleadosDAO.login(usuario, contrasena);
	}
}
