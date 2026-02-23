package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Collection;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;

public class Vuelo {
	private Ruta ruta;
    private String fecha;
    private Avion avion;
    private Collection<Tiquete> tiquetes;
    
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		super();
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
		this.tiquetes = new ArrayList<>();
	}
	
	public Ruta getRuta() {
		return ruta;
	}
	public String getFecha() {
		return fecha;
	}
	public Avion getAvion() {
		return avion;
	}
	public Collection<Tiquete> getTiquetes() {
		return tiquetes;
	}
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
		    
		    // Verifica si hay espacio en el avión
		    if (tiquetes.size() + cantidad > avion.getCapacidad()) {
		        throw new VueloSobrevendidoException();// falta implementar las exception
		    }

		    int tarifa = calculadora.calcularTarifa(this, cliente);
		    
		    for (int i = 0; i < cantidad; i++) {
		    	String codigo = ruta.getCodigoRuta() + "-" + fecha + "-" + i;
	            Tiquete t = new Tiquete(codigo, this, cliente, tarifa);
	            tiquetes.add(t);
	            cliente.agregarTiquete(t);
		    }
		    
		    return tarifa * cantidad;
		}
    
    

}
