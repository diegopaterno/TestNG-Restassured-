package Tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import CentaJava.Core.Reports;
import io.restassured.path.json.JsonPath;
import oracle.jdbc.OracleResultSetMetaData;

public class dbWorker {
	
	
	private String user; 
	private String pass; 
	private String host;
	private String serviceName;
	private String port;
	private String entidad;
	
	// variables a utilizar en otras clases
	private String codAutoEntidad;
    private String comercioDescripcion;
    private String modoIngreso;
    private int idAutorizacionEmisor;
    private int idCodMovimiento;
    private int idEstado;
    private int idCuenta;
    private String fechaAutorizacion;
    private double importe;
    private String nroTarjeta;
    
    //constructor del metodo
    

    

    // Getters para acceder a los valores
    public String getCodAutoEntidad() {
        return codAutoEntidad;
    }

    public String getComercioDescripcion() {
        return comercioDescripcion;
    }

    public String getModoIngreso() {
        return modoIngreso;
    }

    public int getIdAutorizacionEmisor() {
        return idAutorizacionEmisor;
    }

    public int getIdCodMovimiento() {
        return idCodMovimiento;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public String getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public double getImporte() {
        return importe;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public dbWorker(String codAutoEntidad, String comercioDescripcion, String modoIngreso,
            int idAutorizacionEmisor, int idCodMovimiento, int idEstado,
            int idCuenta, String fechaAutorizacion, double importe, String nroTarjeta) {
				this.codAutoEntidad = codAutoEntidad;
				this.comercioDescripcion = comercioDescripcion;
				this.modoIngreso = modoIngreso;
				this.idAutorizacionEmisor = idAutorizacionEmisor;
				this.idCodMovimiento = idCodMovimiento;
				this.idEstado = idEstado;
				this.idCuenta = idCuenta;
				this.fechaAutorizacion = fechaAutorizacion;
				this.importe = importe;
				this.nroTarjeta = nroTarjeta;
}


    
    //fin del constructor
	
	public String getHost() {
		return host;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setHost(String host) {
		this.host = host;
	}
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	
	//Constructor vacío
		public dbWorker() {
		}
	//Constructor con datos
		public dbWorker(String user, String pass, String entidad, String host, String serviceName, String port) {
			this.user = user;
			this.pass = pass;
			this.entidad = entidad;
			this.host = host;
			this.serviceName = serviceName;
			this.port = port;
		}
	
	// DB WORKER CON CADENA DE CONEXION DE ENTIDAD 8118 QA
//	public String oraOneQuery(Reports report, String Query, String colName) throws SQLException
//	{
//		//Variables
//		String resp = "";
//		//String strCon = "jdbc:oracle:thin:@databasepc:1521/xepdb1"; //"jdbc:oracle:thin:@localhost:1521:xe","hr","hrpass"
//		String strCon = "jdbc:oracle:thin:@gpdbqans.cvhla41mhkl1.us-east-1.rds.amazonaws.com:1521/GP247";
//
//		try {
//			report.AddLine("-> Connecting to DB ONE SELECT ENTIDAD 8118<-");
//			System.out.println("-> Connecting to DB ONE SELECT ENTIDAD 8118<-");
//			Connection conn = DriverManager.getConnection(strCon,"qa_entidad8118","int_entidad2022"); //"jdbc:oracle:thin:@localhost:1521:xe","hr","hrpass"
//			Statement stmt = conn.createStatement();
//			
//			//Se muestra la query en el reporte y por consola
//			report.AddLine("-> " + Query + " <-");
//			System.out.println("-> " + Query + " <-");
//			
//			ResultSet rset = stmt.executeQuery(Query);
//
//			while (rset.next())
//			{
//				//int index = rs.findColumn(colName);
//				resp = rset.getString(colName);
//			}
//		} catch (Exception e) {
//			report.AddLineAssertionError("Error: " + e);
//			System.out.println("##[warning] : Error: " + e);
//		}	
//		
//		report.AddLine("-> Connection Close ONE SELECT ENTIDAD 8118 <-<br><br>");
//		System.out.println("-> Connection Close ONE SELECT ENTIDAD 8118 <-\r\n");
//
//		return resp;
//	}
		public ResultSet queryCompuesta(String configEntidad) {
			String respuestaQuery = "";
			ResultSet resultSet = null;
			//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
					String user = JsonPath.from(configEntidad).get("DBCREDITO.user");
					String pass = JsonPath.from(configEntidad).get("DBCREDITO.pass");
					String host = JsonPath.from(configEntidad).get("DBCREDITO.host");
					String SID = JsonPath.from(configEntidad).get("DBCREDITO.SID");
					//concateno las variables para armar el string de conexion
					String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;
					
					Connection conn;
					try {
						System.out.println("****************ejecuta la query ********************");
						conn = DriverManager.getConnection(strCon,user,pass);
						Statement stmt=conn.createStatement();
						
						// Ejecutar la consulta SQL
			            String sqlQuery = "SELECT COD_AUTO_ENTIDAD, COMERCIO_DESCRIPCION, MODO_INGRESO, A.ID_AUTORIZACION_EMISOR, "
			                    + "A.ID_COD_MOVIMIENTO, A.ID_ESTADO, A.ID_CUENTA,A.FECHA_AUTORIZACION, A.IMPORTE, a.nro_tarjeta "
			                    + "FROM AUTORIZACION A "
			                    + "WHERE ID_AUTORIZACION IN (12117)";
			         resultSet = stmt.executeQuery(sqlQuery);
			            
			         // Procesar los resultados
			            while (resultSet.next()) {
			                // Obtener los valores de cada columna por nombre
			                String codAutoEntidad = resultSet.getString("COD_AUTO_ENTIDAD");
			                String comercioDescripcion = resultSet.getString("COMERCIO_DESCRIPCION");
			                String modoIngreso = resultSet.getString("MODO_INGRESO");
			                int idAutorizacionEmisor = resultSet.getInt("ID_AUTORIZACION_EMISOR");
			                int idCodMovimiento = resultSet.getInt("ID_COD_MOVIMIENTO");
			                int idEstado = resultSet.getInt("ID_ESTADO");
			                int idCuenta = resultSet.getInt("ID_CUENTA");
			                String fechaAutorizacion = resultSet.getString("FECHA_AUTORIZACION");
			                double importe = resultSet.getDouble("IMPORTE");
			                String nroTarjeta = resultSet.getString("nro_tarjeta");

			                // Imprimir las variables con el nombre de cada campo y su valor
			                System.out.println("COD_AUTO_ENTIDAD: " + codAutoEntidad);
			                System.out.println("COMERCIO_DESCRIPCION: " + comercioDescripcion);
			                System.out.println("MODO_INGRESO: " + modoIngreso);
			                System.out.println("ID_AUTORIZACION_EMISOR: " + idAutorizacionEmisor);
			                System.out.println("ID_COD_MOVIMIENTO: " + idCodMovimiento);
			                System.out.println("ID_ESTADO: " + idEstado);
			                System.out.println("ID_CUENTA: " + idCuenta);
			                System.out.println("FECHA_AUTORIZACION: " + fechaAutorizacion);
			                System.out.println("IMPORTE: " + importe);
			                System.out.println("nro_tarjeta: " + nroTarjeta);
			            }
						
			            resultSet.close();
			            stmt.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return resultSet;
					
			
		}
	
	public String oraOneQuery(Reports report, String Query, String configEntidad) throws SQLException, IOException
	{
		//Variables
		String resp = "";		
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB ONE SELECT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB ONE SELECT ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + Query + " <-");
			System.out.println("-> " + Query + " <-");
			
			ResultSet rset=stmt.executeQuery(Query);

			while (rset.next())
			{
				resp = rset.getString(1);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		
		report.AddLine("-> Connection Close ONE SELECT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close ONE SELECT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;
	}
	/********************prueba de credito con array******************/
	
	public String oraOneQueryCredito2(String Query, String configEntidad) {
		String respuestaQuery = "";
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
				String user = JsonPath.from(configEntidad).get("DBCREDITO.user");
				String pass = JsonPath.from(configEntidad).get("DBCREDITO.pass");
				String host = JsonPath.from(configEntidad).get("DBCREDITO.host");
				String SID = JsonPath.from(configEntidad).get("DBCREDITO.SID");
				//concateno las variables para armar el string de conexion
				String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;
				
				try {
					System.out.println("-> Connecting to DB ONE SELECT ESQUEMA " + user.toUpperCase() + " <-");
					Connection conn = DriverManager.getConnection(strCon,user,pass);
					Statement stmt=conn.createStatement();
					System.out.println("-> " + Query + " <-");
					ResultSet rset=stmt.executeQuery(Query);
					
					while (rset.next())
					{
						respuestaQuery = rset.getString(1);
					}
					conn.close();
					stmt.close();
					rset.close();
					System.out.println("-> Finished <-");
					
				}
				catch (Exception e){
					System.out.println("##[warning] : Error: " + e);
				}
		
				//return		ResultSet respuestaQuery
		return respuestaQuery;
	}
	
	/*******************************************************************/
	
	/********************************db credito**************/
	
	public String oraOneQueryCredito(Reports report, String Query, String configEntidad) throws SQLException, IOException
	{
		//Variables
		String resp = "";		
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DBCREDITO.user");
		String pass = JsonPath.from(configEntidad).get("DBCREDITO.pass");
		String host = JsonPath.from(configEntidad).get("DBCREDITO.host");
		String SID = JsonPath.from(configEntidad).get("DBCREDITO.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB ONE SELECT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB ONE SELECT ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + Query + " <-");
			System.out.println("-> " + Query + " <-");
			
			ResultSet rset=stmt.executeQuery(Query);

			while (rset.next())
			{
				resp = rset.getString(1);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		
		report.AddLine("-> Connection Close ONE SELECT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close ONE SELECT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;
	}
	/*******************************************************/
	
	/**************metodo getConn*****************/
	
	// Connection
		public Connection getConn() {
			// Variables
			Connection conn = null;
			String strCon = "jdbc:oracle:thin:@" + host + ":" + port + "/" + serviceName; // Esta operación se podría
																							// ahorrar si se genera una
																							// variable strCon con la misma
																							// data en un scope general
			System.out.println(strCon);
			System.out.println("host:" + host);
			System.out.println("Name:" + serviceName);
			System.out.println("port:" + port);
			System.out.println("User:" + user);
			System.out.println("Pass:" + pass);
			try {
				System.out.println("-> Connecting to DB - ENTIDAD " + entidad + " <-");
				conn = DriverManager.getConnection(strCon, user, pass);
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
			return conn;
		}

		// Disconnect
		public void getDisConn(Connection conn) {

			try {
				conn.close();
				System.out.println("-> Connection Close - ESQUEMA " + entidad + " <-\r\n");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	/*********fin metodo getConn*******************/
	
	/*******************solucion oraOneQuery**************/
	
	public String oraOneQueryE(String Query) throws SQLException {
		// Variables
		String resp = "";
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/GP247";

		try {
			System.out.println("-> Connecting to DB - ENTIDAD " + entidad + " <-");
			Connection conn = DriverManager.getConnection(strCon, user, pass);
			Statement stmt = conn.createStatement();
			System.out.println("-> " + Query + " <-");
			ResultSet rset = stmt.executeQuery(Query);

			while (rset.next()) {
				resp = rset.getString(1);
			}
			conn.close();
			stmt.close();
			rset.close();
			System.out.println("-> Finished <-");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		System.out.println("-> Connection Close - ESQUEMA " + entidad + " <-\r\n");

		return resp;
	}
	
	/*******************fin solucion oraOneQuery**********/

	public String[] oraTwoQuery(Reports report, String Query, String configEntidad) throws SQLException
	{
		//Variables
		String[] resp = new String[2];
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB TWO SELECT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB TWO SELECT ESQUEMA " + user.toUpperCase() + " <-");
			//Connection conn = DriverManager.getConnection(strCon,"hr","hrpass");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + Query + " <-");
			System.out.println("-> " + Query + " <-");
			
			ResultSet rset=stmt.executeQuery(Query);

			while (rset.next())
			{
				resp[0] = rset.getString(1);
				resp[1] = rset.getString(2);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		report.AddLine("-> Connection Close TWO SELECT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close TWO SELECT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;
	}

	public String[] oraThreeQuery(Reports report, String Query, String configEntidad) throws SQLException
	{
		//Variables
		String[] resp = new String[3];
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB THREE SELECT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB THREE SELECT ESQUEMA " + user.toUpperCase() + " <-");
			//Connection conn = DriverManager.getConnection(strCon,"hr","hrpass");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + Query + " <-");
			System.out.println("-> " + Query + " <-");
			
			ResultSet rset=stmt.executeQuery(Query);

			while (rset.next())
			{
				resp[0] = rset.getString(1);
				resp[1] = rset.getString(2);
				resp[2] = rset.getString(3);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		report.AddLine("-> Connection Close THREE SELECT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close THREE SELECT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;
	}
	
	public String[] oraFourQuery(Reports report, String Query, String configEntidad) throws SQLException
	{
		//Variables
		String[] resp = new String[4];
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB FOUR SELECT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB FOUR SELECT ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(Query);
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + Query + " <-");
			System.out.println("-> " + Query + " <-");
			

			while (rset.next())
			{
				resp[0] = rset.getString(1);
				resp[1] = rset.getString(2);
				resp[2] = rset.getString(3);
				resp[3] = rset.getString(4);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		report.AddLine("-> Connection Close FOUR SELECT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close FOUR SELECT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;
	}
	
	/*************************************ora ten query*************************/
	
	public String[] oraTenQuery(Reports report, String Query, String configEntidad) throws SQLException
	{
		//Variables
		String[] resp = new String[4];
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DBCREDITO.user");
		String pass = JsonPath.from(configEntidad).get("DBCREDITO.pass");
		String host = JsonPath.from(configEntidad).get("DBCREDITO.host");
		String SID = JsonPath.from(configEntidad).get("DBCREDITO.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB FOUR SELECT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB FOUR SELECT ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(Query);
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + Query + " <-");
			System.out.println("-> " + Query + " <-");
			

			while (rset.next())
			{
				resp[0] = rset.getString(1);
				resp[1] = rset.getString(2);
				resp[2] = rset.getString(3);
				resp[3] = rset.getString(4);
				resp[3] = rset.getString(4);
				resp[3] = rset.getString(4);
				resp[3] = rset.getString(4);
				resp[3] = rset.getString(4);
				resp[3] = rset.getString(4);
				resp[3] = rset.getString(4);
				
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		report.AddLine("-> Connection Close FOUR SELECT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close FOUR SELECT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;
	}
	
	/***************************************************************************/

	public String oraQueryCsv(String Query, String configEntidad) throws SQLException
	{
		//Variables
		String resp = "";
		String header = "";
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;
		int idx = 0;

		try {
			System.out.println("-> Connecting to DB ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(Query);
			OracleResultSetMetaData rsmd = (OracleResultSetMetaData)rset.getMetaData();

			int colCount = rsmd.getColumnCount();

			for (int x = 1; x <= colCount; x++) 
			{	
				header = header + rsmd.getColumnName(x) + ",";
			}

			while (rset.next())
			{
				if (idx != 0)
				{
					resp = resp + "\r\n";
				} else {
					resp = createHeader(header) + "\r\n";
				}
				resp = resp + rset.getString(header.split(",")[0]) + "," +  rset.getString(header.split(",")[1])  + "," + rset.getString(header.split(",")[2]);
				idx++;
			}
			conn.close();
			stmt.close();
			rset.close();
			System.out.println("-> Finished <-");
		} catch (Exception e) {
			System.out.println("##[warning] : Error: " + e);
		}
		System.out.println("-> Connection Close ESQUEMA " + user.toUpperCase() + "<-\r\n");

		return resp;
	}

	public int oraInsert (Reports report, String sqlInsert, String configEntidad) {
		//Variables
		int rows = 0;
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB INSERT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB INSERT ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt = conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + sqlInsert + " <-");
			System.out.println("-> " + sqlInsert + " <-");
			
			rows = stmt.executeUpdate(sqlInsert);

			//SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT... 
			if (rows !=0 && !conn.getAutoCommit())
				conn.commit();

			conn.close();
			stmt.close();
			
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}

		report.AddLine("-> Connection Close INSERT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close INSERT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return rows;
	}
	/*************************metodo para ejecutar query delete en operacion debito*******************/
	
	
	
	/**************************************************************************************************/
	
	public int oraUpdate (Reports report, String sqlUpdate, String configEntidad) {
		//Variables
		int rows = 0;
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		//String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/" + SID;

		try {
			report.AddLine("-> Connecting to DB UPDATE ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB UPDATE ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt = conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + sqlUpdate + " <-");
			System.out.println("-> " + sqlUpdate + " <-");
			
			rows = stmt.executeUpdate(sqlUpdate);

			//SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT... 
			if (rows !=0 && !conn.getAutoCommit())
				conn.commit();

			conn.close();
			stmt.close();

			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}

		report.AddLine("-> Connection Close DB UPDATE ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close DB UPDATE ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return rows;
	}
	/******************************DB DE CREDITO ***********************/
	public int oraUpdateCredito (Reports report, String sqlUpdate, String configEntidad) {
		//Variables
		int rows = 0;
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DBCREDITO.user");
		String pass = JsonPath.from(configEntidad).get("DBCREDITO.pass");
		String host = JsonPath.from(configEntidad).get("DBCREDITO.host");
		String SID = JsonPath.from(configEntidad).get("DBCREDITO.SID");
		//concateno las variables para armar el string de conexion
		//String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/" + SID;

		try {
			report.AddLine("-> Connecting to DB UPDATE ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB UPDATE ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt = conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + sqlUpdate + " <-");
			System.out.println("-> " + sqlUpdate + " <-");
			
			rows = stmt.executeUpdate(sqlUpdate);

			//SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT... 
			if (rows !=0 && !conn.getAutoCommit())
				conn.commit();

			conn.close();
			stmt.close();

			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}

		report.AddLine("-> Connection Close DB UPDATE ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close DB UPDATE ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return rows;
	}
	
	/*****************************************************************/

	public int oraDelete (Reports report, String sqlDelete, String configEntidad) {
		//Variables
		int rows = 0;
		//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB DB DELETE ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB DB DELETE ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt = conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + sqlDelete + " <-");
			System.out.println("-> " + sqlDelete + " <-");
			
			rows = stmt.executeUpdate(sqlDelete);

			//SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT... 
			if (!conn.getAutoCommit())
				conn.commit();

			conn.close();
			stmt.close();
			
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			System.out.println("##[warning] : Error: " + e);
			report.AddLineAssertionError("Error: " + e);
		}
		
		report.AddLine("-> Connection Close DELETE ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close DELETE ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return rows;
	}
	
	/*******************nuevo delete*********************/
	
	
	public int oraDelete(String sqlDelete) {
		// Variables
		int rows = 0;
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/GP247";

		try {
			System.out.println("-> Connecting to DB - DELETE - ENTIDAD " + entidad + " <-");
			Connection conn = DriverManager.getConnection(strCon, user, pass);
			Statement stmt = conn.createStatement();
			System.out.println("-> " + sqlDelete + " <-");

			rows = stmt.executeUpdate(sqlDelete);

			// SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT...
			if (!conn.getAutoCommit())
				conn.commit();

			conn.close();
			stmt.close();

			System.out.println("-> Finished <-");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

		System.out.println("-> Connection Close - ESQUEMA " + entidad + " <-\r\n");

		return rows;
	}

	public int oraDelete(String sqlDelete, Connection conn) {
		// Variables
		int rows = 0;

		try {
			// System.out.println("-> Connecting to DB - DELETE - ENTIDAD " + entidad + "
			// <-");
			Statement stmt = conn.createStatement();
			System.out.println("-> " + sqlDelete + " <-");

			rows = stmt.executeUpdate(sqlDelete);

			// SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT...
			if (!conn.getAutoCommit())
				conn.commit();

			stmt.close();

			System.out.println("-> Finished <-\r\n");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

		// System.out.println("-> Connection Close - ESQUEMA " + entidad + " <-\r\n");

		return rows;
	}
	
	
	/***************fin nuevo delete ********************/

	public boolean oraBorrarCuenta (Reports report, String id_cuenta, String doc, String configEntidad, String cuentas_generales) {
		//Variables
		boolean RES = false;
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		
		
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB - Eliminacion de cuenta - ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB - Eliminacion de cuenta - ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt = conn.createStatement();

			int[] results = new int[13];
			
			results[0] = stmt.executeUpdate("delete tarjetas_historial where id_tarjeta = (select id_tarjeta from tarjetas where id_cuenta = " + id_cuenta + ")");
			results[1] = stmt.executeUpdate("delete tarjetas_nov where id_tarjeta = (select id_tarjeta from tarjetas where id_cuenta = " + id_cuenta + ")");
			results[2] = stmt.executeUpdate("delete socios_nov where id_cuenta = " + id_cuenta);
			results[3] = stmt.executeUpdate("delete personas_nov where id_persona = (select id_persona from personas where nro_documento = " + doc + ")");
			results[4] = stmt.executeUpdate("update socios set id_tarjeta_vigente = null where id_cuenta = " + id_cuenta);
			results[5] = stmt.executeUpdate("delete tarjetas where id_cuenta = " + id_cuenta + " and ID_ADICIONAL = 0");
			results[6] = stmt.executeUpdate("update socios set id_persona = (select id_persona from personas where nro_documento = " + doc + ") where id_cuenta = " + id_cuenta); 
			results[7] = stmt.executeUpdate("delete socios where id_cuenta = " + id_cuenta + " and ID_ADICIONAL = 0");
			results[8] = stmt.executeUpdate("delete cuentas_moneda where id_cuenta = " + id_cuenta);
			results[9] = stmt.executeUpdate("delete cuentas where id_cuenta = " + id_cuenta);
			results[10] = stmt.executeUpdate("delete socios_nov where id_cuenta = " + id_cuenta);
			results[11] = stmt.executeUpdate("delete personas where nro_documento = " + doc);
			results[12] = stmt.executeUpdate("delete cuentas_moneda_auditoria where id_cuenta = " + id_cuenta);
			
		

			//SE RECORREN LOS RESULTADOS PARA VALIDAR LAS RESPUESTAS
			for (int i = 0; i<results.length; i++) {
				if (results[i] == 0) {
					RES = false;
					System.out.println("Fallo: " + Integer.valueOf(i) + "\r\nResultado: " + results[i]);
					//break;
				} else {
					RES = true;
				}
			}
			
			//SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT... 
			if (RES && !conn.getAutoCommit())
				conn.commit();

			//SE CIERRAN LAS CONEXIONES
			stmt.close();
			conn.close();

			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}

		report.AddLine("-> Connection Close - ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close - ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return RES;
	}

	public String oraValidaAlta (Reports report, String doc, String configEntidad) {
		//Variables
		String resp = "";
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB Validacion Alta de Cuenta - ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB Validacion Alta de Cuenta - ESQUEMA " + user .toUpperCase()+ " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery("select c.id_cuenta NRO_CUENTA_CREADA\r\n"
					+ "		from personas p\r\n"
					+ "		inner join socios s on s.id_persona = p.id_persona\r\n"
					+ "		inner join cuentas c on s.id_cuenta = c.id_cuenta\r\n"
					+ "		inner join tarjetas t on c.id_cuenta = t.id_cuenta\r\n"
					+ "		inner join tarjetas_historial th on th.id_tarjeta = t.id_tarjeta\r\n"
					+ "		where p.nro_documento = " + doc);

			while (rset.next())
			{
				resp = rset.getString(1);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		report.AddLine("-> Connection Close - ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close - ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;

	}

	public String oraValidaTipoTarjeta (Reports report, String id_cuenta, String configEntidad) {
		//Variables
		String resp = "";
		String user = JsonPath.from(configEntidad).get("DB.user");
		String pass = JsonPath.from(configEntidad).get("DB.pass");
		String host = JsonPath.from(configEntidad).get("DB.host");
		String SID = JsonPath.from(configEntidad).get("DB.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			report.AddLine("-> Connecting to DB Validacion Tipo de Tarjeta - ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB Validacion Tipo de Tarjeta - ESQUEMA " + user.toUpperCase() + " <-");
			//Connection conn = DriverManager.getConnection(strCon,"hr","hrpass");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery("SELECT SOPORTE_FISICO FROM TARJETAS WHERE ID_CUENTA = " + id_cuenta);

			while (rset.next())
			{
				resp = rset.getString(1);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		
		report.AddLine("-> Connection Close - ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close - ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;

	}
	
	private String createHeader(String header)
	{
		return header.substring(0, header.length()-1);
	}
	
	
	// DB WORKER CON CADENA DE CONEXION DE PARAM	
	public int oraDeleteParam (Reports report, String sqlDelete, String configEntidad) {
		//Variables
		int rows = 0;
		String user = JsonPath.from(configEntidad).get("DBPARAM.user");
		String pass = JsonPath.from(configEntidad).get("DBPARAM.pass");
		String host = JsonPath.from(configEntidad).get("DBPARAM.host");
		String SID = JsonPath.from(configEntidad).get("DBPARAM.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/" + SID;

		try {
			report.AddLine("-> Connecting to DB - DELETE ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB - DELETE ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt = conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + sqlDelete + " <-");
			System.out.println("-> " + sqlDelete + " <-");
			
			rows = stmt.executeUpdate(sqlDelete);

			//SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT... 
			if (!conn.getAutoCommit())
				conn.commit();

			conn.close();
			stmt.close();

			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}

		report.AddLine("-> Connection Close DELETE ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close DELETE ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return rows;
	}
	
	public int oraInsertParam (Reports report, String sqlInsert, String configEntidad) {
		//Variables
		int rows = 0;
		String user = JsonPath.from(configEntidad).get("DBPARAM.user");
		String pass = JsonPath.from(configEntidad).get("DBPARAM.pass");
		String host = JsonPath.from(configEntidad).get("DBPARAM.host");
		String SID = JsonPath.from(configEntidad).get("DBPARAM.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/" + SID;

		try {
			report.AddLine("-> Connecting to DB - INSERT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB - INSERT ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt = conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + sqlInsert + " <-");
			System.out.println("-> " + sqlInsert + " <-");
				
			rows = stmt.executeUpdate(sqlInsert);

			//SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT... 
			if (rows !=0 && !conn.getAutoCommit())
				conn.commit();

			conn.close();
			stmt.close();

			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}

		report.AddLine("-> Connection Close - INSERT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close - INSERT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return rows;
	}

	public int oraUpdateParam (Reports report, String sqlUpdate, String configEntidad) {
		//Variables
		int rows = 0;
		String user = JsonPath.from(configEntidad).get("DBPARAM.user");
		String pass = JsonPath.from(configEntidad).get("DBPARAM.pass");
		String host = JsonPath.from(configEntidad).get("DBPARAM.host");
		String SID = JsonPath.from(configEntidad).get("DBPARAM.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/" + SID;

		try {
			report.AddLine("-> Connecting to DB - UPDATE ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB - UPDATE ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt = conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + sqlUpdate + " <-");
			System.out.println("-> " + sqlUpdate + " <-");
						
			rows = stmt.executeUpdate(sqlUpdate);

			//SI EL RESULTADO ES OK Y LA BASE NO TIENE AUTOCOMMIT... 
			if (rows !=0 && !conn.getAutoCommit())
				conn.commit();

			conn.close();
			stmt.close();

			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}

		report.AddLine("-> Connection Close - UPDATE ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close - UPDATE ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return rows;
	}
	
	public String oraOneQueryParam(Reports report, String Query, String configEntidad) throws SQLException
	{
		//Variables
		String resp = "";
		String user = JsonPath.from(configEntidad).get("DBPARAM.user");
		String pass = JsonPath.from(configEntidad).get("DBPARAM.pass");
		String host = JsonPath.from(configEntidad).get("DBPARAM.host");
		String SID = JsonPath.from(configEntidad).get("DBPARAM.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/" + SID;

		try {
			report.AddLine("-> Connecting to DB - ONE SELECT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB - ONE SELECT ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + Query + " <-");
			System.out.println("-> " + Query + " <-");
				
			ResultSet rset=stmt.executeQuery(Query);

			while (rset.next())
			{
				resp = rset.getString(1);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		
		report.AddLine("-> Connection Close - ONE SELECT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close - ONE SELECT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;
	}
	
	public String[] oraTwoQueryParam(Reports report, String Query, String configEntidad) throws SQLException
	{
		//Variables
		String[] resp = new String[2];
		String user = JsonPath.from(configEntidad).get("DBPARAM.user");
		String pass = JsonPath.from(configEntidad).get("DBPARAM.pass");
		String host = JsonPath.from(configEntidad).get("DBPARAM.host");
		String SID = JsonPath.from(configEntidad).get("DBPARAM.SID");
		//concateno las variables para armar el string de conexion
		String strCon = "jdbc:oracle:thin:@" + host + ":1521/" + SID;

		try {
			report.AddLine("-> Connecting to DB - TWO SELECT ESQUEMA " + user.toUpperCase() + " <-");
			System.out.println("-> Connecting to DB - TWO SELECT ESQUEMA " + user.toUpperCase() + " <-");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			
			//Se muestra la query en el reporte y por consola
			report.AddLine("-> " + Query + " <-");
			System.out.println("-> " + Query + " <-");
				
			ResultSet rset=stmt.executeQuery(Query);

			while (rset.next())
			{
				resp[0] = rset.getString(1);
				resp[1] = rset.getString(2);
			}
			conn.close();
			stmt.close();
			rset.close();
			report.AddLine("-> Finished <-");
			System.out.println("-> Finished <-");
			
		} catch (Exception e) {
			report.AddLineAssertionError("Error: " + e);
			System.out.println("##[warning] : Error: " + e);
		}
		
		report.AddLine("-> Connection Close - TWO SELECT ESQUEMA " + user.toUpperCase() + " <-<br><br>");
		System.out.println("-> Connection Close - TWO SELECT ESQUEMA " + user.toUpperCase() + " <-\r\n");

		return resp;
	}
	/**************************************PAT***********************************************************/
	public String[][] executeQuery(String Query) throws SQLException{
		//Variables
		int rows = 0;
		int columns = 0;
		String resp[][] = null;
		//String host = "gpwf-aq-qa-ent.cunfmyfynl3c.us-east-1.rds.amazonaws.com";
		//String SID = "GP247";
		//String strCon = "jdbc:oracle:thin:@databasepc:1521/xepdb1"; //"jdbc:oracle:thin:@localhost:1521:xe","hr","hrpass"
		//String strCon = "jdbc:oracle:thin:@" + host + ":1521/GP247";  //10.86.2.181
		//String strCon = "jdbc:oracle:thin:@" + host + ":1521";
		//String strCon = "jdbc:oracle:thin:@" + "10.86.2.181" + ":" + "1521" + "/" + "GPINFQA";
		//String strCon = "jdbc:oracle:thin:@" + host + "1521";
		//Variables
				//String resp = "";		
				//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
				String user = "qa_entidad9999";
				String pass = "GP.NS.QA#999";
				String host = "gpwf-aq-qa-ent.cunfmyfynl3c.us-east-1.rds.amazonaws.com";
				String SID = "GP247";
				//concateno las variables para armar el string de conexion
				String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		//Variables
				//String resp = "";
				//String user = JsonPath.from(configEntidad).get("DB.user");
				//String pass = JsonPath.from(configEntidad).get("DB.pass");
				//String host = JsonPath.from(configEntidad).get("DB.host");
				//String SID = JsonPath.from(configEntidad).get("DB.SID");
				//concateno las variables para armar el string de conexion
				//String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			//System.out.println("-> Connecting to DB - ENTIDAD " + entidad + " <-");
			//Connection conn = DriverManager.getConnection(strCon,"hr","hrpass");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			System.out.println("-> " + Query + " <-");

			ResultSet rsCounter=stmt.executeQuery(Query);			
			
			rows = countRows(rsCounter);
			columns = countColumns(rsCounter);
			
			System.out.println("Total Rows: " + rows);
			System.out.println("Total columns: " + columns);
			
			String respAux[][] = new String[rows][columns];
									
			rsCounter.close();
			
			ResultSet rset=stmt.executeQuery(Query);

			for(int i = 0; rset.next(); i++) {
				System.out.println("Row " + (i+1) + ":");
				for(int j = 0; j<respAux[i].length; j++) {
					respAux[i][j] = rset.getString(j+1);					
					System.out.println("Column " + (j+1) + ": " + respAux[i][j]);
				}
			}
			
			conn.close();
			stmt.close();
			rset.close();
			
			resp = respAux;
			
			System.out.println("resp:");
			
			for (int i = 0; i < rows; i++) {
				for(int j = 0; j < columns; j++) {
					System.out.print("| " + resp[i][j] + " |");
				}
				System.out.println("");
			}
			
			System.out.println("-> Finished <-");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		System.out.println("-> Connection Close - ESQUEMA " + entidad + " <-\r\n");
		
		return resp;
	}
	
	/*******************************ECECUTEqUERYcREDITO*********************/
	public String[][] executeQueryCredito(String Query) throws SQLException{
		//Variables
		int rows = 0;
		int columns = 0;
		String resp[][] = null;
		//String host = "gpwf-aq-qa-ent.cunfmyfynl3c.us-east-1.rds.amazonaws.com";
		//String SID = "GP247";
		//String strCon = "jdbc:oracle:thin:@databasepc:1521/xepdb1"; //"jdbc:oracle:thin:@localhost:1521:xe","hr","hrpass"
		//String strCon = "jdbc:oracle:thin:@" + host + ":1521/GP247";  //10.86.2.181
		//String strCon = "jdbc:oracle:thin:@" + host + ":1521";
		//String strCon = "jdbc:oracle:thin:@" + "10.86.2.181" + ":" + "1521" + "/" + "GPINFQA";
		//String strCon = "jdbc:oracle:thin:@" + host + "1521";
		//Variables
				//String resp = "";		
				//Obtengo los datos de la conexion a la BD desde la variable configEntidad que contiene todos los accesos a la entidad
				String user = "desa_entidad8801";
				String pass = "cre_entidad0101";
				String host = "gp247.cvhla41mhkl1.us-east-1.rds.amazonaws.com";
				String SID = "GP247";
				//concateno las variables para armar el string de conexion
				String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		//Variables
				//String resp = "";
				//String user = JsonPath.from(configEntidad).get("DB.user");
				//String pass = JsonPath.from(configEntidad).get("DB.pass");
				//String host = JsonPath.from(configEntidad).get("DB.host");
				//String SID = JsonPath.from(configEntidad).get("DB.SID");
				//concateno las variables para armar el string de conexion
				//String strCon = "jdbc:oracle:thin:@" + host + "/" + SID;

		try {
			//System.out.println("-> Connecting to DB - ENTIDAD " + entidad + " <-");
			//Connection conn = DriverManager.getConnection(strCon,"hr","hrpass");
			Connection conn = DriverManager.getConnection(strCon,user,pass);
			Statement stmt=conn.createStatement();
			System.out.println("-> " + Query + " <-");

			ResultSet rsCounter=stmt.executeQuery(Query);			
			
			rows = countRows(rsCounter);
			columns = countColumns(rsCounter);
			
			System.out.println("Total Rows: " + rows);
			System.out.println("Total columns: " + columns);
			
			String respAux[][] = new String[rows][columns];
									
			rsCounter.close();
			
			ResultSet rset=stmt.executeQuery(Query);

			for(int i = 0; rset.next(); i++) {
				System.out.println("Row " + (i+1) + ":");
				for(int j = 0; j<respAux[i].length; j++) {
					respAux[i][j] = rset.getString(j+1);					
					System.out.println("Column " + (j+1) + ": " + respAux[i][j]);
				}
			}
			
			conn.close();
			stmt.close();
			rset.close();
			
			resp = respAux;
			
			System.out.println("resp:");
			
			for (int i = 0; i < rows; i++) {
				for(int j = 0; j < columns; j++) {
					System.out.print("| " + resp[i][j] + " |");
				}
				System.out.println("");
			}
			
			System.out.println("-> Finished <-");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		System.out.println("-> Connection Close - ESQUEMA " + entidad + " <-\r\n");
		
		return resp;
	}
	
	/**************************************************************************/
	
	private int countColumns(ResultSet rset) throws SQLException {
		return rset.getMetaData().getColumnCount();
	}
	
	private int countRows(ResultSet rset) throws SQLException {
		int rows = 0;
		
		while(rset.next()) {
			rows++;
		}
		
		return rows;
	}
	/***************************************PAT**************************************************************/	
}
