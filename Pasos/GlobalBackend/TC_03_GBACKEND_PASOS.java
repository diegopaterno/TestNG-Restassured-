package Pasos.GlobalBackend;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_WebRepository;

public class TC_03_GBACKEND_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Se hace click en el boton BUSCAR");
		System.out.println("Se hace click en el boton BUSCAR\r\n");
		repo.get_obj_btnBuscar().click();
		Thread.sleep(800);
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el Opciones ");
		System.out.println("Se hace click en el boton Opciones\r\n");
		repo.get_obj_btnOpciones().click();
		Thread.sleep(300);
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el Modificar Cotizacion ");
		System.out.println("Se hace click en el boton Modificar Cotizacion\r\n");
		repo.get_obj_btnOpcionesEditar().click();
		Thread.sleep(500);
		report.Screenshot(name);
		
	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String cotizCompra, String cotizVenta) throws InterruptedException {

		report.AddLine("Se ingresa el precio de compra de la cotizacion: " + cotizCompra);
		System.out.println("Se ingresa el precio de compra de la cotizacion: " + cotizCompra + "\r\n");
		//Envio por comando CONTROL + a y DELETE para borrar correctamente el input de Precio Compra ya que el comando CLEAR() no funciono en este caso porque a continuacion se autocompleta el campo en 0,00
		repo.get_obj_inputCotiCompra().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		repo.get_obj_inputCotiCompra().sendKeys(cotizCompra);
		Thread.sleep(300);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);

		report.AddLine("Se ingresa el precio de venta de la cotizacion: " + cotizVenta);
		System.out.println("Se ingresa el precio de venta de la cotizacion: " + cotizVenta + "\r\n");
		//Envio por comando CONTROL + a y DELETE para borrar correctamente el input de Precio Venta ya que el comando CLEAR() no funciono en este caso porque a continuacion se autocompleta el campo en 0,00
		repo.get_obj_inputCotiVenta().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		repo.get_obj_inputCotiVenta().sendKeys(cotizVenta);
		Thread.sleep(300);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);

		report.AddLine("Se presiona el boton Guardar");
		System.out.println("Se presiona el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();
		Thread.sleep(300);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton Aceptar");
		System.out.println("Se hace click en el boton Aceptar\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_btnAceptarModal());
		repo.get_obj_btnAceptarModal().click();
		Thread.sleep(500);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
	}

	public void validacionEdicion(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {


		report.AddLine("Validamos que la operacion se genere correctamente");
		System.out.println("Validamos que la operacion se genere correctamente\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_modificacionExitosa());

		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
	}
}