package Pasos.GlobalBatch;

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
import io.restassured.path.json.JsonPath;

public class TC_05_BA_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String configEntidad) throws InterruptedException {

		String descripcionEntidad = JsonPath.from(configEntidad).get("GBATCH.descripcion");	
		
		report.AddLine("Se carga el nombre del proceso en el input: Embozado Tarjetas");
		System.out.println("Se carga el nombre del proceso en el input: Embozado Tarjetas\r\n");
		repo.get_obj_inputById("nombre_proceso").sendKeys("Embozado Tarjetas");
		
		report.AddLine("Se seleciona una opcion del select correspondiente:" + descripcionEntidad);
		System.out.println("Se seleciona una opcion del select correspondiente:" + descripcionEntidad + "\r\n");
		repo.get_obj_selectOptionById("entidades", descripcionEntidad).click();

		report.AddLine("Screenshoot de pagina");
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton Buscar");
		System.out.println("Se hace click en el boton Buscar\r\n");
		repo.get_obj_buscar().click();
		
		Thread.sleep(1000);
		
		report.AddLine("Se hace click en el boton (icono) Lanzar proceso");
		System.out.println("Se hace click en el boton (icono) Lanzar proceso\r\n");
		repo.get_obj_lanzar().click();
		
		
	}

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String ID_GRUPO_AFINIDAD, String ID_PRODUCTO, String ID_SUCURSAL) throws InterruptedException {

		report.AddLine("Se vacia el campo Grupo de afinidad y se carga con el ID_GRUPO_AFINIDAD: " + ID_GRUPO_AFINIDAD);
		System.out.println("Se vacia el campo Grupo de afinidad y se carga con el ID_GRUPO_AFINIDAD: " + ID_GRUPO_AFINIDAD + "\r\n");
		repo.get_obj_inputById("parametro_01").clear();
		Thread.sleep(300);
		repo.get_obj_inputById("parametro_01").sendKeys(ID_GRUPO_AFINIDAD);
		Thread.sleep(300);
		
		report.AddLine("Se vacia el campo Marca y se carga con el dato: 1");
		System.out.println("Se vacia el campo Marca y se carga con el dato: 1\r\n");
		repo.get_obj_inputById("parametro_02").clear();
		Thread.sleep(300);
		repo.get_obj_inputById("parametro_02").sendKeys("1");
		Thread.sleep(300);
		
		report.AddLine("Se vacia y se carga el campo Ruta destino de los archivos: E:\\\\App\\GlobalBatch\\Automation\\Embozado Tarjetas");
		System.out.println("Se vacia y se carga el campo Ruta destino de los archivos: E:\\\\App\\GlobalBatch\\Automation\\Embozado Tarjetas\r\n");
		repo.get_obj_inputById("parametro_03").clear();
		Thread.sleep(300);
		repo.get_obj_inputById("parametro_03").sendKeys("E:\\\\App\\GlobalBatch\\Automation\\Embozado Tarjetas");
		Thread.sleep(300);
		
		report.AddLine("Se vacia el campo Id del producto y se carga con el ID_PRODUCTO: " + ID_PRODUCTO);
		System.out.println("Se vacia el campo Id del producto y se carga con el ID_PRODUCTO: " + ID_PRODUCTO + "\r\n");
		repo.get_obj_inputById("parametro_04").clear();
		Thread.sleep(300);
		repo.get_obj_inputById("parametro_04").sendKeys(ID_PRODUCTO);
		Thread.sleep(300);
		
		report.AddLine("Se vacia el campo Sucursal y se carga con el ID_SUCURSAL: " + ID_SUCURSAL);
		System.out.println("Se vacia el campo Sucursal y se carga con el ID_SUCURSAL: " + ID_SUCURSAL + "\r\n");
		repo.get_obj_inputById("parametro_05").clear();
		Thread.sleep(300);
		repo.get_obj_inputById("parametro_05").sendKeys(ID_SUCURSAL);
		Thread.sleep(300);
		
		report.AddLine("Se vacia el campo Tipo de Archivo a generar T1020D-T1025D-T1027D y se carga con el dato: T1027D");
		System.out.println("Se vacia el campo Tipo de Archivo a generar T1020D-T1025D-T1027D y se carga con el dato: T1027D\r\n");
		repo.get_obj_inputById("parametro_06").clear();
		Thread.sleep(300);
		repo.get_obj_inputById("parametro_06").sendKeys("T1027D");
		Thread.sleep(300);
		
		report.AddLine("Screenshoot de pagina");
		System.out.println("Screenshoot de pagina\r\n");
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton LANZAR");
		System.out.println("Se hace click en el boton LANZAR\r\n");
		repo.get_obj_buttonById("boton_confirmacion_lanzamiento").click();
		
		report.AddLine("Screenshoot de pagina");
		System.out.println("Screenshoot de pagina\r\n");
		Thread.sleep(300);
		report.Screenshot(name);		
	}
	
	public void pagina3(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
				
		report.AddLine("Se hace click en el boton CONFIRMAR");
		System.out.println("Se hace click en el boton CONFIRMAR\r\n");
		repo.get_obj_buttonById("boton_lanzamiento").click();
		//Espero algunos segundos para sacar foto de la pantalla principal
		Thread.sleep(2000);
		report.Screenshot(name);
		
	}

}