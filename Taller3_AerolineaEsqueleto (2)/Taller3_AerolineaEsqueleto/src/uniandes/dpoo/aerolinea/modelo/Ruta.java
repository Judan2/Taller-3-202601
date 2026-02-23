package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    // TODO completar
	private String horaSalida;
	private String horaLlegada;
	private String codigoRuta;
	private Aeropuerto destino;
	private Aeropuerto origen;
	
    public Ruta(String horaSalida, String horaLlegada, String codigoRuta, Aeropuerto destino, Aeropuerto origen) {
		super();
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.codigoRuta = codigoRuta;
		this.destino = destino;
		this.origen = origen;
	}
    

	public String getHoraLlegada() {
		return horaLlegada;
	}


	public String getCodigoRuta() {
		return codigoRuta;
	}


	public Aeropuerto getDestino() {
		return destino;
	}


	public Aeropuerto getOrigen() {
		return origen;
	}
	
	public String getHoraSalida() {
		return horaSalida;
	}


	/**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return (int) minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return (int) horas;
    }
    // falta gett duracion como lo hagoo 
    public int getDuracion() 
    {
    	//queremos hallar la duracion para esto se restaran las hora salida con las horas de llegadas 
    	// pero primero tendria que hallar hora salida
    	int hSalida = Ruta.getHoras(horaSalida)*60 + Ruta.getMinutos(horaSalida);
    	int hLlegada = Ruta.getHoras(horaLlegada)*60 + Ruta.getMinutos(horaLlegada);
    	
    	int operacion = hLlegada - hSalida;
    	return Math.abs(operacion);// lo pongo valor absoluto por que cuando yo pongo un vuelo sale a las 20
    	//horas y llega a las tres el resultado seria negativo por lo tanto no tendria sentido 
    	
    }


	

    
}
