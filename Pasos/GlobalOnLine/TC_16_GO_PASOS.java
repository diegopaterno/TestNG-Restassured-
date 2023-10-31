package Pasos.GlobalOnLine;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_WebRepository;

public class TC_16_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_CUENTA) throws InterruptedException {
		
		report.AddLine("Se carga en el input NroCuenta: " + ID_CUENTA);
		System.out.println("Se carga en el input NroCuenta: " + ID_CUENTA + "\r\n");
		repo.get_obj_inputTextByName2("inputNroCuenta").sendKeys(ID_CUENTA);
		
		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar\r\n");
		repo.get_obj_btnBuscar().click();
		
		Thread.sleep(2000);
		
		report.AddLine("Se busca la cuenta");
		System.out.println("Se busca la cuenta\r\n");
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton Opciones del primer elemento");
		System.out.println("Se hace click en el boton Opciones del primer elemento\r\n");
		repo.get_obj_btnOpciones().click();
		
		report.AddLine("Se hace click en el boton Cuenta Tarjeta dentro de las opciones");
		System.out.println("Se hace click en el boton Editar Cuenta dentro de las opciones\r\n");
		repo.get_obj_btnOpcionesEditar().click();

	}
	
	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String dni) throws InterruptedException {
		
		report.AddLine("Se hace click en la solapa Titular");
		System.out.println("Se hace click en la solapa Titular\r\n");
		repo.get_obj_btnTitular().click();
	

		/*report.AddLine("Cargo el input select Tipo de Doc: DNI");
		System.out.println("Cargo el input select Tipo de Doc: DNI\r\n");
		repo.get_obj_selectOptionByName("TipoDocumento", "DNI").click();

		
		report.AddLine("Vacio y Cargo el input Nro de Documento: 34037148");
		System.out.println("Vacio y Cargo el input Nro de Documento: 34037148\r\n");
		repo.get_obj_inputTextByName2("NumeroDocumento").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("NumeroDocumento").sendKeys("34037148");
		*/
		
		report.AddLine("Vacio y Cargo el input Nombre: Selenium");
		System.out.println("Vacio y Cargo el input Nombre: Selenium\r\n");
		repo.get_obj_inputTextByName2("Nombre").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("Nombre").sendKeys("Selenium");
		
		report.AddLine("Vacio y Cargo el input Apellido: Modificado");
		System.out.println("Vacio y Cargo el input Apellido: Modificado\r\n");
		repo.get_obj_inputTextByName2("Apellido").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("Apellido").sendKeys("Modificado");
		
		report.AddLine("Vacio y Cargo el input select Sexo: Femenino");
		System.out.println("Vacio y Cargo el input select Sexo: Femenino\r\n");
		repo.get_obj_selectOptionByName("Sexo", "Femenino").click();
		
		report.AddLine("Vacio y Cargo el input Estado Civil: Casado/a");
		System.out.println("Vacio y Cargo el input Estado Civil: Casado/a\r\n");
		repo.get_obj_selectOptionByName("EstadoCivil", "Casado/a").click();
		
		report.AddLine("Vacio y Cargo el input Fecha Nacimiento: 01/01/2005");
		System.out.println("Vacio y Cargo el input Fecha Nacimiento: 01/01/2005\r\n");
		repo.get_obj_inputTextByName2("FechaNacimiento").clear();
		Thread.sleep(2000);
		//NOTA: Se eligio esta fecha ya que en la ejecucion desde Azure invierte el mes y los dias. De esta manera quedan los dias y meses con los mismos valores.
		repo.get_obj_inputTextByName2("FechaNacimiento").sendKeys("01/01/2005");		
		Thread.sleep(1000);
		repo.get_obj_inputTextByName2("CodTributario").click();
		
		
		report.AddLine("Vacio y Cargo el input select Pais de nacimiento: Alemania");
		System.out.println("Vacio y Cargo el input select Pais de nacimiento: Alemania\r\n");
		Thread.sleep(500);
		repo.get_obj_selectOptionByName("IdPaisNacimiento", "Alemania").click();
		
		report.AddLine("Vacio y Cargo el input Domicilio.Mail: selenium@gmail.com");
		System.out.println("Vacio y Cargo el input Domicilio.Mail: selenium@gmail.com\r\n");
		repo.get_obj_inputTextByName2("Domicilio.Mail").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("Domicilio.Mail").sendKeys("selenium@gmail.com");
		
		/*report.AddLine("Vacio y Cargo el input CUIL: 20340371489");
		System.out.println("Vacio y Cargo el input CUIL: 20340371489\r\n");
		repo.get_obj_inputTextByName2("CodTributario").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("CodTributario").sendKeys("20340371489");
		*/
		
	
		report.AddLine("Vacio y Cargo el Nombre Embozado: RAMIREZ MAURO");
		System.out.println("Vacio y Cargo el Nombre Embozado: RAMIREZ MAURO\r\n");
		repo.get_obj_inputTextByName2("NombreEmbozado").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("NombreEmbozado").sendKeys("RAMIREZ MAURO");
		

		Thread.sleep(2000);
		
		report.AddLine("Se cambian todos los datos del domicilio");
		System.out.println("Se cambian todos los del domicilio\r\n");
		report.Screenshot(name);
		
		report.AddLine("Vacio y Cargo el input Calle: San Martin");
		System.out.println("Vacio y Cargo el input Calle: San Martin\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.Direccion").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.Direccion").sendKeys("San Martin");
		
		report.AddLine("Vacio y Cargo el input Numero: 536");
		System.out.println("Vacio y Cargo el input Numero: 536\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.Numero").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.Numero").sendKeys("536");
		
		report.AddLine("Vacio y Cargo el input Piso: 3");
		System.out.println("Vacio y Cargo el input Piso: 3\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.Piso").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.Piso").sendKeys("3");
		
		report.AddLine("Vacio y Cargo el input Depto: A");
		System.out.println("Vacio y Cargo el input Depto: A\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.Depto").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.Depto").sendKeys("A");
		
		report.AddLine("Vacio y Cargo el input CP: 1004");
		System.out.println("Vacio y Cargo el input CP: 1004\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.IdCodigoPostal").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.IdCodigoPostal").sendKeys("1004");
		
		report.AddLine("Cargo el input CP: Tucuman y Lavalle");
		System.out.println("Cargo el input CP: Tucuman y Lavalle\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.EntreCalles").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.EntreCalles").sendKeys("Tucuman y Lavalle");
		
		report.AddLine("Vacio y Cargo el input Barrio: San Nicolas");
		System.out.println("Vacio y Cargo el input Barrio: San Nicolas\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.Barrio").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.Barrio").sendKeys("San Nicolas");
		
		report.AddLine("Vacio y Cargo el input Localidad: C.A.B.A");
		System.out.println("Vacio y Cargo el input Localidad: C.A.B.A\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.Localidad").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.Localidad").sendKeys("C.A.B.A");
		
		report.AddLine("Vacio y Cargo el input select Provincia: CAPITAL FEDERAL");
		System.out.println("Vacio y Cargo el input select Provincia: CAPITAL FEDERAL\r\n");
		repo.get_obj_selectOptionByName("DomicilioLegal.IdProvincia", "CAPITAL FEDERAL").click();
		
		report.AddLine("Vacio y Cargo el input Telefono: 1145781254");
		System.out.println("Vacio y Cargo el input Telefono: 1145781254\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.Telefono").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.Telefono").sendKeys("1145781254");
		
		report.AddLine("Vacio y Cargo el input Referencia: Edificio GP");
		System.out.println("Vacio y Cargo el input Refencia: Edificio GP\r\n");
		repo.get_obj_inputTextByName2("DomicilioLegal.Referencia").clear();
		Thread.sleep(200);
		repo.get_obj_inputTextByName2("DomicilioLegal.Referencia").sendKeys("Edificio GP");
		
		Thread.sleep(500);
		
		report.AddLine("Se hace click en el boton Guardar");
		System.out.println("Se hace click en el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();
		
		Thread.sleep(1000);
		
		report.AddLine("Se hace click en el boton Aceptar");
		System.out.println("Se hace click en el boton Aceptar\r\n");
		repo.get_obj_btnAceptarModal().click();
		
		Thread.sleep(500);
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
	}
	
	public void validar(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		
		report.AddLine("Validamos que la operacion se genere correctamente");
		System.out.println("Validamos que la operacion se genere correctamente\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_modificacionExitosa());
		repo.get_obj_modificacionExitosa().click();
		
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
	}
	

}