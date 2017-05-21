/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.dao.interfaces;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IExcelContent {
    public List<String> getTitulos();
    public void setTitulos(List<String> titulos);
    public List<List<String>> getDatos();
    public void setDatos(List<List<String>> datos);
    
}
