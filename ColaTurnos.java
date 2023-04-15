/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnosentremedica01;
import java.util.LinkedList;

/**
 *
 * @author CESAR
 */
public class ColaTurnos {
    private LinkedList<Turno> cola;

    public ColaTurnos() {
        cola = new LinkedList<Turno>();
    }

    public void agregarTurno(Turno turno) {
        cola.add(turno);
    }

    public Turno siguienteTurno() {
        return cola.poll();
    }
    
}
