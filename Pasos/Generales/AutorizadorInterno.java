package Pasos.Generales;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;

import CentaJava.Core.DriverManager;
import CentaJava.Core.Reports;
import Repositories.Repo_Variables;
import Tools.apiWorker;
import Tools.dbWorker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

	
public class AutorizadorInterno {
	
	
	// Configuracion del endpoint de la API
		public void setEndpoint(Reports report, Repo_Variables repoVar, String endPoint) {
			//String endpoint = "http://10.72.0.22:8499/approval/credit";
			//String endpoint = "http://10.72.0.22:8293/approval/credit";		
			repoVar.setBaseUri(endPoint);
		}

	// Configuracion del endpoint de la API
	public void setBaseUrl(Reports report, Repo_Variables repoVar, String configEntidad) {
		
		String base_url = JsonPath.from(configEntidad).get("TOKENAUTINTERNO.base_url");
		
		repoVar.setBaseUri(base_url);
	}
	
	
	// Solicitud del token de acceso.
	public String getToken(Reports report, apiWorker apiResp, String configEntidad) {
		String token = ""; 
		
		try {
			token = apiResp.GetAccessTokenAI(configEntidad);
		} catch (Exception E) {
			report.AddLineAssertionError("No se obtuvo un token.\r\nError: " + E);
			System.out.println("##[warning] : No se obtuvo un token.\r\nError: " + E);
		}
		return token;
	}
	
	
	// Extraccion del body del archivo txt
	public String getBodyData(Reports report, String path) {
		
		String bodyData = ""; 
		
		try {
			bodyData = new String(Files.readAllBytes(Paths.get(path)));		
			
			//Se muestra el body en el reporte
			report.AddLine("Request body:<br>" + bodyData);
			System.out.println("Request body:<br>" + bodyData + "\r\n");
			
		} catch (Exception E) {
			report.AddLine("Error al abrir el archivo.<br>Error: " + E);
			System.out.println("##[warning] : Error al abrir el archivo.\r\nError: " + E);
		}
		
		return bodyData;
	}
	
	public String setJsonBody(Reports report, String path, String bodyData, String NRO_TARJETA, String VENCIMIENTO) throws IOException {
		
		//ACTUALIZACION DEL NRO DE TARJETA
		//Leo el json actual para obtener el nro de tarjeta actual
		String nroTarjetaActual = JsonPath.from(bodyData).get("DE2");
		report.AddLine("El nro de tarjeta del archivo json actual es: " + nroTarjetaActual);
		System.out.println("\r\nEl nro de tarjeta del archivo json actual es: " + nroTarjetaActual + "\r\n");
		
		//Reemplazo el nro de tarjeta actual del archivo por el nro de tarjeta del TC
		String bodyDataModif = bodyData.replace("\"DE2\":\"" + nroTarjetaActual + "\"","\"DE2\":\"" + NRO_TARJETA + "\"");
		
		nroTarjetaActual = JsonPath.from(bodyDataModif).get("DE2");
		report.AddLine("El nro de tarjeta del archivo json actualizado es: " + nroTarjetaActual);
		System.out.println("El nro de tarjeta del archivo json actualizado es: " + nroTarjetaActual + "\r\n");
		
		//ACTUALIZACION DE LA FECHA DE VENCIMIENTO
		//Leo el json actual para obtener la fecha de vencimiento actual
		String vencimientoActual = JsonPath.from(bodyDataModif).get("DE14");
		report.AddLine("La fecha de vencimiento actual es: " + vencimientoActual);
		System.out.println("La fecha de vencimiento actual es: " + vencimientoActual + "\r\n");
		
		//Reemplazo la fecha de vencimiento actual del archivo por la fecha de vencimiento de la tarjeta del TC
		bodyDataModif = bodyDataModif.replace("\"DE14\":\"" + vencimientoActual + "\"","\"DE14\":\"" + VENCIMIENTO + "\"");
		
		vencimientoActual = JsonPath.from(bodyDataModif).get("DE14");
		report.AddLine("La fecha de vencimiento del archivo json actualizado es: " + vencimientoActual);
		System.out.println("La fecha de vencimiento del archivo json actualizado es: " + vencimientoActual + "\r\n");
		
		report.AddLine("Se procede a modificar el archivo json actual");
		System.out.println("Se procede a modificar el archivo json actual\r\n");
		report.AddLine("Archivo json actualizado: " + bodyDataModif);
		System.out.println("Archivo json actualizado: \r\n" + bodyDataModif + "\r\n");
		
		//Grabo en el archivo el nuevo json generado
		FileWriter archivoNuevo = new FileWriter(path);
		archivoNuevo.write(bodyDataModif);
		archivoNuevo.close();
		
		return bodyDataModif;
	}
	
	public String setJsonBodyDE35(Reports report, String path, String bodyData, String NRO_TARJETA, String VENCIMIENTO) throws IOException {
		
		//Genero un nuevo DE35 con los datos de la tarjeta utilizado en el TC
		String DE35Actualizado = NRO_TARJETA + "D" + VENCIMIENTO + "10101100100";
		
		//ACTUALIZACION DEL NRO DE TARJETA
		//Leo el json actual para obtener el nro de tarjeta actual
		String nroTarjetaActual = JsonPath.from(bodyData).get("DE2");
		report.AddLine("El nro de tarjeta del archivo json actual es: " + nroTarjetaActual);
		System.out.println("\r\nEl nro de tarjeta del archivo json actual es: " + nroTarjetaActual + "\r\n");
		
		//Reemplazo el nro de tarjeta actual del archivo por el nro de tarjeta del TC
		String bodyDataModif = bodyData.replace("\"DE2\":\"" + nroTarjetaActual + "\"","\"DE2\":\"" + NRO_TARJETA + "\"");
		
		nroTarjetaActual = JsonPath.from(bodyDataModif).get("DE2");
		report.AddLine("El nro de tarjeta del archivo json actualizado es: " + nroTarjetaActual);
		System.out.println("El nro de tarjeta del archivo json actualizado es: " + nroTarjetaActual + "\r\n");
		
		//ACTUALIZACION DE LA FECHA DE VENCIMIENTO
		//Leo el json actual para obtener la fecha de vencimiento actual
		String vencimientoActual = JsonPath.from(bodyDataModif).get("DE14");
		report.AddLine("La fecha de vencimiento actual es: " + vencimientoActual);
		System.out.println("La fecha de vencimiento actual es: " + vencimientoActual + "\r\n");
		
		//Reemplazo la fecha de vencimiento actual del archivo por la fecha de vencimiento de la tarjeta del TC
		bodyDataModif = bodyDataModif.replace("\"DE14\":\"" + vencimientoActual + "\"","\"DE14\":\"" + VENCIMIENTO + "\"");
		
		vencimientoActual = JsonPath.from(bodyDataModif).get("DE14");
		report.AddLine("La fecha de vencimiento del archivo json actualizado es: " + vencimientoActual);
		System.out.println("La fecha de vencimiento del archivo json actualizado es: " + vencimientoActual + "\r\n");
		
		//ACTUALIZACION DEL DE35
		String nroTarjetaActualDE35 = JsonPath.from(bodyDataModif).get("DE35");
		report.AddLine("Valor del DE35 del archivo json actual: " + nroTarjetaActualDE35);
		System.out.println("Valor del DE35 del archivo json actual: " + nroTarjetaActualDE35 + "\r\n");
		
		//Reemplazo el nro de tarjeta actual y el vencimiento del DE35 del archivo por el nro de tarjeta y vencimiento del TC
		bodyDataModif = bodyDataModif.replace("\"DE35\":\"" + nroTarjetaActualDE35 + "\"","\"DE35\":\"" + DE35Actualizado + "\"");	
		
		nroTarjetaActualDE35 = JsonPath.from(bodyDataModif).get("DE35");
		report.AddLine("Valor del DE35 actualizado: " + nroTarjetaActualDE35);
		System.out.println("Valor del DE35 actualizado: " + nroTarjetaActualDE35 + "\r\n");
		
		report.AddLine("Se procede a modificar el archivo json actual");
		System.out.println("Se procede a modificar el archivo json actual\r\n");
		report.AddLine("Archivo json actualizado: <br>" + bodyDataModif);
		System.out.println("Archivo json actualizado: \r\n\r\n" + bodyDataModif + "\r\n");
		
		//Grabo en el archivo el nuevo json generado
		FileWriter archivoNuevo = new FileWriter(path);
		archivoNuevo.write(bodyDataModif);
		archivoNuevo.close();
		
		return bodyDataModif;
	}
	
	
	// Realizamos un post a travez del api wroker, verificamos que el status code sea el correcto y devolvemos la respuesta
	public String post(Reports report, Repo_Variables repoVar, apiWorker apiResp,String bodyData,String  token) throws Exception {
		
		Response response;
		
		response = apiResp.postMethod(repoVar,"" ,"",bodyData, token);
		//Se reporta el Status Code
		report.AddLine("Status Code: " + String.valueOf(response.getStatusCode()));
		System.out.println("Status Code: " + String.valueOf(response.getStatusCode()) + "\r\n");
		//Se valida el status code
		if(response.getStatusCode()!=200) {
			report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()));
			System.out.println("##[warning] : Fallo la validacion del StatusCode\r\nEsperado: 200 - Obtenido: " + String.valueOf(response.getStatusCode()) + "\r\n");
			MatcherAssert.assertThat("##[warning] : Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));				
		}
		//Se obtiene el body del response//Obteniendo el numero de cuenta creado
		return response.getBody().asPrettyString();
	}
	
	/****************************METODO POST*********************/
	
	// Realizamos un post a travez del api wroker, verificamos que el status code sea el correcto y devolvemos la respuesta
		public String post2(Reports report, Repo_Variables repoVar, apiWorker apiWorker, String bodyData, String token, String configEntidad, String statusCodeEsperado) throws Exception {
			Response response;
		//	sshWorker apiCmd = new sshWorker();
			
			String bodyResponse = "";
			for(int i = 1;i < 4;i++) {
				/*apiCmd.stopOpiCmd(report, configEntidad, "opi4qa");
				Thread.sleep(1500);
				apiCmd.startOpiCmd(report, configEntidad, "opi4qa");
				Thread.sleep(1500);
				apiCmd.startOpiCmd(report, configEntidad, "opi4qa");
				Thread.sleep(1500);*/
				
				response = apiWorker.postMethod(repoVar,"" , "", bodyData, token);
				String statusCode = String.valueOf(response.getStatusCode());
				
				//Se reporta el Status Code
				report.AddLine("Resultado " + i + " POST - Status Code: " + statusCode);
				System.out.println("Resultado " + i + " POST - Status Code: " + statusCode + "\r\n");
				//Se valida el status code
				System.out.println("es es el valor que trajo la variable status code esperado :" + statusCodeEsperado);
				if((!statusCode.equals(statusCodeEsperado)) && i == 3) {
					report.AddLineAssertionError("Fallo la validacion del StatusCode<br>Esperado: "+ statusCodeEsperado +" - Obtenido: " + statusCode);
					System.out.println("Fallo la validacion del StatusCode\r\nEsperado: "+ statusCodeEsperado +" - Obtenido: " + statusCode + "\r\n");
					Assert.assertTrue(false);
//					MatcherAssert.assertThat("Validacion del Status code", String.valueOf(response.getStatusCode()), equalTo("200"));
				}else if(statusCode.equals(statusCodeEsperado)) {
					bodyResponse = response.getBody().asPrettyString();
					break;
				}
				bodyResponse = response.getBody().asPrettyString();
			}
			
			//Se obtiene el body del response//Obteniendo el numero de cuenta creado
			return bodyResponse;
		}
	
	/**************************FIN METODO POST*****************/
	
	
	
	// VALIDACIONES de la respuesta
	public boolean validacionCuentasMoneda(Reports report, String[] rtaQuery, String[] resulEsperado) {

		String[] res = new String[17];

		if(!rtaQuery[0].equals(resulEsperado[0])) {
			report.AddLineAssertionError("Error:<br>El DISPONIBLE es: " + rtaQuery[0] + " Se esperaba DISPONIBLE: " + resulEsperado[0]);
			System.out.println("##[warning] : Error:\r\nEl DISPONIBLE es: " + rtaQuery[0] + " Se esperaba DISPONIBLE: " + resulEsperado[0] + "\r\n");
			res[0] = "KO";
		} else {
			report.AddLine("Validacion exitosa<br>El DISPONIBLE esperado es: " + resulEsperado[0] + "y es igual al de la base de datos: " + rtaQuery[0]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DISPONIBLE esperado es: " + resulEsperado[0] + " y es igual al de la base de datos: " + rtaQuery[0] + "\r\n");
			res[0] = "OK";
		}

		if(!rtaQuery[1].equals(resulEsperado[1])) {
			report.AddLineAssertionError("Error:<br>El IMPORTE_PEND_COMPRAS es: " + rtaQuery[1] + " Se esperaba IMPORTE_PEND_COMPRAS: " + resulEsperado[1]);
			System.out.println("##[warning] : Error:\r\nEl IMPORTE_PEND_COMPRAS  es: " + rtaQuery[1] + " Se esperaba IMPORTE_PEND_COMPRAS: " + resulEsperado[1] + "\r\n");
			res[1] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El IMPORTE_PEND_COMPRAS esperado es : " + resulEsperado[1] + " y es igual al de la base de datos: " + rtaQuery[1]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl IMPORTE_PEND_COMPRAS esperado es : " + resulEsperado[1] + " y es igual al de la base de datos: " + rtaQuery[1] + "\r\n");
			res[1] = "OK";
		}

		if (res[0].equals("OK") && res[1].equals("OK"))
		{
			return true;
		} else {
			return false;
		}

	}
	
	public boolean validacionAutorizacion(Reports report, String respuesta, String[] resulEsperado) {
				
		if(!respuesta.equals(resulEsperado[2])) {
			report.AddLineAssertionError("Error:<br>El ID_ESTADO de la autorizacion es: " + respuesta + " Se esperaba el ID_ESTADO: " + resulEsperado[2]);
			System.out.println("##[warning] : Error:\r\nEl ID_ESTADO de la autorizacion es: " + respuesta + " Se esperaba el ID_ESTADO: " + resulEsperado[2] + "\r\n");
			return false;
			
		} else {
			report.AddLine("Validacion exitosa:<br>El ID_ESTADO de la autorizacion es: " + respuesta  + " y es igual al ID_ESTADO esperado = " + resulEsperado[2]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl ID_ESTADO de la autorizacion es: " + respuesta  + " y es igual al ID_ESTADO esperado = " + resulEsperado[2] + "\r\n");
			return true;
		}

	}
	
	public boolean validacionDE39EnResponse(Reports report, String respuesta, String[] resulEsperado) {
		
		if(!respuesta.equals(resulEsperado[3])) {
			report.AddLineAssertionError("Error:<br>El DE39 del response es: " + respuesta + " Se esperaba el DE39: " + resulEsperado[3]);
			System.out.println("##[warning] : Error:\r\nEl DE39 del response es: " + respuesta + " Se esperaba el DE39: " + resulEsperado[3] + "\r\n");
			return false;
			
		} else {
			report.AddLine("Validacion exitosa:<br>El DE39 del response es: " + respuesta  + " y es igual al DE39 esperado: " + resulEsperado[3]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DE39 del response es: " + respuesta  + " y es igual al DE39 esperado: " + resulEsperado[3] + "\r\n");
			return true;
		}

	}
	
	public boolean validacionesRespBase(Reports report, String valorResponse, String valorBD) {
		
		if(valorResponse.equals(valorBD)) {
			System.out.println("##[section] : Validacion exitosa: Los datos del response son iguales a los de la base\r\n");
			return true;
		}
		else {
			System.out.println("##[warning] : Validacion incorrecta: Los datos del response no son iguales a los de la base\r\n");
			return false;
		}
		
	}
	
	public boolean preCondicionParaReversosAI(Reports report, DriverManager DM, String pathPreCondi, String disponible, String importe_pend_compras, String configEntidad, String ID_CUENTA, String NRO_TARJETA, String VENCIMIENTO) throws Exception {
		
		//SET VARIABLES
		// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
		String token = "";
		String bodyData = "";
		String respBody = "";
		String de39 = ""; 
		int rtaPrePostCondi = 0;
		boolean result;

		//SET INSTANCES
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		Repo_Variables repoVar = new Repo_Variables();
		PrePostCondi ppCondi = new PrePostCondi();
		AutorizadorInterno AI = new AutorizadorInterno();
		
		//SET ENVIRONMENT
		AI.setBaseUrl(report, repoVar,configEntidad);

		//PRECONDICION
		report.AddLine("--- Ejecutando autorizacion para precondicion del Reverso ---");
		System.out.println("--- Ejecutando autorizacion para precondicion del Reverso ---\r\n");
		
		rtaPrePostCondi = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 1900, DISPONIBLE = " + disponible + ", DISPONIBLE_ADELANTO = 1900,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + importe_pend_compras + ",\r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = 0,\r\n"
				+ "IMPORTE_PAGOS = 1900\r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		
		ppCondi.preCondicionBD(report, rtaPrePostCondi);
		
		//GET TOKEN
		token = AI.getToken(report, apiResp,configEntidad);
		
		//GET POST BODY FROM FILE
		bodyData = AI.getBodyData(report, pathPreCondi);
		
		//Se actializa el arhivo json con el nro de tarjeta del TC
		bodyData = AI.setJsonBody(report, pathPreCondi, bodyData, NRO_TARJETA, VENCIMIENTO);
		
		//POST - Compra Local - Manual - Disponible insuficiente
		if (!bodyData.isEmpty() && !token.isEmpty())
		{
			
			respBody = AI.post(report, repoVar, apiResp, bodyData, token);
			
			//Se obtiene el valor del DE39 del response
			de39 = JsonPath.from(respBody).getString("DE39");
			report.AddLine("Valor del DE39 en el response: " + de39);
			System.out.println("Valor del DE39 en el response: " + de39 + "\r\n");
			result = true;
			
		} else {
			System.out.println("##[warning] : No se obtuvo un token o el body no existe");
			report.AddLineAssertionError("No se obtuvo un token o el body no existe");
			result = false;
		}	
		return result;
	}
	
    public boolean preCondicionParaReversosDevoluciones(Reports report, DriverManager DM, String pathPreCondi, String disponible, String importe_pend_compras, String configEntidad, String ID_CUENTA, String NRO_TARJETA, String VENCIMIENTO) throws Exception {
		
		//SET VARIABLES
		// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
		String token = "";
		String bodyData = "";
		String respBody = "";
		String de39 = ""; 
		int rtaRollback1 = 0;
		int rtaRollback2 = 0;
		boolean result;

		//SET INSTANCES
		apiWorker apiResp = new apiWorker();
		dbWorker oraResp = new dbWorker();
		Repo_Variables repoVar = new Repo_Variables();
		PrePostCondi ppCondi = new PrePostCondi();
		AutorizadorInterno AI = new AutorizadorInterno();
		
		//SET ENVIRONMENT
		AI.setBaseUrl(report, repoVar,configEntidad);

		//PRECONDICION
		report.AddLine("--- Ejecutando autorizacion para precondicion del Reverso ---");
		System.out.println("--- Ejecutando autorizacion para precondicion del Reverso ---\r\n");
		
		rtaRollback1 = oraResp.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 1900, DISPONIBLE = " + disponible + ", DISPONIBLE_ADELANTO = 1900,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + importe_pend_compras + ",\r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = 0,\r\n"
				+ "IMPORTE_PAGOS = 1900\r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		
		ppCondi.preCondicionBD(report, rtaRollback1);
		
		//Se realiza update para que se pueden generar correctamente las devoluciones utilizando el TID
		rtaRollback2 = oraResp.oraUpdate(report, "UPDATE AUTORIZACION \r\n"
				+ "SET FECHA_RELACION = TRUNC(SYSDATE),MONTO_ACUM_DEVOLUCIONES = 0 \r\n"
				+ "WHERE TERMINAL_POS = 'Reg00004' AND ID_CUENTA = " + ID_CUENTA, configEntidad);
		ppCondi.preCondicionBD(report, rtaRollback2);
		
		//GET TOKEN
		token = AI.getToken(report, apiResp,configEntidad);
		
		//GET POST BODY FROM FILE
		bodyData = AI.getBodyData(report, pathPreCondi);
		
		//Se actializa el arhivo json con el nro de tarjeta del TC
		bodyData = AI.setJsonBodyDE35(report, pathPreCondi, bodyData, NRO_TARJETA, VENCIMIENTO);
		
		//Vuelvo a leer el archivo ya actualizado
		bodyData = AI.getBodyData(report, pathPreCondi);
		
		//POST - Compra Local - Manual - Disponible insuficiente
		if (!bodyData.isEmpty() && !token.isEmpty())
		{
			
			respBody = AI.post(report, repoVar, apiResp, bodyData, token);
			
			//Se obtiene el valor del DE39 del response
			de39 = JsonPath.from(respBody).getString("DE39");
			report.AddLine("Valor del DE39 en el response: " + de39);
			System.out.println("Valor del DE39 en el response: " + de39 + "\r\n");
			result = true;
			
		} else {
			System.out.println("##[warning] : No se obtuvo un token o el body no existe");
			report.AddLineAssertionError("No se obtuvo un token o el body no existe");
			result = false;
		}	
		return result;
	}

    public void setEstadoCuentaTarjeta(Reports report, String ID_CUENTA, String ID_ESTADO_CUENTA, String ID_ESTADO_TARJETA, String configEntidad) {
	
	dbWorker oraResp = new dbWorker();
	PrePostCondi ppCondi = new PrePostCondi();
	int rtaQuery;
	
	rtaQuery = oraResp.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = " + ID_ESTADO_TARJETA + " WHERE ID_CUENTA = " + ID_CUENTA , configEntidad);
	ppCondi.preCondicionBD(report, rtaQuery);

	//Seteo la cuenta en estado activa para que no falle el TC
	rtaQuery = oraResp.oraUpdate(report, "UPDATE CUENTAS SET ID_ESTADO = " + ID_ESTADO_CUENTA + " WHERE ID_CUENTA = " + ID_CUENTA , configEntidad);
	ppCondi.preCondicionBD(report, rtaQuery);
	
	}

}
