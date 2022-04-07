import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

import javax.swing.JOptionPane;

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
		Avion nuevo_avion = pedirDatos("Procedencia");
		
		if(nuevo_avion.getPrioridad().equals("s"))
			aterri_prioridad.add(nuevo_avion);
		else
			aterrizaje.add(nuevo_avion);
	}
	
	public void soliDespegue() {
		Avion nuevo_avion = pedirDatos("Destino");
		
		despege.add(nuevo_avion);
	}
	
	public void ejecutarSolicitud() {
		if(!aterri_prioridad.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Hay aviones con prioridad de aterrizaje");
			opcion(aterri_prioridad,aterri_prioridad.peek(),"Aterrizaje de emergencia");
			
		}else {
			if(alter == 1 && !despege.isEmpty()) {
				opcion(despege, despege.peek(), "despegar");
				
				if(!aterri_prioridad.isEmpty())
					alter = 0;
			}else if(alter == 0 && !aterrizaje.isEmpty()) {
				opcion(aterrizaje, aterrizaje.peek(), "aterrizar");
				if(!despege.isEmpty())
					alter = 1;
			}else if(alter == 1 && aterrizaje.isEmpty() && !despege.isEmpty()) {
				opcion(despege, despege.peek(), "despegar");
			}else if(alter == 1 && !aterrizaje.isEmpty() && despege.isEmpty()) {
				opcion(aterrizaje, aterrizaje.peek(), "aterrizar");
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
		
	public Avion pedirDatos(String prodes) {
		
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
		return new Avion(cod,tiempo.toString(),procedencia,prio);
		
	}
	
}
