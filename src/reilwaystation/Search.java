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
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AmiraRabea
 */
public class Search extends JFrame{
    
    ArrayList<Integer> numbers=new ArrayList<>();
                      
    
        ImageIcon icon = new ImageIcon("image/login.PNG");
        ImageIcon icon1 = new ImageIcon("image/exit.PNG");
        ImageIcon icon2 = new ImageIcon("image/Search.PNG");
        
        Font f = new Font("Arial", Font.CENTER_BASELINE, 20);
        Font d = new Font("Arial", Font.CENTER_BASELINE, 20);        
        Color color = new Color(153, 0, 0);
        
        JLabel leavestation = new JLabel("Leave Station");
        JLabel reachstation = new JLabel("Reach Station");
        JLabel egypttrain = new JLabel("Egypt Train");

        JComboBox leavestation1 = new JComboBox();
        JComboBox reachstation1 = new JComboBox();

        JButton search = new JButton("Search",icon2);
        JButton book = new JButton("Book/Cancel");
        JButton exit = new JButton(icon1);
        JButton login = new JButton("Log in",icon);
        
        JPanel pan = new JPanel();
        JPanel pan1 = new JPanel();
        JPanel pan2 = new JPanel();
        JPanel pan3 = new JPanel();
        JPanel pan4 = new JPanel();
        
        
        
        
        class BListener implements ActionListener{
        
    public void actionPerformed(ActionEvent e){
        
       // /book button go to frame"bookcancel"
       //////show train number which save in sql
     if(e.getSource().equals(book)){ 
                 
                  try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                        PreparedStatement stmt = con.prepareStatement("select  train_number from train_details");
                        ResultSet set = stmt.executeQuery();
                     set.first();
                while (!set.isAfterLast()) {
                    numbers.add(set.getInt(1));
                    set.next();
                    }
                  BookCancel bookcancel= new BookCancel(numbers);
                 bookcancel.setVisible(true);
                 setVisible(false);
         
                  }catch (SQLException ex) {
                        System.out.println(ex.toString());
                    }
                
           }
     /////////lonin  button take to frame"Password"
      else if(e.getSource().equals(login)){
            Password password=new Password();
                 password.setVisible(true);
                 setVisible(false);
                 
           }
      
      ////////////search button search in sql for train which go from city to city
      else if(e.getSource().equals(search)){
         ResultSearch resultsearch = new ResultSearch();
         
                   try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    PreparedStatement stmt = con.prepareStatement("select * from train_details where from_where=? and to_where=?");
                      
                    stmt.setString(1, leavestation1.getSelectedItem().toString());
                    stmt.setString(2, reachstation1.getSelectedItem().toString());
                    ResultSet set = stmt.executeQuery();
                    DefaultTableModel dm = new DefaultTableModel();
                    
                    
                    if(set.first()){
                    
                    dm.addColumn("Train");
                    dm.addColumn("Leave");
                    dm.addColumn("Reach"); 
                    dm.addColumn("Price");
                    dm.addColumn("Class");
                    
                    
                     while (set.next()) {
                        String r[] = {  set.getString(2), set.getString(5), set.getString(6), set.getString(7), set.getString(8)};
                        dm.addRow(r);
                    }
                    
                     resultsearch.table.setModel(dm);
                    resultsearch.setVisible(true);
                           setVisible(false);
                           resultsearch.from1.setText(leavestation1.getSelectedItem().toString());
                           resultsearch.to1.setText(reachstation1.getSelectedItem().toString());
        
                       } else {
                           JOptionPane.showMessageDialog(null, "No Train");
                       }
                    } catch (SQLException ex) {
                System.out.println(ex.toString());
         }
                  

     }
////////////////////////////  exit button (Close programe)
            else if (e.getSource().equals(exit)) {
                System.exit(0);
            }

        }
    }




    public Search() { 
       
        setLayout(new BorderLayout());

        
        /////////////
        
        search.setHorizontalTextPosition(SwingConstants.LEFT); 
        search.setBackground(Color.white);
        
        login.setHorizontalTextPosition(SwingConstants.CENTER); 
        login.setVerticalTextPosition(SwingConstants.BOTTOM); 
        
        leavestation1.addItem("Benha");
        leavestation1.addItem("Qena");
        leavestation1.addItem("Matruh");
        leavestation1.addItem("Damanhour");
        leavestation1.addItem("Zagazig");
        leavestation1.addItem("Damietta");
        leavestation1.addItem("Ismailia");
        leavestation1.addItem("Mansoura");
        leavestation1.addItem("Port Said");
        leavestation1.addItem("Suez");
        leavestation1.addItem("Tanta");
        leavestation1.addItem("Minya");
        leavestation1.addItem("Assiut");
        leavestation1.addItem("Giza");
        leavestation1.addItem("Luxor");
        leavestation1.addItem("Aswan");
        leavestation1.addItem("Cairo");
        leavestation1.addItem("Beni Suef ");
        leavestation1.addItem("Sohag");
        leavestation1.addItem("Alexandria");

        reachstation1.addItem("Alexandria");
        reachstation1.addItem("Tanta");
        reachstation1.addItem("Cairo");
        reachstation1.addItem("Sohag");
        reachstation1.addItem("Beni Suef ");
        reachstation1.addItem("Aswan");
        reachstation1.addItem("Luxor");
        reachstation1.addItem("Giza");
        reachstation1.addItem("Assiut");
        reachstation1.addItem("Minya");
        reachstation1.addItem("Benha");
        reachstation1.addItem("Qena");
        reachstation1.addItem("Matruh");
        reachstation1.addItem("Damanhour");
        reachstation1.addItem("Zagazig");
        reachstation1.addItem("Damietta");
        reachstation1.addItem("Ismailia");
        reachstation1.addItem("Mansoura");
        reachstation1.addItem("Port Said");
        reachstation1.addItem("Suez");
        
        
        pan1.setLayout(new FlowLayout(1, 200, 30));
        pan1.add(leavestation);
        pan1.add(leavestation1);
        pan1.add(reachstation);
        pan1.add(reachstation1);
        
        pan1.add(search);

        pan2.setLayout(new FlowLayout(2, 20, 80));
        pan2.add(book);
         
        pan3.add(login);
        login.setBackground(Color.white);
        
        pan4.add(exit);
        exit.setBackground(Color.white);
        
        
        egypttrain.setForeground(Color.white);
        egypttrain.setFont(new Font("SansSerif", Font.BOLD, 40));
        pan.setBackground(color);
        pan.add(egypttrain);
        
        add(pan, BorderLayout.NORTH);
        add(pan1,BorderLayout.CENTER);
        add(pan2,BorderLayout.SOUTH);
        add(pan3,BorderLayout.EAST);
        add(pan4,BorderLayout.WEST);

       
        exit.setFont(f);
        search.setFont(f);
        login.setFont(f);
        book.setFont(f);
        leavestation.setFont(d);
        reachstation.setFont(d);
        
        book.addActionListener(new BListener());
        search.addActionListener(new BListener());
        login.addActionListener(new BListener());
        exit.addActionListener(new BListener());



        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Search");
    }

    
}
