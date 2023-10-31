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

public class TC_07_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Validamos que estamos ingresando correctamente a Gestion de cuentas");
		System.out.println("Validamos que estamos ingresando correctamente a Gestion de cuentas\r\n");
		report.Screenshot(name);

		report.AddLine("Se buscar y clickea el boton NUEVO");
		System.out.println("Se buscar y clickea el boton NUEVO");
		repo.get_obj_btnNuevoConClase().click();

	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String descripcion_sucursal, String descripcion_ga, String ID_PRODUCTO, String descripcion_producto) throws InterruptedException {

		report.AddLine("Cargo el input select Sucursal: " + descripcion_sucursal);
		System.out.println("Cargo el input select Sucursal:" + descripcion_sucursal + "\r\n");
		repo.get_obj_selectOptionByName("Sucursal", descripcion_sucursal).click();	

		report.AddLine("Cargo el input select Posicion Impositiva: CONSUMIDOR FINAL");
		System.out.println("Cargo el input select Posicion Impositiva: CONSUMIDOR FINAL\r\n");
		repo.get_obj_selectOptionByName("IdPosicionImpositiva", "CONSUMIDOR FINAL").click();
		
		report.AddLine("Cargo el input select Grupo de afinidad:" + descripcion_ga);
		System.out.println("Cargo el input select Grupo de afinidad:" + descripcion_ga + "\r\n");
		repo.get_obj_selectOptionByName("GrupoAfinidad", descripcion_ga).click();

		report.AddLine("Cargo el input select Tipo de producto: Prepaga");
		System.out.println("Cargo el input select Tipo de producto: Prepaga\r\n");
		repo.get_obj_selectOptionByName("TipoProducto", "Prepaga").click();

		report.AddLine("Cargo el input select Producto:" + descripcion_producto);
		System.out.println("Cargo el input select Producto:" + descripcion_producto + "\r\n");
		repo.get_obj_selectOptionByName("IdProducto", ID_PRODUCTO + " - " + descripcion_producto).click();
		
		Thread.sleep(500);
		
		report.AddLine("Cargo el input select Tipo de tarjeta: Tarjeta Virtual");
		System.out.println("Cargo el input select Tipo de tarjeta: Tarjeta Virtual\r\n");
		repo.get_obj_selectOptionByName("FormatoTarjeta", "Tarjeta Virtual").click();
		
		Thread.sleep(500);
		
		report.AddLine("Vemos los datos cargados");
		System.out.println("Vemos los datos cargados\r\n");
		report.Screenshot(name);

		report.AddLine("Se hace click en el boton Siguiente");
		System.out.println("Se hace click en el boton Siguiente\r\n");
		repo.get_obj_btnSiguiente().click();

	}

	public void pagina3(Datasources data, Reports report, DriverManager DM, int iteration, String name, Repo_WebRepository repo, String dni, String cuil) throws InterruptedException {
		
		Thread.sleep(1000);
		
		report.AddLine("Cargo el input select Tipo de Doc: DNI");
		System.out.println("Cargo el input select Tipo de Doc: DNI\r\n");
		repo.get_obj_selectOptionByName("TipoDocumento", "DNI").click();

		report.AddLine("Cargo el input Nro de Documento: "+dni);
		System.out.println("Cargo el input Nro de Documento: "+dni+"\r\n");
		repo.get_obj_inputTextByName("NumeroDocumento").sendKeys(dni);

		report.AddLine("Se clickea el boton Buscar");
		System.out.println("Se clickea el boton Buscar\r\n");
		repo.get_obj_btnBuscarNuevoSocio().click();

		Thread.sleep(2000);
		
		report.AddLine("Validamos que NO exista el DNI");
		System.out.println("Validamos que NO exista el DNI\r\n");
		report.Screenshot(name);

		report.AddLine("Cargo el input Nombre: Satoshi");
		System.out.println("Cargo el input Nombre: Satoshi\r\n");
		repo.get_obj_inputTextByName("Nombre").sendKeys("Satoshi");

		report.AddLine("Cargo el input Apellido: Nakamoto");
		System.out.println("Cargo el input Apellido: Nakamoto\r\n");
		repo.get_obj_inputTextByName("Apellido").sendKeys("Nakamoto");

		report.AddLine("Cargo el input select Sexo: Masculino");
		System.out.println("Cargo el input select Sexo: Masculino\r\n");
		repo.get_obj_selectOptionByName("Sexo", "Masculino").click();

		report.AddLine("Cargo el input Estado Civil: Soltero/a");
		System.out.println("Cargo el input Estado Civil: Soltero/a\r\n");
		repo.get_obj_selectOptionByName("EstadoCivil", "Soltero/a").click();

		report.AddLine("Cargo el input Fecha Nacimiento: 01/01/1991");
		System.out.println("Cargo el input Fecha Nacimiento: 01/01/1991\r\n");
		repo.get_obj_inputTextByName("FechaNacimiento").sendKeys("01/01/1991");

		report.AddLine("Cargo el input select Pais de nacimiento: Argentina");
		System.out.println("Cargo el input select Pais de nacimiento: Argentina\r\n");
		repo.get_obj_selectOptionByName("IdPaisNacimiento", "Argentina").click();

		report.AddLine("Cargo el input Email: satoshi@nakamoto.com");
		System.out.println("Cargo el input Email: satoshi@nakamoto.com\r\n");
		repo.get_obj_inputTextByName("Mail").sendKeys("satoshi@nakamoto.com");
		

		report.AddLine("Cargo el input CodTributario: "+cuil);
		System.out.println("Cargo el input CodTributario: "+cuil+ "\r\n");
		repo.get_obj_inputTextByName("CodTributario").sendKeys(cuil);
		
        Thread.sleep(2000);
		
		report.AddLine("Validamos los datos cargados");
		System.out.println("Validamos los datos cargados\r\n");
		report.Screenshot(name);

		report.AddLine("Cargo el input Calle: Calla Falsa");
		System.out.println("Cargo el input Calle: Calla Falsa\r\n");
		repo.get_obj_inputTextByName("DomicilioLegal.Direccion").sendKeys("Calle Falsa");

		report.AddLine("Cargo el input Numero: 123");
		System.out.println("Cargo el input Numero: 123\r\n");
		repo.get_obj_inputTextByName("DomicilioLegal.Numero").sendKeys("123");

		report.AddLine("Cargo el input CP: 1431");
		System.out.println("Cargo el input CP: 1431\r\n");
		repo.get_obj_inputTextByName("DomicilioLegal.IdCodigoPostal").sendKeys("1431");

		report.AddLine("Cargo el input Localidad: CABA");
		System.out.println("Cargo el input Localidad: CABA\r\n");
		repo.get_obj_inputTextByName("DomicilioLegal.Localidad").sendKeys("CABA");

		report.AddLine("Cargo el input select Provincia: CAPITAL FEDERAL");
		System.out.println("Cargo el input select Provincia: CAPITAL FEDERAL\r\n");
		repo.get_obj_selectOptionByName("DomicilioLegal.IdProvincia", "CAPITAL FEDERAL").click();

		report.AddLine("Cargo el input Telefono: 150606456");
		System.out.println("Cargo el input Telefono: 150606456\r\n");
		repo.get_obj_inputTextByName("DomicilioLegal.Telefono").sendKeys("150606456");
		
		repo.get_obj_inputCorrespondencia("Domicilio Correspondencia").click();
		
		Thread.sleep(1000);
		
		report.AddLine("Validamos los datos de las calles");
		System.out.println("Validamos los datos de las calles\r\n");
		report.Screenshot(name);
		
		report.AddLine("Se presiona el boton guardar");
		System.out.println("Se presiona el boton guardar\r\n");
		repo.get_obj_btnGuardar().click();

	}

	public void validar(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_h1ConfirmacionExitosa());
		repo.get_obj_h1ConfirmacionExitosa().click();
		
		Thread.sleep(1000);

		report.AddLine("Validacion exitosa");
		System.out.println("Validacion exitosa\r\n");
		report.Screenshot(name);	
		
	}

}