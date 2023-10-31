package SmokeTest.GO;

import org.openqa.selenium.WebDriver;
import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Pasos.Generales.Acceso;
import Pasos.Generales.NavLateral;
import Pasos.Smoke.GO.TC_03_SMOKE_GO_PASOS;
import Repositories.Repo_WebRepository;
import io.restassured.path.json.JsonPath;

public class TC_03_SMOKE_GO {
	WebDriver driver;
	
	public boolean Test(Datasources data,Reports report, DriverManager DM, int iteration,String name, String configEntidad, String cuentas_generales) {
		//validation 
		boolean result = true;
		try {				
			//Separador
			System.out.println("\r\n##################################################################################################################################################################################################################"
                          + "##################################################################################################################################################################################################################\r\n");
			
			System.out.println("Configuring TC_03_SMOKE_GO\r\n");

			//SELECT THE DRIVER AND PATH
			driver = DM.CreateDriver(DM.CHROME);
			report.SetDriver(driver);

			//SET THE REPOSITORIES TO USE
			Repo_WebRepository repo = new Repo_WebRepository();
			repo.setDriver(driver);

			//SET STEPS INSTANCES
			Acceso acceso = new Acceso();
			NavLateral menu = new NavLateral();
			TC_03_SMOKE_GO_PASOS tc = new TC_03_SMOKE_GO_PASOS();

			//SET VARIABLES
			String dni = "34293772";
			String cuil = "20342937722";
		
			
			//Obtengo la URL de GO desde la variable configEntidad que contiene todos los accesos a la entidad
			String URL_GO = JsonPath.from(configEntidad).get("GO.url_go");
			
			System.out.println("##[command] : ------ Initializating test: " + name + " ------\r\n");
			
			//Se abre el driver con la URL de GO
			driver.get(URL_GO);
			
     		//ADD THE STEPS BELOW
			//LOGIN
			report.AddLine("Acceso a la pagina de Global Online con las credenciales");
			acceso.loginGlobalOnLine(data, report, DM, iteration, name, repo, configEntidad);

			//NAVEGACION DEL MENU SIDEBAR
			menu.navegacion_lvl3(data, report, DM, iteration, name, repo, "Emisión", "Socios", "Cuentas");

			//PÁGINA 1
			tc.pagina1 (data, report, DM, iteration, name, repo);

			//PÁGINA 2
			tc.pagina2(data, report, DM, iteration, name, repo);

			//PÁGINA 3
			tc.pagina3(data, report, DM, iteration, name, repo, dni, cuil);

		

			// Esperamos a que se grabe el registro en la base de datos
			Thread.sleep(3000);		
			
			
			
			System.out.println("##[command] : ------ Finished test: " + name + " ------\r\n");
			
			
			//Separador
			System.out.println("##################################################################################################################################################################################################################"
              + "##################################################################################################################################################################################################################\r\n");
			
			
		} catch(Exception e) {
			e.printStackTrace();
			report.AddLineAssertionError(e.getMessage());			
			result = false;
		}
		//Try to erase the driver
		try {
			driver.quit();
		} catch(Exception e2) {
			//return the test result
		}
		return result;
	}
}