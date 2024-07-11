import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form1 extends JFrame {
    private JPanel mainPanel;
    private JButton ingresarButton;
    private JTextField placa;
    private JLabel resultado;
    private JTextField marca;
    private JTextField cilindraje;
    private JTextField combustible;
    private JTextField color;
    private JTextField propietario;
    private JButton regresar;
    private JButton borrar;

    public form1() {
        setTitle("Ingresar Datos");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600,600));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehiculos vehiculo = new vehiculos(placa.getText(), marca.getText(), Double.parseDouble(cilindraje.getText()), color.getText(), propietario.getText(), combustible.getText());
                String url = "jdbc:mysql://localhost:3306/conce";
                String user = "root";
                String password = "123456";
                String query = "insert into vehiculos (placa, marca, cilindraje, tipo_combustible, color, propietario) values(?,?,?,?,?,?)";

                if (placa.getText()==null || marca.getText() == null || cilindraje.getText() == null || color.getText() == null || propietario.getText() == null) {
                    JOptionPane.showMessageDialog(null, "Datos invalidos");
                    return;
                }

                try(Connection con = DriverManager.getConnection(url, user, password)){
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, vehiculo.getPlaca());
                    ps.setString(2, vehiculo.getMarca());
                    ps.setDouble(3, vehiculo.getCilindraje());
                    ps.setString(4, vehiculo.getTipo_combustible());
                    ps.setString(5, vehiculo.getColor());
                    ps.setString(6, vehiculo.getPropietario());
                    ps.executeUpdate();
                    resultado.setText("Datos Ingresados Correctamente");
                }catch (SQLException e1){
                    e1.printStackTrace();
                    resultado.setText("Datos Ingresados Incorrectamente");
                }
            }
        });
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form3();
                setVisible(false);
            }
        });
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placa.setText("");
                marca.setText("");
                cilindraje.setText("");
                color.setText("");
                propietario.setText("");
                combustible.setText("");
                resultado.setText("");
            }
        });
    }
}
