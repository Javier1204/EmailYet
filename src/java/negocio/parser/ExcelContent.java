/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.parser;

import java.util.List;
import negocio.dao.interfaces.IExcelContent;

/**
 *
 * @author Lenovo
 */
public class ExcelContent implements IExcelContent{
    private List<String> titulos;
    private List<List<String>> datos;
    
    private ExcelContent(){}
    
    public static IExcelContent getInstantiateExcelContent(){
        return new ExcelContent();
    }
    @Override
    public List<String> getTitulos() {
       return this.titulos;
    }

    @Override
    public List<List<String>> getDatos() {
        return this.datos;
    }

    @Override
    public void setTitulos(List<String> titulos) {
        this.titulos=titulos;
    }

    @Override
    public void setDatos(List<List<String>> datos) {
        this.datos=datos;
    }
    
}
