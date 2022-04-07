 import javax.swing.JOptionPane;

public class Menu {
	int op;
	Boolean salir = false;
	Aeropuerto miAeropuerto;
	Registro miArchivo;
	
	public Menu(Aeropuerto aero, Registro regis) {
		this.miAeropuerto = aero;
		this.miArchivo = regis;
	}
	public String mostrarMenu() {
		String men = "Menu de Operaciones del Aeropuerto\n"
					+"1.- Solicitud de permiso para aterrizar\n"
					+"2.- Solicitud de permiso para despegar\n"
					+"3.- Autorizar solicitud\n"
					+"4.- Registro del dia\n"
					+"5.- Salir del sistema\n"
					+"Introduzca una opcion:";
		
		return men;
	}
	public void elejirOpcion(Aeropuerto aero) {
		
		
		
		op = Integer.parseInt(JOptionPane.showInputDialog(mostrarMenu()));
		
		switch(op) {
			case 1: miAeropuerto.soliAterrizaje();
				break;
			
			case 2: miAeropuerto.soliDespegue();
				break;
				
			case 3: miAeropuerto.ejecutarSolicitud();
				break;
			
			case 4: miArchivo.abrirarchivo();
				break;
			case 5:
				salir = true;
				break;
			
			default:break;
			
		}
	}
	
	public int getOp() {
		return op;
	}
	
	public boolean getSalir() {
		return salir;
	}
	
	
}
