/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.dao.interfaces.IUserDAO;
import negocio.dto.UserDTO;
import util.Conexion;

/**
 *
 * @author javie
 */
public class UserDAO implements IUserDAO {
    private Connection con;
    private UserDAO(Connection con){
        this.con=con;
    }
    public static UserDAO getInstanceUserDAO(Connection con){
        return new UserDAO(con);
    }
    @Override
    public boolean validarUsuario(UserDTO user) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM userdao WHERE username=? AND password=?");
            ps.setString(1,user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs=ps.executeQuery();
            if(rs.absolute(1)){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
