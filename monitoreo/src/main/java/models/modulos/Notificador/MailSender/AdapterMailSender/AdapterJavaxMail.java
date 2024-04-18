package models.modulos.Notificador.MailSender.AdapterMailSender;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.InvalidParameterException;
import java.util.Properties;
public class AdapterJavaxMail implements AdapterMailSender{
  private Properties prop;
  private Session sesion;

  public AdapterJavaxMail()  {
    String username = "Grupo10DDS@gmail.com";
    String password = "qszf rnsd sexo brnx";
    this.prop= new Properties();
    this.loadConfig();
    this.sesion = Session.getInstance(prop,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
          }
        });
  }

  private void loadConfig() throws InvalidParameterException {
    this.prop.put("mail.smtp.host","smtp.gmail.com");
    this.prop.put("mail.smtp.port","25");
    this.prop.put("mail.smtp.user","Grupo10DDS@gmail.com");
    this.prop.put("mail.smtp.password","qszf rnsd sexo brnx");
    this.prop.put("mail.smtp.starttls.enable","true");
    this.prop.put("mail.smtp.auth","true");
    this.checkConfig();
  }

  private void checkConfig() throws InvalidParameterException{
    String[] keys = {
        "mail.smtp.host",
        "mail.smtp.port",
        "mail.smtp.user",
        "mail.smtp.password",
        "mail.smtp.starttls.enable",
        "mail.smtp.auth"
    };

    for(int i = 0; i < keys.length; i++){
      if(this.prop.get(keys[i]) == null){
        throw new InvalidParameterException("No existe esa clave" + keys[i]);
      }
    }
  }

  @Override
  public void notificar(String correoDestino, String mensaje, String asunto) {
    MimeMessage contenedor = new MimeMessage(sesion);
    try {
      contenedor.setFrom(new InternetAddress((String) this.prop.get("mail.smtp.user")));
      contenedor.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
      contenedor.setSubject(asunto);
      contenedor.setText(mensaje);
      Transport transporte = sesion.getTransport("smtp");
      transporte.connect((String) this.prop.get("mail.smtp.user"), (String) this.prop.get("mail.smtp.password"));
      transporte.sendMessage(contenedor, contenedor.getAllRecipients());
    } catch (MessagingException e) {
      throw new RuntimeException("Algo salio mal: " + e.getMessage());
    }
  }

}
