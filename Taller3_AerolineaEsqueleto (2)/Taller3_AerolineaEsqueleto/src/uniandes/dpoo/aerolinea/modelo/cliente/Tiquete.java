package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;

public class Tiquete 
{
	private String codigo;
	private int tarifa;
	private boolean usado;
	private Vuelo vuelo;	
	private Cliente cliente;
	public Tiquete(String codigo, int tarifa, Vuelo vuelo, Cliente clienteComprador) {
		super();
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.usado = false;
		this.vuelo = vuelo;
		this.cliente = clienteComprador;
	}
	//getters and setters
	public String getCodigo() {
		return codigo;
	}
	public int getTarifa() {
		return tarifa;
	}
	public boolean esUsado() {
		return usado;
	}
	public void marcarComoUsado(){
		this.usado= true;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	
	

}
