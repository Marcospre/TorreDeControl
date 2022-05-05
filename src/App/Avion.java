package App;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Avion {
	
	String codigo_avion;
	String fecha_soli;
	LocalDateTime fecha_eje;    /* fecha de la solicitud */
	String proce_desti;
	String prioridad;
	DateTimeFormatter f = DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss");  /* Formato de visualizacion para las fechas */
	
	public Avion(String codigo_avion, String fecha, String pro, String prio) {
		this.codigo_avion = codigo_avion;
		this.fecha_soli = fecha;
		this.prioridad = prio;
		this.proce_desti = pro;
	}

	public String getCodigo_avion() {
		return codigo_avion;
	}

	public void setCodigo_avion(String codigo_avion) {
		this.codigo_avion = codigo_avion;
	}

	public String getFecha() {
		return fecha_soli;
	}

	public void setfecha_eje(LocalDateTime time) {
		this.fecha_eje = time;
	}
	
	public String getFecha_eje() {
		return fecha_eje.format(f);
	}
		
	public String getProcedencia() {
		return proce_desti;
	}

	public void setProcedencia(String procedesti) {
		this.proce_desti = procedesti;
	}
	
	public void setPrioridad(String prio){
		this.prioridad = prio;
	}
	
	public String getPrioridad() {
		return this.prioridad;
	}
	
	@Override
	public String toString() {
		return this.codigo_avion+" "+this.fecha_soli+" "+this.proce_desti;
	}
}
