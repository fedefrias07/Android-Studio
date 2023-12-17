package com.example.zafiro.model;

public class Persona {

    private  String id;
    private String usuario;
    private String correo;
    private String contrasena;
    private boolean is_admin;

    public Persona() {

    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {

        this.correo = correo;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public Boolean getIs_admin() {
        return is_admin;
    }
    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String toString() {
        return "Usuario: " + usuario + "\nCorreo: " + correo;
    }


}
