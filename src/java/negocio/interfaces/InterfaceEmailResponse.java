/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.interfaces;

import negocio.dto.EmailDTO;
import negocio.dto.PersonaDTO;

/**
 *Interface para enviar el email.
 * @author javie
 */
public interface InterfaceEmailResponse {
    
    public boolean enviarEmail(PersonaDTO persona, EmailDTO email);
    
}
