package br.ufal.bibliweb;
		
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.io.File;
import java.util.List;
import java.util.Iterator;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Enumeration;

/**
 * Config.java
 *
 * @author Marcello de Sales
 */
public class Config{
	
	private Vector databases;
	private Hashtable directories;
	private Hashtable emailServer;
	private Hashtable chat;
	private Hashtable whiteboard;
	
	private FileReader configurationFile;
	private BufferedReader readerOfFile;
	private Hashtable hash;
	private Vector variables;
	private final String TXT_CONF = "e:/desenv/nonamepibic/config/graW.txt";
	private final String XML_CONF = "e:/desenv/bibliweb/config.xml";
//	private final String XML_CONF = "/home/graw/conf/graW.xml";
	
	public Config(){
		boolean readFromXML = true;
		
		this.directories = new Hashtable();
		this.emailServer = new Hashtable();
		this.chat        = new Hashtable();
		this.whiteboard  = new Hashtable();
		this.databases   = new Vector();
		if (readFromXML)
			this.initializeXMLConf();
		else this.initializeTXTConf();
	}
	
	private void initializeTXTConf(){
			try {
				//Ler o arquivo com as configuracoes
				this.configurationFile = new FileReader(this.TXT_CONF);
		 		this.readerOfFile      = new BufferedReader(configurationFile);
				this.hash              = new Hashtable();
				this.variables         = new Vector();
				this.readConfigFile();

			} catch(Exception e) {
				System.err.println(e.getMessage());

			} finally {

				try {
					readerOfFile.close();
					configurationFile.close();

				} catch(IOException a) {
				} catch (Exception e){
					System.err.println("Config.java : "+e.getMessage());
				}
			}
	}
	
	private void readConfigFile() {
		String linha = new String();
		Hashtable coisas = new Hashtable();
		try {
			while((linha = this.readerOfFile.readLine()) != null ){
				StringTokenizer token = new StringTokenizer(linha);
				String chave = token.nextToken();
				this.variables.addElement(chave);
				coisas.put(chave,token.nextToken());
			}
			this.databases.add(coisas);
		} catch(IOException f) {
			System.err.println("Arquivo "+this.configurationFile + " não encontrado!");
		}
	}
	
	private void initializeXMLConf(){
		try {
			SAXBuilder saxBuilder = new SAXBuilder(false);
			org.jdom.Document config = saxBuilder.build(new File(this.XML_CONF));
			this.parseConfigFile(config);
		} catch (JDOMException jde){
			System.err.println(jde.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,jde);
		}
	}
	
	private void parseConfigFile(org.jdom.Document configFile){
		Element root      = configFile.getRootElement();
		Element databases = root.getChild("databases");
		Element website   = root.getChild("website");
		
		this.databases = this.parseDatabases(databases);
		this.parseWebsite(website);
	}
	
	private Vector parseDatabases(Element databases){
		Vector list      = new Vector();
		List vendor      = databases.getChildren("vendor");
		Iterator iter    = vendor.iterator();
		Hashtable colums;
		while (iter.hasNext()){
			colums = new Hashtable();
			Element element = (Element)iter.next();
			
			String name    = element.getChild("name").getText();
			String version = element.getChild("version").getText();
			String driver  = element.getChild("driver").getText();
			String local   = element.getChild("local").getText();
			
			Element admin   = element.getChild("administrator");
			String username = admin.getChild("username").getText();
			String password = admin.getChild("password").getText();
			colums.put("name", name);
			colums.put("version", version);
			colums.put("driver", driver);
			colums.put("local", local);
			colums.put("username", username);
			colums.put("password", password);
			list.add(colums);
		}
		return list;
	}
	
	private void parseWebsite(Element website){
		Element directory = website.getChild("directory");
		this.directories.put("documentRoot", directory.getChild("documentRoot").getText());
		this.directories.put("servlet", directory.getChild("servlet").getText());
		this.directories.put("images", directory.getChild("images").getText());
		this.directories.put("upload", directory.getChild("upload").getText());
		
		Element emailServer = website.getChild("emailserver");
		this.emailServer.put("smtp", emailServer.getChild("smtp").getText());
		this.emailServer.put("port", emailServer.getChild("port").getText());
		this.emailServer.put("serviceID", emailServer.getChild("serviceID").getText());
		
		Element chat = website.getChild("chat");
		this.chat.put("socket", chat.getChild("socket").getText());
		this.chat.put("rmi", chat.getChild("rmi").getText());
		this.chat.put("server", chat.getChild("server").getText());
		this.chat.put("html", chat.getChild("html").getText());
		this.chat.put("servlet", chat.getChild("servlet").getText());
		
		Element whiteboard = website.getChild("whiteboard");
		this.whiteboard.put("socket", whiteboard.getChild("socket").getText());
		this.whiteboard.put("server", whiteboard.getChild("server").getText());
	}
		
	public Vector getDatabases(){
		return this.databases;
	}
	
	public String getDatabaseURL(){
		return (String)((Hashtable)this.databases.elementAt(1)).get("local");
	}
	
	public String getDatabaseDriver(){
		return (String)((Hashtable)this.databases.elementAt(1)).get("driver");
	}

	public String getDatabaseUsername(){
		return (String)((Hashtable)this.databases.elementAt(1)).get("username");
	}
	
	public String getDatabasePassword(){
		String a = (String)((Hashtable)this.databases.elementAt(1)).get("password");
		return (a.equals("null")) ? "" : a;
	}

	public String getDocumentRoot(){
		return (String)this.directories.get("documentRoot");
	}
	
	public String getServletDir(){
		return (String)this.directories.get("servlet");
	}
	
	public String getImageDir(){
		return (String)this.directories.get("images");
	}
	
	public String getUploadDir(){
		return (String)this.directories.get("upload");
	}
	
	public String getSMTPHost(){
		return (String)this.emailServer.get("smtp");
	}

	public String getSMTPPort(){
		return (String)this.emailServer.get("port");
	}

	public String getEmailServiceID(){
		return (String)this.emailServer.get("serviceID");
	}
	
	public String getChat(String key) {
		return this.chat.get(key).toString();
	}
	
	public String getWhiteBoard(String key) {
		return this.whiteboard.get(key).toString();
	}
	
	public void printConfig(){
		System.out.println("Bancos de dados");
		Enumeration e = this.databases.elements();
		while (e.hasMoreElements()){
			Hashtable database = (Hashtable)e.nextElement();
			System.out.println("Fabricante  = " +(String)database.get("name"));
			System.out.println("Versao      = " +(String)database.get("version"));
			System.out.println("JAVA Driver = " +(String)database.get("driver"));
			System.out.println("Local       = " +(String)database.get("local"));
			System.out.println("Administrador: " +(String)database.get("username")+" / "+(String)database.get("password"));
			System.out.println();
		}
		System.out.println();
		System.out.println("WebSite Directories");
		System.out.println("DocumentRoot = "+this.getDocumentRoot());
		System.out.println("Servlet      = "+this.getServletDir());
		System.out.println("Image        = "+this.getImageDir());
		System.out.println("Upload       = "+this.getUploadDir());
		System.out.println();
		System.out.println("Email Server");
		System.out.println("SMTP         = "+this.getSMTPHost());
		System.out.println("Porta        = "+this.getSMTPPort());
		System.out.println("ServiceID    = "+this.getEmailServiceID());
		System.out.println();
		System.out.println("Chat Server");

		System.out.println("Socket         = "+this.getChat("socket"));
		System.out.println("RMI            = "+this.getChat("rmi"));
		System.out.println("Server         = "+this.getChat("server"));
		System.out.println("HTML           = "+this.getChat("html"));
		System.out.println("Servlet        = "+this.getChat("servlet"));
	}
		
	public static void main(String[] args){
		System.out.println(new GregorianCalendar().getTime());
		Config config = new Config();
		System.out.println(new GregorianCalendar().getTime());
		System.out.println("Read of the configuration file completed!");
		
		config.printConfig();
	}
}
