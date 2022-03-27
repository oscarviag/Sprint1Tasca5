package n1exercici1;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Exercici1 {
	
	public static void main(String[] args) {
		
		llistar("C:\\");

	}
	
	public static void llistar(String ruta) {
		
		File directori = new File(ruta);
		String[] llista;
		ArrayList<String> llistaOrdenada = new ArrayList<>();
		
		if (directori.exists()) {
			
			llista = directori.list();
			Collections.addAll(llistaOrdenada, llista);
			Collections.sort(llistaOrdenada, String.CASE_INSENSITIVE_ORDER);
			
			for (String element:llistaOrdenada) {
				System.out.println(element);
			}
			
		} else {
			System.out.println("El directori especificat no existeix");
		}		
	}
}
