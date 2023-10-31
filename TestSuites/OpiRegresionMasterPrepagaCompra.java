package TestSuites;

import org.testng.AssertJUnit;
import org.testng.annotations.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_Variables;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import opi.wolfgang.*;
import Tools.dbWorker;
import Tools.msgWorker;
import Tools.sshWorker;


public class OpiRegresionMasterPrepagaCompra {

	static

	// Init
	DriverManager DM;
	static Datasources data;
	static Reports report;
	static Repo_Variables repoVar;
	static String path;
	static String path_2;
	//static String configEntidad;
	static String entidad;
	static String datosEntradaSegunTc;
	static String configEntidad;
	static String cuentas_generales;

	@BeforeSuite
	static void initAll() throws IOException {
		DM = new DriverManager();
		data = new Datasources();
		report = new Reports();
		repoVar = new Repo_Variables();
		path = "./Datasources/config_entidad.json";
		//configEntidad = new String(Files.readAllBytes(Paths.get(path)));
		configEntidad = new String(Files.readAllBytes(Paths.get(path)));
		entidad = JsonPath.from(configEntidad).getString("ENTIDAD.id_entidad");
		path_2 = "./Datasources/cuentas_generales.json";
		cuentas_generales = new String(Files.readAllBytes(Paths.get(path_2)));

	}

	@BeforeClass
	void init() throws IOException {

	}

	@BeforeMethod

	@BeforeTest
	void beforeTest() {

	}
	@AfterTest
	void afterTest() {

	}
	/*************inicio*************/
	
	@Test(priority=1)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraLocalManual() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "01";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Local - Manual";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=2)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraLocalBanda() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "02";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Local - Banda";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=3)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraLocalCodigo() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "03";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Local - Codigo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=4)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraLocalChip() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "04";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Local - Chip";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=5)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraLocalEcommerce() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "05";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Local - ecommerce";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=6)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraLocalContactless() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "06";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Local - contactless";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=7)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraServicioDigitalManual() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "07";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital - manual";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=8)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraServicioDigitalBanda() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "08";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital - banda";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=9)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraServicioDigitalCodigo() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "09";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital - código";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=10)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraServicioDigitalChip() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "10";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital - chip";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=11)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraServicioDigitalEcommerce() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "11";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital - ecommerce";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=12)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void opiPrepagaCompraServicioDigitalContactless() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "12";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital - contactless";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=13)
	void opiPrepagaCompraServicioDigitalExtManual() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "13";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - manual";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=14)
	void opiPrepagaCompraServicioDigitalExtBanda() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "14";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - banda";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=15)
	void opiPrepagaCompraServicioDigitalExtCodigo() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "15";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - código";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=16)
	void opiPrepagaCompraServicioDigitalExtChip() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "16";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - chip";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=17)
	void opiPrepagaCompraServicioDigitalExtEcommerce() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "17";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - ecommerce";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=18)
	void opiPrepagaCompraServicioDigitalExtContactless() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "18";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - contactless";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=19)
	void opiPrepagaCompraServicioDigitalExtMonedaExtManual() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "19";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - Moneda Ext - manual";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=20)
	void opiPrepagaCompraServicioDigitalExtMonedaExtBanda() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "20";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - Moneda Ext - banda";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=21)
	void opiPrepagaCompraServicioDigitalExtMonedaExtCodigo() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "21";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - Moneda Ext - codigo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=22)
	void opiPrepagaCompraServicioDigitalExtMonedaExtChip() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "22";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - Moneda Ext - chip";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=23)
	void opiPrepagaCompraServicioDigitalExtMonedaExtEcommerce() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "23";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - Moneda Ext - ecommerce";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=24)
	void opiPrepagaCompraServicioDigitalExtMonedaExtContactless() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "24";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Compra Servicio Digital externo - Moneda Ext - contactless";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	
	@Test(priority=62)
	void opiPrepagaCompraBinExtManual() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "62";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - compra - manual - bin externo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}
	@Test(priority=63)
	void opiPrepagaCompraBinExtBanda() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "63";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - compra - banda - bin externo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}
	@Test(priority=64)
	void opiPrepagaCompraBinExtCodigo() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "64";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - compra - código - bin externo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}
	@Test(priority=65)
	void opiPrepagaCompraBinExtChip() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "65";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - compra - chip - bin externo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}
	@Test(priority=66)
	void opiPrepagaCompraBinExtEcommerce() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "66";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - compra - ecommerce - bin externo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}
	@Test(priority=67)
	void opiPrepagaCompraBinExtContactless() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "67";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - compra - contactless - bin externo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}

	/*************fin*************/
	@AfterClass
	void tearDown() {
		if (repoVar.getTipoTc().equals("API")) {
			report.addTestCaseToGeneralReport(repoVar.getResult(), repoVar.getDataMsg(), "");
			report.saveTestCaseReport(repoVar.getDataMsg());
		} else {
			System.out.println("El caso de prueba no es: API");
		}
	}

	@AfterSuite
	static void tearDownAll() {
		System.out.println("Execution finished");
		report.saveGeneralReport();
	}
}
