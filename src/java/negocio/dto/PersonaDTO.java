/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.dto;

/**
 *
 * @author javie
 */
public class PersonaDTO {
    private String nombre;
    private String apellidos;
    private String codigo;
    private String calificacion; 
    private String observacion;
    private String email; 

    public PersonaDTO(String nombre, String apellidos, String codigo, String calificacion, String observacion, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.codigo = codigo;
        this.calificacion = calificacion;
        this.observacion = observacion;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
