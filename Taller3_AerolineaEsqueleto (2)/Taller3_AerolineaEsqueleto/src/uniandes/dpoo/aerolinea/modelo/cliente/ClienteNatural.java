package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente
{
	public static final String NATURAL = "Natural";
    private String nombre;


	public ClienteNatural(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public String getTipoCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdentificador() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public void agregarTiquete(Tiquete tiquete) 
	{
		// TODO Auto-generated method stub
	       if( !tiquete.esUsado( ) )
	            this.getTiquetesSinUsar( ).add( tiquete );
	        else
	            this.getTiquetesUsados( ).add( tiquete );
		
	}

	@Override
	public int calcularValorTiquetes()
	{
		// TODO Auto-generated method stub
		int total = 0;
        for( Tiquete t : this.getTiquetesSinUsar( ) )
            total += t.getTarifa( );
        return total;
	}

	@Override
	public void usarTiquetes(Vuelo vuelo) 
	{
		// TODO Auto-generated method stub
		
		for( Tiquete t : this.getTiquetesSinUsar( ) )
        {
            if( t.getVuelo( ).equals( vuelo ) )
            {
                t.marcarComoUsado( );
                this.getTiquetesUsados( ).add( t );
            }
        }
        this.getTiquetesSinUsar( ).removeIf( t -> t.esUsado( ) );
		
	}

}
