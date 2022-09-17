package py.una.entidad;

public class Datos {
	long id;
	String ciudad;
	double porcentaje;
	double temperatura;
	double velocidad;
	String fecha;
	String hora;
	public Datos() {
		
	}
	public Datos(Long pid,String pciudad,double pporcentaje,double ptemperatura,double pvelocidad,String pfecha,String phora){
		this.id = pid;
		this.ciudad=pciudad;
		this.porcentaje=pporcentaje;
		this.temperatura=ptemperatura;
		this.velocidad=pvelocidad;
		this.fecha=pfecha;
		this.hora=phora;
	}
	public long getid() {
		return id;
	}
	public void setid(long id) {
		this.id=id;
	}
	public String getciudad() {
		return ciudad;
	}
	public void setciudad(String ciudad) {
		this.ciudad=ciudad;
	}
	public double getporcentaje() {
		return porcentaje;
	}
	public void setporcentaje(double porcentaje) {
		this.porcentaje=porcentaje;
	}
	public double gettemperatura() {
		return temperatura;
	}
	public void settemperatura(double temperatura) {
		this.temperatura=temperatura;
	}
	public double getvelocidad() {
		return velocidad;
	}
	public void setvelocidad(double velocidad) {
		this.velocidad=velocidad;
	}
	public String getfecha() {
		return fecha;
	}
	public void setfecha(String fecha) {
		this.fecha=fecha;
	}
	public String gethora() {
		return hora;
	}
	public void sethora(String hora) {
		this.hora=hora;
	}
}