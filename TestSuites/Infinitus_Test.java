package TestSuites;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Test;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_Variables;
import TestCases.Infinitus.ApiOperaciones.*;
import Tools.msgWorker;



public class Infinitus_Test 
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
	static String configEntidad2;
	static String cuentas_generales2;
	static String config_entidad2_infinitus;
	
	
	
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
		path = "./Datasources/config_entidad2_infinitus.json";
		configEntidad2 = new String(Files.readAllBytes(Paths.get(path)));
		//PRUEBA DE CUENTAS GENERALES
		//path_2 = "./Datasources/cuentas_generales2.json";
		//cuentas_generales2 = new String(Files.readAllBytes(Paths.get(path_2)));
	}
	
	@BeforeClass
	void init() {
		
	}
	
	//SE REALIZA LA PRIMERA PRUEBA DE TEST CASE DESDE LA CLASE OPERACIONES, METODO POST, PARA API INFINITUS
	
	@Test
	void TC_01_OPERACIONES() {
		//DEFINITIONS
		API_OPERACIONES TC01_API_OPERACIONES = new API_OPERACIONES();

		//SET INDIVIDUAL DATASOURCE

		//Nombre Real del TC
		boolean status = false;
		String nroTC = "01";
		String name = "TC_" + nroTC + "_API-MASTERCARD-PREPAGA/CUENTA-ALTA DE CUENTA";
		String TCFilesPath = "./API_Requests/Infinitus/Apis/TC_" + nroTC;
		String msg = "True;Resultado de la ejecucion OK. TC: " + name;

		//Inicializacion de las Variables del Repositorio
		repoVar.setTipoTc("API");
		repoVar.setResult(status);
		repoVar.setDataMsg(name);

		//SET THE EXECUTION PLAN
		status = TC01_API_OPERACIONES.POST(report, DM, 0, name, configEntidad2, TCFilesPath);

		//Configuracion de variables para el armado del reporte
		repoVar.setResult(status);

		//Verificamos si la ejecucion falla y guardamos el status Gral.
		if (status == false)
		{
			msg = "Fail;Fallo la ejecucion. TC: " + name;
		}

		//Se avisa x jUnit el resultado de la prueba
		AssertJUnit.assertEquals("Resultado: " + msg.split(";")[1],"True", msg.split(";")[0]);
	}
	
	
	/*******************************************************************************************************/
	
	
	@AfterMethod
	//@AfterEach
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

