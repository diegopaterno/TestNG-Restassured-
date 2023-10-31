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

public class TC_08_GO_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_CUENTA) {

		report.AddLine("Se carga NroCuenta: " + ID_CUENTA);
		System.out.println("Se carga NroCuenta: " + ID_CUENTA + "\r\n");
		repo.get_obj_inputNroCuenta().sendKeys(ID_CUENTA);

		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar\r\n");
		repo.get_obj_btnBuscar().click();
		
		report.AddLine("Se visualiza el dato de la busqueda");
		System.out.println("Se visualiza el dato de la busqueda\r\n");
		report.Screenshot(name);

		report.AddLine("Se hace click en el boton Opciones del primer elemento");
		System.out.println("Se hace click en el boton Opciones del primer elemento\r\n");
		repo.get_obj_btnOpciones().click();

		report.AddLine("Se hace click en el boton Editar Producto dentro de las opciones");
		System.out.println("Se hace click en el boton Editar Producto dentro de las opciones\r\n");
		repo.get_obj_btnOpcionesEditar().click();

	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_SUCURSAL_cambio, String ID_GRUPO_AFINIDAD_cambio) throws InterruptedException {

		report.AddLine("Se cambia la Sucursal: " + ID_SUCURSAL_cambio);
		System.out.println("Se cambia la Sucursal: " + ID_SUCURSAL_cambio + "\r\n");
		repo.get_obj_selectOptionByValue("Sucursal", ID_SUCURSAL_cambio).click();
		
		Thread.sleep(2000);

		report.AddLine("Se cambia el Grupo de afinidad: " + ID_GRUPO_AFINIDAD_cambio);
		System.out.println("Se cambia el Grupo de afinidad: " + ID_GRUPO_AFINIDAD_cambio + "\r\n");
		repo.get_obj_selectOptionByValue("GrupoAfinidad", ID_GRUPO_AFINIDAD_cambio).click();

		report.AddLine("Se cambia la Posicion Impositiva: CONSUMIDOR FINAL");
		System.out.println("Se cambia la Posicion Impositiva: CONSUMIDOR FINAL\r\n");
		repo.get_obj_selectOptionByName("IdPosicionImpositiva", "CONSUMIDOR FINAL").click();
		
		Thread.sleep(2000);
		
		
		report.AddLine("Se vacia y cambia la Cuenta externa: TC08GO");
		System.out.println("Se vacia y cambia la Cuenta externa: TC08GO\r\n");
		repo.get_obj_inputTextByName2("CuentaOrigen").clear();
		repo.get_obj_inputTextByName2("CuentaOrigen").sendKeys("TC08GO");

		report.AddLine("Se cambia la Entrega de tarjeta: Domicilio Correspondencia");
		System.out.println("Se cambia la Entrega de tarjeta: Domicilio Correspondencia\r\n");
		repo.get_obj_selectOptionByName("EntregaTarjeta", "Domicilio Correspondencia").click();

		report.AddLine("Se hace click en el boton Guardar");
		System.out.println("Se hace click en el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();

		report.AddLine("Se hace click en el boton Aceptar del Modal");
		System.out.println("Se hace click en el boton Aceptar del Modal\r\n");
		repo.get_obj_btnAceptarModal().click();

	}


	public void validar(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {

		report.AddLine("Validamos que sea Exitosa la operacion");
		System.out.println("Validamos que sea Exitosa la operacion\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_modificacionExitosa());

		report.AddLine("Validacion Exitosa");
		System.out.println("Validacion Exitosa\r\n");
		report.Screenshot(name);
	}

	public void revertir(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_CUENTA_EXTERNA) {

		report.AddLine("Se cambia la Sucursal: Sucursal CABA");
		System.out.println("Se cambia la Sucursal: Sucursal CABA\r\n");
		repo.get_obj_selectOptionByName("Sucursal", "Sucursal CABA").click();

		report.AddLine("Se cambia el Grupo de afinidad: GAF No Habilita al Embozar");
		System.out.println("Se cambia el Grupo de afinidad: GAF No Habilita al Embozar\r\n");
		repo.get_obj_selectOptionByName("GrupoAfinidad", "GAF No Habilita al Embozar").click();

		report.AddLine("Se cambia la Posicion Impositiva: EXENTO");
		System.out.println("Se cambia la Posicion Impositiva: EXENTO\r\n");
		repo.get_obj_selectOptionByName("IdPosicionImpositiva", "EXENTO").click();

		report.AddLine("Se cambia la Cuenta externa: 1");
		System.out.println("Se cambia la Cuenta externa: 1\r\n");
		repo.get_obj_inputTextByName2("CuentaOrigen").sendKeys(ID_CUENTA_EXTERNA);

		report.AddLine("Se cambia la Entrega de tarjeta: Domicilio Legal");
		System.out.println("Se cambia la Entrega de tarjeta: Domicilio Legal\r\n");
		repo.get_obj_selectOptionByName("EntregaTarjeta", "Domicilio Legal").click();

		report.AddLine("Se hace click en el boton Guardar");
		System.out.println("Se hace click en el boton Guardar\r\n");
		repo.get_obj_btnGuardar().click();

		report.AddLine("Se hace click en el boton Aceptar del Modal\r\n");
		System.out.println("Se hace click en el boton Aceptar del Modal");
		repo.get_obj_btnAceptarModal().click();

		report.AddLine("VERIFICAMOS LOS CAMBIOS REVERTIDOS");
		System.out.println("VERIFICAMOS LOS CAMBIOS REVERTIDOS\r\n");
		report.validateObjectIsDisplayable(repo.get_obj_modificacionExitosa());

	}
}