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

public class TC_04_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Se clickea el boton NUEVO");
		System.out.println("Se clickea el boton NUEVO\r\n");
		repo.get_obj_btnNuevoConClase().click();

	}

	public void pagina2(Datasources data, Reports report, DriverManager DM, int iteration, String name, Repo_WebRepository repo, String GDA_desc) throws InterruptedException {


		report.AddLine("Cargo el input alfanumerico Descripccion: "+GDA_desc);
		System.out.println("Cargo el input alfanumerico Descripccion: " + GDA_desc + "\r\n");

		repo.get_obj_inputTextByClass("form-control inputDescripcion").sendKeys(GDA_desc);

		report.AddLine("Cargo el input alfanumerico Tipo de producto: Prepaga");
		System.out.println("Cargo el input alfanumerico Tipo de producto: Prepaga\r\n");
		repo.get_obj_selectOptionByName("TipoProducto", "Prepaga").click();
		
		Thread.sleep(500);
		
		report.AddLine("Se visualizan los datos cargados");
		System.out.println("Se visualizan los datos cargados\r\n");
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton Guardar");
		System.out.println("Se hace click en el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();

	}

	public void pagina3(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_h1ConfirmacionExitosa());
		repo.get_obj_h1ConfirmacionExitosa().click();
		
		Thread.sleep(2000);

		report.AddLine("Validacion exitosa");
		System.out.println("Validacion exitosa\r\n");
		report.Screenshot(name);

	}


}