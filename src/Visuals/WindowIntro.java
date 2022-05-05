package Visuals;

import App.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowIntro implements ActionListener {
	private JFrame frame;
	private Aeropuerto miAeropuerto;
	private Registro miArchivo;
	
	public WindowIntro(Aeropuerto miAero, Registro regis) {
		this.miAeropuerto = miAero;
		this.miArchivo = regis;
		mostrarMenu();
	}
	
	/* Metodo que muestra el menu principal de la aplicacion */
	public void mostrarMenu() {
		
		frame = new JFrame();
		frame.setTitle("Aeropuerto Barajas");
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 1140, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		frame.add(panel);
		
		JButton btnAterrizaje = new JButton("Solicitud de Aterrizaje");
		btnAterrizaje.addActionListener(this);
		btnAterrizaje.setActionCommand("Aterrizar");
		panel.add(btnAterrizaje);
		
		JButton btnDespegue = new JButton("Solicitud de Despegue");
		btnDespegue.addActionListener(this);
		btnDespegue.setActionCommand("Despegar");
		panel.add(btnDespegue);
		
		JButton btnAutorizar = new JButton("Autorizar Solicitud");
		btnAutorizar.addActionListener(this);
		btnAutorizar.setActionCommand("Autorizar");
		panel.add(btnAutorizar);
		
		JButton btnRegistro = new JButton("Registro del dia");
		btnRegistro.addActionListener(this);
		btnRegistro.setActionCommand("Registro");
		panel.add(btnRegistro);
		
		JButton btnSalir = new JButton("Salir del sistema");
		btnSalir.addActionListener(this);
		btnSalir.setActionCommand("Salir");
		panel.add(btnSalir);
		
		frame.setVisible(true);
		
		
		
	}
	
	/* Metodo que recoge la accion realizada por el usuario */
	public void actionPerformed(ActionEvent click) {
		switch(click.getActionCommand()) {
		
			case "Aterrizar":
				miAeropuerto.soliAterrizaje();
				break;
				
			case "Despegar":
				miAeropuerto.soliDespegue();
				break;
				
			case "Autorizar":
				miAeropuerto.ejecutarSolicitud();
				break;
				
			case "Registro":
				miArchivo.abrirarchivo();
				break;
				
			case "Salir":
				frame.dispose();
				break;
				
			
		}
	}
}
