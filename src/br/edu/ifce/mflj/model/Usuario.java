package br.edu.ifce.mflj.model;

public class Usuario {

	private String	apelido,
					latitude,
					longitude,
					raio;

	public Usuario(){}

	public Usuario( String apelido ){
		this.apelido = apelido;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getLatitude() {
		return latitude == null ? "0" : latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude == null ? "0" : longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRaio() {
		return raio == null ? "0" : raio;
	}

	public void setRaio(String raio) {
		this.raio = raio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
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
		Usuario other = (Usuario) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		return true;
	}
}
