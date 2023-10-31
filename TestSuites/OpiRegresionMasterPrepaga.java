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


public class OpiRegresionMasterPrepaga {

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
	@Test(priority=25)
	void opiPrepagaDebitoAutomatico() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "25";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - manual";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA(report, name, configEntidad, entidad, TCFilesPath);

		//es necesario generar un metodo de compra con debito para que genere este el delete del 
		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	@Test(priority=26)
	void opiPrepagaDebitoAutomaticoBanda() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "26";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - banda";
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
	@Test(priority=27)
	void opiPrepagaDebitoAutomaticoCodigo() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "27";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - codigo";
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
	@Test(priority=28)
	void opiPrepagaDebitoAutomaticoChip() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "28";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - chip";
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
	@Test(priority=29)
	void opiPrepagaDebitoAutomaticoEcommerce() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "29";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - ecommerce";
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
	@Test(priority=30)
	void opiPrepagaDebitoAutomaticoContactless() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "30";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - contactless";
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
	@Test(priority=31)
	void opiPrepagaDebitoAutomaticoMonedaExtManual() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "31";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - Moneda Ext - manual";
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
	@Test(priority=32)
	void opiPrepagaDebitoAutomaticoMonedaExtBanda() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "32";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - Moneda Ext - banda";
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
	@Test(priority=33)
	void opiPrepagaDebitoAutomaticoMonedaExtCodigo() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE
		// Nombre Real del TC
		boolean status = false;
		String nroTC = "33";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - Moneda Ext - código";
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
		@Test(priority=34)
		void opiPrepagaDebitoAutomaticoMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "34";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - Moneda Ext - chip";
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
		@Test(priority=35)
		void opiPrepagaDebitoAutomaticoMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "35";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - Moneda Ext - ecommerce";
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
		@Test(priority=36)
		void opiPrepagaDebitoAutomaticoMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "36";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Debito Automatico - Moneda Ext - contactless";
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
		@Test(priority=37)
		void opiPrepagaExtraccionAdelantoManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "37";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - Manual";
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
		@Test(priority=38)
		void opiPrepagaExtraccionAdelantoBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "38";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - banda";
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
		@Test(priority=39)
		void opiPrepagaExtraccionAdelantoBanda2() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "39";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - banda 2";
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
		@Test(priority=40)
		void opiPrepagaExtraccionAdelantoCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "40";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - código";
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
		@Test(priority=41)
		void opiPrepagaExtraccionAdelantoChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "41";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - chip";
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
		@Test(priority=42)
		void opiPrepagaExtraccionAdelantoEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "42";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - ecommerce";
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
		@Test(priority=43)
		void opiPrepagaExtraccionAdelantoContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "43";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - contactless";
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
		@Test(priority=44)
		void opiPrepagaExtraccionAdelantoMonedaExtranjeraManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "44";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - Moneda Ext - manual";
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
		@Test(priority=45)
		void opiPrepagaExtraccionAdelantoMonedaExtranjeraBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "45";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - Moneda Ext - banda";
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
		@Test(priority=46)
		void opiPrepagaExtraccionAdelantoMonedaExtranjeraCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "46";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - Moneda Ext - código";
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
		@Test(priority=47)
		void opiPrepagaExtraccionAdelantoMonedaExtranjeraChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "47";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - Moneda Ext - chip";
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
		@Test(priority=48)
		void opiPrepagaExtraccionAdelantoMonedaExtranjeraEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "48";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - Moneda Ext - ecommerce";
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
		@Test(priority=49)
		void opiPrepagaExtraccionAdelantoMonedaExtranjeraContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "49";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Extracción / Adelanto - Moneda Ext - contactless";
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
		@Test(priority=50)
		void opiPrepagaCashbackManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "50";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - manual";
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
		@Test(priority=51)
		void opiPrepagaCashbackBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "51";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - banda";
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
		@Test(priority=52)
		void opiPrepagaCashbackCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "52";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - código";
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
		@Test(priority=53)
		void opiPrepagaCashbackChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "53";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - chip";
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
		@Test(priority=54)
		void opiPrepagaCashbackEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "54";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - ecommerce";
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
		@Test(priority=55)
		void opiPrepagaCashbackContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "55";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - contactless";
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
		@Test(priority=56)
		void opiPrepagaCashbackManualMonedaExt() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "56";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - manual - moneda extranjera";
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
		@Test(priority=57)
		void opiPrepagaCashbackBandaMonedaExt() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "57";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - banda - moneda extranjera";
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
		@Test(priority=58)
		void opiPrepagaCashbackCodigoMonedaExt() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "58";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - código - moneda extranjera";
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
		@Test(priority=59)
		void opiPrepagaCashbackChipMonedaExt() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "59";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - chip - moneda extranjera";
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
		@Test(priority=60)
		void opiPrepagaCashbackEcommerceMonedaExt() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "60";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - ecommerce - moneda extranjera";
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
		@Test(priority=61)
		void opiPrepagaCashbackContactlessMonedaExt() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "61";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Cashback - contactless - moneda extranjera";
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
		@Test(priority=68)
		void opiPrepagaServicioDigitalBinExt() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "68";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital - bin externo";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=69)
		void opiPrepagaServicioDigitalBinExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "69";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital - bin externo - banda";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=70)
		void opiPrepagaServicioDigitalBinExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "70";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital - bin externo - código";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=71)
		void opiPrepagaServicioDigitalBinExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "71";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital - bin externo - chip";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=72)
		void opiPrepagaServicioDigitalBinExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "72";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital - bin externo - ecommerce";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=73)
		void opiPrepagaServicioDigitalBinExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "73";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital - bin externo - contactless";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=74)
		void opiPrepagaServicioDigitalExtBinExt() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "74";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=75)
		void opiPrepagaServicioDigitalExtBinExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "75";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - banda";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=76)
		void opiPrepagaServicioDigitalExtBinExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "76";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - código";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=77)
		void opiPrepagaServicioDigitalExtBinExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "77";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - chip";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=78)
		void opiPrepagaServicioDigitalExtBinExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "78";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - ecommerce";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=79)
		void opiPrepagaServicioDigitalExtBinExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "79";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - contactless";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=80)
		void opiPrepagaServicioDigitalExtBinExtMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "80";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - moneda ext - manual";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=81)
		void opiPrepagaServicioDigitalExtBinExtMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "81";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - moneda ext - banda";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=82)
		void opiPrepagaServicioDigitalExtBinExtMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "82";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - moneda ext - código";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=83)
		void opiPrepagaServicioDigitalExtBinExtMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "83";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - moneda ext - chip";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=84)
		void opiPrepagaServicioDigitalExtBinExtMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "84";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - moneda ext - ecommerce";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=85)
		void opiPrepagaServicioDigitalExtBinExtMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "85";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - Servicio Digital ext - bin externo - moneda ext - contactless";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.servicioDigital(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=86)
		void opiPrepagaDebitoAutomaticoBinExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "86";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - bin externo - manual";
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
		@Test(priority=87)
		void opiPrepagaDebitoAutomaticoBinExtbanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "87";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - bin externo - banda";
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
		@Test(priority=88)
		void opiPrepagaDebitoAutomaticoBinExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "88";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - bin externo - código";
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
		@Test(priority=89)
		void opiPrepagaDebitoAutomaticoBinExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "89";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - bin externo - chip";
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
		@Test(priority=90)
		void opiPrepagaDebitoAutomaticoBinExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "90";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - bin externo - ecommerce";
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
		@Test(priority=91)
		void opiPrepagaDebitoAutomaticoBinExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "91";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - bin externo - contactless";
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
		@Test(priority=92)
		void opiPrepagaDebitoAutomaticoLocalMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "92";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - local - moneda ext - manual";
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
		@Test(priority=93)
		void opiPrepagaDebitoAutomaticoLocalMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "93";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - local - moneda ext - banda";
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
		@Test(priority=94)
		void opiPrepagaDebitoAutomaticoLocalMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "94";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - local - moneda ext - código";
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
		@Test(priority=95)
		void opiPrepagaDebitoAutomaticoLocalMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "95";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - local - moneda ext - chip";
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
		@Test(priority=96)
		void opiPrepagaDebitoAutomaticoLocalMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "96";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - local - moneda ext - ecommerce";
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
		@Test(priority=97)
		void opiPrepagaDebitoAutomaticoLocalMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "97";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - debito automatico - local - moneda ext - contactless";
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
		@Test(priority=98)
		void opiPrepagaExtraccionAdelantoLocalManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "98";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - local - manual";
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
		@Test(priority=99)
		void opiPrepagaExtraccionAdelantoLocal() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "99";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - local ";
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
		@Test(priority=99)
		void opiPrepagaExtraccionAdelantoMonedaLocal() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "99";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - local ";
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
		@Test(priority=100)
		void opiPrepagaExtraccionAdelantoMonedaLocalBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "100";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - local - banda ";
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
		@Test(priority=101)
		void opiPrepagaExtraccionAdelantoMonedaLocalCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "101";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - local - código ";
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
		@Test(priority=102)
		void opiPrepagaExtraccionAdelantoMonedaLocalChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "102";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - local - chip ";
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
		@Test(priority=103)
		void opiPrepagaExtraccionAdelantoMonedaLocalEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "103";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - local - ecommerce ";
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
		@Test(priority=104)
		void opiPrepagaExtraccionAdelantoMonedaLocalContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "104";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - local - contactless ";
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
		@Test(priority=105)
		void opiPrepagaExtraccionAdelantoMonedaLocalComExtmanual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "105";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - externo - manual ";
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
		@Test(priority=106)
		void opiPrepagaExtraccionAdelantoMonedaLocalComExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "106";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - externo - banda ";
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
		@Test(priority=107)
		void opiPrepagaExtraccionAdelantoMonedaLocalComExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "107";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - externo - codigo ";
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
		@Test(priority=108)
		void opiPrepagaExtraccionAdelantoMonedaLocalComExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "108";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - externo - chip ";
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
		@Test(priority=109)
		void opiPrepagaExtraccionAdelantoMonedaLocalComExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "109";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - externo - ecommerce ";
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
		@Test(priority=110)
		void opiPrepagaExtraccionAdelantoMonedaLocalComExtcontactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "110";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - extraccion / adelanto - externo - contactless ";
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
		@Test(priority=111)
		void opiPrepagaCashbackBinExtmanual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "111";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - manual ";
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
		@Test(priority=112)
		void opiPrepagaCashbackBinExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "112";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - banda ";
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
		@Test(priority=113)
		void opiPrepagaCashbackBinExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "113";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - código ";
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
		@Test(priority=114)
		void opiPrepagaCashbackBinExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "114";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - chip ";
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
		@Test(priority=115)
		void opiPrepagaCashbackBinExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "115";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - ecommerce ";
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
		@Test(priority=116)
		void opiPrepagaCashbackBinExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "116";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - contactless ";
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
		@Test(priority=117)
		void opiPrepagaCashbackBinExtMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "117";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - moneda ext - manual ";
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
		@Test(priority=118)
		void opiPrepagaCashbackBinExtMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "118";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - moneda ext - banda ";
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
		@Test(priority=119)
		void opiPrepagaCashbackBinExtMonedaExtBanda2() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "119";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - moneda ext - banda ";
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
		@Test(priority=120)
		void opiPrepagaCashbackBinExtMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "120";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - moneda ext - código ";
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
		@Test(priority=121)
		void opiPrepagaCashbackBinExtMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "121";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - moneda ext - chip ";
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
		@Test(priority=122)
		void opiPrepagaCashbackBinExtMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "122";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - moneda ext - ecommerce ";
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
		@Test(priority=123)
		void opiPrepagaCashbackBinExtMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "123";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - cashback - bin externo - moneda ext - contactless ";
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
		@Test(priority=124)
		void opiPrepagaDevolucionManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "124";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=125)
		void opiPrepagaDevolucionBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "125";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=126)
		void opiPrepagaDevolucionCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "126";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=127)
		void opiPrepagaDevolucionChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "127";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=128)
		void opiPrepagaDevolucionEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "128";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=129)
		void opiPrepagaDevolucionContactles() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "129";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=130)
		void opiPrepagaDevolucionMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "130";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - moneda ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=131)
		void opiPrepagaDevolucionMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "131";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - moneda ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=132)
		void opiPrepagaDevolucionMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "132";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - moneda ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=133)
		void opiPrepagaDevolucionMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "133";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - moneda ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=134)
		void opiPrepagaDevolucionMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "134";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - moneda ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=135)
		void opiPrepagaDevolucionMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "135";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - moneda ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=136)
		void opiPrepagaDevolucionBinExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "136";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=137)
		void opiPrepagaDevolucionBinExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "137";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=138)
		void opiPrepagaDevolucionBinExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "138";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=139)
		void opiPrepagaDevolucionBinExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "139";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=140)
		void opiPrepagaDevolucionBinExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "140";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=141)
		void opiPrepagaDevolucionBinExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "141";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=142)
		void opiPrepagaDevolucionBinExtMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "142";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - moneda ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=143)
		void opiPrepagaDevolucionBinExtMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "143";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - moneda ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=144)
		void opiPrepagaDevolucionBinExtMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "144";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - moneda ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=145)
		void opiPrepagaDevolucionBinExtMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "145";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - moneda ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=146)
		void opiPrepagaDevolucionBinExtMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "146";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - moneda ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=147)
		void opiPrepagaDevolucionBinExtMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "147";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion - bin externo - moneda ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=148)
		void opiPrepagaReversoManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "148";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=149)
		void opiPrepagaReversoBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "149";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=150)
		void opiPrepagaReversoCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "150";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=151)
		void opiPrepagaReversoChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "151";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=152)
		void opiPrepagaReversoEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "152";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=153)
		void opiPrepagaReversoContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "153";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=154)
		void opiPrepagaReversoMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "154";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - moneda ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=155)
		void opiPrepagaReversoMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "155";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - moneda ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=156)
		void opiPrepagaReversoMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "156";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - moneda ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=157)
		void opiPrepagaReversoMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "157";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - moneda ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=158)
		void opiPrepagaReversoMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "158";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - moneda ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=159)
		void opiPrepagaReversoMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "159";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - moneda ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=160)
		void opiPrepagaReversoBinExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "160";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=161)
		void opiPrepagaReversoBinExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "161";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=162)
		void opiPrepagaReversoBinExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "162";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=163)
		void opiPrepagaReversoBinExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "163";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=164)
		void opiPrepagaReversoBinExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "164";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=165)
		void opiPrepagaReversoBinExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "165";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=166)
		void opiPrepagaReversoBinExtMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "166";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - moneda ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=167)
		void opiPrepagaReversoBinExtMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "167";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - moneda ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=168)
		void opiPrepagaReversoBinExtMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "168";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - moneda ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=169)
		void opiPrepagaReversoBinExtMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "169";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - moneda ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=170)
		void opiPrepagaReversoBinExtMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "170";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - moneda ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=171)
		void opiPrepagaReversoBinExtMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "171";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso - bin ext - moneda ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=172)
		void opiPrepagaDevolucionParcialManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "172";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=173)
		void opiPrepagaDevolucionParcialBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "173";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=174)
		void opiPrepagaDevolucionParcialCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "174";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=175)
		void opiPrepagaDevolucionParcialChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "175";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=176)
		void opiPrepagaDevolucionParcialEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "176";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=177)
		void opiPrepagaDevolucionParcialContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "177";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=178)
		void opiPrepagaDevolucionParcialMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "178";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - moneda ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=179)
		void opiPrepagaDevolucionParcialMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "179";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - moneda ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=180)
		void opiPrepagaDevolucionParcialMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "180";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - moneda ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=181)
		void opiPrepagaDevolucionParcialMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "181";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - moneda ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=182)
		void opiPrepagaDevolucionParcialMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "182";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - moneda ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=183)
		void opiPrepagaDevolucionParcialMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "183";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - moneda ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=184)
		void opiPrepagaDevolucionParcialBinExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "184";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=185)
		void opiPrepagaDevolucionParcialBinExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "185";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=186)
		void opiPrepagaDevolucionParcialBinExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "186";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=187)
		void opiPrepagaDevolucionParcialBinExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "187";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=188)
		void opiPrepagaDevolucionParcialBinExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "188";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=189)
		void opiPrepagaDevolucionParcialBinExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "189";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=190)
		void opiPrepagaDevolucionParcialBinExtMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "190";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - moneda ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=191)
		void opiPrepagaDevolucionParcialBinExtMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "191";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - moneda ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=192)
		void opiPrepagaDevolucionParcialBinExtMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "192";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - moneda ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=193)
		void opiPrepagaDevolucionParcialBinExtMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "193";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - moneda ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=194)
		void opiPrepagaDevolucionParcialBinExtMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "194";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - moneda ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=195)
		void opiPrepagaDevolucionParcialBinExtMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "195";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - devolucion parcial - bin ext - moneda ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=196)
		void opiPrepagaReversoParcialManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "196";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=197)
		void opiPrepagaReversoParcialBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "197";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=198)
		void opiPrepagaReversoParcialCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "198";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=199)
		void opiPrepagaReversoParcialChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "199";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=200)
		void opiPrepagaReversoParcialEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "200";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=201)
		void opiPrepagaReversoParcialContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "201";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=202)
		void opiPrepagaReversoParcialMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "202";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - moneda ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=203)
		void opiPrepagaReversoParcialMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "203";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - moneda ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=204)
		void opiPrepagaReversoParcialMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "204";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - moneda ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=205)
		void opiPrepagaReversoParcialMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "205";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - moneda ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=206)
		void opiPrepagaReversoParcialMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "206";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - moneda ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=207)
		void opiPrepagaReversoParcialMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "207";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - moneda ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=208)
		void opiPrepagaReversoParcialBinExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "208";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=209)
		void opiPrepagaReversoParcialBinExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "209";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=210)
		void opiPrepagaReversoParcialBinExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "210";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=211)
		void opiPrepagaReversoParcialBinExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "211";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=212)
		void opiPrepagaReversoParcialBinExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "212";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=213)
		void opiPrepagaReversoParcialBinExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "213";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=214)
		void opiPrepagaReversoParcialBinExtMonedaExtManual() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "214";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - moneda ext - manual ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=215)
		void opiPrepagaReversoParcialBinExtMonedaExtBanda() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "215";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - moneda ext - banda ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=216)
		void opiPrepagaReversoParcialBinExtMonedaExtCodigo() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "216";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - moneda ext - código ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=217)
		void opiPrepagaReversoParcialBinExtMonedaExtChip() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "217";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - moneda ext - chip ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=218)
		void opiPrepagaReversoParcialBinExtMonedaExtEcommerce() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "218";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - moneda ext - ecommerce ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
		@Test(priority=219)
		void opiPrepagaReversoParcialBinExtMonedaExtContactless() {	
			// DEFINITIONS
			if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
			AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

			// SET INDIVIDUAL DATASOURCE
			// Nombre Real del TC
			boolean status = false;
			String nroTC = "219";
			String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + "Prepaga - reverso parcial - bin ext - moneda ext - contactless ";
			String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
			String msg = "True;Resultado de la ejecucion OK. TC: " + name;

			// Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("SSH");
			repoVar.setResult(status);
			repoVar.setDataMsg(name);

			// SET THE EXECUTION PLAN
			status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

			// Configuracion de variables para el armado del reporte
			repoVar.setResult(status);

			// Verificamos si la ejecucion falla y guardamos el status Gral.
			if (status == false) {
				msg = "Fail;Fallo la ejecucion. TC: " + name;
			}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	/******************************fin de la suite*************************************/
	//@Test(priority=2)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_CONSUMO_EXTRANJERO() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "2";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Consumo - Servicio Digital";
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
	//@Test(priority=3)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_SERVICIOS_DIGITALES_LOCAL() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "3";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Consumo - Servicio Digital";
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
	//@Test(priority=1)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_REVERSO_CONSUMO_LOCAL() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "4";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Manual";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.ANULACIONREVERSO(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	//@Test(priority=1)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_REVERSO_CONSUMO_MONEDA_EXTRANJERA() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "1";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Manual";
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
	
	//@Test(priority=1)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_RECHAZO_MODULO_FRAUDE() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "1";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Manual";
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
	//@Test(priority=1)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_SERVICIOS_DIGITAL_EXTRANJEA_MAYOR_u$s300() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "1";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Manual";
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
	//@Test(priority=1)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_DEVOLUCION_MAYOR_u$s300() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "1";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Manual";
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
	//@Test(priority=1)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_CASHBACK() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "1";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Manual";
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
	
	/*******************PRUEBA DEVOLUCION*******************/
	
	//@Test(priority=1)
	//void TC_1_AUT_OPI_MASTERCARD() {
	void OPI_DEVOLUCION() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "5";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - DEVOLUCION - Manual";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/REGRESION/MASTERCARD/PREPAGA/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.compraDevolucion(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
		// Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
	}
	
	/*******************FIN DE PRUEBA DEVOLUCION************/

	@AfterTest
	void afterTest() {

	}

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
