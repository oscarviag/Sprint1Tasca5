package n1exercici2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Exercici2 {
	
	public static void main(String[] args) {
		
		llistar("C:\\Users\\kart_\\Desktop\\Prova");

	}
	
	public static void llistar(String ruta) {
		
		File directori = new File(ruta);
		File subdirectori;
		SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");		
		ArrayList<String> llistaOrdenada = new ArrayList<>();
		String[] llista;
		
		if (directori.exists()) {
			
			llista = directori.list();
			Collections.addAll(llistaOrdenada, llista);
			Collections.sort(llistaOrdenada, String.CASE_INSENSITIVE_ORDER);
					
			
			for (String element:llistaOrdenada) {
				
				subdirectori = new File(directori,element);
							
				if (subdirectori.isDirectory()) {
					
					System.out.println(element+ " D " + data.format(subdirectori.lastModified()));
					
					llistar(directori+  File.separator + element);
					
				} else {
					
				System.out.println(element + " F " + data.format(subdirectori.lastModified()));
				
				}				
			}
			
		} else {
			System.out.println("El directori especificat no existeix");
		}		
	}
}
