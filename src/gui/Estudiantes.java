package gui;
import javax.swing.*;
public class Estudiantes extends JFrame {

    private JLabel lblTItulo;
    public JPanel panelFormulario;
    private JPanel panelBotones;
    private JPanel panelCargarDatos;
    private JPanel panelConsultar;
    private JButton btnConsultar;
    private JButton btnCargarDatos;
    public JTextField txtLegajo;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JTextField txtTelefono;
    private JTextField txtCarrera;
    private JLabel lblLegajo;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblEdad;
    private JLabel lblTelefono;
    private JLabel lblCarrera;
    private JList listaConsultar;

    public Estudiantes(){

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
