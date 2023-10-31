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
import SmokeTest.AI.TC_01_SMOKE_AI;
import SmokeTest.AI.TC_02_SMOKE_AI;
import SmokeTest.AI.TC_03_SMOKE_AI;
import SmokeTest.AI.TC_04_SMOKE_AI;
import SmokeTest.AI.TC_05_SMOKE_AI;
import SmokeTest.AI.TC_06_SMOKE_AI;
import SmokeTest.AI.TC_07_SMOKE_AI;
import SmokeTest.AI.TC_08_SMOKE_AI;
import SmokeTest.AI.TC_09_SMOKE_AI;
import TestCases.GlobalBackend.TC_01_WEB_GBACKEND;
import TestCases.GlobalBackend.TC_02_WEB_GBACKEND;
import TestCases.GlobalBackend.TC_03_WEB_GBACKEND;
import TestCases.GlobalBatch.TC_01_WEB_BATCH;
import TestCases.GlobalBatch.TC_02_WEB_BATCH;
import TestCases.GlobalBatch.TC_03_WEB_BATCH;
import TestCases.GlobalBatch.TC_04_WEB_BATCH;
import TestCases.GlobalBatch.TC_05_WEB_BATCH;
import TestCases.GlobalBatch.TC_06_WEB_BATCH;
import TestCases.GlobalBatch.TC_07_WEB_BATCH;
import TestCases.GlobalBatch.TC_08_WEB_BATCH;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import opi.wolfgang.*;
import Tools.dbWorker;
import Tools.msgWorker;
import Tools.sshWorker;


public class OPI_TESTS {

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
	void OPI_COMPRA_MANUAL() {	
		// DEFINITIONS
		if(repoVar == null) {try {initAll();} catch (IOException e) {e.printStackTrace();}}
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "1";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Manual";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_2_AUT_OPI_MASTERCARD() {
	void OPI_COMPRA_BANDA() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "2";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Banda";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_3_AUT_OPI_MASTERCARD() {
	void OPI_COMPRA_CHIP() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "3";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Chip";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_4_AUT_OPI_MASTERCARD() {
	void OPI_COMPRA_CONTACLESS() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "4";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Contactless";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_5_AUT_OPI_MASTERCARD() {
	void OPI_COMPRA_ECOMMERCE() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "5";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Ecommerce";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_6_AUT_OPI_MASTERCARD() {
	void OPI_COMPRA_LECTOR_CODIGO() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "6";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Local - Lector de Codigo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_7_AUT_OPI_MASTERCARD() {
	void OPI_COMPRA_BIN_EXTERNO_MANUAL() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "7";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - MANUAL";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_8_AUT_OPI_MASTERCARD() {
	void OPI_BIN_EXTERNO_BANDA() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "8";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - BANDA";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_9_AUT_OPI_MASTERCARD() {
	void OPI_BIN_EXT_CHIP() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "9";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - CHIP";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_10_AUT_OPI_MASTERCARD() {
	void OPI_BIN_EXT_CONTACTLESS() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "10";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - CONTACTLESS";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_11_AUT_OPI_MASTERCARD() {
	void OPI_BIN_EXT_ECOMMERCE() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "11";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - ECOMMERCE";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_12_AUT_OPI_MASTERCARD() {
	void OPI_BIN_EXT_CODIGO() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "12";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Lector de Codigo";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_13_AUT_OPI_MASTERCARD() {
	void OPI_DISPONIBLE_INSUFICIENTE() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "13";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra - Ecommerce - Disponible Insuficiente";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.COMPRA_SALDO_INSUFICIENTE(report, name, configEntidad, entidad, TCFilesPath);

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
	//void TC_14_AUT_OPI_MASTERCARD() {
	void OPI_BIN_EXT_ECOMMERCE_5USD() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "14";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Compra_Ecommer5USD";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_15_AUT_OPI_MASTERCARD() { ****************COMPLETAR************
	void OPI_BIN_EXT_REVERSO_TOTAL_5USD() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "15";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Reverso_Total_Compra_Ecommer5USD";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.REVERSO(report, name, configEntidad, entidad, TCFilesPath);

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
	//void TC_16_AUT_OPI_MASTERCARD() {
	void OPI_BIN_EXT_COMPRA_ECOMMERCE_25USD() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "16";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Compra_Ecommer25USD";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_17_AUT_OPI_MASTERCARD() {
	void OPI_ECOMMERCE_5USD() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "17";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Compra_Ecommer5USD";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_18_AUT_OPI_MASTERCARD() { ****************COMPLETAR************
	void OPI_REVERSO_TOTAL_5USD() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "18";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Reverso_Total_Compra_Ecommer5USD";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("SSH");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = AUT_OPI_OPERACIONES.REVERSO(report, name, configEntidad, entidad, TCFilesPath);

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
	//void TC_19_AUT_OPI_MASTERCARD() {
	void OPI_COMPRA_ECOMMERCE_25USD() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "19";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Compra_Ecommer25USD";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_20_AUT_OPI_MASTERCARD() {
	void OPI_DEVOLUCION_TOTAL_ECOMMERCE_25USD() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "20";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Compra_Ecommer25USD";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	//void TC_20_AUT_OPI_MASTERCARD() {
	void OPI_REVERSO_TOTAL() {
		// DEFINITIONS
		AUT_OPI_OPERACIONES AUT_OPI_OPERACIONES = new AUT_OPI_OPERACIONES();

		// SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		boolean status = false;
		String nroTC = "20";
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " - Prepaga - Compra Extranjera - Compra_Ecommer25USD";
		String TCFilesPath = "./TC_Files/OPI/" + entidad + "/MASTERCARD/TC_" + nroTC;
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
	/**********************API'S**********************/
	@Test
	//void TC_01_SMOKE_AI() {
	void API_COMPRA_DISPONIBLE_INSUFICIENTE() {
		//DEFINITIONS
		TC_01_SMOKE_AI TC01_SMOKE_AI = new TC_01_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_01_SMOKE_AI - Compra Local - Manual - Disponible insuficiente";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC01_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_02_AI_SMOKE")	
	//void TC_02_SMOKE_AI() {
	void API_COMPRA_BANDA() {
		//DEFINITIONS
		TC_02_SMOKE_AI TC02_SMOKE_AI = new TC_02_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_02_SMOKE_AI - Reverso Total - Compra Local - Banda";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC02_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_03_AI_SMOKE")	
	//void TC_03_SMOKE_AI() {
	void API_COMPRA_CHIP() {
		//DEFINITIONS
		TC_03_SMOKE_AI TC03_SMOKE_AI = new TC_03_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_03_SMOKE_AI - Compra Local - Chip";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC03_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_04_AI_SMOKE")	
	//void TC_04_SMOKE_AI() {
	void API_DEVOLUCION_CHIP() {
		//DEFINITIONS
		TC_04_SMOKE_AI TC04_SMOKE_AI = new TC_04_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_04_SMOKE_AI - Reverso Total - Devolucion Local -  Chip";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC04_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_05_AI_SMOKE")	
	//void TC_05_SMOKE_AI() {
	void API_COMPRA_CONTACTLESS() {
		//DEFINITIONS
		TC_05_SMOKE_AI TC05_SMOKE_AI = new TC_05_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_05_SMOKE_AI - Compra Local - Contacless";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC05_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_06_AI_SMOKE")	
	//void TC_06_SMOKE_AI() {
	void API_DEVOLUCION_CONTACTLESS() {
		//DEFINITIONS
		TC_06_SMOKE_AI TC06_SMOKE_AI = new TC_06_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_06_SMOKE_AI - Devolucion Parcial Local - Contacless ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC06_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_07_AI_SMOKE")	
	//void TC_07_SMOKE_AI() {
	void API_DEVOLUCION_PARCIAL_LOCAL() {
		//DEFINITIONS
		TC_07_SMOKE_AI TC07_SMOKE_AI = new TC_07_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_07_SMOKE_AI - Reverso Total - Devolucion Parcial Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC07_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_08_AI_SMOKE")	
	//void TC_08_SMOKE_AI() {
	void API_COMPRA_ECOMMERCE() {
		//DEFINITIONS
		TC_08_SMOKE_AI TC08_SMOKE_AI = new TC_08_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_08_SMOKE_AI - Compra Local - Ecommer";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC08_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_09_AI_SMOKE")	
	//void TC_09_SMOKE_AI() {
	void API_REVERSO_ECOMMERCE() {
		//DEFINITIONS
		TC_09_SMOKE_AI TC09_SMOKE_AI = new TC_09_SMOKE_AI();

		//SET INDIVIDUAL DATASOURCE
		//Nombre Real del TC
		String name = "TC_09_SMOKE_AI - Reverso Total - Compra Local - Ecommer ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC09_SMOKE_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	/******************GLOBAL BATCH PREVIO*****************************/
	// GLOBAL BATCH
		@Test
		//@Tag("ALL")
		//@Tag("GBATCH")
		//@Tag("WEB")
		//@Tag("TC_01_GBATCH")
		//void TC_01_GBATCH() {
		void GLOBAL_BATCH_LOGIN() {
			//DEFINITIONS
			TC_01_WEB_BATCH TC01_BA = new TC_01_WEB_BATCH();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_01_WEB_BA - Login BATCH";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC01_BA.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBATCH")
		//@Tag("WEB")
		//@Tag("TC_02_GBATCH")
		void TC_02_GBATCH() {
			//DEFINITIONS
			TC_02_WEB_BATCH TC02_BA = new TC_02_WEB_BATCH();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_02_WEB_BA - T7001D - Autorizaciones Realizadas";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC02_BA.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBATCH")
		//@Tag("WEB")
		//@Tag("TC_03_GBATCH")
		void TC_03_GBATCH() {
			//DEFINITIONS
			TC_03_WEB_BATCH TC03_BA = new TC_03_WEB_BATCH();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_03_WEB_BA - T2001 Transacciones Diarias";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC03_BA.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBATCH")
		//@Tag("WEB")
		//@Tag("TC_04_GBATCH")
		void TC_04_GBATCH() {
			//DEFINITIONS
			TC_04_WEB_BATCH TC04_BA = new TC_04_WEB_BATCH();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_04_WEB_BA - T3022D - ProcesoAjustes_Cobranzas";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC04_BA.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBATCH")
		//@Tag("WEB")
		//@Tag("TC_05_GBATCH")
		void TC_05_GBATCH() {
			//DEFINITIONS
			TC_05_WEB_BATCH TC05_BA = new TC_05_WEB_BATCH();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_05_WEB_BA - T1027D - Embozado de Tarjetas";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC05_BA.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBATCH")
		//@Tag("WEB")
		//@Tag("TC_06_GBATCH")
		void TC_06_GBATCH() {
			//DEFINITIONS
			TC_06_WEB_BATCH TC06_BA = new TC_06_WEB_BATCH();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_06_WEB_BA - IPM Entrante Emisor";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC06_BA.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBATCH")
		//@Tag("WEB")
		//@Tag("TC_07_GBATCH")
		void TC_07_GBATCH() {
			//DEFINITIONS
			TC_07_WEB_BATCH TC07_BA = new TC_07_WEB_BATCH();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_07_WEB_BA - Presentaciones MC";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC07_BA.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBATCH")
		//@Tag("WEB")
		//@Tag("TC_08_GBATCH")
		void TC_08_GBATCH() {
			//DEFINITIONS
			TC_08_WEB_BATCH TC08_BA = new TC_08_WEB_BATCH();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_08_WEB_BA - R4001 - Novedades Socios - Alta de Cuenta";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC08_BA.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}
	/****************FIN GLOBAL BATCH PREVIO***************************/
		/*************GBACKEND PREVIO**********************************/
		//TCs WEB Global Backend
		@Test
		//@Tag("ALL")
		//@Tag("GBACKEND")
		//@Tag("WEB")
		//@Tag("TC_01_GBACKEND")
		void TC_01_GBACKEND() {
			//DEFINITIONS
			TC_01_WEB_GBACKEND TC01_GB = new TC_01_WEB_GBACKEND();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.ExcelType);
			data.setDataSourceFile("testcase1.xlsx");
			String name = "TC_01_WEB_GB - Login";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;
			
			//for(int x=0;x<data.getTotalIterations();x++) {
			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC01_GB.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBACKEND")
		//@Tag("WEB")
		//@Tag("TC_02_GBACKEND")
		void TC_02_GBACKEND() {
			//DEFINITIONS
			TC_02_WEB_GBACKEND TC02_GB = new TC_02_WEB_GBACKEND();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_02_WEB_GB - Alta de contizacion";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC02_GB.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}

		@Test
		//@Tag("ALL")
		//@Tag("GBACKEND")
		//@Tag("WEB")
		//@Tag("TC_03_GBACKEND")
		void TC_03_GBACKEND() {
			//DEFINITIONS
			TC_03_WEB_GBACKEND TC03_GB = new TC_03_WEB_GBACKEND();
			msgWorker msgWorker = new msgWorker();

			//Inicializacion de las Variables del Repositorio
			repoVar.setTipoTc("WEB");

			//SET INDIVIDUAL DATASOURCE
			data.setDataSourceType(data.CsvType);
			data.setDataSourceFile("testcase2.csv");
			String name = "TC_03_WEB_GB - Modificacion de la cotizacion";
			boolean status = false;
			String msg = "True;Todas las iteraciones resultaron OK";
			int index = 0;

			for(int x=0;x<1;x++) {
				//SET THE EXECUTION PLAN
				status = TC03_GB.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
				report.addTestCaseToGeneralReport(status, name + "_Iteracion_" + x, "");
				report.saveTestCaseReport(name + "_Iteracion_" + x);
				//Verificamos si la ejecucion falla y guardamos el status Gral.
				if (status == false)
				{
					msg = msgWorker.msgGen(repoVar, status, x, index);
					index++;
				}
			}
			//Se avisa x jUnit el resultado de la prueba
			Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
		}
		/*************FIN GBACKEND PREVIO******************************/	


	

	/*******  ********/

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
