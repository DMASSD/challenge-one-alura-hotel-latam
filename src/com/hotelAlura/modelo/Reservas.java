package com.hotelAlura.modelo;

import java.util.Date;

public class Reservas {
	
	private Integer id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private Double valor;
	private String formato_de_pago;
	private Integer id_huesped;
	
	
	public Reservas(Date fecha_entrada, Date fecha_salida,
			Double valor, String formato_de_pago) {
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.formato_de_pago = formato_de_pago;
	}
		
	public Reservas(Integer id, Date fecha_entrada, Date fecha_salida, Double valor, String formato_de_pago,
			Integer id_huesped) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.formato_de_pago = formato_de_pago;
		this.id_huesped = id_huesped;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	
	public java.sql.Date getFecha_entradaSQL() {
		return new java.sql.Date(fecha_entrada.getTime());
	}

	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}
	
	public java.sql.Date getFecha_salidaSQL() {
		return new java.sql.Date(fecha_salida.getTime());
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getFormato_de_pago() {
		return formato_de_pago;
	}

	public void setFormato_de_pago(String formato_de_pago) {
		this.formato_de_pago = formato_de_pago;
	}

	public Integer getId_huesped() {
		return id_huesped;
	}

	public void setId_huesped(Integer id_huesped) {
		this.id_huesped = id_huesped;
	}

	@Override
	public String toString() {
		return "Reservas [id=" + id + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", valor="
				+ valor + ", formato_de_pago=" + formato_de_pago + ", id_huesped=" + id_huesped + "]";
	}

	
	
	
	
	
	

}
