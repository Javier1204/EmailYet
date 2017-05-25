/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import negocio.daos.LogDAO;
import negocio.dto.LogDTO;

/**
 *
 * @author javie
 */
public class test {
    public static void main(String[] args) {
        LogDAO dao = new LogDAO();
        LogDTO log= new LogDTO("Cosa", "Cosa", "2017-12-12", "2017-12-12");
        try{
        System.out.println(dao.registrarLog(log));
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}
