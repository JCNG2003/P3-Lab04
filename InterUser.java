/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnosentremedica01;

import java.util.Scanner;

/**
 *
 * @author CESAR
 */
public class InterUser {
        private ColaTurnos cola;

    public InterUser() {
        cola = new ColaTurnos();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Agregar turno");
            System.out.println("2. Siguiente turno");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del turno: ");
                    String nombre = scanner.next();
                    cola.agregarTurno(new Turno(nombre));
                    System.out.println("Turno agregado.");
                    break;
                case 2:
                    Turno turno = cola.siguienteTurno();
                    if (turno != null) {
                        System.out.println("Turno: " + turno.getNombre());
                    } else {
                        System.out.println("No hay turnos pendientes.");
                    }
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }

            System.out.println();
        }
    }
}

