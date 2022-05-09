package Visuals;
import javax.swing.*;

import App.Avion;
import Cola.Cola;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Queue;
import java.awt.*;	

public class DatosDespege implements ActionListener{
		
		JFrame ventana = new JFrame("Solicitud");
		
		private JButton aceptar = new JButton("Aceptar");
		private JButton cancelar = new JButton("Cancelar");
		private JTextField txtCodigo = new JTextField();
		private JTextField txtProdes = new JTextField();
		private String prodes = null;
		private Cola<Avion> despege;
		private Avion nuevoAvion = null;
		
		public DatosDespege(String prodes, Cola<Avion> despege ) {
			this.prodes = prodes;
			this.despege = despege;
			mostrarMenuDespege();
		}
		
		/* Metodo que muestra la interfaz donde se va a recoger la informacion 
		 * del avion que ca a despegar.
		 */
		public void mostrarMenuDespege() {
				
				ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ventana.setBounds(100, 100, 700, 300);
				ventana.getContentPane().setLayout(new GridLayout(5, 2, 10, 10));
				ventana.setLocationRelativeTo(null);
	
				JLabel labelCodigo = new JLabel("Codigo:");
				ventana.getContentPane().add(labelCodigo);
				ventana.getContentPane().add(txtCodigo);
				
				JLabel labelProdes = new JLabel(this.prodes+":");
				ventana.getContentPane().add(labelProdes);
				ventana.getContentPane().add(txtProdes);
				
				aceptar.addActionListener(this);
				aceptar.setActionCommand("Aceptar");
				ventana.getContentPane().add(aceptar);
				
				cancelar.addActionListener(this);
				cancelar.setActionCommand("Cancelar");
				ventana.getContentPane().add(cancelar);
				
				ventana.setVisible(true);
			}
			
			/* Metodo que recoge la accion que se ha ejecutado en el menu.
			 * Si se acepta, se recogen todos los datos y se crea un nuevo avion.
			 * Despues, se añade a la cola de despegue.
			 */
			public void actionPerformed(ActionEvent click) {
				String cod = null;
				String procedencia = null;
				
				switch(click.getActionCommand()) {
				case "Aceptar":
					try {
						LocalDateTime tiempo = LocalDateTime.now();
						cod = txtCodigo.getText();
						procedencia = txtProdes.getText();
						
						if(cod.equals("") || procedencia.equals("")) {
							throw new Exception("Rellene todos los campos");
						}
						
						nuevoAvion = new Avion(cod,tiempo.toString(),procedencia,null);
						despege.add(nuevoAvion);
						
						ventana.dispose();
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
					break;
				
				case "Cancelar":
					ventana.dispose();
					break;
					
				}
			}

	}


