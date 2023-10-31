package Pasos.Generales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CentaJava.Core.Datasources;
import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_WebRepository;
import io.restassured.path.json.JsonPath;

 
public class Acceso {
	WebDriver driver;

	public void loginGlobalOnLine(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String configEntidad) throws InterruptedException, IOException {
		
		// PALEATIVO AL CERTIFICADO SSL, AGREGAR EN CENTA ADD_ARGUMENT EL CHROMEOPTIONS()
		DM.getActualDriver().findElement(By.xpath("//button[@id='details-button']")).click();
		DM.getActualDriver().findElement(By.xpath("//a[@id='proceed-link']")).click();
		
		//Obtengo el user y pass de GO desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("GO.user");
		String pass = JsonPath.from(configEntidad).get("GO.pass");			
						
		//ADD THE STEPS BELOW
		report.AddLine("Se ingresa el username: " + user);
		System.out.println("Se ingresa el username: " + user + "\r\n");
		repo.get_obj_inputUsername().sendKeys(user);
		
		report.AddLine("Se ingresa el password: " + pass);
		System.out.println("Se ingresa el password: " + pass + "\r\n");
		repo.get_obj_inputPassword().sendKeys(pass);
		
		Thread.sleep(1000);
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton Log in");
		System.out.println("Se hace click en el boton Log in\r\n");
		repo.get_obj_btnLogin().click();
		
		//ADD VALIDATIONS AT THE END
		// ESPERAMOS A QUE CARGUE LA PAGINA Y VALIDAMOS QUE SE ENCUENTRE EL LOGO DE GP O LA ENTIDAD
		report.AddLine("Esperamos que la pagina se cargue");
		System.out.println("Esperamos que la pagina se cargue\r\n");
		WebDriverWait waitFor = new WebDriverWait(DM.getActualDriver(), 15);
		waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath(repo.get_def_LogoGP()[1])));
		
		report.AddLine("Screenshoot de pagina cargada correctamente");
		System.out.println("Screenshoot de pagina cargada correctamente\r\n");
		Thread.sleep(300);
		report.Screenshot(name);

	}
	
	public void loginUsername(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String username, String password) {
		
		// PALEATIVO AL CERTIFICADO SSL ( ADD_ARGUMENT EL CHROMEOPTIONS() )
		System.out.println("Certificado SSL");
		DM.getActualDriver().findElement(By.xpath("//button[@id='details-button']")).click();
		DM.getActualDriver().findElement(By.xpath("//a[@id='proceed-link']")).click();
		
		//ADD THE STEPS BELOW
		report.Screenshot(name);
		
		report.AddLine("Se ingresa el username: " + username);
		System.out.println("Se ingresa el username: " + username + "\r\n");
		repo.get_obj_inputUsername().sendKeys(username);
		
		report.AddLine("Se ingresa el password");
		System.out.println("Se ingresa el password\r\n");
		repo.get_obj_inputPassword().sendKeys(password);
		
		report.AddLine("Se hace click en el boton Log in");
		System.out.println("Se hace click en el boton Log in\r\n");
		repo.get_obj_btnLogin().click();
		
		//ADD VALIDATIONS AT THE END
		// ESPERAMOS A QUE CARGUE UN ELEMENTO DEL MENU PARA VALIDAR EL INGRESO
		
		System.out.println("Esperamos que la pagina se cargue");
		WebDriverWait waitFor = new WebDriverWait(DM.getActualDriver(), 15);
		waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath(repo.get_def_sideBarItem1("Adquirencia")[1])));
		
		report.AddLine("Screenshoot de pagina cargada correctamente");
		System.out.println("Screenshoot de pagina cargada correctamente\r\n");
		report.Screenshot(name);

	}
	
	public void loginUsernameGBackEnd(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String configEntidad) throws InterruptedException {
		
		//Obtengo el user y pass de GO desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("GBACKEND.user");
		String pass = JsonPath.from(configEntidad).get("GBACKEND.pass");	
		
		// PALEATIVO AL CERTIFICADO SSL ( ADD_ARGUMENT EL CHROMEOPTIONS() )
		System.out.println("Certificado SSL\r\n");
		DM.getActualDriver().findElement(By.xpath("//button[@id='details-button']")).click();
		DM.getActualDriver().findElement(By.xpath("//a[@id='proceed-link']")).click();
		
		//ADD THE STEPS BELOW
		Thread.sleep(5000);
		report.Screenshot(name);
		
		report.AddLine("Se ingresa el username: " + user);
		System.out.println("Se ingresa el username: " + user + "\r\n");
		repo.get_obj_inputUsername().sendKeys(user);
		
		report.AddLine("Se ingresa el password: " + pass);
		System.out.println("Se ingresa el password: " + pass + "\r\n");
		repo.get_obj_inputPassword().sendKeys(pass);
		Thread.sleep(300);
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton Log in");
		System.out.println("Se hace click en el boton Log in\r\n");
		repo.get_obj_btnLogin().click();
		
		//ADD VALIDATIONS AT THE END
		// ESPERAMOS A QUE CARGUE UN ELEMENTO DEL MENU PARA VALIDAR EL INGRESO
		
		report.AddLine("Esperamos que la pagina se cargue");
		System.out.println("Esperamos que la pagina se cargue\r\n");
		WebDriverWait waitFor = new WebDriverWait(DM.getActualDriver(), 15);
		waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath(repo.get_def_sideBarItem1("Impuestos")[1])));
		
		report.AddLine("Screenshoot de pagina cargada correctamente");
		System.out.println("Screenshoot de pagina cargada correctamente\r\n");
		report.Screenshot(name);

	}
	
	public void logout(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) throws InterruptedException {
		
		//ADD THE STEPS BELOW
		report.AddLine("Se hace click en el boton Salir");
		System.out.println("Se hace click en el boton Salir\r\n");
		repo.get_obj_btnSalir().click();
		
		Thread.sleep(2000);
		
		report.AddLine("Se hace click en el boton ACEPTAR del Modal");
		System.out.println("Se hace click en el boton ACEPTAR del Modal\r\n");
		repo.get_obj_btnAceptarModal().click();
		
		Thread.sleep(2000);
		
		//ADD VALIDATIONS AT THE END
		System.out.println("Esperamos que la pagina se cargue luego de cerrar sesion\r\n");
		WebDriverWait waitFor = new WebDriverWait(DM.getActualDriver(), 15);
		waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath(repo.get_def_h1Login()[1])));
	}
	
	public void loginUsernameBatch(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo, String configEntidad) throws InterruptedException {
		
		DesiredCapabilities ssl = DesiredCapabilities.chrome ();       
		ssl.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		@SuppressWarnings("deprecation")
		//WebDriver driver = new ChromeDriver (ssl); 
	
		/*ChromeOptions options = new ChromeOptions();
		options.setCapability("acceptInsecureCerts", true);
		WebDriver driver = new ChromeDriver(options);*/
		//Obtengo el user y pass de GO desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("GBATCH.user");
		String pass = JsonPath.from(configEntidad).get("GBATCH.pass");	
		
		// PALEATIVO AL CERTIFICADO SSL ( ADD_ARGUMENT EL CHROMEOPTIONS() )
		report.AddLine("Certificado SSL");
		System.out.println("Certificado SSL\r\n");
		
		//DM.getActualDriver().findElement(By.cssSelector("#details-button")).click();
		//DM.getActualDriver().findElement(By.xpath("Continuar a v2batch.web.qa.global.globalprocessing.net.ar (no seguro)"));
		//DM.getActualDriver().findElement(By.cssSelector("#procced-link")).click();
		//DM.getActualDriver().findElement(By.cssSelector("#username")).click();
		//DM.getActualDriver().findElement(By.cssSelector("#password")).click();
		DM.getActualDriver().findElement(By.xpath("//button[@id='details-button']")).click();
		DM.getActualDriver().findElement(By.xpath("//a[@id='proceed-link']")).click();
		
		//ADD THE STEPS BELOW
		Thread.sleep(2000);
		report.Screenshot(name);
		
		report.AddLine("Se ingresa el username: " + user);
		System.out.println("Se ingresa el username: "+ user + "\r\n");
		repo.get_obj_inputUsername().sendKeys(user);
		
		report.AddLine("Se ingresa el password: " + pass + "\r\n");
		System.out.println("Se ingresa el password: " + pass + "\r\n");
		repo.get_obj_inputPassword().sendKeys(pass);
		
		Thread.sleep(200);
		report.Screenshot(name);
		
		report.AddLine("Se hace click en el boton Log in");
		System.out.println("Se hace click en el boton Log in\r\n");
		repo.get_obj_btnLogin().click();
		
		//ADD VALIDATIONS AT THE END
		// ESPERAMOS A QUE CARGUE UN ELEMENTO DEL MENU PARA VALIDAR EL INGRESO
	
		report.AddLine("Esperamos que la pagina se cargue");
		System.out.println("Esperamos que la pagina se cargue\r\n");

		WebDriverWait waitFor = new WebDriverWait(DM.getActualDriver(), 15);
		waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath(repo.get_def_navBarLink("/")[1])));
		
		report.AddLine("Screenshoot de pagina cargada correctamente");
		System.out.println("Screenshoot de pagina cargada correctamente\r\n");
		Thread.sleep(300);
		report.Screenshot(name);

	}
	/*************login batch
	 * @throws IOException **********************************/
public void loginUsernameBatch1(DriverManager DM,Repo_WebRepository repo) throws InterruptedException, IOException {
		
	     String path = "./Datasources/config_entidad.json";
	     String configEntidad = new String(Files.readAllBytes(Paths.get(path)));
		
		//Obtengo el user y pass de GO desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("GBATCH.user");
		String pass = JsonPath.from(configEntidad).get("GBATCH.pass");	
		
		// PALEATIVO AL CERTIFICADO SSL ( ADD_ARGUMENT EL CHROMEOPTIONS() )
		//report.AddLine("Certificado SSL");
		System.out.println("Certificado SSL\r\n");
		DM.getActualDriver().findElement(By.xpath("//button[@id='details-button']")).click();
		DM.getActualDriver().findElement(By.xpath("//a[@id='proceed-link']")).click();
		
		//ADD THE STEPS BELOW
		Thread.sleep(2000);
		//report.Screenshot(name);
		
		//report.AddLine("Se ingresa el username: " + user);
		System.out.println("Se ingresa el username: "+ user + "\r\n");
		repo.get_obj_inputUsername().sendKeys(user);
		
		//report.AddLine("Se ingresa el password: " + pass + "\r\n");
		System.out.println("Se ingresa el password: " + pass + "\r\n");
		repo.get_obj_inputPassword().sendKeys(pass);
		
		Thread.sleep(200);
		//report.Screenshot(name);
		
		//report.AddLine("Se hace click en el boton Log in");
		System.out.println("Se hace click en el boton Log in\r\n");
		repo.get_obj_btnLogin().click();
		
		//ADD VALIDATIONS AT THE END
		// ESPERAMOS A QUE CARGUE UN ELEMENTO DEL MENU PARA VALIDAR EL INGRESO
	
		//report.AddLine("Esperamos que la pagina se cargue");
		System.out.println("Esperamos que la pagina se cargue\r\n");

		WebDriverWait waitFor = new WebDriverWait(DM.getActualDriver(), 15);
		waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath(repo.get_def_navBarLink("/")[1])));
		
	//	report.AddLine("Screenshoot de pagina cargada correctamente");
		System.out.println("Screenshoot de pagina cargada correctamente\r\n");
		Thread.sleep(300);
	//	report.Screenshot(name);

	}
	
	public void logoutBA(Datasources data,Reports report, DriverManager DM, int iteration,String name,Repo_WebRepository repo) {
		
		//ADD THE STEPS BELOW
		report.AddLine("Se hace click en el boton LogOff");
		System.out.println("Se hace click en el boton LogOff\r\n");
		repo.get_obj_logOut().click();
		
	}
	
}