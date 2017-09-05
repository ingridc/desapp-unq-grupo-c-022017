package model;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mail {

	private static Mail instance = null;
	private SimpleEmail mail = new SimpleEmail();

	public Mail() throws EmailException {
		mail.setHostName("smtp.gmail.com");
		// mail.setTLS(true);
		mail.setStartTLSEnabled(true);
		mail.setSmtpPort(587);
		// mail.setSSL(true);
		mail.setSSLOnConnect(true);
		mail.setAuthentication("MorfiYa2017@gmail.com", "2017Morf");
		mail.setFrom("MorfiYa2017@gmail.com");

	}

	public Mail getInstance() throws EmailException {
		if (instance == null) {
			instance = new Mail();
		}
		return instance;
	}

	public void send(String to, String subject, String mensagge) throws EmailException {
		mail.addTo(to);
		mail.setSubject(subject);
		mail.setMsg(mensagge);
		mail.send();
	}

	public void sendMailDisabledProvider(String userEmailAddress) throws EmailException {
		this.send(userEmailAddress, "Proveedor Inactivo", "Estimado Usuario su cuenta a sido inhabilitada");
	}

}