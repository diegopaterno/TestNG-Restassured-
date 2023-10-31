package Pasos.Generales;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_WebRepository;
import Repositories.Repo_Variables;
 
public class NavSuperior {
	WebDriver driver;
	
	// Metodo que cierra todas las pestañas abiertas en la sesion
	public void CerrarPest(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
				
		report.AddLine("ScreenShot de pestañas abiertas");
		System.out.println("ScreenShot de pestañas abiertas");
		report.Screenshot(name);
		
		while (true)
		{
			Thread.sleep(500);
			try {
				report.AddLine("Se cierra una pestaña activa");
				repo.get_obj_btnCloseTab().click();
			} catch (Exception e) {
				report.AddLine("No hay más pestañas abiertas");
				report.Screenshot(name);
				break;
			}	
		}
	}
	
}