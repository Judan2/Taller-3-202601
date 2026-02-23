package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.Vuelo;

public abstract class CalculadoraTarifas 
{
	public static final double IMPUESTO = 0.28;
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
        int costoBase = calcularCostoBase(vuelo, cliente);
        double descuento = calcularPorcentajeDescuento(cliente);
        int costoConDescuento = (int)(costoBase - (costoBase * descuento));
        return costoConDescuento + calcularValorImpuestos(costoConDescuento);
    }
	
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	protected int calcularDistanciaVuelo(Ruta ruta) {
        return ruta.getDistancia();
    }
	
	protected int calcularValorImpuestos(int costoBase) {
        return (int)(costoBase * IMPUESTO);
        
	}
	

	
	
	
	

}
