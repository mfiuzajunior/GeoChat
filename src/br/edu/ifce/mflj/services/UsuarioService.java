package br.edu.ifce.mflj.services;

import java.util.ArrayList;
import java.util.List;

import net.jini.core.lease.Lease;
import br.edu.ifce.mflj.model.Espaco;
import br.edu.ifce.mflj.model.Tupla;

public class UsuarioService {

	private Espaco espaco;

	private String usuario;

	public UsuarioService( String usuario ){
		espaco = Espaco.getInstancia();

		this.usuario = usuario;
	}

	public boolean usuarioExiste(){
		Tupla tupla = new Tupla();

		tupla.para = usuario;

		if( (Tupla) espaco.lerTupla( tupla ) == null ){
			espaco.escreverTupla( tupla, Lease.FOREVER );
			return false;
		}

		return true;
	}

	public void logarUsuario() {
		Tupla tupla = new Tupla();

		tupla.para = usuario;

		espaco.escreverTupla( tupla, Lease.FOREVER );
	}

	public void atualizarPosicao( String latitude, String longitude, String raio ){
		List<Tupla> tuplasObtidas = new ArrayList<Tupla>();
		Tupla tupla = new Tupla();

		tupla.para = usuario;

		while( espaco.lerTupla( tupla ) != null ){
			tuplasObtidas.add( (Tupla) espaco.retirarTupla( tupla ) );
		}

		for( Tupla tuplaAtual : tuplasObtidas ){
			tuplaAtual.latitude		= latitude;
			tuplaAtual.longitude	= longitude;
			tuplaAtual.raio			= raio;
		}
	}

	public void sair() {
		Tupla tupla = new Tupla();

		tupla.para = usuario;

		while( (Tupla) espaco.lerTupla( tupla ) != null ){
			espaco.retirarTupla( tupla );
		}
	}
}
