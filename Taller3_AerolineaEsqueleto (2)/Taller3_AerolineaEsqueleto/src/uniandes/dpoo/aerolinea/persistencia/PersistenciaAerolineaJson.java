package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea
{
    // Llaves para aviones
    private static final String AVIONES = "aviones";
    private static final String NOMBRE_AVION = "nombre";
    private static final String CAPACIDAD_AVION = "capacidad";

    // Llaves para aeropuertos
    private static final String AEROPUERTOS = "aeropuertos";
    private static final String NOMBRE_AEROPUERTO = "nombre";
    private static final String CODIGO_AEROPUERTO = "codigo";
    private static final String CIUDAD_AEROPUERTO = "ciudad";
    private static final String LATITUD_AEROPUERTO = "latitud";
    private static final String LONGITUD_AEROPUERTO = "longitud";

    // Llaves para rutas
    private static final String RUTAS = "rutas";
    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String HORA_SALIDA = "horaSalida";
    private static final String HORA_LLEGADA = "horaLlegada";
    private static final String ORIGEN_RUTA = "origen";
    private static final String DESTINO_RUTA = "destino";

    // Llaves para vuelos
    private static final String VUELOS = "vuelos";
    private static final String FECHA_VUELO = "fecha";
    private static final String RUTA_VUELO = "codigoRuta";
    private static final String AVION_VUELO = "avion";

    @Override
    public void cargarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException, InformacionInconsistenteException
    {
        String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );

        cargarAviones( aerolinea, raiz.getJSONArray( AVIONES ) );
        cargarAeropuertos( aerolinea, raiz.getJSONArray( AEROPUERTOS ) );
        cargarRutas( aerolinea, raiz.getJSONArray( RUTAS ) );
        cargarVuelos( aerolinea, raiz.getJSONArray( VUELOS ) );
    }

    @Override
    public void salvarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException
    {
        JSONObject raiz = new JSONObject( );

        salvarAviones( aerolinea, raiz );
        salvarAeropuertos( aerolinea, raiz );
        salvarRutas( aerolinea, raiz );
        salvarVuelos( aerolinea, raiz );

        PrintWriter pw = new PrintWriter( archivo );
        raiz.write( pw, 2, 0 );
        pw.close( );
    }

    private void cargarAviones( Aerolinea aerolinea, JSONArray jAviones )
    {
        for( int i = 0; i < jAviones.length( ); i++ )
        {
            JSONObject jAvion = jAviones.getJSONObject( i );
            String nombre = jAvion.getString( NOMBRE_AVION );
            int capacidad = jAvion.getInt( CAPACIDAD_AVION );
            aerolinea.agregarAvion( new Avion( nombre, capacidad ) );
        }
    }

    private void cargarAeropuertos( Aerolinea aerolinea, JSONArray jAeropuertos )
    {
        for( int i = 0; i < jAeropuertos.length( ); i++ )
        {
            JSONObject jAeropuerto = jAeropuertos.getJSONObject( i );
            String nombre = jAeropuerto.getString( NOMBRE_AEROPUERTO );
            String codigo = jAeropuerto.getString( CODIGO_AEROPUERTO );
            String ciudad = jAeropuerto.getString( CIUDAD_AEROPUERTO );
            double latitud = jAeropuerto.getDouble( LATITUD_AEROPUERTO );
            double longitud = jAeropuerto.getDouble( LONGITUD_AEROPUERTO );
            aerolinea.agregarAeropuerto( new Aeropuerto( nombre, codigo, ciudad, latitud, longitud ) );
        }
    }

    private void cargarRutas( Aerolinea aerolinea, JSONArray jRutas ) throws InformacionInconsistenteException
    {
        for( int i = 0; i < jRutas.length( ); i++ )
        {
            JSONObject jRuta = jRutas.getJSONObject( i );
            String codigoRuta = jRuta.getString( CODIGO_RUTA );
            String horaSalida = jRuta.getString( HORA_SALIDA );
            String horaLlegada = jRuta.getString( HORA_LLEGADA );
            String codigoOrigen = jRuta.getString( ORIGEN_RUTA );
            String codigoDestino = jRuta.getString( DESTINO_RUTA );

            Aeropuerto origen = aerolinea.getAeropuerto( codigoOrigen );
            Aeropuerto destino = aerolinea.getAeropuerto( codigoDestino );

            if( origen == null )
                throw new InformacionInconsistenteException( "No existe el aeropuerto origen: " + codigoOrigen );
            if( destino == null )
                throw new InformacionInconsistenteException( "No existe el aeropuerto destino: " + codigoDestino );

            aerolinea.agregarRuta( new Ruta( horaSalida, horaLlegada, codigoRuta, destino, origen ) );
        }
    }

    private void cargarVuelos( Aerolinea aerolinea, JSONArray jVuelos ) throws InformacionInconsistenteException
    {
        for( int i = 0; i < jVuelos.length( ); i++ )
        {
            JSONObject jVuelo = jVuelos.getJSONObject( i );
            String fecha = jVuelo.getString( FECHA_VUELO );
            String codigoRuta = jVuelo.getString( RUTA_VUELO );
            String nombreAvion = jVuelo.getString( AVION_VUELO );

            try
            {
                aerolinea.programarVuelo( fecha, codigoRuta, nombreAvion );
            }
            catch( Exception e )
            {
                throw new InformacionInconsistenteException( e.getMessage( ) );
            }
        }
    }

    private void salvarAviones( Aerolinea aerolinea, JSONObject raiz )
    {
        JSONArray jAviones = new JSONArray( );
        for( Avion avion : aerolinea.getAviones( ) )
        {
            JSONObject jAvion = new JSONObject( );
            jAvion.put( NOMBRE_AVION, avion.getNombre( ) );
            jAvion.put( CAPACIDAD_AVION, avion.getCapacidad( ) );
            jAviones.put( jAvion );
        }
        raiz.put( AVIONES, jAviones );
    }

    private void salvarAeropuertos( Aerolinea aerolinea, JSONObject raiz )
    {
        JSONArray jAeropuertos = new JSONArray( );
        for( Aeropuerto aeropuerto : aerolinea.getAeropuertos( ) )
        {
            JSONObject jAeropuerto = new JSONObject( );
            jAeropuerto.put( NOMBRE_AEROPUERTO, aeropuerto.getNombre( ) );
            jAeropuerto.put( CODIGO_AEROPUERTO, aeropuerto.getCodigo( ) );
            jAeropuerto.put( CIUDAD_AEROPUERTO, aeropuerto.getNombreCiudad( ) );
            jAeropuerto.put( LATITUD_AEROPUERTO, aeropuerto.getLatitud( ) );
            jAeropuerto.put( LONGITUD_AEROPUERTO, aeropuerto.getLongitud( ) );
            jAeropuertos.put( jAeropuerto );
        }
        raiz.put( AEROPUERTOS, jAeropuertos );
    }

    private void salvarRutas( Aerolinea aerolinea, JSONObject raiz )
    {
        JSONArray jRutas = new JSONArray( );
        for( Ruta ruta : aerolinea.getRutas( ) )
        {
            JSONObject jRuta = new JSONObject( );
            jRuta.put( CODIGO_RUTA, ruta.getCodigoRuta( ) );
            jRuta.put( HORA_SALIDA, ruta.getHoraSalida( ) );
            jRuta.put( HORA_LLEGADA, ruta.getHoraLlegada( ) );
            jRuta.put( ORIGEN_RUTA, ruta.getOrigen( ).getCodigo( ) );
            jRuta.put( DESTINO_RUTA, ruta.getDestino( ).getCodigo( ) );
            jRutas.put( jRuta );
        }
        raiz.put( RUTAS, jRutas );
    }

    private void salvarVuelos( Aerolinea aerolinea, JSONObject raiz )
    {
        JSONArray jVuelos = new JSONArray( );
        for( Vuelo vuelo : aerolinea.getVuelos( ) )
        {
            JSONObject jVuelo = new JSONObject( );
            jVuelo.put( FECHA_VUELO, vuelo.getFecha( ) );
            jVuelo.put( RUTA_VUELO, vuelo.getRuta( ).getCodigoRuta( ) );
            jVuelo.put( AVION_VUELO, vuelo.getAvion( ).getNombre( ) );
            jVuelos.put( jVuelo );
        }
        raiz.put( VUELOS, jVuelos );
    }
}