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

public class TC_06_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM,int iteration,String name,Repo_WebRepository repo,String PRODdesc) throws InterruptedException {

		report.AddLine("Se carga descripcion a buscar: "+ PRODdesc);
		System.out.println("Se carga descripcion a buscar: "+ PRODdesc + "\r\n");
		repo.get_obj_inputDescripcion().sendKeys(PRODdesc);

		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar\r\n");
		repo.get_obj_btnBuscar().click();
		
		Thread.sleep(2000);

		report.AddLine("Se busca el producto");
		System.out.println("Se busca el producto\r\n");
		report.Screenshot(name);

		report.AddLine("Se hace click en el boton Opciones");
		System.out.println("Se hace click en el boton Opciones\r\n");
		repo.get_obj_btnOpciones().click();

		report.AddLine("Se hace click en el boton Editar Producto dentro de las opciones");
		System.out.println("Se hace click en el boton Editar Producto dentro de las opciones\r\n");
		repo.get_obj_btnOpcionesEditar().click();

	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Se cambia el IdEstado: Inactivo");
		System.out.println("Se cambia el IdEstado: Inactivo\r\n");
		repo.get_obj_selectOptionByName("IdEstado", "Inactivo").click();

		report.AddLine("Se selecciona GUARDAR");
		System.out.println("Se selecciona GUARDAR \r\n");
		repo.get_obj_btnGuardar().click();

		report.AddLine("Se hace click en el boton Aceptar del Modal");
		System.out.println("Se hace click en el boton Aceptar del Modal\r\n");
		repo.get_obj_btnAceptarModal().click();

	}

	public void validar(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_modificacionExitosa());
		repo.get_obj_modificacionExitosa().click();
		
		Thread.sleep(2000);

		report.AddLine("Validacion exitosa");
		System.out.println("Validacion exitosa\r\n");
		report.Screenshot(name);
	}

	public void revertir(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Se cambia el IdEstado: Inactivo");
		System.out.println("Se cambia el IdEstado: Inactivo\r\n");
		repo.get_obj_selectOptionByName("IdEstado", "Activo").click();

		report.validateObjectIsDisplayable(repo.get_obj_modificacionExitosa());
		
		report.AddLine("Validacion exitosa");
		System.out.println("Validacion exitosa\r\n");
		report.Screenshot(name);
	}


}