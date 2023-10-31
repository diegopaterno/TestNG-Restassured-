package Repositories;

public class Repo_Variables {
	
		private String datoStr = ""; // private = restricted access
		private int datoInt = 0; // private = restricted access
		private String baseUri;
		private String tipoTC;
		private boolean result;
		
		//REPOSITORIO STRING
		// Getter datoStr
		public String getDataStr() {
			return datoStr;
		}
		// Setter datoStr
		public void setDataStr(String newData) {
			this.datoStr = newData;
		}
		
		
		//REPOSITORIO INTEGER
		// Getter datoInt
		public int getDataInt() {
			return datoInt;
		}
		// Setter datoInt
		public void setDataInt(int newData) {
			this.datoInt = newData;
		}


		//REPOSITORIO MENSAJES
		// Getter datoStr
		public String getDataMsg() {
			return datoStr;
		}
		// Setter datoStr
		public void setDataMsg(String newData) {
			this.datoStr = newData;
		}


		//REPOSITORIO TIPOTC
		// Getter getResult
		public String getTipoTc() {
			return tipoTC;
		}
		// Setter setUri
		public void setTipoTc(String newData) {
			this.tipoTC = newData;
		}

		
		//REPOSITORIO RESULTADOS
		// Getter getResult
		public boolean getResult() {
			return result;
		}
		// Setter setUri
		public void setResult(boolean newData) {
			this.result = newData;
		}


		//REPOSITORIO BASEURI
		// Getter getUri
		public String getBaseUri() {
			return baseUri;
		}
		// Setter setUri
		public void setBaseUri(String newData) {
			this.baseUri = newData;
		}
		
		
		

}
