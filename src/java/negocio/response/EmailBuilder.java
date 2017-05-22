/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.response;

import java.util.List;
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
    public boolean enviarEmails(IExcelContent lista, String contenidoMensaje, String asunto, int columna){
        //Recorrer lista y enviar con email.sendEmail() 
        /*Recibir cada boolean de email.sendEmail() y actualizar boolean a retornar. Si email.sendEmail retorna un false
        se cambia a false el boolean de este método. Contar cantidad de false recibidos de email
        */
        return false;
    }
     
    public String construirMensaje(String contenidoMensaje, PersonaDTO persona){
        //Validar sintaxis y construir mensaje con los datos reales
        return "";
    }
    
}
