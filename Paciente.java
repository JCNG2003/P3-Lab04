/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnosentremedica02;

/**
 *
 * @author CESAR
 */
class Paciente {
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

        public Paciente() {

        }

    Paciente(String nombre, int edad, String esPlanComplementario, String esEmbarazada, String tieneLimitacionMotriz) {
        this.nombre = nombre;
    this.edad = edad;
    this.esPlanComplementario = Boolean.parseBoolean(esPlanComplementario);
    this.esEmbarazada = Boolean.parseBoolean(esEmbarazada);
    this.tieneLimitacionMotriz = Boolean.parseBoolean(tieneLimitacionMotriz);
    }

    Paciente(String nombre, int edad, String afiliacion, String condicionEspecial, int prioridad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public boolean isEsPlanComplementario() {
            return esPlanComplementario;
        }

        public void setEsPlanComplementario(boolean esPlanComplementario) {
            this.esPlanComplementario = esPlanComplementario;
        }

        public boolean isEsEmbarazada() {
            return esEmbarazada;
        }

        public void setEsEmbarazada(boolean esEmbarazada) {
            this.esEmbarazada = esEmbarazada;
        }

        public boolean isTieneLimitacionMotriz() {
            return tieneLimitacionMotriz;
        }

        public void setTieneLimitacionMotriz(boolean tieneLimitacionMotriz) {
            this.tieneLimitacionMotriz = tieneLimitacionMotriz;
        }

    String getTurno() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getNombreCompleto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTurno(int turno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean getPrioridad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
