package n2exercici1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class Exercici1 {
	
	static ArrayList<String> llistaContingut = new ArrayList<>();
	
	public static void main(String[] args) {
		
		ArrayList<String> llistaDirectoris = new ArrayList<String>();
		Properties properties= new Properties();
		InputStream input=null;
		
		try {
		      input = new FileInputStream("C:\\Users\\kart_\\eclipse-workspace\\Sprint1Tasca5\\src\\n2exercici1\\configuracio.properties");
		      properties.load(input);
		      
		      System.out.println("Ruta parametritzada de Directori a llegir: " + properties.get("DLLEGIR"));
		      System.out.println("Nom parametritzat de l'arxiu a crear: " + properties.get("FITXER"));
		      System.out.println("Ruta parametritzada de Directori on guardar l'arxiu: " + properties.get("DGUARDAR"));
		      System.out.println();
		      llistaDirectoris=Exercici1.llistar((String) properties.get("DLLEGIR"));
		      Exercici1.EscriureArxiu((String)properties.get("DGUARDAR"), (String) properties.get("FITXER"), llistaDirectoris);

		    } catch (FileNotFoundException e) {
		      
		    	System.out.println("Arxiu no trobat");
		    	e.printStackTrace();
		      
		    } catch (IOException e) {
		      
		      e.printStackTrace();
		    }
		
		
	}
	
	public static  ArrayList<String> llistar(String ruta) {
		
		File directori = new File(ruta);
		File subdirectori;
		SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");		
		String[] llista;		
		ArrayList<String> llistaOrdenada = new ArrayList<>();
		
		if (directori.exists()) {
			
			llista = directori.list();
			Collections.addAll(llistaOrdenada, llista);
			Collections.sort(llistaOrdenada, String.CASE_INSENSITIVE_ORDER);
						
			for (String element:llistaOrdenada) {
				
				subdirectori = new File(directori,element);
								
				if (subdirectori.isDirectory()) {

					llistaContingut.add(element+ " D " + data.format(subdirectori.lastModified()));
					
					llistar(directori + File.separator + element);
					
				} else {
				
				llistaContingut.add(element + " F " + data.format(subdirectori.lastModified()));
				
				}				
			}
			
		} else {
			System.out.println("El directori especificat no existeix");
		}
		
		return llistaContingut;
			
	}
	
	public static void CrearArxiu(String ruta, String nomArxiu) {
		
		File arxiu = new File(ruta + File.separator + nomArxiu);
		
		try {
			
			arxiu.createNewFile();
			System.out.println("Arxiu creat");
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void EscriureArxiu(String ruta, String nomArxiu, ArrayList<String> dades) {
		
		ArrayList<String> llistat = dades;
		CrearArxiu(ruta, nomArxiu);
		BufferedWriter bufferedWriter = null;
		FileWriter escriptura=null;
		
		try {
			
			escriptura = new FileWriter(ruta + File.separator + nomArxiu); 
			bufferedWriter = new BufferedWriter(escriptura);
			
			for (String lineas : llistat) {
				bufferedWriter.write(lineas+ System.lineSeparator());
			}
			bufferedWriter.close();
			escriptura.close();
			System.out.println("Arxiu guardat amb les dades");
			
		} catch (IOException e) {
			
			System.out.println("Arxiu no disponible");
			e.printStackTrace();
		}
				
	}
	
	
	
}
