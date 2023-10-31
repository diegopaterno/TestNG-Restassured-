package SmokeTest.AI;

import org.openqa.selenium.WebDriver;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Pasos.Generales.AutorizadorInterno;
import Pasos.Generales.PrePostCondi;
import Repositories.Repo_Variables;
import Tools.apiWorker;
import Tools.dbWorker;
import io.restassured.path.json.JsonPath;

public class TC_07_SMOKE_AI {
	WebDriver driver;

	// PRUEBA QUE REQUIERE LA EJECUCION DE DEL TC09 (Devolucion Parcial Local - Contacless )
	// Reverso Total - Devolucion Parcial Local 
	public boolean Test(Reports report, DriverManager DM, int iteration, String name, String configEntidad, String cuentas_generales) {
		//Validation var
		boolean result = true;
		try {
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

			System.out.println("Configuring TC_10_API_AI\r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			String path = "./API_Requests/Autorizador/TC_10_ReversoTotalDevolucionParcialLocal_Contacless205.67.txt";		
			String token = "";
			String bodyData = "";
			String respBody = "";
			String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
			String NRO_TARJETA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.nro_tarjeta");
			String VENCIMIENTO = "2502";
			String de39 = ""; 
			int rtaRollback1 = 0;
			
			// Resultados esperados en la prueba
			String[] resulEsperado = new String[4];
			resulEsperado[0] = "970.02";//DISPONIBLE
			resulEsperado[1] = "929.98";//IMPORTE_PEND_COMPRAS
			resulEsperado[2] = "2";//ID_ESTADO de AUTORIZACION
			resulEsperado[3] = "00";//DE39 EN RESPONSE

	
			//SET INSTANCES
			apiWorker apiResp = new apiWorker();
			dbWorker oraResp = new dbWorker();
			Repo_Variables repoVar = new Repo_Variables();
			PrePostCondi ppCondi = new PrePostCondi();
			AutorizadorInterno AI = new AutorizadorInterno();

			//SET ENVIRONMENT
			AI.setBaseUrl(report, repoVar, configEntidad);

			System.out.println("##[command] : <------ Initializating Test: " + name + " ------>\r\n");
			report.AddLine("<------ Initializating Test: " + name + " ------>");


			//GET TOKEN
			token = AI.getToken(report, apiResp, configEntidad);

			//GET POST BODY FROM FILE
			bodyData = AI.getBodyData(report, path);
			
			//Se actualiza el arhivo json y la variable bodyData con el nro de tarjeta del TC
			bodyData = AI.setJsonBody(report, path, bodyData, NRO_TARJETA, VENCIMIENTO);

			//POST - Reverso Total - Devolucion Parcial Local
			if (!bodyData.isEmpty() && !token.isEmpty())
			{

				respBody = AI.post(report, repoVar, apiResp, bodyData, token);


			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe");
				result = false;
			}	

			//ROLLBACK
			report.AddLine("--- Ejecutando PostCondicion ---");
			System.out.println("--- Ejecutando PostCondicion ---\r\n");

			rtaRollback1 = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
					+ "SET SALDO = 1900, DISPONIBLE = 970.02, DISPONIBLE_ADELANTO = 1900,\r\n"
					+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = 929.98, \r\n"
					+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = 0,\r\n"
					+ "IMPORTE_PAGOS = 1900\r\n"
					+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
			ppCondi.validaRollBackUpdate(report, rtaRollback1);

			System.out.println("##[command] : <------ Finished Test: " + name + " ------>\r\n");
			report.AddLine("<------ Finished Test: " + name + " ------>");

			//Separador
			System.out.println("##################################################################################################################################################################################################################"
					+ "##################################################################################################################################################################################################################\r\n");

		} catch (Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getStackTrace()[0].toString());
			report.AddLineAssertionError(e.getMessage());
			result = false;
		}

		return result;
	}
}

