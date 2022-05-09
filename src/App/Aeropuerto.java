package App;
import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

import javax.swing.JOptionPane;

import Cola.Cola;
import Visuals.Autorizar;
import Visuals.AutorizarPrioridad;
import Visuals.DatosAterrizaje;
import Visuals.DatosDespege;

public class Aeropuerto {
	String nombre;
	int alter = 1;
	Registro mi_regis;
	DateTimeFormatter f = DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss");
	
	Cola<Avion> aterrizaje;   /* cola con los aviones a aterrizar */
	Cola<Avion> despege;      /* cola con los aviones a despegar */
	Cola<Avion> aterri_prioridad;   /* cola con los aviones con prioridad */
	
	public Aeropuerto(String nombre, Registro regis) {
		this.nombre = nombre;
		this.aterrizaje = new Cola<Avion>();
		this.despege = new Cola<Avion>();
		this.aterri_prioridad = new Cola<Avion>();
		this.mi_regis = regis;
	}
	
	/* Metodo que abre la intefaz para recoger los datos del avion que va aterrizar */
	public void soliAterrizaje() {
		DatosAterrizaje nuevo_avion = new DatosAterrizaje("Procedencia", aterrizaje, aterri_prioridad);
		
	}
	
	/* Metodo que abre la interfaz para recoger los datos del avion a despegar */
	public void soliDespegue() {
		DatosDespege nuevo_avion = new DatosDespege("Destino", despege);

	}
	
	/* Metodo que ejecuta la solicitud de autorizar un aterrizaje o despegue.
	 * El metodo abrira la interfaz de autorizar o autorizarPrioridad dependiendo
	 * de si el avion tiene prioridad o no. Ademas ira alternando entre las colas de aterrizaje 
	 * y despegue
	 */
	public void ejecutarSolicitud() {
		if(!aterri_prioridad.isEmpty()) {
			AutorizarPrioridad ventana_prio = new AutorizarPrioridad(aterri_prioridad,mi_regis); 
			
		}else {
			if(alter == 1 && !despege.isEmpty()) {
				Autorizar ventana_des = new Autorizar(despege,mi_regis,"despegar"); 
				
				if(!aterrizaje.isEmpty())
					alter = 0;
			}else if(alter == 0 && !aterrizaje.isEmpty()) {
				Autorizar ventana_ate = new Autorizar(aterrizaje,mi_regis,"aterrizar"); 
				if(!despege.isEmpty())
					alter = 1;
			}else if(alter == 0 && aterrizaje.isEmpty() && !despege.isEmpty()) {
				Autorizar ventana_des = new Autorizar(despege,mi_regis,"despegar"); 
				alter = 1;
			}else if(alter == 1 && !aterrizaje.isEmpty() && despege.isEmpty()) {
				Autorizar ventana_ate = new Autorizar(aterrizaje,mi_regis,"aterrizar"); 
				alter = 0;
			}else if(despege.isEmpty() && aterrizaje.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No quedan mas solicitudes");
			}
		}
	}
	
	public void opcion(Queue<Avion> cola,Avion avion, String ope) {
		String mp;
		String op = null;
		
			if(ope.equals("aterrizar") || ope.equals("Aterrizaje de emergencia")) {
				 mp = "El avion con codigo "+avion.getCodigo_avion()+" procedente de "+avion.getProcedencia()+" desea "+ope+"\n"
							+"Desea ejecutar la ope(s/n):";
			}else {
				 mp = "El avion con codigo "+avion.getCodigo_avion()+" con destino a "+avion.getProcedencia()+" desea "+ope+"\n"
						+"Desea ejecutar la ope(s/n):";
			}
			String mf = "Aeropueto "+this.nombre+"\n";
			
			do {
				op = JOptionPane.showInputDialog(mp);
			}while(!op.equals("s") && !op.equals("n"));
			
			if(op.equals("s")) {
				LocalDateTime tiempo_ope = LocalDateTime.now();
				
				avion.setfecha_eje(tiempo_ope);
				mf= mf + "Operacion realizada: "+ope+"\n";
				mf= mf + "Hora operacion:         "+tiempo_ope.format(f)+"\n";		
				mf= mf + "Codigo avion:              "+avion.getCodigo_avion()+"\n";
				
				mi_regis.escribirFichero(avion,ope);
				
				cola.remove();
				
				JOptionPane.showMessageDialog(null, mf);
			}
				
	}
	
}
