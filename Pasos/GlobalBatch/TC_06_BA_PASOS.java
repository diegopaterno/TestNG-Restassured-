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

public class TC_06_BA_PASOS {
	WebDriver driver;

	public void pagina1(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String configEntidad) throws InterruptedException {

		String descripcionEntidad = JsonPath.from(configEntidad).get("GBATCH.descripcion");	
		
		report.AddLine("Se carga el nombre del proceso en el input: IPM Entrante Emisor");
		System.out.println("Se carga el nombre del proceso en el input: IPM Entrante Emisor\r\n");
		repo.get_obj_inputById("nombre_proceso").sendKeys("IPM Entrante Emisor");
		
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

	public void pagina2(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		report.AddLine("Se vacia y se carga el campo Ruta de destino de los archivos de backup: E:\\\\App\\GlobalBatch\\Automation\\IPM_Entrante\\BACKUP");
		System.out.println("Se vacia y se carga el campo Ruta de destino de los archivos de backup: E:\\\\App\\GlobalBatch\\Automation\\IPM_Entrante\\BACKUP\r\n");
		repo.get_obj_inputById("parametro_01").clear();
		Thread.sleep(300);
		repo.get_obj_inputById("parametro_01").sendKeys("E:\\\\App\\GlobalBatch\\Automation\\IPM_Entrante\\BACKUP");
		Thread.sleep(300);

		report.AddLine("Se vacia y se carga el campo Ruta de destino de los archivos de origen: E:\\\\App\\GlobalBatch\\Automation\\IPM_Entrante\\IN");
		System.out.println("Se vacia y se carga el campo Ruta de destino de los archivos de origen: E:\\\\App\\GlobalBatch\\Automation\\IPM_Entrante\\IN\r\n");
		repo.get_obj_inputById("parametro_02").clear();
		Thread.sleep(300);
		repo.get_obj_inputById("parametro_02").sendKeys("E:\\\\App\\GlobalBatch\\Automation\\IPM_Entrante\\IN");
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
	
	public void pagina3(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {
		
		report.AddLine("Se hace click en el boton CONFIRMAR");
		System.out.println("Se hace click en el boton CONFIRMAR\r\n");
		repo.get_obj_buttonById("boton_lanzamiento").click();
		
		
	}


}