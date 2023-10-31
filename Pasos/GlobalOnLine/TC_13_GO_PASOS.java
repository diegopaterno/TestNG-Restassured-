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

public class TC_13_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_CUENTA) throws InterruptedException {

	
		report.AddLine("Se carga el input NroCuenta: " + ID_CUENTA);
		System.out.println("Se carga el input NroCuenta: " + ID_CUENTA + "\r\n");
		repo.get_obj_inputNroCuenta().sendKeys(ID_CUENTA);

		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar\r\n");
		repo.get_obj_btnBuscar().click();
		
		Thread.sleep(2000);
		
		report.AddLine("Se visualiza que la busqueda fue exitosa");
		System.out.println("Se visualiza que la busqueda fue exitosa\r\n");
		report.Screenshot(name);

		report.AddLine("Se hace click en el boton Opciones del primer elemento");
		System.out.println("Se hace click en el boton Opciones del primer elemento\r\n");
		repo.get_obj_btnOpciones().click();

		report.AddLine("Se hace click en el boton Editar Tarjeta dentro de las opciones");
		System.out.println("Se hace click en el boton Editar Tarjeta dentro de las opciones\r\n");
		repo.get_obj_btnOpcionesEditar().click();

		Thread.sleep(2000);
		
		report.AddLine("Se visualiza que la tarjeta este en estado BAJA");
		System.out.println("Se visualiza que la tarjeta este en estado BAJA\r\n");
		report.Screenshot(name);

	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Se cambia el Estado de la tarjeta: Habilitada");
		System.out.println("Se cambia el Estado de la tarjeta: Habilitada\r\n");
		repo.get_obj_selectOptionByName("selectEstado", "Habilitada").click();

		
		report.AddLine("Se visualiza que la tarjeta cambio de estado a Habilitada");
		System.out.println("Se visualiza que la tarjeta cambio de estado a Habilitada\r\n");
		report.Screenshot(name);

		report.AddLine("Se hace click en el boton Guardar");
		System.out.println("Se hace click en el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();

		report.AddLine("Se hace click en el boton Aceptar del Modal");
		System.out.println("Se hace click en el boton Aceptar del Modal\r\n");
		repo.get_obj_btnAceptarModal().click();
		
		Thread.sleep(500);
		
	}


	public void validar(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_modificacionExitosa());
		repo.get_obj_modificacionExitosa().click();
		
		Thread.sleep(500);

		report.AddLine("Modificacion exitosa");
		System.out.println("Modificacion exitosa\r\n");
		report.Screenshot(name);
	}

}