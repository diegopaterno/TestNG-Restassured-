package Tools;

import java.io.IOException;
import java.sql.SQLException;

import CentaJava.Core.Reports;
import Pasos.Generales.PrePostCondi;

public class opiWorker {
	
	//Metodos para utilizar color en la consola
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	
	//PRECONDICIONES
	public void preCondicion(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CUENTA, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE = " + dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraDelete(report, "DELETE COTIZACIONES", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraInsert(report, "Insert into COTIZACIONES \r\n"
				+ "(ID_ENTIDAD,FECHA,ID_MONEDA_ORIGEN,ID_MONEDA_DESTINO,COTIZACION_COMPRA,COTIZACION_VENDE,ID_ESTADO_TIPO,ID_ESTADO,ID_AUDITORIA,ROW_VERSION,ROW_FECHA,ID_ESTADO_TIPO_CONFIRM,ID_ESTADO_CONFIRM) \r\n"
				+ "values ('8118',TRUNC (SYSDATE),'840','32',94.44,95.44,'4','0','10184','0',to_date('01/01/0001 00:00:00','DD/MM/RRRR HH24:MI:SS'),'40','1')", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		// - COMENTADO HASTA TENER ACCESO AL SCHEMA - 
		//Operacion 2 - ESQUEMA PARAM
		rta = oraWorker.oraDeleteParam(report, "DELETE COTIZACIONES", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraInsertParam(report, "Insert into COTIZACIONES \r\n"
				+ "(FECHA,ID_MONEDA_ORIGEN,ID_MONEDA_DESTINO,COTIZACION_COMPRA,COTIZACION_VENDE,ID_ESTADO_TIPO,ID_ESTADO,ID_AUDITORIA,ROW_VERSION,ROW_FECHA,ID_ESTADO_TIPO_CONFIRM,ID_ESTADO_CONFIRM) \r\n"
				+ "values (TRUNC (SYSDATE) ,'840','32',74.55,74.85,'4','0',null,null,null,'40',null)", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraInsertParam(report, "Insert into COTIZACIONES \r\n"
				+ "(FECHA,ID_MONEDA_ORIGEN,ID_MONEDA_DESTINO,COTIZACION_COMPRA,COTIZACION_VENDE,ID_ESTADO_TIPO,ID_ESTADO,ID_AUDITORIA,ROW_VERSION,ROW_FECHA,ID_ESTADO_TIPO_CONFIRM,ID_ESTADO_CONFIRM) \r\n"
				+ "values (TRUNC (SYSDATE-1) ,'840','32',94.44,95.44,'4','0',null,null,null,'40',null)", configEntidad);
		ppCondi.preCondicionBD(report, rta);
		 
	}

	public void preCondicion_CM_Tarjetas(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CTA, String ID_ESTADO, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE = " + dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = " + ID_ESTADO + " WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.preCondicionBD(report, rta);
	}
	
	public void postCondicion_CM_Tarjetas(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CTA, String ID_ESTADO, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE = " + dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);

		rta = oraWorker.oraUpdate(report, "UPDATE TARJETAS SET ID_ESTADO = " + ID_ESTADO + " WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);
	}

	public void preCondicionDev(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CUENTA, String TID, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE =  "+ dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraUpdate(report, "UPDATE AUTORIZACION SET FECHA_RELACION = TRUNC(SYSDATE),MONTO_ACUM_DEVOLUCIONES = 0 WHERE TID = '" + TID + "'", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraDelete(report, "DELETE COTIZACIONES", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraInsert(report, "Insert into COTIZACIONES \r\n"
				+ "(ID_ENTIDAD,FECHA,ID_MONEDA_ORIGEN,ID_MONEDA_DESTINO,COTIZACION_COMPRA,COTIZACION_VENDE,ID_ESTADO_TIPO,ID_ESTADO,ID_AUDITORIA,ROW_VERSION,ROW_FECHA,ID_ESTADO_TIPO_CONFIRM,ID_ESTADO_CONFIRM) \r\n"
				+ "values ('8118',TRUNC (SYSDATE),'840','32',94.44,95.44,'4','0','10184','0',to_date('01/01/0001 00:00:00','DD/MM/RRRR HH24:MI:SS'),'40','1')", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		//Operacion 2 - ESQUEMA PARAM
		rta = oraWorker.oraDeleteParam(report, "DELETE COTIZACIONES", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraInsertParam(report, "Insert into COTIZACIONES \r\n"
				+ "(FECHA,ID_MONEDA_ORIGEN,ID_MONEDA_DESTINO,COTIZACION_COMPRA,COTIZACION_VENDE,ID_ESTADO_TIPO,ID_ESTADO,ID_AUDITORIA,ROW_VERSION,ROW_FECHA,ID_ESTADO_TIPO_CONFIRM,ID_ESTADO_CONFIRM) \r\n"
				+ "values \r\n"
				+ "(TRUNC (SYSDATE) ,'840','32',74.55,74.85,'4','0',null,null,null,'40',null)", configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraInsertParam(report, "Insert into COTIZACIONES \r\n"
				+ "(FECHA,ID_MONEDA_ORIGEN,ID_MONEDA_DESTINO,COTIZACION_COMPRA,COTIZACION_VENDE,ID_ESTADO_TIPO,ID_ESTADO,ID_AUDITORIA,ROW_VERSION,ROW_FECHA,ID_ESTADO_TIPO_CONFIRM,ID_ESTADO_CONFIRM) \r\n"
				+ "values \r\n"
				+ "(TRUNC (SYSDATE-1) ,'840','32',94.44,95.44,'4','0',null,null,null,'40',null)", configEntidad);
		ppCondi.preCondicionBD(report, rta);
		 
	}
	
	public void postCondicionDev(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CUENTA, String TID, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE =  "+ dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);

		rta = oraWorker.oraUpdate(report, "UPDATE AUTORIZACION SET FECHA_RELACION = TRUNC(SYSDATE),MONTO_ACUM_DEVOLUCIONES = 0 WHERE TID = '" + TID + "'", configEntidad);
		ppCondi.postCondicionBD(report, rta);
		 
	}

	public void preCondicionCMyVto(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CTA, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE = " + dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.preCondicionBD(report, rta);

		rta = oraWorker.oraUpdate(report, "UPDATE TARJETAS SET VENCIMIENTO = TO_DATE ('31/10/2020','DD/MM/YYYY') WHERE ID_CUENTA =" + ID_CTA, configEntidad);
		ppCondi.preCondicionBD(report, rta);
	}
	
	public void postCondicionVto(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CTA, String configEntidad, String fechaVencimientoRollback) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE = " + dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);

		rta = oraWorker.oraUpdate(report, "UPDATE TARJETAS SET VENCIMIENTO = TO_DATE ('" + fechaVencimientoRollback + "') WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);
	}

	public void preCondicionCM(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CTA, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE = " + dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.preCondicionBD(report, rta);
	}
	
	public void postCondicionCM(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CTA, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 10000, DISPONIBLE = " + dispInicial + ", DISPONIBLE_ADELANTO = 10000,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = " + pendCompras + ", \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = " + ajustePend + ",\r\n"
				+ "IMPORTE_PAGOS = 10000\r\n"
				+ "WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.postCondicionBD(report, rta);
	}
	
	public void preCondicionCM_en_cero(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String ID_CTA, String configEntidad) {
		//Variables
		int rta;

		//Operacion 1 - ESQUEMA 8118
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS_MONEDA\r\n"
				+ "SET SALDO = 0, DISPONIBLE = 0, DISPONIBLE_ADELANTO = 0,\r\n"
				+ "IMPORTE_COMPRAS = 0, IMPORTE_ADELANTOS = 0, IMPORTE_PEND_COMPRAS = 0, \r\n"
				+ "IMPORTE_PEND_ADELANTOS = 0, IMPORTE_AJUSTES = 0, IMPORTE_AJUSTES_PEND = 0,\r\n"
				+ "IMPORTE_PAGOS = 0\r\n"
				+ "WHERE ID_CUENTA = " + ID_CTA, configEntidad);
		ppCondi.preCondicionBD(report, rta);
	}
	
	public boolean preCondicionParaReversosOPI(Reports report, String XML, String dispInicial, String pendCompras, String ajustePend,String ID_CUENTA, String configEntidad) {
		
		String res;
		String de39 = "00";
		boolean result;

		//Instancias
		sshWorker opiCmd = new sshWorker();
		dbWorker oraResp = new dbWorker();
		PrePostCondi ppCondi = new PrePostCondi();
		opiWorker opiAcciones = new opiWorker();
		
		report.AddLine("--- Ejecutando autorizacion para precondicion del Reverso ---");
		System.out.println("--- Ejecutando autorizacion para precondicion del Reverso ---\r\n");
		
		opiAcciones.preCondicion(oraResp, ppCondi, report, dispInicial, pendCompras, ajustePend, ID_CUENTA, configEntidad);
		
		//EJECUCION OPI
		res = opiCmd.sshSendCmd(report, XML, configEntidad);

		if (res.equals(de39))
		{
			report.AddLine("Ejecucion Correcta:<br>DE39:" + res);
			System.out.println("##[section] : Ejecucion Correcta:\r\nDE39:" + res + "\r\n");
			result = true;
		} else {
			report.AddLineAssertionError("FAIL<br>DE39:" + res + " Se esperaba: " + de39);
			System.out.println("##[warning] : FAIL:\r\nDE39:" + res + " Se esperaba: " + de39 + "\r\n");
			result = false;
		}
		return result;
	}
	
	public boolean preCondicionParaReversosOPIDevoluciones(Reports report, String XML, String dispInicial, String pendCompras, String ajustePend,String ID_CUENTA,String TID, String configEntidad) {
		
		String res;
		String de39 = "00";
		boolean result;

		//Instancias
		sshWorker opiCmd = new sshWorker();
		dbWorker oraResp = new dbWorker();
		PrePostCondi ppCondi = new PrePostCondi();
		opiWorker opiAcciones = new opiWorker();
		
		report.AddLine("--- Ejecutando autorizacion para precondicion del Reverso ---");
		System.out.println("--- Ejecutando autorizacion para precondicion del Reverso ---\r\n");
		
		opiAcciones.preCondicionDev(oraResp, ppCondi, report, dispInicial, pendCompras, ajustePend, ID_CUENTA, TID, configEntidad);
		
		//EJECUCION OPI
		res = opiCmd.sshSendCmd(report, XML, configEntidad);

		if (res.equals(de39))
		{
			report.AddLine("Ejecucion Correcta:<br>DE39:" + res);
			System.out.println("##[section] : Ejecucion Correcta:\r\nDE39:" + res + "\r\n");
			result = true;
		} else {
			report.AddLineAssertionError("FAIL<br>DE39:" + res + " Se esperaba: " + de39);
			System.out.println("##[warning] : FAIL:\r\nDE39:" + res + " Se esperaba: " + de39 + "\r\n");
			result = false;
		}
		return result;
	}

	//VALIDACIONES
	public boolean validacionBD_CM_Autorizacion(Reports report, dbWorker oraResp, String ID_ESTADO_AUTORIZACION, String disponible, String impPendComp, String impAjustePend, String ID_CUENTA, String configEntidad) throws SQLException, IOException {
		//Variables
		String resCant1;
		String[] resCant2 = new String[3];
		String[] res = new String[4];

		//Operacion 1
		resCant1 = oraResp.oraOneQuery(report, "SELECT ID_ESTADO FROM AUTORIZACION WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_AUTORIZACION = (SELECT MAX(ID_AUTORIZACION)FROM AUTORIZACION WHERE ID_CUENTA = " + ID_CUENTA + ")", configEntidad);

		if(!resCant1.equals(ID_ESTADO_AUTORIZACION)) {
			report.AddLineAssertionError("Error.<br>El ID_ESTADO es: " + resCant1 + " Se esperaba ID_ESTADO: " + ID_ESTADO_AUTORIZACION );
			System.out.println("##[warning] : Error.\r\nEl ID_ESTADO es: " + resCant1 + " Se esperaba ID_ESTADO: " + ID_ESTADO_AUTORIZACION + "\r\n");
			
			res[0] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El ID_ESTADO esperado es: " + ID_ESTADO_AUTORIZACION + " y es igual al de la base de datos: " + resCant1);
			System.out.println("##[section] : Validacion exitosa:\r\nEl ID_ESTADO esperado es: " + ID_ESTADO_AUTORIZACION + " y es igual al de la base de datos: " + resCant1 + "\r\n");
			res[0] = "OK";
		}

		//Operacion 2
		resCant2 = oraResp.oraThreeQuery(report, "SELECT DISPONIBLE, IMPORTE_PEND_COMPRAS, IMPORTE_AJUSTES_PEND\r\n"
				+ "FROM CUENTAS_MONEDA \r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);

		if(!resCant2[0].equals(disponible)) {
			report.AddLineAssertionError("Error.<br>El DISPONIBLE es: " + resCant2[0] + " Se esperaba: " + disponible);
			System.out.println("##[warning] : Error.\r\nEl DISPONIBLE es: " + resCant2[0] + " Se esperaba: " + disponible + "\r\n");
			res[1] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El DISPONIBLE esperado es: " + disponible + " y es igual al de la base de datos: " + resCant2[0]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DISPONIBLE esperado es: " + disponible + " y es igual al de la base de datos: " + resCant2[0] + "\r\n");
			res[1] = "OK";
		}

		if(!resCant2[1].equals(impPendComp)) {
			report.AddLineAssertionError("Error.<br>El IMPORTE PENDIENTE DE COMPRAS es: " + resCant2[1] + " Se esperaba: " + impPendComp);
			System.out.println("##[warning] : Error.\r\nEl IMPORTE PENDIENTE DE COMPRAS es: " + resCant2[1] + " Se esperaba: " + impPendComp + "\r\n");
			res[2] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El IMPORTE PENDIENTE DE COMPRAS esperado es: " + impPendComp + " y es igual al de la base de datos: " + resCant2[1]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl IMPORTE PENDIENTE DE COMPRAS esperado es: " + impPendComp + " y es igual al de la base de datos: " + resCant2[1] + "\r\n");
			res[2] = "OK";
		}

		if(!resCant2[2].equals(impAjustePend)) {
			report.AddLineAssertionError("Error.<br>El IMPORTE AJUSTES PENDIENTE es: " + resCant2[2] + " Se esperaba: " + impAjustePend);
			System.out.println("##[warning] : Error.\r\nEl IMPORTE AJUSTES PENDIENTE es: " + resCant2[2] + " Se esperaba: " + impAjustePend + "\r\n");
			res[3] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El IMPORTE AJUSTES PENDIENTE esperado es: " + impAjustePend + " y es igual al de la base de datos: " + resCant2[2]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl IMPORTE AJUSTES PENDIENTE esperado es: " + impAjustePend + " y es igual al de la base de datos: " + resCant2[2] + "\r\n");
			res[3] = "OK";
		}

		//Retorno Validacion
		if (res[0].equals("OK") && res[1].equals("OK") && res[2].equals("OK") && res[3].equals("OK"))
		{
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validacionBD_Id_Estado_Autorizacion(Reports report, dbWorker oraResp, String ID_ESTADO, String ID_CTA, String configEntidad) throws SQLException, IOException {
		//Variables
		String resCant1;
		String res;

		//Operacion 1
		resCant1 = oraResp.oraOneQuery(report, "SELECT ID_ESTADO FROM AUTORIZACION WHERE ID_CUENTA = " + ID_CTA + " AND ID_AUTORIZACION = (SELECT MAX(ID_AUTORIZACION)FROM AUTORIZACION WHERE ID_CUENTA = " + ID_CTA + ")", configEntidad);

		if(!resCant1.equals(ID_ESTADO)) {
			report.AddLineAssertionError("Error.<br>El ID_ESTADO es: " + resCant1 + " Se esperaba ID_ESTADO: " + ID_ESTADO );
			System.out.println("##[warning] : Error.\r\nEl ID_ESTADO es: " + resCant1 + " Se esperaba ID_ESTADO: " + ID_ESTADO + "\r\n");
			res = "KO";
		} else {
			report.AddLine("Validacion exitosa<br>El ID_ESTADO es: " + ID_ESTADO + " y es igual al de la base de datos: " + resCant1);
			System.out.println("##[section] : Validacion exitosa\r\nEl ID_ESTADO es: " + ID_ESTADO + " y es igual al de la base de datos: " + resCant1 + "\r\n");
			res = "OK";
		}

		//Retorno Validacion
		if (res.equals("OK"))
		{
			return true;
		} else {
			return false;
		}
	}

	public boolean validacionAdelantoBD(Reports report, dbWorker oraResp, String ID_ESTADO, String disponible, String dispAdelanto, String impPendAdelanto, String impAjustePend, String ID_CUENTA, String configEntidad) throws SQLException, IOException {
		String resCant1;
		String[] resCant4 = new String[4];
		String[] res = new String[5];
		resCant1 = oraResp.oraOneQuery(report, "SELECT ID_ESTADO FROM AUTORIZACION WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_AUTORIZACION = (SELECT MAX(ID_AUTORIZACION)FROM AUTORIZACION WHERE ID_CUENTA = " + ID_CUENTA + ")", configEntidad);

		if(!resCant1.equals(ID_ESTADO)) {
			report.AddLineAssertionError("Error.<br>El ID_ESTADO es: " + resCant1 + " Se esperaba ID_ESTADO: " + ID_ESTADO );
			System.out.println("##[warning] : Error.\r\nEl ID_ESTADO es: " + resCant1 + " Se esperaba ID_ESTADO: " + ID_ESTADO + "\r\n");
			res[0] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El ID_ESTADO es: " + ID_ESTADO + " y es igual al de la base de datos: " + resCant1);
			System.out.println("##[section] : Validacion exitosa:\r\nEl ID_ESTADO es: " + ID_ESTADO + " y es igual al de la base de datos: " + resCant1 + "\r\n");
			res[0] = "OK";
		}

		resCant4 = oraResp.oraFourQuery(report, "SELECT DISPONIBLE, DISPONIBLE_ADELANTO, IMPORTE_PEND_ADELANTOS, IMPORTE_AJUSTES_PEND\r\n"
				+ "FROM CUENTAS_MONEDA \r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		
		if(!resCant4[0].equals(disponible)) {
			report.AddLineAssertionError("Error.<br>El DISPONIBLE es: " + resCant4[0] + " Se esperaba: " + disponible);
			System.out.println("##[warning] : Error.\r\nEl DISPONIBLE es: " + resCant4[0] + " Se esperaba: " + disponible + "\r\n");
			res[1] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El DISPONIBLE esperado es: " + disponible + " y es igual al de la base de datos: " + resCant4[0]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DISPONIBLE esperado es: " + disponible + " y es igual al de la base de datos: " + resCant4[0] + "\r\n");
			res[1] = "OK";
		}
		
		if(!resCant4[1].equals(dispAdelanto)) {
			report.AddLineAssertionError("Error.<br>El DISPONIBLE ADELANTO es: " + resCant4[1] + " Se esperaba: " + dispAdelanto);
			System.out.println("##[warning] : Error.\r\nEl DISPONIBLE ADELANTO es: " + resCant4[1] + " Se esperaba: " + dispAdelanto + "\r\n");
			res[2] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El DISPONIBLE ADELANTO esperado es: " + dispAdelanto + " y es igual al de la base de datos: " + resCant4[1]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DISPONIBLE ADELANTO esperado es: " + dispAdelanto + " y es igual al de la base de datos: " + resCant4[1] + "\r\n");
			res[2] = "OK";
		}

		if(!resCant4[2].equals(impPendAdelanto)) {
			report.AddLineAssertionError("Error.<br>El IMPORTE PENDIENTE ADELANTO es: " + resCant4[2] + " Se esperaba: " + impPendAdelanto);
			System.out.println("##[warning] : Error.\r\nEl IMPORTE PENDIENTE ADELANTO es: " + resCant4[2] + " Se esperaba: " + impPendAdelanto + "\r\n");
			res[3] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El IMPORTE PENDIENTE ADELANTO esperado es: " + impPendAdelanto + " y es igual al de la base de datos: " + resCant4[2]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl IMPORTE PENDIENTE ADELANTO esperado es: " + impPendAdelanto + " y es igual al de la base de datos: " + resCant4[2] + "\r\n");
			res[3] = "OK";
		}
		
		if(!resCant4[3].equals(impAjustePend)) {
			report.AddLineAssertionError("Error.<br>El IMPORTE AJUSTES PENDIENTE es: " + resCant4[3] + " Se esperaba: " + impAjustePend);
			System.out.println("##[warning] : Error.\r\nEl IMPORTE AJUSTES PENDIENTE es: " + resCant4[3] + " Se esperaba: " + impAjustePend + "\r\n");
			res[4] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El IMPORTE AJUSTES PENDIENTE esperado es: " + impAjustePend + " y es igual al de la base de datos: " + resCant4[3]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl IMPORTE AJUSTES PENDIENTE esperado es: " + impAjustePend + " y es igual al de la base de datos: " + resCant4[3] + "\r\n");
			res[4] = "OK";
		}
		
		if (res[0].equals("OK") && res[1].equals("OK") && res[2].equals("OK") && res[3].equals("OK") && res[4].equals("OK"))
		{
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validacionConsultaSaldoLocal(Reports report, dbWorker oraResp, String ID_ESTADO, String ID_CUENTA, String NRO_TARJETA, String configEntidad) throws SQLException, IOException {
		String resCant1;
		String[] resCant2 = new String[2];
		String[] resCant2CM = new String[4];
		String[] res = new String[5];
		
		resCant1 = oraResp.oraOneQuery(report, "SELECT ID_ESTADO FROM AUTORIZACION_CONSULTA WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_CONSULTA = (SELECT MAX(ID_CONSULTA)FROM AUTORIZACION_CONSULTA WHERE ID_CUENTA = " + ID_CUENTA + ")", configEntidad);

		if(!resCant1.equals(ID_ESTADO)) {
			report.AddLineAssertionError("Error:<br>El ID_ESTADO de la AUTORIZACION es: " + resCant1 + " Se esperaba ID_ESTADO: " + ID_ESTADO );
			System.out.println("##[warning] : Error:\r\nEl ID_ESTADO de la AUTORIZACION es: " + resCant1 + " Se esperaba ID_ESTADO: " + ID_ESTADO + "\r\n");
			res[0] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El ID_ESTADO de la AUTORIZACION es: " + resCant1 + " y es igual al esperado ID_ESTADO: " + ID_ESTADO);
			System.out.println("##[section] : Validacion exitosa:\r\nEl ID_ESTADO de la AUTORIZACION es: " + resCant1 + " y es igual al esperado ID_ESTADO: " + ID_ESTADO + "\r\n");
			res[0] = "OK";
		}
		
		resCant2 = oraResp.oraTwoQuery(report, "SELECT SUBSTR (OUT_IMPORTES_ADICIONALES_54,15,6)DISPONIBLE , SUBSTR (OUT_IMPORTES_ADICIONALES_54,7,2)ID_MONEDA\r\n"
				+ "FROM AUTORIZACION_EMISOR_LOG A \r\n"
				+ "WHERE IN_NRO_TARJETA = '" + NRO_TARJETA + "'\r\n"
				+ "AND ID_AUTORIZACION_EMISOR = (SELECT MAX(ID_AUTORIZACION_EMISOR) FROM AUTORIZACION_EMISOR_LOG WHERE IN_NRO_TARJETA = '" + NRO_TARJETA + "')", configEntidad);
		
		//Reemplazo 0 por nada para igualar el dato con el campo ID_MONEDA de CUENTAS_MONEDA
		resCant2[1] = resCant2[1].replace("0", "");

		resCant2CM = oraResp.oraTwoQuery(report, "SELECT DISPONIBLE, ID_MONEDA\r\n"
				+ "FROM CUENTAS_MONEDA \r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA, configEntidad);
		
		//Reemplazo . por nada para igualarlar con el dato de ID_MONEDA del campo OUT_IMPORTES_ADICIONALES_54,14,7 de AUTORIZACION_EMISOR_LOG
		resCant2CM[0] = resCant2CM[0].replace(".", "");
		
		if(!resCant2CM[0].equals(resCant2[0])) {
			report.AddLineAssertionError("Error:<br>El DISPONIBLE del response DE54 es: " + resCant2[0] + " Se esperaba: " + resCant2CM[0]);
			System.out.println("##[warning] : Error:\r\nEl DISPONIBLE del response DE54 es: " + resCant2[0] + " Se esperaba: " + resCant2CM[0] + "\r\n");
			res[1] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El DISPONIBLE en CUENTAS_MONEDA es: " + resCant2CM[0] + " y es igual al del response DE54: " + resCant2[0]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DISPONIBLE en CUENTAS_MONEDA es: " + resCant2CM[0] + " y es igual al del response DE54: " + resCant2[0] + "\r\n");
			res[1] = "OK";
		}
		
		if(!resCant2CM[1].equals(resCant2[1])) {
			report.AddLineAssertionError("Error:<br>El ID_MONEDA del response DE54 es: " + resCant2[1] + " Se esperaba: " + resCant2CM[1]);
			System.out.println("##[warning] : Error:\r\nEl ID_MONEDA del response DE54 es: " + resCant2[1] + " Se esperaba: " + resCant2CM[1] + "\r\n");
			res[2] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El ID_MONEDA en CUENTAS_MONEDA es: " + resCant2CM[1] + " y es igual al del response DE54: " + resCant2[1]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl ID_MONEDA en CUENTAS_MONEDA es: " + resCant2CM[1] + " y es igual al del response DE54: " + resCant2[1] + "\r\n");
			res[2] = "OK";
		}
		
		if (res[0].equals("OK") && res[1].equals("OK") && res[2].equals("OK"))
		{
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validacionBD_CM_Pagos(Reports report, dbWorker oraResp, String saldo, String disponible, String disponibleAdelanto, String importePagos, String ID_CTA, String configEntidad) throws SQLException {
		//Variables
		String[] resCant4 = new String[3];
		String[] res = new String[4];

		//Operacion 1
		resCant4 = oraResp.oraFourQuery(report, "SELECT SALDO, DISPONIBLE, DISPONIBLE_ADELANTO, IMPORTE_PAGOS\r\n"
				+ "FROM CUENTAS_MONEDA\r\n"
				+ "WHERE ID_CUENTA = " + ID_CTA, configEntidad);

		if(!resCant4[0].equals(saldo)) {
			report.AddLineAssertionError("Error:<br>El SALDO es: " + resCant4[0] + " Se esperaba: " + saldo);
			System.out.println("##[warning] : Error:\r\nEl SALDO es: " + resCant4[0] + " Se esperaba: " + saldo  + "\r\n");
			res[0] = "KO";
		} else {
			report.AddLine("Validacion exitosa<br>El SALDO es: " + saldo + " y es igual al de la base de datos: " + resCant4[0]);
			System.out.println("##[section] : Validacion exitosa\r\nEl SALDO es: " + saldo + " y es igual al de la base de datos: " + resCant4[0]  + "\r\n");
			res[0] = "OK";
		}

		if(!resCant4[1].equals(disponible)) {
			report.AddLineAssertionError("Error:<br>El DISPONIBLE es: " + resCant4[1] + " Se esperaba: " + disponible);
			System.out.println("##[warning] : Error:\r\nEl DISPONIBLE es: " + resCant4[1] + " Se esperaba: " + disponible + "\r\n");
			res[1] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El DISPONIBLE es: " + disponible + " y es igual al de la base de datos: " + resCant4[1]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DISPONIBLE es: " + disponible + " y es igual al de la base de datos: " + resCant4[1]  + "\r\n");
			res[1] = "OK";
		}

		if(!resCant4[2].equals(disponibleAdelanto)) {
			report.AddLineAssertionError("Error:<br>El DISPONIBLE_ADELANTO es: " + resCant4[2] + " Se esperaba: " + disponibleAdelanto);
			System.out.println("##[warning] : Error:\r\nEl DISPONIBLE_ADELANTO es: " + resCant4[2] + " Se esperaba: " + disponibleAdelanto  + "\r\n");
			res[2] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El DISPONIBLE_ADELANTO es: " + disponibleAdelanto + " y es igual al de la base de datos: " + resCant4[2]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DISPONIBLE_ADELANTO es: " + disponibleAdelanto + " y es igual al de la base de datos: " + resCant4[2]  + "\r\n");
			res[2] = "OK";
		}
		
		if(!resCant4[3].equals(importePagos)) {
			report.AddLineAssertionError("Error:<br>El IMPORTE_PAGOS es: " + resCant4[3] + " Se esperaba: " + importePagos);
			System.out.println("##[warning] : Error:\r\nEl IMPORTE_PAGOS es: " + resCant4[3] + " Se esperaba: " + importePagos  + "\r\n");
			res[3] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El IMPORTE_PAGOS es: " + importePagos + " y es igual al de la base de datos: " + resCant4[3]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl IMPORTE_PAGOS es: " + importePagos + " y es igual al de la base de datos: " + resCant4[3] + "\r\n");
			res[3] = "OK";
		}
		
		//Retorno Validacion
		if (res[0].equals("OK") && res[1].equals("OK") && res[2].equals("OK") && res[3].equals("OK"))
		{
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validacionAjusteSocios(Reports report, String[] rtaQuery, String datoAjuste) {
		String[] res = new String[2];
		
		if(!rtaQuery[0].equals(datoAjuste)) {
			report.AddLineAssertionError("Error:<br>El ID_AJUSTE es: " + rtaQuery[0] + " Se esperaba ID_AJUSTE: " + datoAjuste);
			System.out.println("##[warning] : Error:\r\nEl ID_AJUSTE es: " + rtaQuery[0] + " Se esperaba ID_AJUSTE: " + datoAjuste + "\r\n");
			res[0] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El ID_AJUSTE es: " + datoAjuste + " y es igual al de la base de datos: " + rtaQuery[0]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl ID_AJUSTE es: " + datoAjuste + " y es igual al de la base de datos: " + rtaQuery[0] + "\r\n");
			res[0] = "OK";
		}
		
		if(!rtaQuery[1].equals("2")) {
			report.AddLineAssertionError("Error:<br>El ID_ESTADO es: " + rtaQuery[1] + " Se esperaba ID_ESTADO: 2");
			System.out.println("##[warning] : Error:\r\nEl ID_ESTADO es: " + rtaQuery[1] + " Se esperaba ID_ESTADO: 2" + "\r\n");
			res[1] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El ID_ESTADO es: 2 y es igual al de la base de datos: " + rtaQuery[1]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl ID_ESTADO es: 2 y es igual al de la base de datos: " + rtaQuery[1] + "\r\n");
			res[1] = "OK";
		}
		
		if (res[0].equals("OK") && res[1].equals("OK"))
		{
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validacionCM_Saldo_Disponible_Importe_Ajustado(Reports report, String[] rtaQuery, String saldo, String disponible, String importeAjuste) {
		String[] res = new String[3];
		
		if(!rtaQuery[0].equals(saldo)) {
			report.AddLineAssertionError("Error:<br>El SALDO es: " + rtaQuery[0] + " Se esperaba: " + saldo);
			System.out.println("##[warning] : Error:\r\nEl SALDO es: " + rtaQuery[0] + " Se esperaba: " + saldo + "\r\n");
			res[0] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El SALDO es: " + saldo + " y es igual al de la base de datos: " + rtaQuery[0]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl SALDO es: " + saldo + " y es igual al de la base de datos: " + rtaQuery[0] + "\r\n");
			res[0] = "OK";
		}
		
		if(!rtaQuery[1].equals(disponible)) {
			report.AddLineAssertionError("Error:<br>El DISPONIBLE es: " + rtaQuery[1] + " Se esperaba DISPONIBLE: " + disponible);
			System.out.println("##[warning] : Error:\r\nEl DISPONIBLE es: " + rtaQuery[1] + " Se esperaba DISPONIBLE: " + disponible + "\r\n");
			res[1] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El DISPONIBLE es: " + disponible + " y es igual al de la base de datos: " + rtaQuery[1]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl DISPONIBLE es: " + disponible + " y es igual al de la base de datos: " + rtaQuery[1] + "\r\n");
			res[1] = "OK";
		}
		
		if(!rtaQuery[2].equals(importeAjuste)) {
			report.AddLineAssertionError("Error:<br>El IMPORTE AJUSTADO es: " + rtaQuery[2] + " Se esperaba IMPORTE AJUSTADO: " + importeAjuste);
			System.out.println("##[warning] : Error:\r\nEl IMPORTE AJUSTADO es: " + rtaQuery[2] + " Se esperaba IMPORTE AJUSTADO: " + importeAjuste + "\r\n");
			res[2] = "KO";
		} else {
			report.AddLine("Validacion exitosa:<br>El IMPORTE AJUSTADO es: " + importeAjuste + " y es igual al de la base de datos: " + rtaQuery[2]);
			System.out.println("##[section] : Validacion exitosa:\r\nEl IMPORTE AJUSTADO es: " + importeAjuste + " y es igual al de la base de datos: " + rtaQuery[2] + "\r\n");
			res[2] = "OK";
		}
		
		if (res[0].equals("OK") && res[1].equals("OK") && res[2].equals("OK"))
		{
			return true;
		} else {
			return false;
		}
	}
	
	public void setDomicilioLegal(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CUENTA, String configEntidad, String ID_PROVINCIA, String ID_CODIGO_POSTAL) {
		
		//Variables
		int rta;
		
		report.AddLine("Se cambia el domicilio legal de la cuenta " + ID_CUENTA + " seteando el ID_PROVINCIA " + ID_PROVINCIA + " y el ID_CODIGO_POSTAL " + ID_CODIGO_POSTAL);
		System.out.println("Se cambia el domicilio legal de la cuenta " + ID_CUENTA + " seteando el ID_PROVINCIA " + ID_PROVINCIA + " y el ID_CODIGO_POSTAL " + ID_CODIGO_POSTAL + "\r\n");
		
		rta = oraWorker.oraUpdate(report, "UPDATE DOMICILIOS\r\n"
				+ "SET ID_PROVINCIA = " + ID_PROVINCIA + ", ID_CODIGO_POSTAL = " + ID_CODIGO_POSTAL + "\r\n"
				+ "WHERE ID_DOMICILIO = (SELECT ID_DOMICILIO_LEGAL FROM PERSONAS WHERE ID_PERSONA = (SELECT ID_PERSONA FROM SOCIOS WHERE ID_CUENTA = " + ID_CUENTA + "))", configEntidad);
		ppCondi.preCondicionBD(report, rta);
		 
	}
	
	public void setPosicionImpositiva(dbWorker oraWorker, PrePostCondi ppCondi, Reports report, String dispInicial, String pendCompras, String ajustePend, String ID_CUENTA, String configEntidad, String ID_POSICION_IMPOSITIVA) {
		
		//Variables
		int rta;
		
		report.AddLine("Se cambia la posicion impositiva de la cuenta " + ID_CUENTA + " seteando el ID_POSICION_IMPOSITIVA " + ID_POSICION_IMPOSITIVA);
		System.out.println("Se cambia la posicion impositiva de la cuenta " + ID_CUENTA + " seteando el ID_POSICION_IMPOSITIVA " + ID_POSICION_IMPOSITIVA + "\r\n");
		
		rta = oraWorker.oraUpdate(report, "UPDATE CUENTAS\r\n"
				+ "SET ID_POSICION_IMPOSITIVA = " + ID_POSICION_IMPOSITIVA + "\r\n"
				+ "WHERE ID_CUENTA = " + ID_CUENTA + " AND ID_PAIS = 32", configEntidad);
		ppCondi.preCondicionBD(report, rta);
		 
	}

}
