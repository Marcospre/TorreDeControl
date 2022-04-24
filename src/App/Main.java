package App;

import Visuals.WindowIntro;

public class Main {
	
	public static void main(String[] args) {
		Registro miArchivo = new Registro("registro.txt");
		Aeropuerto miAeropuerto = new Aeropuerto("Barajas",miArchivo);
		WindowIntro miMenu = new WindowIntro(miAeropuerto, miArchivo);
	}
}
