/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalccalculator;

import java.net.*;
import java.io.*;
import org.apache.commons.lang3.ArrayUtils;

import protocol.common.*;
import protocol.clientcalculator.*;

import compengine.*;

/**
 *
 * @author root
 */
public class TaskClient {
    
    private ClientCalculatorProtocol ccp;
    private FloatEngine fengine;
        
    //--------------------------------------------------------------------------
    
    public TaskClient(ClientCalculatorProtocol ccp)
    {
        this.ccp = ccp;
        this.fengine = new FloatEngine();
    }
    
    //--------------------------------------------------------------------------
    
    public void run() throws ComputeEngineException
    {
        // Recibimos peticiones y las procesamos
        boolean end = false;
        Request r = ccp.receiveRequest();
        while( !end && r!=null ) {
            if( r.getSubtype().compareTo("_JCALC_END_")==0 ) {
                end = true;
            }
            else if( r.getSubtype().compareTo("_JCALC_OPERATION_")==0 ) {
                this.cmdOperation(r);
            }                
            r = ccp.receiveRequest();
        }

        // Finalizamos la conexion
        // this.sc.close();
    }
    
    //--------------------------------------------------------------------------
    
    public boolean cmdOperation(Request rq) throws ComputeEngineException
    {   
        Response rs = new Response();
        
        Operation op = (Operation)rq.getData().get(0);
        if( op.getType().compareTo("x+y")==0 ) {
            Float[] numeros = new Float[op.getInputData().size()];
            for(int i=0; i<numeros.length; i++) {
                numeros[i] = (Float)op.getInputData().get(i).value;
            }
            float[] datos = ArrayUtils.toPrimitive(numeros);
            Double res = new Double(fengine.sumar(datos));
            op.setResult(res);
            
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);
        }
        else if( op.getType().compareTo("x-y")==0 ) {
            Float[] numeros = new Float[op.getInputData().size()];
            for(int i=0; i<numeros.length; i++) {
                numeros[i] = (Float)op.getInputData().get(i).value;
            }
            float[] datos = ArrayUtils.toPrimitive(numeros);
            Double res = new Double(fengine.restar(datos));
            op.setResult(res);
            
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);   
        }

        else if( op.getType().compareTo("x*y")==0 ) {
            Float[] numeros = new Float[op.getInputData().size()];
            for(int i=0; i<numeros.length; i++) {
                numeros[i] = (Float)op.getInputData().get(i).value;
            }
            float[] datos = ArrayUtils.toPrimitive(numeros);
            Double res = new Double(fengine.multiplicar(datos));
            op.setResult(res);
            
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs); 
        }        
        else if( op.getType().compareTo("x/y")==0 ) {
            Float[] numeros = new Float[op.getInputData().size()];
            for(int i=0; i<numeros.length; i++) {
                numeros[i] = (Float)op.getInputData().get(i).value;
            }
            float[] datos = ArrayUtils.toPrimitive(numeros);
            try {
                Double res = new Double(fengine.dividir(datos));
                op.setResult(res);
            
                rs.setSubtype("_JCALC_OPERATION_OK_");
                rs.addData(op);
            }
            catch(ComputeEngineException ex) {
                protocol.common.Error err = new protocol.common.Error();
                err.type = ex.getMessage();
                err.msg = "Error el divisor no puede ser 0";
                op.setResult(err);
                rs.setSubtype("_JCALC_OPERATION_ERROR_");
            }
            rs.addData(op);
            return ccp.sendResponse(rs);            
        }           
        else if( op.getType().compareTo("x2")==0 ) {
            Float n = (Float)op.getInputData().get(0).value;
            double rss = fengine.x2(n.floatValue());
            Double res = new Double(rss);
            // Double res = new Double(fengine.x2(n.floatValue()));
            op.setResult(res);            
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);            
        }        
        else if( op.getType().compareTo("xey")==0 ) {
            
        }
        else if( op.getType().compareTo("moda")==0 ) {
            Float[] numeros =(Float[])op.getInputData().get(0).value;
 
            float[] datos = ArrayUtils.toPrimitive(numeros);
            Double res = null;
            res = new Double(fengine.moda(datos));
            op.setResult(res);
            
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);
        }
        else if( op.getType().compareTo("raiz2")==0 ) {
            Float n = (Float)op.getInputData().get(0).value;
            double rss = fengine.raiz2(n.floatValue());
            Double res = new Double(rss);
            // Double res = new Double(fengine.x2(n.floatValue()));
            op.setResult(res);            
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);   
        } 
        else if( op.getType().compareTo("cambioSigno")==0 ) {
            Float n = (Float)op.getInputData().get(0).value;
            double rss = fengine.cambioSigno(n.floatValue());
            Double res = new Double(rss);
            // Double res = new Double(fengine.x2(n.floatValue()));
            op.setResult(res);            
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);   
        } 
        else if( op.getType().compareTo("mediaGeome")==0){
            Float [] Operandos = (Float[]) op.getInputData().get(0).value;
            float [] datos = ArrayUtils.toPrimitive(Operandos);
            Double resultado = null;
            try {
                resultado = fengine.mediaGeometrica(datos);
            } catch (Exception e) {
            }
            op.setResult(resultado);           
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);   
        }
        else if( op.getType().compareTo("varianza")==0){
            Float [] Operandos = (Float[]) op.getInputData().get(0).value;
            float [] datos = ArrayUtils.toPrimitive(Operandos);
            Double resultado = null;
            try {
                resultado = fengine.varianza(datos);
            } catch (Exception e) {
            }
            op.setResult(resultado);           
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);   
        }
        else if( op.getType().compareTo("mediaAritmetica")==0){
            Float [] Operandos = (Float[]) op.getInputData().get(0).value;
            float [] datos = ArrayUtils.toPrimitive(Operandos);
            Double resultado = null;
            try {
                resultado = fengine.mediaAritmetica(datos);
            } catch (Exception e) {
            }
            op.setResult(resultado);           
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);   
        }
        else if( op.getType().compareTo("aproximacionNumE")==0 ) {
            Float n = (Float)op.getInputData().get(0).value;
            double rss = fengine.aproximacionNumE(n.floatValue());
            Double res = new Double(rss);
            op.setResult(res);            
            rs.setSubtype("_JCALC_OPERATION_OK_");
            rs.addData(op);
            return ccp.sendResponse(rs);   
        }
        else if( op.getType().compareTo("segundoGrado")==0){
            Float [] Operandos = new Float[op.getInputData().size()];
            for (int i = 0; i < Operandos.length; i++) {
                Operandos[i] = (Float) op.getInputData().get(i).value;
            }
            float [] datos = ArrayUtils.toPrimitive(Operandos);
            float[] resta = new float[2];
            float[] suma = new float[2];
            resta[0] = datos[0];
            resta[1] = datos[2];
            suma[0]  = datos[1];
            suma[1]  = datos[2];
            try {
                Double[] res = new Double[2];
                res[0] = new Double(fengine.dividir(resta));
                res[1] = new Double(fengine.dividir(suma));
                op.setResult(res);
                rs.setSubtype("_JCALC_OPERATION_OK_");
                rs.addData(op);
            } catch (Exception e) {
            }
            //op.setResult(resultado);           
            return ccp.sendResponse(rs);   
        }

        return true;
    }      
}
