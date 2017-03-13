/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;



public class LSystem {
    
    public static ArrayList<String> simbolos = new ArrayList<>();
    public static ArrayList<String> con = new ArrayList<>();
    public static ArrayList<String> matriz = new ArrayList<>() ;
    public static ArrayList<String> generatriz = new ArrayList<>();
    public static ArrayList<String> numOp = new ArrayList<>() ;
    public static int pasos;
    public static int[] posicion = {0,0};
    public static int angulo;
    public static int probabilidad;
    public static ArrayList<String> frac = new ArrayList<>();

    public LSystem() {
        simbolos = new ArrayList<>();
        con = new ArrayList<>();
        matriz = new ArrayList<>();
        generatriz = new ArrayList<>();
        numOp = new ArrayList<>();
        frac = new ArrayList<>();
        pasos = 0;
        angulo = 0;
        probabilidad = 0;
        
    }
    
    
    public void setFrac(String simbolos, String matriz, String generatriz, String con, String numop) {
        LSystem.frac.add(simbolos);
        LSystem.frac.add(con);
        LSystem.frac.add(matriz);
        LSystem.frac.add(generatriz);
        LSystem.frac.add(numop);
    }
 

    
    public static void addSimbolo(String s){
        simbolos.add(s);
    }
    
    public static void addMatriz(String m){
        matriz.add(m);
    }
    
    public static void addGeneratriz(String g){
        generatriz.add(g);
    }
    
    
    public static void addMove(String m){
        simbolos.add(m);
    }
    
    public static void addNumop(String n){
        for (String num: LSystem.numOp){
            if (num.equals(n)||num.contains(n)) return;
        }
        String op ="";
        switch (n) {
            case "+":
                op=("+:90");
                break;
            case "-":
                op=("-:-90");
                break;
            case "[":
                op=("[:[");
                break;
            case "]":
                op=("]:]");
                break;
            default:
                for (String simbolo:LSystem.simbolos){
                    if(n.equals(simbolo)){
                        op=simbolo+":"+simbolo.toLowerCase();
                    }
                }   break;
        }
        
        LSystem.numOp.add(op);
    }
     public static void addCon(String c){
        for (String ch: LSystem.con){
            if (ch.equals(c)) return;
        }
        LSystem.con.add(c);
    }

    public static void setPasos(int pasos) {
        LSystem.pasos = pasos;
    }

    public static void setAngulo(int angulo) {
        LSystem.angulo = angulo;
    }

    public static void setProbabilidad(int probabilidad) {
        LSystem.probabilidad = probabilidad;
    }
    
    public static ArrayList<String> depurarMatriz(){
        ArrayList<String> newArr = new ArrayList<>();
        LSystem.matriz.forEach((str) -> {
           
            str = str.replace("to", "");
             System.out.println(str);
            newArr.add(str);
        });
        
        return newArr;
    }

    
    public static void imprimir(){
        System.out.println("Simbolos: " + LSystem.simbolos);
        System.out.println("Matriz: " + LSystem.matriz);
        System.out.println("Generatriz: " + LSystem.generatriz);
        System.out.println("Con: " + LSystem.con);
        System.out.println("nump: " + LSystem.numOp);
        System.out.println("Probabilidad: " + LSystem.probabilidad);
        System.out.println("Angulo: " + LSystem.angulo);
        System.out.println("Pasos: " + LSystem.pasos);
        System.out.println("Probabilidad: " + LSystem.probabilidad);
        
    }
    
    public String traducir(String movimiento){
           String nstr = "";
for (int i = 0; i < movimiento.length(); i++){
        String letra = Character.toString(movimiento.charAt(i));
        for (String simbolo: LSystem.simbolos){
            if (letra.contains(simbolo)){
                nstr= nstr +simbolo;
            }
        }
        if (letra.contains("{")){
            nstr = nstr + "[";
        }
        else if (letra.contains("}")){
            nstr = nstr + "]";
        }
        else if (letra.contains("&")){
            nstr = nstr + "+";
        }
        else if (letra.contains("$")){
            nstr = nstr + "-";
        }
        else if (letra.contains("-")&& (i <=2)){
            nstr = nstr + "to";
        }

}
    return nstr;
    }
}    
