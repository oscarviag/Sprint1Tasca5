package n1exercici5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Serialitzacio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Persona personaEnviada = new Persona("Antonio", "Rodriguez","Garcia",25);		
		Serialitzacio.serialitzar(personaEnviada, "C:\\Users\\kart_\\Desktop\\Prova", "serie.ser");
		System.out.println(personaEnviada);	
		
		System.out.println("-----------");	
		
		Persona personaRetornada = Serialitzacio.desserialitzar("C:\\Users\\kart_\\Desktop\\Prova", "serie.ser");
		System.out.println(personaRetornada);
	}
	
	public static void serialitzar(Persona personaSer, String directori, String arxiu) {
		
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			
			fileOutputStream = new FileOutputStream(directori + File.separator + arxiu);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(personaSer);
			
			
		} catch (IOException e) {
			
			System.err.println("Error de serialització");

		} finally {
			if (objectOutputStream != null) {
				try {
					
					objectOutputStream.close();
				} catch (IOException e) {
					
					System.err.println("Error al tancar objectOutputStream");
				}
			}
		}
		
	}
	
	public static Persona desserialitzar(String directori, String arxiu) {
		
		Persona personaDes = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		try {
			fileInputStream = new FileInputStream(directori + File.separator + arxiu);
			objectInputStream = new ObjectInputStream(fileInputStream);
			personaDes = (Persona) objectInputStream.readObject(); 
			

			
		} catch (IOException e) {
			
			System.err.println("Error de desserialitzacio");
			
		} catch (ClassNotFoundException e) {
			
			System.err.println("Classe no trobada");
		} finally {
			if (objectInputStream != null) {
				try {
					
					objectInputStream.close();
				} catch (IOException e) {
					System.err.println("Error al tancar objectInputStream");
				}
			}
		}
		return personaDes;
	}

}

// --------------------------------------------------------------

class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String cognom1;
	private String cognom2;
	private int edat;
	
	public Persona(String nom, String cognom1, String cognom2, int edat) {
		super();
		this.nom = nom;
		this.cognom1 = cognom1;
		this.cognom2 = cognom2;
		this.edat = edat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom1() {
		return cognom1;
	}

	public void setCognom1(String cognom1) {
		this.cognom1 = cognom1;
	}

	public String getCognom2() {
		return cognom2;
	}

	public void setCognom2(String cognom2) {
		this.cognom2 = cognom2;
	}

	public int getEdat() {
		return edat;
	}

	public void setEdad(int edat) {
		this.edat = edat;
	}
	
	@Override
	public String toString() {
		return "Nom: " + nom + ", Primer Cognom: " + cognom1 + ", Segon Cognom: " + cognom2 + ", Edat: " + edat;
		
		
	}
	
}

