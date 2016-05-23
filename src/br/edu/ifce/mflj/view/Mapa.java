package br.edu.ifce.mflj.view;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mapa extends JPanel {

	private static final long serialVersionUID = 1561286188213865485L;

	private int	latitude,
				longitude,
				raio;

	public Mapa( int latitude, int longitude, int raio ) {
		super();

		setLatitude( latitude );
		setLongitude( longitude );
		setRaio( raio );
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;			
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;			
	}

	public int getRaio() {
		return raio;
	}

	public void setRaio(int raio) {
		this.raio = raio;
	}

	@Override
	protected void paintComponent( Graphics graphics ){
		super.paintComponent( graphics );
		try {
			graphics.drawImage(new ImageIcon(ImageIO.read(new File("img/mapa-mundi.jpg"))).getImage(), 0, 0, null);

			graphics.fillOval( longitude, latitude, 2, 2 );
			graphics.drawOval( longitude - raio, latitude - raio, raio*2, raio*2 );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}