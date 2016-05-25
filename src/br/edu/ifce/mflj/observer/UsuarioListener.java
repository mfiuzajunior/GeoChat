package br.edu.ifce.mflj.observer;

public interface UsuarioListener {
	void logon( String usuario );
	void logoff( String usuario );
}
