package edu.ar.model;

public class Director extends Persona {

  private int cantPelDirigidas;
  private int id;



  public Director(String nombre, String apellido, int anioNacimiento, int cantPelDirigidas) {
    super(nombre, apellido, anioNacimiento);
    this.cantPelDirigidas = cantPelDirigidas;
  }

  public Director(int id, String nombre, String apellido) {
    this.id = id;
    this.setNombre(nombre);
    this.setApellido(apellido);
  }

  public int getCantPelDirigidas() {
    return cantPelDirigidas;
  }

  public void setCantPelDirigidas(int cantPelDirigidas) {
    this.cantPelDirigidas = cantPelDirigidas;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
