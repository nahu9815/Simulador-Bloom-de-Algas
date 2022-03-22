package paquete;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

/**
 *
 * @author Nahuel
 */

public class NumeroAleatorioCMultiplicativo {

    int ni; //Semilla
    int a; // Cte Multipliccativa
    int m; // Modulo
    Random r = new Random();
    double ui; // Valor pseudoaleatorio

    
    
    public NumeroAleatorioCMultiplicativo() {
    }

    public double getNumAleatorioCMultiplicativo() {
        m = calcularM();
        a = calcularA();
        ni = calcularNi();
        ui = (double) ((a * ni) % m) / m;
        
        return ui;
    }

    public int calcularA() {
        ArrayList<Integer> listaValoresP = new ArrayList<>();
        listaValoresP.add(3);
        listaValoresP.add(11);
        listaValoresP.add(13);
        listaValoresP.add(19);
        listaValoresP.add(21);
        listaValoresP.add(27);
        listaValoresP.add(29);
        listaValoresP.add(37);
        listaValoresP.add(53);
        listaValoresP.add(59);
        listaValoresP.add(61);
        listaValoresP.add(69);
        listaValoresP.add(77);
        listaValoresP.add(83);
        listaValoresP.add(91);
        
        int t = r.nextInt(100);
        
        
        int x = r.nextInt(1);
        if (x == 0) {
            return 200 * t + listaValoresP.get(r.nextInt(15));
        } else {
            return 200 * t - listaValoresP.get(r.nextInt(15));
        }
    }

    
    public int calcularNi() {
        ni=0;
        
        while ((ni % 2 == 0) || (ni % 5 == 0)){
             ni = r.nextInt(100000) + 1;
        }
        
        return ni;
    }
    
    
    public int calcularM() {
        m = r.nextInt(10000) + 1;
        return m;
    }
    

}
