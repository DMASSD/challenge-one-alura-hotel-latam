package com.hotelAlura.modelo;

import java.util.Date;

public class Huespedes {
	
	private int id;
	private String nombre;
	private String apellido;
	private Date fecha_de_nacimiento; 
	private String nacionalidad; 
	private String telefono;
	private Integer reservacion_actual;
	
	
	public Huespedes(String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}


	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}
	
	
	public java.sql.Date getFecha_de_nacimientoSQL() {
		return new java.sql.Date(fecha_de_nacimiento.getTime());
	}


	public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Integer getReservacion_actual() {
		return reservacion_actual;
	}


	public void setReservacion_actual(Integer reservacion_actual) {
		this.reservacion_actual = reservacion_actual;
	}
	
	
	
	
	

}
