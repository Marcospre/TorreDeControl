package App;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.time.LocalDate;


public class Registro {
	String titulo;
	String url;
	File file = null;
	FileWriter fichero_escri = null;
	BufferedWriter bw = null;
	
	

	//BufferedReader entrada = null:
	public Registro(String url) {
		this.url = url;
		borrarRegistro();
		escribirTitulo();
	}

	public void escribirFichero(Avion avion, String ope) {
		PrintWriter salida = null;
		
		try {
			fichero_escri = new FileWriter(this.url,true);
			salida = new PrintWriter(fichero_escri);
			salida.println(avion.getCodigo_avion()+" "+String.format("%-25s",ope)+" "+avion.getFecha_eje());
			salida.flush();
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			salida.close();
		}
		
	}
	
	public void escribirTitulo() {
		
		PrintWriter salida = null;
		LocalDate hoy = LocalDate.now();
		String m ="Operaciones del dia "+hoy.toString()+"\n";
		
		try {
			fichero_escri = new FileWriter(this.url,true);
			salida = new PrintWriter(fichero_escri);
			salida.println(m);
			salida.flush();
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			salida.close();
		}
	}
	
	public void borrarRegistro() {
		try {
			 
			bw = new BufferedWriter(new FileWriter(this.url));
			bw.write("");
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void abrirarchivo(){

	     try {

	            File objetofile = new File (url);
	            Desktop.getDesktop().open(objetofile);

	     }catch (IOException ex) {

	            System.out.println(ex);

	     }

	}  
}
