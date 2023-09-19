package com.hotelAlura.modelo;

import java.util.Date;

public class Reservas {
	
	private Integer id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private Double valor;
	private String formato_de_pago;
	
	public Reservas(Date fecha_entrada, Date fecha_salida,
			Double valor, String formato_de_pago) {
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.formato_de_pago = formato_de_pago;
	}

	@Override
	public String toString() {
		return "Reservas [id=" + id + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", valor="
				+ valor + ", formato_de_pago=" + formato_de_pago + "]";
	}
	
	
	
	
	

}
