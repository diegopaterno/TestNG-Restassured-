package Repositories;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Repo_WebRepository {
	WebDriver driver = null;
	WebDriverWait waitt;

	public void setDriver(WebDriver d) {
		driver = d;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	}
	//
	public String[] get_def_txtsearch() {
		return new String[] {"id","//input[@class='gLFyf gsfi']"};
	}
	public WebElement get_obj_txtsearch(){		
		return driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
	}
	//
	public String[] get_def_btnsearch() {
		return new String[] {"id","//input[@name='btnK']"};
	}
	public WebElement get_obj_btnsearch() {
		return driver.findElement(By.xpath("//input[@name='btnK']"));
	}
	//
	public String[] get_def_lblcite(String value) {
		return new String[] {"id","(//cite[contains(text(),'" + value + "')])[1]"};
	}
	public WebElement get_obj_lblcite(String value) {
		return driver.findElement(By.xpath("(//cite[contains(text(),'" + value + "')])[1]"));
	}
	//
	public String[] get_def_txtFirstName() {
		return new String[] {"id","//input[@id='FirstName']"};
	}
	public WebElement get_obj_txtFirstName(){		
		return driver.findElement(By.xpath("//input[@id='FirstName']"));
	}
	//
	public String[] get_def_txtLastName() {
		return new String[] {"id","//input[@id='LastName']"};
	}
	public WebElement get_obj_txtLastName(){		
		return driver.findElement(By.xpath("//input[@id='LastName']"));
	}
	//
	public String[] get_def_selCategory(String categoria) {
		return new String[] {"id","//select[@id='Category']/option[text()='" + categoria + "']"};
	}
	public WebElement get_obj_selCategory(String categoria){		
		return driver.findElement(By.xpath("//select[@id='Category']/option[text()='" + categoria + "']"));
	}
	//
	public String[] get_def_rdoGenero(String genero) {
		return new String[] {"id","//input[@id='Gender' and @value='" + genero + "']"};
	}
	public WebElement get_obj_rdoGenero(String genero){		
		return driver.findElement(By.xpath("//input[@id='Gender' and @value='" + genero + "']"));
	}
	//
	public String[] get_def_btnAdd() {
		return new String[] {"id","//input[@id='Add']"};
	}
	public WebElement get_obj_btnAdd(){		
		return driver.findElement(By.xpath("//input[@id='Add']"));
	}
	//
	public String[] get_def_lblNombreTabla(String nombreAgregado) {
		return new String[] {"id","//table[@id='VIPs']//td[.='" + nombreAgregado + "']"};
	}
	public WebElement get_obj_lblNombreTabla(String nombreAgregado){		
		return driver.findElement(By.xpath("//table[@id='VIPs']//td[.='" + nombreAgregado + "']"));
	}
	//
	public String[] get_def_lnkLogIn() {
		return new String[] {"id","//a[@id='login2']"};
	}
	public WebElement get_obj_lnkLogIn(){		
		return driver.findElement(By.xpath("//a[@id='login2']"));
	}
	//
	public String[] get_def_txtUser() {
		return new String[] {"id","//input[@id='loginusername']"};
	}
	public WebElement get_obj_txtUser(){		
		return driver.findElement(By.xpath("//input[@id='loginusername']"));
	}
	//
	public String[] get_def_txtPass() {
		return new String[] {"id","//input[@id='loginpassword']"};
	}
	public WebElement get_obj_txtPass(){		
		return driver.findElement(By.xpath("//input[@id='loginpassword']"));
	}
	//
	public String[] get_def_btnLogIn() {
		return new String[] {"text","//button[.='Log in']"};
	}
	public WebElement get_obj_btnLogIn(){		
		return driver.findElement(By.xpath("//button[.='Log in']"));
	}
	//
	public String[] get_def_lblWelbome() {
		return new String[] {"text","//a[@id='nameofuser']"};
	}
	public WebElement get_obj_lblWelcome(){		
		return driver.findElement(By.xpath("//a[@id='nameofuser']"));
	}
	//
	public String[] get_def_itemCategoria(String categoria) {
		return new String[] {"text","//a[@id='itemc' and text()='" + categoria + "']"};
	}
	public WebElement get_obj_itemCategoria(String categoria){		
		return driver.findElement(By.xpath("//a[@id='itemc' and text()='" + categoria + "']"));
	}


	// -------------- LOGIN FORM PAGE --------------
	// INPUT USERNAME FIELD
	public String[] get_def_inputUsername() {
		return new String[] {"id","//input[@id='username']"};
	}
	public WebElement get_obj_inputUsername(){		
		return driver.findElement(By.xpath("//input[@id='username']"));
	}
	// INPUT PASSWORD FIELD
	public String[] get_def_inputPassword() {
		return new String[] {"id","//input[@id='password']"};
	}
	public WebElement get_obj_inputPassword(){		
		return driver.findElement(By.xpath("//input[@id='password']"));
	}
	// LABEL LOGIN
	public String[] get_def_lblLogin() {
		return new String[] {"id","//h1[@id='kc-page-title']"};
	}
	public WebElement get_obj_lblLogin(){		
		return driver.findElement(By.xpath("//h1[@id='kc-page-title']"));
	}
	// BUTTON LOGIN
	public String[] get_def_btnLogin() {
		return new String[] {"id","//input[@id='kc-login']"};
	}
	public WebElement get_obj_btnLogin(){		
		return driver.findElement(By.xpath("//input[@id='kc-login']"));
	}


	// -------------- MENU SIDE BAR --------------
	// SUB-NIVELES DEL MENU
	public String[] get_def_sideBarItem1(String nivel1) {
		return new String[] {"id","//a[text()='" + nivel1 + "']"};
	}
	public WebElement get_obj_sideBarItem1(String nivel1){		
		return driver.findElement(By.xpath("//a[text()='" + nivel1 + "']"));
	}
	//
	public String[] get_def_sideBarItem2(String nivel1, String nivel2) {
		return new String[] {"id","//a[text()='" + nivel1 + "']/..//a[text()='" + nivel2 + "']"};
	}
	public WebElement get_obj_sideBarItem2(String nivel1, String nivel2){		
		return driver.findElement(By.xpath("//a[text()='" + nivel1 + "']/..//a[text()='" + nivel2 + "']"));
	}
	//
	public String[] get_def_sideBarItem3(String nivel1, String nivel2, String nivel3) {
		return new String[] {"id","//a[text()='" + nivel1 + "']/..//a[text()='" + nivel2 + "']/..//a[text()='" + nivel3 + "']"};
	}
	public WebElement get_obj_sideBarItem3(String nivel1, String nivel2, String nivel3){		
		return driver.findElement(By.xpath("//a[text()='" + nivel1 + "']/..//a[text()='" + nivel2 + "']/..//a[text()='" + nivel3 + "']"));
	}
	// 
	public String[] get_def_sideBarItem4(String nivel1, String nivel2, String nivel3, String nivel4) {
		return new String[] {"id","//a[text()='" + nivel1 + "']/..//a[text()='" + nivel2 + "']/..//a[text()='" + nivel3 + "']/..//a[text()='" + nivel4 + "']"};
	}
	public WebElement get_obj_sideBarItem4(String nivel1, String nivel2, String nivel3, String nivel4){		
		return driver.findElement(By.xpath("//a[text()='" + nivel1 + "']/..//a[text()='" + nivel2 + "']/..//a[text()='" + nivel3 + "']/..//a[text()='" + nivel4 + "']"));
	}


	// -------------- NAV BAR SUPERIOR --------------
	// PESTAÑAS SUPERIORES CLOSE BUTTON (PRIMERA NRO 5)
	public String[] get_def_btnCloseTab() {
		return new String[] {"id","//li[@class='tab active' and @id[contains(.,'tab-')]]//i"};
	}
	public WebElement get_obj_btnCloseTab(){		
		return driver.findElement(By.xpath("//li[@class='tab active' and @id[contains(.,'tab-')]]//i"));
	}
	// BOTON DE SALIR
	public String[] get_def_btnSalir() {
		return new String[] {"id","//a[@id='idLogOff']"};
	}
	public WebElement get_obj_btnSalir(){		
		return driver.findElement(By.xpath("//a[@id='idLogOff']"));
	}


	// -------------- NAVEGACION INTERNA --------------
	// BOTONES
	// BOTON NUEVO POR NAME
	public String[] get_def_btnNuevo() {
		return new String[] {"id","//button[@name='btnNew']"};
	}
	public WebElement get_obj_btnNuevo(){		
		return driver.findElement(By.xpath("//button[@name='btnNew']"));
	}
	// BOTON "GUARDAR"
	public String[] get_def_btnGuardar() {
		return new String[] {"id","//button[text()='Guardar']"};
	}
	public WebElement get_obj_btnGuardar(){		
		return driver.findElement(By.xpath("//button[text()='Guardar']"));
	}
	// BOTON "BUSCAR" +
	public String[] get_def_btnBuscar() {
		return new String[] {"id","//button[text()='Buscar']"};
	}
	public WebElement get_obj_btnBuscar(){		
		return driver.findElement(By.xpath("//button[text()='Buscar']"));
	}
	// BOTON "BUSCAR2" 
	public String[] get_def_btnBuscar2() {
		return new String[] {"id","//button[@type='button' and text()='Buscar']"};
	}
	public WebElement get_obj_btnBuscar2(){		
		return driver.findElement(By.xpath("//button[@type='button' and text()='Buscar']"));
		
	}
	// BOTON NUEVO POR CLASE
	public String[] get_def_btnNuevoConClase() {
		return new String[] {"id","//button[@class='btn btn-new btnNew']"};
	}
	public WebElement get_obj_btnNuevoConClase(){		
		return driver.findElement(By.xpath("//button[@class='btn btn-new btnNew']"));
	}
	// BOTON SIGUIENTE
	public String[] get_def_btnSiguiente() {
		return new String[] {"id","//button[@class='btn btn-nav goToStep']"};
	}
	public WebElement get_obj_btnSiguiente(){		
		return driver.findElement(By.xpath("//button[@class='btn btn-nav goToStep']"));
	}	
	// BOTON AGREGAR MCC
	public String[] get_def_btnAgregarMcc() {
		return new String[] {"id","//button[@class='btn btn-submit btn-agregar']"};
	}
	public WebElement get_obj_btnAgregarMcc(){		
		return driver.findElement(By.xpath("//button[@class='btn btn-submit btn-agregar']"));
	}
	// BOTON OPCIONES (RUEDITA)
	public String[] get_def_btnOpciones() {
		return new String[] {"id","//button[@type='button']/i[@class='fa fa-gear']"};
	}
	public WebElement get_obj_btnOpciones(){		
		return driver.findElement(By.xpath("//button[@type='button']/i[@class='fa fa-gear']"));
	}
	// BOTON EDITAR (DENTRO DE RUEDITA)
	public String[] get_def_btnOpcionesEditar() {
		return new String[] {"id","//a[@class='btn-edit']"};
	}
	public WebElement get_obj_btnOpcionesEditar(){		
		return driver.findElement(By.xpath("//a[@class='btn-edit']"));
	}
	// BOTON CONSULTAR DISPONIBLE (DENTRO DE RUEDITA)
	public String[] get_def_btnOpcionesConsultar() {
		return new String[] {"id","//a[@class='btn-disponible']"};
	}
	public WebElement get_obj_btnOpcionesConsultar(){		
		return driver.findElement(By.xpath("//a[@class='btn-disponible']"));
	}
	// BOTON DEL MODAL "ACEPTAR" 
	public String[] get_def_btnAceptarModal() {
		return new String[] {"id","//button[@id='idBtnModalAceptar']"};
	}
	public WebElement get_obj_btnAceptarModal(){		
		return driver.findElement(By.xpath("//button[@id='idBtnModalAceptar']"));
	}
	// BOTON BUSCAR  DE NUEVO SOCIO
	public String[] get_def_btnBuscarNuevoSocio() {
		return new String[] {"id","//div[@class='form-group col-md-3 form-check']//button[text()='Buscar']"};
	}
	public WebElement get_obj_btnBuscarNuevoSocio(){		
		return driver.findElement(By.xpath("//div[@class='form-group col-md-3 form-check']//button[text()='Buscar']"));
	}
	// BOTON PESTAÑA TITULAR (DENTRO DE CUENTA)
	public String[] get_def_btnTitular() {
		return new String[] {"id","//li[text()='Titular']"};
	}
	public WebElement get_obj_btnTitular(){		
		return driver.findElement(By.xpath("//li[text()='Titular']"));
	}



	// VALIDACIONES
	// SPAN CONFIRMANCION
	public String[] get_def_spanConfirmacion(String text) {
		return new String[] {"id","//span[text()='" + text + "']"};
	}
	public WebElement get_obj_spanConfirmacion(String text){		
		return driver.findElement(By.xpath("//span[text()='" + text + "']"));
	}
	// H1 CONFIRMANCION MODELOS DE LIMITE
	public String[] get_def_h1Confirmacion() {
		return new String[] {"id","//h1[contains(.,'Alta Exitosa.')]"};
	}
	public WebElement get_obj_h1ConfirmacionExitosa(){		
		return driver.findElement(By.xpath("//h1[contains(.,'Alta Exitosa.')]"));
	}
	// TITULO INTERNO
	public String[] get_def_tituloInterno(String titulo) {
		return new String[] {"id","//h2[text()[contains(.,'" + titulo + "')]]"};
	}
	public WebElement get_obj_tituloInterno(String titulo){
		return driver.findElement(By.xpath("//h2[text()[contains(.,'" + titulo + "')]]"));
	}
	// MODIFICACION EXITOSA
	public String[] get_def_modificacionExitosa() {
		return new String[] {"id","//div[@id='maincontent']//p[text()='Modificación Exitosa']"};
	}
	public WebElement get_obj_modificacionExitosa(){		
		return driver.findElement(By.xpath("//div[@id='maincontent']//p[text()='Modificación Exitosa']"));
	}
	// NUEVOS SOCIOS NO ENCONTRADOS +
	public String[] get_def_nuevosSocios() {
		return new String[] {"id","//form[@method='post']//p[@class='borrar' and text()='No se encontraron Socios']"};
	}
	public WebElement nuevosSocios(){		
		return driver.findElement(By.xpath("//form[@method='post']//p[@class='borrar' and text()='No se encontraron Socios']"));
	}
	// TITULO DEL LOGIN 
	public String[] get_def_h1Login() {
		return new String[] {"id","//a[@id='idHome']"};
	}
	public WebElement get_obj_h1Login(){		
		return driver.findElement(By.xpath("//a[@id='idHome']"));
	}
	// CONSULTAR DISPONIBLE EN CUENTA, MODIFICAR XPATH!! <<<
	public String[] get_def_consultarDisponibleCuenta() {
		return new String[] {"id","//div[@id='idTabDatos']//td[3]/div[@class='content-tooltip']"};
	}
	public WebElement get_obj_consultarDisponibleCuenta(){		
		return driver.findElement(By.xpath("//div[@id='idTabDatos']//td[3]/div[@class='content-tooltip']"));
	}
	// CONSULTAR DISPONIBLE EN CUENTA, MODIFICAR XPATH!! <<<
	public String[] get_def_extraccionIdCuenta() {
		return new String[] {"id","//span[@class='name']"};
	}
	public WebElement get_obj_extraccionIdCuenta(){		
		return driver.findElement(By.xpath("//span[@class='name']"));
	}
	

	// INPUTS
	// SELECT GENERICO X NAME
	public String[] get_def_selectOptionByName(String name, String opcion) {
		return new String[] {"id","//select[@name='" + name + "']/option[text()='" + opcion + "']"};
	}
	public WebElement get_obj_selectOptionByName(String name, String opcion){		
		return driver.findElement(By.xpath("//select[@name='" + name + "']/option[text()='" + opcion + "']"));
	}
	
	// SELECT GENERICO X NAME Y VALOR
		public String[] get_def_selectOptionByValue(String name, String opcion) {
			return new String[] {"id","//select[@name='" + name + "']/option[@value'" + opcion + "']"};
		}
		public WebElement get_obj_selectOptionByValue(String name, String opcion){		
			return driver.findElement(By.xpath("//select[@name='" + name + "']/option[@value='" + opcion + "']"));
		}
	

	// INPUT GENERICO X CLASS
	public String[] get_def_inputTextByClass(String className) {
		return new String[] {"id","//form[@method='post']//input[@class='" + className + "']"};
	}
	public WebElement get_obj_inputTextByClass(String className){		
		return driver.findElement(By.xpath("//form[@method='post']//input[@class='" + className + "']"));
	}
	// INPUT GENERICO X NAME
	public String[] get_def_inputTextByName(String name) {
		return new String[] {"id","//form[@method='post']//input[@name='" + name + "']"};
	}
	public WebElement get_obj_inputTextByName(String name){		
		return driver.findElement(By.xpath("//form[@method='post']//input[@name='" + name + "']"));
	}
	// INPUT GENERICO X NAME
	public String[] get_def_inputTextByName2(String name) {
		return new String[] {"id","//input[@name='" + name + "']"};
	}
	public WebElement get_obj_inputTextByName2(String name){		
		return driver.findElement(By.xpath("//input[@name='" + name + "']"));
	}


	// OTROS INPUTS
	// Dias
	public String[] get_def_inputDiasCheck(String dia) {
		return new String[] {"id","//input[@id='input" + dia + "']"};
	}
	public WebElement get_obj_inputDiasCheck(String dia){		
		return driver.findElement(By.xpath("//input[@id='input" + dia + "']"));
	}
	// OPCIONES DEL SELECT MCC
	public String[] get_def_selectMCC(String mcc) {
		return new String[] {"id","//option[@value='" + mcc + "']"};
	}
	public WebElement get_obj_selectMCC(String mcc){		
		return driver.findElement(By.xpath("//option[@value='" + mcc + "']"));
	}
	// OPCIONES PARA QUE EL CALENDARIO DESAPAREZCA DENTRO DEL TC07
	public String[] get_def_inputCorrespondencia(String titulo) {
		return new String[] {"id","//h4[text()[contains(.,'" + titulo + "')]]"};
		
	}
	public WebElement get_obj_inputCorrespondencia(String titulo){		
		return driver.findElement(By.xpath("//h4[text()[contains(.,'" + titulo + "')]]"));
	}


	// EDITAR ESTADO DE PRODUCTO
	public String[] get_def_inputDescripcion() {
		return new String[] {"id","//input[@name='inputDescripcion']"};
	}
	public WebElement get_obj_inputDescripcion(){		
		return driver.findElement(By.xpath("//input[@name='inputDescripcion']"));
	}
	// INPUT NUERO DE CUENTA
	public String[] get_def_inputNroCuenta() {
		return new String[] {"id","//input[@name='inputNroCuenta']"};
	}
	public WebElement get_obj_inputNroCuenta(){		
		return driver.findElement(By.xpath("//input[@name='inputNroCuenta']"));
	}
	//
	
	//------------GLOBALBATCH---------------
	
	// NAVBAR LINKS
	public String[] get_def_navBarLink(String option) {
		return new String[] {"id","//nav//a[@href='"+option+"']"};
	}
	public WebElement get_obj_navBarLink(String option){		
		return driver.findElement(By.xpath("//nav//a[@href='"+option+"']"));
	}
	
	// LOGOUT
	public String[] get_def_logOut() {
		return new String[] {"id","//a[@id='logOff']/i[@class='fa fa-power-off']"};
	}
	public WebElement get_obj_logOut(){		
		return driver.findElement(By.xpath("//a[@id='logOff']/i[@class='fa fa-power-off']"));
	}
	
	// INPUTS
	// Utilizando el id del input
	public String[] get_def_inputById(String id) {
		return new String[] {"id","//input[@id='"+id+"']"};
	}
	public WebElement get_obj_inputById(String id){		
		return driver.findElement(By.xpath("//input[@id='"+id+"']"));
	}
	
	// Select utilizando el id y la opcion
	public String[] get_def_selectOptionById(String id, String opcion) {
		return new String[] {"id","//id[@id='" + id + "']/option[text()='" + opcion + "']"};
	}
	public WebElement get_obj_selectOptionById(String id, String opcion){	
		return driver.findElement(By.xpath("//select[@id='" + id + "']/option[contains(text(),'" + opcion + "')]"));
		//return driver.findElement(By.xpath("//select[@id='" + id + "']/option[text()='" + opcion + "']"));
	}										
	
	// BOTONES DE NAVEGACION
	// Boton Generico por Id
	public String[] get_def_buttonById(String id) {
		return new String[] {"id","//button[@id='"+id+"']"};
	}
	public WebElement get_obj_buttonById(String id){		
		return driver.findElement(By.xpath("//button[@id='"+id+"']"));
	}
	
	// Buscar (Lupita)
	public String[] get_def_buscar() {
		return new String[] {"id","//button[@type='submit']"};
	}
	public WebElement get_obj_buscar(){		
		return driver.findElement(By.xpath("//button[@type='submit']"));
	}
	
	// Icono de lanzar proceso.
	public String[] get_def_lanzar() {
		return new String[] {"id","//i[@class='fa fa-external-link']"};
	}
	public WebElement get_obj_lanzar(){		
		return driver.findElement(By.xpath("//i[@class='fa fa-external-link']"));
	}
	
	//------------GLOBALBACKEND---------------
	
	// Campo precio compra en pantalla de modifiacion de cotizacion
	public String[] get_def_inputCotiCompra() {
		return new String[] {"id","//input[@name='CotizacionCompra']"};
	}
	public WebElement get_obj_inputCotiCompra(){		
		return driver.findElement(By.xpath("//input[@name='CotizacionCompra']"));
	}
	
	// Campo precio venta en pantalla de modifiacion de cotizacion
	public String[] get_def_inputCotiVenta() {
		return new String[] {"id","//input[@name='CotizacionVenta']"};
	}
	public WebElement get_obj_inputCotiVenta(){		
		return driver.findElement(By.xpath("//input[@name='CotizacionVenta']"));
	}
	
	// Logo GP en Global Online y GlobalBackend
	public String[] get_def_LogoGP() {
		return new String[] {"id","//li[@class='logo-entidad']"};
	}
	public WebElement get_obj_LogoGP(){		
		return driver.findElement(By.xpath("//li[@class='logo-entidad']"));
	}
	
	
}