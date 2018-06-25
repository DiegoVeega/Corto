/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class producto {
    private String nombre;
    private int codigo;
    private String tipo;
    private int cantidad;
    private float precio;
    private boolean disponibilidad;
    
    public producto(){
        
    }
    
    public producto(String nombre, int codigo, String tipo, int cantidad, float precio, boolean disponibilidad){
        this.nombre=nombre;
        this.codigo=codigo;
        this.tipo=tipo;
        this.cantidad=cantidad;
        this.precio=precio;
        this.disponibilidad=disponibilidad;
    }

    public producto(String string, int aInt, String string0, float aFloat, boolean aBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}
