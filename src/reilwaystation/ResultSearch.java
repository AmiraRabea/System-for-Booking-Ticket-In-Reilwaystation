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
public class ResultSearch extends JFrame{
   
     ImageIcon icon=new ImageIcon("image/Home.PNG");
     ImageIcon icon1=new ImageIcon("image/exit.PNG");
     Color color=new Color(153,0,0);
     Font font=new Font("SansSerif", Font.BOLD, 40);
     Font font1=new Font("SansSerif", Font.BOLD, 20);
     
     DefaultTableModel dm = new DefaultTableModel();
        JTable table=new JTable();
        
     

     JButton home=new JButton(icon);
     JButton exit=new JButton(icon1);
     
     JLabel egyptTrains=new JLabel("Egypt Trains");
     JLabel from=new JLabel("From:");
     JLabel to=new JLabel("   To:");

     JLabel from1=new JLabel();
     JLabel to1=new JLabel();
     
     JPanel panel1=new JPanel();
     JPanel panel2=new JPanel();
     JPanel panel3=new JPanel();
     
      class BListener implements ActionListener{

        private ArrayList<Integer> numbers;
        
    public void actionPerformed(ActionEvent e){
        ////////////// /book button go to frame"bookcancel"
      if(e.getSource().equals(home)){
            Search search=new Search();
            search.setVisible(true);
            setVisible(false);
                search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } 
    ////////////////exit button (Close programe)
    else if (e.getSource().equals(exit)) {
                System.exit(0);
            }

        }
    }




    public ResultSearch()
    {
        
        
        panel1.setLayout(new FlowLayout(1, 60, 15));
        panel2.setLayout(new FlowLayout(1, 120, 90));
        panel3.setLayout(new FlowLayout(1, 10, 100));
        
        
        from1.setLayout(new BorderLayout());
       
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();
        model1.addRow(new Object[]{"Train","Leave", "Reach","Price","Class"});
        table.setSelectionBackground(color);
        table.setSelectionForeground(Color.WHITE);
        
        home.setBackground(Color.white);
        exit.setBackground(Color.white);
        
        
        add(panel1,BorderLayout.NORTH);
        add(panel2,BorderLayout.SOUTH);
        add(panel3,BorderLayout.CENTER);
        
        panel1.add(egyptTrains,BorderLayout.WEST);
        
        panel1.setBackground(color);
        egyptTrains.setForeground(Color.white);
        egyptTrains.setFont(font);
        
        from1.setForeground(Color.red);
        from1.setFont(font1);
        
        to1.setForeground(Color.red);
        to1.setFont(font1);
        
        
        dm.addColumn("Train");
        dm.addColumn("Leave");
        dm.addColumn("Reach");
        dm.addColumn("Price");
        dm.addColumn("Class");
        
        
//        
        
        
        panel2.add(home);
        panel2.add(exit);
        
        panel3.add(from);
        panel3.add(from1);
        panel3.add(to);
        panel3.add(to1);
        panel3.add(table);
       
        
        
        home.addActionListener(new BListener());
        exit.addActionListener(new BListener());

        
       
       
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
       
    }
     
String r[];
    public void result_search(){
        
                    
                        dm.addRow(r);
        
             
         } 
    
}
