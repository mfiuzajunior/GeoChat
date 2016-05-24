package br.edu.ifce.mflj.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.edu.ifce.mflj.services.UsuarioService;

public class GeoChatMainView extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 2282089787721299410L;

	private	JScrollPane			scrollLogDeMensagens,
								scrollEditorDeMensagens,
								scrollListaDeUsuarios;
	private	JButton				botaoEnviarMensagem;
	private JTextArea			textEditorDeMensagens;
	private JLabel				labelLatitude,
								labelLongitude,
								labelRaio;
	private JSlider				latitude,
								longitude,
								raio;
	private Mapa				mapa;

	private String				apelido;

	private	ListaDeUsuarios		listaDeUsuarios;
	private	LogDeMensagens		logDeMensagens;

	private UsuarioService		usuarioService;

	public GeoChatMainView(){
		super();
		usuarioService = new UsuarioService();
	}

	public Mapa getMapa() {
		if( mapa == null ){
			mapa = new Mapa(0, 0, 0);
			mapa.setBounds(	getScrollLogDeMensagens().getX() + getScrollLogDeMensagens().getWidth() + 5,
							5,
							600,
							250 );
		}

		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public JScrollPane getScrollLogDeMensagens() {
		if( scrollLogDeMensagens == null ){
			scrollLogDeMensagens = new JScrollPane();
			scrollLogDeMensagens.setBounds( 5, 5, 150, 250 );
			scrollLogDeMensagens.setViewportView( getLogDeMensagens() );
		}
		return scrollLogDeMensagens;
	}

	public void setScrollLogDeMensagens(JScrollPane scrollLogDeMensagens) {
		this.scrollLogDeMensagens = scrollLogDeMensagens;
	}

	public JScrollPane getScrollEditorDeMensagens() {
		if( scrollEditorDeMensagens == null ){
			scrollEditorDeMensagens = new JScrollPane();
			scrollEditorDeMensagens.setViewportView( getTextEditorDeMensagens() );
			scrollEditorDeMensagens.setBounds(	5,
												getMapa().getHeight() + 10,
												getScrollLogDeMensagens().getWidth() + getMapa().getWidth() + getScrollListaDeUsuarios().getWidth() + 10, 40 );

		}
		return scrollEditorDeMensagens;
	}

	public void setScrollEditorDeMensagens(JScrollPane scrollEditorDeMensagens) {
		this.scrollEditorDeMensagens = scrollEditorDeMensagens;
	}

	public LogDeMensagens getLogDeMensagens() {
		if( logDeMensagens == null ){
			logDeMensagens = new LogDeMensagens();
			logDeMensagens.setEditable( false );
		}
		return logDeMensagens;
	}

	public void setLogDeMensagens(LogDeMensagens logDeMensagens) {
		this.logDeMensagens = logDeMensagens;
	}

	public JButton getBotaoEnviarMensagem() {
		if( botaoEnviarMensagem == null ){
			botaoEnviarMensagem = new JButton("Enviar");
			botaoEnviarMensagem.setBounds(	( getScrollEditorDeMensagens().getX() + getScrollEditorDeMensagens().getWidth() ) - 117, 
											getScrollEditorDeMensagens().getY() + getScrollEditorDeMensagens().getHeight() + 5,
											117, 25 );
		}
		return botaoEnviarMensagem;
	}

	public void setBotaoEnviarMensagem(JButton botaoEnviarMensagem) {
		this.botaoEnviarMensagem = botaoEnviarMensagem;
	}

	public JTextArea getTextEditorDeMensagens() {
		if( textEditorDeMensagens == null ){
			textEditorDeMensagens = new JTextArea();
		}
		return textEditorDeMensagens;
	}

	public void setTextEditorDeMensagens(JTextArea textEditorDeMensagens) {
		this.textEditorDeMensagens = textEditorDeMensagens;
	}

	public ListaDeUsuarios getListaDeUsuarios() {
		if( listaDeUsuarios == null ){
			listaDeUsuarios = new ListaDeUsuarios();
		}
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(ListaDeUsuarios listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public JScrollPane getScrollListaDeUsuarios() {
		if( scrollListaDeUsuarios == null ){
			scrollListaDeUsuarios = new JScrollPane();
			scrollListaDeUsuarios.setViewportView( getListaDeUsuarios() );
			scrollListaDeUsuarios.setBounds( getMapa().getX() + getMapa().getWidth() + 5, 5, 160, getMapa().getHeight() );
		}
		return scrollListaDeUsuarios;
	}

	public void setScrollListaDeUsuarios(JScrollPane scrollListaDeUsuarios) {
		this.scrollListaDeUsuarios = scrollListaDeUsuarios;
	}

	public JLabel getLabelLatitude(){
		if( labelLatitude == null ){
			labelLatitude = new JLabel( "Latitude" );
			labelLatitude.setBounds( 5, getBotaoEnviarMensagem().getY() + getBotaoEnviarMensagem().getHeight() + 5, 100, 10 );
		}
		return labelLatitude;
	}

	public JLabel getLabelLongitude(){
		if( labelLongitude == null ){
			labelLongitude = new JLabel( "Longitude" );
			labelLongitude.setBounds( 5, getLatitude().getY() + getLatitude().getHeight() + 5, 100, 15 );
		}
		return labelLongitude;
	}

	public JLabel getLabelRaio(){
		if( labelRaio == null ){
			labelRaio = new JLabel( "Raio" );
			labelRaio.setBounds( 5, getLongitude().getY() + getLongitude().getHeight() + 5, 100, 15 );
		}
		return labelRaio;
	}

	public JSlider getLatitude() {
		if( latitude == null ){
			latitude = new JSlider(JSlider.HORIZONTAL, 0, getMapa().getHeight(), 0 );
			latitude.setMajorTickSpacing( 50 );
			latitude.setMinorTickSpacing( 5 );
			latitude.setPaintTicks( true );
			latitude.setPaintLabels( true );
			latitude.setBounds( 5,
								getLabelLatitude().getY() + getLabelLatitude().getHeight() + 5, getScrollEditorDeMensagens().getWidth(), 100 );
			latitude.addChangeListener( this );
		}
		return latitude;
	}

	public void setLatitude(JSlider latitude) {
		this.latitude = latitude;
	}

	public JSlider getLongitude() {
		if( longitude == null ){
			longitude = new JSlider(JSlider.HORIZONTAL, 0, getMapa().getWidth(), 0 );
			longitude.setMajorTickSpacing( 100 );
			longitude.setMinorTickSpacing( 10 );
			longitude.setPaintTicks( true );
			longitude.setPaintLabels( true );
			longitude.setBounds( 5,
								getLabelLongitude().getY() + getLabelLongitude().getHeight() + 5, getScrollEditorDeMensagens().getWidth(), 100 );
			longitude.addChangeListener( this );
		}
		return longitude;
	}

	public void setLongitude(JSlider longitude) {
		this.longitude = longitude;
	}

	public JSlider getRaio() {
		if( raio == null ){
			raio = new JSlider(JSlider.HORIZONTAL, 0, 100, 0 );
			raio.setMajorTickSpacing( 10 );
			raio.setMinorTickSpacing( 2 );
			raio.setPaintTicks( true );
			raio.setPaintLabels( true );
			raio.setBounds( 5,
							getLabelRaio().getY() + getLabelRaio().getHeight() + 5, getScrollEditorDeMensagens().getWidth(), 100 );
			raio.addChangeListener( this );
		}
		return raio;
	}

	public void setRaio(JSlider raio) {
		this.raio = raio;
	}

	public void inicializarInterfaceGrafica(){
		do {
			apelido = JOptionPane.showInputDialog( this, "Como deseja ser chamado?", "Bem vindo", JOptionPane.QUESTION_MESSAGE );
			if( apelido == null ){
				System.exit( 0 );
			} else if( usuarioService.usuarioExiste( apelido ) ){
				JOptionPane.showMessageDialog( this, "Usuário já logado", "Atenção", JOptionPane.INFORMATION_MESSAGE );
				apelido = "";
			}
		} while( apelido.equals( "" ) );

		usuarioService.logarUsuario( apelido );

		setResizable( false );
		setBounds( 100, 100, 950, 700 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		getContentPane().setLayout( null );
		setVisible( true );
		setTitle( "Seja bem vindo " + apelido );

		add( getScrollLogDeMensagens() );
		add( getMapa() );
		add( getScrollListaDeUsuarios() );
		add( getBotaoEnviarMensagem() );
		add( getScrollEditorDeMensagens() );

		add( getLabelLatitude() );
		add( getLatitude() );

		add( getLabelLongitude() );
		add( getLongitude() );

		add( getLabelRaio() );
		add( getRaio() );
		
		repaint();
	}

	@Override
	public void stateChanged(ChangeEvent changeEvent) {
		if( changeEvent.getSource().equals( latitude ) ){
			getMapa().setLatitude( latitude.getValue() );
			getMapa().repaint();
		}

		if( changeEvent.getSource().equals( longitude ) ){
			getMapa().setLongitude( longitude.getValue() );
			getMapa().repaint();
		}

		if( changeEvent.getSource().equals( raio ) ){
			getMapa().setRaio( raio.getValue() );
			getMapa().repaint();
		}

		usuarioService.atualizarPosicao( apelido, latitude.getValue(), longitude.getValue(), raio.getValue() );
	}

	public static void main(String[] args) {
		new GeoChatMainView().inicializarInterfaceGrafica();
	}
}