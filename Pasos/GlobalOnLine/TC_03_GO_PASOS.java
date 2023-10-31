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

public class TC_03_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Se hace clickea el boton NUEVO");
		System.out.println("Se hace clickea el boton NUEVO\r\n");
		repo.get_obj_btnNuevoConClase().click();

	}

	public void pagina2(Datasources data, Reports report, DriverManager DM, int iteration, String name, Repo_WebRepository repo, String MDR_desc) throws InterruptedException {

		report.AddLine("Carga el input alfanumerico Descripcion: " + MDR_desc);
		System.out.println("Carga el input alfanumerico Descripcion: " + MDR_desc + "\r\n");
		repo.get_obj_inputTextByClass("form-control inputDescripcion").sendKeys(MDR_desc);

		report.AddLine("Se clickea el input del dia Lunes");
		System.out.println("Se clickea el input del dia Lunes\r\n");
		repo.get_obj_inputDiasCheck("Lunes").click();

		report.AddLine("Se clickea el input del dia Miercoles");
		System.out.println("Se clickea el input del dia Miercoles\r\n");
		repo.get_obj_inputDiasCheck("Miercoles").click();

		report.AddLine("Se clickea el input del dia Viernes");
		System.out.println("Se clickea el input del dia Viernes\r\n");
		repo.get_obj_inputDiasCheck("Viernes").click();
		
		Thread.sleep(500);
		
		report.AddLine("Se visualizan los dias cargados");
		System.out.println("Se visualizan los dias cargados \r\n");
		report.Screenshot(name);

		report.AddLine("Se carga el tope de la operacion: 9000");
		System.out.println("Se carga el tope de la operacion: 9000 \r\n");
		repo.get_obj_inputTextByName("inputTopeOperacion").sendKeys("9000");

		Thread.sleep(500);
		
		report.AddLine("Se visualizan la carga del tope de la operacion");
		System.out.println("Se visualizan la carga del tope de la operacion\r\n");
		report.Screenshot(name);

		report.AddLine("Se selecciona el boton SIGUIENTE");
		System.out.println("Se selecciona el boton SIGUIENTE \r\n");
		repo.get_obj_btnSiguiente().click();

		report.AddLine("Se agrega opcion 3777 del select MCC");
		System.out.println("Se agrega opcion 3777 del select MCC\r\n");
		repo.get_obj_selectMCC("3777").click();
		repo.get_obj_btnAgregarMcc().click();

		report.AddLine("Se agrega opcion 1799 del select MCC");
		System.out.println("Se agrega opcion 1799 del select MCC\r\n");
		repo.get_obj_selectMCC("1799").click();
		repo.get_obj_btnAgregarMcc().click();
		
		Thread.sleep(500);
		
		report.AddLine("Se visualiza la carga del MCC");
		System.out.println("Se visualiza la carga del MCC\r\n");
		report.Screenshot(name);

		report.AddLine("Se selecciona GUARDAR");
		System.out.println("Se selecciona GUARDAR \r\n");
		repo.get_obj_btnGuardar().click();

	}

	public void pagina3(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Validamos que sea exitosa la operacion con la confirmacion");
		System.out.println("Validamos que sea exitosa la operacion con la confirmacion\r\n");

		report.validateObjectIsDisplayable(repo.get_obj_h1ConfirmacionExitosa());

		report.AddLine("Validacion exitosa");
		System.out.println("Validacion exitosa\r\n");
		report.Screenshot(name);

	}


}