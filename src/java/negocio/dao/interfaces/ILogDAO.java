/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.dao.interfaces;

import java.util.ArrayList;
import negocio.dto.LogDTO;

/**
 *
 * @author javie
 */
public interface ILogDAO {
    
    public boolean registrarLog(LogDTO log);
    public ArrayList<ILogDAO> listarLogs();
    
}
