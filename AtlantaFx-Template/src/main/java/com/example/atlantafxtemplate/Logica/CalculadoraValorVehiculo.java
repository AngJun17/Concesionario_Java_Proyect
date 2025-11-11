package com.example.atlantafxtemplate.Logica;
import java.time.Year;

public class CalculadoraValorVehiculo {

    /**
     * Calcula el valor estimado del vehículo basado en su año y estado.
     * @param anio Año del vehículo
     * @param estado Estado del vehículo ("Como nuevo", "Usado", etc.)
     * @param valorOriginal Precio original del vehículo
     * @return Valor ajustado
     */

    public static double calcularPrecioFinal(int anio, String estado, double valorOriginal){
        double precio = valorOriginal;
        int anioActual = Year.now().getValue();
        int antiguedad = anioActual - anio;

        double descuentoEstado = switch (estado.toLowerCase()){
            case "como nuevo" -> 0.05;
            case "usado" -> 0.010;
            case "con desperfectos esteticos" -> 0.25;
            case "necesita repareciones considerables" -> 0.40;
            default -> 0;
        };

        double descuentoAntiguedad;

        if (antiguedad >= 1 && antiguedad <= 5){
            descuentoAntiguedad = 0.10;
        }else if (antiguedad >= 6 && antiguedad <= 10){
            descuentoAntiguedad = 0.20;
        }else if (antiguedad >= 11 && antiguedad <= 20){
            descuentoAntiguedad = 0.50;
        }else if (antiguedad >  20){
            return -1;
        }else{
            descuentoAntiguedad = 0;
        }

        precio -= (precio * descuentoEstado);
        precio -= (precio * descuentoAntiguedad);

        return Math.max(precio, 0);

    }
}
