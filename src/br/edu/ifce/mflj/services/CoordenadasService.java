package br.edu.ifce.mflj.services;

import java.util.ArrayList;
import java.util.List;

import net.jini.core.lease.Lease;
import br.edu.ifce.mflj.model.Coordenadas;
import br.edu.ifce.mflj.model.Espaco;
import br.edu.ifce.mflj.model.Usuario;
import br.edu.ifce.mflj.observer.CoordenadasListener;

public class CoordenadasService implements Runnable {

	private List<CoordenadasListener> coordenadasListerners = new ArrayList<CoordenadasListener>();

	private Espaco	espaco;
	private Usuario	usuario;
	private boolean	online,
					atualizarCoordenadas;

	public CoordenadasService( Usuario usuario ){
		espaco			= Espaco.getInstancia();
		this.usuario	= usuario;

		setOnline( true );
	}

	public void setUsuario( Usuario usuario ){
		this.usuario = usuario;
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public boolean isOnline(){
		return online;
	}

	public void setOnline( boolean online ){
		this.online = online;
	}

	public void salvarCoordenadas(){
		atualizarCoordenadas = true;
	}

	public void addCoordenadasListener( CoordenadasListener coordenadasListener ){
		coordenadasListerners.add( coordenadasListener );
	}

	private void atualizarCoordenadas(){
		Coordenadas coordenadas = new Coordenadas();

		coordenadas.apelido = getUsuario().getApelido();

		coordenadas = (Coordenadas) espaco.retirarTupla( coordenadas );

		if( coordenadas == null ){
			coordenadas			= new Coordenadas();
			coordenadas.apelido	= getUsuario().getApelido();
		}

		coordenadas.latitude	= getUsuario().getLatitude();
		coordenadas.longitude	= getUsuario().getLongitude();

		espaco.escreverTupla( coordenadas, Lease.FOREVER );
		
		atualizarCoordenadas = false;
	}

	@Override
	public void run() {
		Coordenadas			coordenadas;
		List<Coordenadas>	coordenadasEncontradas = new ArrayList<Coordenadas>();

		while( isOnline() ){

			// O trabalho de atualizar as coordenadas podem entrar em conflito com o processamento das coordenadas dos outros usuários.
			// Essa flag faz um chaveamento entre meramente atualizar as próprias coordenadas ou continuar o processamento.
			if( atualizarCoordenadas ){
				atualizarCoordenadas();
				continue;
			}

			do {
				coordenadas = (Coordenadas) espaco.retirarTupla( new Coordenadas() );
				if( coordenadas != null ){
					coordenadasEncontradas.add( coordenadas );
				}
			} while( coordenadas != null );

			for( Coordenadas coordenadasAtuais : coordenadasEncontradas ){
				if( !coordenadasAtuais.apelido.equals( getUsuario().getApelido() ) ){
					notificarDistancia( coordenadasAtuais.apelido, usuarioDentroDoRaio( coordenadasAtuais ) );
				}
				espaco.escreverTupla( coordenadasAtuais, Lease.FOREVER );
			}
			coordenadasEncontradas.clear();
		}
	}

	private void notificarDistancia( String apelido, boolean perto ){
		for( CoordenadasListener coordenadasListener : coordenadasListerners ){
			if( perto ){
				coordenadasListener.perto( apelido );
			} else {
				coordenadasListener.longe( apelido );
			}
		}
	}

	private boolean usuarioDentroDoRaio(Coordenadas coordenadas) {
		return Math.sqrt(	Math.pow( Math.abs( coordenadas.latitude - getUsuario().getLatitude() ), 2 ) +
							Math.pow( Math.abs( coordenadas.longitude - getUsuario().getLongitude() ), 2 ) ) <= getUsuario().getRaio();
	}
}
