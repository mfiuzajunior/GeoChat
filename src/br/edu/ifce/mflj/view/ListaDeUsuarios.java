package br.edu.ifce.mflj.view;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ListaDeUsuarios extends JList<String> {

	private static final long serialVersionUID = 5963633108831408666L;

	private DefaultListModel<String> usuarios;

	public ListaDeUsuarios(){
		this.usuarios = new DefaultListModel<String>();
		this.setModel( usuarios );
	}

	public void adicionarUsuarios( List<String> usuarios ){
		for( String usuarioAtual : usuarios ){
			adicionarUsuario( usuarioAtual );
		}
	}

	public void adicionarUsuario( String usuario ){
		this.usuarios.addElement( usuario );
	}

	public DefaultListModel<String> getUsuarios(){
		return usuarios;
	}
}
