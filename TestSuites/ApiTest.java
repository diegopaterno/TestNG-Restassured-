package TestSuites;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.MatcherAssert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_Variables;
import Tools.BodyHelper;
import Tools.apiWorker;
import Tools.dbWorker;
import apis.wolfgang.API_OPERACIONES;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTest {
	static

	// Init
	DriverManager DM;
	static Datasources data;
	static Reports report;
	static Repo_Variables repoVar;
	static String path;
	static String path_2;
	static String configEntidad;
	static String entidad;
	static String datosEntradaSegunTc;
	static String cuentas_generales;

	@BeforeSuite(alwaysRun = true)
	static void initAll() throws IOException {
		DM = new DriverManager();
		data = new Datasources();
		report = new Reports();
		repoVar = new Repo_Variables();
		path = "./Datasources/config_entidad.json";
		configEntidad = new String(Files.readAllBytes(Paths.get(path)));
		entidad = JsonPath.from(configEntidad).getString("ENTIDAD.id_entidad");
		//PRUEBA VARIABLES GLOBALES PARA UTILIZAR URL,CONEXIONES, ETC
		//PRUEBA DE CUENTAS GENERALES
		path_2 = "./Datasources/cuentas_generales.json";
		cuentas_generales = new String(Files.readAllBytes(Paths.get(path_2)));
		

	}

	@BeforeClass(alwaysRun = true)
	void init() throws IOException {

	}

	@BeforeMethod(alwaysRun = true)

	@BeforeTest(alwaysRun = true)
	void beforeTest() {
		
		
		
		apiWorker apiResp = new apiWorker();
		
		//SET ENVIRONMENT
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
	}
	@Test
	public void testApiEndpoints1() {
		String path = "./API_Requests/PrePagas/TC_01_Alta_Cuenta_Virtual.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		Response response;	
		apiWorker apiResp = new apiWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));		
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E);
		}
		//La configuracion de los datos está dentro del método
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E);
		}
		try {
			response = apiResp.postMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/", "Cuentas", bodyData, token);
			
			if (response.getStatusCode()!=201) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 201 - Obtenido: " + String.valueOf(response.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("201"));
			} else {
				System.out.println("##TEST OK, API ALTA DE CUENTA OK");
				report.AddLineAssertionError("TEST OK, API ALTA DE CUENTA OK");
				//result = false;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	} 
	
	@Test
	public void testApiEndpoint2() {
		String path = "./API_Requests/PrePagas/TC_02_Alta_Cuenta_Fisica.txt";
		String token = "";
		String bodyData = "";
		String respBody = "";
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		Response response;
		repoVar.setTipoTc("API");
		apiWorker apiResp = new apiWorker();
		BodyHelper requestHelper = new BodyHelper();
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));
			
			//MODIFICO BODY (DNI y CUIL)
			bodyData = requestHelper.generadorBodyDocCuil(bodyData);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\n Error: " + E);
		}
		try {
			response = apiResp.postMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/", "Cuentas", bodyData, token);
			
			if(response.getStatusCode()!=201) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 201 - Obtenido: " + String.valueOf(response.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("201"));
		} else {
			System.out.println("##TEST OK, API ALTA DE CUENTA OK");
			report.AddLineAssertionError("TEST OK, API ALTA DE CUENTA OK");
			//result = false;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testApiEndpoint3() {
		Response response;
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		String token="";
		repoVar.setTipoTc("API");
		apiWorker apiResp = new apiWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		//GET TOKEN
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.\r\nError: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E);
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Habilitar", token);
		if(response.getStatusCode()!=200) {
			   report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			   System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			   MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
			    } else {
					System.out.println("##TEST OK, API HABILITAR TARJETA OK");
					report.AddLineAssertionError("TEST OK, API HABILITAR TARJETA OK");
			}
	}
	
	@Test
	public void testApiEndpoint4() {
		String path = "./API_Requests/PrePagas/TC_04_Crear_Carga_Local.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = "10001685";
		String ID_PRODUCTO = "88";
		Response response;
		apiWorker apiResp = new apiWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));	
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		try {
			response = apiResp.postMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Transacciones/","Cargas", bodyData, token);
			if(response.getStatusCode()!=201) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 201 - Obtenido: " + String.valueOf(response.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("201"));				
				} else {
					System.out.println("##TEST OK, API HABILITAR TARJETA OK");
					report.AddLineAssertionError("TEST OK, API HABILITAR TARJETA OK");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testApiEndpoint5() {
		String token = "";
		String respBody = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		Response response;
		apiWorker apiResp = new apiWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		response = apiResp.getMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Disponible", token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode:<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
			} else {
				System.out.println("##TEST OK, API CONSULTAR DISPONIBLE OK");
				report.AddLineAssertionError("TEST OK, API CONSULTAR DISPONIBLE OK");
		}
	}
	
	@Test
	public void testApiEndpoint6() {
		String token = "";
		String respBody = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		Response response;
		apiWorker apiResp = new apiWorker();
		//Obtengo los datos de la base url del archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		response = apiResp.getMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Saldo", token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
			} else {
				System.out.println("##TEST OK, API CONSULTAR SALDO OK");
				report.AddLineAssertionError("TEST OK, API CONSULTAR SALDO OK");
		}
	}
	
	@Test
	public void testApiEndpoint7() {
		String path = "./API_Requests/PrePagas/TC_07_Cambio_Estado_Cuenta_Baja.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		oraResp.oraUpdate(report, "UPDATE CUENTAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));		
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Estado", bodyData, token);
		if (!String.valueOf(response.getStatusCode()).equals("200")) {
			report.AddLineAssertionError("Error: Status Code esperado: 200; Status Code obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status Code", String.valueOf(response.getStatusCode()), equalTo("200"));
			} else {
				System.out.println("##TEST OK, API CAMBIO DE ESTADO OK");
				report.AddLineAssertionError("TEST OK, API CAMBIO DE ESTADO OK");
		}
	}
	
	@Test
	public void testApiEndpoint8() {
		String path = "./API_Requests/PrePagas/TC_08_Cambio_Estado_Cuenta_Activa.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		oraResp.oraUpdate(report, "update cuentas set id_estado = 1 where id_cuenta = " + ID_CUENTA, configEntidad);
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));			
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Estado", bodyData, token);
		if(response.getStatusCode()!=200) {
			   report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			   System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			   MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
				}
		else {
			System.out.println("##TEST OK, API CAMBIO DE ESTADO OK");
			report.AddLineAssertionError("TEST OK, API CAMBIO DE ESTADO OK");
	}
	}
	
	@Test
	public void testApiEndpoint9() {
		String path = "./API_Requests/PrePagas/TC_09_Cambio_Estado_Tarjeta_DadaDeBaja.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E);
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));		
			
		//Se muestra el body en el reporte
		report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Estado", bodyData, token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
			}else {
				System.out.println("##TEST OK, API CAMBIO DE ESTADO OK");
				report.AddLineAssertionError("TEST OK, API CAMBIO DE ESTADO OK");
		}
	}
	
	@Test
	public void testApiEndpoint10() {
		String path = "./API_Requests/PrePagas/TC_10_Cambio_Estado_Tarjeta_NormalHabilitada.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));	
			
		//Se muestra el body en el reporte
		report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Estado", bodyData, token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
			} else {
				System.out.println("##TEST OK, API CAMBIO DE ESTADO OK");
				report.AddLineAssertionError("TEST OK, API CAMBIO DE ESTADO OK");
		}
	}
	
	@Test
	public void testApiEndpoint11() {
		String path = "./API_Requests/PrePagas/TC_11_Ingreso_de_Ajuste_Debito2.txt";
		String token = "";	
		String bodyData="";
		repoVar.setTipoTc("API");
		String ID_CUENTA = "10001182";
		String ID_PRODUCTO = "101";
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));			
			
		//Se muestra el body en el reporte
		report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		try {
			response = apiResp.postMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "IngresoDeAjuste", bodyData, token);
			if(response.getStatusCode()!=201) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 201 - Obtenido: " + String.valueOf(response.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("201"));				
				} else {
					System.out.println("##TEST OK, API AJUSTE POR DEBITO OK");
					report.AddLineAssertionError("TEST OK, API AJUSTE POR DEBITO OK");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testApiEndpoint12() {
		String path = "./API_Requests/PrePagas/TC_12_Ingreso_de_Ajuste_Credito2.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = "10001182";
		String ID_PRODUCTO = "101";
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));				
		//Se muestra el body en el reporte
		report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		try {
			response = apiResp.postMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "IngresoDeAjuste", bodyData, token);
			if(response.getStatusCode()!=201) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 201 - Obtenido: " + String.valueOf(response.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("201"));				
			} else {
				System.out.println("##TEST OK, API AJUSTE POR CREDITO OK");
				report.AddLineAssertionError("TEST OK, API AJUSTE POR CREDITO OK");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testApiEndpoint13() {
		String path = "./API_Requests/PrePagas/TC_13_Reimpresion_Tarjeta_Fisica_a_Fisica.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = "21";
		String ID_PRODUCTO = "1";
		String REFERENCIA = "8008";
		String ID_TARJETA = "391";
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		oraResp.oraUpdate(report, "UPDATE TARJETAS SET SOPORTE_FISICO = 1 WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA = " + ID_TARJETA, configEntidad);
        //Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));	
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Reimprimir", bodyData, token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
		} else {
			System.out.println("##TEST OK, API REIMPRESION OK");
			report.AddLineAssertionError("TEST OK, REIMPRESION OK");
	}
	}
	
	@Test
	public void testApiEndpoint14() {
		String path = "./API_Requests/PrePagas/TC_14_Reimpresion_Tarjeta_Virtual_a_Fisica.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_VIRTUAL_2.cuenta_virtual");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_VIRTUAL_2.referencia");
		String ID_TARJETA = JsonPath.from(cuentas_generales).get("CUENTA_VIRTUAL_2.id_tarjeta");
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA = " + ID_TARJETA, configEntidad);
		oraResp.oraDelete(report, "DELETE TARJETAS_HISTORIAL WHERE ID_TARJETA IN (SELECT ID_TARJETA FROM TARJETAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA NOT IN (" + ID_TARJETA + "))", configEntidad);
		oraResp.oraUpdate(report, "UPDATE SOCIOS SET ID_TARJETA_VIGENTE = NULL WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		 oraResp.oraDelete(report, "DELETE TARJETAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA NOT IN (" + ID_TARJETA + ")", configEntidad);
			
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Reimprimir", bodyData, token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
		} else {
			System.out.println("##TEST OK, API REIMPRESION OK");
			report.AddLineAssertionError("TEST OK, REIMPRESION OK");
	}
	}
	
	//@Test
	public void testApiEndpoint15() {
		String path = "./API_Requests/PrePagas/TC_15_Reimpresion_Tarjeta_Virtual_a_Virtual2.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = "35";
		String ID_PRODUCTO = "1";
		String REFERENCIA = "9997";
		String ID_TARJETA = "44";
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		//oraResp.oraUpdate(report, "UPDATE TARJETAS SET SOPORTE_FISICO = 2 WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA = " + ID_TARJETA, configEntidad);
	     
		//oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 0 WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA = " + ID_TARJETA, configEntidad);
		
		oraResp.oraDelete(report, "DELETE TARJETAS_HISTORIAL WHERE ID_TARJETA IN (SELECT ID_TARJETA FROM TARJETAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA NOT IN (" + ID_TARJETA + "))", configEntidad);
		oraResp.oraUpdate(report, "UPDATE SOCIOS SET ID_TARJETA_VIGENTE = NULL WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		oraResp.oraDelete(report, "DELETE TARJETAS WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA NOT IN (" + ID_TARJETA + ")", configEntidad);
		oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = 2 WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_TARJETA = " + ID_TARJETA, configEntidad);
		oraResp.oraUpdate(report, "UPDATE SOCIOS SET ID_TARJETA_VIGENTE = 28 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));	
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Reimprimir", bodyData, token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
		} else {
			System.out.println("##TEST OK, API REIMPRESION OK");
			report.AddLineAssertionError("TEST OK, REIMPRESION OK");
	}
	}
	
	@Test
	public void testApiEndpoint16() {
		String path = "./API_Requests/PrePagas/TC_16_Editar_Datos_De_Cuenta2.txt";
		String token = "";
		String bodyData = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_VIRTUAL_3.cuenta_virtual");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
	   	Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));		
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLineAssertionError("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA, "", bodyData, token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
		} else {
			System.out.println("##TEST OK, API EDITAR DATOS OK");
			report.AddLineAssertionError("TEST OK, EDITAR DATOS OK");
	}
	}
	
	@Test
	public void testApiEndpoint17() {
		String token = "";
		repoVar.setTipoTc("API");
		String ID_CUENTA = "10001685";
		String ID_PRODUCTO = "88";
		Response response;
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		response = apiResp.getMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Tarjetas?tarjetaCompleta=false", token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
		} else {
			System.out.println("##TEST OK, API CONSULTA DE TARJETA OK");
			report.AddLineAssertionError("TEST OK, CONSULTA DE TARJETA OK");
	}
	}
	
	@Test
	public void testApiEndpoint18() {
		String token = "";
		Response response;
		repoVar.setTipoTc("API");
		String ID_CUENTA = "10001685";
		String ID_PRODUCTO = "88";
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		response = apiResp.getMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/", "TarjetaVirtual", token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
		} else {
			System.out.println("##TEST OK, API CONSULTA DE TARJETA VIRTUAL OK");
			report.AddLineAssertionError("TEST OK, CONSULTA DE TARJETA VIRTUAL OK");
	}
	}
	
	@Test
	public void testApiEndpoint19() {
		String token = "";
		Response response;
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		repoVar.setTipoTc("API");
		apiWorker apiResp = new apiWorker();
		Repo_Variables repoVar = new Repo_Variables();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		response = apiResp.getMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Pin", token);

		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
		} else {
			System.out.println("##TEST OK, API CONSULTA DE PINL OK");
			report.AddLineAssertionError("TEST OK, CONSULTA DE PIN OK");
	}
	}
	
	@Test
	public void testApiEndpoint20() {
		String token = "";
		String respBody = "";
		Response response;
		repoVar.setTipoTc("API");
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		apiWorker apiResp = new apiWorker();
		Repo_Variables repoVar = new Repo_Variables();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		response = apiResp.getMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "PinEnc", token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
		}  else {
			System.out.println("##TEST OK, API CONSULTA DE PIN ENCRIPTADO OK");
			report.AddLineAssertionError("TEST OK, CONSULTA DE PIN ENCRIPTADO OK");
	}
	}
	
	@Test
	public void testApiEndpoint21() {
		String path = "./API_Requests/PrePagas/TC_21_Cambio_PIN_Con_Verificacion.txt";
		String token = "";
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		repoVar.setTipoTc("API");
		Response response;
		apiWorker apiResp = new apiWorker();
		Repo_Variables repoVar = new Repo_Variables();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		response = apiResp.getMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Pin", token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
		} else {
			System.out.println("##TEST OK, API CONSULTA DE PIN ENCRIPTADO CON VERIFICACION OK");
			report.AddLineAssertionError("TEST OK, CONSULTA DE PIN ENCRIPTADO CON VERIFICACION OK");
	}
	}
	@Test
	public void testApiEndpoint22() {
		String path = "./API_Requests/PrePagas/TC_22_Cambio_PIN_Sin_verificacion.txt";
		String token = "";
		String bodyData = "";
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		Response response;
		repoVar.setTipoTc("API");
		apiWorker apiResp = new apiWorker();
		Repo_Variables repoVar = new Repo_Variables();
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));	
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLine("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}
		try {
			response = apiResp.postMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/", "Pin", bodyData, token);
			if(response.getStatusCode()!=200) {
				report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
				System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
				MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
			} else {
				System.out.println("##TEST OK, API CAMBIO DE PIN OK");
				report.AddLineAssertionError("TEST OK, CAMBIO DE PIN OK");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testApiEndpoint23() {
		String token = "";
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
		String REFERENCIA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.referencia");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		Response response;
		repoVar.setTipoTc("API");
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		oraResp.oraUpdate(report, "UPDATE TARJETAS SET PIN_INVALIDOS = 3 WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		
		//Obtengo los datos de la base url lel archivo configEntidad que contiene todos los accesos a la entidad
		String base_url = JsonPath.from(configEntidad).get("TOKENPREPAGA.base_url");
		repoVar.setBaseUri(base_url);
		try {
			token = apiResp.GetAccessTokenPP(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");
		}
		response = apiResp.putMethod(repoVar, "/Api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/Tarjetas/" + REFERENCIA + "/Pin/", "BlanquearReIntentos", token);
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
		}  else {
			System.out.println("##TEST OK, API BLANQUEO DE INTENTOS FALLIDOS OK");
			report.AddLineAssertionError("TEST OK, BLANQUEO DE INTENTOS FALLIDOS OK");
	}
	}
	@Test
	public void testApiEndpoints24() {
		String token = "";
		String path = "./API_Requests/PrePagas/TC_24_Editar_Datos_De_Cuenta_Domicilios.txt";
		repoVar.setTipoTc("API");
		Response response;
		String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_VIRTUAL_3.cuenta_virtual");
		String ID_PRODUCTO = JsonPath.from(cuentas_generales).get("PARAMETRIA_CUENTAS.id_producto");
		String bodyData = "";
		apiWorker apiResp = new apiWorker();
		
		//GET POST BODY FROM FILE
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));	
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			
		} catch (Exception E) {
			report.AddLine("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E + "\r\n");
		}	
		//GET TOKEN
				try {
					token = apiResp.GetAccessTokenPP(configEntidad);
				} catch (Exception E) {
					report.AddLineAssertionError("No se obtuvo un token.<br>Error: " + E);
					System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E + "\r\n");					
				}	
		response = apiResp.putMethod(repoVar, "/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA, "", bodyData, token);
		
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode:\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("Validacion: Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
		} else {
			System.out.println("##TEST OK, API EDITAR DATOS DE CUENTA DOMICILIO");
			report.AddLineAssertionError("TEST OK, API EDITAR DATOS DE CUENTA DOMICILIO");
			//result = false;
		}		
	}

	@Test(priority = 1, groups = { "alta de cuenta" })
	void TC_1_ALTA_CUENTA_VIRTUAL() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "01";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " ALTA DE CUENTA PREPAGA TARJETA VIRTUAL";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "1";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas";
		String statusCodeEsperado = "201";
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.post(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);
		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	
	@AfterTest(alwaysRun = true)
	void afterTest() {

	}

	@AfterMethod

	@AfterClass(alwaysRun = true)
	void tearDown() {
		if (repoVar.getTipoTc().equals("API")) {
			report.addTestCaseToGeneralReport(repoVar.getResult(), repoVar.getDataMsg(), "");
			report.saveTestCaseReport(repoVar.getDataMsg());
		} else {
			System.out.println("El caso de prueba es: API");
		}
	}

	@AfterSuite(alwaysRun = true)
	static void tearDownAll() {
		System.out.println("Execution finished");
		report.saveGeneralReport();
	}

}
