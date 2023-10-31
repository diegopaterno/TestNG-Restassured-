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

public class TC_17_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		Thread.sleep(500);
		
		report.AddLine("Se buscar y clickea el boton NUEVO");
		System.out.println("Se buscar y clickea el boton NUEVO\r\n");
		repo.get_obj_btnNuevoConClase().click();
		
	}
	
	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_CUENTA, String ID_SUCURSAL) throws InterruptedException {

		report.AddLine("Se carga el input numeroCuenta:" + ID_CUENTA);
		System.out.println("Se carga el input numeroCuenta: " + ID_CUENTA +"\r\n");
		repo.get_obj_inputTextByName("numeroCuenta").sendKeys(ID_CUENTA);
		
		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar\r\n");
		repo.get_obj_btnBuscar2().click();
		
		Thread.sleep(1000);
		
		report.AddLine("Se busca el numero de cuenta");
		System.out.println("Se busca el numero de cuenta\r\n");
		report.Screenshot(name);
		
		report.AddLine("Cargo el input select Sucursal: Sucursal CABA");
		System.out.println("Cargo el input select Sucursal: Sucursal CABA\r\n");
		repo.get_obj_selectOptionByValue("Sucursal", ID_SUCURSAL).click();
		
		report.AddLine("Cargo el input select moneda: PESOS");
		System.out.println("Cargo el input select moneda: PESOS\r\n");
		repo.get_obj_selectOptionByName("moneda", "PESOS").click();
		
		report.AddLine("Se carga el input importe: 1000");
		System.out.println("Se carga el input importe: 1000\r\n");
		repo.get_obj_inputTextByName("importe").sendKeys("1000");
		
		report.AddLine("Se carga el input NroComprobante: 2589");
		System.out.println("Se carga el input NroComprobante: 2589\r\n");
		repo.get_obj_inputTextByName("NroComprobante").sendKeys("2589");
		
		Thread.sleep(2000);
		
		report.AddLine("Se realiza la carga de importe");
		System.out.println("Se realiza la carga de importe\r\n");
		report.Screenshot(name);
		
		report.AddLine("Se buscar y clickea el boton Guardar");
		System.out.println("Se buscar y clickea el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();
		
	}


	public void validar(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_h1ConfirmacionExitosa());
		
		Thread.sleep(2000);

		report.AddLine("Validacion exitosa");
		System.out.println("Validacion exitosa\r\n");
		report.Screenshot(name);
	}

}