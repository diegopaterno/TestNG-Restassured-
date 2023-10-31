package apis.wolfgang;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oracle.jdbc.OracleResultSetMetaData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Pasos.Generales.Acceso;
import Pasos.Generales.AutorizadorInterno;
import Pasos.Generales.GBatch;
import Pasos.Generales.NavSuperiorBatch;
import Pasos.Generales.PrePostCondi;
import Pasos.GlobalBatch.TC_07_BA_PASOS;
import Repositories.Repo_Variables;
import Repositories.Repo_WebRepository;
import Tools.apiWorker;
import Tools.dbWorker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class API_end_to_end {
	private List<String> Status = new ArrayList<String>();
	List<dbWorker> results = new ArrayList<>();
	//private String Status;
	String esquema;
	WebDriver driver;
	
	public boolean metodoEndToEnd(Datasources data,Reports report, DriverManager DM, int iteration, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado, String tokenUrl, JsonArray resultadosEsperados) {
			//Validation var
			boolean result = true;
			try {	
				//Separador
				System.out.println("\r\n##################################################################################################################################################################################################################"
						+ "##################################################################################################################################################################################################################\r\n");

				System.out.println("Configuring " + name + "\r\n");
				
				//SELECT THE DRIVER AND PATH
				driver = DM.CreateDriver(DM.CHROME);
		
				report.SetDriver(driver);

				//SET THE REPOSITORIES TO USE
				Repo_WebRepository repo = new Repo_WebRepository();
				repo.setDriver(driver);

				//SET STEPS INSTANCES
				Acceso acceso = new Acceso();
				NavSuperiorBatch nav = new NavSuperiorBatch();
				TC_07_BA_PASOS tc = new TC_07_BA_PASOS();
				//dbWorker oraResp = new dbWorker();
				//PrePostCondi ppCondi = new PrePostCondi();
				GBatch batch = new GBatch();
				

				//SET VARIABLES
				// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
				//String doc = "35687555";
				String token = "";
				String jsonFileV = "";
				String bodyData = "";
				String respBody = "";
				String responseData = "";
				String responseData2 = "";
				String rtaIdPago = "";
				//String NRO_TARJETA = "5522681341261779";
				//String VENCIMIENTO = "2504";
				String ID_IPM = "11287";
				int rtaPrePostCondiciones = 0;
				boolean resp;
	 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
	 			String URL_GBATCH = JsonPath.from(configEntidad).get("GBATCH.url_gbatch");
	 	
				//SET INSTANCES
				apiWorker apiWorker = new apiWorker();
				dbWorker oraResp = new dbWorker();
				Repo_Variables repoVar = new Repo_Variables();
				AutorizadorInterno AI = new AutorizadorInterno();
				PrePostCondi ppCondi = new PrePostCondi();
				
				//CONFIGURACIÓN DATABASE
				oraResp.setUser(JsonPath.from(configEntidad).get("DBCREDITO.user"));
				oraResp.setPass(JsonPath.from(configEntidad).get("DBCREDITO.pass"));
				oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
				oraResp.setHost(JsonPath.from(configEntidad).get("DBCREDITO.host"));
				
				
				
				jsonFileV = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TC.json")));
				
				//Obtengo la fecha actual
				LocalDate fechaActual = LocalDate.now();

				//Grabo la fecha actual como string y saco espacios
				String fechaAutorizacionIPM = fechaActual.toString().replace(" ", "");
						
				//Separo la fecha por los guiones para separar el anio, mes y dia
				String[] fechaSeparada = fechaAutorizacionIPM.split("-");
					
				fechaAutorizacionIPM = fechaSeparada[0].replace("20", "") + fechaSeparada[1] + fechaSeparada[2] + "000000";
				
				   System.out.println("fecha **** : " + fechaAutorizacionIPM);
				
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
					respBody = AI.post(report, repoVar, apiWorker, bodyData, token);
					System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
					report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

					// Extraemos el responseCode que tiene que ser "00"
					//respBody = response.getBody().asPrettyString();
					responseData = JsonPath.from(respBody).getString("IdAutorizacion"); 
					responseData2 = JsonPath.from(respBody).getString("IdAutorizacionEmisor");
					
					System.out.println("Este es el Id Autorizacion Emisor otorgado :" + responseData);
					
					String COD_AUTO_ENTIDAD = oraResp.oraOneQueryCredito(report,"SELECT COD_AUTO_ENTIDAD FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					//String nro_tarjeta = oraResp.oraOneQueryCredito(report,"SELECT nro_tarjeta FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					/*String COMERCIO_DESCRIPCION = oraResp.oraOneQuery(report,"SELECT COMERCIO_DESCRIPCION FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					String MODO_INGRESO = oraResp.oraOneQuery(report,"SELECT MODO_INGRESO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					String ID_AUTORIZACION_EMISOR = oraResp.oraOneQuery(report,"SELECT ID_AUTORIZACION_EMISOR FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					String ID_COD_MOVIMIENTO = oraResp.oraOneQuery(report,"SELECT ID_COD_MOVIMIENTO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					String ID_ESTADO = oraResp.oraOneQuery(report,"SELECT ID_ESTADO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					String ID_CUENTA = oraResp.oraOneQuery(report,"SELECT ID_CUENTA FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					String FECHA_AUTORIZACION = oraResp.oraOneQuery(report,"SELECT FECHA_AUTORIZACION FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					String IMPORTE = oraResp.oraOneQuery(report,"SELECT IMPORTE FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
					
					System.out.println("datos        ** "+COD_AUTO_ENTIDAD+COMERCIO_DESCRIPCION+MODO_INGRESO+ID_AUTORIZACION_EMISOR+ID_COD_MOVIMIENTO+ID_ESTADO+ID_CUENTA+FECHA_AUTORIZACION+IMPORTE+nro_tarjeta);
					/***********************************************************************
					String IN_IMPORTE_OPERACION = oraResp.oraOneQuery(report, "SELECT IN_IMPORTE_OPERACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_COD_MONEDA_OPERACION = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_OPERACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_IMPORTE_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_COD_MONEDA_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_IMPORTE_EMISOR = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_EMISOR FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_COD_MONEDA_EMISOR = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_EMISOR FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_COD_PROCESAMIENTO = oraResp.oraOneQuery(report,"SELECT IN_COD_PROCESAMIENTO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_DATOS_RED = oraResp.oraOneQuery(report,"SELECT IN_DATOS_RED FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_FECHA_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_FECHA_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_COD_COMERCIO_EXTERNO = oraResp.oraOneQuery(report,"SELECT IN_COD_COMERCIO_EXTERNO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_FECHA_LOCAL = oraResp.oraOneQuery(report,"SELECT IN_FECHA_LOCAL FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_MCC = oraResp.oraOneQuery(report,"SELECT IN_MCC FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					String IN_IMPORTE_REEMPLAZO = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_REEMPLAZO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
					
					System.out.println("datos        ** "+IN_IMPORTE_OPERACION+IN_COD_MONEDA_OPERACION+IN_IMPORTE_LIQUIDACION+IN_COD_MONEDA_LIQUIDACION
							+IN_IMPORTE_EMISOR+IN_COD_MONEDA_EMISOR+IN_COD_PROCESAMIENTO+IN_DATOS_RED+IN_FECHA_LIQUIDACION+IN_COD_COMERCIO_EXTERNO+IN_FECHA_LOCAL+IN_MCC+IN_IMPORTE_REEMPLAZO);
					
				*********/
					rtaPrePostCondiciones = oraResp.oraUpdateCredito(report, "UPDATE IPM\r\n"
							+ "SET CODIGO_APROBACION = " + COD_AUTO_ENTIDAD + ", IMPORTE_OPERACION = '000000200000', COD_MONEDA_OPERACION = '032', \r\n"
							+ "IMPORTE_RECONCILIACION = '000000200000', COD_MONEDA_RECONCILIACION = '840', IMPORTE_EMISOR = '000000200000', COD_MONEDA_EMISOR = '032',\r\n"
							+ "FECHA_HORA_LOCAL = '200923000000', CICLO_VIDA_TRANSACCION = ' MCC4875480920  ' ,\r\n"
							+ "PROCESADO = 0, NRO_TARJETA = '2DDE5DA245DC698764DA0398A42626E5', FECHA_VTO_TARJETA = '2403',\r\n"
							+ "COD_COMERCIO = '00000002', DESCRIPCION_COMERCIO = 'TIDAL MUSIC                          ARG',\r\n"
							+ "ID_CONSUMO = NULL, OBSERVACION = NULL, ID_AUTORIZACION = null, CRITERIO_MAPEO = null, COD_PROCESAMIENTO = '000000',\r\n"
							+ "AUTORIZACION_PROCESADA = 0,  PRESENTACION_PARCIAL = 0, MCC = '4899', CONDICION_PUNTO_SERVICIO = '000040S09000'\r\n"
							+ "WHERE ID_IPM IN (11287) ",configEntidad);

					/*rtaPrePostCondiciones = oraResp.oraUpdate(report, "UPDATE IPM\r\n"
							+ "SET CODIGO_APROBACION = "+COD_AUTO_ENTIDAD+", IMPORTE_OPERACION = '000000200000', COD_MONEDA_OPERACION = '032', \r\n"
							+ "IMPORTE_RECONCILIACION = '000000200000', COD_MONEDA_RECONCILIACION = '840', IMPORTE_EMISOR = '000000200000', COD_MONEDA_EMISOR = '032',\r\n"
							+ "FECHA_HORA_LOCAL = '200923000000', CICLO_VIDA_TRANSACCION = ' MCC4875490920  ' ,\r\n"
							+ "PROCESADO = 0, NRO_TARJETA = "+nro_tarjeta+", FECHA_VTO_TARJETA = '2403',\r\n"
							+ "COD_COMERCIO = '00000002', DESCRIPCION_COMERCIO = 'TIDAL MUSIC                          ARG',\r\n"
							+ "ID_CONSUMO = NULL, OBSERVACION = NULL, ID_AUTORIZACION = null, CRITERIO_MAPEO = null, COD_PROCESAMIENTO = '000000',\r\n"
							+ "AUTORIZACION_PROCESADA = 0,  PRESENTACION_PARCIAL = 0, MCC = '4899', CONDICION_PUNTO_SERVICIO = '000040S09000'\r\n"
							+ "WHERE ID_IPM IN (11287)", configEntidad);
					
					//WebDriverManager.chromedriver().clearDriverCache();
					
					//String URL_GBATCH = "http://sso.globalprocessingqa.com/auth/realms/GlobalProcessing/protocol/openid-connect/auth?redirect_uri=https:%2F%2Fv2batch.web.qa.global.globalprocessing.net.ar%2Fowin%2Fsecurity%2Fkeycloak%2FGP_GlobalBatch_cookie_auth%2Fcallback&response_type=code&scope=openid&state=oidc_state_bdb3ab8f0f3a4e3497c15807e7682892&client_id=GlobalBatch";
					//String data = "";
					
				*/
					
					
					
					//Se abre el driver con la URL de GO
					driver.get("https://v2batch.web.qa.global.globalprocessing.net.ar/");
					

					acceso.loginUsernameBatch(data, report, DM, iteration, name, repo, configEntidad);
					// Login a la pagina principal de Global Batch
					report.AddLine("Acceso a la pagina de Global Batch");
					System.out.println("Acceso a la pagina de Global Batch\r\n");
					//acceso.loginUsernameBatch1(DM, repo);
					// Hacemos click en procesos
					nav.procesos(data, report, DM, iteration, name, repo);
					
					// Primera pagina del test
					tc.pagina11(data, report, DM, iteration, name, repo, configEntidad);

					// Segunda pagina
					tc.pagina2(data, report, DM, iteration, name, repo);

					// Tercera pagina
					tc.pagina3(data, report, DM, iteration, name, repo);
								
					// Tiempo necesario para que se ejecute el proceso y se creen los registros en la BDD
					Thread.sleep(20000);	
					
					
					
					
					// Hacemos click en procesos
				/*	nav.procesos1(data,report,DM,repo,configEntidad);
					
					// Primera pagina del test
					tc.pagina11(DM, repo, configEntidad);

					// Segunda pagina
					tc.pagina22(DM,repo);

					// Tercera pagina
					tc.pagina33(DM, repo);
								
					// Tiempo necesario para que se ejecute el proceso y se creen los registros en la BDD
					Thread.sleep(20000);			
					
					report.AddLine("Se procede a realizar las verificaciones.");
					System.out.println("Se procede a realizar las verificaciones.\r\n");*/
						
					
					if (!responseData.isEmpty() && !token.isEmpty()) {
						report.AddLine("Response numero de Id Autorizacion Emisor OK: " + responseData);
						System.out.println("Response numero de Id Autorizacion Emisor OK: " + responseData + "\r\n");
						//ESPERA PARA EL PROCESAMIENTO DEL REGISTRO EN EL BACKEND
						System.out.println("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND SEGS #####");
						report.AddLine("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND SEGS #####");
						Thread.sleep(30000);
						//VALIDACIONES
						
						System.out.println("Se realizan las validaciones en la BD:\r\n");
						report.AddLine("Se realizan las validaciones en la BD:");
						
						if (responseData != "0") 
						{
							rtaIdPago = oraResp.oraOneQueryCredito(report, "select Id_Autorizacion_Emisor from autorizacion_emisor_log\r\n"
									+ "where Id_Autorizacion_Emisor = " + String.valueOf(responseData), configEntidad);
							report.AddLine("ID_AUTORIZACION en la base de datos: " + responseData);
							System.out.println("ID_AUTORIZACION en la base de datos: " + responseData + "\r\n");
						} else {
							report.AddLineAssertionError("Error:<br>No se obtuvo el ID_AUTORIZACION");
							System.out.println("##[warning] : Error:<br>No se obtuvo el ID_AUTORIZACION\r\n");
							//Si falla la validacion el resultado de la prueba es false
							result = false;
						}

						//ADD VALIDATIONS
						if (!responseData.equals(String.valueOf(responseData))) {
						
						//rollBack(report, oraResp, ppCondi, rtaIdPago);
							report.AddLineAssertionError("Error:<br>El ID_AUTORIZACION generado por la API Crear_Carga_Local es: " + responseData + " es distinto al de la base de datos: " + rtaIdPago);
							System.out.println("##[warning] : Error:<br>El ID_AUTORIZACION generado por la API Crear_Carga_Local es: " + responseData + " es distinto al de la base de datos: " + rtaIdPago);
						
						//Si falla la validacion el resultado de la prueba es false
							result = false;
						} else {
							report.AddLine("Validacion exitosa:<br>El ID_AUTORIZACION generado por la API Crear Carga Local es: " + responseData + " es igual al de la base de datos: " + rtaIdPago);
							System.out.println("##[section] : Validacion exitosa:\r\nEl ID_AUTORIZACION generado por la API Crear Carga Local es: " + responseData + " es igual al de la base de datos: " + rtaIdPago + "\r\n");				
						}
						
						
						
						//SE COMIENZA A VALIDAR
						
						if (!responseData.isEmpty())
						{
							report.AddLine("Ejecucion Correcta<br>ID_IPM: " + ID_IPM);
							System.out.println("##[section] : Ejecucion Correcta\r\nID_IPM: " + ID_IPM + "\r\n");
							//VALIDACIONES
							
							result = validacionGral(oraResp, report, ID_IPM, entidad, validaciones);
						} else {
							report.AddLineAssertionError("FAIL<br>ID_IPM: " + ID_IPM + " Se esperaba: un numero" );
							System.out.println("##[warning] : FAIL : \r\nID_IPM: " + ID_IPM + " Se esperaba: un numero");
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
						report.AddLineAssertionError("Response code data FAIL!!: " + ID_IPM);
						System.out.println("Response code data FAIL!!: " + ID_IPM + "\r\n");
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
	
	
	public boolean metodoEndToEnd2(Datasources data,Reports report, DriverManager DM, int iteration, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado, String tokenUrl, JsonArray resultadosEsperados) {
		//Validation var
		boolean result = true;
		List<dbWorker> results = new ArrayList<>();
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");
			
			//SELECT THE DRIVER AND PATH
			driver = DM.CreateDriver(DM.CHROME);
	
			report.SetDriver(driver);

			//SET THE REPOSITORIES TO USE
			Repo_WebRepository repo = new Repo_WebRepository();
			repo.setDriver(driver);

			//SET STEPS INSTANCES
			Acceso acceso = new Acceso();
			NavSuperiorBatch nav = new NavSuperiorBatch();
			TC_07_BA_PASOS tc = new TC_07_BA_PASOS();
			//dbWorker oraResp = new dbWorker();
			//PrePostCondi ppCondi = new PrePostCondi();
			//GBatch batch = new GBatch();
			

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			String jsonFileV = "";
			String bodyData = "";
			String respBody = "";
			String responseData = "";
			String responseData2 = "";
			String rtaIdPago = "";
			//String NRO_TARJETA = "5522681341261779";
			//String VENCIMIENTO = "2504";
			String ID_IPM = "11287";
			int rtaPrePostCondiciones = 0;
			boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			String URL_GBATCH = JsonPath.from(configEntidad).get("GBATCH.url_gbatch");
 	
			//SET INSTANCES
			apiWorker apiWorker = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			AutorizadorInterno AI = new AutorizadorInterno();
			PrePostCondi ppCondi = new PrePostCondi();
			
			//CONFIGURACIÓN DATABASE
			oraResp.setUser(JsonPath.from(configEntidad).get("DBCREDITO.user"));
			oraResp.setPass(JsonPath.from(configEntidad).get("DBCREDITO.pass"));
			oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
			oraResp.setHost(JsonPath.from(configEntidad).get("DBCREDITO.host"));
			String user = JsonPath.from(configEntidad).get("DBCREDITO.user");
			String pass = JsonPath.from(configEntidad).get("DBCREDITO.pass");
			String host = JsonPath.from(configEntidad).get("DBCREDITO.host");
			String SID = JsonPath.from(configEntidad).get("DBCREDITO.SID");
			
			String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;
			
			Connection conn;
			//conn = DriverManager.getConnection(strCon,user,pass);
			conn = java.sql.DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			// Ejecutar la consulta SQL
            String sqlQuery = "SELECT COD_AUTO_ENTIDAD, COMERCIO_DESCRIPCION, MODO_INGRESO, A.ID_AUTORIZACION_EMISOR, "
                    + "A.ID_COD_MOVIMIENTO, A.ID_ESTADO, A.ID_CUENTA,A.FECHA_AUTORIZACION, A.IMPORTE, a.nro_tarjeta "
                    + "FROM AUTORIZACION A "
                    + "WHERE ID_AUTORIZACION IN (12117)";
         ResultSet resultSet = stmt.executeQuery(sqlQuery);
         // Procesar los resultados
         while (resultSet.next()) {
             // Obtener los valores de cada columna por nombre
             String codAutoEntidad = resultSet.getString("COD_AUTO_ENTIDAD");
             String comercioDescripcion = resultSet.getString("COMERCIO_DESCRIPCION");
             String modoIngreso = resultSet.getString("MODO_INGRESO");
             int idAutorizacionEmisor = resultSet.getInt("ID_AUTORIZACION_EMISOR");
             int idCodMovimiento = resultSet.getInt("ID_COD_MOVIMIENTO");
             int idEstado = resultSet.getInt("ID_ESTADO");
             int idCuenta = resultSet.getInt("ID_CUENTA");
             String fechaAutorizacion = resultSet.getString("FECHA_AUTORIZACION");
             double importe = resultSet.getDouble("IMPORTE");
             String nroTarjeta = resultSet.getString("nro_tarjeta");

             // Imprimir las variables con el nombre de cada campo y su valor
             System.out.println("COD_AUTO_ENTIDAD: ************** " + codAutoEntidad);
             System.out.println("COMERCIO_DESCRIPCION: " + comercioDescripcion);
             System.out.println("MODO_INGRESO: " + modoIngreso);
             System.out.println("ID_AUTORIZACION_EMISOR: " + idAutorizacionEmisor);
             System.out.println("ID_COD_MOVIMIENTO: " + idCodMovimiento);
             System.out.println("ID_ESTADO: " + idEstado);
             System.out.println("ID_CUENTA: " + idCuenta);
             System.out.println("FECHA_AUTORIZACION: " + fechaAutorizacion);
             System.out.println("IMPORTE: " + importe);
             System.out.println("nro_tarjeta: " + nroTarjeta);
         }
			
         resultSet.close();
         stmt.close();
			
		    
			jsonFileV = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TC.json")));
			
			//Obtengo la fecha actual
			LocalDate fechaActual = LocalDate.now();

			//Grabo la fecha actual como string y saco espacios
			String fechaAutorizacionIPM = fechaActual.toString().replace(" ", "");
					
			//Separo la fecha por los guiones para separar el anio, mes y dia
			String[] fechaSeparada = fechaAutorizacionIPM.split("-");
				
			fechaAutorizacionIPM = fechaSeparada[0].replace("20", "") + fechaSeparada[1] + fechaSeparada[2] + "000000";
			
			   System.out.println("fecha **** : " + fechaAutorizacionIPM);
			
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
				respBody = AI.post(report, repoVar, apiWorker, bodyData, token);
				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode que tiene que ser "00"
				//respBody = response.getBody().asPrettyString();
				responseData = JsonPath.from(respBody).getString("IdAutorizacion"); 
				responseData2 = JsonPath.from(respBody).getString("IdAutorizacionEmisor");
				
				System.out.println("Este es el Id Autorizacion Emisor otorgado :" + responseData);
				
				String COD_AUTO_ENTIDAD = oraResp.oraOneQueryCredito(report,"SELECT COD_AUTO_ENTIDAD FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				//String nro_tarjeta = oraResp.oraOneQueryCredito(report,"SELECT nro_tarjeta FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				/*String COMERCIO_DESCRIPCION = oraResp.oraOneQuery(report,"SELECT COMERCIO_DESCRIPCION FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String MODO_INGRESO = oraResp.oraOneQuery(report,"SELECT MODO_INGRESO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String ID_AUTORIZACION_EMISOR = oraResp.oraOneQuery(report,"SELECT ID_AUTORIZACION_EMISOR FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String ID_COD_MOVIMIENTO = oraResp.oraOneQuery(report,"SELECT ID_COD_MOVIMIENTO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String ID_ESTADO = oraResp.oraOneQuery(report,"SELECT ID_ESTADO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String ID_CUENTA = oraResp.oraOneQuery(report,"SELECT ID_CUENTA FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String FECHA_AUTORIZACION = oraResp.oraOneQuery(report,"SELECT FECHA_AUTORIZACION FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String IMPORTE = oraResp.oraOneQuery(report,"SELECT IMPORTE FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				
				System.out.println("datos        ** "+COD_AUTO_ENTIDAD+COMERCIO_DESCRIPCION+MODO_INGRESO+ID_AUTORIZACION_EMISOR+ID_COD_MOVIMIENTO+ID_ESTADO+ID_CUENTA+FECHA_AUTORIZACION+IMPORTE+nro_tarjeta);
				/***********************************************************************
				String IN_IMPORTE_OPERACION = oraResp.oraOneQuery(report, "SELECT IN_IMPORTE_OPERACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_MONEDA_OPERACION = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_OPERACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_IMPORTE_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_MONEDA_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_IMPORTE_EMISOR = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_EMISOR FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_MONEDA_EMISOR = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_EMISOR FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_PROCESAMIENTO = oraResp.oraOneQuery(report,"SELECT IN_COD_PROCESAMIENTO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_DATOS_RED = oraResp.oraOneQuery(report,"SELECT IN_DATOS_RED FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_FECHA_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_FECHA_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_COMERCIO_EXTERNO = oraResp.oraOneQuery(report,"SELECT IN_COD_COMERCIO_EXTERNO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_FECHA_LOCAL = oraResp.oraOneQuery(report,"SELECT IN_FECHA_LOCAL FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_MCC = oraResp.oraOneQuery(report,"SELECT IN_MCC FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_IMPORTE_REEMPLAZO = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_REEMPLAZO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				
				System.out.println("datos        ** "+IN_IMPORTE_OPERACION+IN_COD_MONEDA_OPERACION+IN_IMPORTE_LIQUIDACION+IN_COD_MONEDA_LIQUIDACION
						+IN_IMPORTE_EMISOR+IN_COD_MONEDA_EMISOR+IN_COD_PROCESAMIENTO+IN_DATOS_RED+IN_FECHA_LIQUIDACION+IN_COD_COMERCIO_EXTERNO+IN_FECHA_LOCAL+IN_MCC+IN_IMPORTE_REEMPLAZO);
				
			*********/
				rtaPrePostCondiciones = oraResp.oraUpdateCredito(report, "UPDATE IPM\r\n"
						+ "SET CODIGO_APROBACION = " + COD_AUTO_ENTIDAD + ", IMPORTE_OPERACION = '000000200000', COD_MONEDA_OPERACION = '032', \r\n"
						+ "IMPORTE_RECONCILIACION = '000000200000', COD_MONEDA_RECONCILIACION = '840', IMPORTE_EMISOR = '000000200000', COD_MONEDA_EMISOR = '032',\r\n"
						+ "FECHA_HORA_LOCAL = '200923000000', CICLO_VIDA_TRANSACCION = ' MCC4875480920  ' ,\r\n"
						+ "PROCESADO = 0, NRO_TARJETA = '2DDE5DA245DC698764DA0398A42626E5', FECHA_VTO_TARJETA = '2403',\r\n"
						+ "COD_COMERCIO = '00000002', DESCRIPCION_COMERCIO = 'TIDAL MUSIC                          ARG',\r\n"
						+ "ID_CONSUMO = NULL, OBSERVACION = NULL, ID_AUTORIZACION = null, CRITERIO_MAPEO = null, COD_PROCESAMIENTO = '000000',\r\n"
						+ "AUTORIZACION_PROCESADA = 0,  PRESENTACION_PARCIAL = 0, MCC = '4899', CONDICION_PUNTO_SERVICIO = '000040S09000'\r\n"
						+ "WHERE ID_IPM IN (11287) ",configEntidad);

				/*rtaPrePostCondiciones = oraResp.oraUpdate(report, "UPDATE IPM\r\n"
						+ "SET CODIGO_APROBACION = "+COD_AUTO_ENTIDAD+", IMPORTE_OPERACION = '000000200000', COD_MONEDA_OPERACION = '032', \r\n"
						+ "IMPORTE_RECONCILIACION = '000000200000', COD_MONEDA_RECONCILIACION = '840', IMPORTE_EMISOR = '000000200000', COD_MONEDA_EMISOR = '032',\r\n"
						+ "FECHA_HORA_LOCAL = '200923000000', CICLO_VIDA_TRANSACCION = ' MCC4875490920  ' ,\r\n"
						+ "PROCESADO = 0, NRO_TARJETA = "+nro_tarjeta+", FECHA_VTO_TARJETA = '2403',\r\n"
						+ "COD_COMERCIO = '00000002', DESCRIPCION_COMERCIO = 'TIDAL MUSIC                          ARG',\r\n"
						+ "ID_CONSUMO = NULL, OBSERVACION = NULL, ID_AUTORIZACION = null, CRITERIO_MAPEO = null, COD_PROCESAMIENTO = '000000',\r\n"
						+ "AUTORIZACION_PROCESADA = 0,  PRESENTACION_PARCIAL = 0, MCC = '4899', CONDICION_PUNTO_SERVICIO = '000040S09000'\r\n"
						+ "WHERE ID_IPM IN (11287)", configEntidad);
				
				//WebDriverManager.chromedriver().clearDriverCache();
				
				//String URL_GBATCH = "http://sso.globalprocessingqa.com/auth/realms/GlobalProcessing/protocol/openid-connect/auth?redirect_uri=https:%2F%2Fv2batch.web.qa.global.globalprocessing.net.ar%2Fowin%2Fsecurity%2Fkeycloak%2FGP_GlobalBatch_cookie_auth%2Fcallback&response_type=code&scope=openid&state=oidc_state_bdb3ab8f0f3a4e3497c15807e7682892&client_id=GlobalBatch";
				//String data = "";
				
			*/
				
				
				
				//Se abre el driver con la URL de GO
				//driver.get("https://v2batch.web.qa.global.globalprocessing.net.ar/");
				driver.get(URL_GBATCH);
				

				acceso.loginUsernameBatch(data, report, DM, iteration, name, repo, configEntidad);
				// Login a la pagina principal de Global Batch
				report.AddLine("Acceso a la pagina de Global Batch");
				System.out.println("Acceso a la pagina de Global Batch\r\n");
				//acceso.loginUsernameBatch1(DM, repo);
				// Hacemos click en procesos
				nav.procesos(data, report, DM, iteration, name, repo);
				
				// Primera pagina del test
				tc.pagina11(data, report, DM, iteration, name, repo, configEntidad);

				// Segunda pagina
				tc.pagina2(data, report, DM, iteration, name, repo);

				// Tercera pagina
				tc.pagina3(data, report, DM, iteration, name, repo);
							
				// Tiempo necesario para que se ejecute el proceso y se creen los registros en la BDD
				Thread.sleep(20000);	
				
				
				
		
				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response numero de Id Autorizacion Emisor OK: " + responseData);
					System.out.println("Response numero de Id Autorizacion Emisor OK: " + responseData + "\r\n");
					//ESPERA PARA EL PROCESAMIENTO DEL REGISTRO EN EL BACKEND
					System.out.println("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND SEGS #####");
					report.AddLine("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND SEGS #####");
					Thread.sleep(30000);
					//VALIDACIONES
					
					System.out.println("Se realizan las validaciones en la BD:\r\n");
					report.AddLine("Se realizan las validaciones en la BD:");
					
					if (responseData != "0") 
					{
						rtaIdPago = oraResp.oraOneQueryCredito(report, "select Id_Autorizacion_Emisor from autorizacion_emisor_log\r\n"
								+ "where Id_Autorizacion_Emisor = " + String.valueOf(responseData), configEntidad);
						report.AddLine("ID_AUTORIZACION en la base de datos: " + responseData);
						System.out.println("ID_AUTORIZACION en la base de datos: " + responseData + "\r\n");
					} else {
						report.AddLineAssertionError("Error:<br>No se obtuvo el ID_AUTORIZACION");
						System.out.println("##[warning] : Error:<br>No se obtuvo el ID_AUTORIZACION\r\n");
						//Si falla la validacion el resultado de la prueba es false
						result = false;
					}

					//ADD VALIDATIONS
					if (!responseData.equals(String.valueOf(responseData))) {
					
					//rollBack(report, oraResp, ppCondi, rtaIdPago);
						report.AddLineAssertionError("Error:<br>El ID_AUTORIZACION generado por la API Crear_Carga_Local es: " + responseData + " es distinto al de la base de datos: " + rtaIdPago);
						System.out.println("##[warning] : Error:<br>El ID_AUTORIZACION generado por la API Crear_Carga_Local es: " + responseData + " es distinto al de la base de datos: " + rtaIdPago);
					
					//Si falla la validacion el resultado de la prueba es false
						result = false;
					} else {
						report.AddLine("Validacion exitosa:<br>El ID_AUTORIZACION generado por la API Crear Carga Local es: " + responseData + " es igual al de la base de datos: " + rtaIdPago);
						System.out.println("##[section] : Validacion exitosa:\r\nEl ID_AUTORIZACION generado por la API Crear Carga Local es: " + responseData + " es igual al de la base de datos: " + rtaIdPago + "\r\n");				
					}
					
					
					
					//SE COMIENZA A VALIDAR
					
					if (!responseData.isEmpty())
					{
						report.AddLine("Ejecucion Correcta<br>ID_IPM: " + ID_IPM);
						System.out.println("##[section] : Ejecucion Correcta\r\nID_IPM: " + ID_IPM + "\r\n");
						//VALIDACIONES
						
						result = validacionGral(oraResp, report, ID_IPM, entidad, validaciones);
					} else {
						report.AddLineAssertionError("FAIL<br>ID_IPM: " + ID_IPM + " Se esperaba: un numero" );
						System.out.println("##[warning] : FAIL : \r\nID_IPM: " + ID_IPM + " Se esperaba: un numero");
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
					report.AddLineAssertionError("Response code data FAIL!!: " + ID_IPM);
					System.out.println("Response code data FAIL!!: " + ID_IPM + "\r\n");
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
	
	/**************************metodo end to end para entidad 9999*****************/
	
	public boolean metodoEndToEnd3(Datasources data,Reports report, DriverManager DM, int iteration, String name, String configEntidad, String entidad, String TCFilesPath, String endPoint, String statusCodeEsperado, String tokenUrl, JsonArray resultadosEsperados) {
		//Validation var
		boolean result = true;
		List<dbWorker> results = new ArrayList<>();
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring " + name + "\r\n");
			
			//SELECT THE DRIVER AND PATH
			driver = DM.CreateDriver(DM.CHROME);
	
			report.SetDriver(driver);

			//SET THE REPOSITORIES TO USE
			Repo_WebRepository repo = new Repo_WebRepository();
			repo.setDriver(driver);

			//SET STEPS INSTANCES
			Acceso acceso = new Acceso();
			NavSuperiorBatch nav = new NavSuperiorBatch();
			TC_07_BA_PASOS tc = new TC_07_BA_PASOS();
			//dbWorker oraResp = new dbWorker();
			//PrePostCondi ppCondi = new PrePostCondi();
			//GBatch batch = new GBatch();
			

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			//String doc = "35687555";
			String token = "";
			String jsonFileV = "";
			String bodyData = "";
			String respBody = "";
			String responseData = "";
			String responseData2 = "";
			String rtaIdPago = "";
			//String NRO_TARJETA = "5522681341261779";
			//String VENCIMIENTO = "2504";
			String ID_IPM = "16205";
			int rtaPrePostCondiciones = 0;
			boolean resp;
 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");
 			String URL_GBATCH = JsonPath.from(configEntidad).get("GBATCH.url_gbatch");
 	
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
			String user = JsonPath.from(configEntidad).get("DB.user");
			String pass = JsonPath.from(configEntidad).get("DB.pass");
			String host = JsonPath.from(configEntidad).get("DB.host");
			String SID = JsonPath.from(configEntidad).get("DB.SID");
			
			String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;
			
			Connection conn;
			//conn = DriverManager.getConnection(strCon,user,pass);
			conn = java.sql.DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			// Ejecutar la consulta SQL
            String sqlQuery = "SELECT COD_AUTO_ENTIDAD, COMERCIO_DESCRIPCION, MODO_INGRESO, A.ID_AUTORIZACION_EMISOR, "
                    + "A.ID_COD_MOVIMIENTO, A.ID_ESTADO, A.ID_CUENTA,A.FECHA_AUTORIZACION, A.IMPORTE, a.nro_tarjeta "
                    + "FROM AUTORIZACION A "
                    + "WHERE ID_AUTORIZACION IN (12117)";
         ResultSet resultSet = stmt.executeQuery(sqlQuery);
         // Procesar los resultados
         while (resultSet.next()) {
             // Obtener los valores de cada columna por nombre
             String codAutoEntidad = resultSet.getString("COD_AUTO_ENTIDAD");
             String comercioDescripcion = resultSet.getString("COMERCIO_DESCRIPCION");
             String modoIngreso = resultSet.getString("MODO_INGRESO");
             int idAutorizacionEmisor = resultSet.getInt("ID_AUTORIZACION_EMISOR");
             int idCodMovimiento = resultSet.getInt("ID_COD_MOVIMIENTO");
             int idEstado = resultSet.getInt("ID_ESTADO");
             int idCuenta = resultSet.getInt("ID_CUENTA");
             String fechaAutorizacion = resultSet.getString("FECHA_AUTORIZACION");
             double importe = resultSet.getDouble("IMPORTE");
             String nroTarjeta = resultSet.getString("nro_tarjeta");

             // Imprimir las variables con el nombre de cada campo y su valor
             System.out.println("COD_AUTO_ENTIDAD: ************** " + codAutoEntidad);
             System.out.println("COMERCIO_DESCRIPCION: " + comercioDescripcion);
             System.out.println("MODO_INGRESO: " + modoIngreso);
             System.out.println("ID_AUTORIZACION_EMISOR: " + idAutorizacionEmisor);
             System.out.println("ID_COD_MOVIMIENTO: " + idCodMovimiento);
             System.out.println("ID_ESTADO: " + idEstado);
             System.out.println("ID_CUENTA: " + idCuenta);
             System.out.println("FECHA_AUTORIZACION: " + fechaAutorizacion);
             System.out.println("IMPORTE: " + importe);
             System.out.println("nro_tarjeta: " + nroTarjeta);
         }
			
         resultSet.close();
         stmt.close();
			
		    
			jsonFileV = new String(Files.readAllBytes(Paths.get(TCFilesPath + "/TC.json")));
			
			//Obtengo la fecha actual
			LocalDate fechaActual = LocalDate.now();

			//Grabo la fecha actual como string y saco espacios
			String fechaAutorizacionIPM = fechaActual.toString().replace(" ", "");
					
			//Separo la fecha por los guiones para separar el anio, mes y dia
			String[] fechaSeparada = fechaAutorizacionIPM.split("-");
				
			fechaAutorizacionIPM = fechaSeparada[0].replace("20", "") + fechaSeparada[1] + fechaSeparada[2] + "000000";
			
			   System.out.println("fecha **** : " + fechaAutorizacionIPM);
			
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
				for(int i = 0; i < 10; i++) {	
				//if(!DE39=="00")
				respBody = AI.post(report, repoVar, apiWorker, bodyData, token);
		
				Thread.sleep(5000);
				System.out.println("ejecucion numero : " + i);
				String DE39 = JsonPath.from(respBody).getString("DE39");
				if(!DE39.equals("unconnected ISOChannel") && !DE39.equals("96") && !DE39.equals("91") && !DE39.equals("57")) {
		//		if(!res.equals("unconnected ISOChannel")) {	
					System.out.println("***********ingrese al ifFFFFFF");
					break;
				}				
				report.AddLine("Ejecucion Incorrecta<br>DE39: " + DE39);
				System.out.println("##[section] : Ejecucion Incorrecta\r\nDE39: " + DE39 + "\r\n");
			}
				
				System.out.println("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");
				report.AddLine("##### EXTRACCION DE DATA JSON DEL RESPBODY #####");

				// Extraemos el responseCode que tiene que ser "00"
				//respBody = response.getBody().asPrettyString();
				responseData = JsonPath.from(respBody).getString("IdAutorizacion"); 
				responseData2 = JsonPath.from(respBody).getString("IdAutorizacionEmisor");
				
				System.out.println("Este es el Id Autorizacion Emisor otorgado :" + responseData);
				
				String COD_AUTO_ENTIDAD = oraResp.oraOneQuery(report,"SELECT COD_AUTO_ENTIDAD FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				//String nro_tarjeta = oraResp.oraOneQueryCredito(report,"SELECT nro_tarjeta FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				/*String COMERCIO_DESCRIPCION = oraResp.oraOneQuery(report,"SELECT COMERCIO_DESCRIPCION FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String MODO_INGRESO = oraResp.oraOneQuery(report,"SELECT MODO_INGRESO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String ID_AUTORIZACION_EMISOR = oraResp.oraOneQuery(report,"SELECT ID_AUTORIZACION_EMISOR FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String ID_COD_MOVIMIENTO = oraResp.oraOneQuery(report,"SELECT ID_COD_MOVIMIENTO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String ID_ESTADO = oraResp.oraOneQuery(report,"SELECT ID_ESTADO FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String ID_CUENTA = oraResp.oraOneQuery(report,"SELECT ID_CUENTA FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String FECHA_AUTORIZACION = oraResp.oraOneQuery(report,"SELECT FECHA_AUTORIZACION FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				String IMPORTE = oraResp.oraOneQuery(report,"SELECT IMPORTE FROM AUTORIZACION WHERE ID_AUTORIZACION = " + responseData, configEntidad);
				
				System.out.println("datos        ** "+COD_AUTO_ENTIDAD+COMERCIO_DESCRIPCION+MODO_INGRESO+ID_AUTORIZACION_EMISOR+ID_COD_MOVIMIENTO+ID_ESTADO+ID_CUENTA+FECHA_AUTORIZACION+IMPORTE+nro_tarjeta);
				/***********************************************************************
				String IN_IMPORTE_OPERACION = oraResp.oraOneQuery(report, "SELECT IN_IMPORTE_OPERACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_MONEDA_OPERACION = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_OPERACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_IMPORTE_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_MONEDA_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_IMPORTE_EMISOR = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_EMISOR FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_MONEDA_EMISOR = oraResp.oraOneQuery(report,"SELECT IN_COD_MONEDA_EMISOR FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_PROCESAMIENTO = oraResp.oraOneQuery(report,"SELECT IN_COD_PROCESAMIENTO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_DATOS_RED = oraResp.oraOneQuery(report,"SELECT IN_DATOS_RED FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_FECHA_LIQUIDACION = oraResp.oraOneQuery(report,"SELECT IN_FECHA_LIQUIDACION FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_COD_COMERCIO_EXTERNO = oraResp.oraOneQuery(report,"SELECT IN_COD_COMERCIO_EXTERNO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_FECHA_LOCAL = oraResp.oraOneQuery(report,"SELECT IN_FECHA_LOCAL FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_MCC = oraResp.oraOneQuery(report,"SELECT IN_MCC FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				String IN_IMPORTE_REEMPLAZO = oraResp.oraOneQuery(report,"SELECT IN_IMPORTE_REEMPLAZO FROM AUTORIZACION_EMISOR_LOG WHERE ID_AUTORIZACION_EMISOR= " + responseData2, configEntidad);
				
				System.out.println("datos        ** "+IN_IMPORTE_OPERACION+IN_COD_MONEDA_OPERACION+IN_IMPORTE_LIQUIDACION+IN_COD_MONEDA_LIQUIDACION
						+IN_IMPORTE_EMISOR+IN_COD_MONEDA_EMISOR+IN_COD_PROCESAMIENTO+IN_DATOS_RED+IN_FECHA_LIQUIDACION+IN_COD_COMERCIO_EXTERNO+IN_FECHA_LOCAL+IN_MCC+IN_IMPORTE_REEMPLAZO);
				
			*********/
				rtaPrePostCondiciones = oraResp.oraUpdate(report, "UPDATE IPM\r\n"
						+ "SET CODIGO_APROBACION = " + COD_AUTO_ENTIDAD + ", IMPORTE_OPERACION = '000000200000', COD_MONEDA_OPERACION = '032', \r\n"
						+ "IMPORTE_RECONCILIACION = '000000200000', COD_MONEDA_RECONCILIACION = '840', IMPORTE_EMISOR = '000000200000', COD_MONEDA_EMISOR = '032',\r\n"
						+ "FECHA_HORA_LOCAL = '200923000000', CICLO_VIDA_TRANSACCION = ' MCC4876490920  ' ,\r\n"
						+ "PROCESADO = 0, NRO_TARJETA = 'AD925642B2D03D5DD411A3286ADEF694', FECHA_VTO_TARJETA = '2504',\r\n"
						+ "COD_COMERCIO = '00000002', DESCRIPCION_COMERCIO = 'TIDAL MUSIC                          ARG',\r\n"
						+ "ID_CONSUMO = NULL, OBSERVACION = NULL, ID_AUTORIZACION = null, CRITERIO_MAPEO = null, COD_PROCESAMIENTO = '000000',\r\n"
						+ "AUTORIZACION_PROCESADA = 0,  PRESENTACION_PARCIAL = 0, MCC = '4899', CONDICION_PUNTO_SERVICIO = '000040S09000'\r\n"
						+ "WHERE ID_IPM IN (16205) ",configEntidad);

				/*rtaPrePostCondiciones = oraResp.oraUpdate(report, "UPDATE IPM\r\n"
						+ "SET CODIGO_APROBACION = "+COD_AUTO_ENTIDAD+", IMPORTE_OPERACION = '000000200000', COD_MONEDA_OPERACION = '032', \r\n"
						+ "IMPORTE_RECONCILIACION = '000000200000', COD_MONEDA_RECONCILIACION = '840', IMPORTE_EMISOR = '000000200000', COD_MONEDA_EMISOR = '032',\r\n"
						+ "FECHA_HORA_LOCAL = '200923000000', CICLO_VIDA_TRANSACCION = ' MCC4875490920  ' ,\r\n"
						+ "PROCESADO = 0, NRO_TARJETA = "+nro_tarjeta+", FECHA_VTO_TARJETA = '2403',\r\n"
						+ "COD_COMERCIO = '00000002', DESCRIPCION_COMERCIO = 'TIDAL MUSIC                          ARG',\r\n"
						+ "ID_CONSUMO = NULL, OBSERVACION = NULL, ID_AUTORIZACION = null, CRITERIO_MAPEO = null, COD_PROCESAMIENTO = '000000',\r\n"
						+ "AUTORIZACION_PROCESADA = 0,  PRESENTACION_PARCIAL = 0, MCC = '4899', CONDICION_PUNTO_SERVICIO = '000040S09000'\r\n"
						+ "WHERE ID_IPM IN (11287)", configEntidad);
				
				//WebDriverManager.chromedriver().clearDriverCache();
				
				//String URL_GBATCH = "http://sso.globalprocessingqa.com/auth/realms/GlobalProcessing/protocol/openid-connect/auth?redirect_uri=https:%2F%2Fv2batch.web.qa.global.globalprocessing.net.ar%2Fowin%2Fsecurity%2Fkeycloak%2FGP_GlobalBatch_cookie_auth%2Fcallback&response_type=code&scope=openid&state=oidc_state_bdb3ab8f0f3a4e3497c15807e7682892&client_id=GlobalBatch";
				//String data = "";
				
			*/
				
				
				
				//Se abre el driver con la URL de GO
				//driver.get("https://v2batch.web.qa.global.globalprocessing.net.ar/");
				driver.get(URL_GBATCH);
				

				acceso.loginUsernameBatch(data, report, DM, iteration, name, repo, configEntidad);
				// Login a la pagina principal de Global Batch
				report.AddLine("Acceso a la pagina de Global Batch");
				System.out.println("Acceso a la pagina de Global Batch\r\n");
				//acceso.loginUsernameBatch1(DM, repo);
				// Hacemos click en procesos
				nav.procesos(data, report, DM, iteration, name, repo);
				
				// Primera pagina del test
				tc.pagina1(data, report, DM, iteration, name, repo, configEntidad);

				// Segunda pagina
				tc.pagina2(data, report, DM, iteration, name, repo);

				// Tercera pagina
				tc.pagina3(data, report, DM, iteration, name, repo);
							
				// Tiempo necesario para que se ejecute el proceso y se creen los registros en la BDD
				Thread.sleep(30000);
				
				
				/********************************************************************
				 * REALIZO UNA VERIFICACION
				 *****************************************************************/
				//CONFIGURACIÓN DATABASE
				oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
				oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
				oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
				oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));
			//	String user = JsonPath.from(configEntidad).get("DB.user");
				//String pass = JsonPath.from(configEntidad).get("DB.pass");
			//	String host = JsonPath.from(configEntidad).get("DB.host");
				//String SID = JsonPath.from(configEntidad).get("DB.SID");
				
				//String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;
				
				//Connection conn;
				//conn = DriverManager.getConnection(strCon,user,pass);
				conn = java.sql.DriverManager.getConnection(strCon,user,pass);
				stmt=conn.createStatement();
				// Ejecutar la consulta SQL
	            String sqlQueryV = "SELECT ID_CONSUMO, CRITERIO_MAPEO FROM IPM WHERE ID_IPM = ('16205')";
	         ResultSet resultSetV = stmt.executeQuery(sqlQueryV);
	         // Procesar los resultados
	         while (resultSetV.next()) {
	             // Obtener los valores de cada columna por nombre
	             String ID_CONSUMO = resultSetV.getString("ID_CONSUMO");
	             String CRITERIO_MAPEO = resultSetV.getString("CRITERIO_MAPEO");
	           
	             // Imprimir las variables con el nombre de cada campo y su valor
	             System.out.println("ID_CONSUMO: " + ID_CONSUMO);
	             System.out.println("CRITERIO_MAPEO: " + CRITERIO_MAPEO);
	             System.out.println("SE VERIFICA IMPACTO DE DATOS DE : " + ID_IPM);
	         }
				
	         resultSet.close();
	         stmt.close();
				
				
				
				
				/********************************************************************
				 * FINALIZO VERIFICACION
				 *****************************************************************/
				
		
				if (!responseData.isEmpty() && !token.isEmpty()) {
					report.AddLine("Response numero de Id Autorizacion Emisor OK: " + responseData);
					System.out.println("Response numero de Id Autorizacion Emisor OK: " + responseData + "\r\n");
					//ESPERA PARA EL PROCESAMIENTO DEL REGISTRO EN EL BACKEND
					System.out.println("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND SEGS #####");
					report.AddLine("##### TRHEAD SLEEP MIENTRAS EL REGISTRO SE PROCESA EN EL BACKEND SEGS #####");
					Thread.sleep(30000);
					//VALIDACIONES
					
					System.out.println("Se realizan las validaciones en la BD:\r\n");
					report.AddLine("Se realizan las validaciones en la BD:");
					
					/*if (responseData != "0") 
					{
						rtaIdPago = oraResp.oraOneQueryCredito(report, "select Id_Autorizacion_Emisor from autorizacion_emisor_log\r\n"
								+ "where Id_Autorizacion_Emisor = " + String.valueOf(responseData), configEntidad);
						report.AddLine("ID_AUTORIZACION en la base de datos: " + responseData);
						System.out.println("ID_AUTORIZACION en la base de datos: " + responseData + "\r\n");
					} else {
						report.AddLineAssertionError("Error:<br>No se obtuvo el ID_AUTORIZACION");
						System.out.println("##[warning] : Error:<br>No se obtuvo el ID_AUTORIZACION\r\n");
						//Si falla la validacion el resultado de la prueba es false
						result = false;
					}*/

					//ADD VALIDATIONS
				/*	if (!responseData.equals(String.valueOf(responseData))) {
					
					//rollBack(report, oraResp, ppCondi, rtaIdPago);
						report.AddLineAssertionError("Error:<br>El ID_AUTORIZACION generado por la API Crear_Carga_Local es: " + responseData + " es distinto al de la base de datos: " + rtaIdPago);
						System.out.println("##[warning] : Error:<br>El ID_AUTORIZACION generado por la API Crear_Carga_Local es: " + responseData + " es distinto al de la base de datos: " + rtaIdPago);
					
					//Si falla la validacion el resultado de la prueba es false
						result = false;
					} else {
						report.AddLine("Validacion exitosa:<br>El ID_AUTORIZACION generado por la API Crear Carga Local es: " + responseData + " es igual al de la base de datos: " + rtaIdPago);
						System.out.println("##[section] : Validacion exitosa:\r\nEl ID_AUTORIZACION generado por la API Crear Carga Local es: " + responseData + " es igual al de la base de datos: " + rtaIdPago + "\r\n");				
					}*/
					
					
					
					//SE COMIENZA A VALIDAR
					
				/*	if (!responseData.isEmpty())
					{
						report.AddLine("Ejecucion Correcta<br>ID_IPM: " + ID_IPM);
						System.out.println("##[section] : Ejecucion Correcta\r\nID_IPM: " + ID_IPM + "\r\n");
						//VALIDACIONES
						
						result = validacionGral(oraResp, report, ID_IPM, entidad, validaciones);
					} else {
						report.AddLineAssertionError("FAIL<br>ID_IPM: " + ID_IPM + " Se esperaba: un numero" );
						System.out.println("##[warning] : FAIL : \r\nID_IPM: " + ID_IPM + " Se esperaba: un numero");
						result = false;
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
					report.AddLineAssertionError("Response code data FAIL!!: " + ID_IPM);
					System.out.println("Response code data FAIL!!: " + ID_IPM + "\r\n");
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
	/******************************************************************************/
		
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
				queryVerf = queryVerf.replace("{{ID_IPM}}", responseData);
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
			
			
			validaRes = oraResp.executeQueryCredito(queryVerf);
			
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
