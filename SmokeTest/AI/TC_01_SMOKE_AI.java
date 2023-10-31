package SmokeTest.AI;

import org.openqa.selenium.WebDriver;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Pasos.Generales.PrePostCondi;
import Repositories.Repo_Variables;
import Tools.apiWorker;
import Tools.dbWorker;
import io.restassured.path.json.JsonPath;
import Pasos.Generales.AutorizadorInterno;

public class TC_01_SMOKE_AI {
	WebDriver driver;

	// compraLocalManualDispInsuficiente
	public boolean Test(Reports report, DriverManager DM, int iteration, String name, String configEntidad, String cuentas_generales) {
		//Validation var
		boolean result = true;
		try {	
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
							 + "##################################################################################################################################################################################################################\r\n");
			
			System.out.println("Configuring TC_01_SMOKE_AI \r\n");

			//SET VARIABLES
			// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
			String path = "./API_Requests/Autorizador/TC_01_CompraLocal_Manual_2779,00.txt";
			String token = "";
			String bodyData = "";
			String respBody = "";
			String ID_CUENTA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.cuenta_fisica");
			String NRO_TARJETA = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.nro_tarjeta");
			String VENCIMIENTO = JsonPath.from(cuentas_generales).get("CUENTA_FISICA_1.vencimiento");
			String de39 = ""; 
			int idAutorizacionEmisor = 0;
			int IdAutorizacion = 0;
			int rtaPrePostCondi = 0;
		
			
			// Resultados esperados en la prueba
			String[] resulEsperado = new String[4];
			resulEsperado[0] = "1900";//DISPONIBLE
			resulEsperado[1] = "0";//IMPORTE_PEND_COMPRAS
			resulEsperado[2] = "1";//ID_ESTADO de AUTORIZACION
			resulEsperado[3] = "51";//DE39 EN RESPONSE

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
			token = AI.getToken(report, apiResp,configEntidad);
			
			//GET POST BODY FROM FILE
			bodyData = AI.getBodyData(report, path);
			
			//Se actualiza el arhivo json y la variable bodyData con el nro de tarjeta del TC
			bodyData = AI.setJsonBody(report, path, bodyData, NRO_TARJETA, VENCIMIENTO);
			
			//POST - Compra Local - Manual - Disponible insuficiente
			if (!bodyData.isEmpty() && !token.isEmpty())
			{
							
				respBody = AI.post(report, repoVar, apiResp, bodyData, token);
				
				//Se obtiene el valor del DE39 del response
				de39 = JsonPath.from(respBody).getString("DE39");
				report.AddLine("Valor del DE39 en el response: " + de39);
				System.out.println("Valor del DE39 en el response: " + de39 + "\r\n");
				
				//Se obtiene el valor del IdAutorizacionEmisor del response
				idAutorizacionEmisor = JsonPath.from(respBody).getInt("IdAutorizacionEmisor");
				report.AddLine("Valor del IdAutorizacionEmisor en el response: " + idAutorizacionEmisor);
				System.out.println("Valor del IdAutorizacionEmisor en el response: " + idAutorizacionEmisor + "\r\n");
				
				//Se obtiene el valor del IdAutorizacion del response
				IdAutorizacion = JsonPath.from(respBody).getInt("IdAutorizacion");
				report.AddLine("Valor del IdAutorizacion en el response: " + IdAutorizacion);
				System.out.println("Valor del IdAutorizacion en el response: " + IdAutorizacion + "\r\n");
				
			} else {
				System.out.println("No se obtuvo un token o el body no existe");
				report.AddLineAssertionError("No se obtuvo un token o el body no existe\r\n");
				result = false;
			}	
			
			
			//ROLLBACK
			report.AddLine("--- Ejecutando PostCondicion ---");
			System.out.println("--- Ejecutando PostCondicion ---\r\n");
			
			rtaPrePostCondi = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
					+ "SET SALDO = 1900, DISPONIBLE = 1900, DISPONIBLE_ADELANTO = 1900,\r\n"
					+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = 0, \r\n"
					+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = 0,\r\n"
					+ "IMPORTE_PAGOS = 1900\r\n"
					+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
			
			ppCondi.validaRollBackUpdate(report, rtaPrePostCondi);
			
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