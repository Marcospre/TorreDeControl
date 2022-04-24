package Visuals;
import javax.swing.*;

import App.Avion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Queue;
import java.awt.*;	

public class DatosAterrizaje implements ActionListener{
		
		JFrame ventana = new JFrame("Solicitud");
		
		private JButton aceptar = new JButton("Aceptar");
		private JButton cancelar = new JButton("Cancelar");
		private JTextField txtCodigo = new JTextField();
		private JTextField txtProdes = new JTextField();
		private String prio = null;
		private String prodes = null;
		private JRadioButton rb1;
		private JRadioButton rb2;
		private Queue<Avion> aterrizaje;
		private Queue<Avion> aterri_prioridad;
		private Avion nuevoAvion = null;
		private ButtonGroup buttonGroup = new ButtonGroup();
		
		public DatosAterrizaje(String prodes, Queue<Avion> aterrizaje, Queue<Avion> aterri_prioridad ) {
			this.prodes = prodes;
			this.aterrizaje = aterrizaje;
			this.aterri_prioridad = aterri_prioridad;
			mostrarMenuAterrizaje();
		}
		
		public void mostrarMenuAterrizaje() {
			
			ventana.setBounds(100, 100, 700, 300);
			ventana.getContentPane().setLayout(new GridLayout(5, 2, 10, 10));
			ventana.setLocationRelativeTo(null);

			JLabel labelCodigo = new JLabel("Codigo:");
			ventana.getContentPane().add(labelCodigo);
			ventana.getContentPane().add(txtCodigo);
			
			JLabel labelProdes = new JLabel(this.prodes+":");
			ventana.getContentPane().add(labelProdes);
			ventana.getContentPane().add(txtProdes);
			
			JLabel labelPrioridad = new JLabel("Prioridad");
			ventana.getContentPane().add(labelPrioridad);
				
			JPanel panelPrioridad = new JPanel();
			ventana.getContentPane().add(panelPrioridad);
			panelPrioridad.setLayout(new GridLayout(0,2,0,0));
			
			
			rb1 = new JRadioButton("Si",false);
			rb1.setActionCommand("s");
			buttonGroup.add(rb1);
			panelPrioridad.add(rb1);
			
			rb2 = new JRadioButton("No",true);
			rb1.setActionCommand("n");
			buttonGroup.add(rb2);
			panelPrioridad.add(rb2);
			
			aceptar.addActionListener(this);
			aceptar.setActionCommand("Aceptar");
			ventana.getContentPane().add(aceptar);
			
			cancelar.addActionListener(this);
			cancelar.setActionCommand("Cancelar");
			ventana.getContentPane().add(cancelar);
			
			ventana.setVisible(true);
		}
		
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
					
					for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			            AbstractButton button = buttons.nextElement();

			            if (button.isSelected()) {
			                prio = button.getText();
			            }
			        }
					//prio = buttonGroup.getSelection().getActionCommand();
					
					nuevoAvion = new Avion(cod,tiempo.toString(),procedencia,prio);
					
					if(nuevoAvion.getPrioridad().equals("s"))
						aterri_prioridad.add(nuevoAvion);
					else 
						aterrizaje.add(nuevoAvion);
					
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

