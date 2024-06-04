package Clases;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//private static final long serialVersionUID = -544301453550829104L;
 private int nro;
 private String nombre;
 private long dni;
public Cliente(int nro, String nombre, long dni) {
	super();
	this.nro = nro;
	this.nombre = nombre;
	this.dni = dni;
}
public int getNro() {
	return nro;
}
public void setNro(int nro) {
	this.nro = nro;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public long getDni() {
	return dni;
}
public void setDni(long dni) {
	this.dni = dni;
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
	Cliente other = (Cliente) obj;
	return nro == other.nro;
}
 
 
}
