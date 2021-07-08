/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.presentacion;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.List;
import static java.lang.String.valueOf;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uy.proyectofinal.excepciones.PersistenciaException;
import uy.proyectofinal.excepciones.UsuarioException;
import uy.proyectofinal.excepciones.VehiculosException;
import uy.proyectofinal.logica.FachadaLogica;
import uy.proyectofinal.logica.Vehiculo;
import uy.proyectofinal.logica.Vehiculos;
import uy.proyectofinal.persistencia.PersistenciaVehiculos;

/**
 *
 * @author Richard
 */
public class VentanaVehiculos extends javax.swing.JFrame {

    /**
     * Creates new form VentanaVehiculos
     */
    public VentanaVehiculos() {
        initComponents();
        setTitle("Vehiculos");
        //setSize(1020,574);
        setLocationRelativeTo(null);
        setResizable(false);
        
   
    }
    public void listarVehiculos(){
         //Cargar los Vehiculos y mostrarlos en la JTable
        FachadaLogica fachada = new FachadaLogica();
        Vehiculos listaVehiculo = new Vehiculos();
        ArrayList<Vehiculo> v = new ArrayList<Vehiculo>();
        DefaultTableModel model = new DefaultTableModel();
        tdVehiculosDatos.setModel(model);
        model.addColumn("Marca");
        model.addColumn("Modelo");
        model.addColumn("Patente");
        model.addColumn("Padron");
        model.addColumn("Asientos");
        model.addColumn("KM");
        model.addColumn("Precio");
        model.addColumn("Fecha");
        model.addColumn("Disponibilidad");
        if (tdVehiculosDatos.getColumnModel().getColumnCount() > 0) {
            tdVehiculosDatos.getColumnModel().getColumn(4).setMaxWidth(40);
            tdVehiculosDatos.getColumnModel().getColumn(6).setMaxWidth(40);
            tdVehiculosDatos.getColumnModel().getColumn(8).setMaxWidth(40);
        }
        try {
          listaVehiculo = fachada.obtenerVehiculos();//OBTENGO MI LISTA
          v = listaVehiculo.getVehiculos();
         
            String[] datosVehiculos = new String[9];
            for (Vehiculo datos : v) {
                datosVehiculos[0]= datos.getMarca();
                datosVehiculos[1]= datos.getModelo();
                datosVehiculos[2]= datos.getPatente();
                datosVehiculos[3]= datos.getPadron();
                datosVehiculos[4]= datos.getCantidadAsientos();
                datosVehiculos[5]= datos.getKilometros();
                datosVehiculos[6]= datos.getPrecio();
                datosVehiculos[7]= datos.getFecha();
                datosVehiculos[8]= datos.getDisponibilidad();
                model.addRow(datosVehiculos);
            }
            
          } catch (VehiculosException ex) {
            Logger.getLogger(VentanaVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UsuarioException ex) {
            Logger.getLogger(VentanaVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    public void borrarVehiculo() /*throws PersistenciaException*/{
        FachadaLogica flogica = new FachadaLogica();
        Vehiculo bVehiculo = new Vehiculo();
        int fila = tdVehiculosDatos.getSelectedRow();//Selecionar fila
        
        if (fila > -1){
            String bMarca = String.valueOf(tdVehiculosDatos.getValueAt(fila, 0));
            bVehiculo.setMarca(bMarca);
            try {
                flogica.bajaVehiculos(bVehiculo);
                fila = -1;
                JOptionPane.showMessageDialog(null,"Vehiculo " + bVehiculo.getMarca()+ " ha sido borrado"+" "+JOptionPane.INFORMATION_MESSAGE);
                listarVehiculos();
            } catch (SQLException ex) {
                Logger.getLogger(VentanaVehiculos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PersistenciaException ex) {
                Logger.getLogger(VentanaVehiculos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void calcularTotal(){
        int precio = 0;
        int total = 0,dias;
        int fila = tdVehiculosDatos.getSelectedRow();//Selecionar fila
        
        if (fila > -1){
            precio = Integer.parseInt((String) tdVehiculosDatos.getValueAt(fila, 6));
            System.out.println(precio);
            dias = Integer.parseInt((String)txtCantidadDeDias.getText());
            System.out.println(dias);
            total = precio*dias;
            System.out.println(total);
            txtTotal.setText(String.valueOf(total));//CONVERTIR DE INT A STRING
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnListarVehiculos = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tdVehiculosDatos = new javax.swing.JTable();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCantidadDeDias = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 480));

        btnListarVehiculos.setText("Ver Vehiculos");
        btnListarVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListarVehiculosMouseClicked(evt);
            }
        });
        btnListarVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarVehiculosActionPerformed(evt);
            }
        });

        btnAtras.setText("Atras");
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasMouseClicked(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        tdVehiculosDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Marca", "Modelo", "Patente", "Padron", "Cantidad de Asientos", "Kilometros", "Precio", "Fecha", "Disponibilidad"
            }
        ));
        jScrollPane3.setViewportView(tdVehiculosDatos);
        if (tdVehiculosDatos.getColumnModel().getColumnCount() > 0) {
            tdVehiculosDatos.getColumnModel().getColumn(4).setMaxWidth(40);
            tdVehiculosDatos.getColumnModel().getColumn(6).setMaxWidth(40);
            tdVehiculosDatos.getColumnModel().getColumn(8).setMaxWidth(40);
        }

        jLabel1.setText("Cantidad de Dias");

        jLabel2.setText("Total");

        txtCantidadDeDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadDeDiasActionPerformed(evt);
            }
        });

        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTotal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtCantidadDeDias, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnCalcular, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)))
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(txtCantidadDeDias, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCalcular)
                        .addGap(76, 76, 76))))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantidadDeDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnCalcular))
                .addGap(37, 37, 37))
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Alquilar Vehiculo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnListarVehiculos)
                .addGap(260, 260, 260))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnListarVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarVehiculosActionPerformed
        listarVehiculos();
    }//GEN-LAST:event_btnListarVehiculosActionPerformed

    private void btnListarVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListarVehiculosMouseClicked
       
    }//GEN-LAST:event_btnListarVehiculosMouseClicked

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseClicked
        Alquileres al = new Alquileres();   
        al.setVisible(true);
        dispose();
     
    }//GEN-LAST:event_btnAtrasMouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        borrarVehiculo();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void txtCantidadDeDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadDeDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadDeDiasActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        calcularTotal();
    }//GEN-LAST:event_btnCalcularActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaVehiculos().setVisible(true);
   }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnListarVehiculos;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tdVehiculosDatos;
    private javax.swing.JTextField txtCantidadDeDias;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
