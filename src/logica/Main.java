package logica;

import gui.Estudiantes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main {
    static Connection con;
    PreparedStatement prepStatement;

    public static void main(String[] args) {
        inicializarFormulario();
        conectarDB();
    }

    private static void inicializarFormulario(){
        Estudiantes est = new Estudiantes();
        est.setContentPane(est.panelFormulario);
        est.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        est.setVisible(true);
        est.setSize(500,350);
        est.setLocationRelativeTo(null);
    }
    private static void conectarDB(){
        try{
             con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/CRUDApp?serverTimezone=UTC",
                    "root",
                    "Boca2007!"
            );
            System.out.println("CONEXION ESTABLECIDA");
        }catch (SQLException e){
            System.out.println(e);
            //throw new RuntimeException(e);
        }
    }
    public void limpiarFormulario(Estudiantes est){
        est.txtLegajo.setText("");
        est.txtNombre.setText("");
        est.txtApellido.setText("");
        est.txtEdad.setText("");
        est.txtTelefono.setText("");
        est.txtCarrera.setText("");
    }
    public void insertarEstudiante() throws SQLException {
        Estudiantes cargaEst = new Estudiantes();
        prepStatement = con.prepareStatement("INSERT INTO estudiantes VALUES (?,?,?,?,?,?) ");
        prepStatement.setInt(1, Integer.parseInt(cargaEst.getLegajo()));
        prepStatement.setString(2,cargaEst.getNombre());
        prepStatement.setString(3,cargaEst.getApellido());
        prepStatement.setInt(4,Integer.parseInt(cargaEst.getEdad()));
        prepStatement.setString(5,cargaEst.getTelefono());
        prepStatement.setString(6,cargaEst.getCarrera());

        limpiarFormulario(cargaEst);
    }
}
