package br.edu.ifce.mflj.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GeoChatMainView extends JFrame {

	private static final long serialVersionUID = 2282089787721299410L;

	private JFrame				janelaPrincipal;
	private	JScrollPane			scrollLogDeMensagens,
								scrollEditorDeMensagens,
								scrollListaDeUsuarios;
	private	JButton				botaoEnviarMensagem;
	private JTextArea			textEditorDeMensagens;
	private JPanel				mapa;

	private	ListaDeUsuarios		listaDeUsuarios;
	private	LogDeMensagens		logDeMensagens;

	private String				apelido;

	public JPanel getMapa() {
		return mapa;
	}

	public void setPanelTabuleiro(JPanel mapa) {
		this.mapa = mapa;
	}

	public JScrollPane getScrollLogDeMensagens() {
		return scrollLogDeMensagens;
	}

	public void setScrollLogDeMensagens(JScrollPane scrollLogDeMensagens) {
		this.scrollLogDeMensagens = scrollLogDeMensagens;
	}

	public JScrollPane getScrollEditorDeMensagens() {
		return scrollEditorDeMensagens;
	}

	public void setScrollEditorDeMensagens(JScrollPane scrollEditorDeMensagens) {
		this.scrollEditorDeMensagens = scrollEditorDeMensagens;
	}

	public LogDeMensagens getLogDeMensagens() {
		return logDeMensagens;
	}

	public void setLogDeMensagens(LogDeMensagens logDeMensagens) {
		this.logDeMensagens = logDeMensagens;
	}

	public JButton getBotaoEnviarMensagem() {
		return botaoEnviarMensagem;
	}

	public void setBotaoEnviarMensagem(JButton botaoEnviarMensagem) {
		this.botaoEnviarMensagem = botaoEnviarMensagem;
	}

	public JTextArea getTextEditorDeMensagens() {
		return textEditorDeMensagens;
	}

	public void setTextEditorDeMensagens(JTextArea textEditorDeMensagens) {
		this.textEditorDeMensagens = textEditorDeMensagens;
	}

	public ListaDeUsuarios getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(ListaDeUsuarios listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public JScrollPane getScrollListaDeUsuarios() {
		return scrollListaDeUsuarios;
	}

	public void setScrollListaDeUsuarios(JScrollPane scrollListaDeUsuarios) {
		this.scrollListaDeUsuarios = scrollListaDeUsuarios;
	}

	public void inicializarInterfaceGrafica(){
		apelido = JOptionPane.showInputDialog( janelaPrincipal, "Como deseja ser chamado?" );

		janelaPrincipal.setResizable( false );
		janelaPrincipal.setBounds( 100, 100, 950, 550 );
		janelaPrincipal.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janelaPrincipal.getContentPane().setLayout( null );
		janelaPrincipal.setVisible( true );
		janelaPrincipal.setTitle( "Seja bem vindo " + apelido );

		scrollLogDeMensagens.setViewportView( logDeMensagens );
		logDeMensagens.setEditable( false );

		scrollEditorDeMensagens.setViewportView( textEditorDeMensagens );

		scrollListaDeUsuarios.setViewportView( listaDeUsuarios );

		scrollLogDeMensagens.setBounds( 5, 5, 160, 0 );
		mapa.setBounds(	5 + scrollLogDeMensagens.getWidth() + 10,
									5,
									100,
									100 );
		scrollLogDeMensagens.setSize(	scrollLogDeMensagens.getWidth(),
										mapa.getHeight() );
		scrollListaDeUsuarios.setBounds(	mapa.getX() + mapa.getWidth() + 5, 5,
											160, mapa.getHeight() );
		scrollEditorDeMensagens.setBounds(	5, mapa.getHeight() + 10,
											scrollLogDeMensagens.getWidth() + mapa.getWidth() + scrollListaDeUsuarios.getWidth() + 10, 40 );
		botaoEnviarMensagem.setBounds(	( scrollEditorDeMensagens.getX() + scrollEditorDeMensagens.getWidth() ) - 117, 
										scrollEditorDeMensagens.getY() + scrollEditorDeMensagens.getHeight() + 5,
										117, 25 );

		janelaPrincipal.getContentPane().add( scrollLogDeMensagens );
		janelaPrincipal.getContentPane().add( mapa );
		janelaPrincipal.getContentPane().add( scrollListaDeUsuarios );
		janelaPrincipal.getContentPane().add( botaoEnviarMensagem );
		janelaPrincipal.getContentPane().add( scrollEditorDeMensagens );
	}
}
