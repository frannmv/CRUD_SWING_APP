package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Estudiantes extends JFrame {
    static Connection con;
    PreparedStatement prepStatement;
    DefaultListModel mod = new DefaultListModel(); //se está creando una lista de modelos predeterminada que pueden almacenar y administrar datos que se mostrarán en componentes de lista
    Statement statement;
    ResultSet resultSet;
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
        }
    }
    public void limpiarFormulario(){
        this.txtLegajo.setText("");
        this.txtNombre.setText("");
        this.txtApellido.setText("");
        this.txtEdad.setText("");
        this.txtTelefono.setText("");
        this.txtCarrera.setText("");
    }

    public boolean seAgregoEstudiante(PreparedStatement ps) throws SQLException {
        return ps.executeUpdate() > 0;
    }
    public void msjExitoEnLista(){
        mod.addElement(" ¡ Inserción Exitosa !");
    }
    public void removerElementosLista(){
        listaConsultar.setModel(mod);
        mod.removeAllElements();
    }
    public void removerElementosListaYmsjExito(){
        removerElementosLista();
        msjExitoEnLista();
    }
    public void insertarEstudiante() throws SQLException {

        prepStatement = con.prepareStatement("INSERT INTO estudiantes VALUES (?,?,?,?,?,?) ");
        prepStatement.setInt(1, Integer.parseInt(this.getLegajo()));
        prepStatement.setString(2,this.getNombre());
        prepStatement.setString(3,this.getApellido());
        prepStatement.setInt(4,Integer.parseInt(this.getEdad()));
        prepStatement.setString(5,this.getTelefono());
        prepStatement.setString(6,this.getCarrera());

    }
    public void insertarEstudianteYLimpiarForm() throws SQLException {
        insertarEstudiante();
        if(seAgregoEstudiante(prepStatement)){
            removerElementosListaYmsjExito();
            limpiarFormulario();
        }
    }
    public void consultarEstudiantes() throws SQLException {
        statement = con.createStatement();
        resultSet = statement.executeQuery("SELECT legajo,name,surname FROM estudiantes");
        while (resultSet.next()){
            mod.addElement(
                    resultSet.getString(1)+ " " + resultSet.getString(2)+" " + resultSet.getString(3)
            );
        }
    }
    public void consultarEstudiantesYLimpiarForm() throws SQLException {
        removerElementosLista();
        consultarEstudiantes();
    }
    private JLabel lblTItulo;
    public JPanel panelFormulario;
    private JPanel panelBotones;
    private JPanel panelCargarDatos;
    private JPanel panelConsultar;
    private JButton btnConsultar;
    private JButton btnCargarDatos;
    public JTextField txtLegajo;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtEdad;
    public JTextField txtTelefono;
    public JTextField txtCarrera;
    private JLabel lblLegajo;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblEdad;
    private JLabel lblTelefono;
    private JLabel lblCarrera;
    private JList listaConsultar;

    public Estudiantes(){
        btnCargarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertarEstudianteYLimpiarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    consultarEstudiantesYLimpiarForm();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public String getLegajo(){
        return txtLegajo.getText();
    }
    public String getNombre(){
        return txtNombre.getText();
    }
    public String getApellido(){
        return txtApellido.getText();
    }
    public String getEdad(){
        return txtEdad.getText();
    }
    public String getTelefono(){
        return txtTelefono.getText();
    }
    public String getCarrera(){
        return txtCarrera.getText();
    }
}
