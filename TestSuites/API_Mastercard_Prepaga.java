package TestSuites;

import org.testng.AssertJUnit;

import apis.wolfgang.API_OPERACIONES;

import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_Variables;

import io.restassured.path.json.JsonPath;

public class API_Mastercard_Prepaga {

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
	@Test(priority = 2, groups = { "alta de cuenta" })
	void TC_2_ALTA_CUENTA_FISICA() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "02";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " alta de cuenta fisica";
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
	@Test(priority = 3, groups = { "alta de cuenta" })
	void TC_3_HABILITAR_TARJETA() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "03";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " habilitar tarjeta fisica";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		
		
		
		//String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/"+ ID_CUENTA +"/Tarjetas/" + referenciaTarjeta + "/Habilitar";
		//String endPoint = baseURL + "/Api/Productos/101/Cuentas/10001182/Tarjetas/0346/Habilitar";
		String statusCodeEsperado = "201";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.habilitarTarjetaFisica(report, name, configEntidad, entidad, TCFilesPath, statusCodeEsperado);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 4, groups = { "crear carga" })
	void TC_4_crearCarga() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "04";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " crear carga";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Transacciones/Cargas";
		String statusCodeEsperado = "201";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.postCrearCarga(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 5, groups = { "consulta" })
	void TC_5_consultarDisponible() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "05";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consultar disponible";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		//String producto = "101";
		//String cuenta = "10001182";
		//String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Transacciones/Cargas";
		String statusCodeEsperado = "201";
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDisponible(report, name, configEntidad, entidad, TCFilesPath, statusCodeEsperado);
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);
		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (!status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 6, groups = { "consulta" })
	void TC_6_consultarDeSaldo() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "05";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consultar saldo ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Saldo";
		String statusCodeEsperado = "201";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeSaldo(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 7, groups = { "consulta" })
	void TC_7_cambioDeEstadoCuentaConBaja() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "07";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " cambio de estado de cuenta dada de baja ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Estado";
	//	/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Estado
	//	String statusCodeEsperado = "201";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.cambioDeEstadoDeCuenta(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 8, groups = { "consulta" })
	void TC_8_cambioDeEstadoCuentaActiva() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "08";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " cambio de estado de cuenta activa ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Estado";
	//	/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Estado
	//	String statusCodeEsperado = "201";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.cambioDeEstadoDeCuenta(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 9, groups = { "consulta" })
	void TC_9_cambioDeEstadoTarjetaDadaDeBaja() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "09";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " cambio de estado de tarjeta dada de baja ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String referenciaTarjeta = "0346";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Estado";
	//	/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Estado
	//	String statusCodeEsperado = "201";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.cambioDeEstadoDeTarjeta(report, name, configEntidad, entidad, TCFilesPath, cuenta, producto, referenciaTarjeta, baseURL);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	
	@Test(priority = 10, groups = { "consulta" })
	void TC_10_cambioDeEstadoTarjetaNormalHabilitada() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "10";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " cambio de estado de tarjeta a normal habilitada ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String referenciaTarjeta = "0346";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Estado";
	//	/api/Productos/" + ID_PRODUCTO + "/Cuentas/" + ID_CUENTA + "/", "Estado
	//	String statusCodeEsperado = "201";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.cambioDeEstadoDeTarjetaNormalHabilitada(report, name, configEntidad, entidad, TCFilesPath, cuenta, producto, referenciaTarjeta, baseURL);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	
	@Test(priority = 11, groups = { "consulta" })
	void TC_11_ingresoAjusteDebito() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "11";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " ingreso ajuste debito ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String referenciaTarjeta = "0346";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.ingresoAjusteDebito(report, name, configEntidad, entidad, TCFilesPath, cuenta, producto, referenciaTarjeta, baseURL);
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);
		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 12, groups = { "consulta" })
	void TC_12_ingresoAjusteCredito() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "12";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " ingreso ajuste credito ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String referenciaTarjeta = "0346";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.ingresoAjusteDebito(report, name, configEntidad, entidad, TCFilesPath, cuenta, producto, referenciaTarjeta, baseURL);
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);
		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 13, groups = { "consulta" })
	void TC_13_reimpresionDeTarjetaFisica() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "13";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " reimpresion de tarjeta fisica ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String referenciaTarjeta = "0346";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.reimpresionDeTarjetaFisica(report, name, configEntidad, entidad, TCFilesPath, cuenta, producto, referenciaTarjeta, baseURL,  cuentas_generales);
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);
		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	
	@Test(priority = 14, groups = { "consulta" })
	void TC_14_reimpresionDeTarjetaVirtualFisica() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "14";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " reimpresion de tarjeta fisica virtual ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String referenciaTarjeta = "0346";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.reimpresionDeTarjetaVirtual(report, name, configEntidad, entidad, TCFilesPath, cuenta, producto, referenciaTarjeta, baseURL,  cuentas_generales);
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);
		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 15, groups = { "consulta" })
	void TC_15_reimpresionDeTarjetaVirtualVirtual() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "15";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " reimpresion de tarjeta virtual - virtual";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String referenciaTarjeta = "0346";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.reimpresionDeTarjetaVirtual(report, name, configEntidad, entidad, TCFilesPath, cuenta, producto, referenciaTarjeta, baseURL,  cuentas_generales);
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);
		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 16, groups = { "consulta" })
	void TC_16_editarDatosDeCuenta() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "16";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " editar datos en cuenta ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "101";
		String cuenta = "10001182";
		String referenciaTarjeta = "0346";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.editarDatosDeCuenta(report, name, configEntidad, entidad, TCFilesPath, cuenta, producto, referenciaTarjeta, baseURL,  cuentas_generales);
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);
		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 17, groups = { "consulta" })
	void TC_17_consultaDeEstadoTarjeta() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "17";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de tarjeta";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas?tarjetaCompleta=false";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeEstadoTarjeta(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 18, groups = { "consulta" })
	void TC_18_consultaDeEstadoTarjetaVirtual() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "18";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de tarjeta";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "1";
		String cuenta = "19";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/TarjetaVirtual";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeEstadoTarjeta(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 19, groups = { "consulta" })
	void TC_19_consultaDePIN() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "19";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de PIN";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String referenciaTarjeta = "2851"; 
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/" + referenciaTarjeta + "/Pin";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDePIN(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 20, groups = { "consulta" })
	void TC_20_consultaDePINENCRIPTADO() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "20";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de PIN ENCRIPTADO";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String referenciaTarjeta = "2851"; 
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/" + referenciaTarjeta + "/Pin";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDePIN(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 21, groups = { "consulta" })
	void TC_21_cambioDePINconVerificacion() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "20";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " cambio de PIN con verificacion";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String referenciaTarjeta = "2851"; 
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/" + referenciaTarjeta + "/Pin";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.cambioDePIN(report, DM, 0, name, entidad, cuentas_generales);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 22, groups = { "consulta" })
	void TC_22_cambioDePINsinVerificacion() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "22";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " cambio de PIN sin verificacion";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String referenciaTarjeta = "2851"; 
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/" + referenciaTarjeta + "/Pin";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.cambioDePIN(report, DM, 0, name, configEntidad, cuentas_generales);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 23, groups = { "consulta" })
	void TC_23_cambioDePINconVerificacion() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "23";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " blanqueo de PIN ";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String referenciaTarjeta = "2851"; 
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/" + referenciaTarjeta + "/Pin";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.blanqueoDePin(report, DM, 0, name, configEntidad, cuentas_generales);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se aMASTERCARD x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority = 24, groups = { "consulta" })
	void TC_24_modificacionDeDomicilioCorrespondencia() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String name = "TC_24_API_PP - Modificacion_Domicilio_Legal_Correspondencia  ";
		//String nroTC = "24";
		//String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " modificacion de domicilio de correspondencia ";
		//String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		//String producto = "88";
		//String cuenta = "10001685";
		//String referenciaTarjeta = "2851"; 
		//String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		//String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/" + referenciaTarjeta + "/Pin";
		//String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.blanqueoDePin(report, DM, 0, name, configEntidad, cuentas_generales);

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

	