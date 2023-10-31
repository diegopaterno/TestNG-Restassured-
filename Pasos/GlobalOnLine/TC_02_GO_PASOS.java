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

public class TC_02_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Se hace click en el boton NUEVO");
		System.out.println("Se hace click en el boton NUEVO\r\n");
		repo.get_obj_btnNuevo().click();

	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String MDL_desc) {

		report.AddLine("Se carga la Descripcion del MDL: " + MDL_desc);
		System.out.println("Se carga la Descripcion del MDL: " + MDL_desc + "\r\n");
		repo.get_obj_inputTextByName("inputDescripcion").sendKeys(MDL_desc);
		report.Screenshot(name);

		report.AddLine("Se ingresa el porcentaje de adelanto");
		System.out.println("Se ingresa el porcentaje de adelanto\r\n");
		repo.get_obj_inputTextByName("inputPorcentajeAdelantos").sendKeys("007");
		report.Screenshot(name);

		report.AddLine("Se presiona el boton Guardar");
		System.out.println("Se presiona el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();	

	}

	public void pagina3(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String MDL_desc) throws InterruptedException {

		// DOBLE CONFIRMACION SPAN Y H1 CORRECTOS
		report.AddLine("Validamos que la descripcion sea la correcta");
		System.out.println("Validamos que la descripcion sea la correcta \r\n");
		report.validateObjectIsDisplayable(repo.get_obj_spanConfirmacion(MDL_desc));

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_h1ConfirmacionExitosa());
		repo.get_obj_h1ConfirmacionExitosa().click();
		
		Thread.sleep(5000);

		report.AddLine("Captura de la validacion exitosa");
		System.out.println("Captura de la validacion exitosa\r\n");
		report.Screenshot(name);
	}


}