package Pasos.GlobalBackend;

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

public class TC_02_GBACKEND_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Se hace click en el boton NUEVO");
		System.out.println("Se hace click en el boton NUEVO\r\n");
		repo.get_obj_btnNuevo().click();

	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String cotizCompra, String cotizVenta) throws InterruptedException {

		report.AddLine("Se ingresa el precio de compra de la cotizacion: " + cotizCompra);
		System.out.println("Se ingresa el precio de compra de la cotizacion: " + cotizCompra + "\r\n");
		repo.get_obj_inputTextByName("CotizacionCompra").sendKeys(cotizCompra);
		Thread.sleep(500);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
		report.AddLine("Se ingresa el precio de venta de la cotizacion: " + cotizVenta);
		System.out.println("Se ingresa el precio de venta de la cotizacion: " + cotizVenta + "\r\n");
		repo.get_obj_inputTextByName("CotizacionVenta").sendKeys(cotizVenta);
		Thread.sleep(500);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);

		report.AddLine("Se presiona el boton Guardar");
		System.out.println("Se presiona el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();
		Thread.sleep(800);
		report.Screenshot(name);
		
	}

	public void validacion(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		//Tiempo de espera para que impacte la modificacion
		Thread.sleep(1000);
		report.AddLine("Validamos que la operacion se genere correctamente");
		System.out.println("Validamos que la operacion se genere correctamente\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_h1ConfirmacionExitosa());

		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
	}


}