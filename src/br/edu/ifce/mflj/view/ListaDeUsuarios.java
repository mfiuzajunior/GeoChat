package br.edu.ifce.mflj.view;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import br.edu.ifce.mflj.dados.Usuario;

public class ListaDeUsuarios extends JList<Usuario> {

	private static final long serialVersionUID = 5963633108831408666L;

	private DefaultListModel<Usuario> usuarios;

	public ListaDeUsuarios(){
		this.usuarios = new DefaultListModel<Usuario>();
		this.setModel( usuarios );
	}
}
