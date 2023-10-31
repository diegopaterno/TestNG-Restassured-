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
 
public class NavSuperiorBatch {
	WebDriver driver;
	
	// Inicio
	public void inicio(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		String option = "/";
		
		report.AddLine("Se hace click en el boton : Inicio");
		System.out.println("Se hace click en el boton : Inicio\r\n");
		repo.get_obj_navBarLink(option).click();
		
		report.AddLine("ScreenShot de la pagina selecionada");
		System.out.println("ScreenShot de la pagina selecionada\r\n");
		Thread.sleep(300);
		report.Screenshot(name);
	}
	
	// Procesos en ejecucion
	public void procesosEjec(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		String option = "/Ejecucion";
		
		report.AddLine("Se hace click en el boton : Inicio");
		System.out.println("Se hace click en el boton : Inicio\r\n");
		repo.get_obj_navBarLink(option).click();
		
		report.AddLine("ScreenShot de la pagina selecionada");
		System.out.println("ScreenShot de la pagina selecionada\r\n");
		Thread.sleep(300);
		report.Screenshot(name);
	}
	
	// Procesos
	public void procesos(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		String option = "/Proceso";
		
		report.AddLine("Se hace click en el boton : Inicio");
		System.out.println("Se hace click en el boton : Inicio\r\n");
		repo.get_obj_navBarLink(option).click();
		
		report.AddLine("ScreenShot de la pagina selecionada");
		System.out.println("ScreenShot de la pagina selecionada\r\n");
		Thread.sleep(300);
		report.Screenshot(name);
	}
	/******************************************************************/
public void procesos1(Datasources data,Reports report,DriverManager DM,Repo_WebRepository repo,String configEntidad) throws InterruptedException {
		
		String option = "/Proceso";
		
		//report.AddLine("Se hace click en el boton : Inicio");
		System.out.println("Se hace click en el boton : Inicio\r\n");
		repo.get_obj_navBarLink(option).click();
		
		//report.AddLine("ScreenShot de la pagina selecionada");
		System.out.println("ScreenShot de la pagina selecionada\r\n");
		Thread.sleep(300);
		//report.Screenshot(name);
	}
	
	/******************************************************************/
	
	
	// Historial
	public void historial(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		String option = "/Ejecucion/Logs";
		
		report.AddLine("Se hace click en el boton : Inicio");
		System.out.println("Se hace click en el boton : Inicio\r\n");
		repo.get_obj_navBarLink(option).click();
		
		report.AddLine("ScreenShot de la pagina selecionada");
		System.out.println("ScreenShot de la pagina selecionada\r\n");
		Thread.sleep(300);
		report.Screenshot(name);
	}
	
}