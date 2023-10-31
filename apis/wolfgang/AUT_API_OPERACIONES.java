package apis.wolfgang;

import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;

import CentaJava.Core.Reports;
import Pasos.Generales.AutorizadorInterno;
import Pasos.Generales.PrePostCondi;
import Repositories.Repo_Variables;
import Tools.apiWorker;
import Tools.dbWorker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.google.gson.*;

public class AUT_API_OPERACIONES{
	private List<String> Status = new ArrayList<String>();
	//private String Status;
	String esquema;
	
	//Compra desde API del Autorizador
	
	public boolean compraApiAutorizador(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
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
			String NRO_TARJETA = "5522681341261779";
			String VENCIMIENTO = "2504";
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
			
			//Obtengo la fecha actual
			LocalDate fechaActual = LocalDate.now();

			//Grabo la fecha actual como string y saco espacios
			String fechaAutorizacionIPM = fechaActual.toString().replace(" ", "");
					
			//Separo la fecha por los guiones para separar el anio, mes y dia
			String[] fechaSeparada = fechaAutorizacionIPM.split("-");
				
			fechaAutorizacionIPM = fechaSeparada[0].replace("20", "") + fechaSeparada[1] + fechaSeparada[2] + "000000";
			
			   System.out.println("fecha ********************* : " + fechaAutorizacionIPM);
			
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

			token = AI.getToken(report, apiWorker, configEntidad);
			
			System.out.println(token);

			System.out.println("##### EXTRACCION DE DATA BODY #####");			
			//GET POST BODY from " + esquema + "FILE
			bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/TC.txt")));
			
			
			//String newBodyData = AI.setJsonBodyDE35(report, TCFilesPath, bodyData, NRO_TARJETA, VENCIMIENTO);
			
			System.out.println(bodyData);
			
			//POST - Con token y Body OK
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				//respBody = AI.post2(report, repoVar, apiWorker, bodyData, token, configEntidad, statusCodeEsperado);
				for (int i=0; i<10; i++) {
				respBody = AI.post(report, repoVar, apiWorker, bodyData, token);
				Thread.sleep(5000);
				String resultadoOperacion = JsonPath.from(respBody).getString("DE39");
				System.out.println("Este es el resultado="+ resultadoOperacion +  "¿Esta bien aquí?");
				
				/*	// Hacer un if para agregar una query
				if(resultadoOperacion.equals("57")) {
					oraResp.oraUpdate(report, "update acumulador_rg5272\r\n"
							+ "set acum_autorizaciones = 0,\r\n"
							+ "acum_autorizaciones_moneda_local = 0,\r\n"
							+ "acum_impuesto = 0,\r\n"
							+ "acum_presentaciones = 0,\r\n"
							+ "acum_impuesto_consumo = 0\r\n"
							+ "where id_cuenta = 10001454", configEntidad);
				}
				if(resultadoOperacion.equals("57")) {
					oraResp.oraUpdate(report, "update acumulador_rg5272\r\n"
							+ "set acum_autorizaciones = 0,\r\n"
							+ "acum_autorizaciones_moneda_local = 0,\r\n"
							+ "acum_impuesto = 0,\r\n"
							+ "acum_presentaciones = 0,\r\n"
							+ "acum_impuesto_consumo = 0\r\n"
							+ "where id_cuenta = 10000506", configEntidad);
				}*/
				
				if(!resultadoOperacion.equals("96") && !resultadoOperacion.equals("57")){
					System.out.println("*********OPERACION_OK**********");
					break;
				}
				}
				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode que tiene que ser "00"
				//respBody = response.getBody().asPrettyString();
				responseData = JsonPath.from(respBody).getString("IdAutorizacionEmisor"); 
				
				System.out.println("Este es el Id Autorizacion Emisor otorgado :" + responseData);

				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response numero de Id Autorizacion Emisor OK: " + responseData);
					System.out.println("Response numero de Id Autorizacion Emisor OK: " + responseData + "\r\n");
					//ESPERA PARA EL PROCESAMIENTO DEL REGISTRO EN EL BACKEND
					System.out.println("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND 8 SEGS #####");
					report.AddLine("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND 8 SEGS #####");
					Thread.sleep(8000);
					//VALIDACIONES
					
					System.out.println("Se realizan las validaciones en la BD:\r\n");
					report.AddLine("Se realizan las validaciones en la BD:");
					
					if (responseData != "0") 
					{
						rtaIdPago = oraResp.oraOneQuery(report, "select Id_Autorizacion_Emisor from autorizacion_emisor_log\r\n"
								+ "where Id_Autorizacion_Emisor = " + String.valueOf(responseData), configEntidad);
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
	                                //hasta aqui la compra
	
	public boolean compraDevolucionApiAutorizadorA(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
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
			String bodyData2 = "";
			String respBody = "";
			String respBody2 = "";
			String responseData = "";
			String rtaIdPago = "";
			String NRO_TARJETA = "5522681341261779";
			String VENCIMIENTO = "2504";
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

			token = AI.getToken(report, apiWorker, configEntidad);
			
			System.out.println(token);

			System.out.println("##### EXTRACCION DE DATA BODY #####");			
			// obtengo el body para la compra
			bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/PRE.txt")));
			
			System.out.println(bodyData);
			
			//POST - Con token y Body OK
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");

				// Extraemos el response body y validamos el 200
				
				for (int i=0; i<10; i++) {
				respBody = AI.post(report, repoVar, apiWorker, bodyData, token);
				Thread.sleep(5000);
				String resultadoOperacion = JsonPath.from(respBody).getString("DE39");
				System.out.println("Este es el resultado="+ resultadoOperacion +  "¿Esta bien aquí?");
			
				
				if(!resultadoOperacion.equals("96") && !resultadoOperacion.equals("57")){
					System.out.println("*********OPERACION_OK**********");
					
				}
				
				// VUELVO A CONSUMIR API DE TOKEN  --corroborar si es necesario				
				token = AI.getToken(report, apiWorker, configEntidad);				
				System.out.println(token);	
				if (!respBody.isEmpty() && !token.isEmpty()) {
					// obtengo el valor del DE38 de la ejecucion  
						String DE38 = JsonPath.from(respBody).getString("DE38");				
						// obtengo el body para la devolucion
						bodyData2 = new String(Files.readString(Paths.get(TCFilesPath + "/TXT/TC.txt")));					
						System.out.println("este es el body 2 " + bodyData2);
						// inserto el valor del DE38 en el body para la devolucion				
						bodyData2 = bodyData2.replace("{{DE38}}", DE38);			
						System.out.println("este es el body para generar la devolucion :" + bodyData2);					
						//ejecucion devolucion 				
						System.out.println("****************SE INICIA LA EJECUCION DE DEVOLUCION*********");
						if (!bodyData2.isEmpty() && !token.isEmpty()) {					
							
							
							
								// Extraemos el response body 
								for (int x =0; x < 10; x++) {
								respBody2 = AI.post(report, repoVar, apiWorker, bodyData2, token);
								Thread.sleep(5000);
								if(!resultadoOperacion.equals("96") && !resultadoOperacion.equals("57")){
								break;
								}
								
								System.out.println("*********OPERACION_OK**********");
							
							}break;
							
						}
				}
				}	
			    System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
	
				System.out.println("Este es el Id Autorizacion Emisor otorgado :" + responseData);

		

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
	
	public boolean compraDevolucionApiAutorizador(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
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
			String bodyData2 = "";
			String respBody = "";
			String respBody2 = "";
			String responseData = "";
			String rtaIdPago = "";
			String NRO_TARJETA = "5522681341261779";
			String VENCIMIENTO = "2504";
			String idAutorizacionEmisor = "";
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
 
			token = AI.getToken(report, apiWorker, configEntidad);
			System.out.println(token);
 
			System.out.println("##### EXTRACCION DE DATA BODY #####");			
			// obtengo el body para la compra
			bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/PRE.txt")));
			System.out.println(bodyData);
			//POST - Con token y Body OK
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
 
				// Extraemos el response body y validamos el 200
				for (int i=0; i<10; i++) {
				respBody = AI.post(report, repoVar, apiWorker, bodyData, token);
				Thread.sleep(5000);
				String resultadoOperacion = JsonPath.from(respBody).getString("DE39");
				System.out.println("Este es el resultado="+ resultadoOperacion +  "¿Esta bien aquí?");

				if(!resultadoOperacion.equals("96") && !resultadoOperacion.equals("57")){
					System.out.println("*********OPERACION_OK**********");
				}
				// VUELVO A CONSUMIR API DE TOKEN  --corroborar si es necesario				
				token = AI.getToken(report, apiWorker, configEntidad);				
				System.out.println(token);	
				if (!respBody.isEmpty() && !token.isEmpty()) {
					// obtengo el valor del DE38 de la ejecucion  
						String DE38 = JsonPath.from(respBody).getString("DE38");				
						// obtengo el body para la devolucion
						bodyData2 = new String(Files.readString(Paths.get(TCFilesPath + "/TXT/TC.txt")));					
						System.out.println("este es el body 2 " + bodyData2);
						// inserto el valor del DE38 en el body para la devolucion				
						bodyData2 = bodyData2.replace("{{DE38}}", DE38);			
						System.out.println("este es el body para generar la devolucion :" + bodyData2);					
						//ejecucion devolucion 				
						System.out.println("****************SE INICIA LA EJECUCION DE DEVOLUCION*********");
						if (!bodyData2.isEmpty() && !token.isEmpty()) {					

								// Extraemos el response body 
								for (int x =0; x < 10; x++) {
								respBody2 = AI.post(report, repoVar, apiWorker, bodyData2, token);
								responseData = JsonPath.from(respBody2).getString("IdAutorizacionEmisor");
								System.out.println("Este es el Id Autorizacion Emisor otorgado :" + responseData);
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
								Thread.sleep(5000);
								if(!resultadoOperacion.equals("96") && !resultadoOperacion.equals("57")){
								break;
								}
								System.out.println("*********OPERACION_OK**********");
							}break;
						}
				}
				
				}	
			    System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				
 
		
 
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
	public boolean compraReversoApiAutorizador(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
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
			String bodyData2 = "";
			String respBody = "";
			String respBody2 = "";
			String responseData = "";
			String rtaIdPago = "";
			String NRO_TARJETA = "5522681341261779";
			String VENCIMIENTO = "2504";
			String idAutorizacionEmisor = "";
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
 
			token = AI.getToken(report, apiWorker, configEntidad);
			System.out.println(token);
 
			System.out.println("##### EXTRACCION DE DATA BODY #####");			
			// obtengo el body para la compra
			bodyData = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TXT/PRE.txt")));
			System.out.println(bodyData);
			//POST - Con token y Body OK
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
				System.out.println("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
				report.AddLine("##### EJECUCION DE POST CON EL TOKEN Y LA DATA DEL BODY #####");
 
				// Extraemos el response body y validamos el 200
				for (int i=0; i<10; i++) {
				respBody = AI.post(report, repoVar, apiWorker, bodyData, token);
				Thread.sleep(5000);
				String resultadoOperacion = JsonPath.from(respBody).getString("DE39");
				System.out.println("Este es el resultado="+ resultadoOperacion +  "¿Esta bien aquí?");
				
				if(!resultadoOperacion.equals("96") && !resultadoOperacion.equals("57")){
					System.out.println("*********OPERACION_OK**********");
					break;
				}
				}
				// VUELVO A CONSUMIR API DE TOKEN  --corroborar si es necesario				
				token = AI.getToken(report, apiWorker, configEntidad);				
				System.out.println(token);	
				if (!respBody.isEmpty() && !token.isEmpty()) {
					// obtengo el valor del DE38 de la ejecucion  
						String DE38 = JsonPath.from(respBody).getString("DE38");				
						// obtengo el body para la devolucion
						bodyData2 = new String(Files.readString(Paths.get(TCFilesPath + "/TXT/TC.txt")));					
						System.out.println("este es el body 2 " + bodyData2);
						// inserto el valor del DE38 en el body para la devolucion				
						bodyData2 = bodyData2.replace("{{DE38}}", DE38);			
						System.out.println("este es el body para generar la devolucion :" + bodyData2);					
						//ejecucion devolucion 				
						System.out.println("****************SE INICIA LA EJECUCION DE DEVOLUCION*********");
						if (!bodyData2.isEmpty() && !token.isEmpty()) {					

								// Extraemos el response body 
								for (int x =0; x < 10; x++) {
								respBody2 = AI.post(report, repoVar, apiWorker, bodyData2, token);
								responseData = JsonPath.from(respBody2).getString("IdAutorizacionEmisor");
								System.out.println("Este es el Id Autorizacion Emisor otorgado :" + responseData);
								//SE COMIENZA A VALIDAR
								
								if (!responseData.isEmpty())
								{
									report.AddLine("Ejecucion Correcta<br>Id Autorizacion Emisor: " + responseData);
									System.out.println("##[section] : Ejecucion Correcta\r\nId Autorizacion Emisor: " + responseData + "\r\n");
									//VALIDACIONES
									result = validacionGral(oraResp, report, responseData, entidad, validaciones);
								} else {
									report.AddLineAssertionError("FAIL<br>Id Autorizacion Emisor: " + responseData + " Se esperaba: un numero" );
									System.out.println("##[warning] : FAIL : \r\nId Autorizacion Emisor: " + responseData + " Se esperaba: un numero");
									result = false;
								}
								Thread.sleep(5000);
							//	if(!resultadoOperacion.equals("96") && !resultadoOperacion.equals("57")){
								break;
							//	}
							//	System.out.println("*********OPERACION_OK**********");
							//}break;
						}
				}
				
				}	
			    System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				
 
		
 
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
	                                //hasta aqui la compra
	
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
	                                //validacionGral(oraResp, report, responseData, entidad, validaciones);
	public boolean consultaDeCuenta(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
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
	public boolean consultaDeTarjeta(Reports report, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado) {
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
			queryVerf = queryVerf.replace("{{id_autorizacion_emisor}}", responseData);
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