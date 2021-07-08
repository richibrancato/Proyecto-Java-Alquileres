/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.presentacion;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.UIManager.get;
import javax.swing.table.DefaultTableModel;
import uy.proyectofinal.excepciones.PersistenciaException;
import uy.proyectofinal.excepciones.UsuarioException;
import uy.proyectofinal.excepciones.UsuarioYaExisteException;
import uy.proyectofinal.logica.FachadaLogica;
import uy.proyectofinal.logica.Usuario;
import uy.proyectofinal.logica.Usuarios;
import uy.proyectofinal.logica.Vehiculos;

/**
 *
 * @author Richard
 */
public class VentanaAdminUsuario extends javax.swing.JFrame {

    /**
     * Creates new form VentanaAdminUsuario
     */
    public VentanaAdminUsuario() {
        initComponents();
        setTitle("Admin");
        setLocationRelativeTo(null);
        setResizable(false);
        listarUsuarios();
        
    }
    //METEDO LISTAR USUARIOS//
    public void listarUsuarios(){
        FachadaLogica fachada = new FachadaLogica();
        Usuarios listaUsuario = new Usuarios();
        
        DefaultTableModel model = new DefaultTableModel();  
        jTable1.setModel(model);
        model.addColumn("id");
        model.addColumn("nomUsuario");
        model.addColumn("clave");
        
        try {
            listaUsuario = fachada.obtenerUsuarios();//OBTENGO MI LISTA
            int largo = listaUsuario.getUsuarioList().size();
            String[] filas = new String[largo];
            int i = 0;
            while (i <= largo-1){
                filas[0] = listaUsuario.getUsuarioList().get(i).getIdUsuario();
                filas[1] = listaUsuario.getUsuarioList().get(i).getNomUsuario();
                filas[2] = listaUsuario.getUsuarioList().get(i).getClave();
                i++;
                model.addRow(filas);
            }
        } catch (UsuarioException ex) {
            Logger.getLogger(VentanaAdminUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    //METEDO MODIFICAR USUARIOS//
    public void borrarUsuario() /*throws SQLException*/  {
        
        FachadaLogica flogica = new FachadaLogica();
        Usuario bUsuario = new Usuario();
        int fila = jTable1.getSelectedRow();

        if (fila > -1){
            String bNombre = String.valueOf(jTable1.getValueAt(fila, 1));
            bUsuario.setNomUsuario(bNombre);
            try {
                flogica.bajaUsuario(bUsuario);
                fila = -1;
                JOptionPane.showMessageDialog(null,"Usuario " + bUsuario.getNomUsuario()+ " ha sido borrado"+" "+JOptionPane.INFORMATION_MESSAGE);
                listarUsuarios();
            } catch (PersistenciaException ex) {
                System.err.println("PersistenciaException: " + ex.getMessage());
            
           // }catch (SQLException ex) {
               // throw new SQLException("Error al validar Usuario");
        }   catch (SQLException ex) {
                Logger.getLogger(VentanaAdminUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //METEDO MODIFICAR USUARIOS//
    public void modificarUsuario(){
        FachadaLogica flogica = new FachadaLogica();
        Usuario bUsuario = new Usuario();
        int fila = jTable1.getSelectedRow();
        
        if (fila > -1){//PARA SABER SI FUE SELECIONADA LA FILA
            String bId     = String.valueOf(jTable1.getValueAt(fila, 0));
            String bNombre = String.valueOf(jTable1.getValueAt(fila, 1));
            String bClave  = String.valueOf(jTable1.getValueAt(fila, 2));
            bUsuario.setIdUsuario(bId);
            bUsuario.setNomUsuario(bNombre);
            bUsuario.setClave(bClave);
            try {
                flogica.modificarUsuario(bUsuario);
                fila = -1;
                //JOptionPane.showMessageDialog(null,"Usuario " + bUsuario.getNomUsuario()+ " ha sido modificado"+" "+JOptionPane.INFORMATION_MESSAGE);
                //listarUsuarios();
            } catch (PersistenciaException ex) {
                System.err.println("PersistenciaException: " + ex.getMessage());
            }
        } 
    }
    public void guardarUsuario() {
        try {
            Usuario usuario = new Usuario();
            FachadaLogica fluser = new FachadaLogica();
            //ValidacionesVentanas validacionClave = new ValidacionesVentanas();
            usuario.setNomUsuario(txtNombre1.getText());
            usuario.setClave(txtClave1.getText());

            fluser.altaUsuario(usuario);
            Boolean usuarioValido = fluser.validarIngreso(usuario);
            //Boolean validacionclave = validacionClave.confirmarClave(txtClave.getText(), txtRepClave.getText());
            if (usuarioValido ) {
                
                listarUsuarios();

            } else {
                System.out.println("EL USUARIO NO ES VALIDO");
                JOptionPane.showMessageDialog(this, "El usuario que has introducido no coincide con ninguna cuenta", "MENSAJE DE ERROR ", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(VentanaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UsuarioYaExisteException ex) {
            Logger.getLogger(VentanaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UsuarioException ex) {
            Logger.getLogger(VentanaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaAdminUsuario.class.getName()).log(Level.SEVERE, null, ex);
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

        btnModificar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnBorrar = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();
        btnIngresar = new javax.swing.JButton();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        txtClave1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnListar.setText("Listar");
        btnListar.setPreferredSize(new java.awt.Dimension(40, 30));
        btnListar.setRequestFocusEnabled(false);
        btnListar.setSelected(true);
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnIngresar.setText("Ingrsear");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jDesktopPane3.setPreferredSize(new java.awt.Dimension(310, 200));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel10.setText("Clave");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setText("Nombre");

        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });

        txtClave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClave1ActionPerformed(evt);
            }
        });

        jDesktopPane3.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(txtNombre1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(txtClave1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
        jDesktopPane3.setLayout(jDesktopPane3Layout);
        jDesktopPane3Layout.setHorizontalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClave1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane3Layout.setVerticalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(48, Short.MAX_VALUE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDesktopPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listarUsuarios();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificarUsuario();
        listarUsuarios();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
       
        
            borrarUsuario();
        
        
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        ValidacionesVentanas validacionNombre = new ValidacionesVentanas();

        if(validacionNombre.usuarioValido(txtNombre1.getText())){
            System.out.println("NOMBRE VALIDO");
        }else{
            JOptionPane.showMessageDialog(null, "El largo no debe superar 15 caracteres", "Nombre", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtClave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClave1ActionPerformed
        ValidacionesVentanas validacionClave = new ValidacionesVentanas();

        if(validacionClave.usuarioValido(txtClave1.getText())){
            System.out.println("CLAVE VALIDA");
        }else{
            JOptionPane.showMessageDialog(null, "El largo no debe superar 30 caracteres", "Clave", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtClave1ActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
     guardarUsuario();
        
        
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        VentanaLogin vl = new VentanaLogin();
        vl.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaAdminUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAdminUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAdminUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAdminUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAdminUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtClave1;
    private javax.swing.JTextField txtNombre1;
    // End of variables declaration//GEN-END:variables
}
