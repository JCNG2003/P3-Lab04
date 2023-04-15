/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueader;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CESAR
 */
public class Parqueadero extends javax.swing.JFrame implements ActionListener {

    private JLabel lblPlaca, lblTipo, lblHora, lblNumVehiculo;
    private JTextField txtPlaca, txtTipo, txtHora, txtNumVehiculo;
    private JButton btnIngreso, btnVisualizar, btnVehiculos2Ruedas, btnVehiculos4Ruedas, btnCantidadTotal, btnEliminar, btnSalir;
    private JTable tblVehiculos;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private ArrayList<Vehiculo> vehiculos;
    private Stack<Vehiculo> vehiculos2Ruedas;
    private Stack<Vehiculo> vehiculos4Ruedas;
    private int numVehiculos = 0;
    private int totalPagar = 0;

    public Parqueadero() {
        // Configurar ventana principal
        super("Parqueadero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        // Inicializar componentes
        lblPlaca = new JLabel("Placa:");
        lblTipo = new JLabel("Tipo:");
        lblHora = new JLabel("Hora de ingreso:");
        lblNumVehiculo = new JLabel("Número de vehículo:");
        txtPlaca = new JTextField();
        txtTipo = new JTextField();
        txtHora = new JTextField();
        txtNumVehiculo = new JTextField();
        btnIngreso = new JButton("Ingreso de vehículo");
        btnVisualizar = new JButton("Visualizar tabla actualizada");
        btnVehiculos2Ruedas = new JButton("Vehículos de 2 ruedas");
        btnVehiculos4Ruedas = new JButton("Vehículos de 4 ruedas");
        btnCantidadTotal = new JButton("Cantidad total de vehículos y valor a pagar");
        btnEliminar = new JButton("Eliminar vehículo");
        btnSalir = new JButton("Salir");
        modeloTabla = new DefaultTableModel();
        tblVehiculos = new JTable(modeloTabla);
        scrollPane = new JScrollPane(tblVehiculos);
        vehiculos = new ArrayList<>();
        vehiculos2Ruedas = new Stack<>();
        vehiculos4Ruedas = new Stack<>();

        // Configurar componentes
        lblPlaca.setBounds(50, 50, 100, 20);
        lblTipo.setBounds(50, 80, 100, 20);
        lblHora.setBounds(50, 110, 100, 20);
        lblNumVehiculo.setBounds(50, 140, 150, 20);
        txtPlaca.setBounds(200, 50, 150, 20);
        txtTipo.setBounds(200, 80, 150, 20);
        txtHora.setBounds(200, 110, 150, 20);
        txtNumVehiculo.setBounds(200, 140, 150, 20);
        btnIngreso.setBounds(400, 50, 200, 20);
        btnVisualizar.setBounds(400, 80, 200, 20);
        btnVehiculos2Ruedas.setBounds(50, 180, 200, 20);
        btnVehiculos4Ruedas.setBounds(300, 180, 200, 20);
        btnCantidadTotal.setBounds(50, 220, 200, 20);
        btnEliminar.setBounds(300, 220, 200, 20);
        btnSalir.setBounds(200, 260, 200, 20);
        scrollPane.setBounds(50, 300, 550, 150);
        // Agregar componentes a la ventana
        add(lblPlaca);
        add(lblTipo);
        add(lblHora);
        add(lblNumVehiculo);
        add(txtPlaca);
        add(txtTipo);
        add(txtHora);
        add(txtNumVehiculo);
        add(btnIngreso);
        add(btnVisualizar);
        add(btnVehiculos2Ruedas);
        add(btnVehiculos4Ruedas);
        add(btnCantidadTotal);
        add(btnEliminar);
        add(btnSalir);
        add(scrollPane);

        // Configurar tabla
        modeloTabla.addColumn("Número de vehículo");
        modeloTabla.addColumn("Placa");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Hora de ingreso");
        modeloTabla.addColumn("Valor a pagar");

        // Configurar eventos de botones
        btnIngreso.addActionListener(this);
        btnVisualizar.addActionListener(this);
        btnVehiculos2Ruedas.addActionListener(this);
        btnVehiculos4Ruedas.addActionListener(this);
        btnCantidadTotal.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnSalir.addActionListener(this);

        // Mostrar ventana
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngreso) {
            ingresarVehiculo();
        } else if (e.getSource() == btnVisualizar) {
            visualizarTabla();
        } else if (e.getSource() == btnVehiculos2Ruedas) {
            visualizarVehiculos2Ruedas();
        } else if (e.getSource() == btnVehiculos4Ruedas) {
            visualizarVehiculos4Ruedas();
        } else if (e.getSource() == btnCantidadTotal) {
            cantidadTotalVehiculos();
        } else if (e.getSource() == btnEliminar) {
            eliminarVehiculo();
        } else if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }

    public void ingresarVehiculo() {
        // Obtener datos de los campos de texto
        String placa = txtPlaca.getText();
        String tipo = txtTipo.getText();
        String hora = txtHora.getText();

        // Validar que se hayan ingresado todos los datos
        if (placa.isEmpty() || tipo.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calcular valor a pagar
        int valorPagar = calcularValorPagar(tipo);

        // Incrementar número de vehículos
        numVehiculos++;

        // Crear objeto Vehiculo y agregarlo a la lista de vehículos
        Vehiculo vehiculo = new Vehiculo(numVehiculos, placa, tipo, hora, valorPagar);
        vehiculos.add(vehiculo);

        // Agregar fila a la tabla
        modeloTabla.addRow(new Object[]{vehiculo.getNumVehiculo(), vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getHora(), vehiculo.getValorPagar()});

        // Limpiar campos de texto
        txtPlaca.setText("");
        txtTipo.setText("");
        txtHora.setText("");
    }

    public int calcularValorPagar(String tipo) {
        int valorPagar = 0;

        switch (tipo) {
            case "Bicicleta":
            case "Ciclomotor":
                valorPagar = 20;
                break;
            case "Motocicleta":
            case "Automóvil":
                valorPagar = 40;
                break;
            case "Camioneta":
            case "Autobús":
                valorPagar = 60;
                break;
            default:
                valorPagar = 0;
                break;
        }
        return valorPagar;
    }

    public void visualizarTabla() {
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay vehículos ingresados", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Tabla de vehículos ingresados", true);
        dialog.setSize(700, 300);
        dialog.setLocationRelativeTo(this);

        JTable tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        dialog.add(scrollPane);

        dialog.setVisible(true);
    }

    public void visualizarVehiculos2Ruedas() {
        int cantidad = 0;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getTipo().equals("Bicicleta") || vehiculo.getTipo().equals("Ciclomotor")) {
                cantidad++;
            }
        }

        JOptionPane.showMessageDialog(this, "Cantidad de vehículos de 2 ruedas: " + cantidad, "Cantidad de vehículos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void visualizarVehiculos4Ruedas() {
        int cantidad = 0;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getTipo().equals("Motocicleta") || vehiculo.getTipo().equals("Automóvil") || vehiculo.getTipo().equals("Camioneta") || vehiculo.getTipo().equals("Autobús")) {
                cantidad++;
            }
        }

        JOptionPane.showMessageDialog(this, "Cantidad de vehículos de 4 ruedas: " + cantidad, "Cantidad de vehículos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cantidadTotalVehiculos() {
        JOptionPane.showMessageDialog(this, "Cantidad total de vehículos ingresados: " + numVehiculos, "Cantidad total de vehículos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void eliminarVehiculo() {
        int numVehiculo = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el número de vehículo a eliminar", "Eliminar vehículo", JOptionPane.QUESTION_MESSAGE));
        boolean encontrado = false;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getNumVehiculo() == numVehiculo) {
                vehiculos.remove(vehiculo);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "No se encontró ningún vehículo con ese número", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

// Actualizar tabla
        modeloTabla.setRowCount(0);

        for (Vehiculo vehiculo : vehiculos) {
            modeloTabla.addRow(new Object[]{vehiculo.getNumVehiculo(), vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getHora(), vehiculo.getValorPagar()});
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

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
            java.util.logging.Logger.getLogger(Parqueadero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Parqueadero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Parqueadero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Parqueadero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Parqueadero().setVisible(true);
            }
        });
    }



    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
