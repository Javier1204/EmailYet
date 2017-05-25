/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.interfaces;

import java.util.ArrayList;
import negocio.dao.interfaces.IExcelContent;
import negocio.dto.PersonaDTO;

/**
 * Interface para leer el archivo.
 * @author javie
 */
public interface InterfaceExcel {
    
    public IExcelContent leerArchivo(String ruta) throws Exception;
}
