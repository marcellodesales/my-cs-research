/**
 * Class for Mail Transactions.
 */

package br.ufal.graw.web;

import java.util.Properties;
import java.util.Enumeration;
import java.util.Vector;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.BodyPart;
import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.MessagingException;

import java.io.UnsupportedEncodingException;

import java.net.URL;

import br.ufal.graw.exception.MailException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.User;
import br.ufal.graw.Group;
import br.ufal.graw.Course;
import br.ufal.graw.Community;
import br.ufal.graw.Utility;
import br.ufal.graw.Config;

public class Mail{
	
	public static final String serviceID = "[graW] ";
	private static String hostMailServer;
	private static String adminEmail="graw@tci.ufal.br";

	private static String[] signature = {
		"                                                        ",
		"                                                        ",
		"                                       M''MMM''MMM''M   ",
		"                                       M  MMM  MMM  M   ",
		"            .d8888b. 88d888b. .d8888b. M  MMP  MMP  M   ",
		"            88'  `88 88'  `88 88'  `88 M  MM'  MM' .M   ",
		"            88.  .88 88       88.  .88 M  `' . '' .MM   ",
		"            `8888P88 dP       `88888P8 M    .d  .dMMM   ",
		"                 .88                   MMMMMMMMMMMMMM   ",
		"             d8888P   Comunidades de graduação na Web   ",
		"                                                        ",
		"          	          Ciência da Computação              ",
		"          Departamento de Tecnologia da Informação (TCI)",
		"              Universidade Federal de Alagoas (UFAL)    ",
		"                          Alagoas - Brasil              ",
		"                     Contato: graw@tci.ufal.br          ",
		"                                                        ",
		"                                                        "
	};
			
	private static String getContentsWithSignature(String messageContents){
		String[] signature = Mail.signature;
		StringBuffer newSignature = new StringBuffer(messageContents);
		for (int i = 0; i < signature.length; i++){
			newSignature.append("\n"+signature[i]);
		}
		return newSignature.toString();
	}
	
	public static void send(String fromAddress, String fromName, String toAddress,
							String subject, String messageContents)
	throws MailException {
		try{
			hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : Mail.hostMailServer;

			if (fromName == null) fromName="";
			// Get system properties
			Properties props = System.getProperties();

			// Setup mail server
			props.put("mail.smtp.host", hostMailServer);

			// Get session
			Session session = Session.getDefaultInstance(props, null);

			// Define message
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(fromAddress,fromName));
			message.addRecipient(Message.RecipientType.TO ,new InternetAddress(toAddress));
			message.setSubject(Mail.serviceID+subject);
			message.setText(messageContents);

			// Send message
			Transport.send(message);
		}catch(Exception e ){
			Utility.log(Utility.ERROR_FILE_LOG,e);
			throw new MailException(e.getMessage());
		}
	}

	public static void send(String fromAddress, String fromName, String toAddress[],
							String subject, String messageContents)
	throws MailException {

		try{
			hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : hostMailServer;
			InternetAddress toInternetAddress[] = new InternetAddress[toAddress.length];

			if (fromName==null) fromName="";
			// Get system properties
			Properties props = System.getProperties();

			// Setup mail server
			props.put("mail.smtp.host", hostMailServer);

			// Get session
			Session session = Session.getDefaultInstance(props, null);

			// Define message
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(fromAddress,fromName));

			//Prepare the ToAddress
			for (int i=0 ; i< toInternetAddress.length ; i++){
				toInternetAddress[i] = new InternetAddress(toAddress[i]);
			}
			message.addRecipients(Message.RecipientType.TO ,toInternetAddress);
			message.setSubject(Mail.serviceID+subject);
			message.setText(messageContents);

			// Send message
			Transport.send(message);
		}catch(Exception e ){
			Utility.log(Utility.ERROR_FILE_LOG,e);
			throw new MailException(e.getMessage());
		}
	}
	
	/**
	Envia email com attachments.
	@param files[] - Correspondem aos nomes(completos) dos arquivos. Note que os arquivos precisam
	estar no servidor.
	 */
	public static void send(String fromAddress, String fromName, String toAddress[],
							String subject, String messageContents, String files[])
	throws MailException {

		try{
			hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : hostMailServer;
			InternetAddress toInternetAddress[] = new InternetAddress[toAddress.length];

			if (fromName==null) fromName="";
			// Get system properties
			Properties props = System.getProperties();

			// Setup mail server
			props.put("mail.smtp.host", hostMailServer);

			// Get session
			Session session = Session.getDefaultInstance(props, null);

			// Define message
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(fromAddress,fromName));

			//Prepare the ToAddress
			for (int i=0 ; i< toInternetAddress.length ; i++){
				toInternetAddress[i] = new InternetAddress(toAddress[i]);
			}
			message.addRecipients(Message.RecipientType.TO ,toInternetAddress);
			message.setSubject(Mail.serviceID+subject);
			
			BodyPart messageBodyPart = new MimeBodyPart();
			
			/* Seta o conteúdo da mensagem */
			messageBodyPart.setText(messageContents);
			
			/* Cuida do attachment */
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			DataSource source;
			/* Adiciona todos os arquivos */
			for (int i=0 ; i < files.length ; i++ ){
				source = new FileDataSource(files[i]);
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(source.getName());
				multipart.addBodyPart(messageBodyPart);
			}

			// Put parts in message
			message.setContent(multipart);

			// Send message
			Transport.send(message);
		}catch(Exception e ){
			e.printStackTrace();
			throw new MailException(e.getMessage());
		}
	}
	

	public static void send(User sender, User to, String subject, String messageContents)
	throws MailException{
		send(sender.getEmail(),sender.getName(),to.getEmail(),subject,messageContents);
	}
	
	public static void send(User sender, User to, String subject, String messageContents, String files[])
	throws MailException{
		send(sender.getEmail(),sender.getName(),new String[] {to.getEmail()},subject,messageContents,files);
	}
	
	
	public static void send(User sender, String toAddress, String subject, String messageContents,String files[])
	throws MailException{
		send(sender.getEmail(),sender.getName(),new String[]{toAddress},subject,messageContents,files);
	}
	
	public static void send(User sender, String toAddress, String subject, String messageContents)
	throws MailException{
		send(sender.getEmail(),sender.getName(),toAddress,subject,messageContents);
	}

	public static void send(User sender, User[] to, String subject, String messageContents)
	throws MailException{
		hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : hostMailServer;
		String destinationMailAddress[] =  new String[to.length];
		for (int i=0 ; i < destinationMailAddress.length ; i++){
			destinationMailAddress[i] = to[i].getEmail();
		}
		send(sender.getEmail(),sender.getName(),destinationMailAddress,subject,messageContents);
	}
	
	public static void send(User sender , Community to , String subject , String messageContents)
	throws MailException{
		
		hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : hostMailServer;
		Vector members = to.getMembers();
		Vector destinationAddress = new Vector();
		
		for (int i=0 ; i< members.size() ; i++){
			destinationAddress.add(((User)members.get(i)).getEmail());
		}
		String destinationMailAddress[] =  new String[destinationAddress.size()];
		destinationAddress.toArray(destinationMailAddress);
		send(sender.getEmail(),sender.getName(),destinationMailAddress,subject,messageContents);
	}
	
	public static void send(User sender , Community to , String subject , String messageContents, String files[])
	throws MailException{
		
		hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : hostMailServer;
		Vector members = to.getMembers();
		Vector destinationAddress = new Vector();
		
		for (int i=0 ; i< members.size() ; i++){
			destinationAddress.add(((User)members.get(i)).getEmail());
		}
		String destinationMailAddress[] =  new String[destinationAddress.size()];
		destinationAddress.toArray(destinationMailAddress);
		send(sender.getEmail(),sender.getName(),destinationMailAddress,subject,messageContents,files);
	}
	
	public static void log(String message){
		try{
			Mail.send(adminEmail,"Servidor graW",adminEmail,"Erro de operação",message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void log(Throwable throwable){
		try{
			Mail.send(adminEmail,"Servidor graW",adminEmail,"Erro de operação - Exceção não esperada.",Utility.getStackTrace(throwable));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void log(Throwable throwable, String message){
		try{
			Mail.send(adminEmail,"Servidor graW",adminEmail,"Erro de operação - Exceção não esperada.",Utility.getStackTrace(throwable)+
					  "\n\n##################################################\n"+
					  "Mensagem:\n"+message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*	public static void send(User sender, Group to, String subject, String messageContents)
	throws MailException{
		try{
			hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : hostMailServer;
			Enumeration members = to.getMembers();
			Vector destinationAddress = new Vector();
			
			while (members.hasMoreElements()){
				destinationAddress.add(((User)members.nextElement()).getEmail());
			}
			String destinationMailAddress[] =  new String[destinationAddress.size()];
			destinationAddress.toArray(destinationMailAddress);
			send(sender.getEmail(),sender.getName(),destinationMailAddress,subject,messageContents);
		}catch(UserNotFoundException unfe){
			throw new MailException("Impossível mandar email para todo o grupo. "+unfe.getMessage());
		}
	 }*/
	
	/*	public static void send(User sender, Course to, String subject, String messageContents)
	throws MailException{
		try{
			hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : hostMailServer;
			Enumeration members = to.getMembers();
			Vector destinationAddress = new Vector();
			
			while (members.hasMoreElements()){
				destinationAddress.add(((User)members.nextElement()).getEmail());
			}
			String destinationMailAddress[] =  new String[destinationAddress.size()];
			destinationAddress.toArray(destinationMailAddress);
			send(sender.getEmail(),sender.getName(),destinationMailAddress,subject,messageContents);
		}catch(UserNotFoundException unfe){
			throw new MailException("Impossível mandar email para todo o curso. "+unfe.getMessage());
		}
	 }*/
	
	
	
	public static void sendBlind(String fromAddress, String fromName, String toAddress[],
							String subject, String messageContents)
	throws MailException {

		try{
			hostMailServer = (hostMailServer==null) ? new Config().getSMTPHost() : hostMailServer;
			InternetAddress toInternetAddress[] = new InternetAddress[toAddress.length];

			if (fromName==null) fromName="";
			// Get system properties
			Properties props = System.getProperties();

			// Setup mail server
			props.put("mail.smtp.host", hostMailServer);

			// Get session
			Session session = Session.getDefaultInstance(props, null);

			// Define message
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(fromAddress,fromName));

			//Prepare the ToAddress
			for (int i=0 ; i< toInternetAddress.length ; i++){
				toInternetAddress[i] = new InternetAddress(toAddress[i]);
			}
			message.addRecipients(Message.RecipientType.TO ,toInternetAddress);
			message.setSubject(Mail.serviceID+subject);
			message.setText(messageContents);

			// Send message
			Transport.send(message);
		}catch(Exception e ){
			Utility.log(Utility.ERROR_FILE_LOG,e);
			throw new MailException(e.getMessage());
		}
	}

	/**
	 * This method is called after start up.
	 *
	 * @param args   The command-line arguments.
	 *               They can be changed in the
	 *               "Project Settings" dialog in CodeGuide.
	 */
	public static void main(String[] args)
	{
		// Print application prompt to console.
		System.out.println("Mail");

		try{
			//String destination[] = {"mosca@ieg.com.br","r0drigopaes@yahoo.com.br"};
			//Mail.send("mosca@ieg.com.br","|Rei-MoMo|",destination,"multiplos","Eis aqui o conteúdo.");
			//br.ufal.graw.DatabaseLayer db = new br.ufal.graw.DatabaseLayer();
			//db.connect();
			//br.ufal.graw.Student from = new br.ufal.graw.Student("1999G55D041V",db);
			//br.ufal.graw.Student to = new br.ufal.graw.Student("2001G55D041V",db);
			//il.send("masj@tci.ufal.br","",new String[]{"r0drigopaes@yahoo.com.br"},"Testes com attachments","Corpo da mensagem",new String[] {"c:\\download\\rawrite.exe","c:\\download\\Eudora51-crack.zip"});
			/////////////Mail.send("masj@tci.ufal.br","Marcello","masj@tci.ufal.br","Testes com attachments","Corpo da mensagem");
			String corpo = "ddddd";
			corpo = Mail.getContentsWithSignature(corpo);
			System.out.println(corpo);
			/*String email="rosarionet@bol.com.br";
			String name = "Evandro de Barros Costa";
			String title = "Gestao do Conhecimento";
			String senha="1017693678060";
			String message="Francisco,\n  vc estará recebendo informações sobre como proceder na GraW";
			 */
			/*Mail.send("graw@tci.ufal.br",Mail.serviceID,email,"Você recebeu um convite de "+name+" para ingressar na comunidade de "+title,
			"Parabéns !\nVocê foi "+
			"convidado por "+name+" a participar da comunidade '"+title+"'\n"+
    		"Se você já é um usuário do graW, basta acessar o menu de convites e digitar a senha do convite. SENHA="+senha+"\n"+
			"Caso voce ainda não seja usuário do graW acesse: "+ServletUtility.INVITATION_GUEST+"?communityGuestsID="+senha+" para realizar um cadastro e acessar os recursos do graW."+
			"Para rejeitar este convite basta ignorar esta mensagem.\nATENÇÃO: Você tem 15 dias para confirmar o convite a partir da seguinte data: "+new java.util.Date()+"\n"+
					 "MENSAGEM DO CONVITE: \n"+
					 message);
			 */
			System.out.println(ServletUtility.INVITATION_GUEST);

		}catch(Exception e){
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}

		System.out.println("Finished! Press ENTER to continue.");

	}


}
