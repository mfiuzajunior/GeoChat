package br.edu.ifce.mflj.observer;

import br.edu.ifce.mflj.model.Mensagem;

public interface MensagemListener {
	void novaMensagem( Mensagem mensagem );
}
