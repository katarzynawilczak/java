package pkg1;
//nalezy dodac java.mail do classpath przed wysylaniem
import java.util.LinkedList;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Send {

		public static void main(String[] args) {
			LinkedList<String> ll = new LinkedList<String>();
			ll.add("emailTo@wp.pl");
			EmailMessage email = EmailMessage.builder("emailFrom@wp.pl",ll)
				 .addSubject("Mail testowy")
				 .addcontent("Brak tresci")
				 .build();
			
			email.Print();
			
			//Sending:
			
			String host="smtp.wp.pl"; 
			final String user=email.From();
			final String password="*****";//nalezy wpisac poprawne haslo  
			String to = email.To().get(0);
		   
			Properties props = new Properties();  
			props.put("mail.smtp.host",host);  
			props.put("mail.smtp.auth", "true");  
			     
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {  
				protected PasswordAuthentication getPasswordAuthentication() {  
					return new PasswordAuthentication(user,password);  
			    }  
			});
			   
			try {  
				MimeMessage message = new MimeMessage(session);  
				message.setFrom(new InternetAddress(user));  
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
				message.setSubject(email.Subject());  
				message.setText(email.Content());  
				       
				//wysylanie:
				Transport.send(message);  
				  
				System.out.println("message sent successfully...");  
				   
			} catch (MessagingException e) {e.printStackTrace();}  
		   
		}

}
