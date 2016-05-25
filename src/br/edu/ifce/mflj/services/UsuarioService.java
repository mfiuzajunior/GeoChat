package br.edu.ifce.mflj.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.jini.core.lease.Lease;
import br.edu.ifce.mflj.model.Espaco;
import br.edu.ifce.mflj.model.Tupla;
import br.edu.ifce.mflj.model.Usuario;

public class UsuarioService implements Runnable {

	private Espaco espaco;
	private Usuario usuario;

	private boolean servicoOnline;

	public UsuarioService(){}

	public UsuarioService( Usuario usuario ){
		espaco			= Espaco.getInstancia();
		this.usuario	= usuario;
		servicoOnline	= true;

		new Thread( this ).start();
	}

	public void setUsuario( Usuario usuario ){
		this.usuario = usuario;
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public void setServicoOnline( boolean online ){
		servicoOnline = online;
	}

	public boolean usuarioExiste(){
		Tupla tupla = new Tupla();

		tupla.para = usuario.getApelido();

		if( (Tupla) espaco.lerTupla( tupla ) == null ){
			espaco.escreverTupla( tupla, Lease.FOREVER );
			return false;
		}

		return true;
	}

	public void logarUsuario() {
		Tupla tupla = new Tupla();

		tupla.para = usuario.getApelido();

		espaco.escreverTupla( tupla, Lease.FOREVER );
	}

	public void atualizarPosicao(){
		List<Tupla> tuplasObtidas = new ArrayList<Tupla>();
		Tupla tupla = new Tupla();

		tupla.para = usuario.getApelido();

		do {
			tupla = (Tupla) espaco.retirarTupla( tupla );
			if( tupla != null ){
				tuplasObtidas.add( tupla );
			}
		} while( tupla != null );

		for( Tupla tuplaAtual : tuplasObtidas ){
			tuplaAtual.latitude		= usuario.getLatitude();
			tuplaAtual.longitude	= usuario.getLongitude();
			tuplaAtual.raio			= usuario.getRaio();
		}
	}

	public void sair() {
		Tupla tupla = new Tupla();

		tupla.para = usuario.getApelido();

		while( (Tupla) espaco.lerTupla( tupla ) != null ){
			espaco.retirarTupla( tupla );
		}
	}

	@Override
	public void run() {
		Set<Tupla>	tuplasObtidas	= new HashSet<Tupla>();
		Tupla		tupla			= new Tupla();

		while( servicoOnline ){
			tupla = (Tupla) espaco.retirarTupla( tupla );

			if( tupla != null ){
				tuplasObtidas.add( tupla );
			}

			for( Tupla tuplaAtual : tuplasObtidas ){
				if( !tuplaAtual.para.equals( usuario.getApelido() ) ){
					if( tuplaDentroDoRaio( tupla ) ){
						System.out.println("perto");
					}
				}

				espaco.escreverTupla( tuplaAtual, Lease.FOREVER );
			}
			tuplasObtidas.clear();
		}
	}

	private boolean tuplaDentroDoRaio(Tupla tupla) {
		int 	latitude	= tupla.latitude == null ? 0 : Integer.parseInt( tupla.latitude ),
				longitude	= tupla.longitude == null ? 0 : Integer.parseInt( tupla.longitude );
		double	distancia	= Math.sqrt(	Math.pow( Math.abs( latitude - Integer.parseInt( usuario.getLatitude() ) ), 2 ) + 
											Math.pow( Math.abs( longitude - Integer.parseInt( usuario.getLongitude() ) ), 2 ) );

		return distancia <= Double.parseDouble( usuario.getRaio() );
	}
}