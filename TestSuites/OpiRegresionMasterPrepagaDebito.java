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


public class OpiRegresionMasterPrepagaDebito {

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
		status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
			status = AUT_OPI_OPERACIONES.debitoAutomatico(report, name, configEntidad, entidad, TCFilesPath);

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
