/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compengine;

/**
 *
 * @author profesor
 */
public class FloatEngine {
    
    public FloatEngine()
    {       
    }

    //--------------------------------------------------------------------------
    
    public double sumar(float[] numeros) 
    { 
        double result = 0.0;
        for(int i=0;i<numeros.length;i++)
            result += numeros[i];
        return result;        
    }

    //--------------------------------------------------------------------------
    
    public double restar(float[] numeros)
    {
        double result = numeros[0];
        for(int i=1;i<numeros.length;i++)
            result -= numeros[i];
        return result;
    }

    //--------------------------------------------------------------------------
    
    public double multiplicar(float[] numeros)
    {        
        double result = 1.0;
        for(int i=0;i<numeros.length;i++)
            result *= numeros[i];
        return result;
    }

    //--------------------------------------------------------------------------
    
    public double dividir(float[] numeros) throws ComputeEngineException
    {        
        double result = numeros[0];
        for(int i=1;i<numeros.length;i++) {
            if( numeros[i]==0 ) throw new ComputeEngineException("DIVIDE_BY_ZERO");
            result /= numeros[i];
        }
        return result;
    } 

    //--------------------------------------------------------------------------
    
    public double x2(float x)
    {        
        return (double)(x*x);
    }   

    //--------------------------------------------------------------------------
    
    public double xey(float x, float y)
    {        
        return Math.pow((double)x, (double)y);
    }   
    
    //--------------------------------------------------------------------------
    
    public double raiz2(float x) throws ComputeEngineException
    {        
        if( x<0.0F ) throw new ComputeEngineException("SQUAREROOT_NEGATIVE");
        return Math.sqrt((double)x);
    }
    public double cambioSigno(float x){
        return x*-1;
    }
    public double mediaAritmetica (float[] operandos) throws ComputeEngineException{
        double resultado = 0;
        for (float operando: operandos) {
            resultado = operando + resultado;
        }
        if( operandos.length==0 ) throw new ComputeEngineException("DIVIDE_BY_ZERO");
        resultado = resultado / operandos.length;
        return resultado;
    }
    public double mediaGeometrica(float[] datos) {
      double resultado = 1;
      for(float dato : datos){
          resultado = dato * resultado;
      }
      return Math.pow(resultado, 1.0/datos.length);
    }
    public double varianza (float[] operandos) throws ComputeEngineException{
        Double media = mediaAritmetica(operandos);
        double resultado = 0;
        for (float operando: operandos) {
            resultado =resultado + x2((float) (operando - media));
        }
        resultado = resultado / operandos.length;
        return resultado;
    }
    public static  double factorial (int n){
        double resultado = 1;
        for(int i=1; i<=n; i++) {
            resultado = i*resultado;
        }
        return resultado;
    }
    public double aproximacionNumE(float x){
        double resultado=1;   
        for (int i = 1; i <= x; i++) {
           resultado= resultado + (1/factorial(i));      
        }
        return resultado;
    }
}
