package com.hotelAlura.controller;

import java.util.List;

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
	
	public void setReservacionActual(Huespedes huesped) {
		this.huespedesDAO.setReservacionActual(huesped);
	}

	public List<Huespedes> listarTodo() {
		return this.huespedesDAO.listarTodo();
	}
	
	public List<Huespedes> busqueda(String nombre){
		return this.huespedesDAO.busqueda(nombre);
	}
	
	public void editar(Huespedes huesped) {
		this.huespedesDAO.editar(huesped);
	}

}
