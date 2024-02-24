package logica;

import gui.Estudiantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    private static void inicializarFormulario(){
        Estudiantes est = new Estudiantes();
        est.setContentPane(est.panelFormulario);
        est.setVisible(true);
        est.setSize(500,350);
        est.setLocationRelativeTo(null);
    }
    private static void conectarDB(){
        try{
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/CRUDAaapp",
                    "root",
                    "Boca2007!"
            );
            System.out.println("CONEXION ESTABLECIDA");
        }catch (SQLException e){
            System.out.println(e);
            //throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        inicializarFormulario();
        conectarDB();
    }
}
