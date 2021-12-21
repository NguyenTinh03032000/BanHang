package control.Cart;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Account;

@WebServlet(name = "sendEmailControl",urlPatterns = {"/sendEmail"} )
public class sendEmailControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
		final String username = "nguyenvantinh030320@gmail.com";
        final String password = "";
        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","465");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.socketFactory.port", "465");    
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
        });
        HttpSession sessionacc = request.getSession();
        String accMail = (String)sessionacc.getAttribute("accMail");
        String emailTo = "nguyentinh03032000@gmail.com";
        String emailSubject = "Ban da mua hang tai MobileCity";
        String emailContent = "Chuc mung ban mua hang thanh cong";
        try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));
			message.setSubject(emailSubject);
			message.setText(emailContent);
			Transport.send(message);
		} catch (Exception e) {
			// TODO: handle exception
		}
        response.sendRedirect("home");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);		
	}
}
