package App;
import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

import javax.swing.JOptionPane;

import Visuals.Autorizar;
import Visuals.AutorizarPrioridad;
import Visuals.DatosAterrizaje;
import Visuals.DatosDespege;

public class Aeropuerto {
	String nombre;
	int alter = 1;
	Registro mi_regis;
	DateTimeFormatter f = DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss");
	
	Queue<Avion> aterrizaje;
	Queue<Avion> despege;
	Queue<Avion> aterri_prioridad;
	
	public Aeropuerto(String nombre, Registro regis) {
		this.nombre = nombre;
		this.aterrizaje = new LinkedList<Avion>();
		this.despege = new LinkedList<Avion>();
		this.aterri_prioridad = new LinkedList<Avion>();
		this.mi_regis = regis;
	}
	
	public void soliAterrizaje() {
		DatosAterrizaje nuevo_avion = new DatosAterrizaje("Procedencia", aterrizaje, aterri_prioridad);
		
	}
	
	public void soliDespegue() {
		DatosDespege nuevo_avion = new DatosDespege("Destino", despege);

	}
	
	public void ejecutarSolicitud() {
		if(!aterri_prioridad.isEmpty()) {
			/*JOptionPane.showMessageDialog(null, "Hay aviones con prioridad de aterrizaje");
			opcion(aterri_prioridad,aterri_prioridad.peek(),"Aterrizaje de emergencia");*/
			AutorizarPrioridad ventana_prio = new AutorizarPrioridad(aterri_prioridad,mi_regis); 
			
		}else {
			if(alter == 1 && !despege.isEmpty()) {
				//opcion(despege, despege.peek(), "despegar");
				Autorizar ventana_des = new Autorizar(despege,mi_regis,"despegar"); 
				
				if(!aterri_prioridad.isEmpty())
					alter = 0;
			}else if(alter == 0 && !aterrizaje.isEmpty()) {
				//opcion(aterrizaje, aterrizaje.peek(), "aterrizar");
				Autorizar ventana_ate = new Autorizar(aterrizaje,mi_regis,"aterrizar"); 
				if(!despege.isEmpty())
					alter = 1;
			}else if(alter == 1 && aterrizaje.isEmpty() && !despege.isEmpty()) {
				//opcion(despege, despege.peek(), "despegar");
				Autorizar ventana_des = new Autorizar(despege,mi_regis,"despegar"); 
			}else if(alter == 1 && !aterrizaje.isEmpty() && despege.isEmpty()) {
				//opcion(aterrizaje, aterrizaje.peek(), "aterrizar");
				Autorizar ventana_ate = new Autorizar(aterrizaje,mi_regis,"aterrizar"); 
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
		
	/*public Avion pedirDatos(String prodes) {
		DatosVisual nuevoAvion = new DatosVisual(prodes);
		
		LocalDateTime tiempo = LocalDateTime.now();
		String cod = JOptionPane.showInputDialog("Introduce un codigo:");
		String procedencia = JOptionPane.showInputDialog("Introduce "+prodes+":");
		String prio = null;
		if(prodes.equals("Procedencia")) {
			do {
				prio = JOptionPane.showInputDialog("Tienes prioridad:(s/n)");
			}while(!prio.equals("s") && !prio.equals("n"));
		}else
			prio = null;
		return nuevoAvion.getAvion();
		
	}*/
	
}
