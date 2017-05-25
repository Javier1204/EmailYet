/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.dao.interfaces.IExcelContent;
import negocio.daos.LogDAO;
import negocio.dto.LogDTO;
import negocio.facade.Facade;
import negocio.interfaces.InterfaceExcel;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Clase EmailReader. Se encarga de leer el archivo y retornar la lista de
 * personas para enviar el email.
 *
 * @author javie
 */
public class ExcelReader implements InterfaceExcel {

    @Override
    public IExcelContent leerArchivo(String ruta) throws Exception{
        java.util.Date date = new java.util.Date();
        Date entrada = new Date(date.getTime());
        IExcelContent ec = ExcelContent.getInstantiateExcelContent();
        try {
            LogDAO dao = new LogDAO();
            LogDTO dto = new LogDTO("Leer archivo", "Comienzo de lectura de archivo", entrada.toString(), entrada.toString());
            dao.registrarLog(dto);
            File archivo = new File(ruta);
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(archivo)); //crear un libro excel
            XSSFSheet sheet = workbook.getSheetAt(0); //acceder a la primera hoja
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            boolean sw = true;
            ArrayList<List<String>> datos = new ArrayList<>();
            while (rowIterator.hasNext()) {

                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell celda;
                List<String> fila = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    celda = cellIterator.next();
                    String dato = "";
                    switch (celda.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(celda)) {
                                dato = celda.getDateCellValue().toString();
                            } else {
                                dato = celda.getNumericCellValue() + "";
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            dato = celda.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            dato = celda.getBooleanCellValue() + "";
                            break;
                    }
                    fila.add(dato);
                }
                if (sw) {
                    sw = false;
                    ec.setTitulos(fila);
                } else {
                    datos.add(fila);
                }
            }
            ec.setDatos(datos);
            workbook.close();
            return ec;
        } catch (IOException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static void main(String[] args) {
//        ExcelReader er = new ExcelReader();
//        IExcelContent ec = er.leerArchivo("C:\\Users\\Lenovo\\Desktop\\prueba.xlsx");
//        System.out.println("hola mundo");
//        System.out.println("hola mundo");
    }
}
