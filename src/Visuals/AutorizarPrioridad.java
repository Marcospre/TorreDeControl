package Visuals;

import App.Avion;
import App.Registro;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;




public class AutorizarPrioridad implements ActionListener {
	
	JFrame ventana = new JFrame("Avion con prioridad");
	private Queue<Avion> aterri_prioridad;
	private JButton aceptar = new JButton("Aceptar");
	private JButton cancelar = new JButton("Cancelar");
	private Avion avion = null;
	private String ope;
	private Registro mi_regis;
	DateTimeFormatter f = DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss");
	
	public AutorizarPrioridad(Queue<Avion> aterri_prioridad, Registro regis) {
		this.aterri_prioridad = aterri_prioridad;
		avion = aterri_prioridad.peek();
		ope = "Aterrizaje de emergencia";
		this.mi_regis = regis;
		menuAutori();
	}
	
	public void menuAutori() {
		
		ventana.setBounds(100, 100, 700, 300);
		ventana.getContentPane().setLayout(new GridLayout(5, 2, 10, 10));
		ventana.setLocationRelativeTo(null);
		
		JLabel descrip1 = new JLabel("El avion con codigo "+avion.getCodigo_avion()+" procedente de "+avion.getProcedencia()+" desea Aterrizaje de emergencia");
		ventana.getContentPane().add(descrip1);
		
		JLabel descrip2 = new JLabel("Desea ejecutar la operacion");
		ventana.getContentPane().add(descrip2);
		
		aceptar.addActionListener(this);
		aceptar.setActionCommand("Aceptar");
		ventana.getContentPane().add(aceptar);
		aceptar.setBackground(Color.LIGHT_GRAY);
		
		cancelar.addActionListener(this);
		cancelar.setActionCommand("Cancelar");
		ventana.getContentPane().add(cancelar);
		cancelar.setBackground(Color.LIGHT_GRAY);
		
		ventana.setVisible(true);
		
		
		
	}
	
	public void actionPerformed(ActionEvent click) {
		switch(click.getActionCommand()) {
		case "Aceptar":
			
			LocalDateTime tiempo_ope = LocalDateTime.now();
			avion.setfecha_eje(tiempo_ope);
			String mf = "Aeropueto \n";
			mf= mf + "Operacion realizada: "+ope+"\n";
			mf= mf + "Hora operacion:         "+tiempo_ope.format(f)+"\n";		
			mf= mf + "Codigo avion:              "+avion.getCodigo_avion()+"\n";
			
			ventana.dispose();
			JFrame ventanainfo = new JFrame("Informacion");
			ventanainfo.setBounds(100, 100, 700, 300);
			ventanainfo.setLocationRelativeTo(null);
			JTextArea descrip = new JTextArea(mf);
			descrip.setBounds(100, 100, 700, 300);
			ventanainfo.getContentPane().add(descrip);
			ventanainfo.setVisible(true);
			
			mi_regis.escribirFichero(avion,ope);
			
			aterri_prioridad.remove();
			
			break;
			
		case "Cancelar":
			ventana.dispose();
			break;
			
		}
	}
	
}
