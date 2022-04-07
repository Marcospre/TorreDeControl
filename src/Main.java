
public class Main {
	
	static Registro miArchivo = new Registro("registro.txt");
	static Aeropuerto miAeropuerto = new Aeropuerto("Barajas",miArchivo);
	static Menu miMenu = new Menu(miAeropuerto, miArchivo);
	public static void main(String[] args) {
		
		try {
			do {
				miMenu.mostrarMenu();
				miMenu.elejirOpcion(miAeropuerto);
			}while(!miMenu.getSalir());
		}catch(Exception e) {
			System.out.println("Introduce una opcion valida");
		}
	}
}
