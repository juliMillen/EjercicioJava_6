package Clases;

import java.util.Objects;

public class Proveedor {

	private String nombre;
	private int nro;
	private String email;
	private long celular;
	public Proveedor(String nombre, int nro, String email, long celular) {
		super();
		this.nombre = nombre;
		this.nro = nro;
		this.email = email;
		this.celular = celular;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getCelular() {
		return celular;
	}
	public void setCelular(long celular) {
		this.celular = celular;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nro);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proveedor other = (Proveedor) obj;
		return nro == other.nro;
	}
	
}
