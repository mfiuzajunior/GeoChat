package br.edu.ifce.mflj.services;

import java.util.ArrayList;
import java.util.List;

import net.jini.core.lease.Lease;
import br.edu.ifce.mflj.model.Espaco;
import br.edu.ifce.mflj.model.Mensagem;
import br.edu.ifce.mflj.model.Usuario;
import br.edu.ifce.mflj.observer.MensagemListener;

public class MensagemService implements Runnable {
	private Espaco	espaco;
	private Usuario	usuario;
	private boolean	online;

	public MensagemService( Usuario usuario ){
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

	private List<MensagemListener> mensagemListeners = new ArrayList<MensagemListener>();

	public void addMensagemListener( MensagemListener mensagemListener ){
		this.mensagemListeners.add( mensagemListener );
	}

	public void enviarMensagem( String para, String texto ){
		Mensagem mensagem = new Mensagem();

		mensagem.de			= usuario.getApelido();
		mensagem.para		= para;
		mensagem.mensagem	= texto;

		espaco.escreverTupla( mensagem, Lease.FOREVER );
	}

	@Override
	public void run() {
		Mensagem	template = new Mensagem(),
					mensagem;

		template.para = getUsuario().getApelido();

		while( isOnline() ){
			mensagem = (Mensagem) espaco.retirarTupla( template );

			if( mensagem != null ){
				notificarNovaMensagem( mensagem );
			}
		}
	}

	private void notificarNovaMensagem( Mensagem mensagem ){
		for( MensagemListener mensagemListener : mensagemListeners ){
			mensagemListener.novaMensagem( mensagem );
		}
	}
}