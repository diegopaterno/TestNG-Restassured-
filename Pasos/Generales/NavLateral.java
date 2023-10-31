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

 
public class NavLateral {
	WebDriver driver;
	
	// Metodo que utiliza los 4 parametros como niveles de navegacoin
	public void navegacion_lvl2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String nivel1, String nivel2) throws InterruptedException {
		
		//ADD THE STEPS BELOW

		report.AddLine("Desplegamos el primer nivel del menu: " + nivel1);
		System.out.println("Desplegamos el primer nivel del menu: " + nivel1 + "\r\n");
		repo.get_obj_sideBarItem1(nivel1).click();
		Thread.sleep(500);
		
		report.AddLine("Desplegamos el segundo nivel del menu: " + nivel2);
		System.out.println("Desplegamos el segundo nivel del menu: " + nivel2 + "\r\n");
		repo.get_obj_sideBarItem2(nivel1, nivel2).click();
		Thread.sleep(700);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
		repo.get_obj_sideBarItem1(nivel1).click();
		Thread.sleep(500);
		
	}
	
	public void navegacion_lvl3(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String nivel1, String nivel2, String nivel3) throws InterruptedException {
		
		//ADD THE STEPS BELOW

		report.AddLine("Desplegamos el primer nivel del menu: " + nivel1);
		System.out.println("Desplegamos el primer nivel del menu: " + nivel1 + "\r\n");
		repo.get_obj_sideBarItem1(nivel1).click();
		Thread.sleep(500);
		
		report.AddLine("Desplegamos el segundo nivel del menu: " + nivel2);
		System.out.println("Desplegamos el segundo nivel del menu: " + nivel2 + "\r\n");
		repo.get_obj_sideBarItem2(nivel1, nivel2).click();
		Thread.sleep(500);
		
		report.AddLine("Desplegamos el tercer nivel del menu: " + nivel3);
		System.out.println("Desplegamos el tercer nivel del menu: " + nivel3 + "\r\n");
		repo.get_obj_sideBarItem3(nivel1, nivel2, nivel3).click();
		Thread.sleep(700);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
		repo.get_obj_sideBarItem1(nivel1).click();
		Thread.sleep(500);
		
	}
	
	public void navegacion_lvl4(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String nivel1, String nivel2, String nivel3, String nivel4) throws InterruptedException {
				
		//ADD THE STEPS BELOW

		report.AddLine("Desplegamos el primer nivel del menu: " + nivel1);
		System.out.println("Desplegamos el primer nivel del menu: " + nivel1 + "\r\n");
		repo.get_obj_sideBarItem1(nivel1).click();
		Thread.sleep(500);
		
		report.AddLine("Desplegamos el segundo nivel del menu: " + nivel2);
		System.out.println("Desplegamos el segundo nivel del menu: " + nivel2 + "\r\n");
		repo.get_obj_sideBarItem2(nivel1, nivel2).click();
		Thread.sleep(500);
		
		report.AddLine("Desplegamos el tercer nivel del menu: " + nivel3);
		System.out.println("Desplegamos el tercer nivel del menu: " + nivel3 + "\r\n");
		repo.get_obj_sideBarItem3(nivel1, nivel2, nivel3).click();
		Thread.sleep(500);
		
		report.AddLine("Desplegamos el cuarto nivel del menu: " + nivel4);
		System.out.println("Desplegamos el cuarto nivel del menu: " + nivel4 + "\r\n");
		repo.get_obj_sideBarItem4(nivel1, nivel2, nivel3, nivel4).click();
		Thread.sleep(700);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
		repo.get_obj_sideBarItem1(nivel1).click();
		Thread.sleep(500);
				
	}
	
}
