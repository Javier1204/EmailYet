/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.dao.interfaces;

import negocio.dto.UserDTO;

/**
 *
 * @author javie
 */
public interface IUserDAO {
    
    public boolean validarUsuario(UserDTO user);
    
}
