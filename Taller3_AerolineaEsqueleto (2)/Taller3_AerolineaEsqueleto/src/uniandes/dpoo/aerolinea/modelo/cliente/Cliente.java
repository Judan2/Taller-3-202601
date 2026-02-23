package uniandes.dpoo.aerolinea.modelo.cliente;
import java.util.*;


public abstract class Cliente 
{
	private ArrayList<Tiquete> tiquetesSinUsar;//lista de tiquetes que no se han usado 
	private ArrayList<Tiquete> tiquetesUsados;//lista de tiqutes que se usaron


	public Cliente() {
		super();
		tiquetesSinUsar = new ArrayList<>();
		tiquetesUsados = new ArrayList<>();
	}
	//public Cliente() {}
	public abstract String getTipoCliente();
	public abstract String getIdentificador();
	
	public abstract void agregarTiquete(Tiquete tiquete);
	public abstract int calcularValorTiquetes();
	public abstract void usarTiquetes(Vuelo vuelo);
	
	public ArrayList<Tiquete> getTiquetesSinUsar() {
		return tiquetesSinUsar;
	}
	public ArrayList<Tiquete> getTiquetesUsados() {
		return tiquetesUsados;
	}

	

}
