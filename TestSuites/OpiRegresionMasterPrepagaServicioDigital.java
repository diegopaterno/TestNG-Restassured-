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


public class OpiRegresionMasterPrepagaServicioDigital {

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
