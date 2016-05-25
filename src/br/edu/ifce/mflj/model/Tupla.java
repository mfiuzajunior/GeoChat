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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((de == null) ? 0 : de.hashCode());
		result = prime * result + ((para == null) ? 0 : para.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tupla other = (Tupla) obj;
		if (de == null) {
			if (other.de != null)
				return false;
		} else if (!de.equals(other.de))
			return false;
		if (para == null) {
			if (other.para != null)
				return false;
		} else if (!para.equals(other.para))
			return false;
		return true;
	}
}
