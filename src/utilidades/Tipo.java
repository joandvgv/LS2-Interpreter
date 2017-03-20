/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Objects;

/**
 *
 * @author jarg95
 */
public class Tipo {
    private String nombre;
    private String tipoBase;
    private Float valor;

    public Tipo(String nombre, String tipoBase, Float valor) {
        this.nombre = nombre;
        this.tipoBase = tipoBase;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoBase() {
        return tipoBase;
    }

    public void setTipoBase(String tipoBase) {
        this.tipoBase = tipoBase;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
    public void print(){
        if (!(valor == null))
            if (tipoBase.equals("Int"))
                System.out.println(nombre + "   " + valor.intValue() + "   " + tipoBase);
            else
                System.out.println(nombre + "   " + valor + "   " + tipoBase);
        else
            System.out.println(nombre + "   null   " + tipoBase);
    }
    
}