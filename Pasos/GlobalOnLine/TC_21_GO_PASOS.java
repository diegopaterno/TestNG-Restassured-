package Pasos.GlobalOnLine;

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

public class TC_21_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Se buscar y clickea el boton NUEVO");
		System.out.println("Se buscar y clickea el boton NUEVO\r\n");
		repo.get_obj_btnNuevoConClase().click();
		
	}
	
	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Se carga el input numeroCuenta: 97");
		System.out.println("Se carga el input numeroCuenta: 97\r\n");
		repo.get_obj_inputTextByName("CotizacionCompra").sendKeys("97");
		
		report.AddLine("Se carga el input importe: 104");
		System.out.println("Se carga el input importe: 104\r\n");
		repo.get_obj_inputTextByName("CotizacionVenta").sendKeys("104");
		
		report.AddLine("Se verifican las cargas");
		System.out.println("Se verifican las cargas\r\n");
		report.Screenshot(name);
		
		Thread.sleep(2000);
		
		report.AddLine("Se buscar y clickea el boton Guardar");
		System.out.println("Se buscar y clickea el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();
		
		Thread.sleep(5000);
		
	}

	public void validar(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_h1ConfirmacionExitosa());
		repo.get_obj_h1ConfirmacionExitosa().click();
		
		
		Thread.sleep(5000);

		report.AddLine("Validacion exitosa");
		System.out.println("Validacion exitosa\r\n");
		report.Screenshot(name);
	}

}