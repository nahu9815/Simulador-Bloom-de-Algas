
package paquete;

import java.util.ArrayList;

/**
 *
 * @author Nahuel
 */
public class EstadoAgua {
    ArrayList<Double> temperaturaSem;
    ArrayList<Double> temperaturaDias;
    ArrayList<Double> pHList;
    ArrayList<Double> oxDis;
    ArrayList<Double> porcAlgasSem;
    ArrayList<Double> porcPecesMuertosSem;
    

    public EstadoAgua() {
        temperaturaSem = new ArrayList<>();
        pHList = new ArrayList<>();
        oxDis = new ArrayList<>();
        porcAlgasSem = new ArrayList<>();
        porcPecesMuertosSem = new ArrayList<>();
        temperaturaDias = new ArrayList<>();
    }
    public void agregarDatosSemana(double temperatura, double pHList, double oxDis, double porcAlgasSem, double porcPecesMuertosSem){
        this.temperaturaSem.add(temperatura);
        this.pHList.add(pHList);
        this.oxDis.add(oxDis);
        this.porcAlgasSem.add(porcAlgasSem);
        this.porcPecesMuertosSem.add(porcPecesMuertosSem);
    }
    public void agregarDatosDias(double temperatura){
        this.temperaturaDias.add(temperatura);
    }
    
    
    /*@Override
    public String toString(){
        int c=1;
        String datosTemp="";
        for (Double tempSem : temperaturaSem) {
            
            datosTemp.
            c++;
        }
     
        return datosTemp+"\n"+"";
        
    }*/

    public ArrayList<Double> getTemperaturaSem() {
        return temperaturaSem;
    }

    public void setTemperaturaSem(ArrayList<Double> temperaturaSem) {
        this.temperaturaSem = temperaturaSem;
    }

    public ArrayList<Double> getTemperaturaDias() {
        return temperaturaDias;
    }

    public void setTemperaturaDias(ArrayList<Double> temperaturaDias) {
        this.temperaturaDias = temperaturaDias;
    }

    public ArrayList<Double> getpHList() {
        return pHList;
    }

    public void setpHList(ArrayList<Double> pHList) {
        this.pHList = pHList;
    }

    public ArrayList<Double> getOxDis() {
        return oxDis;
    }

    public void setOxDis(ArrayList<Double> oxDis) {
        this.oxDis = oxDis;
    }

    public ArrayList<Double> getPorcAlgasSem() {
        return porcAlgasSem;
    }

    public void setPorcAlgasSem(ArrayList<Double> porcAlgasSem) {
        this.porcAlgasSem = porcAlgasSem;
    }

    public ArrayList<Double> getPorcPecesMuertosSem() {
        return porcPecesMuertosSem;
    }

    public void setPorcPecesMuertosSem(ArrayList<Double> porcPecesMuertosSem) {
        this.porcPecesMuertosSem = porcPecesMuertosSem;
    }
    
    
    
}
