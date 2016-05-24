package br.edu.ifce.mflj.services;

import javax.swing.JSlider;

import br.edu.ifce.mflj.dados.Usuario;
import br.edu.ifce.mflj.model.Espaco;
import br.edu.ifce.mflj.model.Tupla;

public class UsuarioService {

	private Espaco espaco;

	public UsuarioService(){
		espaco = Espaco.getInstancia();
	}

	public boolean usuarioExiste( Usuario usuario ){
		try {
			return usuarioExiste( usuario.getApelido() );
		} catch( NullPointerException nullPointerException ){
			return false;
		}
	}

	public boolean usuarioExiste( String apelido ){
		Tupla tupla = new Tupla();

		tupla.de = apelido;

		do {
			tupla = (Tupla) espaco.retirarTupla( tupla );

			if( tupla != null ){
				espaco.escreverTupla( tupla, 0 );
				return true;
			}

		} while( tupla == null );

		return false;
	}

	public void logarUsuario(String apelido) {
		Tupla tupla = new Tupla();

		tupla.para = apelido;

		espaco.escreverTupla( tupla, 0 );
	}

	public void atualizarPosicao( String usuario, int latitude, int longitude, int raio ){
	}
}
