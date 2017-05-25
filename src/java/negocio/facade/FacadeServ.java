/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.facade;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;
import negocio.dto.PersonaDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FilenameUtils;

/**
 * ¡¡¡¡OJOOOO!!! Clase FacadeServlet: Recibe datos de la vista y envía a Facade
 * para el proceso. También se encarga de subir el archivo al servidor y obtener
 * la ruta(String) para enviar a los métodos
 *
 * @author javie
 */
@WebServlet(name = "FacadeServ", urlPatterns = {"/FacadeServ"})
public class FacadeServ extends HttpServlet {

    private Facade facade = new Facade();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FacadeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FacadeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        /*if(request.getParameter("prueba")!=null){
         prueba(request, response);
         }*/
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        if (request.getParameter("enviar") != null) {
            sendEmail(request, response);
        }
        if (request.getParameter("prueba") != null) {
            prueba(request, response);
        }
        if (request.getParameter("iniciar") != null) {
            iniciarSesion(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public boolean iniciarSesion(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        boolean exito= false;
        try {
            exito = facade.IniciarSesion(user, password);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return exito;
    }

    public void prueba(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            /*FileItemFactory es una interfaz para crear FileItem*/
            FileItemFactory file_factory = new DiskFileItemFactory();
            String ruta2 = "";
            String contenido = "";
            String asunto = "";
            int column = 0;
            /*ServletFileUpload esta clase convierte los input file a FileItem*/
            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
            /*sacando los FileItem del ServletFileUpload en una lista */
            List items = servlet_up.parseRequest(request);

            for (int i = 0; i < items.size(); i++) {
                /*FileItem representa un archivo en memoria que puede ser pasado al disco duro*/
                FileItem item = (FileItem) items.get(i);
                /*item.isFormField() false=input file; true=text field*/
                if (!item.isFormField()) {
                    /*cual sera la ruta al archivo en el servidor*/
                    String ruta = getServletContext().getRealPath("/");
                    ruta2 = ruta + "/archivos/" + item.getName();
                    File archivo_server = new File(ruta2);
                    /*y lo escribimos en el servidor*/
                    item.write(archivo_server);
                    out.print("Ruta ---->" + ruta2);
                } else {
                    String name = item.getFieldName();
                    String value = item.getString();
                    if (name.equals("txt_email")) {
                        contenido = value;
                    } else if (name.equals("txt_asunto")) {
                        asunto = value;
                    } else if (name.equals("txt_column")) {
                        column = Integer.parseInt(value);
                    }
                }

            }
            out.print("Contenido: " + contenido + " asunto: " + asunto + " numero col = " + (column-1));
            //Leer archivo retorna un boolean. 
            boolean ex = facade.leerArchivo(ruta2, contenido, asunto, (column-1));
            out.print(ex);
            if (!ex) {
                response.sendRedirect("inicio.jsp");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public boolean sendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Código de subir archivo 
        try {
            /*FileItemFactory es una interfaz para crear FileItem*/
            FileItemFactory file_factory = new DiskFileItemFactory();

            /*ServletFileUpload esta clase convierte los input file a FileItem*/
            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
            /*sacando los FileItem del ServletFileUpload en una lista */
            List items = servlet_up.parseRequest(request);

            for (int i = 0; i < items.size(); i++) {
                /*FileItem representa un archivo en memoria que puede ser pasado al disco duro*/
                FileItem item = (FileItem) items.get(i);
                /*item.isFormField() false=input file; true=text field*/
                if (!item.isFormField()) {
                    /*cual sera la ruta al archivo en el servidor*/
                    String ruta = getServletContext().getRealPath("/");
                    String ruta2 = ruta + "/archivos/" + item.getName();
                    File archivo_server = new File(ruta);
                    /*y lo escribimos en el servidor*/
                    item.write(archivo_server);
                    out.print("Ruta ---->" + ruta);
//                    out.print("Nombre --> " + item.getName());
//                    out.print("<br> Tipo --> " + item.getContentType());
//                    out.print("<br> tamaño --> " + (item.getSize() / 1240) + "KB");
//                    out.print("<br>");
                }
            }

            //Leer archivo retorna un boolean. 
            facade.leerArchivo("ruta", "contenido_mensaje", "asunto", 0);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }
}
