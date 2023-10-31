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


public class OpiRegresionMasterPrepagaCashback {

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
		String name = "TC_" + nroTC + "_AUT_OPI_MASTERCARD_" + entidad + " Prepaga - Cashback - manual";
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
		/*if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}*/
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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		/*if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}*/
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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		/*if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}*/
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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		/*if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}*/
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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		/*if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}*/
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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}
	//@Test(priority=56)
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
	//@Test(priority=57)
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
	//@Test(priority=58)
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
	//@Test(priority=59)
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
	//@Test(priority=60)
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
	//@Test(priority=61)
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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}
	//@Test(priority=116)
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
		status = AUT_OPI_OPERACIONES.compraConCashback(report, name, configEntidad, entidad, TCFilesPath);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}
	// Se avisa x jUnit el resultado de la prueba
	AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1], "True", msg.split(";")[0]);
}
	//@Test(priority=117)
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
	//@Test(priority=118)
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
	//@Test(priority=119)
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
	//@Test(priority=120)
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
	//@Test(priority=121)
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
	//@Test(priority=122)
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
	//@Test(priority=123)
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
