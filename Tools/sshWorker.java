package Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
//import org.json.JSONObject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import CentaJava.Core.Reports;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class sshWorker {

	public String sshSendCmd(Reports report, String xmlFile, String config_entidad2_infinitus) {
		//Variables
		String privateKey = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.user");
		String host = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.host");
		String ID_ENTIDAD = JsonPath.from(config_entidad2_infinitus).get("ENTIDAD.id_entidad");
		int port = Integer.valueOf(JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.port"));
		String result = "FAIL";
		
		try {
			//PreCondicion
			//if (!getOpiStatus(config_entidad2_infinitus)) {
			//	report.AddLine("Se inicializa OPI");
	        //    System.out.println("Se inicializa OPI");
			//	startOpiCmd(report,config_entidad2_infinitus);
			//}
			
			//Instancias
            JSch jsch = new JSch();
            
            Session session = jsch.getSession(user, host, port);
            report.AddLine("Creating Session...");
            System.out.println("Creating Session...");

            jsch.addIdentity(privateKey);
            
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(2000);
            String variable = "";
            report.AddLine("Session connected" + variable);
            System.out.println("Session connected");

            Channel channel = session.openChannel("shell");
            report.AddLine("Opening Channel... ");
            System.out.println("Opening Channel... ");

            OutputStream inputstream_for_the_channel = channel.getOutputStream();
            PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

            channel.setOutputStream(null);
            channel.connect(2000);
            report.AddLine("Channel connected");
            System.out.println("Channel connected");
            
            //shell script
            commander.println("cd /home/opiemimcaut/testclient");
            commander.println("./tc-acq.sh " + ID_ENTIDAD + "/" + xmlFile);
            commander.println("exit");
            commander.flush();

            System.out.println("" + channel.getExitStatus());

            InputStream outputstream_from_the_channel = channel.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
            String line = null;
            StringBuilder sb = new StringBuilder();
            //boolean isloginStringPassed = false ;
           

            while ((line = br.readLine()) != null) {
            	sb.append(line + "\n");
            	if (line.contains("field id=\"39\"")) {
            		result = line.split("=")[2].replace("\"", "").replace("/>","");
            	}
            		
            	//Pasar line a un metodo para evaluar si tiene lo que busco
            }
            
            System.out.println("Console result:\r\n" + sb.toString());
            
            channel.disconnect();
            session.disconnect();
 
            report.AddLine("Completed");
            System.out.println("Completed");
            
        } catch (JSchException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		return result;
	}
	/************************************copia******************************/
	public void startOpiCmd2(Reports report, String configEntidad, String opi) {

		System.out.println("<<<<<<<<<<<<<<<<<<< Incia START del OPI >>>>>>>>>>>>>>>>>>>>>>");
		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(5000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			report.AddLine("Session connected");
			System.out.println("Session connected");
			

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);
			
			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");
			

			// shell script
			//commander.println("cd /home/"+ opi + "/opi-switch");
			commander.println("cd "+ opi + "/opi-switch");
			commander.println("pwd");
			commander.println("./start.sh ");
			commander.println("exit");
			commander.flush();

			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			System.out.println("<<<<<<<<<<<<<<< FINALIZA START del OPI >>>>>>>>>>>>>>");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("<<<<<<<<<<<<<<< FINALIZA START del OPI >>>>>>>>>>>>>>");
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("<<<<<<<<<<<<<<< FINALIZA START del OPI >>>>>>>>>>>>>>");
			ex.printStackTrace();
		}
		System.out.println("<<<<<<<<<<<<<<< FINALIZA START del OPI >>>>>>>>>>>>>>");
	}
	
	/**************************/
	
	public String executeXmlGetDE39(Reports report, String xmlFileRun, String configEntidad, String opi) {
		System.out
				.println("<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));
		String result = null;

		// Inicialización de variables

		try {

			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);
			System.out.println("Identity added");

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			// Channel channel = session.openChannel("shell");

			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect();

			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell scripts
			commander.println("cd " + opi + "/testclient/");
			commander.println("pwd");
			commander.println("./tc-acq.sh " + xmlFileRun);
			commander.println("exit");
			commander.flush();

//			commander.println("cd /opi/opi4qa/testClient");
//			commander.println("cd " + opi + "/testClient");
//			commander.println("pwd");
//			commander.println("./tc-acq.sh " + xmlFileRun);
//			commander.println("exit");
//			commander.flush();

			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				// FIELD 39
				if (line.contains("field id=\"39\"")) {
					result = line.split("=")[2].replace("\"", "").replace("/>", "");
					System.out.println("DE39: " + result);
				}

				// Pasar line a un metodo para evaluar si tiene lo que busco
			}
			System.out.println("Console result:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return result;
	}
	
	
	
	/***************************/
	
	
	
	/**********************************fin de copia*************************/
	// agregue parametro "String opi" como lo trabajamos en adquirencia, de igual manera veo que no es necesario, lo cual se cambiara 
	
	public void startOpiCmd(Reports report, String config_entidad2_infinitus, String opi) {
		System.out.println("<<<<<<<<<<<<<<<<<<< Incia START del OPI 77777>>>>>>>>>>>>>>>>>>>>>>");
		//Variables
		String privateKey = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.user");
		String host = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.port"));
		
		
		
		try {			
			//Instancias
            JSch jsch = new JSch();
            
            report.AddLine("Inicializando OPI...");
            System.out.println("Inicializando OPI...");
            
            Session session = jsch.getSession(user, host, port);
            report.AddLine("Creating Session...");
            System.out.println("Creating Session...");
            
            jsch.addIdentity(privateKey);
            
            
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(2000);
            String variable = "";
			report.AddLine("Session connected" + variable);
            report.AddLine("Session connected");
            System.out.println("Session connected");

            Channel channel = session.openChannel("shell");
            report.AddLine("Opening Channel... ");
            System.out.println("Opening Channel... ");

            OutputStream inputstream_for_the_channel = channel.getOutputStream();
            PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

            channel.setOutputStream(null);
            channel.connect(2000);
            report.AddLine("Channel connected");
            System.out.println("Channel connected");
            
            
            //shell script
            commander.println("cd /home/"+ opi + "/opi-switch");
            commander.println("pwd");
            commander.println("./start.sh");
            commander.println("exit");
            commander.flush();
            

            //Se agrega tiempo de espera para esperar que levante OPI
            Thread.sleep(5000);
            
            System.out.println(channel.getExitStatus());

            InputStream outputstream_from_the_channel = channel.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                    sb.append(line.trim() + "\n");
            }
            System.out.println("Console Result:\r\n" + sb.toString());

            channel.disconnect();
            session.disconnect();
            
            report.AddLine("Inicializacion de OPI finalizada");
            System.out.println("Inicializacion de OPI finalizada");
            
        } catch (JSchException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
	}
	
	public boolean getOpiStatus(String configEntidad) {
		
		System.out.println("<<<<<<<<<<<<<<<<<<< Incia STATUS del OPI >>>>>>>>>>>>>>>>>>>>>>");
		
		//Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));
		boolean result = false;

		try {			
			//Instancias
            JSch jsch = new JSch();
            
            Session session = jsch.getSession(user, host, port);
            System.out.println("Creating Session...");

            jsch.addIdentity(privateKey);
            
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(2000);
            System.out.println("Session connected");

            Channel channel = session.openChannel("shell");
            System.out.println("Opening Channel... ");

            OutputStream inputstream_for_the_channel = channel.getOutputStream();
            PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

            channel.setOutputStream(null);
            channel.connect(2000);
            System.out.println("Channel connected");
            
            //shell script
            commander.println("cd /home/opiemimcaut/opi-switch");
            commander.println("./status.sh");
            commander.println("exit");
            commander.flush();

            System.out.println(channel.getExitStatus());

            InputStream outputstream_from_the_channel = channel.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
            String line = null;
            StringBuilder sb = new StringBuilder();
            //boolean isloginStringPassed = false;

            while ((line = br.readLine()) != null) {
                    sb.append(line.trim() + "\n");
                    if (line.contains("is running.")) {
                    	System.out.println("OPI en Ejecucion");
                    	result = true;
                    }
            }
            
            System.out.println("Console Result:\r\n" + sb.toString());

            channel.disconnect();
            session.disconnect();
            
            System.out.println("Completed");
            
        } catch (JSchException ex) {
            ex.printStackTrace();
            Assert.assertFalse("Error:\r\n" + ex, true);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertFalse("Error:\r\n" + ex, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.assertFalse("Error:\r\n" + ex, true);
        }
		
		return result;
	}
	public void stopOpiCmd(Reports report, String configEntidad, String opi) {

		System.out.println("<<<<<<<<<<<<<<<<<<< Incia STOP del OPI >>>>>>>>>>>>>>>>>>>>>>");
		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			//commander.println("cd /home/"+ opi + "/opi-switch");
			commander.println("cd "+ opi + "/opi-switch");
			commander.println("pwd");
			commander.println("./stop.sh ");
			commander.println("exit");
			commander.flush();

			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			System.out.println("<<<<<<<<<<<<<<< FINALIZA STOP del OPI >>>>>>>>>>>>>>");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("<<<<<<<<<<<<<<< FINALIZA STOP del OPI >>>>>>>>>>>>>>");
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("<<<<<<<<<<<<<<< FINALIZA STOP del OPI >>>>>>>>>>>>>>");
			ex.printStackTrace();
		}
		System.out.println("<<<<<<<<<<<<<<< FINALIZA STOP del OPI >>>>>>>>>>>>>>");
	}
	
	
	public void cambioEntidadSimulador(Reports report, String configEntidad, String opi, String entidad,
			String operacion) {
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA CAMBIO DE CONFIGURACION DEL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			commander.println("cd /home/opiemimcaut/opi-switch");
			commander.println("pwd");
			commander.println("sudo cp opi-switch-" + entidad + "-" + operacion + ".cfg opi-switch.cfg");
			commander.println("exit");
			commander.flush();
			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA CAMBIO DE CONFIGURACION DEL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA CAMBIO DE CONFIGURACION DEL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA CAMBIO DE CONFIGURACION DEL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA CAMBIO DE CONFIGURACION DEL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	public void sshSendCmdCreateXml(Reports report, String xmlOpi, String configEntidad, String opi, String xmlTcFile) {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA ENVIO DEL XML DEL TC AL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));

		// Inicialización de variables

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(5000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(5000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			// Se copia el string al archivo temporal xmlBaseTemp.xml
			commander.println("cd " + opi + "/testclient");
			commander.println("pwd");
			commander.println("cat > " + xmlOpi + " << EOF");
			commander.println(xmlTcFile);
			commander.println("EOF");
			// Se quitan los espacios en blanco (x windows) y se envía el resultado al
			// archivo final xmlBase.xml
		    //commander.println("sed '/^[[:space:]]*$/d' xmlBaseAux.xml > " + xmlTcFile);
			commander.println("exit");
			commander.flush();

			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA ENVIO DEL XML DEL TC AL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA ENVIO DEL XML DEL TC AL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA ENVIO DEL XML DEL TC AL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out
				.println("<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA ENVIO DEL XML DEL TC AL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	public String[] sshSendCmdGetDE37(Reports report, String xmlFileRun, String configEntidad, String opi) {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		//Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));
		String[] result = new String[2];

		//Inicialización de variables
		result[0] = "FAIL";

		try {
			//PreCondicion
			/*
			if (!getOpiStatus(configEntidad)) {
				report.AddLine("Se inicializa OPI");
				System.out.println("Se inicializa OPI");
				startOpiCmd(report,configEntidad);
			}
			 */

			//Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);
			System.out.println("Identity added");
			
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			//Channel channel = session.openChannel("shell");
			
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect();
			
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			//shell scripts
			commander.println("cd " + opi + "/testclient/");
			commander.println("pwd");
			commander.println("./tc-acq.sh " + xmlFileRun);
			commander.println("exit");
			commander.flush();
			
//			commander.println("cd /opi/opi4qa/testClient");
//			commander.println("cd " + opi + "/testClient");
//			commander.println("pwd");
//			commander.println("./tc-acq.sh " + xmlFileRun);
//			commander.println("exit");
//			commander.flush();
			
			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			//boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				//FIELD 37
				if (line.contains("field id=\"37\"")) {
					result[1] = line.split("=")[2].replace("\"", "").replace("/>","");
					System.out.println("DE37?: " + result[1]);
				}
				//FIELD 39
				if (line.contains("field id=\"39\"")) {
					result[0] = line.split("=")[2].replace("\"", "").replace("/>","");
					System.out.println("DE39?: " + result[0]);
				}

				//Pasar line a un metodo para evaluar si tiene lo que busco
			}
			System.out.println("Console result:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return result;
	}
	public String executeXml(Reports report, String xmlFileRun, String configEntidad, String opi) {
		System.out
				.println("<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));
		String result = "FAIL";

		// Inicialización de variables

		try {

			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);
			System.out.println("Identity added");

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			// Channel channel = session.openChannel("shell");

			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect();

			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell scripts
			commander.println("cd " + opi + "/testclient/");
			commander.println("pwd");
			commander.println("./tc-acq.sh " + xmlFileRun);
			commander.println("exit");
			commander.flush();

//			commander.println("cd /opi/opi4qa/testClient");
//			commander.println("cd " + opi + "/testClient");
//			commander.println("pwd");
//			commander.println("./tc-acq.sh " + xmlFileRun);
//			commander.println("exit");
//			commander.flush();

			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");

				// FIELD 39
				if (line.contains("field id=\"39\"")) {
					result = line.split("=")[2].replace("\"", "").replace("/>", "");
					System.out.println("DE39: " + result);
					
				}
				if (line.contains("unconnected ISOChannel")){
					System.out.println("***********sssssssssssssfffffffffffffffffffff");
					result="unconnected ISOChannel";
				}

				// Pasar line a un metodo para evaluar si tiene lo que busco
			}
			System.out.println("Console result:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return result;
	}
	public String executeXml1(Reports report, String xmlFileRun, String configEntidad, String opi) {
		System.out
				.println("<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));
		String result = "FAIL";

		// Inicialización de variables

		try {

			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);
			System.out.println("Identity added");

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(5000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			// Channel channel = session.openChannel("shell");

			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect();

			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell scripts
			//cd /home/opiemimcaut/opi-switch
			commander.println("cd /home/opiemimcaut/testclient");
			//commander.println("su " + opi );
			//commander.println("cd ");
			//commander.println("cd testclient");
			//commander.println("cd /home/"+ opi + "/testclient");
			//commander.println("cd " + opi + "/testclient");
			commander.println("pwd");
			commander.println("./tc-acq.sh " + xmlFileRun);
			commander.println("exit");
			commander.flush();
			

//			commander.println("cd /opi/opi4qa/testClient");
//			commander.println("cd " + opi + "/testClient");
//			commander.println("pwd");
//			commander.println("./tc-acq.sh " + xmlFileRun);
//			commander.println("exit");
//			commander.flush();

			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");

				// FIELD 39
				if (line.contains("field id=\"39\"")) {
					result = line.split("=")[2].replace("\"", "").replace("/>", "");
					System.out.println("DE39: " + result);
				}

				// Pasar line a un metodo para evaluar si tiene lo que busco
			}
			System.out.println("Console result:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA EJECUCION DE XML TC EN EL OPI >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return result;
	}
	public String getIdAutorizacionEmision(Reports report, String config_entidad, String opi) {
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DEL ID_AUTORIZACION_EMISION >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(config_entidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(config_entidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(config_entidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(config_entidad).get("OPI_CONFIG.port"));
		String idAutorizacionEmisor = "0000000";

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			commander.println("cd " + opi + "/opi-switch/logs");
			commander.println("pwd");
			commander.println("cat stdrout.log");
			commander.println("exit");
			commander.flush();
			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				// FIELD 37
				if (line.contains("IdAutorizacionEmisor")) {
					line = line.substring(32, 39);

					while (!line.matches("[0-9]*")) {
						line = line.substring(0, line.length() - 1);
					}
					// .split(":")[2].replace(",", "").replace("/>","");
					idAutorizacionEmisor = line;
					System.out.println("idAutorizacionEmisor: " + idAutorizacionEmisor);
					break;
				}
			}
			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_EMISOR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_EMISOR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_EMISOR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_EMISOR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return idAutorizacionEmisor;
	}
	public String getIdAutorizacionEmisor(Reports report, String configEntidad, String opi) {
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DEL ID_AUTORIZACION_EMISOR >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));
		String idAutorizacionEmisor = "00000";

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			commander.println("cd " + opi + "/opi-switch/logs");
			commander.println("pwd");
			commander.println("cat stdrout.log");
			commander.println("exit");
			commander.flush();
			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				
				
				if (line.contains("IdAutorizacionEmisor")) {
					sb.append(line + "\n");
					idAutorizacionEmisor = line;
					
					break;
				}
			}
			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_EMISOR A   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_EMISOR B   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_EMISOR C   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_EMISOR D   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return idAutorizacionEmisor;
	}
	/********************************idAutorizacionEmisorReverso************************/
	
	public String getIdAutorizacionEmisorReverso(Reports report, String configEntidad, String opi) {
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DEL ID_AUTORIZACION_EMISOR >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));
		String idAutorizacionEmisor = "00000";

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			commander.println("cd " + opi + "/opi-switch/logs");
			commander.println("pwd");
			commander.println("cat stdrout.log");
			commander.println("exit");
			commander.flush();
			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				// FIELD 37
				if (line.contains("Operacion")) {
					line = line.substring(297, 304);
					

					while (!line.matches("[0-9]*")) {
						line = line.substring(0, line.length() - 1);
						
					}
					// .split(":")[2].replace(",", "").replace("/>","");
					idAutorizacionEmisor = line;
					System.out.println("idAutorizacionEmisor: " + idAutorizacionEmisor);
					break;
				}
			}
			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_ADQUIRENTE A   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_ADQUIRENTE B   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_ADQUIRENTE C   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_ADQUIRENTE D   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return idAutorizacionEmisor;
	}
	
	/******************************fin**************************************************/
	
	
	/****************SOLO PARA CONFIRMAR SALDO INSUFICIENTE****************************/
	public String getIdAutorizacionEmisorSinSaldo(Reports report, String config_entidad2_infinitus, String opi) {
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DEL ID_AUTORIZACION_EMISOR >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.user");
		String host = JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(config_entidad2_infinitus).get("OPI_CONFIG.port"));
		String idAutorizacionEmisor = "00000";

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			commander.println("cd " + opi + "/opi-switch/logs");
			commander.println("pwd");
			commander.println("cat stdrout.log");
			commander.println("exit");
			commander.flush();
			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				// FIELD 37
				if (line.contains("Insuficiente")) {
					line = line.substring(298, 308);
					

					while (!line.matches("[0-9]*")) {
						line = line.substring(0, line.length() - 1);
						
					}
					// .split(":")[2].replace(",", "").replace("/>","");
					idAutorizacionEmisor = line;
					System.out.println("idAutorizacionEmisor: " + idAutorizacionEmisor);
					break;
				}
			}
			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_ADQUIRENTE A   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_ADQUIRENTE B   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_ADQUIRENTE C   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DEL ID_AUTORIZACION_ADQUIRENTE D   >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return idAutorizacionEmisor;
	}
	/**************************************FIN DE OBTENCION DE ID_AUTORIZADOR_EMISOR PARA ESTADO 51 SALDO INSUFICIENTE*****************/
	public String[] sshPreCondi(Reports report, String xmlFileRun, String config_entidad, String opi) {
		// Variables
		String privateKey = JsonPath.from(config_entidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(config_entidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(config_entidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(config_entidad).get("OPI_CONFIG.port"));
		String[] result = new String[3];

		// Inicialización de variables
		result[0] = "FAIL";
		// Instancias
		JSch jsch = new JSch();

		try {
			// PreCondicion

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			commander.println("cd " + opi + "/testclient");
			commander.println("pwd");

			commander.println("./tc-acq.sh xmlBaseAux.xml");

			commander.println("exit");
			commander.flush();

			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				// FIELD 37
				if (line.contains("field id=\"37\"")) {
					result[1] = line.split("=")[2].replace("\"", "").replace("/>", "");
				}
				// FIELD 38
				if (line.contains("field id=\"38\"")) {
					result[2] = line.split("=")[2].replace("\"", "").replace("/>", "");
				}
				// FIELD 39
				if (line.contains("field id=\"39\"")) {
					result[0] = line.split("=")[2].replace("\"", "").replace("/>", "");
				}

				// Pasar line a un metodo para evaluar si tiene lo que busco
			}
			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();
			if (!result[0].equals("91"))

				report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}
	public String getMessages(Reports report, String configEntidad, String opi) {
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< INICIA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Variables
		String privateKey = JsonPath.from(configEntidad).get("OPI_CONFIG.privateKey");
		String user = JsonPath.from(configEntidad).get("OPI_CONFIG.user");
		String host = JsonPath.from(configEntidad).get("OPI_CONFIG.host");
		int port = Integer.valueOf(JsonPath.from(configEntidad).get("OPI_CONFIG.port"));
		String xmlMessage = "";

		try {
			// Instancias
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			report.AddLine("Creating Session...");
			System.out.println("Creating Session...");

			jsch.addIdentity(privateKey);

			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(2000);
			String variable = "";
			report.AddLine("Session connected" + variable);
			System.out.println("Session connected");

			Channel channel = session.openChannel("shell");
			report.AddLine("Opening Channel... ");
			System.out.println("Opening Channel... ");

			OutputStream inputstream_for_the_channel = channel.getOutputStream();
			PrintStream commander = new PrintStream(inputstream_for_the_channel, true);

			channel.setOutputStream(null);
			channel.connect(2000);
			report.AddLine("Channel connected");
			System.out.println("Channel connected");

			// shell script
			commander.println("cd " + opi + "/opi-switch/logs");
			commander.println("pwd");
			commander.println("cat stdrout.log");
			commander.println("exit");
			commander.flush();
			System.out.println("" + channel.getExitStatus());

			InputStream outputstream_from_the_channel = channel.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			boolean saveLine = false;
			StringBuilder sb = new StringBuilder();
			// boolean isloginStringPassed = false ;

			while ((line = br.readLine()) != null) {

				if (line.contains("<receive>") || line.contains("<send>")) {
					saveLine = true;
				}

				if (line.contains("</receive>") || line.contains("</send>")) {
					saveLine = false;
					xmlMessage += line + "\n" + "-separator-" + "\n";
				}

				if (saveLine) {
					xmlMessage += line + "\n";
				}
			}

			System.out.println("Console reslut:\r\n" + sb.toString());

			channel.disconnect();
			session.disconnect();

			report.AddLine("Completed");
			System.out.println("Completed");

		} catch (JSchException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		System.out.println(
				"<<<<<<<<<<<<<<<<<<<<<<<<<< FINALIZA OBTENCION DE LOS DATA ELEMENTS A VALIDAR >>>>>>>>>>>>>>>>>>>>>>>>>>>");

		return xmlMessage;
	}
	
}
