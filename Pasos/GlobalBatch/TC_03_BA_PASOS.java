package Pasos.GlobalBatch;

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
import io.restassured.path.json.JsonPath;

public class TC_03_BA_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String configEntidad) throws InterruptedException {

		String descripcionEntidad = JsonPath.from(configEntidad).get("GBATCH.descripcion");	
		
		report.AddLine("Se carga el nombre del proceso en el input");
		System.out.println("Se carga el nombre del proceso en el input");
		repo.get_obj_inputById("nombre_proceso").sendKeys("Archivo T2001 Transacciones Diarias");
		
		report.AddLine("Se seleciona una opcion del select correspondiente:" + descripcionEntidad);
		System.out.println("Se seleciona una opcion del select correspondiente:" + descripcionEntidad + "\r\n");
		repo.get_obj_selectOptionById("entidades", descripcionEntidad).click();
		
		report.AddLine("Screenshoot de pagina");
		System.out.println("Screenshoot de pagina");
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar");
		repo.get_obj_buscar().click();
		
		Thread.sleep(1000);
		
		report.AddLine("Se hace click en el boton (icono) Lanzar proceso");
		System.out.println("Se hace click en el boton (icono) Lanzar proceso");
		repo.get_obj_lanzar().click();
		
		
	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {
		
		report.AddLine("Screenshoot de pagina");
		System.out.println("Screenshoot de pagina");
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton LANZAR");
		System.out.println("Se hace click en el boton LANZAR");
		repo.get_obj_buttonById("boton_confirmacion_lanzamiento").click();
		
		
	}
	
	public void pagina3(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Screenshoot de pagina");
		System.out.println("Screenshoot de pagina");
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton CONFIRMAR");
		System.out.println("Se hace click en el boton CONFIRMAR");
		repo.get_obj_buttonById("boton_lanzamiento").click();
		//Espero algunos segundos para sacar foto de la pantalla principal
		Thread.sleep(2000);
		report.Screenshot(name);
		
	}


}