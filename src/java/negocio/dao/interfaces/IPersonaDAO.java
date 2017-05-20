/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.dao.interfaces;

import java.util.ArrayList;
import negocio.dto.PersonaDTO;

/**
 *
 * @author javie
 */
public interface IPersonaDAO {
    
    public boolean registrarPersona(String nombre, String apellidos, String calificacion, String codigo, String obser, String email);
    public PersonaDTO consultarPersona();
    public ArrayList<PersonaDTO> listarPersonas();
    
}
