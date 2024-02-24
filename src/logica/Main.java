package logica;

import gui.Estudiantes;

public class Main {
    public static void main(String[] args) {
        Estudiantes est = new Estudiantes();
        est.setContentPane(est.panelFormulario);
        est.setVisible(true);
        est.setSize(500,350);
        est.setLocationRelativeTo(null);
    }
}
