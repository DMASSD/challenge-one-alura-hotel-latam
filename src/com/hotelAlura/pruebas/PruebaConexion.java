package com.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import com.hotelAlura.factory.ConnectionFactory;

public class PruebaConexion {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.recuperaConexion();

        System.out.println("Cerrando la conexión");

        con.close();	
        
     // Formato de dinero
        NumberFormat formatoDinero = NumberFormat.getCurrencyInstance();

        // Número formateado como dinero
        String numeroFormateado = "$ 1,234.56";
        
        String nformateado2 = numeroFormateado.replaceAll("\\s", "");
        
        System.out.println(numeroFormateado);
        
        System.out.println(nformateado2);

        // Desformatear el número
        Number numeroDesformateado = 0;
		try {
			numeroDesformateado = formatoDinero.parse(nformateado2);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        // Obtener el número como double
        double numero = numeroDesformateado.doubleValue();

        // Imprimir el número desformateado
        System.out.println("Número desformateado: " + numero);
		
	}
}
