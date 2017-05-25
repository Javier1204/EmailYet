/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import negocio.dao.interfaces.ILogDAO;
import negocio.dto.LogDTO;
import util.Conexion;

/**
 *
 * @author javie
 */
public class LogDAO implements ILogDAO{

    private Connection conn;
    
    @Override
    public ArrayList<ILogDAO> listarLogs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registrarLog(LogDTO log) throws Exception{
        boolean exito =false;
        PreparedStatement stmt = null;
        try{
            conn = Conexion.conectar();
            stmt = conn.prepareStatement("INSERT INTO log(titulo, descripcion, fecha_inicio, fecha_fin) VALUES(?,?,?,?)");
            stmt.setString(1, log.getTitulo());
            stmt.setString(2, log.getDescripcion());
            stmt.setString(3, log.getFecha_hora_inicio());
            stmt.setString(4, log.getFecha_hora_fin());
            int total = stmt.executeUpdate();
            if (total > 0) {
                stmt.close();
                exito = true;
            }
             stmt.close();
        }catch(Exception e){
          e.printStackTrace();
        }finally{
            if(conn != null){
                conn.close();
            }
        }
        return exito;
    }
    
}
