/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reilwaystation;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AmiraRabea
 */
public class Password  extends JFrame{
        Color color = new Color(153, 0, 0);
        Font font=new Font("SansSerif", Font.BOLD, 40);
        ImageIcon icon=new ImageIcon("image/Home.PNG");
        ImageIcon icon1=new ImageIcon("image/exit.PNG");
        
        JPanel pan1= new JPanel();
        JPanel pan2= new JPanel();
        JPanel pan3= new JPanel();
        
        JButton Login=new JButton("Login");
        JButton Home=new JButton(icon);
        JButton Exit =new JButton(icon1);
        
        JPasswordField Password1 =new JPasswordField(8);
        
        JLabel Password=new JLabel("Password");
        JLabel egypttrain=new JLabel("Egypt Train");
        
       class BListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //////////////home button backe frame"Search"
            if (e.getSource().equals(Home)) {
                Search search = new Search();
                search.setVisible(true);
                setVisible(false);
                search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
            ////////////////exit button (Close programe)
            else if (e.getSource().equals(Exit)) {
                System.exit(0);
            }

            // /login button go to frame"Login"
           else if (e.getSource().equals(Login)) {
                 LogIn login = new LogIn();
                /////////////////////////////////////////
               ///////////////show data from sql in table1 "train details"
                try {
                    
                   
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    PreparedStatement stmt = con.prepareStatement("select trip_number,train_number,from_where,to_where,leave_time,Reach_time,price,class,Coach,seats,Allseats from train_details");
                    ResultSet set = stmt.executeQuery();
                  
                   
                    
                     
                     while (set.next()) {
                        String r[] = { set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getString(9), set.getString(10),set.getString(11)};
                        login.dm.addRow(r);
                    }

                    

                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                
                //////////////////show data from sql in table2 "reserve"
               
                 try {
                     
                                   Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                        PreparedStatement stmt = con.prepareStatement("select  Reserve_number,name,from_where,to_where,price,Coach,seats,class,train_number,Date,leave_time,Ticket_availability from reserve");
                        ResultSet set = stmt.executeQuery();
                         
                     while (set.next()) {
                        String r[] = { set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getString(9), set.getString(10),set.getString(11),set.getString(12)};
                        login.dm2.addRow(r);
                    }

                    

                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                
            /////////////check to password if is true or false
                String pass = "123";
                String p = Password1.getText();
                if (pass.equals(p)) {
                   
                    login.setVisible(true);
                    setVisible(false);}
            
            else {
                    JOptionPane.showMessageDialog(null, "Error");
                }
           }
//            ///////////////////////////////////////////

            }
        }

        Password() {
            setLayout(new BorderLayout());

            egypttrain.setForeground(Color.white);
            egypttrain.setFont(font);

            Password.setForeground(Color.white);
            Password.setFont(new Font("SansSerif", Font.BOLD, 20));

            Home.setBackground(Color.white);
            Exit.setBackground(Color.white);

            pan1.add(egypttrain);
            pan1.setBackground(color);

            pan2.add(Password);
            pan2.add(Password1);
            pan2.add(Login);
            pan2.setBackground(color);
            pan2.setLayout(new FlowLayout(1, 30, 125));

            pan3.add(Home);
            pan3.add(Exit);
            pan3.setBackground(color);
            pan3.setLayout(new FlowLayout(1, 175, 150));

            add(pan1, BorderLayout.NORTH);
            add(pan2, BorderLayout.CENTER);
            add(pan3, BorderLayout.SOUTH);
            Home.addActionListener(new BListener());
            Login.addActionListener(new BListener());
            Exit.addActionListener(new BListener());

            //////////
            setTitle("Password");
            setSize(800, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //setVisible(true);

        }

    }
