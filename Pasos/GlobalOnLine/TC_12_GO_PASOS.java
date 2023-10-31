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

public class TC_12_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_CUENTA) throws InterruptedException {

		report.AddLine("Se carga el input Nro cuenta:" + ID_CUENTA);
		System.out.println("Se carga el input Nro cuenta:" + ID_CUENTA + "\r\n");
		repo.get_obj_inputNroCuenta().sendKeys(ID_CUENTA);

		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar\r\n");
		repo.get_obj_btnBuscar().click();
		
		Thread.sleep(5000);
		
		report.AddLine("Se busca la tarjeta");
		System.out.println("Se busca la tarjeta\r\n");
		report.Screenshot(name);

		report.AddLine("Se hace click en el boton Opciones del primer elemento");
		System.out.println("Se hace click en el boton Opciones del primer elemento\r\n");
		repo.get_obj_btnOpciones().click();

		report.AddLine("Se hace click en el boton Editar Tarjeta dentro de las opciones");
		System.out.println("Se hace click en el boton Editar Tarjeta dentro de las opciones\r\n");
		repo.get_obj_btnOpcionesEditar().click();
		
		Thread.sleep(5000);
		
		report.AddLine("Se Verifica que este en estado habilitada");
		System.out.println("Se Verifica que este en estado habilitada\r\n");
		report.Screenshot(name);

	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Se cambia el Estado de la tarjeta: Dada de Baja");
		System.out.println("Se cambia el Estado de la tarjeta: Dada de Baja\r\n");
		repo.get_obj_selectOptionByName("selectEstado", "Dada de Baja").click();

		Thread.sleep(500);
		
		report.AddLine("Se Verifica que este en estado Dada de Baja");
		System.out.println("Se Verifica que este en estado Dada de Baja\r\n");
		report.Screenshot(name);

		report.AddLine("Se hace click en el boton Guardar");
		System.out.println("Se hace click en el boton Guardar\r\n");
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
		
		Thread.sleep(5000);

		report.AddLine("Se verifica que la pagina hizo el cambio de forma exitosa");
		System.out.println("Se verifica que la pagina hizo el cambio de forma exitosa\r\n");
		report.Screenshot(name);		

	}
}