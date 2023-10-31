package Pasos.Generales;

import CentaJava.Core.Reports;

public class PrePostCondi {
	
	public void validaRollBackUpdate(Reports report, int rta) {
		if (rta != 0) {
			report.AddLine("Roll Back realizado, se actualizo el registro correctamente");
			System.out.println("##[section] : Roll Back realizado, se actualizo el registro correctamente\r\n");
		} else {
			report.AddLineAssertionError("Roll Back fallido, realizar la actualizacion manualmente");
			System.out.println("##[warning] : Roll Back fallido, realizar la actualizacion manualmente\r\n");
		}
	}
		
	public void validaRollBackUpdate(Reports report, int rta, String resp, String valor) {
		if (rta != 0) {
			report.AddLine("Roll Back realizado, se actualizo el registro correctamente");
			System.out.println("##[section] : Roll Back realizado, se actualizo el registro correctamente\r\n");
		} else {
			report.AddLineAssertionError("Roll Back fallido, realizar la actualizacion manualmente");
			System.out.println("##[warning] : Roll Back fallido, realizar la actualizacion manualmente\r\n");
		}

		if (rta != 0 && !resp.equals(valor)) {
			report.AddLineAssertionError("Error, realizar la actualizacion manualmente");
			System.out.println("##[warning] : Error, realizar la actualizacion manualmente\r\n");
		}	
	}

	public void validaRollBackDelete(Reports report, int rta) {
		if (rta != 0) {
			report.AddLine("Roll Back realizado, se elimino el registro correctamente");
			System.out.println("##[section] : Roll Back realizado, se elimino el registro correctamente\r\n");
		} else {
			report.AddLineAssertionError("Roll Back fallido, realizar la eliminacion manualmente");
			System.out.println("##[warning] : Roll Back fallido, realizar la eliminacion manualmente\r\n");
		}
	}
	
	public void validaRollBackDelete(Reports report, int rta, String resp) {
		if (rta != 0) {
			report.AddLine("Roll Back realizado, se elimino el registro correctamente");
			System.out.println("##[section] : Roll Back realizado, se elimino el registro correctamente\r\n");
		} else {
			report.AddLineAssertionError("Roll Back fallido, realizar la eliminacion manualmente");
			System.out.println("##[warning] : Roll Back fallido, realizar la eliminacion manualmente\r\n");
		}

		if (rta != 0 && !resp.equals("")) {
			report.AddLineAssertionError("Error, realizar la eliminacion manualmente");
			System.out.println("##[warning] : Error, realizar la eliminacion manualmente");
		}	
	}

	public void validaRollBackDelete(Reports report, int rta1, int rta2, String resp) {
		if (rta1 != 0 && rta2 != 0) {
			report.AddLine("Roll Back realizado, se eliminaron los registros correctamente");
			System.out.println("##[section] : Roll Back realizado, se eliminaron los registros correctamente\r\n");
		} else {
			report.AddLineAssertionError("Roll Back fallido, realizar la eliminacion manualmente");
			System.out.println("##[warning] : Roll Back fallido, realizar la eliminacion manualmente\r\n");
		}

		if (rta1 != 0 && rta2 != 0 && !resp.equals("")) {
			report.AddLineAssertionError("Error, realizar la eliminacion manualmente");
			System.out.println("##[warning] : Error, realizar la eliminacion manualmente\r\n");
		}	
	}

	public void rollBackBorrarId (Reports report, String idCuenta, String dni, boolean res) {
		if(res) {
			report.AddLine("Eliminacion de los datos exitosa");
			System.out.println("##[section] : Eliminacion de los datos exitosa\r\n");
		} else {
			report.AddLineAssertionError("Error al eliminar la cuenta; realizar su eliminacion manual");
			report.AddLineAssertionError("Datos para eliminacion manual:<br>ID de CUENTA: " + idCuenta + "<br>DOCUMENTO: " + dni);
			System.out.println("##[warning] : Error al eliminar la cuenta; realizar su eliminacion manual\r\n");
			System.out.println("##[warning] : Datos para eliminacion manual:\r\nID de CUENTA: " + idCuenta + "\r\nDOCUMENTO: " + dni + "\r\n");
		}
	}
	
	public void preCondicionBD (Reports report, int rta) {
		if (rta != 0) {
			report.AddLine("--- PreCondicion ejecutada correctamente ---<br><br>");
			System.out.println("##[section] : <--- PreCondicion ejecutada correctamente --->\r\n");
		}
		else {
			report.AddLine("--- La PreCondicion no modifico ningun registro en la BD ---");
			System.out.println("##[warning] : <--- La PreCondicion no modifico ningun registro en la BD --->\r\n");
		}
	}
	
	public void preCondicionBD (Reports report, String rta) {
		if (rta.equals("")) {
			report.AddLine("<--- La PreCondicion no modifico ningun registro en la BD --->");
			System.out.println("##[warning] : <--- La PreCondicion no modifico ningun registro en la BD --->\r\n");
		} else {
			report.AddLine("<--- PreCondicion ejecutada correctamente --->");
			System.out.println("##[section] : <--- PreCondicion ejecutada correctamente --->\r\n");
		}
	}
	
	public void postCondicionBD (Reports report, int rta) {
		if (rta != 0) {
			report.AddLine("<--- PostCondicion ejecutada correctamente --->");
			System.out.println("##[section] : <--- PostCondicion ejecutada correctamente --->\r\n");
		}
		else {
			report.AddLineAssertionError("La PostCondicion no modifico ningun registro en la BD");
			System.out.println("##[warning] : La PostCondicion no modifico ningun registro en la BD\r\n");
		}
	}
	
	public void compWebConBD (Reports report, int rta, int valorWeb) {
		
		if (valorWeb == rta) {
			report.AddLine("Los valores mostrados en la web("+ valorWeb +") coiciden con los de la BDD("+ rta +").");
			System.out.println("##[section] : Los valores mostrados en la web("+ valorWeb +") coiciden con los de la BDD("+ rta +").");
		} else {
			report.AddLineAssertionError("Error!! Los valores mostrados en la web("+ valorWeb +") no coiciden con los de la BDD("+ rta +").");
			System.out.println("##[warning] : Error!! Los valores mostrados en la web("+ valorWeb +") no coiciden con los de la BDD("+ rta +").");
		}
	}
}
