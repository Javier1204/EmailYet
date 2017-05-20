/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.daos;

import java.util.ArrayList;
import negocio.dao.interfaces.ILogDAO;
import negocio.dto.LogDTO;
import util.Conexion;

/**
 *
 * @author javie
 */
public class LogDAO implements ILogDAO{

    private Conexion conn;
    
    @Override
    public ArrayList<ILogDAO> listarLogs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registrarLog(LogDTO log) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
