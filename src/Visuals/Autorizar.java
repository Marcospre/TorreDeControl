package Visuals;

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
import javax.swing.JTextArea;

import App.Avion;
import App.Registro;
import Cola.Cola;

public class Autorizar implements ActionListener {
	JFrame ventana;
	private Cola<Avion> cola;
	private JButton aceptar = new JButton("Aceptar");
	private JButton cancelar = new JButton("Cancelar");
	private Avion avion = null;
	private String ope;
	private Registro mi_regis;
	DateTimeFormatter f = DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss");
	
	public Autorizar(Cola<Avion> cola, Registro regis, String ope) {
		ventana = new JFrame(ope);
		this.cola = cola;
		avion = cola.peek();
		this.ope = ope;
		this.mi_regis = regis;
		menuAutori();
	}
	
	/* Metodo que muestra por pantalla la interfaz del menu */
	public void menuAutori() {
		
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ventana.setBounds(100, 100, 700, 300);
			ventana.getContentPane().setLayout(new GridLayout(5, 2, 10, 10));
			ventana.setLocationRelativeTo(null);
			if(ope.equals("aterrizar")) {
				JLabel descrip1 = new JLabel("El avion con codigo "+avion.getCodigo_avion()+" procedente de "+avion.getProcedencia()+" desea "+ope);
				ventana.getContentPane().add(descrip1);
			}else {
				JLabel descrip1 = new JLabel("El avion con codigo "+avion.getCodigo_avion()+" con destino a "+avion.getProcedencia()+" desea "+ope);
				ventana.getContentPane().add(descrip1);
			}
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
	
	/* Metodo que registra la accion que se ha ejecutado en el menu.
	 * Si se acepta se eliminara el avion de la cola y mostrar un JText 
	 * con la informacion de la operacion que ha realizado el avion. Tambien
	 * se escribe toda esta informacion en el archivo de texto.
	 */
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
			
			cola.remove();
			
			break;
			
		case "Cancelar":
			ventana.dispose();
			break;
			
		}
	}
	
}
