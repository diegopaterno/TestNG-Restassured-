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

public class TC_14_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_CUENTA) throws InterruptedException {

		report.AddLine("Se carga en el input NroCuenta: " + ID_CUENTA);
		System.out.println("Se carga en el input NroCuenta: " + ID_CUENTA + "\r\n");
		repo.get_obj_inputNroCuenta().sendKeys(ID_CUENTA);

		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar\r\n");
		repo.get_obj_btnBuscar().click();
		
		Thread.sleep(2000);
		
		report.AddLine("Se verifica que la busqueda sea de la cuenta correcta");
		System.out.println("Se verifica que la busqueda sea de la cuenta correcta\r\n");
		report.Screenshot(name);

		report.AddLine("Se hace click en el boton Opciones del primer elemento");
		System.out.println("Se hace click en el boton Opciones del primer elemento\r\n");
		repo.get_obj_btnOpciones().click();

		report.AddLine("Se hace click en el boton Consultar Disponible dentro de las opciones");
		System.out.println("Se hace click en el boton Editar Consultar Disponible dentro de las opciones\r\n");
		repo.get_obj_btnOpcionesConsultar().click();
		
		Thread.sleep(2000);
		
		report.AddLine("Se verifican los disponibles");
		System.out.println("Se verifican los disponibles\r\n");
		report.Screenshot(name);

	}

	public int disponible(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		// Extraemos el valor del disponible y la transformamos a int para retornarlo y compararlo con la bdd
		Integer disponible = Integer.valueOf(repo.get_obj_consultarDisponibleCuenta().getText());

		report.AddLine("El numero mostrado en la pagina de Consulta Disponibles es: "+ disponible);
		System.out.println("El numero mostrado en la pagina de Consulta Disponibles es: "+ disponible+ "\r\n");


		return disponible;

	}

}