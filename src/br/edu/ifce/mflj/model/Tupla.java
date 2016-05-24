package br.edu.ifce.mflj.model;

import net.jini.core.entry.Entry;

public class Tupla implements Entry {
	private static final long serialVersionUID = 8288483845511481068L;

	public String	de,
					para,
					latitude,
					longitude,
					raio;

	public Tupla(){}

	public Tupla( String de, String para, String latitude, String longitude, String raio ){
		super();

		this.de			= de;
		this.para		= para;
		this.latitude	= latitude;
		this.longitude	= longitude;
		this.raio		= raio;
	}
}
