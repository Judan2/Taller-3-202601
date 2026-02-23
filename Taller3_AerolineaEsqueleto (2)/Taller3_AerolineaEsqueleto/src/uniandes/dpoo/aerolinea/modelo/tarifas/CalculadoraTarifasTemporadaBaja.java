package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.cliente.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas
{
	protected static final int COSTO_POR_KM_NATURAL = 600;
    protected static final int COSTO_POR_KM_CORPORATIVO = 900;
    protected static final double DESCUENTO_PEQ = 0.02;
    protected static final double DESCUENTO_MEDIANAS = 0.1;
    protected static final double DESCUENTO_GRANDES = 0.2;
	@Override
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		// TODO Auto-generated method stub
		int distancia = calcularDistanciaVuelo( vuelo.getRuta( ) );
        if( cliente.getTipoCliente( ).equals( ClienteCorporativo.CORPORATIVO ) )
            return distancia * COSTO_POR_KM_CORPORATIVO;
        else
            return distancia * COSTO_POR_KM_NATURAL;
	}
	@Override
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		// TODO Auto-generated method stub
		if( !cliente.getTipoCliente( ).equals( ClienteCorporativo.CORPORATIVO ) )
            return 0.0;

        ClienteCorporativo corp = ( ClienteCorporativo ) cliente;
        int tamano = corp.getTamanoEmpresa( );

        if( tamano == ClienteCorporativo.PEQUENA )
            return DESCUENTO_PEQ;
        else if( tamano == ClienteCorporativo.MEDIANA )
            return DESCUENTO_MEDIANAS;
        else
            return DESCUENTO_GRANDES;
	}
    
}
