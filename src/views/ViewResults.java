package views;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import paquete.Distribucion;
import paquete.EstadoAgua;
import paquete.NumeroAleatorioCMultiplicativo;
/**
 *
 * @author Nahuel
 */

public class ViewResults extends javax.swing.JDialog {
    private int sem;
    public ViewResults(java.awt.Frame parent, boolean modal, int semanas) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        sem = semanas;
        
        this.calcularDatos();
        this.setResultados();
        
        
    }
    
    
    private EstadoAgua estado = new EstadoAgua();
    private int s = 1;
    private int dias;
    private double temp;
    private double tempT;
    private double tempProm;
    private double temperatura;
    private double tempTOT; // Promedio de temperatura total
    private double porAlgas;
    private double porAlgasTot;
    private double porAlgasTOT; //Promedio de porcentaje de poblacion de algas de semanas totales
    private double porMort;
    private double porMortTotal;
    private double porMortTOT; //Promedio porc de mortandad de peces de semanas totales
    private double pH;
    private double pHT;
    private double pHTOT; //Promedio del nivel de pH total
    private double oxigenoD;
    private double oxigenoDT;
    private double oxigenoDTOT; // Promedio del oxigeno disuelto total
    private NumeroAleatorioCMultiplicativo num = new NumeroAleatorioCMultiplicativo();

    private void calcularDatos() {
        while (s <= sem) {
            dias = 1;
            tempProm = this.getTemperaturaPromXSem();
            if (tempProm <= 30) {
                pH = 7;
                oxigenoD = 5;
                porAlgas = Distribucion.getUniforme(10, 30, num.getNumAleatorioCMultiplicativo());
                porMort = Distribucion.getNormal(10, 5);
            } else {
                pH = Distribucion.getUniforme(7, 11, num.getNumAleatorioCMultiplicativo());
                

                if (num.getNumAleatorioCMultiplicativo() <= 60) {
                    porAlgas = Distribucion.getUniforme(31, 45, num.getNumAleatorioCMultiplicativo());
                    porMort = Distribucion.getNormal(30, 5);
                    oxigenoD = Distribucion.getUniforme(1.6, 3, num.getNumAleatorioCMultiplicativo());
                    
                } else {
                    porAlgas = Distribucion.getUniforme(60, 95, num.getNumAleatorioCMultiplicativo());
                    porMort = Distribucion.getNormal(70, 15);
                    oxigenoD = Distribucion.getUniforme(0.2, 1.5, num.getNumAleatorioCMultiplicativo());
                }
                
            }
            
            porAlgasTot = porAlgasTot + porAlgas;
            porMortTotal = porMortTotal + porMort;
            pHT = pHT + pH;
            oxigenoDT = oxigenoDT + oxigenoD;
            tempT = tempT + tempProm;
            estado.agregarDatosSemana(tempProm, pH, oxigenoD, porAlgas, porMort);
            s++;
            
        }
    }

    private double getTemperaturaPromXSem() {
        temperatura = 0;
        dias=1;
        while (dias <= 7) {
            if (num.getNumAleatorioCMultiplicativo() < 0.30) {
                temp = Distribucion.getUniforme(20, 28, num.getNumAleatorioCMultiplicativo());
            } else {
                temp = Distribucion.getUniforme(30, 42, num.getNumAleatorioCMultiplicativo());
            }
            temperatura = temperatura + temp;
            estado.agregarDatosDias(temp);
            dias++;
        }
        return temperatura / 7;

    }

    private void setResultados() {
        double semana = (double) sem;
        tempTOT = tempT / semana;
        porMortTOT = porMortTotal / semana;
        porAlgasTOT = porAlgasTot / semana;
        pHTOT = pHT / semana;
        oxigenoDTOT = oxigenoDT / semana;

        if (porAlgasTOT <= 30) {
            this.lblAguaNormal.setVisible(true);
            this.lblAguaDeplorable.setVisible(false);
            this.lblEstadoAgua.setText("NORMAL");
        } else {
            this.lblAguaNormal.setVisible(false);
            this.lblAguaDeplorable.setVisible(true);
            this.lblEstadoAgua.setText("VERDE ACUOSO");

        }
        DecimalFormat myFormat = new DecimalFormat("0.00");
        String porAlgas = myFormat.format(porAlgasTOT);
        String porPeces = myFormat.format(porMortTOT);
        String oxTOT = myFormat.format(oxigenoDTOT);
        String pHTOTAL = myFormat.format(pHTOT);
        String tTotal = myFormat.format(tempTOT);
        
        this.lblPorcAlgas.setText("" + porAlgas+" % ");
        this.lblPorcPecesMuertos.setText("" + porPeces+" % ");
        this.lblOxi.setText(""+oxTOT);
        this.lblpH.setText(""+pHTOTAL);
        this.lblTemperaturaTot.setText(""+tTotal);
    }
    
    public void mostrarDetalles(){
        int c = 1;
        DETALLE detail = new DETALLE(null,true);
        
        DecimalFormat myFormat = new DecimalFormat("0.00");
        Font font = new Font("Verdana", Font.BOLD,12);
        detail.txtDetalle.setFont(font);
        detail.txtDetalle.append("PROMEDIO DE TEMEPERATURA POR SEMANA (°C)\n\n");
        for (Double tempSem : estado.getTemperaturaSem()) {
            String tempSemana = myFormat.format(tempSem);
            detail.txtDetalle.append("Semana "+c+": "+tempSemana+"\n");
            c++;
        }
        detail.txtDetalle.append("\n\nTEMPERATURA POR DIA (°C)\n");
        c=1;
        for (Double temperaturaDia : estado.getTemperaturaDias()) {
            String tempdia = myFormat.format(temperaturaDia);
            detail.txtDetalle.append("Temperatura dia "+c+": "+tempdia+"\n");
            c++;
        }
        c=1;
        detail.txtDetalle.append("\n\nPROMEDIO DE pH POR SEMANA\n");
        for (Double ph : estado.getpHList()) {
            String pH = myFormat.format(ph);
            detail.txtDetalle.append("pH semana "+c+": "+pH+"\n");
            c++;
        }
        c=1;
        detail.txtDetalle.append("\n\nPROMEDIO DE OXIGENO DISUELTO POR SEMANA (mg/litros) \n");
        for (Double oxDi : estado.getOxDis()) {
            String oxigenoSem = myFormat.format(oxDi);
            detail.txtDetalle.append("Oxigeno disuelto semana "+c+": "+oxigenoSem+"\n");
            c++;
        }
        c=1;
        detail.txtDetalle.append("\n\nPORCENTAJE DE ALGAS POR SEMANA (%)\n");
        for (Double algas : estado.getPorcAlgasSem()) {
            String algaSemana = myFormat.format(algas);
            detail.txtDetalle.append("Semana "+c+": "+algaSemana+"\n");
            c++;
        }
        c=1;
        detail.txtDetalle.append("\n\nPORCENTAJE DE PECES MUERTOS POR SEMANA (%)\n");
        for (Double peces : estado.getPorcPecesMuertosSem()) {
            String peceSemana = myFormat.format(peces);
            detail.txtDetalle.append("Semana "+c+": "+peceSemana+"\n");
            c++;
        }
        detail.setVisible(true);
        detail.setLocationRelativeTo(null);
        
        
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblAguaDeplorable = new javax.swing.JLabel();
        lblAguaNormal = new javax.swing.JLabel();
        btnDetalles = new javax.swing.JButton();
        lblPorcPecesMuertos = new javax.swing.JLabel();
        lblPorcAlgas = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblOxi = new javax.swing.JLabel();
        lblpH = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblEstadoAgua = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTemperaturaTot = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAguaDeplorable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Escaba3.PNG"))); // NOI18N
        jPanel2.add(lblAguaDeplorable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 340, 250));

        lblAguaNormal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1469715936 - Dique Escaba. Escaba.jpg"))); // NOI18N
        lblAguaNormal.setText("jLabel1");
        jPanel2.add(lblAguaNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 250));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 348, 253));

        btnDetalles.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnDetalles.setText("Ver detalles");
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });
        getContentPane().add(btnDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 203, 37));

        lblPorcPecesMuertos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPorcPecesMuertos.setForeground(new java.awt.Color(255, 255, 255));
        lblPorcPecesMuertos.setText("- - -");
        getContentPane().add(lblPorcPecesMuertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 13, -1, -1));

        lblPorcAlgas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPorcAlgas.setForeground(new java.awt.Color(255, 255, 255));
        lblPorcAlgas.setText("- - -");
        getContentPane().add(lblPorcAlgas, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 37, -1, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("PROMEDIO DEL PORCENTAJE POBLACIONAL DE MORTANDAD DE PECES:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("PROMEDIO PORCENTAJE POBLACIONAL DE ALGAS:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        lblOxi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblOxi.setForeground(new java.awt.Color(255, 255, 255));
        lblOxi.setText("- - -");
        getContentPane().add(lblOxi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, -1, -1));

        lblpH.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblpH.setForeground(new java.awt.Color(255, 255, 255));
        lblpH.setText("- - -");
        getContentPane().add(lblpH, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PROMEDIO DE OXIGENO DISUELTO TOTAL:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PROMEDIO DE TEMPERATURA TOTAL:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Estado del dique:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        lblEstadoAgua.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblEstadoAgua.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoAgua.setText("- - -");
        getContentPane().add(lblEstadoAgua, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 160, 20));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PROMEDIO DE NIVEL DE pH TOTAL:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        lblTemperaturaTot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTemperaturaTot.setForeground(new java.awt.Color(255, 255, 255));
        lblTemperaturaTot.setText("- - -");
        getContentPane().add(lblTemperaturaTot, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bajo-el-agua-3056.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        this.mostrarDetalles();

    }//GEN-LAST:event_btnDetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAguaDeplorable;
    private javax.swing.JLabel lblAguaNormal;
    private javax.swing.JLabel lblEstadoAgua;
    private javax.swing.JLabel lblOxi;
    private javax.swing.JLabel lblPorcAlgas;
    private javax.swing.JLabel lblPorcPecesMuertos;
    private javax.swing.JLabel lblTemperaturaTot;
    private javax.swing.JLabel lblpH;
    // End of variables declaration//GEN-END:variables
}
