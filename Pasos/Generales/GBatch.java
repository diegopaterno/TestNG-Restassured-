package Pasos.Generales;

import static org.hamcrest.Matchers.equalTo;
import java.sql.SQLException;

import org.hamcrest.MatcherAssert;

import CentaJava.Core.Reports;
import Repositories.Repo_Variables;
import Tools.dbWorker;

public class GBatch {


	// VALIDACIONES EN EL ESQUEMA PARAM

	public void verifProcesosEjecParam(Reports report, Repo_Variables repoVar, String verifQueryParam, String registrosOK, String configEntidad) throws SQLException {

		//SET STEPS INSTANCES
		dbWorker oraResp = new dbWorker();

		// SET VARIABLES
		String[] rtaParam = new String[2];
		// "0" es estado es exitoso.
		String estado = "0";

		// Ejecutamos la query de verificacion con el dbWorker
		// rtaParam[0] = ID_ESTADO y 
		rtaParam = oraResp.oraTwoQueryParam(report, verifQueryParam,configEntidad);

		// Si rtaParam[0] es 0 finalizo correctamente.
		if (rtaParam[0].equals(estado)) {
			report.AddLine("PROCESO EXITOSO. ID ESTADO: " + rtaParam[0]);
			System.out.println("##[section] : PROCESO EXITOSO. ID ESTADO: " + rtaParam[0] + "\r\n");
		} else {
			report.AddLineAssertionError("PROCESO FALLIDO!! (X) . ID ESTADO: " + rtaParam[0] );
			System.out.println("##[warning] : PROCESO FALLIDO!! (X) . ID ESTADO: " + rtaParam[0] + "\r\n");
		}

		// Se verifica que la cantidad de registros sea igual a registrosOK
		// rtaParam[1] = OBJETOS_PROCESADOS_OK
		if (rtaParam[1].equals(registrosOK)) {
			report.AddLine("CANTIDAD DE OBJETOS PROCESADOS OK CORRECTA: " + rtaParam[1]);
			System.out.println("##[section] : CANTIDAD DE OBJETOS PROCESADOS OK CORRECTA: " + rtaParam[1] + "\r\n");
		} else {
			report.AddLineAssertionError("ERROR: SE ESPERABAN " + registrosOK + " REGISTROS PERO SE OBTUVIERON " + rtaParam[1] + " REGISTROS");
			System.out.println("##[warning] : ERROR: SE ESPERABAN " + registrosOK + " REGISTROS PERO SE OBTUVIERON " + rtaParam[1] + " REGISTROS\r\n");
		}
	}

	public boolean verifProcesosEjec(Reports report, Repo_Variables repoVar, String[] rtaParam, String cantidadRegistrosEsperados) throws SQLException {
		
		// SET VARIABLES
		// "0" es estado es exitoso.
		String estado = "0";
		String res[] = new String[2];

		// Si rtaParam[0] es 0 finalizo correctamente.
		if (rtaParam[0].equals(estado)) {
			report.AddLine("PROCESO EXITOSO: ID ESTADO: " + rtaParam[0]);
			System.out.println("##[section] : PROCESO EXITOSO: ID ESTADO: " + rtaParam[0] + "\r\n");
			res[0] = "OK";
		} else {
			report.AddLineAssertionError("PROCESO FALLIDO!! (X) . ID ESTADO: " + rtaParam[0] );
			System.out.println("##[warning] : PROCESO FALLIDO!! (X) . ID ESTADO: " + rtaParam[0] + "\r\n");
			res[0] = "KO";
		}

		// Se verifica que la cantidad de registros sea igual a registrosOK
		// rtaParam[1] = OBJETOS_PROCESADOS_OK
		if (rtaParam[1].equals(cantidadRegistrosEsperados)) {
			report.AddLine("VALIDACION CORRECTA: CANTIDAD DE OBJETOS PROCESADOS OK CORRECTA: " + rtaParam[1]);
			System.out.println("##[section] : VALIDACION CORRECTA: CANTIDAD DE OBJETOS PROCESADOS OK CORRECTA: " + rtaParam[1] + "\r\n");
			res[1] = "OK";
		} else {
			report.AddLineAssertionError("ERROR: SE ESPERABAN " + cantidadRegistrosEsperados + " REGISTROS PERO SE OBTUVIERON " + rtaParam[1] + " REGISTROS");
			System.out.println("##[warning] : ERROR: SE ESPERABAN " + cantidadRegistrosEsperados + " REGISTROS PERO SE OBTUVIERON " + rtaParam[1] + " REGISTROS" + "\r\n");
			res[1] = "KO";
		}

		if (res[0].equals("OK") && res[1].equals("OK"))
		{
			return true;
		} else {
			return false;
		}
	}

	public boolean verifCantRegistros(Reports report, Repo_Variables repoVar, String resultadoEsperado, String rtaAut8118) throws SQLException {

		// Se verifica que la cantidad de registros sea igual a los procesados que los esperados
		if (resultadoEsperado.equals(rtaAut8118)) {
			report.AddLine("VALIDACION CORRECTA: CANTIDAD DE REGISTROS EN EL ESQUEMA DE LA ENTIDAD: " + rtaAut8118 + " ES IGUAL A LA CANTIDAD ESPERADA: " + resultadoEsperado);
			System.out.println("##[section] : VALIDACION CORRECTA: CANTIDAD DE REGISTROS EN EL ESQUEMA DE LA ENTIDAD: " + rtaAut8118 + " ES IGUAL A LA CANTIDAD ESPERADA: " + resultadoEsperado + "\r\n");
			return true;
		} else {
			report.AddLineAssertionError("VALIDACION FALLIDA!! REGISTROS ESQUEMA  DE LA ENTIDAD: " + rtaAut8118 + " REGISTROS ESPERADOS: " + resultadoEsperado);
			System.out.println("##[warning] : VALIDACION FALLIDA!! REGISTROS ESQUEMA  DE LA ENTIDAD: " + rtaAut8118 + " REGISTROS ESPERADOS: " + resultadoEsperado + "\r\n");
			return false;
		}
	}

	public boolean validacionCuentasMoneda(Reports report, Repo_Variables repoVar, String verifQuery8118a, String SALDO, String DISPONIBLE, String IMPORTE_COMPRAS, String IMPORTE_PEND_COMPRAS, String configEntidad) throws SQLException {

		//SET STEPS INSTANCES
		dbWorker oraResp = new dbWorker();
		String res[] = new String[4];

		// SET VARIABLES
		// CUENTAS_MONEDA - VERIFICACION DEL SALDO, DISPONIBLE, IMPORTE_COMPRAS Y IMPORTE_PEND_COMPRAS
		String[] rtaAut = new String[4];

		rtaAut = oraResp.oraFourQuery(report, verifQuery8118a, configEntidad);

		if (rtaAut[0].equals(SALDO)) {
			report.AddLine("VALIDACION CORRECTA: SALDO: $" + rtaAut[0] + " es igual al SALDO esperado: $" + SALDO);
			System.out.println("##[section] : VALIDACION CORRECTA: SALDO: $" + rtaAut[0] + " es igual al SALDO esperado: $" + SALDO + "\r\n");
			res[0] = "OK";
		} else {
			report.AddLineAssertionError("ERROR: EL SALDO ESPERADO ES: $" + SALDO + " PERO SE OBTUVO $ " + rtaAut[0]);
			System.out.println("##[warning] : ERROR: EL SALDO ESPERADO ES:  $"+SALDO+" PERO SE OBTUVO $" + rtaAut[0] + "\r\n");
			res[0] = "KO";
		}

		if (rtaAut[1].equals(DISPONIBLE)) {
			report.AddLine("VALIDACION CORRECTA: DISPONIBLE: $" + rtaAut[1] + " es igual al DISPONIBLE esperado: $" + DISPONIBLE);
			System.out.println("##[section] : VALIDACION CORRECTA: DISPONIBLE: $" + rtaAut[1] + " es igual al DISPONIBLE esperado: $" + DISPONIBLE + "\r\n");
			res[1] = "OK";
		} else {
			report.AddLineAssertionError("ERROR: EL DISPONIBLE ESPERADO ES: $" + DISPONIBLE + " PERO SE OBTUVO $" + rtaAut[1]);
			System.out.println("##[warning] : ERROR: EL DISPONIBLE ESPERADO ES: $" + DISPONIBLE + " PERO SE OBTUVO $" + rtaAut[1] + "\r\n");
			res[1] = "KO";
		}

		if (rtaAut[2].equals(IMPORTE_COMPRAS)) {
			report.AddLine("VALIDACION CORRECTA: IMPORTE_COMPRAS: $" + rtaAut[2] + " es igual al IMPORTE_COMPRAS esperado: $" + IMPORTE_COMPRAS);
			System.out.println("##[section] : VALIDACION CORRECTA: IMPORTE_COMPRAS: $" + rtaAut[2] +  " es igual al IMPORTE_COMPRAS esperado: $" + IMPORTE_COMPRAS + "\r\n");
			res[2] = "OK";
		} else {
			report.AddLineAssertionError("ERROR: EL IMPORTE_COMPRAS ESPERADO ES: $" + IMPORTE_COMPRAS + " PERO SE OBTUVO $" + rtaAut[2]);
			System.out.println("##[warning] : ERROR: EL IMPORTE_COMPRAS ESPERADO ES: $" + IMPORTE_COMPRAS + " PERO SE OBTUVO $" + rtaAut[2] + "\r\n");
			res[2] = "KO";
		}

		if (rtaAut[3].equals(IMPORTE_PEND_COMPRAS)) {
			report.AddLine("VALIDACION CORRECTA: IMPORTE_PEND_COMPRAS: $" + rtaAut[3] + " es igual al IMPORTE_PEND_COMPRAS esperado: $" + IMPORTE_PEND_COMPRAS);
			System.out.println("##[section] : VALIDACION CORRECTA: IMPORTE_PEND_COMPRAS: $" + rtaAut[3] + " es igual al IMPORTE_PEND_COMPRAS esperado: $" + IMPORTE_PEND_COMPRAS + "\r\n");
			res[3] = "OK";
		} else {
			report.AddLineAssertionError("ERROR: EL IMPORTE_PEND_COMPRAS ESPERADO ES : $" + IMPORTE_PEND_COMPRAS + " PERO SE OBTUVO $" + rtaAut[3]);
			System.out.println("##[warning] : ERROR: EL IMPORTE_PEND_COMPRAS ESPERADO ES : $" + IMPORTE_PEND_COMPRAS + " PERO SE OBTUVO $" + rtaAut[3] + "\r\n");
			res[3] = "KO";
		}
		if (res[0].equals("OK") && res[1].equals("OK") && res[2].equals("OK") && res[3].equals("OK"))
		{
			return true;
		} else {
			return false;
		}

	}

	public boolean validacionConsumos(Reports report, String rtaAut8118c) {

		// CONSUMOS
		if (rtaAut8118c.equals("1")) {
			report.AddLine("VALIDACION DE CONSUMOS CORRECTA: REGISTROS: " + rtaAut8118c + ". SE CREO UN REGISTRO EN LAS TABLAS CONSUMOS, CONSUMOS_CUOTAS Y CONSUMOS_DATOS_ADICIONALES CORRECTAMENTE");
			System.out.println("##[section] : VALIDACION DE CONSUMOS CORRECTA: REGISTROS: " + rtaAut8118c + ". SE CREO UN REGISTRO EN LAS TABLAS CONSUMOS, CONSUMOS_CUOTAS Y CONSUMOS_DATOS_ADICIONALES CORRECTAMENTE\r\n");
			return true;
		} else {
			report.AddLineAssertionError("VALIDACION FALLIDA (X) NO HAY REGISTROS EN CONSUMOS");
			System.out.println("##[warning] : VALIDACION FALLIDA (X) NO HAY REGISTROS EN CONSUMOS\r\n");
			return false;
		}
	}

}
