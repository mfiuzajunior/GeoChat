package br.edu.ifce.mflj.model;

import net.jini.core.entry.Entry;

public class Tupla implements Entry {
	private static final long serialVersionUID = 8288483845511481068L;

	public String	de,
					para,
					status,
					latitude,
					longitude;

	public Tupla(){}

	public Tupla( String de, String para, String status, String latitude, String longitude ){
		super();

		this.de			= de;
		this.para		= para;
		this.status		= status;
		this.latitude	= latitude;
		this.longitude	= longitude;
	}
}
