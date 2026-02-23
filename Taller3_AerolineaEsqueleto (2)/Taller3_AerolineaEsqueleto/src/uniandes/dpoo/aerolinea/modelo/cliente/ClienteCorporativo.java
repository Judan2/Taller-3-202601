package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas
 */
public class ClienteCorporativo extends Cliente
{
    // TODO completar
	public static final String CORPORATIVO= "Corporativo";
	public static final int GRANDE= 1;
	public static final int MEDIANA=2;
	public static final int PEQUENA=3;
	private String nombreEmpresa;
	private int tamanoEmpresa;

    public ClienteCorporativo(String nombreEmpresa, int tamanoEmpresa) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.tamanoEmpresa = tamanoEmpresa;
		/*
		this.CORPORATIVO = "Corporativo";
		this.GRANDE = 1;
		this.MEDIANA=2;
		this.PEQUENA=3;
		*/
		
	}
    

	public String getCORPORATIVO() {
		return CORPORATIVO;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public int getTamanoEmpresa() {
		return tamanoEmpresa;
	}
	
	
	
	

	/**
     * Crea un nuevo objeto de tipo a partir de un objeto JSON.
     * 
     * El objeto JSON debe tener dos atributos: nombreEmpresa (una cadena) y tamanoEmpresa (un número).
     * @param cliente El objeto JSON que contiene la información
     * @return El nuevo objeto inicializado con la información
     */
    public static ClienteCorporativo cargarDesdeJSON( JSONObject cliente )
    {
        String nombreEmpresa = cliente.getString( "nombreEmpresa" );
        int tam = cliente.getInt( "tamanoEmpresa" );
        return new ClienteCorporativo( nombreEmpresa, tam );
    }

    /**
     * Salva este objeto de tipo ClienteCorporativo dentro de un objeto JSONObject para que ese objeto se almacene en un archivo
     * @return El objeto JSON con toda la información del cliente corporativo
     */
    public JSONObject salvarEnJSON( )
    {
        JSONObject jobject = new JSONObject( );
        jobject.put( "nombreEmpresa", this.nombreEmpresa );
        jobject.put( "tamanoEmpresa", this.tamanoEmpresa );
        jobject.put( "tipo", CORPORATIVO );
        return jobject;
    }


	@Override
	public String getTipoCliente() {
		// TODO Auto-generated method stub
		return CORPORATIVO;
	}


	@Override
	public String getIndentificador() {
		// TODO Auto-generated method stub
		return nombreEmpresa;
	}


	@Override
	public void agregarTiquete(Tiquete tiquete) {
		// TODO Auto-generated method stub
		if (!tiquete.esUsado()) {
			this.getTiquetesSinUsar().add(tiquete);// que estoy haciendo aca?
												//agrega los tiquetes que no estan usados
		}else {
			this.getTiquetesUsados().add(tiquete);//los agrega a los tiquetes que estan usados
		}
	}
	

	@Override
	public int calcularValorTiquetes() {
		// TODO Auto-generated method stub
		int total = 0;
	    
	    for (Tiquete t : this.getTiquetesSinUsar()) {
	        total += t.getTarifa();
	    }
	    for (Tiquete t : this.getTiquetesUsados()) {
	        total += t.getTarifa();
	    }
	    
	    return total;//no estoy seguro de este 
	}


	@Override
	public void usarTiquetes(Vuelo vuelo) {
		// TODO Auto-generated method stub
		for (Tiquete t : this.getTiquetesSinUsar()) {
	        t.marcarComoUsado();
	        this.getTiquetesUsados().add(t);
	    }
	    this.getTiquetesSinUsar().clear();
		
	}
}
