/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalcclient;

/**
 *
 * @author Alberto
 */
public class Interfaz {
     public static void showmenu(){
        System.out.println("Operaciones:\n"
                +"1. Media Aritmetica \n"
                +"2. Media Geometrica\n"
                +"3. Segundo Grado\n"
                +"4. op X2Y21Z3\n"
                +"5. Varianza\n"
                +"6. Aproximación al numeroE\n"
                +"7. Moda\n"
                +"8. Raiz2\n"
                +"9. Division\n"
                +"s. Salir\n"
                +"Seleccione la operación desada:"
            );
        
    }
    public static boolean isvalid (int ch){
        if (ch <'1'| ch>'9'& ch !='s') return false;
        else return true;
    }
    public static String optionName(char ch){
        if (ch == '1') return "mediaAritmetica";
        if (ch == '2') return "mediaGeometrica";
        if (ch == '3') return "segundoGrado";
        if (ch == '4') return "opX2Y21Z3";
        if (ch == '5') return "varianza";
        if (ch == '6') return "aproximacionNumE";
        if (ch == '7') return "Moda";
        if (ch == '8') return "Raiz2";
        if (ch == '9') return "Division";
        if (ch == 's') return "salir";
        else return "fallo";  
    }
    
}
