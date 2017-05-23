/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.response;

import negocio.dto.EmailDTO;
import negocio.dto.PersonaDTO;
import negocio.interfaces.InterfaceEmailResponse;

/**
 * Clase EmailResponse. Es encarga de enviar el email individual y pedir a los DAOs la inserción en la bd
 * @author javie
 */
public class EmailResponse implements InterfaceEmailResponse{

    @Override
    public boolean enviarEmail(PersonaDTO persona, EmailDTO email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
