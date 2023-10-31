package TestSuites;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_Variables;
import Tools.msgWorker;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;


//WEB GO
//API PREPAGAS
//TESTCASES
import TestCases.ApiPrepaga.TC_01_API_PP;
import TestCases.ApiPrepaga.TC_02_API_PP;
import TestCases.ApiPrepaga.TC_03_API_PP;
import TestCases.ApiPrepaga.TC_04_API_PP;
import TestCases.ApiPrepaga.TC_05_API_PP;
import TestCases.ApiPrepaga.TC_06_API_PP;
import TestCases.ApiPrepaga.TC_07_API_PP;
import TestCases.ApiPrepaga.TC_08_API_PP;
import TestCases.ApiPrepaga.TC_09_API_PP;
import TestCases.ApiPrepaga.TC_10_API_PP;
import TestCases.ApiPrepaga.TC_11_API_PP;
import TestCases.ApiPrepaga.TC_12_API_PP;
import TestCases.ApiPrepaga.TC_13_API_PP;
import TestCases.ApiPrepaga.TC_14_API_PP;
import TestCases.ApiPrepaga.TC_15_API_PP;
import TestCases.ApiPrepaga.TC_16_API_PP;
import TestCases.ApiPrepaga.TC_17_API_PP;
import TestCases.ApiPrepaga.TC_18_API_PP;
import TestCases.ApiPrepaga.TC_19_API_PP;
import TestCases.ApiPrepaga.TC_20_API_PP;
import TestCases.ApiPrepaga.TC_21_API_PP;
import TestCases.ApiPrepaga.TC_22_API_PP;
import TestCases.ApiPrepaga.TC_23_API_PP;
import TestCases.ApiPrepaga.TC_24_API_PP;
import TestCases.ApiPrepaga.TC_PruebaGet_API;
import TestCases.AutorizadorInterno.TC_01_API_AI;
import TestCases.AutorizadorInterno.TC_02_API_AI;
import TestCases.AutorizadorInterno.TC_03_API_AI;
import TestCases.AutorizadorInterno.TC_04_API_AI;
import TestCases.AutorizadorInterno.TC_05_API_AI;
import TestCases.AutorizadorInterno.TC_06_API_AI;
import TestCases.AutorizadorInterno.TC_07_API_AI;
import TestCases.AutorizadorInterno.TC_08_API_AI;
import TestCases.AutorizadorInterno.TC_09_API_AI;
import TestCases.AutorizadorInterno.TC_10_API_AI;
import TestCases.AutorizadorInterno.TC_11_API_AI;
import TestCases.AutorizadorInterno.TC_12_API_AI;
import TestCases.AutorizadorInterno.TC_13_API_AI;
import TestCases.AutorizadorInterno.TC_14_API_AI;
import TestCases.AutorizadorInterno.TC_15_API_AI;
import TestCases.AutorizadorInterno.TC_16_API_AI;
import TestCases.AutorizadorInterno.TC_17_API_AI;
import TestCases.AutorizadorInterno.TC_18_API_AI;
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
import TestCases.GlobalOnLine.TC_01_WEB_GO;
import TestCases.GlobalOnLine.TC_02_WEB_GO;
import TestCases.GlobalOnLine.TC_03_WEB_GO;
import TestCases.GlobalOnLine.TC_04_WEB_GO;
import TestCases.GlobalOnLine.TC_05_WEB_GO;
import TestCases.GlobalOnLine.TC_06_WEB_GO;
import TestCases.GlobalOnLine.TC_07_WEB_GO;
import TestCases.GlobalOnLine.TC_08_WEB_GO;
import TestCases.GlobalOnLine.TC_09_WEB_GO;
import TestCases.GlobalOnLine.TC_10_WEB_GO;
import TestCases.GlobalOnLine.TC_11_WEB_GO;
import TestCases.GlobalOnLine.TC_12_WEB_GO;
import TestCases.GlobalOnLine.TC_13_WEB_GO;
import TestCases.GlobalOnLine.TC_14_WEB_GO;
import TestCases.GlobalOnLine.TC_15_WEB_GO;
import TestCases.GlobalOnLine.TC_16_WEB_GO;
import TestCases.GlobalOnLine.TC_17_WEB_GO;
import TestCases.GlobalOnLine.TC_18_WEB_GO;
import TestCases.GlobalOnLine.TC_19_WEB_GO;
import TestCases.GlobalOnLine.TC_20_WEB_GO;
import TestCases.GlobalOnLine.TC_21_WEB_GO;
import TestCases.OPI.TC_01_OPI;
import TestCases.OPI.TC_02_OPI;
import TestCases.OPI.TC_03_OPI;
import TestCases.OPI.TC_04_OPI;
import TestCases.OPI.TC_05_OPI;
import TestCases.OPI.TC_06_OPI;
import TestCases.OPI.TC_07_OPI;
import TestCases.OPI.TC_08_OPI;
import TestCases.OPI.TC_09_OPI;
import TestCases.OPI.TC_10_OPI;
import TestCases.OPI.TC_11_OPI;
import TestCases.OPI.TC_12_OPI;
import TestCases.OPI.TC_13_OPI;
import TestCases.OPI.TC_14_OPI;
import TestCases.OPI.TC_15_OPI;
import TestCases.OPI.TC_16_OPI;
import TestCases.OPI.TC_17_OPI;
import TestCases.OPI.TC_18_OPI;
import TestCases.OPI.TC_19_OPI;
import TestCases.OPI.TC_20_OPI;
import TestCases.OPI.TC_21_OPI;
import TestCases.OPI.TC_22_OPI;
import TestCases.OPI.TC_23_OPI;
import TestCases.OPI.TC_24_OPI;
import TestCases.OPI.TC_25_OPI;
import TestCases.OPI.TC_26_OPI;
import TestCases.OPI.TC_27_OPI;
import TestCases.OPI.TC_28_OPI;
import TestCases.OPI.TC_29_OPI;
import TestCases.OPI.TC_30_OPI;
import SmokeTest.GO.TC_01_SMOKE_GO;
import SmokeTest.GO.TC_02_SMOKE_GO;
import SmokeTest.GO.TC_03_SMOKE_GO;
import SmokeTest.GO.TC_04_SMOKE_GO;
import SmokeTest.ApiPrepaga.TC_01_SMOKE_PP;
import SmokeTest.ApiPrepaga.TC_02_SMOKE_PP;
import SmokeTest.ApiPrepaga.TC_03_SMOKE_PP;
import SmokeTest.ApiPrepaga.TC_04_SMOKE_PP;
import SmokeTest.ApiPrepaga.TC_05_SMOKE_PP;
import SmokeTest.ApiPrepaga.TC_06_SMOKE_PP;
import SmokeTest.ApiPrepaga.TC_07_SMOKE_PP; 
import SmokeTest.ApiPrepaga.TC_08_SMOKE_PP; 
import SmokeTest.ApiPrepaga.TC_09_SMOKE_PP; 
import SmokeTest.ApiPrepaga.TC_10_SMOKE_PP;
import SmokeTest.AI.TC_01_SMOKE_AI;
import SmokeTest.AI.TC_02_SMOKE_AI;
import SmokeTest.AI.TC_03_SMOKE_AI;
import SmokeTest.AI.TC_04_SMOKE_AI;
import SmokeTest.AI.TC_05_SMOKE_AI;
import SmokeTest.AI.TC_06_SMOKE_AI;
import SmokeTest.AI.TC_07_SMOKE_AI;
import SmokeTest.AI.TC_08_SMOKE_AI;
import SmokeTest.AI.TC_09_SMOKE_AI;
import TestCases.OPI.TC_PruebaOPI;



public class GlobalProcessing_Test 
{	
	//VARIABLES GLOBALES PARA UTILIZAR URL,CONEXIONES, ETC
	static 

	//Init
	DriverManager DM;
	static Datasources data;
	static Reports report;
	static Repo_Variables repoVar;
	static String path;
	static String path_2;
	static String configEntidad;
	static String cuentas_generales;
	
	
	
	@BeforeSuite
	static void initAll() throws IOException {
		//DriverManager
		DM = new DriverManager();
		//DataSource
		data = new Datasources();
		//Reports
		report = new Reports();
		//Variables Repository
		repoVar = new Repo_Variables();
		//PRUEBA VARIABLES GLOBALES PARA UTILIZAR URL,CONEXIONES, ETC
		path = "./Datasources/config_entidad.json";
		configEntidad = new String(Files.readAllBytes(Paths.get(path)));
		//PRUEBA DE CUENTAS GENERALES
		path_2 = "./Datasources/cuentas_generales.json";
		cuentas_generales = new String(Files.readAllBytes(Paths.get(path_2)));
	}
	
	@BeforeClass
	void init() {
		
	}
	
	//TCs WEB Global Online
	@Test
	//@Tag("ALL")
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_01_GO")
	void TC_01_GO() {
		//DEFINITIONS
		TC_01_WEB_GO TC01_GO = new TC_01_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.ExcelType);
		data.setDataSourceFile("testcase1.xlsx");
		String name = "TC_01_WEB_GO - Login";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		//for(int x=0;x<data.getTotalIterations();x++) {
		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC01_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_02_GO")
	void TC_02_GO() {
		//DEFINITIONS
		TC_02_WEB_GO TC02_GO = new TC_02_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");
		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.JsonType);
		data.setDataSourceFile("URL_GO.json");	
		String name = "TC_02_WEB_GO - Alta_Modelo_Limites";
		//System.out.println(URL_GO);
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;
		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC02_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_03_GO")
	void TC_03_GO() {
		//DEFINITIONS
		TC_03_WEB_GO TC03_GO = new TC_03_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_03_WEB_GO - Alta_Modelo_Restriccion";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC03_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_04_GO")
	void TC_04_GO() {
		//DEFINITIONS
		TC_04_WEB_GO TC04_GO = new TC_04_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_04_WEB_GO - Alta_Grupo_Afinidad";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC04_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_05_GO")
	void TC_05_GO(){
		//DEFINITIONS
		TC_05_WEB_GO TC05_GO = new TC_05_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_05_WEB_GO - Alta_Productos";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC05_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_06_GO")
	void TC_06_GO() {
		//DEFINITIONS
		TC_06_WEB_GO TC06_GO = new TC_06_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_06_WEB_GO - Editar_Estado_Producto";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC06_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_07_GO")
	void TC_07_GO() {
		//DEFINITIONS
		TC_07_WEB_GO TC07_GO = new TC_07_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_07_WEB_GO - Alta_Cta_Virtual";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC07_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_08_GO")
	void TC_08_GO() {
		//DEFINITIONS
		TC_08_WEB_GO TC08_GO = new TC_08_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_08_WEB_GO - Editar_Cta_Virtual";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC08_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_09_GO")
	void TC_09_GO() {
		//DEFINITIONS
		TC_09_WEB_GO TC09_GO = new TC_09_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_09_WEB_GO - Editar_Cta_de_Activa_a_Baja";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC09_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_10_GO")
	void TC_10_GO() {
		//DEFINITIONS
		TC_10_WEB_GO TC10_GO = new TC_10_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");		

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_10_WEB_GO - Editar_Cta_de_Baja_a_Activo";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC10_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_11_GO")
	void TC_11_GO() {
		//DEFINITIONS
		TC_11_WEB_GO TC11_GO = new TC_11_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_11_WEB_GO - Alta_Cta_Fisica";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC11_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales); 
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_12_GO")
	void TC_12_GO() {
		//DEFINITIONS
		TC_12_WEB_GO TC12_GO = new TC_12_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_12_WEB_GO - Editar_Estado_Tarjeta_Habilitada_Baja";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC12_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_13_GO")
	void TC_13_GO() {
		//DEFINITIONS
		TC_13_WEB_GO TC13_GO = new TC_13_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_13_WEB_GO - Editar_Estado_Tarjeta_Baja_Habilitada";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC13_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_14_GO")
	void TC_14_GO() {
		//DEFINITIONS
		TC_14_WEB_GO TC14_GO = new TC_14_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_14_WEB_GO - Consulta_de_Disponible";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC14_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_15_GO")
	void TC_15_GO() {
		//DEFINITIONS
		TC_15_WEB_GO TC15_GO = new TC_15_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_15_WEB_GO - Habilitacion_Tarjeta_Fisica";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC15_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_16_GO")
	void TC_16_GO() {
		//DEFINITIONS
		TC_16_WEB_GO TC16_GO = new TC_16_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_16_WEB_GO - Editar_Datos_cta_Titular";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC16_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_17_GO")
	void TC_17_GO() {
		//DEFINITIONS
		TC_17_WEB_GO TC17_GO = new TC_17_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_17_WEB_GO - Crear_Carga_Local";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC17_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_18_GO")
	void TC_18_GO() {
		//DEFINITIONS
		TC_18_WEB_GO TC18_GO = new TC_18_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_18_WEB_GO - Reimpresion_Tarjeta_Virtual_Virtual";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC18_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_19_GO")
	void TC_19_GO() {
		//DEFINITIONS
		TC_19_WEB_GO TC19_GO = new TC_19_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_19_WEB_GO - Reimpresion_Tarjeta_Fisica_Fisica";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC19_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_20_GO")
	void TC_20_GO() {
		//DEFINITIONS
		TC_20_WEB_GO TC20_GO = new TC_20_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_20_WEB_GO - Reimpresion_Tarjeta_Virtual_Fisica";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC20_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("GO")
	//@Tag("WEB")
	//@Tag("TC_21_GO")
	void TC_21_GO() {
		//DEFINITIONS
		TC_21_WEB_GO TC21_GO = new TC_21_WEB_GO();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase2.csv");
		String name = "TC_21_WEB_GO - Alta_Cotizacion";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC21_GO.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
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
	

	// GLOBAL BATCH
	@Test
	//@Tag("ALL")
	//@Tag("GBATCH")
	//@Tag("WEB")
	//@Tag("TC_01_GBATCH")
	void TC_01_GBATCH() {
		//DEFINITIONS
		TC_01_WEB_BATCH TC01_BA = new TC_01_WEB_BATCH();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.CsvType);
		data.setDataSourceFile("testcase1.csv");
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


	//TCs APIs PrePagas
	@Test
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_01_API_PP")
	void TC_01_API_PP() {
		//DEFINITIONS
		TC_01_API_PP TC01_PP = new TC_01_API_PP();

		//SET INDIVIDUAL DATASOURCE

		// Nombre Real del TC
		String name = "TC_01_API_PP - Alta_Cta_Prepaga_Tarjeta_Virtual";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		// Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		// SET THE EXECUTION PLAN
		status = TC01_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		// Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		// Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false) {
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		// Se avisa x jUnit el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}

	@Test
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_02_API_PP")
	void TC_02_API_PP() {
		//DEFINITIONS
		TC_02_API_PP TC02_PP = new TC_02_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_02_API_PP - Alta_Cta_Prepaga_Tarjeta_Fisica";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC02_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_03_API_PP")
	void TC_03_API_PP() {
		//DEFINITIONS
		TC_03_API_PP TC03_PP = new TC_03_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_03_API_PP - Habilitacion_Tarjeta_Fisica";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC03_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_04_API_PP")
	void TC_04_API_PP() {
		//DEFINITIONS
		TC_04_API_PP TC04_PP = new TC_04_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_04_API_PP - Crear_Carga_Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC04_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_05_API_PP")
	void TC_05_API_PP() {
		//DEFINITIONS
		TC_05_API_PP TC05_PP = new TC_05_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_05_API_PP - Consulta_Disponible_Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC05_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_06_API_PP")
	void TC_06_API_PP() {
		//DEFINITIONS
		TC_06_API_PP TC06_PP = new TC_06_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_06_API_PP - Consulta_Saldo_Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC06_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);
		//report.addTestCaseToGeneralReport(status, name, "");
		//report.saveTestCaseReport(name);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_07_API_PP")
	void TC_07_API_PP() {
		//DEFINITIONS
		TC_07_API_PP TC07_PP = new TC_07_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_07_API_PP - Cambio_Estado_Cta_Baja";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC07_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);
		//report.addTestCaseToGeneralReport(status, name, "");
		//report.saveTestCaseReport(name);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_08_API_PP")
	void TC_08_API_PP() {
		//DEFINITIONS
		TC_08_API_PP TC08_PP = new TC_08_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_08_API_PP - Cambio_Estado_Cta_Activa";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC08_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_09_API_PP")
	void TC_09_API_PP() {
		//DEFINITIONS
		TC_09_API_PP TC09_PP = new TC_09_API_PP(); 

		//SET INDIVIDUAL DATASOURCES

		//Nombre Real del TC
		String name = "TC_09_API_PP - Cambio_Estado_Tarjeta_DadaDeBaja";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC09_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_10_API_PP")
	void TC_10_API_PP() {
		//DEFINITIONS
		TC_10_API_PP TC10_PP = new TC_10_API_PP(); 

		//SET INDIVIDUAL DATASOURCES

		//Nombre Real del TC
		String name = "TC_10_API_PP - Cambio_Estado_Tarjeta_NormalHabilitada";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC10_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_11_API_PP")
	void TC_11_API_PP() {
		//DEFINITIONS
		TC_11_API_PP TC11_PP = new TC_11_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_11_API_PP - Ingreso_Ajuste_Debito";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC11_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_12_API_PP")
	void TC_12_API_PP() {
		//DEFINITIONS
		TC_12_API_PP TC12_PP = new TC_12_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_12_API_PP - Ingreso_Ajuste_Credito";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC12_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_13_API_PP")
	void TC_13_API_PP() {
		//DEFINITIONS
		TC_13_API_PP TC13_PP = new TC_13_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_13_API_PP - Reimpresion_Tarjeta_Fisica_Fisica";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC13_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_14_API_PP")
	void TC_14_API_PP() {
		//DEFINITIONS
		TC_14_API_PP TC14_PP = new TC_14_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_14_API_PP - Reimpresion_Tarjeta_Virtual_Fisica";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC14_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_15_API_PP")
	void TC_15_API_PP() {
		//DEFINITIONS
		TC_15_API_PP TC15_PP = new TC_15_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_15_API_PP - Reimpresion_Tarjeta_Virtual_Virtual";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC15_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_16_API_PP")
	void TC_16_API_PP() {
		//DEFINITIONS
		TC_16_API_PP TC16_PP = new TC_16_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_16_API_PP - Editar_Datos_cuenta";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC16_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_17_API_PP")
	void TC_17_API_PP() {
		//DEFINITIONS
		TC_17_API_PP TC17_PP = new TC_17_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_17_API_PP - Consulta_De_Tarjeta";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC17_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_18_API_PP")
	void TC_18_API_PP() {
		//DEFINITIONS
		TC_18_API_PP TC18_PP = new TC_18_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_18_API_PP - Consulta_De_Tarjeta_Virtual";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC18_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_19_API_PP")
	void TC_19_API_PP() {
		//DEFINITIONS
		TC_19_API_PP TC19_PP = new TC_19_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_19_API_PP - Consulta_De_PIN";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC19_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_20_API_PP")
	void TC_20_API_PP() {
		//DEFINITIONS
		TC_20_API_PP TC20_PP = new TC_20_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_20_API_PP - Consulta_De_PIN_Encriptado";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC20_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_21_API_PP")
	void TC_21_API_PP() {
		//DEFINITIONS
		TC_21_API_PP TC21_PP = new TC_21_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_21_API_PP - Cambio_de_PIN_Con_Verificacion";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC21_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_22_API_PP")
	void TC_22_API_PP() {
		//DEFINITIONS
		TC_22_API_PP TC22_PP = new TC_22_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_22_API_PP - Cambio_de_PIN_Sin_Verificacion";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC22_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_23_API_PP")
	void TC_23_API_PP() {
		//DEFINITIONS
		TC_23_API_PP TC23_PP = new TC_23_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_23_API_PP - Blanqueo_Intentos_Fallidos_PIN ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC23_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_PP")
	//@Tag("TC_24_API_PP")
	void TC_24_API_PP() {
		//DEFINITIONS
		TC_24_API_PP TC24_PP = new TC_24_API_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "TC_24_API_PP - Modificacion_Domicilio_Legal_Correspondencia  ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataStr(name);

		//SET THE EXECUTION PLAN
		status = TC24_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("TC_PruebaGet_API")
	//@Tag("APIs")
	void TC_PruebaGet_API() {
		//DEFINITIONS
		TC_PruebaGet_API TC02 = new TC_PruebaGet_API();
		Repo_Variables repoVar = new Repo_Variables();
		msgWorker msgWorker = new msgWorker();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "ALTA CTA FISICA";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC02.Test(report, DM, x, name + "_Iteracion_" + x, configEntidad);
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


	//TCs OPI Autorizador
	@Test
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_01_OPI")
	void TC_01_OPI() {
		//DEFINITIONS
		TC_01_OPI TC01_OPI = new TC_01_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_01_OPI - Compra_Ecommer_DisponibleInsuficiente";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC01_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_02_OPI")
	void TC_02_OPI() {
		//DEFINITIONS
		TC_02_OPI TC02_OPI = new TC_02_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_02_OPI - Compra_Ecommer5USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC02_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_03_OPI")
	void TC_03_OPI() {
		//DEFINITIONS
		TC_03_OPI TC03_OPI = new TC_03_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_03_OPI - Reverso_Total_Compra_Ecommer5USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC03_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_04_OPI")
	void TC_04_OPI() {
		//DEFINITIONS
		TC_04_OPI TC04_OPI = new TC_04_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_04_OPI - Compra_Ecommer25USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC04_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_05_OPI")
	void TC_05_OPI() {
		//DEFINITIONS
		TC_05_OPI TC05_OPI = new TC_05_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_05_OPI - Devolucion_Total_Ecommer25USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC05_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_06_OPI")
	void TC_06_OPI() {
		//DEFINITIONS
		TC_06_OPI TC06_OPI = new TC_06_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_06_OPI - Reversto_Total_DevolucionTotal_Ecommer25USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC06_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_07_OPI")
	void TC_07_OPI() {
		//DEFINITIONS
		TC_07_OPI TC07_OPI = new TC_07_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_07_OPI - Compra_Ecommer7,35USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC07_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_08_OPI")
	void TC_08_OPI() {
		//DEFINITIONS
		TC_08_OPI TC08_OPI = new TC_08_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_08_OPI -Devolucion_Parcial_Ecommer5,50USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC08_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_09_OPI")
	void TC_09_OPI() {
		//DEFINITIONS
		TC_09_OPI TC09_OPI = new TC_09_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_09_OPI - Devolucion_Parcial_Ecommer5,50USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC09_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_10_OPI")
	void TC_10_OPI() {
		//DEFINITIONS
		TC_10_OPI TC10_OPI = new TC_10_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_10_OPI -Compra_Ecommer9,50USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC10_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_11_OPI")
	void TC_11_OPI() {
		//DEFINITIONS
		TC_11_OPI TC11_OPI = new TC_11_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_11_OPI - Reverso_Total_CompraEcommer9,50USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC11_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_12_OPI")
	void TC_12_OPI() {
		//DEFINITIONS
		TC_12_OPI TC12_OPI = new TC_12_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_12_OPI - Compra_Ecommer10USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC12_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_13_OPI")
	void TC_13_OPI() {
		//DEFINITIONS
		TC_13_OPI TC13_OPI = new TC_13_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_13_OPI - Reverso_Parcial_5USD_CompraEcommer10USD";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC13_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_14_OPI")
	void TC_14_OPI() {
		//DEFINITIONS
		TC_14_OPI TC14_OPI = new TC_14_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_14_OPI -TC_14_OPI - Consulta de Saldo - Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC14_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_15_OPI")
	void TC_15_OPI() {
		//DEFINITIONS
		TC_15_OPI TC15_OPI = new TC_15_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_15_OPI - Compra_TarjetaPendienteEmbozado";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC15_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_16_OPI")
	void TC_16_OPI() {
		//DEFINITIONS
		TC_16_OPI TC16_OPI = new TC_16_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_16_OPI -Compra_TarjetaEmbozada";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC16_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_17_OPI")
	void TC_17_OPI() {
		//DEFINITIONS
		TC_17_OPI TC17_OPI = new TC_17_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_17_OPI - Compra_TarjetaDadaDeBaja";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC17_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_18_OPI")
	void TC_18_OPI() {
		//DEFINITIONS
		TC_18_OPI TC18_OPI = new TC_18_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_18_OPI - Compra_TarjetaVencida";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC18_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_19_OPI")
	void TC_19_OPI() {
		//DEFINITIONS
		TC_19_OPI TC19_OPI = new TC_19_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_19_OPI - BIN_Inexistente";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC19_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_20_OPI")
	void TC_20_OPI() {
		//DEFINITIONS
		TC_20_OPI TC20_OPI = new TC_20_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_20_OPI - VencimientoInformadoDifiereDelRegistrado";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC20_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_21_OPI")
	void TC_21_OPI() {
		//DEFINITIONS
		TC_21_OPI TC21_OPI = new TC_21_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_21_OPI - Cuenta_NO_activa:Baja";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC21_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_22_OPI")
	void TC_22_OPI() {
		//DEFINITIONS
		TC_22_OPI TC22_OPI = new TC_22_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_22_OPI - Validar Impuesto Por MCC8042";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC22_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_23_OPI")
	void TC_23_OPI() {
		//DEFINITIONS
		TC_23_OPI TC23_OPI = new TC_23_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_23_OPI - Compra Exterior Comercio no Servicio Digital";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC23_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_24_OPI")
	void TC_24_OPI() {
		//DEFINITIONS
		TC_24_OPI TC_24_OPI = new TC_24_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_24_OPI - Compra Servicio Digital Domi Cordoba y Sucur Neuquen";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC_24_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_25_OPI")
	void TC_25_OPI() {

		//DEFINITIONS
		TC_25_OPI TC_25_OPI = new TC_25_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_25_OPI - Compra Servicio Digital Domi CABA y Sucur CABA";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC_25_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_26_OPI")
	void TC_26_OPI() {
		//DEFINITIONS
		TC_26_OPI TC_26_OPI = new TC_26_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_26_OPI - Compra Servicio Digital Responsable Inscripto";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC_26_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_27_OPI")
	void TC_27_OPI() {
		//DEFINITIONS
		TC_27_OPI TC_27_OPI = new TC_27_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_26_OPI - Tarjeta con producto no opera internacional";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC_27_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_28_OPI")
	void TC_28_OPI() {
		//DEFINITIONS
		TC_28_OPI TC_28_OPI = new TC_28_OPI();	

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_28_OPI - Tarjeta con producto no opera adelantos";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC_28_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_29_OPI")
	void TC_29_OPI() {

		//DEFINITIONS
		TC_29_OPI TC_29_OPI = new TC_29_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_29_OPI - Adelanto en el exterior";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC_29_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("OPIs")
	//@Tag("TC_30_OPI")
	void TC_30_OPI() {

		//DEFINITIONS
		TC_30_OPI TC_30_OPI = new TC_30_OPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_30_OPI - Adelanto Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC_30_OPI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("OPI_Prueba")
	//@Tag("OPI")
	void TC_Prueba_OPI() {
		//DEFINITIONS
		TC_PruebaOPI PruebaOpi = new TC_PruebaOPI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC: Transferencia entre cuentas de distintos bancos
		String name = "PRUEBA INTEGRACION";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";


		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);


		//SET THE EXECUTION PLAN
		status = PruebaOpi.Test(report, DM, 0, name, configEntidad);

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


	//TCs APIs Autorizador
	@Test
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_01_API_AI")
	void TC_01_API_AI() {
		//DEFINITIONS
		TC_01_API_AI TC01_AI = new TC_01_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_01_API_AI - Compra Local - Manual - Disponible insuficiente";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC01_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_02_API_AI")
	void TC_02_API_AI() {
		//DEFINITIONS
		TC_02_API_AI TC02_AI = new TC_02_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_02_API_AI - Compra Local - Manual";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC02_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUn1it el resultado de la prueba
		Assert.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}

	@Test
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_03_API_AI")
	void TC_03_API_AI() {
		//DEFINITIONS
		TC_03_API_AI TC03_AI = new TC_03_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_03_API_AI - Compra Local - Banda";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC03_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_04_API_AI")
	void TC_04_API_AI() {
		//DEFINITIONS
		TC_04_API_AI TC04_AI = new TC_04_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_04_API_AI - Reverso Total - Compra Local - Banda";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC04_AI.Test(report, DM, 0, name, configEntidad,cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_05_API_AI")
	void TC_05_API_AI() {
		//DEFINITIONS
		TC_05_API_AI TC05_AI = new TC_05_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_05_API_AI - Compra Local - Chip";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC05_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_06_API_AI")
	void TC_06_API_AI() {
		//DEFINITIONS
		TC_06_API_AI TC06_AI = new TC_06_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_06_API_AI - Devolucion Local - Chip";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC06_AI.Test(report, DM, 0, name, configEntidad,cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_07_API_AI")
	void TC_07_API_AI() {
		//DEFINITIONS
		TC_07_API_AI TC07_AI = new TC_07_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_07_API_AI - Reverso Total - Devolucion Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC07_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_08_API_AI")
	void TC_08_API_AI() {
		//DEFINITIONS
		TC_08_API_AI TC08_AI = new TC_08_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_08_API_AI - Compra Local - Contacless ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC08_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_09_API_AI")
	void TC_09_API_AI() {
		//DEFINITIONS
		TC_09_API_AI TC09_AI = new TC_09_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_09_API_AI - Devolucion Parcial Local - Contacless ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC09_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_10_API_AI")
	void TC_10_API_AI() {
		//DEFINITIONS
		TC_10_API_AI TC10_AI = new TC_10_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_10_API_AI - Reverso Total - Devolucion Parcial Local ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC10_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_11_API_AI")
	void TC_11_API_AI() {
		//DEFINITIONS
		TC_11_API_AI TC11_AI = new TC_11_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_11_API_AI - Compra Local - Ecommer ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC11_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_12_API_AI")
	void TC_12_API_AI() {
		//DEFINITIONS
		TC_12_API_AI TC12_AI = new TC_12_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_12_API_AI - Reverso Total - Compra Local - Ecommer";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC12_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_13_API_AI")
	void TC_13_API_AI() {
		//DEFINITIONS
		TC_13_API_AI TC13_AI = new TC_13_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_13_API_AI - Adelanto Local - Manual ";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC13_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_14_API_AI")
	void TC_14_API_AI() {
		//DEFINITIONS
		TC_14_API_AI TC14_AI = new TC_14_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_14_API_AI - Consulta de Saldo -  Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC14_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_15_API_AI")
	void TC_15_API_AI() {
		//DEFINITIONS
		TC_15_API_AI TC15_AI = new TC_15_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_15_API_AI - Compra Local - Forzada";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC15_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_16_API_AI")
	void TC_16_API_AI() {
		//DEFINITIONS
		TC_16_API_AI TC16_AI = new TC_16_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_16_API_AI - Compra_con_cashback";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC16_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_17_API_AI")
	void TC_17_API_AI() {
		//DEFINITIONS
		TC_17_API_AI TC17_AI = new TC_17_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_17_API_AI - Devolucion Total- Compra con cash back";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC17_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("ALL")
	//@Tag("APIs_AI")
	//@Tag("TC_18_API_AI")
	void TC_18_API_AI() {
		//DEFINITIONS
		TC_18_API_AI TC18_AI = new TC_18_API_AI();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_18_API_AI - Reverso total de cashback sobre una devolucion";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC18_AI.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	
	
	
	//SMOKE TEST GO
	@Test
	//@Tag("SMOKE")
	//@Tag("GO_SMOKE")
	//@Tag("WEB_SMOKE")
	//@Tag("TC_01_GO_SMOKE")
	void TC_01_GO_SMOKE() {
		//DEFINITIONS
		TC_01_SMOKE_GO TC01_SMOKE = new TC_01_SMOKE_GO ();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.ExcelType);
		data.setDataSourceFile("testcase1.xlsx");
		String name = "TC_01_SMOKE_GO - Login";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		//for(int x=0;x<data.getTotalIterations();x++) {
		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC01_SMOKE.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad);
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
	//@Tag("SMOKE")
	//@Tag("GO_SMOKE")
	//@Tag("WEB_SMOKE")
	//@Tag("TC_02_GO_SMOKE")
	void TC_02_GO_SMOKE() {
		//DEFINITIONS
		TC_02_SMOKE_GO TC02_SMOKE = new TC_02_SMOKE_GO ();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.ExcelType);
		data.setDataSourceFile("testcase1.xlsx");
		String name = "TC_02_SMOKE_GO - Alta de productos";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		//for(int x=0;x<data.getTotalIterations();x++) {
		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC02_SMOKE.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("SMOKE")
	//@Tag("GO_SMOKE")
	//@Tag("WEB_SMOKE")
	//@Tag("TC_03_GO_SMOKE")
	void TC_03_GO_SMOKE() {
		//DEFINITIONS
		TC_03_SMOKE_GO TC03_SMOKE = new TC_03_SMOKE_GO ();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.ExcelType);
		data.setDataSourceFile("testcase1.xlsx");
		String name = "TC_03_SMOKE_GO - Alta de cuenta";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		//for(int x=0;x<data.getTotalIterations();x++) {
		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC03_SMOKE.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	//@Tag("SMOKE")
	//@Tag("GO_SMOKE")
	//@Tag("WEB_SMOKE")
	//@Tag("TC_04_GO_SMOKE")
	void TC_04_GO_SMOKE() {
		//DEFINITIONS
		TC_04_SMOKE_GO TC04_SMOKE = new TC_04_SMOKE_GO ();
		msgWorker msgWorker = new msgWorker();

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("WEB");

		//SET INDIVIDUAL DATASOURCE
		data.setDataSourceType(data.ExcelType);
		data.setDataSourceFile("testcase1.xlsx");
		String name = "TC_04_SMOKE_GO - Crear carga local";
		boolean status = false;
		String msg = "True;Todas las iteraciones resultaron OK";
		int index = 0;

		//for(int x=0;x<data.getTotalIterations();x++) {
		for(int x=0;x<1;x++) {
			//SET THE EXECUTION PLAN
			status = TC04_SMOKE.Test(data, report, DM, x, name + "_Iteracion_" + x, configEntidad, cuentas_generales);
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
	
	
	//SMOKE API PP
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_01_PP_SMOKE")
	void TC_01_SMOKE_PP () {
		//DEFINITIONS
		TC_01_SMOKE_PP TC01_SMOKE_PP = new TC_01_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_01_SMOKE_PP - Consulta de Disponible Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC01_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_02_PP_SMOKE")
	void TC_02_SMOKE_PP () {
		//DEFINITIONS
		TC_02_SMOKE_PP TC02_SMOKE_PP = new TC_02_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_02_SMOKE_PP - Consulta de Tarjeta";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC02_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_03_PP_SMOKE")
	void TC_03_SMOKE_PP () {
		//DEFINITIONS
		TC_03_SMOKE_PP TC03_SMOKE_PP = new TC_03_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_03_SMOKE_PP - Crear Carga Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC03_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_04_PP_SMOKE")	
	void TC_04_SMOKE_PP () {
		//DEFINITIONS
		TC_04_SMOKE_PP TC04_SMOKE_PP = new TC_04_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_04_SMOKE_PP - Consulta de Saldo Local";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC04_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_05_PP_SMOKE")
	void TC_05_SMOKE_PP () {
		//DEFINITIONS
		TC_05_SMOKE_PP TC05_SMOKE_PP = new TC_05_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_05_SMOKE_PP - Cambio de estado Cuenta - Baja";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC05_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_06_PP_SMOKE")
	void TC_06_SMOKE_PP () {
		//DEFINITIONS
		TC_06_SMOKE_PP TC06_SMOKE_PP = new TC_06_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_06_SMOKE_PP - Cambio de estado Tarjeta - DadaDeBaja";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC06_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_07_PP_SMOKE")
	void TC_07_SMOKE_PP () {
		//DEFINITIONS
		TC_07_SMOKE_PP TC07_SMOKE_PP = new TC_07_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_07_SMOKE_PP - Ingreso de Ajuste Credito";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC07_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_08_PP_SMOKE")
	void TC_08_SMOKE_PP () {
		//DEFINITIONS
		TC_08_SMOKE_PP TC08_SMOKE_PP = new TC_08_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_08_SMOKE_PP - Consulta de PIN Encriptado";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC08_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_09_PP_SMOKE")
	void TC_09_SMOKE_PP () {
		//DEFINITIONS
		TC_09_SMOKE_PP TC09_SMOKE_PP = new TC_09_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_09_SMOKE_PP - Consulta de PIN";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC09_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	//@Tag("APIs_PP_SMOKE")
	//@Tag("TC_10_PP_SMOKE")
	void TC_10_SMOKE_PP () {
		//DEFINITIONS
		TC_10_SMOKE_PP TC10_SMOKE_PP = new TC_10_SMOKE_PP();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		String name = "TC_10_SMOKE_PP - Cambio de PIN con verificación";
		boolean status = false;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC10_SMOKE_PP.Test(report, DM, 0, name, configEntidad, cuentas_generales);

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
	
	
	
	//SMOKE AUTORIZADOR PP
	@Test
	//@Tag("SMOKE")
	//@Tag("APIs_AI_SMOKE")
	//@Tag("TC_01_AI_SMOKE")		
	void TC_01_SMOKE_AI() {
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
	void TC_02_SMOKE_AI() {
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
	void TC_03_SMOKE_AI() {
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
	void TC_04_SMOKE_AI() {
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
	void TC_05_SMOKE_AI() {
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
	void TC_06_SMOKE_AI() {
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
	void TC_07_SMOKE_AI() {
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
	void TC_08_SMOKE_AI() {
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
	void TC_09_SMOKE_AI() {
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
	
	
	
	
	
	
	@AfterMethod
	void tearDown() {
		if (repoVar.getTipoTc().equals("API")) {
			report.addTestCaseToGeneralReport(repoVar.getResult(), repoVar.getDataStr(), "");
			report.saveTestCaseReport(repoVar.getDataStr());
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

