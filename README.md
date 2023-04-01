# P3-Lab04


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnosentremedica03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

/**
 *
 * @author CESAR
 */
public class TurnosEntremedica03 {

    private static class Paciente {

        public Paciente() {
        }

        private Paciente(String nombre, int edad, String afiliacion, String condicionEspecial, int prioridad) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String getTurno() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String getNombreCompleto() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setTurno(int turno) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public class EPS extends JFrame implements ActionListener {

        private LinkedList<Paciente> cola;
        private Paciente pacienteActual;
        private JLabel turnoLabel;
        private JLabel tiempoLabel;
        private JLabel pendientesLabel;
        private Timer timer;
        private int tiempoRestante;
        private int turnos;

        public EPS() {
            super("Asignación de turnos EPS");

            // Crea la cola de pacientes
            cola = new LinkedList<>();

            // Crea los componentes de la interfaz gráfica
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            turnoLabel = new JLabel("Turno actual: ");
            panel.add(turnoLabel);
            tiempoLabel = new JLabel("Tiempo restante: ");
            panel.add(tiempoLabel);
            pendientesLabel = new JLabel("Turnos pendientes: ");
            panel.add(pendientesLabel);
            JButton extenderButton = new JButton("Extender tiempo");
            extenderButton.addActionListener(this);
            panel.add(extenderButton);
            JButton nuevoPacienteButton = new JButton("Nuevo paciente");
            nuevoPacienteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Crea un nuevo paciente y lo agrega a la cola
                    Paciente paciente = crearPaciente();
                    cola.add(paciente);
                    pendientesLabel.setText("Turnos pendientes: " + cola.size());
                    // Si no hay pacientes en espera, el nuevo paciente es el siguiente
                    if (pacienteActual == null) {
                        siguientePaciente();
                    }
                }
            });
            panel.add(nuevoPacienteButton);
            add(panel);

            // Configura la ventana principal
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 200);
            setVisible(true);

            // Crea el temporizador
            tiempoRestante = 5; // 5 segundos
            timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pacienteActual != null) {
                        tiempoRestante--;
                        if (tiempoRestante == 0) {
                            // Muestra el nombre y el número de turno del paciente actual
                            JOptionPane.showMessageDialog(null, "Turno " + pacienteActual.getTurno() + ": " + pacienteActual.getNombreCompleto());
                            // Pasa al siguiente paciente en la cola
                            siguientePaciente();
                        } else {
                            tiempoLabel.setText("Tiempo restante: " + tiempoRestante + " segundos");
                        }
                    }
                }
            });
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Extender tiempo")) {
                tiempoRestante += 5; // Extiende el tiempo en 5 segundos
                tiempoLabel.setText("Tiempo restante: " + tiempoRestante + " segundos");
            }
        }

        private void siguientePaciente() {
            if (!cola.isEmpty()) {
                // Obtiene el siguiente paciente en la cola
                pacienteActual = cola.poll();
                tiempoRestante = 5; // Reinicia el tiempo restante
                turnoLabel.setText("Turno actual: " + pacienteActual.getTurno());
                pendientesLabel.setText("Turnos pendientes: " + cola.size());
                // Mostrar el nombre y turno del paciente actual en un cuadro de diálogo
                JOptionPane.showMessageDialog(null, "Turno " + pacienteActual.getTurno() + ": " + pacienteActual.getNombreCompleto());
            } else {
                // No hay más pacientes en la cola
                pacienteActual = null;
                tiempoLabel.setText("Tiempo restante: ");
                turnoLabel.setText("Turno actual: ");
                pendientesLabel.setText("Turnos pendientes: ");
            }
        }

        private void asignarTurno(Paciente paciente) {
            // Asigna el turno al paciente
            int turno = turnos++;
            paciente.setTurno(turno);
            // Personas de la tercera edad o menores de 12 años
            // Mujeres embarazadas
            // Limitación motriz
            // Afiliados a plan complementario y/o prepagada
//            int turno = turnos.size() + 1;

            // Agrega el paciente a la cola, ordenando por excepciones prioritarias
            int index = 0;
            for (Paciente p : cola) {
                if (paciente.getPrioridad() > p.getPrioridad()) {
                    cola.add(index, paciente);
                    pendientesLabel.setText("Turnos pendientes: " + cola.size());
                    return;
                }
                index++;
            }
// Si no se cumple ninguna excepción prioritaria, agregar al final de la cola
            cola.add(paciente);
            pendientesLabel.setText("Turnos pendientes: " + cola.size());

        }

        private Paciente crearPaciente() {
            String nombre = JOptionPane.showInputDialog("Ingrese su nombre y apellidos:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad:"));
            String afiliacion = JOptionPane.showInputDialog("Ingrese su afiliación (POS o PC):");
            String condicionEspecial = JOptionPane.showInputDialog("¿Tiene alguna condición especial? (embarazo o limitación motriz):");

//            // Verifica las excepciones prioritarias
//            boolean esPrioritario = false;
//            if (edad >= 60 || edad <= 12) {
//                esPrioritario = true;
//            } else if (condicionEspecial.equalsIgnoreCase("embarazo")) {
//                esPrioritario = true;
//            } else if (condicionEspecial.equalsIgnoreCase("limitación motriz")) {
//                esPrioritario = true;
//            } else if (afiliacion.equalsIgnoreCase("PC")) {
//                esPrioritario = true;
//            }
//
//            // Crea un nuevo paciente con los datos ingresados y la prioridad correspondiente
//            return new Paciente(nombre, edad, afiliacion, condicionEspecial, esPrioritario);
            // Crea un nuevo paciente y asigna su prioridad según su condición especial y edad
            int prioridad = 0;
            if (condicionEspecial.equalsIgnoreCase("embarazo")) {
                prioridad = 2;
            } else if (condicionEspecial.equalsIgnoreCase("limitación motriz")) {
                prioridad = 3;
            } else if (edad < 12 || edad >= 60) {
                prioridad = 1;
            }
            Paciente paciente = new Paciente(nombre, edad, afiliacion, condicionEspecial, prioridad);

// Asigna el turno al paciente y lo agrega a la cola
            asignarTurno(paciente);

            return paciente;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EPS eps = new EPS();
    }

}
