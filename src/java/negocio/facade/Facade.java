/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.facade;

import java.util.List;
import negocio.dto.PersonaDTO;
import negocio.interfaces.InterfaceEmailResponse;
import negocio.interfaces.InterfaceExcel;
import negocio.response.EmailBuilder;

/**
 * Clase Facade. Clase controlador. La separo del servlet para no hacer masacote de código
 * Se encarga de recibir la ruta, pedir el procesamiento del archivo y envío de los emails para retornar el resultado 
 * @author javie
 */
public class Facade {
    private InterfaceExcel excel_helper;
    private EmailBuilder email_helper;
    
    public boolean IniciarSesion(String user, String pass){
        return false;
    }
    
    public boolean leerArchivo(String ruta, String contenido, String asunto){
        //Pedir a excel_helper que retorne la lista de Personas a través de leerArchivo
        excel_helper.leerArchivo(ruta);
        //Delegar función de envío de email a enviarEmail (Abajo). Retornar su boolean
        return false;
    }
    
    public boolean enviarEmail(List<PersonaDTO> personas, String contenido, String asunto){
        //Al tener el list pedir al email_helper enviar los email. email_helper (Instacia de EmailBuilder)
        email_helper.enviarEmails(personas, contenido, asunto);
        //Retornar boolean de email_helper
        return false;
    }
}
