package Tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import io.restassured.path.json.JsonPath;

public class BodyHelper {
	
	// Variables privadas del calculo de CUIL
	private static int dniStc; 
	private static int xyStc; 
	private static int digitoStc; 
	
	//genero un metodo para generar un request id diferente x cada vez PAT
		public static int generarRequestId() {
		    Random rand = new Random();
		    int requestId = rand.nextInt(1000000); // Genera un número aleatorio entre 0 y 999999
		    return requestId;
		}

		//*********************PAT
	
	public String generadorBodyDocCuil(String bodyData) throws IOException {
		//Variables
		String bodyDataModif;

		String cuilViejo = JsonPath.from(bodyData).get("DocumentoOtro");
		String documentoViejo = JsonPath.from(bodyData).get("Documento.Numero");

		String documentoNuevo = editorDocumento(documentoViejo);;
		String cuilNuevo = generadorCuil(documentoNuevo);

		// Trabajamos con el body y medificamos los parametros antiguos por los nuevos
		bodyDataModif = bodyData.replace("\"Numero\": \"" + documentoViejo + "\"","\"Numero\": \"" + documentoNuevo + "\"");
		bodyDataModif = bodyDataModif.replace("\"DocumentoOtro\": \"" + cuilViejo + "\"","\"DocumentoOtro\": \"" + cuilNuevo + "\"");
		
		return bodyDataModif;
	}
	
	public boolean grabaBodyFile(String path, String newBody) throws IOException {
		// Grabacion del nuevo archivo editado
		try {
			FileWriter archivo = new FileWriter(path);
			archivo.write(newBody);
			archivo.close();
			System.out.println("Archivo del body editado");
			return true;
		} catch (IOException e) {
			System.out.println("Error en la edicion del body");
			e.printStackTrace();
			return false;
		}
	}

	// Metodo de carga de cuil
	private String generadorCuil(String dni) {
		String cuil = generar(Integer.parseInt(dni), 'M');
		return cuil;
	}

	// Metodo que aumenta en uno el numero del documento
	private String editorDocumento(String dni) {
		String valor = Integer.toString(Integer.parseInt(dni)+1);
		return valor;
	}

	// ------------------------------ CONJUNTO DE METODOS QUE CALCULAN EL CUIL ------------------------------
	private static String generar(int dniInt, char xyChar) { 
		if (xyChar == 'F' || xyChar == 'f') 
			xyStc = 27; 
		else if (xyChar == 'M' || xyChar == 'm') 
			xyStc = 20; 
		else 
			xyStc = 30; 
		dniStc = dniInt; 
		calcular(); 
		return formatear(); 
	}

	/** Metodo privado q calcula el CUIT **/ 
	private static void calcular() { 
		long tmp1, tmp2; 
		long acum = 0; 
		int n = 2; 
		tmp1 = xyStc * 100000000L + dniStc; 
		for (int i = 0; i < 10; i++) { 
			tmp2 = tmp1 / 10; 
			acum += (tmp1 - tmp2 * 10L) * n; 
			tmp1 = tmp2; 
			if (n < 7) 
				n++; 
			else 
				n = 2; 
		} 
		n = (int) (11 - acum % 11); 
		if (n == 10) { 
			if (xyStc == 20 || xyStc == 27 || xyStc == 24) 
				xyStc = 23; 
			else 
				xyStc = 33;
			calcular(); 
		} else { 
			if (n == 11) 
				digitoStc = 0; 
			else 
				digitoStc = n; 
		} 
	} 

	/** Metodo privado q da formato al CUIT como String **/ 
	private static String formatear() { 
		return String.valueOf(xyStc) + completar(String.valueOf(dniStc)) + String.valueOf(digitoStc); 
	} 

	/** Metodo privado q completa con ceros el DNI para q quede con 8 digitos **/ 
	private static String completar(String dniStr) { 
		int n = dniStr.length(); 
		while (n < 8) { 
			dniStr = "0" + dniStr; 
			n = dniStr.length(); 
		} 
		return dniStr; 
	} 

}
