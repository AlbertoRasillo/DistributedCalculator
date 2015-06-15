/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalcclient;

import java.net.*;
import java.io.*;

import protocol.common.*;
import protocol.clientcontroller.*;
import protocol.clientcalculator.*;
/**
 *
 * @author root
 */
public class MainClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            Socket sc = new Socket("localhost",7000);   // Controller            
        
            ClientControllerProtocol ccp = new ClientControllerProtocol(sc);
            ccp.initClient();
            
            Request rq = ccp.createCalculationRequest("aproximacionNumE");
            ccp.sendRequest(rq);
            Response rs = ccp.receiveResponse();
            
            rq = new Request();
            rq.setSubtype("_JCALC_END_");
            ccp.sendRequest(rq);
            
            if( rs.getSubtype().compareTo("_JCALC_CALCULATION_ERROR_")==0 ) {
                System.out.println("Error");
            }
            else if( rs.getSubtype().compareTo("_JCALC_CALCULATION_OK_")==0 ) {
                
                Calculation calc = (Calculation)rs.getData().get(0);
                
                for(Operation op : calc.getOperaciones()) {                    
                    TaskOperation task = new TaskOperation(op, calc);
                    task.run();
                    if( task.getError()==null ) {
                        calc.updateOperation( task.getResult() );                        
                    }
                    else {
                        // Error en operacion. Abortar o repetir??
                    }                                        
                }  // for Operation
                
                // El resultado del calculo es el de la última operacion
                Operation last = calc.getOperaciones().get(calc.getOperaciones().size()-1);
                //if( opcion == segundoGrado)
//                calc.setResult( last.getResult());
//                Double[] res = new Double[2];
//                res = (Double[]) calc.getResult();
//                System.out.println("Exito. Resultado: " + res[0]);
//                System.out.println("Exito. Resultado: " + res[1]);
//                //else
                calc.setResult( last.getResult());
                
                System.out.println("Exito. Resultado: " + calc.getResult() );
            }
        }
        catch(UnknownHostException e) {
        }
        catch(IOException e) {
        }
    }    
}
