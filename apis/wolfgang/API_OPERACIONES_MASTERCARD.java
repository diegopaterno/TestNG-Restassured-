package apis.wolfgang;

import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;

import CentaJava.Core.Reports;
import Pasos.Generales.AutorizadorInterno;
import Pasos.Generales.PrePostCondi;
import Repositories.Repo_Variables;
import Tools.apiWorker;
import Tools.dbWorker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.google.gson.*;

public class API_OPERACIONES_MASTERCARD {
	private List<String> Status = new ArrayList<String>();
	//private String Status;
	String esquema;
	public boolean altaCuentaVirtual(Reports report, String name, String configEntidad, String entidad, String path, String statusCodeEsperado) {
		//Validation var
		boolean result = true;
			try {
				//Separador
				System.out.println("\r\n##################################################################################################################################################################################################################"
								 + "##################################################################################################################################################################################################################\r\n");
				
				System.out.println("  SE INICIA EL TEST :" + name + "\r\n");
				//SET VARIABLES
				// Ruta donde se encuentra el archivo con el Body que se envia en el metodo Post
				String token = "";
				String jsonFile = "";
				String bodyData = "";
				String respBody = "";
				String responseData = "";
				boolean resp;
	 			esquema = JsonPath.from(configEntidad).getString("ENTIDAD.esquema_db");

				//SET INSTANCES
				apiWorker apiWorker = new apiWorker();
				dbWorker oraResp = new dbWorker();
				Repo_Variables repoVar = new Repo_Variables();
				AutorizadorInterno AI = new AutorizadorInterno();
				PrePostCondi ppCondi = new PrePostCondi();
				
				//CONFIGURACIÓN DATABASE
				oraResp.setUser(JsonPath.from(configEntidad).get("DB.user"));
				oraResp.setPass(JsonPath.from(configEntidad).get("DB.pass"));
				oraResp.setEntidad(JsonPath.from(configEntidad).get("ENTIDAD.id_entidad"));
				oraResp.setHost(JsonPath.from(configEntidad).get("DB.host"));

				
			}
			catch(Exception e) {
				e.printStackTrace();
				report.AddLineAssertionError(e.getStackTrace()[0].toString());
				report.AddLineAssertionError(e.getMessage());
			}
		
		return result;
	}
}
