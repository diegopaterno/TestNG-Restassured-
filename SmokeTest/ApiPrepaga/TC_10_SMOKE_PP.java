package SmokeTest.ApiPrepaga;

import static org.hamcrest.Matchers.equalTo;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_Variables;
import Tools.apiWorker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC_10_SMOKE_PP {
	WebDriver driver;

	public boolean Test(Reports report, DriverManager DM, int iteration, String name, String configEntidad, String cuentas_generales) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
							 + "##################################################################################################################################################################################################################\r\n");
			
			System.out.println("Configuring TC_10_SMOKE_PP \r\n");
			
			//SET VARIABLES
			String path = "./API_Requests/PrePagas/TC_21_Cambio_PIN_Con_Verificacion.txt";
			String token = "";
			String bodyData = "";
			String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
			String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
			String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
			String respBody = "";
			String pinActual = "";
			int pinNuevo;
			String jsonData = "";
			Response response;
			
			//SET INSTANCES
			apiWorker apiResp = new apiWorker();
			Repo_Variables repoVar = new Repo_Variables();
			
			//SET ENVIRONMENT
			//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
			String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
			repoVar.setBaseUri(base_url);
			
			System.out.println("##[command] : <------ Initializating Test: " + name + " ------>\r\n");
			report.AddLine("<------ Initializating Test: " + name + " ------>");

			//GET TOKEN
			try {
				token = apiResp.GetAccessTokenPP(configEntidad);
			} catch (Exception E) {
				report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
				System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
			}
			
			//PRECONDICION
			//PRIMER PASO: EJECUTO LA CONSULTA DE PIN
			System.out.println("\r\nEjecuto una consulta de PIN como precondicion para obtener el PIN actual de la cuenta " + ID_CUENTA + "\r\n");
			report.AddLine("Ejecuto una consulta de PIN como precondicion para obtener el PIN actual de la cuenta " + ID_CUENTA);
			
			//GET - Consulta de PIN actual para luego utilizarlo en el cambio de PIN con verificacion
			if (!token.isEmpty())
			{
				response = apiResp.getMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Pin", token);
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
				//Se valida el status code
				if(response.getStatusCode()!=200) {
					report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
					System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
					MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
				}
				//Se obtiene el body del response
				respBody = response.getBody().asPrettyString();
				//Se muestra response en el reporte
				report.AddLine("Response: " + respBody);
				
				//Se obtienen datos y se informan
				pinActual = JsonPath.from(respBody).get("Data[0].PinActual");
				report.AddLine("La cuenta " + ID_CUENTA + " tiene el numero de PIN: " + pinActual);
				System.out.println("La cuenta " + ID_CUENTA + " tiene el numero de PIN: " + pinActual + "\r\n");
			} else {
				System.out.println("##[warning] : No se obtuvo un token\r\n");
				report.AddLineAssertionError("No se obtuvo un token");
				result = false;
			}
			//Genero siempre un nuevo valor de pin con el actual mas 1
			pinNuevo = Integer.valueOf(pinActual)+1;
			
			report.AddLine("EL nuevo PIN a utilizar es: " + pinNuevo);
			System.out.println("EL nuevo PIN a utilizar es: " + pinNuevo + "\r\n");
			
			//Modifico el archivo json con el PIN actual y el PIN nuevo
			
			jsonData = new String(Files.readAllBytes(Paths.get(path)));
			report.AddLine("Archivo json actual: " + jsonData);
			System.out.println("Archivo json actual: " + jsonData + "\r\n");
			
			//Leo el json actual para obtener el pin actual y pin nuevo para luego reemplazarlos
			String pinActualArchivo = JsonPath.from(jsonData).get("PinActual");
			report.AddLine("El PIN Actual del archivo es: " + pinActualArchivo);
			System.out.println("El PIN Actual del archivo es: " + pinActualArchivo + "\r\n");
			String pinNuevoArchivo = JsonPath.from(jsonData).get("PinNuevo");
			report.AddLine("El PIN Nuevo del archivo es: " + pinNuevoArchivo);
			System.out.println("El PIN Nuevo del archivo es: " + pinNuevoArchivo + "\r\n");
			
			//Reemplazo los valores actuales por los valores de PIN de la cuenta actual y el valor del PIN nuevo
			String jsonDataModif = jsonData.replace("\"PinActual\": \"" + pinActualArchivo + "\"","\"PinActual\": \"" + pinActual + "\"");			
			jsonDataModif = jsonDataModif.replace("\"PinNuevo\": \"" + pinNuevoArchivo + "\"","\"PinNuevo\": \"" + pinNuevo + "\"");
			
			report.AddLine("Se procede a modificar el archivo json actual");
			System.out.println("Se procede a modificar el archivo json actual\r\n");
			report.AddLine("Archivo json actualizado: " + jsonDataModif);
			System.out.println("Archivo json actualizado: " + jsonDataModif + "\r\n");
			
			//Grabo en el archivo el nuevo json generado
			FileWriter archivoNuevo = new FileWriter(path);
			archivoNuevo.write(jsonDataModif);
			archivoNuevo.close();
			
			//SEGUNDO PASO: 
			//YA CON LOS NUMEROS DE PIN OBTENIDOS LOS UTILIZO PARA GENERAR EL CAMBIO DE PIN
			report.AddLine("Se procede a ejecutar la api de cambio de PIN con verificacion");
			System.out.println("Se procede a ejecutar la api de cambio de PIN con verificacion\r\n");
			
			//GET POST BODY FROM FILE
			//Utilizo el nuevo archivo para realizar el camio de PIN
			try {
				bodyData = new String(Files.readAllBytes(Paths.get(path)));	
				
				//Se muestra el body en el reporte
				report.AddLine("Request body:<br>" + bodyData);
				
			} catch (Exception E) {
				report.AddLine("Error al abrir el archivo.<br>Error: " + E);
				System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
			}
					
			//PUT - Cambio de PIN con verificación
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				response = apiResp.putMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Pin", bodyData, token);
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()) + " - El PIN se actualizo correctamente");
				System.out.println("\r\n##[section]: Status Code: " + String.valueOf(response.getStatusCode()) + " - El PIN se actualizo correctamente\r\n");
				//Se valida el status code
				if(response.getStatusCode()!=200) {
					report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
					System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
					MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
				}
				
			} else {
				System.out.println("##[warning] : No se obtuvo un token\r\n");
				report.AddLineAssertionError("No se obtuvo un token");
				result = false;
			}
			
			System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
			report.AddLine("<------ Finished Test: " + name + " ------>\r\n");
			
			//Separador
			System.out.println("##################################################################################################################################################################################################################"
					 + "##################################################################################################################################################################################################################\r\n");
		
						
		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}
		//return the test result
		return result;

	}
	
}
