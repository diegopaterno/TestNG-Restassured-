package TestSuites;

import org.testng.AssertJUnit;

import apis.wolfgang.API_OPERACIONES;
import apis.wolfgang.API_end_to_end;
import apis.wolfgang.AUT_API_OPERACIONES;

import org.testng.annotations.*;

import com.google.gson.JsonArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_Variables;

import io.restassured.path.json.JsonPath;

public class API_autorizador_end_to_end {

	static

	// Init
	DriverManager DM;
	static Datasources data;
	static Reports report;
	static Repo_Variables repoVar;
	static String path;
	static String configEntidad;
	static String entidad;
	static String datosEntradaSegunTc;

	@BeforeSuite(alwaysRun = true)
	static void initAll() throws IOException {
		DM = new DriverManager();
		data = new Datasources();
		report = new Reports();
		repoVar = new Repo_Variables();
		path = "./Datasources/config_entidad.json";
		configEntidad = new String(Files.readAllBytes(Paths.get(path)));
		entidad = JsonPath.from(configEntidad).getString("ENTIDAD.id_entidad");

	}

	@BeforeClass(alwaysRun = true)
	void init() throws IOException {

	}

	@BeforeMethod(alwaysRun = true)

	@BeforeTest(alwaysRun = true)
	void beforeTest() {

	}

	
	//@Test(priority = 1, groups = { "compra" })
	void TC_1_compraApiAutorizador() {
		// DEFINITIONS
		API_end_to_end API_end_to_end = new API_end_to_end();

		// SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase3.csv");

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "01";
		System.out.println(nroTC);
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " compra - local - manual";
		String TCFilesPath = "./TC_Files/API_E2E/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		//String producto = "88";
		//String NRO_TARJETA = "5522681341261779";
		//String VENCIMIENTO = "2504";
		//String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String tokenUrl = "http://sso.globalprocessingqa.com/auth/realms/GlobalProcessing/protocol/openid-connect/token";
		JsonArray resultadosEsperados = null;
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas";
		//v2entidad9999.api.qa.global.globalprocessing.net.ar/Prepaga
		int x = 1;
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas";
		String endPoint = "http://AutorizadorGw.api.qa.global.globalprocessing.net.ar/api/emision/autorizacion";
		String statusCodeEsperado = "201";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_end_to_end.metodoEndToEnd2(data, report, DM,  x, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado, tokenUrl, resultadosEsperados);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	
	@Test(priority = 1, groups = { "compra" })
	void TC_2_compraApiAutorizador() {
		// DEFINITIONS
		API_end_to_end API_end_to_end = new API_end_to_end();
		// SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase3.csv");
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "02";
		System.out.println(nroTC);
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " compra - local - manual - presentaciones MC";
		String TCFilesPath = "./TC_Files/API_E2E/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String tokenUrl = "http://sso.globalprocessingqa.com/auth/realms/GlobalProcessing/protocol/openid-connect/token";
		JsonArray resultadosEsperados = null;
		int x = 1;
		String endPoint = "http://AutorizadorGw.api.qa.global.globalprocessing.net.ar/api/emision/autorizacion";
		String statusCodeEsperado = "201";
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_end_to_end.metodoEndToEnd3(data, report, DM,  x, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado, tokenUrl, resultadosEsperados);
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

