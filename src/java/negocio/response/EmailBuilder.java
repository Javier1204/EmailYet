/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import negocio.dao.interfaces.IExcelContent;
import negocio.dto.PersonaDTO;

/**
 * Clase Email Builder. Se encarga de recibir la lista de persona y enviar email a cada una
 * Recorre la lista enviada y retorna el resultado de la operación
 * @author javie
 */
public class EmailBuilder {
    
    private EmailResponse email;

    public EmailBuilder() {
        email = new EmailResponse();
    }
    
    //Recibe la lista y la recorre delegando a email (Atributo) el envío del correo
    public boolean enviarEmails(IExcelContent tabla, String contenidoMensaje, String asunto, int columna){
        //Recorrer lista y enviar con email.sendEmail() 
        /*Recibir cada boolean de email.sendEmail() y actualizar boolean a retornar. Si email.sendEmail retorna un false
        se cambia a false el boolean de este método. Contar cantidad de false recibidos de email
        */
        String errores=this.checkEmailSyntax(contenidoMensaje,tabla);
        if(!errores.isEmpty())return false;
        String correos="";
        ArrayList<String> correosEnviados=new ArrayList<>();
        for (int i=0; i<tabla.getDatos().size();i++) {
            String body=this.buildBody(i,contenidoMensaje, tabla);
            this.sendEmailIndividualSingle("djjaemaiyet@gmail.com","passworddjja" ,tabla.getDatos().get(i).get(columna), body,asunto);
        }
        
        
        
        return false;
    }
     private String buildBody(int posicion, String contenido, IExcelContent tabla){
        String bodyEmail=contenido;
        for (int i = 0; i < tabla.getTitulos().size(); i++) {
            bodyEmail=bodyEmail.replace("$"+tabla.getTitulos().get(i)+"$",tabla.getDatos().get(posicion).get(i));
        }        
        return bodyEmail;
    }
    
    public boolean sendEmailIndividualSingle( String emailUser, String emailPass, String emailDestino, String contenido, String asunto){
         Properties propiedades = System.getProperties();
          propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
          propiedades.setProperty("mail.smtp.starttls.enable", "true");
          propiedades.setProperty("mail.smtp.port", "587");
          propiedades.setProperty("mail.user", emailUser); 
          propiedades.setProperty("mail.password", emailPass);
          propiedades.setProperty("mail.smtp.auth", "true");
          Session sesion = Session.getDefaultInstance(propiedades);
          try{
              Autenticador autenticador = new Autenticador();
               MimeMessage mensaje = new MimeMessage(Session.getInstance(propiedades, autenticador));
               mensaje.setFrom(new InternetAddress(emailUser));
               mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDestino));
                mensaje.setSubject(asunto);
                mensaje.setText(contenido);
                Transport.send(mensaje);
                return true;
          } catch (MessagingException ex) {
            Logger.getLogger(EmailBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
     
    public String construirMensaje(String contenidoMensaje){
        //Validar sintaxis y construir mensaje con los datos reales
        return "";
    }
    private String checkEmailSyntax(String contenido, IExcelContent tabla){
        String errores="";
        int numError=1;
        String bodyEmail=contenido;
        for (int i = 0; i < tabla.getTitulos().size(); i++) {
            bodyEmail=bodyEmail.replace("$"+tabla.getTitulos().get(i)+"$",tabla.getTitulos().get(i));
        }
        char c[]=bodyEmail.toCharArray();
        for (int i=0;i<c.length;i++) {
            if(c[i]=='$'){
                boolean encontro=false;
                int j=i+1;
                for (; j < c.length; j++) {
                    if(c[j]=='$'){
                        encontro=!encontro;
                        break;
                    }
                }
                
                if(!encontro){
                    errores+="\n N°"+numError+": posee $abiertos sin cerrar";
                    numError++;
                    break;
                }else{
                    String comando=bodyEmail.substring(i, j+1);
                    if(comando.isEmpty())errores+="\n N°"+numError+": Posee comandos vacios,ees decir, sin asignar una columna: "+comando;
                    else errores+="\n N°"+numError+": El comando "+comando+" no tiene un titulo de columna correspondiente";
                    numError++;
                }
            }
        }
        return errores;
    }
    
    public static void main(String[] args) {
        EmailBuilder eb=new EmailBuilder();
        eb.sendEmailIndividualSingle("djjaemaiyet@gmail.com", "passworddjja", "deniseduardoisidrogonzalez@gmail.com", "Prueba de correo", "Este correo si se envía");
    }
}
