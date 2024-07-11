import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form2 extends JFrame {
    private JPanel mainPanel;
    private JTextField buscar;
    private JButton button1;
    private JLabel placa;
    private JLabel marca;
    private JLabel cilindraje;
    private JLabel combustible;
    private JLabel color;
    private JLabel propietario;
    private JButton regresar;

    public form2() {
        setTitle("Buscar Datos");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400,600));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/conce";
                String user = "root";
                String password = "123456";
                String query = "select * from vehiculos where placa = '"+buscar.getText()+"'";
                try (Connection connection = DriverManager.getConnection(url,user,password)){
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    if(resultSet.next()){
                        System.out.println("Datos encontrados");
                        placa.setText("Placa: "+resultSet.getString("placa"));
                        marca.setText("Marca: "+resultSet.getString("marca"));
                        cilindraje.setText("Cilindraje: "+resultSet.getString("cilindraje"));
                        combustible.setText("Combustible: "+resultSet.getString("tipo_combustible"));
                        color.setText("Color: "+resultSet.getString("color"));
                        propietario.setText("Propietario: "+resultSet.getString("propietario"));
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
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
    }
}
