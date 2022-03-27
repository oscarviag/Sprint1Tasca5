package n1exercici4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Exercici4 {
	
	static ArrayList<String> llistaContingut = new ArrayList<>();
	
	public static void main(String[] args) {
		
		lectura("C:\\Users\\kart_\\Desktop\\Prova\\prova.txt");

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
	
	public static void lectura(String nomArxiu) {
		
		String contingut;
		FileReader arxiu =null;
		BufferedReader lecturaArxiu=null;
		
		try {
			arxiu = new FileReader(nomArxiu);
			lecturaArxiu = new BufferedReader(arxiu);
			
			while ((contingut = lecturaArxiu.readLine())!=null) {
					   System.out.println(contingut);
					}
			lecturaArxiu.close();
			arxiu.close();
			System.out.println("\nArxiu llegit ");
			
		} catch (IOException e) {
			
			System.out.println("Arxiu no disponible");
			e.printStackTrace();
		}
	
	}
	
}
