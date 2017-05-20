/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.daos;

import java.util.ArrayList;
import negocio.dao.interfaces.IPersonaDAO;
import negocio.dto.PersonaDTO;
import util.Conexion;

/**
 *
 * @author javie
 */
public class PersonaDAO implements IPersonaDAO{

    private Conexion conn; 
    
    @Override
    public boolean registrarPersona(String nombre, String apellidos, String calificacion, String codigo, String obser, String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonaDTO consultarPersona() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PersonaDTO> listarPersonas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
