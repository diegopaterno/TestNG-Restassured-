package Tools;

import static io.restassured.RestAssured.given;

import Repositories.Repo_Variables;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class apiWorker {
		
	public String GetAccessTokenPP(String configEntidad) {
		//Base Configuration
		//RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

		//Variables
		//Obtengo los datos necesarios para generar el Token desde el archivo configEntidad que contiene todos los accesos a la entidad
		String url_token = JsonPath.from(configEntidad).get("TOKENPREPAGA.url_token");
		String username = JsonPath.from(configEntidad).get("TOKENPREPAGA.username");
		String password = JsonPath.from(configEntidad).get("TOKENPREPAGA.password");
		String grant_type = JsonPath.from(configEntidad).get("TOKENPREPAGA.grant_type");
		String client_id = JsonPath.from(configEntidad).get("TOKENPREPAGA.client_id");
		String client_secret = JsonPath.from(configEntidad).get("TOKENPREPAGA.client_secret");
		
		String token = RestAssured.given()
				.config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs("x-www-form-urlencoded",ContentType.URLENC)))
				.log().all() //Loguea el Request
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("grant_type", grant_type)
				.formParam("client_id", client_id)
				.formParam("client_secret", client_secret)
				.formParam("username",username)
				.formParam("password",password)
				.when()
				.post(url_token)
				.then()
				.log().all() //Loguea el Response
				.extract()
				.body()
				.asString();

		return JsonPath.from(token).get("token_type") + " " + JsonPath.from(token).get("access_token");
	}
	
	public String GetAccessTokenAI(String configEntidad) {
		//Base Configuration
		//RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		
		//Variables
		//Obtengo los datos necesarios para generar el Token desde el archivo configEntidad que contiene todos los accesos a la entidad
		String url_token = JsonPath.from(configEntidad).get("TOKENAUTINTERNO.url_token");
		String username = JsonPath.from(configEntidad).get("TOKENAUTINTERNO.username");
		String password = JsonPath.from(configEntidad).get("TOKENAUTINTERNO.password");
		String grant_type = JsonPath.from(configEntidad).get("TOKENAUTINTERNO.grant_type");
		String client_id = JsonPath.from(configEntidad).get("TOKENAUTINTERNO.client_id");
		String client_secret = JsonPath.from(configEntidad).get("TOKENAUTINTERNO.client_secret");
		//String endPoint = "http://sso.global.globalprocessing.net.ar/auth/realms/GlobalProcessing/protocol/openid-connect/token";

		String token = RestAssured.given()
				.config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs("x-www-form-urlencoded",ContentType.URLENC)))
				.log().all() //Loguea el Request
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("grant_type", grant_type)
				.formParam("client_id", client_id)
				.formParam("client_secret", client_secret)
				.formParam("username",username)
				.formParam("password",password)
				.when()
				.post(url_token)
				.then()
				.log().all() //Loguea el Response
				.extract()
				.body()
				.asString();

		return JsonPath.from(token).get("token_type") + " " + JsonPath.from(token).get("access_token");
	}

	public Response postMethod(Repo_Variables repoVar, String basePath, String endPoint, String bodyData, String token) throws Exception {
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		RestAssured.basePath = basePath;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.body(bodyData)
				.post(endPoint)
				.then()
				.log().all()
				.extract()
				.response();
	}

	public Response getMethod(Repo_Variables repoVar, String basePath, String endPoint, String token) {
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		RestAssured.basePath = basePath;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.get(endPoint)
				.then()
				.log().all()
				.extract()
				.response();
	}
	
	/*************************GET DE CONSULTAS***************************************/
	public Response getMethodConsultas(Repo_Variables repoVar, String endPoint, String token) {
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		//RestAssured.basePath = basePath;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.get(endPoint)
				.then()
				.log().all()
				.extract()
				.response();
		
	}
	
	/*************************FIN DEL GET DE CONSULTAS*******************************/
	

	public Response getMethod(Repo_Variables repoVar, String basePath, String endPoint, String body, String token) {
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		RestAssured.basePath = basePath;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.body(body)
				.get(endPoint)
				.then()
				.log().all()
				.extract()
				.response();
	}
	/*************************************put*******************************************************/
	
	public Response putMethodHabilitar(Repo_Variables repoVar, String endPoint, String token) {
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		RestAssured.basePath = endPoint;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.when()
				.put(endPoint)
				.then()
				.log().all()
				.extract()
				.response();
	}
	
	public Response putMethodModificarCuenta(Repo_Variables repoVar, String endPoint, String bodyData, String token) {
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		RestAssured.basePath = endPoint;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.when()
				.put(endPoint)
				.then()
				.log().all()
				.extract()
				.response();
	}
	
	/**********************************************************************************************/

	public Response putMethod(Repo_Variables repoVar, String basePath, String endPoint, String token) {
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		RestAssured.basePath = basePath;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.when()
				.put(endPoint)
				.then()
				.log().all()
				.extract()
				.response();
	}

	public Response putMethod(Repo_Variables repoVar, String basePath, String endPoint, String body, String token) {
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		RestAssured.basePath = basePath;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.body(body)
				.put(endPoint)
				.then()
				.log().all()
				.extract()
				.response();
	}
	/**************INFINITUS********************************/
	public Response postMethodInfinitus(Repo_Variables repoVar, String endPoint, String bodyData, String token) throws Exception {
		//se utiliza un RequestId incremental
		//int RequestIdNumber = 0;
		//RequestIdNumber++;
		
		//for(int i = 0; i<10; i++) {	RequestIdNumber = RequestIdNumber++;}
		
		
		// Genera un nuevo requestId PAT
	    int requestId;
	
			requestId = BodyHelper.generarRequestId();
			
		//*********************PAT	
		
		//Base Configuration
		RestAssured.baseURI = repoVar.getBaseUri();
		RestAssured.basePath = endPoint;

		return given()
				.log().all()
				.relaxedHTTPSValidation()
				.header("Authorization",token)
				.header("Content-Type","application/json")
				.header("RequestId", requestId)
				.body(bodyData)
				.post()
				.then()
				.log().all()
				.extract()
				.response();
		
		//.header("RequestId", "9485956")
		
	}
	
	public String GetAccessTokenInfinitus(String configEntidad2_infinitus) {
		//Base Configuration
		//RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

		//Variables
		//Obtengo los datos necesarios para generar el Token desde el archivo configEntidad que contiene todos los accesos a la entidad
		String url_token = JsonPath.from(configEntidad2_infinitus).get("TOKENACCESO.base_url");	
		String scope = JsonPath.from(configEntidad2_infinitus).get("TOKENACCESO.scope");
		String grant_type = JsonPath.from(configEntidad2_infinitus).get("TOKENACCESO.grant_type");
		String client_id = JsonPath.from(configEntidad2_infinitus).get("TOKENACCESO.client_id");
		String client_secret = JsonPath.from(configEntidad2_infinitus).get("TOKENACCESO.client_secret");
		
		String token = RestAssured.given()
				.config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs("x-www-form-urlencoded",ContentType.URLENC)))
				.log().all() //Loguea el Request
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("grant_type", grant_type)
				.formParam("client_id", client_id)
				.formParam("client_secret", client_secret)
				.formParam("scope",scope)
				.relaxedHTTPSValidation()
				.when()
				.post(url_token)
				.then()
				.log().all() //Loguea el Response
				.extract()
				.body()
				.asString();

		return JsonPath.from(token).get("token_type") + " " + JsonPath.from(token).get("access_token");
	}

	
	/************************get token*******************/
	public String GetAccessTokenA() {	
		//Base Configuration
		//RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

		//Variables
		String endPoint = "http://sso.globalprocessingqa.com/auth/realms/GlobalProcessing/protocol/openid-connect/token";
		
		System.out.println("Iniciando restAssured"); 
		
		String token = RestAssured.given()
				.config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs("x-www-form-urlencoded",ContentType.URLENC)))
				.log().all() //Loguea el Request
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("grant_type", "password")
				.formParam("client_id", "APREPAID")
				.formParam("client_secret", "45ae46c0-1792-4830-a564-7f09f17edc6a")
				.formParam("username","prepagas")
				.formParam("password","GlobalProc")
				.when()
				.post(endPoint)
				.then()
				.log().all() //Loguea el Response
				.extract()
				.body()
				.asString();

		return JsonPath.from(token).get("token_type") + " " + JsonPath.from(token).get("access_token");
		
		
		/*String token = RestAssured.given()
				.config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs("x-www-form-urlencoded",ContentType.URLENC)))
				.log().all() //Loguea el Request
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("grant_type", "password")
				.formParam("client_id", "ACOMMERCE")
				.formParam("client_secret", "0dfb5cf9-cd49-42d0-aac0-5b18fb6b279e")
				.formParam("username","comercios")
				.formParam("password","GlobalProc")
				.when()
				.post(endPoint)
				.then()
				.log().all() //Loguea el Response
				.extract()
				.body()
				.asString();

		return JsonPath.from(token).get("access_token");*/
	}
	
	/**************fin metodo get token****************/

}
