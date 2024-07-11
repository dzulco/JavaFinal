package edu.ar.model;

public class Usuario {

    int id;
    String nombre;
    String apellido;
    String email;
    String contrasenia;
    String fechaNacimiento;
    String pais;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String email, String contrasenia, String fechaNacimiento,
            String pais) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setFechaNacimiento(String fecha_nac) {
        this.fechaNacimiento = fecha_nac;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
                + ", contrasenia=" + contrasenia + ", fechaNacimiento=" + fechaNacimiento + ", pais=" + pais + "]";
    }

}
