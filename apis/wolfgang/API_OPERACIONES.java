package apis.wolfgang;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;

import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Pasos.Generales.AutorizadorInterno;
import Pasos.Generales.PrePostCondi;
import Repositories.Repo_Variables;
import Tools.apiWorker;
import Tools.dbWorker;
import Tools.opiWorker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.google.gson.*;

public class API_OPERACIONES{
	private List<String> Status = new ArrayList<String>();
	//private String Status;
	String esquema;
	
	// Alta sub comercio
	public boolean altaCuenta(Reports report, String name, String configEntidad, String entidad, String path, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			String token = "";
			String jsonFile = "";
			String bodyData = "";
			String respBody = "";
			String responseData = "";
			boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");

			//SET INSTANCES
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));

			
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, "https://v2entidad9999.api.qa.global.globalprocessing.net.ar/Prepaga/api/Productos/1/Cuentas");

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			//GET TOKEN - NO ES NECESARIO EN AUT API
			//			System.out.println("##### EXTRACCION DE TOKEN #####");
			//			token = AI.getToken(report, apiResp);
			token = getToken(report, apiWorker);
			
			System.out.println(token);

			System.out.println("##### EXTRACCION DE DATA BODY #####");			
			//GET POST BODY from " + esquema + "FILE
			
			bodyData = new String(Files.readAllBytes(Paths.get(path + "/TC.txt")));
			
			System.out.println(bodyData);
			
			//POST - Con token y Body OK
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = AI.post2(report, repoVar, apiWorker, bodyData, token, configEntidad, statusCodeEsperado);

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode que tiene que ser "00"
				//respBody = response.getBody().asPrettyString();
				responseData = JsonPath.from(respBody).getString("Data.IdCuenta");
				
				System.out.println("Este es el ID de Cuenta creado :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response numero de Id Cuenta OK: " + responseData);
					System.out.println("Response numero de Id Cuenta OK: " + responseData + "\r\n");
					//ESPERA PARA EL PROCESAMIENTO DEL REGISTRO EN EL BACKEND
					System.out.println("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND 8 SEGS #####");
					report.AddLine("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND 8 SEGS #####");
					Thread.sleep(8000);
					//VALIDACIONES
					
					//DATABASE - VERIFICACION ALTA DE CUENTA
					if (!responseData.isEmpty()) 
					{
						responseData = oraResp.oraValidaAlta(report, "29456740", configEntidad);
						report.AddLine("Se dio de alta correctamente la cuenta: " + responseData + " impactando en las tablas CUENTAS, SOCIOS, PERSONAS, TARJETAS y TARJETAS_HISTORIAL");
						System.out.println("##[section] : Se dio de alta correctamente la cuenta: " + responseData + " impactando en las tablas CUENTAS, SOCIOS, PERSONAS, TARJETAS y TARJETAS_HISTORIAL\r\n");
					} else {
						report.AddLineAssertionError("Error: No se genero el alta de cuenta");
						System.out.println("##[warning] : Error: No se genero el alta de cuenta\r\n");
						//Si falla la validacion el resultado de la prueba es false
						result = false;
					}
					
					
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					// POSTCONDICIONES
					System.out.println("##### INICIA EJECUCION DE POSTCONDICIONES #####");
					report.AddLine("##### INICIA EJECUCION DE POSTCONDICIONES #####");

					//execPostCondiciones(report, oraResp, rrnLast6);
					//ROLLBACK
					report.AddLine("Se procede a la eliminacion de los datos generados...");
					System.out.println("Se procede a la eliminacion de los datos generados...\r\n");
					
					report.AddLine("--- Ejecutando PostCondicion ---");
					System.out.println("--- Ejecutando PostCondicion ---\r\n");
					
					resp = oraResp.oraBorrarCuenta(report, responseData, "29456740", configEntidad, "1");
					ppCondi.rollBackBorrarId(report, responseData, "29456740", resp);

					System.out.println("##### FIN DE EJECUCION DE POSTCONDICIONES #####");
					report.AddLine("##### FIN DE EJECUCION DE POSTCONDICIONES #####");				
					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	
	public boolean post(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			String doc = "35687555";
			String token = "";
			String jsonFileV = "";
			String bodyData = "";
			String respBody = "";
			String responseData = "";
			boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 	
			//SET INSTANCES
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
			
			
			jsonFileV = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TC.json")));
			
			   
			
            JsonParser parser = new JsonParser();
			
			JsonObject jsonObject = parser.parse(jsonFileV).getAsJsonObject();
			System.out.println("JSON Object: ");
			System.out.println(jsonObject);
			JsonArray validaciones = jsonObject.getAsJsonArray("VALIDACIONESDB");
			
			System.out.println("VALIDACIONES: ");
			System.out.println(validaciones);
			
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			//GET TOKEN - NO ES NECESARIO EN AUT API
			//			System.out.println("##### EXTRACCION DE TOKEN #####");
			//			token = AI.getToken(report, apiResp);
			token = getToken(report, apiWorker);
			
			System.out.println(token);

			System.out.println("##### EXTRACCION DE DATA BODY #####");			
			//GET POST BODY from " + esquema + "FILE
			
			bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));
			
			
			
			System.out.println(bodyData);
			
			//POST - Con token y Body OK
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = AI.post2(report, repoVar, apiWorker, bodyData, token, configEntidad, statusCodeEsperado);

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode que tiene que ser "00"
				//respBody = response.getBody().asPrettyString();
				responseData = JsonPath.from(respBody).getString("Data.IdCuenta");
				
				System.out.println("Este es el ID de Cuenta creado :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response numero de Id Cuenta OK: " + responseData);
					System.out.println("Response numero de Id Cuenta OK: " + responseData + "\r\n");
					//ESPERA PARA EL PROCESAMIENTO DEL REGISTRO EN EL BACKEND
					System.out.println("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND 8 SEGS #####");
					report.AddLine("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND 8 SEGS #####");
					Thread.sleep(8000);
					//VALIDACIONES
					
					
					//SE COMIENZA A VALIDAR
					
					if (!responseData.isEmpty())
					{
						report.AddLine("Ejecucion Correcta<br>ID_CUENTA: " + responseData);
						System.out.println("##[section] : Ejecucion Correcta\r\nID_CUENTA: " + responseData + "\r\n");
						//VALIDACIONES
						
						result = validacionGral(oraResp, report, responseData, entidad, validaciones);
					} else {
						report.AddLineAssertionError("FAIL<br>ID_CUENTA: " + responseData + " Se esperaba: un numero" );
						System.out.println("##[warning] : FAIL : \r\nID_CUENTA: " + responseData + " Se esperaba: un numero");
						result = false;
					}

					
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					// POSTCONDICIONES
					System.out.println("##### INICIA EJECUCION DE POSTCONDICIONES #####");
					report.AddLine("##### INICIA EJECUCION DE POSTCONDICIONES #####");

					//execPostCondiciones(report, oraResp, rrnLast6);
					//ROLLBACK
					report.AddLine("Se procede a la eliminacion de los datos generados...");
					System.out.println("Se procede a la eliminacion de los datos generados...\r\n");
					
					report.AddLine("--- Ejecutando PostCondicion ---");
					System.out.println("--- Ejecutando PostCondicion ---\r\n");
					
					resp = oraResp.oraBorrarCuenta(report, responseData, doc, configEntidad, "1");
					ppCondi.rollBackBorrarId(report, responseData, doc, resp);

					System.out.println("##### FIN DE EJECUCION DE POSTCONDICIONES #####");
					report.AddLine("##### FIN DE EJECUCION DE POSTCONDICIONES #####");				
					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean habilitarTarjetaFisica(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			String doc = "35687555";
			String token = "";
			String jsonFileV = "";
			String bodyData = "";
			String respBody = "";
			String responseData = "";
			//boolean resp;
			int rta = 0;
			Response response;
			String resp = "";
			String producto = "101";
			String referenciaTarjeta = "0346"; 
			String ID_CUENTA = "10001182";
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
			//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
			String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
			repoVar.setBaseUri(base_url);
			
			//Seteo de la tarjeta en estado 3-Embozada para luego habilitarla
			rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 3 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
			ppCondi.preCondicionBD(report, rta);
			//Seteo la cuenta en estado 0-Activa para que no falle el TC
			rta = oraResp.oraUpdate(report, "UPDATE CUENTAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
			ppCondi.preCondicionBD(report, rta);
		
			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");
			token = getToken(report, apiWorker);
			
			System.out.println(token);
		//POST - Con token y Body OK
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN #####");

				// Extraemos el response body y validamos el 200
				
				response = apiWorker.putMethod(repoVar, "/api/Productos/" + producto + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + referenciaTarjeta + "/", "Habilitar", token);
				System.out.println("ESTO SE OBTIENE DE LA RESPUESTA : " + response);
			
				// Extraemos el responseCode que tiene que ser "00"
				respBody = response.getBody().asPrettyString();
				System.out.println("este es el body obtenido :" + respBody);
			
	
					//VALIDACIONES
					resp = oraResp.oraOneQuery(report, "SELECT ID_ESTADO FROM TARJETAS WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
					
					if (!resp.equals("0")) {
						report.AddLineAssertionError("Error<br>El Estado de la tarjeta es: " + resp + " y se esperaba el estado: 0");
						System.out.println("##[warning] : Error<br> El Estado de la tarjeta es: " + resp + " y se esperaba el estado: 0" + "\r\n");
					
					//Si falla la validacion el resultado de la prueba es false
						result = false;
					} else {
						report.AddLine("La tarjeta se habilito correctamente. El nuevo estado de la tarjeta " + referenciaTarjeta + " de la cuenta " + ID_CUENTA + " es: " + resp);
						System.out.println("##[section] : La tarjeta se habilito correctamente. El nuevo estado de la tarjeta " + referenciaTarjeta + " de la cuenta " + ID_CUENTA + " es: " + resp + "\r\n");	
					       }		
				
					
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					// POSTCONDICIONES
					System.out.println("##### INICIA EJECUCION DE POSTCONDICIONES #####");
					report.AddLine("##### INICIA EJECUCION DE POSTCONDICIONES #####");
					
					//POSTCONDICION
					report.AddLine("--- Ejecutando PostCondicion ---");
					System.out.println("--- Ejecutando PostCondicion ---\r\n");
					rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
					
					ppCondi.postCondicionBD(report, rta);
					
					System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
					report.AddLine("<------ Finished Test: " + name + " ------>");
		
					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	                                //validacionGral(oraResp, report, responseData, entidad, validaciones);
	
	public boolean postCrearCarga(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			String jsonFileV = "";
			String bodyData = "";
			String respBody = "";
			String responseData = "";
			String rtaIdPago = "";
			boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 	
			//SET INSTANCES
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
			
			
			jsonFileV = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TC.json")));
			
			   
			
            JsonParser parser = new JsonParser();
			
			JsonObject jsonObject = parser.parse(jsonFileV).getAsJsonObject();
			System.out.println("JSON Object: ");
			System.out.println(jsonObject);
			JsonArray validaciones = jsonObject.getAsJsonArray("VALIDACIONESDB");
			
			System.out.println("VALIDACIONES: ");
			System.out.println(validaciones);
			
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			//GET TOKEN - NO ES NECESARIO EN AUT API
			//			System.out.println("##### EXTRACCION DE TOKEN #####");
			//			token = AI.getToken(report, apiResp);
			token = getToken(report, apiWorker);
			
			System.out.println(token);

			System.out.println("##### EXTRACCION DE DATA BODY #####");			
			//GET POST BODY from " + esquema + "FILE
			
			bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));
			
			
			
			System.out.println(bodyData);
			
			//POST - Con token y Body OK
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = AI.post2(report, repoVar, apiWorker, bodyData, token, configEntidad, statusCodeEsperado);

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode que tiene que ser "00"
				//respBody = response.getBody().asPrettyString();
				responseData = JsonPath.from(respBody).getString("Data.CodigoConfirmacion");
				
				System.out.println("Este es el CodigoConfirmacion otorgado :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response numero de CodigoConfirmacion OK: " + responseData);
					System.out.println("Response numero de CodigoConfirmacion OK: " + responseData + "\r\n");
					//ESPERA PARA EL PROCESAMIENTO DEL REGISTRO EN EL BACKEND
					System.out.println("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND 8 SEGS #####");
					report.AddLine("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND 8 SEGS #####");
					Thread.sleep(8000);
					//VALIDACIONES
					
					System.out.println("Se realizan las validaciones en la BD:\r\n");
					report.AddLine("Se realizan las validaciones en la BD:");
					
					if (responseData != "0") 
					{
						rtaIdPago = oraResp.oraOneQuery(report, "SELECT ID_PAGO FROM PAGOS WHERE ID_PAGO = " + String.valueOf(responseData), configEntidad);
						report.AddLine("ID_PAGO en la base de datos: " + responseData);
						System.out.println("ID_PAGO en la base de datos: " + responseData + "\r\n");
					} else {
						report.AddLineAssertionError("Error:<br>No se obtuvo el ID_PAGO");
						System.out.println("##[warning] : Error:<br>No se obtuvo el ID_PAGO\r\n");
						//Si falla la validacion el resultado de la prueba es false
						result = false;
					}

					//ADD VALIDATIONS
					if (!responseData.equals(String.valueOf(responseData))) {
					
					//rollBack(report, oraResp, ppCondi, rtaIdPago);
						report.AddLineAssertionError("Error:<br>El ID_PAGO generado por la API Crear_Carga_Local es: " + responseData + " es distinto al de la base de datos: " + rtaIdPago);
						System.out.println("##[warning] : Error:<br>El ID_PAGO generado por la API Crear_Carga_Local es: " + responseData + " es distinto al de la base de datos: " + rtaIdPago);
					
					//Si falla la validacion el resultado de la prueba es false
						result = false;
					} else {
						report.AddLine("Validacion exitosa:<br>El ID_PAGO generado por la API Crear Carga Local es: " + responseData + " es igual al de la base de datos: " + rtaIdPago);
						System.out.println("##[section] : Validacion exitosa:\r\nEl ID_PAGO generado por la API Crear Carga Local es: " + responseData + " es igual al de la base de datos: " + rtaIdPago + "\r\n");				
					}
					
					//SE COMIENZA A VALIDAR
				/*	
					if (!responseData.isEmpty())
					{
						report.AddLine("Ejecucion Correcta<br>ID_CUENTA: " + responseData);
						System.out.println("##[section] : Ejecucion Correcta\r\nID_CUENTA: " + responseData + "\r\n");
						//VALIDACIONES
						
						result = validacionGral(oraResp, report, responseData, entidad, validaciones);
					} else {
						report.AddLineAssertionError("FAIL<br>ID_CUENTA: " + responseData + " Se esperaba: un numero" );
						System.out.println("##[warning] : FAIL : \r\nID_CUENTA: " + responseData + " Se esperaba: un numero");
						result = false;
					}
*/
					
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					// POSTCONDICIONES
				//	System.out.println("##### INICIA EJECUCION DE POSTCONDICIONES #####");
				//	report.AddLine("##### INICIA EJECUCION DE POSTCONDICIONES #####");

					//execPostCondiciones(report, oraResp, rrnLast6);
					//ROLLBACK
			//		report.AddLine("Se procede a la eliminacion de los datos generados...");
				//	System.out.println("Se procede a la eliminacion de los datos generados...\r\n");
					
			//		report.AddLine("--- Ejecutando PostCondicion ---");
			//		System.out.println("--- Ejecutando PostCondicion ---\r\n");
					
			//		resp = oraResp.oraBorrarCuenta(report, responseData, doc, configEntidad, "1");
			//		ppCondi.rollBackBorrarId(report, responseData, doc, resp);

			//		System.out.println("##### FIN DE EJECUCION DE POSTCONDICIONES #####");
			//		report.AddLine("##### FIN DE EJECUCION DE POSTCONDICIONES #####");				
					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean consultaDisponible(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String statusCodeEsperado) {
		boolean result = false;
		
		try {
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("---Inicializando  " + name + "\r\n");
			//SET VARIABLES
			String token = "";
			//String respBody = "";
			String producto = "101";
			String cuenta = "10001182";
			int rta;
			float dispCompra = 0;
			float dispAnticipo = 0;
			String sDispCompra = "";
			String sDispAnticipo = "";		
			String[] resp = new String[2];
			Response respBody;
			
			//SET INSTANCES
			
			apiWorker apiResp = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//SET ENVIRONMENT
			//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
			String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
			repoVar.setBaseUri(base_url);
			//SET ENVIRONMENT
			//AI.setEndpoint(report, repoVar, endPoint);
			
			//PRECONDICION
			report.AddLine("--- Ejecutando PreCondicion ---");
			System.out.println("--- Ejecutando PreCondicion ---\r\n");
			rta = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA SET SALDO = 1000, DISPONIBLE = 1000, DISPONIBLE_ADELANTO = 1000 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.preCondicionBD(report, rta);
			//Seteo la cuenta y la tarjeta en estado 0-Activa para que no falle el TC
			rta = oraResp.oraUpdate(report, "UPDATE CUENTAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.preCondicionBD(report, rta);
			rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.preCondicionBD(report, rta);
			
            token = getToken(report, apiResp);
			
			System.out.println(token);
			
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				//respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
			
				respBody = apiResp.getMethod(repoVar, "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/", "Disponible", token);
		
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo("200"));
				}
			
			String respuestaBody = respBody.getBody().asPrettyString();
			System.out.println("***respuesta : " + respuestaBody);
			//Se muestra response en el reporte
			report.AddLine("Response: " + respuestaBody);
			
			//Se obtienen datos y se informan
			dispCompra = JsonPath.from(respuestaBody).get("Data.DisponibleCompra");
			report.AddLine("El disponible es: " + dispCompra);
			System.out.println("El disponible es: " + dispCompra  + "\r\n");
			dispAnticipo = JsonPath.from(respuestaBody).get("Data.DisponibleAnticipo");
			report.AddLine("El disponible adelanto es: " + dispAnticipo);
			System.out.println("El disponible adelanto es: " + dispAnticipo + "\r\n");
		} else {
			System.out.println("##[warning] : No se obtuvo un token\r\n");
			report.AddLineAssertionError("No se obtuvo un token");
			result = false;
		}

		sDispCompra = String.valueOf(dispCompra).replace(".0", "");
		report.AddLine("Disponible ajustado para verificar en la BD: " + sDispCompra);
		System.out.println("Disponible ajustado para verificar en la BD: " + sDispCompra  + "\r\n");
		//sDispAnticipo = String.valueOf(dispAnticipo).replace(".0", ""); 
	//	report.AddLine("Disponible Anticipo ajustado para verificar en la BD: " + sDispAnticipo);
	//	System.out.println("Disponible Anticipo ajustado para verificar en la BD: " + sDispAnticipo + "\r\n");

		//ADD VALIDATIONS
		System.out.println("Se realizan las validaciones en la BD:\r\n");
		report.AddLine("Se realizan las validaciones en la BD:");
		
		resp = oraResp.oraTwoQuery(report, "SELECT DISPONIBLE, DISPONIBLE_ADELANTO FROM CUENTAS_MONEDA WHERE ID_CUENTA = " + cuenta, configEntidad);

		if (!sDispCompra.equals(resp[0])) {
			report.AddLineAssertionError("Error:<br>El disponible de compra es: " + sDispCompra + " y no es igual al de la base: " + resp[0]);
			System.out.println("##[warning] : Error:\r\nEl disponible de compra es: " + sDispCompra + " y no es igual al de la base: " + resp[0] + "\r\n");
		
		//Si falla la validacion el resultado de la prueba es false
			result = false;			
		} else {
			report.AddLine("Validacion exitosa:<br>El disponible de compra es: " + sDispCompra + " y es igual al de la base: " + resp[0]);
			System.out.println("##[section] : Validacion exitosa:\r\n>El disponible de compra es: " + sDispCompra + " es igual al de la base: " + resp[0] + "\r\n");
		}

	/*	if (!sDispAnticipo.equals(resp[1])) {
			report.AddLineAssertionError("Error:<br>El disponible de adelanto es: " + sDispCompra + " y no es igual al de la base: " + resp[1]);
			System.out.println("##[warning] : Error:\r\nEl disponible de adelanto es: " + sDispCompra + " y no es igual al de la base: " + resp[1] + "\r\n");
		
		//Si falla la validacion el resultado de la prueba es false
			result = false;
		} else {
			report.AddLine("Validacion exitosa:<br>El disponible adelanto es: " + sDispAnticipo + " y es igual al de la base: " + resp[1]);
			System.out.println("##[section] : Validacion exitosa:\r\n>El disponible adelanto es: " + sDispAnticipo + " es igual al de la base: " + resp[1] + "\r\n");
		}	*/
		
		//ROLBACK
		report.AddLine("--- Ejecutando PostCondicion ---");
		System.out.println("--- Ejecutando PostCondicion ---\r\n");
		rta = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA SET SALDO = 1000, DISPONIBLE = 1000, DISPONIBLE_ADELANTO = 1000 WHERE ID_CUENTA = " + cuenta, configEntidad);
		ppCondi.postCondicionBD(report, rta);

		System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
		report.AddLine("<------ Finished Test: " + name + " ------>");

			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
//Se agrega de nuevo ROLLBACK para que no quede la base inconsistente si falla el TestCase  
			
			//SET VARIABLES 
			int rta;
			dbWorker oraResp = new dbWorker();
			PrePostCondi ppCondi = new PrePostCondi();
			//String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
			String cuenta = "10001182";
			//ROLLBACK
			report.AddLine("--- Ejecutando PostCondicion ---");
			System.out.println("--- Ejecutando PostCondicion ---\r\n");
			rta = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA SET SALDO = 1000, DISPONIBLE = 1000, DISPONIBLE_ADELANTO = 1000 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.postCondicionBD(report, rta);
			
			result = false;
		}
		return result;
	}
	                                //validacionGral(oraResp, report, responseData, entidad, validaciones);
	public boolean consultaDeCuenta(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("---Inicializando  " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("IdCuenta");
				 	
				System.out.println("Este es el ID de Cuenta consultado es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response el ID de Cuenta consultado es: " + responseData);
					System.out.println("Response el ID de Cuenta consultado es: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	                                //validacionGral(oraResp, report, responseData, entidad, validaciones);
	public boolean consultaDeEstadoTarjeta(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Data.Resultado.Estado");
				 	
				System.out.println("Este es el ID de Cuenta consultado es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response el ID de Cuenta consultado es: " + responseData);
					System.out.println("Response el ID de Cuenta consultado es: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	
	public boolean consultaDeSaldo(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Data.SaldoPesos");
				 	
				System.out.println("Este es el ID de Cuenta consultado es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response el Saldo en pesos de Cuenta consultado es: " + responseData);
					System.out.println("Response el Saldo en pesos de Cuenta consultado es: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean cambioDeEstadoDeCuenta(Reports report, String name, String configEntidad, String entidad, String TCFilesPath) {
		boolean result = true;
		try {
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("---Inicializando   " + name + "\r\n");
			//seteo variables necesarias
			String token = "";
			Response response;
			String responseData = "";
			String producto = "101";
			String cuenta = "10001182";
			int rta = 0;
			String bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));;
			String resp = "";
			
			//SET INSTANCES
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			PrePostCondi ppCondi = new PrePostCondi();
			AutorizadorInterno AI = new AutorizadorInterno();
			//SET ENVIRONMENT
			//AI.setEndpoint(report, repoVar, endPoint);	
			System.out.println("<------ Iniciando la ejecucion: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);
			
			rta = oraResp.oraUpdate(report, "UPDATE CUENTAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.preCondicionBD(report, rta);

			System.out.println("este es el body que le estoy pasando :" + bodyData);
			//inicio ejecucion
			if (!token.isEmpty())
			{
				//respBody = apiWorker.putMethodModificarCuenta(repoVar, endPoint, bodyData, token);		
				response = apiWorker.putMethod(repoVar, "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/", "Estado", bodyData, token);
			//Se reporta el Status Code
			report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
			System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
			
			//Se valida el status Code
			//MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
			if (!String.valueOf(response.getStatusCode()).equals("200")) {
				report.AddLineAssertionError("Error: Status Code esperado: 200; Status Code obtenido: " + String.valueOf(response.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion: Status Code", String.valueOf(response.getStatusCode()), equalTo("200"));
				}	
			} else {
				System.out.println("##[warning] : No se obtuvo un token");
				report.AddLineAssertionError("No se obtuvo un token\r\n");
				result = false;
			}
			resp = oraResp.oraOneQuery(report, "SELECT ID_ESTADO FROM CUENTAS WHERE ID_CUENTA = " + cuenta, configEntidad);

			if (!resp.equals("1")) {
				report.AddLineAssertionError("Error: La validacion en la base de datos fallo. El estado de cuenta es: " + resp);
				System.out.println("##[warning] : Error: La validacion en la base de datos fallo. El estado de cuenta es: " + resp + "\r\n");
			
			//Si falla la validacion el resultado de la prueba es false
				result = false;
			} else {
				report.AddLine("Validacion correcta: El nuevo estado de la Cuenta 1 es: " + resp);
				System.out.println("##[section] : Validacion correcta: El nuevo estado de la Cuenta 1 es: " + resp + "\r\n");
			}

			
			//ROLLBACK
			report.AddLine("--- Ejecutando PostCondicion ---");
			System.out.println("--- Ejecutando PostCondicion ---\r\n");
			rta = oraResp.oraUpdate(report, "UPDATE CUENTAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.validaRollBackUpdate(report, rta);
			
			report.AddLine("<------ Finished Test: " + name + " ------>\r\n");
			System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");

			
		

		}
		catch(Exception e) {
			
		}
		
		return result; 
	}
	public boolean cambioDeEstadoDeTarjeta(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String cuenta, String producto, String referenciaTarjeta, String baseURL) {
		boolean result = true;
		try {
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Se inician las configuraciones " + name + "\r\n");
			
			String token = "";
			//String bodyData = "";
			String resp = "";
			int rta = 0;
			String bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));;
			Response response;

			//SET INSTANCES
			apiWorker apiResp = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//SET ENVIRONMENT
			//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
			String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
			repoVar.setBaseUri(base_url);
			
			//PRECONDICION
			report.AddLine("--- Ejecutando PreCondicion ---");
			System.out.println("--- Ejecutando PreCondicion ---\r\n");
			rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.preCondicionBD(report, rta);
			
			System.out.println("<------ Iniciando la ejecucion: " + name + " ------>\r\n");
			token = getToken(report, apiResp);		
			System.out.println(token);
			
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				response = apiResp.putMethod(repoVar, "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/" + referenciaTarjeta + "/", "Estado", bodyData, token);
				System.out.println(" response : " + response);
			//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
				
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
			resp = oraResp.oraOneQuery(report, "SELECT ID_ESTADO FROM TARJETAS WHERE ID_CUENTA = " + cuenta, configEntidad);

			if(!resp.equals("1")) {
				report.AddLineAssertionError("Error: El estado de la tarjeta es: " + resp + " Se esperaba el estado: 1");
				System.out.println("##[warning] : Error: El estado de la tarjeta es: " + resp + " Se esperaba el estado: 1\r\n");
			
			//Si falla la validacion el resultado de la prueba es false
			result = false;
			} else {
				report.AddLine("Validacion exitosa:<br>El estado de la tarjeta es: 1 y es igual al de la base de datos: " + resp);
				System.out.println("##[section] : Validacion exitosa:\r\nEl estado de la tarjeta es: 1 y es igual al de la base de datos: " + resp + "\r\n");
			}
			//ROLLBACK
			report.AddLine("--- Ejecutando PostCondicion ---");
			System.out.println("--- Ejecutando PostCondicion ---\r\n");
			rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.validaRollBackUpdate(report, rta);

			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}
		
		
		return result;
		
	}
	public boolean cambioDeEstadoDeTarjetaNormalHabilitada(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String cuenta, String producto, String referenciaTarjeta, String baseURL) {
		boolean result = true;
		try {
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Se inician las configuraciones " + name + "\r\n");
			
			String token = "";
			//String bodyData = "";
			String resp = "";
			int rta = 0;
			String bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));
			Response response;

			//SET INSTANCES
			apiWorker apiResp = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//SET ENVIRONMENT
			//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
			String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
			repoVar.setBaseUri(base_url);
			
			//PRECONDICION
			report.AddLine("--- Ejecutando PreCondicion ---");
			System.out.println("--- Ejecutando PreCondicion ---\r\n");
			rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 1 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.preCondicionBD(report, rta);
			
			System.out.println("<------ Iniciando la ejecucion: " + name + " ------>\r\n");
			token = getToken(report, apiResp);		
			System.out.println(token);
			
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				response = apiResp.putMethod(repoVar, "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/" + referenciaTarjeta + "/", "Estado", bodyData, token);
				System.out.println(" response : " + response);
			//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
				
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
			resp = oraResp.oraOneQuery(report, "SELECT ID_ESTADO FROM TARJETAS WHERE ID_CUENTA = " + cuenta, configEntidad);

			if(!resp.equals("0")) {
				report.AddLineAssertionError("Error: El estado de la cuenta es: " + resp + " Se esperaba el estado: 0");
				System.out.println("##[warning] : Error: El estado de la cuenta es: " + resp + " Se esperaba el estado: 0\r\n");
			
			//Si falla la validacion el resultado de la prueba es false
			result = false;
			} else {
				report.AddLine("Validacion exitosa:<br>El estado de la tarjeta es: 0 y es igual al de la base de datos: " + resp);
				System.out.println("##[section] : Validacion exitosa:\r\nEl estado de la tarjeta es: 0 y es igual al de la base de datos: " + resp + "\r\n");
			}
			
			//POSTCONDICION
			report.AddLine("--- Ejecutando PostCondicion ---");
			System.out.println("--- Ejecutando PostCondicion ---\r\n");
			rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + cuenta, configEntidad);
			ppCondi.validaRollBackUpdate(report, rta);

			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}
		
		
		return result;
		
	}
	public boolean ingresoAjusteDebito(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String cuenta, String producto, String referenciaTarjeta, String baseURL) {
		boolean result = true;
		
		try {
		
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Se inician las configuraciones " + name + "\r\n");
			String token = "";
			String respBody = "";
			int comprobanteAjuste = 0;
			int rta1 = 0;
			int rta2 = 0;
			String[] rtaCant2 = new String[2];
			String[] rtaCant3 = new String[3];
			String saldoEsperado = "998.5";
			String disponibleEsperado = "998.5";
			String importeAjusteEsperado = "1.5";
			boolean res;
			Response response;
			String bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));
			String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
			
			//SET INSTANCES
			apiWorker apiResp = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			PrePostCondi ppCondi = new PrePostCondi();
			opiWorker validacionAjus = new opiWorker();
			
			repoVar.setBaseUri(base_url);
			
			//PRECONDICION
			report.AddLine("--- Ejecutando PreCondicion ---");
			System.out.println("--- Ejecutando PreCondicion ---\r\n");
			
			rta1 = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
					+ "SET SALDO = 1000, DISPONIBLE = 1000, DISPONIBLE_ADELANTO = 1000,\r\n"
					+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = 0, \r\n"
					+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = 0,\r\n"
					+ "IMPORTE_PAGOS = 1000\r\n"
					+ "WHERE ID_CUENTA = " + cuenta + " AND ID_MONEDA IN (32)", configEntidad);
			ppCondi.preCondicionBD(report, rta1);
			
			System.out.println("<------ Iniciando la ejecucion: " + name + " ------>\r\n");
			token = getToken(report, apiResp);		
			System.out.println(token);
			
			System.out.println("objeto del body : " + bodyData);
			
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				response = apiResp.postMethod(repoVar, "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/", "IngresoDeAjuste", bodyData, token);
				
			//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
				
			//Se valida el status code
			if(response.getStatusCode()!=201) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 201 - Obtenido: " + String.valueOf(response.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("201"));				
				}
			
			//Se obtiene el body del response
			respBody = response.getBody().asPrettyString();
				
			//Se muestra response en el reporte
			report.AddLine("Response: " + respBody);				
		    comprobanteAjuste = JsonPath.from(respBody).get("Data.ComprobanteAjusteSocio");
				
		    //Se reporta el ID de Cuenta
			report.AddLine("Comprobante de Ajuste: " + String.valueOf(comprobanteAjuste));
			System.out.println("Comprobante de Ajuste: " + String.valueOf(comprobanteAjuste) + "\r\n");
			} else {
				System.out.println("##[warning] : No se obtuvo un token\r\n");
				report.AddLineAssertionError("No se obtuvo un token");
				result = false;
			}
			//ADD VALIDATIONS	
			System.out.println("Se realizan las validaciones en la BD:\r\n");
			report.AddLine("Se realizan las validaciones en la BD:");
			
			//Validacion Saldo, Disponible e Importe de Ajuste
			rtaCant3 = oraResp.oraThreeQuery(report, "SELECT SALDO, DISPONIBLE,IMPORTE_AJUSTES FROM CUENTAS_MONEDA WHERE ID_CUENTA = " + cuenta, configEntidad);
			res = validacionAjus.validacionCM_Saldo_Disponible_Importe_Ajustado(report, rtaCant3, saldoEsperado, disponibleEsperado, importeAjusteEsperado);
			if (!res) {
				
			//Si falla la validacion el resultado de la prueba es false
				result = false;			
			}
			
			//Validacion ID_ESTADO y COMPROBANTE AJUSTE
			rtaCant2 = oraResp.oraTwoQuery(report, "SELECT ID_AJUSTE, ID_ESTADO FROM AJUSTES_SOCIOS WHERE ID_AJUSTE = " + String.valueOf(comprobanteAjuste), configEntidad);
			res = validacionAjus.validacionAjusteSocios(report, rtaCant2, String.valueOf(comprobanteAjuste));
			if (!res) {
				
			//Si falla la validacion el resultado de la prueba es false
				result = false;
			}
			
			//ROLLBACK
			report.AddLine("--- Ejecutando PostCondicion ---");
			System.out.println("--- Ejecutando PostCondicion ---\r\n");
			
			rta1 = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
					+ "SET SALDO = 1000, DISPONIBLE = 1000, DISPONIBLE_ADELANTO = 1000,\r\n"
					+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = 0, \r\n"
					+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = 0,\r\n"
					+ "IMPORTE_PAGOS = 1000\r\n"
					+ "WHERE ID_CUENTA = " + cuenta + " AND ID_MONEDA IN (32)", configEntidad);
			ppCondi.validaRollBackUpdate(report, rta1);
			
			rta2 = oraResp.oraDelete(report, "DELETE AJUSTES_SOCIOS WHERE ID_AJUSTE = " + String.valueOf(comprobanteAjuste), configEntidad);
			ppCondi.validaRollBackDelete(report, rta2);
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		
		}
		
		return result;
	}
	public boolean reimpresionDeTarjetaFisica(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String cuenta, String producto, String referenciaTarjeta, String baseURL, String cuentas_generales) {
		//Validation var
				boolean result = true;
				try {
					System.out.println("\r\n##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					System.out.println("Se inician las configuraciones " + name + "\r\n");
					String token = "";
					String respBody = "";
					String bodyData = "";
					String[] resp = new String[2];
					int rta = 0;
					String ID_CUENTA = "21";
					String ID_PRODUCTO = "1";
					String REFERENCIA = "8008";
					String ID_TARJETA = "391";
					Response response;
					
					
					//SET INSTANCES
					apiWorker apiResp = new apiWorker();
					dbWorker oraResp = new dbWorker();
					Repo_Variables repoVar = new Repo_Variables();
					PrePostCondi ppCondi = new PrePostCondi();
					
					//SET ENVIRONMENT
					//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
					String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
					repoVar.setBaseUri(base_url);
					
					System.out.println("##[command] : <------ Initializating Test: " + name + " ------>\r\n");
					report.AddLine("<------ Initializating Test: " + name + " ------>");
					
					//PRECONDICION
					report.AddLine("--- Ejecutando PreCondicion ---");
					System.out.println("--- Ejecutando PreCondicion ---\r\n");
					
					rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA = " + ID_TARJETA, configEntidad);

					ppCondi.preCondicionBD(report, rta);

					//GET TOKEN
					try {
						token = apiResp.GetAccessTokenPP(configEntidad);
					} catch (Exception E) {
						report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
						System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
					}
					
					//GET POST BODY FROM FILE
					try {
						bodyData =new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));
						
						//Se muestra el body en el reporte
						report.AddLine("Request body:<br>" + bodyData);
						
					} catch (Exception E) {
						report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
						System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
					}

					//PUT - Reimpresion de Tarjeta - Fisica a Fisica
					if (!bodyData.isEmpty() && !token.isEmpty())
					{

					response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Reimprimir", bodyData, token);

						//Se reporta el Status Code
						report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
						System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
						//Se valida el status code
						if(response.getStatusCode()!=200) {
							report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
							System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
							MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
						}
						//Se obtiene el body del response
						respBody = response.getBody().asPrettyString();
						//Se muestra response en el reporte
						report.AddLine("Response: " + respBody);
			
					} else {
						System.out.println("##[warning] : No se obtuvo un token\r\n");
						report.AddLineAssertionError("No se obtuvo un token");
						result = false;
					}
							
					//ADD VALIDATIONS
					
					System.out.println("Se realizan las validaciones en la BD:\r\n");
					report.AddLine("Se realizan las validaciones en la BD:");
					
					resp = oraResp.oraTwoQuery(report, "SELECT ID_ESTADO, SOPORTE_FISICO FROM TARJETAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA NOT IN (" + ID_TARJETA + ")", configEntidad);
					
					if(!resp[0].equals("2")) {
						rollBack(report, oraResp, ppCondi, configEntidad, cuentas_generales);
						System.out.println("##[warning] : Error: El estado de la nueva tarjeta es: " + resp[0] + " Se esperaba el estado: 2");
						report.AddLineAssertionError("Error: El estado de la nueva tarjeta es: " + resp[0] + " Se esperaba el estado: 2");
					
					//Si falla la validacion el resultado de la prueba es false
						result = false;
					} else {
						report.AddLine("Validacion exitosa:<br>El estado de la nueva tarjeta es: 2 y es igual al de la base de datos: " + resp[0]);
						System.out.println("##[section] : Validacion exitosa:\r\nEl estado de la nueva tarjeta es: 2 y es igual al de la base de datos: " + resp[0] + "\r\n");
					}
					
					if(!resp[1].equals("0")) {
						rollBack(report, oraResp, ppCondi, configEntidad, cuentas_generales);
						System.out.println("##[warning] : Error: El Soporte Fisico es: " + resp[1] + " Se esperaba: 0");
						report.AddLineAssertionError("Error: El Soporte Fisico es: " + resp[1] + " Se esperaba: 0");
						report.AddLine("Error: El Soporte Fisico es: " + resp[1] + " Se esperaba: 0\r\n");
						//Si falla la validacion el resultado de la prueba es false
						result = false;
					} else {
						report.AddLine("Validacion exitosa:<br>El Soporte Fisico es: 0 y es igual al de la base de datos: " + resp[1]);
						System.out.println("##[section] : Validacion exitosa:\r\nEl Soporte Fisico es: 0 y es igual al de la base de datos: " + resp[1] + "\r\n");
					}
					
					//ROLLBACK
					report.AddLine("--- Ejecutando PostCondicion para eliminar los datos generados en la BD ---");
					System.out.println("--- Ejecutando PostCondicion para eliminar los datos generados en la BD ---\r\n");
					
					rollBack(report, oraResp, ppCondi, configEntidad, cuentas_generales);	
					
					System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
					report.AddLine("<------ Finished Test: " + name + " ------>");
					
					//Separador
					System.out.println("##################################################################################################################################################################################################################"
							 + "##################################################################################################################################################################################################################\r\n");
				
					
				}
				catch(Exception e) {
					
				}
				
				
		return result;
	}
	private void rollBack(Reports report, dbWorker oraResp, PrePostCondi ppCondi, String configEntidad, String cuentas_generales) {
		//Variables
		int rta;
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_2.cuenta_fisica");
		String ID_TARJETA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_2.id_tarjeta");
		
		//Operacion 1
		rta = oraResp.oraDelete(report, "DELETE TARJETAS_HISTORIAL WHERE ID_TARJETA IN (SELECT ID_TARJETA FROM TARJETAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA NOT IN (" + ID_TARJETA + "))", configEntidad);
		ppCondi.postCondicionBD(report, rta);
		//Operacion 2
		rta = oraResp.oraUpdate(report, "UPDATE SOCIOS SET ID_TARJETA_VIGENTE = NULL WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);
		//Operacion 3
		rta = oraResp.oraDelete(report, "DELETE TARJETAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA NOT IN (" + ID_TARJETA + ")", configEntidad);
		ppCondi.postCondicionBD(report, rta);
		//Operacion 4
		rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA = " + ID_TARJETA, configEntidad);
		ppCondi.postCondicionBD(report, rta);	
		//Operacion 5
		rta = oraResp.oraUpdate(report, "UPDATE SOCIOS SET ID_TARJETA_VIGENTE = " + ID_TARJETA + " WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);
	}
	public boolean reimpresionDeTarjetaVirtual(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String cuenta, String producto, String referenciaTarjeta, String baseURL, String cuentas_generales) {
		//Validation var
				boolean result = true;
				try {
					System.out.println("\r\n##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					System.out.println("Se inician las configuraciones " + name + "\r\n");
					String token = "";
					String respBody = "";
					String bodyData = "";
					String[] resp = new String[2];
					int rta = 0;
					String ID_CUENTA = "35";
					String ID_PRODUCTO = "1";
					String REFERENCIA = "9997";
					String ID_TARJETA = "44";
					Response response;
					
					
					//SET INSTANCES
					apiWorker apiResp = new apiWorker();
					dbWorker oraResp = new dbWorker();
					Repo_Variables repoVar = new Repo_Variables();
					PrePostCondi ppCondi = new PrePostCondi();
					
					//SET ENVIRONMENT
					//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
					String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
					repoVar.setBaseUri(base_url);
					
					System.out.println("##[command] : <------ Initializating Test: " + name + " ------>\r\n");
					report.AddLine("<------ Initializating Test: " + name + " ------>");
					
					//PRECONDICION
					report.AddLine("--- Ejecutando PreCondicion ---");
					System.out.println("--- Ejecutando PreCondicion ---\r\n");
					
					rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA = " + ID_TARJETA, configEntidad);

					ppCondi.preCondicionBD(report, rta);

					//GET TOKEN
					try {
						token = apiResp.GetAccessTokenPP(configEntidad);
					} catch (Exception E) {
						report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
						System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
					}
					
					//GET POST BODY FROM FILE
					try {
						bodyData =new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));
						
						//Se muestra el body en el reporte
						report.AddLine("Request body:<br>" + bodyData);
						
					} catch (Exception E) {
						report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
						System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
					}

					//PUT - Reimpresion de Tarjeta - Fisica a Fisica
					if (!bodyData.isEmpty() && !token.isEmpty())
					{

					response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Reimprimir", bodyData, token);

						//Se reporta el Status Code
						report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
						System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
						//Se valida el status code
						if(response.getStatusCode()!=200) {
							report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
							System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
							MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
						}
						//Se obtiene el body del response
						respBody = response.getBody().asPrettyString();
						//Se muestra response en el reporte
						report.AddLine("Response: " + respBody);
			
					} else {
						System.out.println("##[warning] : No se obtuvo un token\r\n");
						report.AddLineAssertionError("No se obtuvo un token");
						result = false;
					}
							
					//ADD VALIDATIONS
					
					System.out.println("Se realizan las validaciones en la BD:\r\n");
					report.AddLine("Se realizan las validaciones en la BD:");
					
					resp = oraResp.oraTwoQuery(report, "SELECT ID_ESTADO, SOPORTE_FISICO FROM TARJETAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA NOT IN (" + ID_TARJETA + ")", configEntidad);
					
					if(!resp[0].equals("2")) {
						rollBack(report, oraResp, ppCondi, configEntidad, cuentas_generales);
						System.out.println("##[warning] : Error: El estado de la nueva tarjeta es: " + resp[0] + " Se esperaba el estado: 2");
						report.AddLineAssertionError("Error: El estado de la nueva tarjeta es: " + resp[0] + " Se esperaba el estado: 2");
					
					//Si falla la validacion el resultado de la prueba es false
						result = false;
					} else {
						report.AddLine("Validacion exitosa:<br>El estado de la nueva tarjeta es: 2 y es igual al de la base de datos: " + resp[0]);
						System.out.println("##[section] : Validacion exitosa:\r\nEl estado de la nueva tarjeta es: 2 y es igual al de la base de datos: " + resp[0] + "\r\n");
					}
					
					if(!resp[1].equals("0")) {
						rollBack(report, oraResp, ppCondi, configEntidad, cuentas_generales);
						System.out.println("##[warning] : Error: El Soporte Fisico es: " + resp[1] + " Se esperaba: 0");
						report.AddLineAssertionError("Error: El Soporte Fisico es: " + resp[1] + " Se esperaba: 0");
						report.AddLine("Error: El Soporte Fisico es: " + resp[1] + " Se esperaba: 0\r\n");
						//Si falla la validacion el resultado de la prueba es false
						result = false;
					} else {
						report.AddLine("Validacion exitosa:<br>El Soporte Fisico es: 0 y es igual al de la base de datos: " + resp[1]);
						System.out.println("##[section] : Validacion exitosa:\r\nEl Soporte Fisico es: 0 y es igual al de la base de datos: " + resp[1] + "\r\n");
					}
					
					//ROLLBACK
					report.AddLine("--- Ejecutando PostCondicion para eliminar los datos generados en la BD ---");
					System.out.println("--- Ejecutando PostCondicion para eliminar los datos generados en la BD ---\r\n");
					
					rollBack(report, oraResp, ppCondi, configEntidad, cuentas_generales);	
					
					System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
					report.AddLine("<------ Finished Test: " + name + " ------>");
					
					//Separador
					System.out.println("##################################################################################################################################################################################################################"
							 + "##################################################################################################################################################################################################################\r\n");
				
					
				}
				catch(Exception e) {
					
				}
				
				
		return result;
	}
	
	public boolean editarDatosDeCuenta(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String cuenta, String producto, String referenciaTarjeta, String baseURL, String cuentas_generales) {
		boolean result = true;
		try {
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring TC_16_API_PP\r\n");

			//SET VARIABLES
			String path = "./TC_Files/API/9999/TC_16/TXT/TC.txt";
			String token = "";
			String bodyData = "";
			String resp = "";
			int rta = 0;
			String ID_CUENTA = "41";
			String ID_PRODUCTO = "1";
		    String ID_SUCURSAL_PRECONDI = "1";
		    String ID_GRUPO_AFINIDAD_PRECONDI = "1"; 
		    String ID_SUCURSAL_NUEVO = "2";
		    String ID_GRUPO_AFINIDAD_NUEVO = "5";
		    String jsonData = "";
			Response response;
			String respBody = "";

			//SET INSTANCES
			apiWorker apiResp = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			PrePostCondi ppCondi = new PrePostCondi();

			//SET ENVIRONMENT
			//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
			String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
			repoVar.setBaseUri(base_url);

			System.out.println("##[command] : <------ Initializating Test: " + name + " ------>\r\n");
			report.AddLine("<------ Initializating Test: " + name + " ------>");

			//PRECONDICION
			report.AddLine("--- Ejecutando PreCondicion ---");
			System.out.println("--- Ejecutando PreCondicion ---\r\n");

			rta = oraResp.oraUpdate(report, "UPDATE CUENTAS SET ID_SUCURSAL = " + ID_SUCURSAL_PRECONDI + ", ID_GRUPO_AFINIDAD = " + ID_GRUPO_AFINIDAD_PRECONDI + ",ID_POSICION_IMPOSITIVA = 1,\r\n"
					+ "CUENTA_EXTERNA = " + ID_CUENTA + ",ENTREGA_TARJETA = 2 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
			ppCondi.preCondicionBD(report, rta);

			rta = oraResp.oraUpdate(report, "UPDATE PERSONAS SET ID_TIPO_DOCUMENTO = 1,SEXO = 'F',ESTADO_CIVIL = 0,\r\n"
					+ "FECHA_NACIMIENTO = to_date('13/10/1988 00:00:00','DD/MM/RRRR HH24:MI:SS'), \r\n"
					+ "MAIL = 'rmendoza@lol.com',APELLIDO = 'Mendoza',NOMBRE = 'Roma',\r\n"
					+ "ID_PAIS_NACIMIENTO = 32 WHERE ID_PERSONA = (SELECT ID_PERSONA FROM SOCIOS WHERE ID_CUENTA = " + ID_CUENTA + ")", configEntidad);
			ppCondi.preCondicionBD(report, rta);
			
			//Actualizo archivo json con los datos de la sucursal y grupo de afinidad
			
			jsonData =  new String(Files.readAllBytes(Paths.get(path)));
			
			report.AddLine("Archivo json actual: " + jsonData);
			System.out.println("Archivo json actual: \r\n" + jsonData + "\r\n");
			
			//Leo el json actual para obtener el id de sucursal y el id de grupo de afinidad
			int sucursalActual = JsonPath.from(jsonData).get("SucursalEmisora");
			report.AddLine("El ID de la Sucursal del archivo actual es: " + sucursalActual);
			System.out.println("El ID de la Sucursal del archivo actual es: " + sucursalActual + "\r\n");
			
			int grupoAfinidadActual = JsonPath.from(jsonData).get("GrupoAfinidad");
			report.AddLine("El ID del Grupo de Afinidad del archivo actual es: " + grupoAfinidadActual);
			System.out.println("El ID del Grupo de Afinidad del archivo actual es: " + grupoAfinidadActual + "\r\n");
			
			//Reemplazo los valores actuales por los valores nuevos
			String jsonDataModif = jsonData.replace("\"SucursalEmisora\": " + sucursalActual ,"\"SucursalEmisora\": " + ID_SUCURSAL_NUEVO);			
			jsonDataModif = jsonDataModif.replace("\"GrupoAfinidad\": " + grupoAfinidadActual ,"\"GrupoAfinidad\": " + ID_GRUPO_AFINIDAD_NUEVO);
			
			report.AddLine("Se procede a modificar el archivo json actual");
			System.out.println("Se procede a modificar el archivo json actual\r\n");
			report.AddLine("Archivo json actualizado: " + jsonDataModif);
			System.out.println("Archivo json actualizado: \r\n\r\n" + jsonDataModif + "\r\n");
			
			//Grabo en el archivo el nuevo json generado
			FileWriter archivoNuevo = new FileWriter(path);
			archivoNuevo.write(jsonDataModif);
			archivoNuevo.close();
			
			//GET TOKEN
			try {
				token = apiResp.GetAccessTokenPP(configEntidad);
			} catch (Exception E) {
				report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
				System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
			}

			//GET POST BODY FROM FILE
			try {
				bodyData = new String(Files.readAllBytes(Paths.get(path)));			
				
				//Se muestra el body en el reporte
				report.AddLine("Request body:<br>" + bodyData);
				
			} catch (Exception E) {
				report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
				System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
			}

			//PUT - Editar Datos de cuenta -Datos principales y Titular
			
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				bodyData=bodyData.replace("{{IDCTAEXT}}", ID_CUENTA);
				response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA, "", bodyData, token);
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
				//Se valida el status code
				if(response.getStatusCode()!=200) {
					report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
					System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
					MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
				}
				//Se obtiene el body del response
				respBody = response.getBody().asPrettyString();
				//Se muestra response en el reporte
				report.AddLine("Response: " + respBody);

			} else {
				System.out.println("##[warning] : No se obtuvo un token\r\n");
				report.AddLineAssertionError("No se obtuvo un token");
				result = false;
			}

			//ADD VALIDATIONS
			System.out.println("Se realizan las validaciones en la BD:\r\n");
			report.AddLine("Se realizan las validaciones en la BD:");
			
			resp = oraResp.oraOneQuery(report, "SELECT COUNT (*) FROM CUENTAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_SUCURSAL = " + ID_SUCURSAL_NUEVO + " AND ID_GRUPO_AFINIDAD = " + ID_GRUPO_AFINIDAD_NUEVO + " \r\n"
					+ "AND ID_POSICION_IMPOSITIVA = 1 AND CUENTA_EXTERNA = " + ID_CUENTA + " AND ENTREGA_TARJETA = 2", configEntidad);

			if(!resp.equals("1")) {
				report.AddLineAssertionError("Error: No se modificaron los datos en la tabla CUENTAS");
				System.out.println("##[warning] : Error: No se modificaron los datos en la tabla CUENTAS\r\n");
				//Si falla la validacion el resultado de la prueba es false
				result = false;
			} else {
				report.AddLine("Validacion exitosa:<br>Se modificaron correctamente los datos en la tabla CUENTAS");
				System.out.println("##[section] : Validacion exitosa:\r\nSe modificaron correctamente los datos en la tabla CUENTAS\r\n");
			}

			resp = oraResp.oraOneQuery(report, "SELECT COUNT (P.ID_PERSONA) FROM PERSONAS P \r\n"
					+ "WHERE  P.ID_PERSONA = (SELECT ID_PERSONA FROM SOCIOS WHERE ID_CUENTA = " + ID_CUENTA + ") \r\n"
					+ "AND P.ID_TIPO_DOCUMENTO = 1  \r\n"
					+ "AND P.ID_PAIS_NACIMIENTO = 276 \r\n"
					+ "AND P.SEXO = 'F' \r\n"
					+ "AND P.ESTADO_CIVIL = 0 \r\n"
					+ "AND P.FECHA_NACIMIENTO = TO_DATE ('30/08/1988','DD/MM/YYYY')\r\n"
					+ "AND P.MAIL = 'TC16API@gmail.com' \r\n"
					+ "AND P.APELLIDO = 'NO BORRAR' \r\n"
					+ "AND P.NOMBRE = 'TC_16_API'", configEntidad);

			if(!resp.equals("1")) {
				report.AddLineAssertionError("Error: No se modificarion los datos en la tabla PERSONAS");
				System.out.println("##[warning] : Error: No se modificarion los datos en la tabla PERSONAS\r\n");
				//Si falla la validacion el resultado de la prueba es false
				result = false;
			} else {
				report.AddLine("Validacion exitosa:<br>Se modificarion correctamente los datos en la tabla PERSONAS");
				System.out.println("##[section] : Validacion exitosa:\r\nSe modificarion correctamente los datos en la tabla PERSONAS\r\n");
			}

			//ROLLBACK
			report.AddLine("--- Ejecutando PostCondicion ---");
			System.out.println("--- Ejecutando PostCondicion ---\r\n");

			rollBack(report, oraResp, ppCondi, configEntidad, cuentas_generales, ID_SUCURSAL_PRECONDI, ID_GRUPO_AFINIDAD_PRECONDI);

			System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
			report.AddLine("<------ Finished Test: " + name + " ------>");

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

	private void rollBack(Reports report, dbWorker oraResp, PrePostCondi ppCondi, String configEntidad, String cuentas_generales, String ID_SUCURSAL_PRECONDI, String ID_GRUPO_AFINIDAD_PRECONDI) {
		//Variables
		int rta;
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_VIRTUAL_3.cuenta_virtual");
		
		//Operacion 1
		rta = oraResp.oraUpdate(report, "UPDATE CUENTAS SET ID_SUCURSAL = " + ID_SUCURSAL_PRECONDI + ", ID_GRUPO_AFINIDAD = " + ID_GRUPO_AFINIDAD_PRECONDI + ",ID_POSICION_IMPOSITIVA = 1,\r\n"
				+ "CUENTA_EXTERNA = " + ID_CUENTA + ",ENTREGA_TARJETA = 2 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);

		//Operacion 2
		rta = oraResp.oraUpdate(report, "UPDATE PERSONAS SET ID_TIPO_DOCUMENTO = 1,SEXO = 'F',ESTADO_CIVIL = 0,\r\n"
				+ "FECHA_NACIMIENTO = to_date('13/10/1988 00:00:00','DD/MM/RRRR HH24:MI:SS'), \r\n"
				+ "MAIL = 'rmendoza@lol.com',APELLIDO = 'Mendoza',NOMBRE = 'Roma',\r\n"
				+ "ID_PAIS_NACIMIENTO = 32 WHERE ID_PERSONA = (SELECT ID_PERSONA FROM SOCIOS WHERE ID_CUENTA = " + ID_CUENTA + ")", configEntidad);
		ppCondi.postCondicionBD(report, rta);
	}
		
	//public boolean consultaDeTarjeta() {}
	
	
	public boolean consultaDeSaldoEnDolares(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 			
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Data.SaldoDolar");
				 	
				System.out.println("Este es el ID de Cuenta consultado es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response el Saldo en dolares de Cuenta consultado es: " + responseData);
					System.out.println("Response el Saldo en dolares de Cuenta consultado es: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean consultaDeConsumos(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Resultado");
				 	
				System.out.println("Consumos consultados :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response consumos de Cuenta consultado es: " + responseData);
					System.out.println("Response consumos de Cuenta consultado es: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean consultaDeDevoluciones(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Resultado");
				 	
				System.out.println("Consumos consultados :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response consumos de Cuenta consultado es: " + responseData);
					System.out.println("Response consumos de Cuenta consultado es: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean consultaDeAdelantos(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Resultado");
				 	
				System.out.println("El resultado de la consulta es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response resultado de la consulta: " + responseData);
					System.out.println("Response resultado de la consulta: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean consultaDeMovimientos(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Resultado");
				 	
				System.out.println("El resultado de la consulta es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response resultado de la consulta: " + responseData);
					System.out.println("Response resultado de la consulta: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean consultaDeAjustes(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Resultado");
				 	
				System.out.println("El resultado de la consulta es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response resultado de la consulta: " + responseData);
					System.out.println("Response resultado de la consulta: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean consultaDeCargas(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				 responseData = jsonPath.getString("Resultado");
				 	
				System.out.println("El resultado de la consulta es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response resultado de la consulta: " + responseData);
					System.out.println("Response resultado de la consulta: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean consultaDePIN(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			String token = "";
			String pinActual = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 				
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
				
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			/*************************************************/
			
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
			
			/*****************************************************/
			                                 
			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			for(int i = 0; i < 3; i++) {
			token = getToken(report, apiWorker);
			
			}//System.out.println(token);
			//POST - Con token y Body OK
				
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				//Se muestra response en el reporte
				report.AddLine("Response: " + responseBody);
				
				//Se obtienen datos y se informan
				pinActual = JsonPath.from(responseBody).get("Data.PinActual");
				report.AddLine("El numero de PIN actual es: " + pinActual);
				System.out.println("El numero de PIN actual es: " + pinActual + "\r\n");
			} else {
				System.out.println("##[warning] : No se obtuvo un token\r\n");
				report.AddLineAssertionError("No se obtuvo un token");
				result = false;
			}
				

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	public boolean cambioDePIN(Reports report, DriverManager DM, int iteration, String name, String configEntidad, String cuentas_generales) {
		
		//Validation var
				boolean result = true;
				try {	
					//Separador
					System.out.println("\r\n##################################################################################################################################################################################################################"
									 + "##################################################################################################################################################################################################################\r\n");
					
					System.out.println("Configuring TC_21_API_PP\r\n");
					
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
	public boolean blanqueoDePin(Reports report, DriverManager DM, int iteration, String name, String configEntidad, String cuentas_generales) {
		//Validation var
				boolean result = true;
				try {
					//Separador
					System.out.println("\r\n##################################################################################################################################################################################################################"
									 + "##################################################################################################################################################################################################################\r\n");
					
					System.out.println("Configuring TC_23_API_PP\r\n");
					
					//SET VARIABLES
					String token = "";
					String resp = "";
					int rta = 0;
					String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
					String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
					String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
					Response response;
					
					//SET INSTANCES
					apiWorker apiResp = new apiWorker();
					dbWorker oraResp = new dbWorker();
					Repo_Variables repoVar = new Repo_Variables();
					PrePostCondi ppCondi = new PrePostCondi();
					
					//SET ENVIRONMENT
					//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
					String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
					repoVar.setBaseUri(base_url);
					
					System.out.println("##[command] : <------ Initializating Test: " + name + " ------>\r\n");
					report.AddLine("<------ Initializating Test: " + name + " ------>");
					
					//PRECONDICION
					report.AddLine("--- Ejecutando PreCondicion ---");
					System.out.println("--- Ejecutando PreCondicion ---\r\n");
					
					rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET PIN_INVALIDOS = 3 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
					ppCondi.preCondicionBD(report, rta);

					//GET TOKEN
					try {
						token = apiResp.GetAccessTokenPP(configEntidad);
					} catch (Exception E) {
						report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
						System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
					}
					
					//PUT - Cambio de estado Tarjeta - NormalHabilitada
					if (!token.isEmpty())
					{
						response = apiResp.putMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/Pin/", "BlanquearReIntentos", token);
						//Se reporta el Status Code
						report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
						System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
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
							
					//ADD VALIDATIONS
					System.out.println("Se realizan las validaciones en la BD:\r\n");
					report.AddLine("Se realizan las validaciones en la BD:");
					
					resp = oraResp.oraOneQuery(report, "SELECT PIN_INVALIDOS FROM TARJETAS WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
					
					if(!resp.equals("0")) {
						report.AddLineAssertionError("Error: El PIN INVALIDO es: " + resp + "; se esperaba el estado: 0");
						System.out.println("##[warning] : Error: El PIN INVALIDO es: " + resp + "; se esperaba el estado: 0\r\n");
						//Si falla la validacion el resultado de la prueba es false
						result = false;	
					} else {
						report.AddLine("Validacion exitosa:<br>El campo PIN_INVALIDO es: " + resp + " - Se blanqueo correctamente los intentos fallidos de PIN");
						System.out.println("##[section] : Validacion exitosa: El campo PIN_INVALIDO es: " + resp + " - Se blanqueo correctamente los intentos fallidos de PIN\r\n");
					}
					
					//ROLLBACK
					report.AddLine("--- Ejecutando PostCondicion ---");
					System.out.println("--- Ejecutando PostCondicion ---\r\n");
					
					rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET PIN_INVALIDOS = 0 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
					ppCondi.validaRollBackUpdate(report, rta);
					
					System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
					report.AddLine("<------ Finished Test: " + name + " ------>");
					
					//Separador
					System.out.println("##################################################################################################################################################################################################################"
							 + "##################################################################################################################################################################################################################\r\n");
							
					
				} catch (Exception e) {
					e.printStackTrace();
					report.AddLineAssertionError(e.getStackTrace()[0].toString());
					report.AddLineAssertionError(e.getMessage());
					
		             //Se agrega de nuevo ROLLBACK para que no quede la base inconsistente si falla el TestCase  
					
					//SET VARIABLES 
					int rta = 0;
					dbWorker oraResp = new dbWorker();
					PrePostCondi ppCondi = new PrePostCondi();
					String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
					
					//ROLLBACK
					report.AddLine("--- Ejecutando PostCondicion ---");
					System.out.println("--- Ejecutando PostCondicion ---\r\n");
					
					rta = oraResp.oraUpdate(report, "UPDATE TARJETAS SET PIN_INVALIDOS = 0 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
					ppCondi.validaRollBackUpdate(report, rta);
					
					result = false;
				}
				//return the test result
				return result;

			}
	
	public boolean modificacionDomicilioCorrespondencia(Reports report, DriverManager DM, int iteration, String name, String configEntidad, String cuentas_generales) {
		//Validation var
				boolean result = true;
				try {	
					//Separador
					System.out.println("\r\n##################################################################################################################################################################################################################"
									 + "##################################################################################################################################################################################################################\r\n");
					
					System.out.println("Configuring TC_24_API_PP\r\n");
					
					//SET VARIABLES
					String path = "./API_Requests/PrePagas/TC_24_Editar_Datos_De_Cuenta_Domicilios.txt";
					String token = "";
					String bodyData = "";
					String resp = "";
					int rta = 0;
					String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_VIRTUAL_3.cuenta_virtual");
					String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
					Response response;
					
					//SET INSTANCES
					apiWorker apiResp = new apiWorker();
					dbWorker oraResp = new dbWorker();
					Repo_Variables repoVar = new Repo_Variables();
					PrePostCondi ppCondi = new PrePostCondi();
					
					//SET ENVIRONMENT
					//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
					String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
					repoVar.setBaseUri(base_url);
					
					System.out.println("##[command] : <------ Initializating Test: " + name + " ------>\r\n");
					report.AddLine("<------ Initializating Test: " + name + " ------>");
					
					//PRECONDICION
					report.AddLine("--- Ejecutando PreCondicion ---");
					System.out.println("--- Ejecutando PreCondicion ---\r\n");
					
					rta = oraResp.oraUpdate(report, "UPDATE DOMICILIOS\r\n"
							+ "SET ID_PAIS = 32, ID_PROVINCIA = 2, ID_CODIGO_POSTAL = 5728, DIRECCION = 'San Marco', NUMERO = 5896, PISO = 3, DEPTO = 'A',\r\n"
							+ "REFERENCIA = 'Oficinas GP', TELEFONO = '111561475812', LOCALIDAD = 'Ezeiza' \r\n"
							+ "WHERE ID_DOMICILIO IN ((SELECT ID_DOMICILIO_LEGAL FROM PERSONAS WHERE ID_PERSONA = (SELECT ID_PERSONA FROM SOCIOS WHERE ID_CUENTA = " + ID_CUENTA + ")), (SELECT ID_DOMICILIO_CORRESPONDENCIA FROM CUENTAS WHERE ID_CUENTA = " + ID_CUENTA + "))\r\n"
							+ "", configEntidad);
					ppCondi.preCondicionBD(report, rta);

					//GET TOKEN
					try {
						token = apiResp.GetAccessTokenPP(configEntidad);
					} catch (Exception E) {
						report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
						System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
					}
					
					//GET POST BODY FROM FILE
					try {
						bodyData = new String(Files.readAllBytes(Paths.get(path)));	
						
						//Se muestra el body en el reporte
						report.AddLine("Request body:<br>" + bodyData);
						
					} catch (Exception E) {
						report.AddLine("Error al abrir el archivo.<br>Error: " + E);
						System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
					}

					//PUT - Cambio de estado Tarjeta - NormalHabilitada
					if (!bodyData.isEmpty() && !token.isEmpty())
					{
						response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA, "", bodyData, token);
						//Se reporta el Status Code
						report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
						System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
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
					
					//ADD VALIDATIONS
					System.out.println("Se realizan las validaciones en la BD:\r\n");
					report.AddLine("Se realizan las validaciones en la BD:");
					
					resp = oraResp.oraOneQuery(report, "SELECT COUNT (P.ID_PERSONA) FROM PERSONAS P \r\n"
							+ "INNER JOIN DOMICILIOS D ON D.ID_DOMICILIO = P.ID_DOMICILIO_LEGAL \r\n"
							+ "INNER JOIN CUENTAS C ON D.ID_DOMICILIO = C.ID_DOMICILIO_CORRESPONDENCIA \r\n"
							+ "WHERE  P.ID_PERSONA = (SELECT ID_PERSONA FROM SOCIOS WHERE ID_CUENTA = " + ID_CUENTA + ") \r\n"
							+ "AND D.ID_PROVINCIA = 4 \r\n"
							+ "AND D.ID_CODIGO_POSTAL = 8887 \r\n"
							+ "AND D.DIRECCION = 'Chopin' \r\n"
							+ "AND D.NUMERO = 24 \r\n"
							+ "AND D.PISO = 3 \r\n"
							+ "AND D.DEPTO = 'A' \r\n"
							+ "AND D.REFERENCIA = 'Oficinas GP' \r\n"
							+ "AND D.TELEFONO = 11147896325 \r\n"
							+ "AND D.LOCALIDAD = 'CABA'", configEntidad);
					
					if(!resp.equals("1")) {
						report.AddLineAssertionError("Error: No se modificaron los datos en las tablas PERSONAS y DOMICILIOS");
						System.out.println("##[warning] : Error: No se modificaron los datos en las tablas PERSONAS y DOMICILIOS\r\n");
						//Si falla la validacion el resultado de la prueba es false
						result = false;	
					} else {
						report.AddLine("Validacion exitosa:<br>Se modificaron correctamente los datos en las tablas PERSONAS y DOMICILIOS");
						System.out.println("##[section] : Validacion exitosa:\r\nSe modificaron correctamente los datos en las tablas PERSONAS y DOMICILIOS\r\n");
					}
					
					//ROLLBACK
					report.AddLine("--- Ejecutando PostCondicion ---");
					System.out.println("--- Ejecutando PostCondicion ---\r\n");
					
					rollBack2(report, oraResp, ppCondi, configEntidad, cuentas_generales);
					
					System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
					report.AddLine("<------ Finished Test: " + name + " ------>");
					
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
			
			private void rollBack2(Reports report, dbWorker oraResp, PrePostCondi ppCondi, String configEntidad, String cuentas_generales) {
				//Variables
				int rta;
				String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_VIRTUAL_3.cuenta_virtual");
				
				//Operacion 1
				rta = oraResp.oraUpdate(report, "UPDATE DOMICILIOS\r\n"
						+ "SET ID_PAIS = 32, ID_PROVINCIA = 2, ID_CODIGO_POSTAL = 5728, DIRECCION = 'San Marco', NUMERO = 5896, PISO = 3, DEPTO = 'A',\r\n"
						+ "REFERENCIA = 'Oficinas GP', TELEFONO = '111561475812', LOCALIDAD = 'Ezeiza' \r\n"
						+ "WHERE ID_DOMICILIO IN ((SELECT ID_DOMICILIO_LEGAL FROM PERSONAS WHERE ID_PERSONA = (SELECT ID_PERSONA FROM SOCIOS WHERE ID_CUENTA = " + ID_CUENTA + ")), (SELECT ID_DOMICILIO_CORRESPONDENCIA FROM CUENTAS WHERE ID_CUENTA = " + ID_CUENTA + "))\r\n"
						+ "", configEntidad);
				ppCondi.postCondicionBD(report, rta);
			}
			
		

	
	
	
	public boolean consultaDeDisponible(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			//String jsonFileV = "";
			//String bodyData = "";
			//String respBody = "";
			Response respBody;
			String responseData = "";
			//boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			
 	
			//SET INSTANCES
 			
 			apiWorker apiResp = new apiWorker();
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			
		
			
			//SET ENVIRONMENT
			AI.setEndpoint(report, repoVar, endPoint);
			                                 

			System.out.println("<------ Initializating Test: " + name + " ------>\r\n");

			token = getToken(report, apiWorker);
			
			System.out.println(token);

		
			//POST - Con token y Body OK
			
		
			if (!token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				respBody = apiResp.getMethodConsultas(repoVar, endPoint, token);
				
				//Se reporta el Status Code
				report.AddLine("Status Code: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("Status Code: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
			
			//Se valida el status code
			if(respBody.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(respBody.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(respBody.getStatusCode()), equalTo(statusCodeEsperado));
				}

				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode 
				String responseBody = respBody.getBody().asPrettyString();
				 
				// Parsea la cadena JSON en un objeto JsonPath
				 JsonPath jsonPath = new JsonPath(responseBody);
				 
				// responseData = jsonPath.getString("DisponibleCompra");
				 responseData = jsonPath.getString("Data");
				 	
				System.out.println("El resultado de la consulta es :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response resultado de la consulta: " + responseData);
					System.out.println("Response resultado de la consulta: " + responseData + "\r\n");
										
					System.out.println("##################################################################################################################################################################################################################"
							+ "##################################################################################################################################################################################################################\r\n");

					
				} else {
					report.AddLineAssertionError("Response code data FAIL!!: " + responseData);
					System.out.println("Response code data FAIL!!: " + responseData + "\r\n");
					result = false;
				}

				// POSTCONDICION

			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
				
				System.out.println("<------ Finished Test: " + name + " ------>\r\n");
				//Separador
				System.out.println("##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

			}	
		

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;

	}
	                                //validacionGral(oraResp, report, responseData, entidad, validaciones);
	
	private boolean validacionGral(dbWorker oraResp, Reports report, String responseData, String entidad, JsonArray validaciones) throws SQLException {
		//Variables
		boolean result = true;
		String queryVerf;
		
		System.out.println("##### INICIO EJECUCION DE VALIDACIONES #####");
		report.AddLine("##### INICIO EJECUCION DE VALIDACIONES #####");
		
		for(JsonElement validacion : validaciones) {
			JsonObject validacionObject = validacion.getAsJsonObject();
			queryVerf = validacionObject.get("query").getAsString();
			//queryVerf = queryVerf.replace("{{esquema}}", esquema);
			queryVerf = queryVerf.replace("{{id_cuenta}}", responseData);
			JsonArray camposEsperados = validacionObject.get("camposEsperados").getAsJsonArray();
			JsonArray resultadosEsperados = validacionObject.get("valoresEsperados").getAsJsonArray();
			
			System.out.println("Query: " + queryVerf);
			System.out.println("Campos Esperados: " + camposEsperados);
			System.out.println("ResultadosEsperados: " + resultadosEsperados);
			System.out.println("ResultadosEsperados Size: " + resultadosEsperados.size());
			System.out.println("ResultadosEsperados Size: " + resultadosEsperados.get(0));
			System.out.println("ResultadosEsperados Size: " + resultadosEsperados.get(0).getAsJsonArray().get(0));
			System.out.println();
			System.out.println();
			
			Validacion(oraResp, report, queryVerf, resultadosEsperados, entidad);
			
			System.out.println("Se sale?");
		}


		//Verificacion de todos los resultados obtenidos
		for(String state : Status)
		if (!state.equals("P")) {
			report.AddLineAssertionError("===== " + Status + " =====");;
			System.out.println("===== " + Status + " =====");
			result = false;
		}

		System.out.println("##### FIN DE EJECUCION DE VALIDACIONES #####");
		report.AddLine("##### FIN DE EJECUCION DE VALIDACIONES #####");

		return result;
	}
	
	private void Validacion(dbWorker oraResp, Reports report , String queryVerf, JsonArray resultadosEsperados, String entidad) throws SQLException {
		
		String validaRes[][];
		
		
		validaRes = oraResp.executeQuery(queryVerf);
		
		String resultados[][] = new String[validaRes.length][validaRes[0].length];
		
		for(JsonElement fila : resultadosEsperados) {
			int i = 0;
			System.out.println("Fila: " + fila);
			for(JsonElement valor : fila.getAsJsonArray()) {
				int j = 0;
				System.out.println("Valor: " + valor.getAsString());
				resultados[i][j] = valor.getAsString();
				j++;
			}
			i++;
		}
		
		System.out.println();
		
		for(int i = 0; i < validaRes.length; i++) {
			for(int j = 0; j < validaRes[i].length; j++) {
				System.out.println("validaRes: " + validaRes[i][j] + " - resultadoEsperado: " + resultadosEsperados.get(i).getAsJsonArray().get(j).getAsString());
				if(validaRes[i][j].equals(resultadosEsperados.get(i).getAsJsonArray().get(j).getAsString())) {
					report.AddLine("Ejecucion Correcta:<br>Se cumplieron todas las validaciones");
					System.out.println("##[section] : Ejecucion Correcta:\r\nSe cumplieron todas las validaciones\r\n");
					Status.add("P");
				}else {
					report.AddLineAssertionError("FAIL<br>No se cumplieron todas las validaciones");
					System.out.println("##[warning] : FAIL:\r\nNo se cumplieron todas las validaciones\r\n");
					Status.add("FAIL - Cantidad de resultados");
				}
			}
		}
	}
	
	


	private String getToken(Reports report, apiWorker apiWorker) {
		String token = ""; 
		
		try {
			token = apiWorker.GetAccessTokenA();
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.\r\nError: " + E);
			System.out.println("No se obtuvo un token.\r\nError: " + E);
		}
		return token;
	}
}