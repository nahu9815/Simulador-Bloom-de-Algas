
package paquete;

/**
 *
 * @author Nahuel
 */
public class Principal {

    
    public static void main(String[] args) {
       NumeroAleatorioCMultiplicativo num = new NumeroAleatorioCMultiplicativo();
       
        
        System.out.println(num.getNumAleatorioCMultiplicativo());
        
        System.out.println(Distribucion.getUniforme(0.2, 2.4, num.getNumAleatorioCMultiplicativo()));
        /*System.out.println(NumeroAleatorioCMixto.calcularC());
        System.out.println("Valor de distribucion uniforme");
        System.out.println(Distribucion.getUniforme(15, 25, num.getNumAleatorioCMultiplicativo()));
        System.out.println("Valor de distribucion Normal");
        System.out.println(Distribucion.getNormal(8000, 100));*/
        
        
    }
    
}
