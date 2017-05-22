/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.daos;

import java.util.ArrayList;
import negocio.dao.interfaces.IEmailDAO;
import negocio.dto.EmailDTO;
import util.Conexion;

/**
 *
 * @author javie
 */
public class EmailDAO implements IEmailDAO{

    private Conexion conn; 
    
    @Override
    public boolean registrarEmail(EmailDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EmailDTO> listarEmails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
