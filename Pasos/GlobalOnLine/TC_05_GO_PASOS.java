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

public class TC_05_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Se clickea el boton NUEVO");
		System.out.println("Se clickea el boton NUEVO\r\n");
		repo.get_obj_btnNuevo().click();

	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String PROD_desc, String MODELO_LIMITE_desc) throws InterruptedException {

		report.AddLine("Cargo la DESCRIPCION: "+PROD_desc);
		System.out.println("Cargo la DESCRIPCION: "+PROD_desc + "\r\n");
		repo.get_obj_inputTextByClass("form-control inputDescripcion").sendKeys(PROD_desc);

		report.AddLine("Cargo el input alfanumerico DE 40 - Service Code: 777");
		System.out.println("Cargo el input alfanumerico DE 40 - Service Code: 777\r\n");
		repo.get_obj_inputTextByClass("form-control inputServiceCode").sendKeys("777");

		report.AddLine("Cargo el input select Marca: Mastercard");
		System.out.println("Cargo el input select Marca: Mastercard\r\n");
		repo.get_obj_selectOptionByName("idMarca", "Mastercard").click();	

		report.AddLine("Cargo el input select Tipo Producto: Prepaga");
		System.out.println("Cargo el input select Tipo Producto: Prepaga\r\n");
		repo.get_obj_selectOptionByName("TipoProducto", "Prepaga").click();	

		report.AddLine("Cargo el input alfanumerico Bin: 55473000");
		System.out.println("Cargo el input alfanumerico Bin: 55473000\r\n");
		repo.get_obj_inputTextByName("PrefijoBin").sendKeys("55473000");

		report.AddLine("Cargo el input select Tipo de Tarjeta: Ambas");
		System.out.println("Cargo el input select Tipo de Tarjeta: Ambas\r\n");
		repo.get_obj_selectOptionByName("FormatoTarjeta", "Ambas").click();

		report.AddLine("Cargo el input select Atributos Tarjeta: Chip Contactless ");
		System.out.println("Cargo el input select Atributos Tarjeta: Chip Contactless\r\n");
		repo.get_obj_selectOptionByName("AtributosTarjeta", "Chip Contactless").click();

		report.AddLine("Cargo el input select Meses Vigencia Alta: 72");
		System.out.println("Cargo el input select Meses Vigencia Alta: 72\r\n");
		repo.get_obj_selectOptionByName("MesesVigenciaAlta", "72").click();

		report.AddLine("Cargo el input alfanumerico Edad minima: 33");
		System.out.println("Cargo el input alfanumerico Edad minima: 33\r\n");
		repo.get_obj_inputTextByName("EdadMinima").sendKeys("33");

		report.AddLine("Cargo el input alfanumerico Edad maxima: 77");
		System.out.println("Cargo el input alfanumerico Edad maxima: 77\r\n");
		repo.get_obj_inputTextByName("EdadMaxima").sendKeys("77");

		report.AddLine("Cargo el input alfanumerico Meses para Renovacion: 7");
		System.out.println("Cargo el input alfanumerico Meses para Renovacion: 7\r\n");
		repo.get_obj_inputTextByName("MesesRenovacion").sendKeys("7");

		report.AddLine("Cargo el input alfanumerico Dias para Habilitacion: 7");
		System.out.println("Cargo el input alfanumerico Dias para Habilitacion: 7\r\n");
		repo.get_obj_inputTextByName("LimiteDiasHabilitacion").sendKeys("7");

		report.AddLine("Cargo el input select Modelos de Limite: " + MODELO_LIMITE_desc);
		System.out.println("Cargo el input select Modelos de Limite: " + MODELO_LIMITE_desc + "\r\n");
		repo.get_obj_selectOptionByName("IdModeloLimite", MODELO_LIMITE_desc).click();
		
		Thread.sleep(500);
		
		report.AddLine("Se visualizan los datos cargados");
		System.out.println("Se visualizan los datos cargados\r\n");
		report.Screenshot(name);


		report.AddLine("Se selecciona GUARDAR");
		System.out.println("Se selecciona GUARDAR \r\n");
		repo.get_obj_btnGuardar().click();

	}

	public void pagina3(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_h1ConfirmacionExitosa());
		
		Thread.sleep(200);
		
		repo.get_obj_h1ConfirmacionExitosa().click();

		report.AddLine("Validacion exitosa");
		System.out.println("Validacion exitosa\r\n");
		report.Screenshot(name);
	}


}