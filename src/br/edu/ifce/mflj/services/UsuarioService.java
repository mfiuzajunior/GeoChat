package br.edu.ifce.mflj.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.jini.core.lease.Lease;
import br.edu.ifce.mflj.model.Espaco;
import br.edu.ifce.mflj.model.Usuario;
import br.edu.ifce.mflj.model.UsuarioLogado;

public class UsuarioService {

	private Espaco	espaco;
	private Usuario	usuario;
	private boolean	online;

	public UsuarioService( Usuario usuario ){
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

	public boolean usuarioExiste(){
		UsuarioLogado tupla = new UsuarioLogado();

		tupla.apelido = usuario.getApelido();

		if( (UsuarioLogado) espaco.lerTupla( tupla ) == null ){
			return false;
		}

		return true;
	}

	public void logarUsuario() {
		UsuarioLogado tupla = new UsuarioLogado();

		tupla.apelido = usuario.getApelido();

		espaco.escreverTupla( tupla, Lease.FOREVER );

	}

	public List<String> usuariosLogados(){
		List<String>		usuariosLogados	= new ArrayList<String>();
		Set<UsuarioLogado>	tuplasObtidas	= new HashSet<UsuarioLogado>();
		UsuarioLogado		tupla			= null;

		do {
			tupla = (UsuarioLogado) espaco.retirarTupla( new UsuarioLogado() );
			if( tupla != null ){
				tuplasObtidas.add( tupla );
			}
		} while( tupla != null );

		for( UsuarioLogado tuplaAtual : tuplasObtidas ){
			if( !tuplaAtual.apelido.equals( usuario.getApelido() ) ){
				usuariosLogados.add( tuplaAtual.apelido );
			}

			espaco.escreverTupla( tuplaAtual, Lease.FOREVER );
		}

		return usuariosLogados;
	}

	public void sair() {
		UsuarioLogado tupla = new UsuarioLogado();

		tupla.apelido = usuario.getApelido();

		while( (UsuarioLogado) espaco.lerTupla( tupla ) != null ){
			espaco.retirarTupla( tupla );
		}
	}
}