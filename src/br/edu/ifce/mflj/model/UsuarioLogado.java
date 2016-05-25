package br.edu.ifce.mflj.model;

import net.jini.core.entry.Entry;

public class UsuarioLogado implements Entry {
	private static final long serialVersionUID = 8288483845511481068L;

	public String apelido;

	public UsuarioLogado(){}

	public UsuarioLogado( String apelido ){
		super();
		this.apelido = apelido;
	}
}
