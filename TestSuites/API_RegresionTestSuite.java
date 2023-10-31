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

public class API_RegresionTestSuite {

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

	@Test(priority = 1, groups = { "alta de cuenta" })
	void TC_1_ALTA_CUENTA_VIRTUAL() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "01";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " alta de cuenta virtual";
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
	@Test(priority = 3, groups = { "crear carga" })
	void TC_3_crearCarga() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "03";
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
	@Test(priority = 4, groups = { "consulta" })
	void TC_4_consultaDeCuenta() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "04";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de cuenta";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta;
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeCuenta(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

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
	void TC_5_consultaDeEstadoTarjeta() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "05";
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
	@Test(priority = 6, groups = { "consulta" })
	void TC_6_consultaDeSaldo() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "06";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de saldo";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Saldo";
		String statusCodeEsperado = "200";

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
	void TC_7_consultaDeSaldoEnDolares() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "07";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de saldo en dolares";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String cuenta = "10001685";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Saldo";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeSaldoEnDolares(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

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
	void TC_8_consultaDeConsumos() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "08";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta consumos";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		//String cuenta = "10001685";
		String cuenta = "10001454";
		String fechaDesde = "2023-09-30";
		String fechaHasta = "2023-10-01";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Transacciones/Consumos?FechaDesde="+fechaDesde+"&FechaHasta="+fechaHasta+"&Confirmadas=true";
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeConsumos(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

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
	void TC_9_consultaDeDevoluciones() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "09";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de devoluciones";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		//String cuenta = "10001685";
		String cuenta = "10001454";
		String fechaDesde = "2023-08-30";
		String fechaHasta = "2023-10-01";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Transacciones/Devoluciones?FechaDesde="+fechaDesde+"&FechaHasta="+fechaHasta;
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeDevoluciones(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

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
	void TC_10_consultaDeAdelantos() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "10";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de adelantos";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		//String cuenta = "10001685";
		String cuenta = "10001454";
		String fechaDesde = "2023-07-30";
		String fechaHasta = "2023-10-01";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Transacciones/Adelantos?FechaDesde="+fechaDesde+"&FechaHasta="+fechaHasta;
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeAdelantos(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

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
	void TC_11_consultaDeMovimientos() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "11";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de movimientos";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		//String cuenta = "10001685";
		String cuenta = "10001454";
		String fechaDesde = "2023-09-20";
		String fechaHasta = "2023-09-30";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Transacciones/Movimientos?FechaDesde="+fechaDesde+"&FechaHasta="+fechaHasta;
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeMovimientos(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

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
	void TC_12_consultaDeAjustes() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "12";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de ajustes";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		//String cuenta = "10001685";
		String cuenta = "10001454";
		String fechaDesde = "2023-09-01";
		String fechaHasta = "2023-09-30";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Transacciones/Ajustes?FechaDesde="+fechaDesde+"&FechaHasta="+fechaHasta;
		String statusCodeEsperado = "200";

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeAjustes(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);

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
	void TC_13_consultaDeCargasPorCuentaYProducto() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "13";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de cargas por cuenta y producto";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		//String cuenta = "10001685";
		String cuenta = "10001454";
		String fechaDesde = "2023-09-20";
		String fechaHasta = "2023-09-30";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Transacciones/Cargas?FechaDesde="+fechaDesde+"&FechaHasta="+fechaHasta;
		String statusCodeEsperado = "200";
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeCargas(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);
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
	void TC_14_consultaDePIN() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "14";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de PIN";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String tarjeta = "5522681341261779";
		String cuenta = "10001454";
		String fechaDesde = "2023-09-20";
		String fechaHasta = "2023-09-30";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/"+ tarjeta +"/Pin";
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
	@Test(priority = 15, groups = { "consulta" })
	void TC_15_consultaDePINEncriptado() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "15";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de PIN encriptado";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String tarjeta = "5522681341261779";
		String cuenta = "10001454";
		String fechaDesde = "2023-09-20";
		String fechaHasta = "2023-09-30";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/api/Productos/" + producto + "/Cuentas/" + cuenta + "/Tarjetas/"+ tarjeta +"/PinEnc";
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
	@Test(priority = 16, groups = { "consulta" })
	void TC_16_consultaDeDisponible() {
		// DEFINITIONS
		API_OPERACIONES API_OPERACIONES = new API_OPERACIONES();
		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "16";
		String name = "TC_" + nroTC + "_API_OPERACIONES_MASTERCARD_" + entidad + " consulta de disponible";
		String TCFilesPath = "./TC_Files/API/" + entidad + "/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;
		String producto = "88";
		String tarjeta = "5522681341261779";
		String cuenta = "10001454";
		String fechaDesde = "2023-09-20";
		String fechaHasta = "2023-09-30";
		String baseURL = JsonPath.from(configEntidad).getString("TOKENPREPAGA.base_url");
		String endPoint = baseURL + "/Api/Cuentas/" + cuenta + "/Disponible";
		String statusCodeEsperado = "200";
		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);
		// SET THE EXECUTION PLAN
		status = API_OPERACIONES.consultaDeDisponible(report, name, configEntidad, entidad, TCFilesPath, endPoint, statusCodeEsperado);
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
