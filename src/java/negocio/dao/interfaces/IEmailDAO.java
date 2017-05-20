/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.dao.interfaces;

import java.util.ArrayList;
import negocio.dto.EmailDTO;

/**
 *
 * @author javie
 */
public interface IEmailDAO {
    
    public boolean registrarEmail(EmailDTO e);
    public ArrayList<EmailDTO> listarEmails();
    
}
