package App;

import Visuals.WindowIntro;

public class Main {
	
	/* Metodo main donde se inicia el programa. Creamos un registro, un aeropuerto
	 * y llamamos al menu de la app
	 */
	public static void main(String[] args) {
		Registro miArchivo = new Registro("registro.txt");
		Aeropuerto miAeropuerto = new Aeropuerto("Barajas",miArchivo);
		WindowIntro miMenu = new WindowIntro(miAeropuerto, miArchivo);
	}
}
