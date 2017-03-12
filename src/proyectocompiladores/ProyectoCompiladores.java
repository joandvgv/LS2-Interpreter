/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompiladores;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 *
 * @author jarg95
 */
public class ProyectoCompiladores {

    public static void main(String[] args) {
//        File f = new File("entradas");
//        File[] ficheros = f.listFiles();
//       
//        System.out.println("0) Salir");
//        for (int x=0;x<ficheros.length;x++){
//            System.out.println(x+1 + ") " + ficheros[x].getName());
//        }
//       
//        System.out.print("Elija el archivo de entrada: ");
//        
//        Scanner sc = new Scanner(System.in);
//        int opcion = sc.nextInt();
//        opcion--;
//        if(opcion < 0 || opcion > ficheros.length - 1)
//            System.exit(0); 
//        interpretar(ficheros[opcion].getPath());
          interpretar("entradas/prueba");
   
    }
    
    private static void interpretar(String path) {
        analizadores.Sintactico pars;
        try {
            pars=new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(path)));
            pars.parse();        
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        }    
    }
    
}
