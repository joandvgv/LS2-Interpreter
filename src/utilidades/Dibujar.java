/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author joandv
 */
public class Dibujar {
    public static void dibujar(){
        LSystem.imprimir();
        System.out.println("I was called");
        List<String> simbolos = LSystem.simbolos;
        List<String> con = LSystem.con;
        List<String> matriz = LSystem.depurarMatriz();
        System.out.println(matriz);
        List<String> generatriz = LSystem.generatriz;
        List<String> numOp = LSystem.numOp;
        
        String[] vars = new String[LSystem.simbolos.size()];
        String[] cons = new String[LSystem.con.size()];
        String[] axi = new String[LSystem.matriz.size()];
        String[] rules = new String[LSystem.generatriz.size()];
        String[] numOps = new String[LSystem.numOp.size()];
        vars = simbolos.toArray(vars);
        cons = con.toArray(cons);
        axi = matriz.toArray(axi);
        rules = generatriz.toArray(rules);
        numOps = numOp.toArray(numOps);
        
                   
        
        
        Grafica test1 = new Grafica(vars,cons,axi,rules);
        String[][] frac = {vars, cons, axi, rules, numOps};
        String str = test1.generate(LSystem.pasos,frac[2][0]);
        test1.createFractal(str, numOps, LSystem.posicion, LSystem.angulo);

        
        
    }
}
