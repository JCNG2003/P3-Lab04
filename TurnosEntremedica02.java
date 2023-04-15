/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnosentremedica02;


import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;
import javax.swing.*;

/**
 *
 * @author CESAR
 */
public class TurnosEntremedica02 extends JFrame {

    // Clase para representar los pacientes
    private static class Paciente {

        String nombre;
        int edad;
        boolean esPlanComplementario;
        boolean esEmbarazada;
        boolean tieneLimitacionMotriz;

        public Paciente(String nombre, int edad, boolean esPlanComplementario, boolean esEmbarazada, boolean tieneLimitacionMotriz) {
            this.nombre = nombre;
            this.edad = edad;
            this.esPlanComplementario = esPlanComplementario;
            this.esEmbarazada = esEmbarazada;
            this.tieneLimitacionMotriz = tieneLimitacionMotriz;
        }
    }
// Componentes de la interfaz de usuario
    private final JLabel turnoActualLabel;
    private final JTextArea pacientesTextArea;
    private final JTextField nombreTextField;
    private final JTextField edadTextField;
    private final JCheckBox esPlanComplementarioCheckBox;
    private final JCheckBox esEmbarazadaCheckBox;
    private final JCheckBox tieneLimitacionMotrizCheckBox;
    private final JButton agregarPacienteButton;

    // Cola para los pacientes en espera
    private final Queue<Paciente> colaPacientes;

    // Tiempo en segundos para cada turno
    private final int tiempoTurno = 300;

    // Timer para controlar el tiempo de cada turno
    private final Timer timer;

    // Número del turno actual
    private int numeroTurnoActual = 1;

    public TurnosEntremedica02() {
        // Configurar la ventana
        setTitle("Turnos Entremédica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Crear los componentes de la interfaz de usuario
        JPanel cabeceraPanel = new JPanel();
        turnoActualLabel = new JLabel("Turno Actual: " + numeroTurnoActual);
        cabeceraPanel.add(turnoActualLabel);

        JPanel pacientesPanel = new JPanel(new BorderLayout());
        pacientesTextArea = new JTextArea(10, 20);
        pacientesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(pacientesTextArea);
        pacientesPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel agregarPacientePanel = new JPanel(new GridLayout(6, 2));
        agregarPacientePanel.setBorder(BorderFactory.createTitledBorder("Agregar Paciente"));
        nombreTextField = new JTextField();
        edadTextField = new JTextField();
        esPlanComplementarioCheckBox = new JCheckBox("Plan Complementario");
        esEmbarazadaCheckBox = new JCheckBox("Embarazada");
        tieneLimitacionMotrizCheckBox = new JCheckBox("Limitación Motriz");
        agregarPacienteButton = new JButton("Agregar");
        agregarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPaciente();
            }
        });
        agregarPacientePanel.add(new JLabel("Nombre:"));
        agregarPacientePanel.add(nombreTextField);
        agregarPacientePanel.add(new JLabel("Edad:"));
        agregarPacientePanel.add(edadTextField);
        agregarPacientePanel.add(new JLabel(""));
        agregarPacientePanel.add(esPlanComplementarioCheckBox);
        agregarPacientePanel.add(esEmbarazadaCheckBox);
        agregarPacientePanel.add(new JLabel(""));
        agregarPacientePanel.add(tieneLimitacionMotrizCheckBox);
        agregarPacientePanel.add(new JLabel(""));
        agregarPacientePanel.add(new JLabel(""));
        agregarPacientePanel.add(agregarPacienteButton);
          // Agregar los componentes a la ventana
    add(cabeceraPanel, BorderLayout.NORTH);
    add(pacientesPanel, BorderLayout.CENTER);
    add(agregarPacientePanel, BorderLayout.SOUTH);

    // Inicializar la cola de pacientes
    colaPacientes = new LinkedList<>();

    // Configurar el timer para controlar el tiempo de cada turno
    timer = new Timer(tiempoTurno * 1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Verificar si hay pacientes en la cola
            if (!colaPacientes.isEmpty()) {
                // Obtener el próximo paciente
                Paciente paciente = colaPacientes.poll();

                // Actualizar la interfaz de usuario
                pacientesTextArea.setText(pacientesTextArea.getText() + "Turno #" + numeroTurnoActual + ": " + paciente.nombre + "\n");

                // Incrementar el número de turno actual
                numeroTurnoActual++;
                turnoActualLabel.setText("Turno Actual: " + numeroTurnoActual);
            }
        }
    });

    // Mostrar la ventana
    setVisible(true);
}

// Método para agregar un nuevo paciente a la cola
private void agregarPaciente() {
    // Obtener los datos del paciente
    String nombre = nombreTextField.getText();
    int edad = Integer.parseInt(edadTextField.getText());
    boolean esPlanComplementario = esPlanComplementarioCheckBox.isSelected();
    boolean esEmbarazada = esEmbarazadaCheckBox.isSelected();
    boolean tieneLimitacionMotriz = tieneLimitacionMotrizCheckBox.isSelected();

    // Crear el objeto Paciente y agregarlo a la cola
    Paciente paciente = new Paciente(nombre, edad, esPlanComplementario, esEmbarazada, tieneLimitacionMotriz);
    colaPacientes.offer(paciente);

    // Actualizar la interfaz de usuario
    pacientesTextArea.setText(pacientesTextArea.getText() + "Nuevo paciente: " + paciente.nombre + "\n");

    // Iniciar el timer si aún no está en ejecución
    if (!timer.isRunning()) {
        timer.start();
    }
}
    public static void main(String[] args) {
//         TODO code application logic here
// Cola para los pacientes en espera
        Queue<Paciente> colaPacientes = new LinkedList<>();

        // Tiempo en segundos para cada turno
        int tiempoTurno = 300;

        // Timer para controlar el tiempo de cada turno
        Timer timer = new Timer();

        // Número del turno actual
        int numeroTurnoActual = 1;

        // Bucle principal
        while (true) {

            // Mostrar el turno actual
            System.out.println("Turno Actual: " + numeroTurnoActual);

            // Si hay pacientes en espera, mostrarlos
            if (!colaPacientes.isEmpty()) {
                System.out.println("Pacientes en espera:");
                for (Paciente paciente : colaPacientes) {
                    System.out.println("- " + paciente.nombre);
                }
            } else {
                System.out.println("No hay pacientes en espera.");
            }

            // Preguntar por un nuevo paciente
            System.out.println("Ingrese un nuevo paciente:");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Nombre y apellidos: ");
            String nombre = scanner.nextLine();

            System.out.print("Edad: ");
            int edad = scanner.nextInt();

            System.out.print("Afiliación (POS o PC): ");
            String afiliacion = scanner.next();
            boolean esPlanComplementario = afiliacion.equals("PC");

            System.out.print("¿Es embarazada? (s/n): ");
            String respuesta = scanner.next().toLowerCase();
            boolean esEmbarazada = respuesta.equals("s");

            System.out.print("¿Tiene limitación motriz? (s/n): ");
            respuesta = scanner.next().toLowerCase();
            boolean tieneLimitacionMotriz = respuesta.equals("s");

            // Crear un nuevo paciente
            Paciente paciente = new Paciente(nombre, edad, esPlanComplementario, esEmbarazada, tieneLimitacionMotriz);

            // Agregar el paciente a la cola de espera, con excepciones prioritarias
            if (paciente.edad >= 65 || paciente.edad <= 12 || paciente.esEmbarazada || paciente.tieneLimitacionMotriz || paciente.esPlanComplementario) {
                colaPacientes.add(paciente);
            } else {
                colaPacientes.offer(paciente);
            }

        }
    }
}
