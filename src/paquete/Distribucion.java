
package paquete;

/**
 *
 * @author Nahuel
 */
public class Distribucion {
  
   
    
    //DISTRIBUCION UNIFORME
    public static double getUniforme(double a, double b, double u){
        return a + (b-a)*u;
    }
    
    
    //DISTRIBUCION NORMAL
    public static double getNormal(double media, double desviacion){
        NumeroAleatorioCMultiplicativo num = new NumeroAleatorioCMultiplicativo();
        double suma=0;
        for (int i = 1; i <= 12; i++) {
            suma = suma + num.getNumAleatorioCMultiplicativo();
            
        }
        
        return (media + desviacion*(suma-6));
    }
    
}
