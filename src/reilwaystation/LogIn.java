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
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author AmiraRabea
 */
public class LogIn extends JFrame{
        ImageIcon icon=new ImageIcon("image/Home.PNG");
        ImageIcon icon1=new ImageIcon("image/exit.PNG");
        ImageIcon icon2 = new ImageIcon("image/Search1.PNG");
        
        Color color = new Color(153, 0, 0);
        Font font1 = new Font("Arial", Font.BOLD + Font.ITALIC, 16);
        
        JTabbedPane tabbedpane=new JTabbedPane();
        
        JLabel egypttrain=new JLabel("Egypt Train");
        JLabel tripnumber =new JLabel("Trip Number");
        JLabel train =new JLabel("Train");
        JLabel coach =new JLabel("Coach");
        JLabel seat =new JLabel("Seats");
        JLabel from =new JLabel("From");
        JLabel to =new JLabel("To");
        JLabel leavetime =new JLabel("Leave Time");
        JLabel reatchtime =new JLabel("Reach Time");
        JLabel time =new JLabel("Speed");
        JLabel class1=new JLabel("Class");
        JLabel price=new JLabel("Price");
        JLabel Reservenumber= new JLabel("Reserve Number");
        
        JTextField train1=new JTextField(5);
        JTextField tripnumber1=new JTextField(5);
        JTextField price1=new JTextField(5);
        JTextField Reservenumber1= new JTextField(10);
        
        JSpinner coach1=new JSpinner();
        JSpinner seat1=new JSpinner();
        
        JComboBox leavetime1 = new JComboBox();
        JComboBox reatchtime1 = new JComboBox();
        
        
        JComboBox speed=new JComboBox();
        JComboBox from1=new JComboBox();
        JComboBox to1=new JComboBox();
        JComboBox class2=new JComboBox();
       
        JButton search =new JButton("Search",icon2);
        JButton save =new JButton("Save");
        JButton modify =new JButton("Modify");
        JButton delete =new JButton("Delete");
        JButton home =new JButton(icon);
        JButton exit =new JButton(icon1);
        JButton home2 = new JButton(icon);
        JButton exit2 = new JButton(icon1);
        JButton delete2 = new JButton("Delete");

        JPanel pan=new JPanel();
        JPanel pan1=new JPanel();
        JPanel pan2=new JPanel();
        JPanel pan3=new JPanel();
        JPanel pan4=new JPanel();
        JPanel pan5=new JPanel();
        JPanel pan6=new JPanel();
        JPanel pan2_2=new JPanel();
        JPanel pan3_2=new JPanel();
        JPanel pan3_3=new JPanel();
        JPanel pan3_4=new JPanel();
        
        DefaultTableModel dm = new DefaultTableModel();
        JTable table=new JTable(dm);
        
        DefaultTableModel dm2 = new DefaultTableModel();
        JTable table2=new JTable(dm2);
        
        /////////////////////////////action
        class mListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
             //////////select row in table back data to there feilds
             DefaultTableModel model = (DefaultTableModel) table.getModel();
                TableModel modal = table.getModel();
                
                int row = table.getSelectedRow();
                
                train1.setText(model.getValueAt(row, 1).toString());
                String s1=table.getValueAt(row, 2).toString();
                from1.setSelectedItem(s1);
                String s2=table.getValueAt(row, 3).toString();
                to1.setSelectedItem(s2);
                String s3=table.getValueAt(row,4).toString();
                leavetime1.setSelectedItem(s3);
                String s4=table.getValueAt(row, 5).toString();
                reatchtime1.setSelectedItem(s4);
                price1.setText(model.getValueAt(row, 6).toString());
                String s5=table.getValueAt(row, 7).toString();
                class2.setSelectedItem(s5);
                coach1.setValue(Integer.parseInt((String)table.getValueAt(row,8)));
                seat1.setValue(Integer.parseInt((String)table.getValueAt(row,9)));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

     }
        
        
        
        //////////////
        
           class BListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ////////////////////////home button backe frame"Search"
            if (e.getSource().equals(home)) {
                Search search = new Search();
                search.setVisible(true);
                setVisible(false);
                search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } 
            /////////exit button(Close programe)
            else if (e.getSource().equals(exit)) {
                System.exit(0);
                
                ///////////////home button backe frame"Search"
            } else if (e.getSource().equals(home2)) {
                Search search = new Search();
                search.setVisible(true);
                setVisible(false);
                search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } 
            /////////////exit button(Close programe)
            else if (e.getSource().equals(exit2)) {
                System.exit(0);
            }

            //////////////////////////  save button save data in sql
           if (e.getSource().equals(save)) {
                try {
                     
                    
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    
                    PreparedStatement s = conn  .prepareStatement("insert into train_details(train_number,from_where,to_where,leave_time,Reach_time,price,class,Coach,seats,Allseats) values(?,?,?,?,?,?,?,?,?,?)");
                    int allseats=Integer.parseInt(coach1.getValue().toString())*Integer.parseInt(seat1.getValue().toString());
                    
                    s.setString(1, train1.getText());
                    s.setString(2, from1.getSelectedItem().toString());
                    s.setString(3, to1.getSelectedItem().toString());
                    s.setString(4, leavetime1.getSelectedItem().toString());
                    s.setString(5, reatchtime1.getSelectedItem().toString());
                    s.setString(6, price1.getText());
                    s.setString(7, class2.getSelectedItem().toString());
                    s.setString(8, coach1.getValue().toString());
                    s.setString(9, seat1.getValue().toString());
                    s.setString(10, ""+allseats);
                    

                    s.executeUpdate();

                    JOptionPane.showMessageDialog(null, "save");

                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                //////////////
                /////////////show data which sava in sql  table1"train details
                
                try {
                    for (int i = dm.getRowCount()-1; i >0; i--) {
                   dm.removeRow(i);
               }
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    PreparedStatement stmt = con.prepareStatement("select trip_number,train_number,from_where,to_where,leave_time,Reach_time,price,class,Coach,seats,Allseats from train_details");
                    ResultSet set = stmt.executeQuery();
                 
                     while (set.next()) {
                        String r[] = { set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getString(9), set.getString(10),set.getString(11)};
                        dm.addRow(r);
                    }
                     
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                
           }
            
            //////////////////////
            /////////////delete button delete from table1"train details"
            if (e.getSource().equals(delete)){     
         try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
             PreparedStatement st = con.prepareStatement("delete from train_details where trip_number=?");
             
                          st.setString(1, tripnumber1.getText());
                          st.executeUpdate();
                          JOptionPane.showMessageDialog(null, "Delete");
         }
         catch (SQLException ex) {
             
           System.out.println("Error");}   
           ////////////////////
           /////////////// show data which save in sql in table1"train_details"
           try {
                     for (int i = dm.getRowCount()-1; i >0; i--) {
                   dm.removeRow(i);
               }
                   
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    PreparedStatement stmt = con.prepareStatement("select trip_number,train_number,from_where,to_where,leave_time,Reach_time,price,class,Coach,seats,Allseats from train_details");
                    ResultSet set = stmt.executeQuery();
                     
                     while (set.next()) {
                        String r[] = { set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getString(9), set.getString(10),set.getString(11)};
                        dm.addRow(r);
                    }

                    

                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
           /////////////////////
            }
         
         
                /////////////////search button  search in sql with trip number
                if (e.getSource().equals(search)) {
                    try {
                        
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                        PreparedStatement stmt = con.prepareStatement("select trip_number,train_number,from_where,to_where,leave_time,Reach_time,price,class,Coach,seats,Allseats from train_details where trip_number=" + tripnumber1.getText() + "");
                        ResultSet set = stmt.executeQuery();     
                    
                        if(set.first()){
                            train1.setText(set.getString("train_number"));
                            price1.setText(set.getString("price"));
                            class2.setSelectedItem(set.getString("class"));
                            
                            leavetime1.setSelectedItem(set.getString("leave_time"));
                            reatchtime1.setSelectedItem(set.getString("Reach_time"));
                            from1.setSelectedItem(set.getString("from_where"));
                            to1.setSelectedItem(set.getString("to_where"));
                          
                            coach1.setValue(set.getInt("Coach"));
                            seat1.setValue(set.getInt("seats"));
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Not found");

                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }

                }
                

            
            /////////////////modify button updata to data which save in sql
               if (e.getSource().equals(modify)) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    PreparedStatement s = conn.prepareStatement("update train_details set from_where=? , to_where=? , leave_time=? , Reach_time=? , price=? , class=? , Coach=? , seats=? , Allseats=? where train_number=?   ");
                    int allseats = Integer.parseInt(coach1.getValue().toString()) * Integer.parseInt(seat1.getValue().toString());

                    s.setString(1, from1.getSelectedItem().toString());
                    s.setString(2, to1.getSelectedItem().toString());
                    s.setString(3, leavetime1.getSelectedItem().toString());
                    s.setString(4, reatchtime1.getSelectedItem().toString());
                    s.setString(5, price1.getText());
                    s.setString(6, class2.getSelectedItem().toString());
                    s.setString(7, coach1.getValue().toString());
                    s.setString(8, seat1.getValue().toString());
                    s.setString(9, "" + allseats);
                    s.setString(10, train1.getText());

                    s.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Modify");

                } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
                
                
                try {
                    int rowCount = dm.getRowCount();
                for (int i = rowCount - 1; i > 0; i--) {
                    dm.removeRow(i);
                }
                
                   
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    PreparedStatement stmt = con.prepareStatement("select trip_number,train_number,from_where,to_where,leave_time,Reach_time,price,class,Coach,seats,Allseats from train_details");
                    ResultSet set = stmt.executeQuery();
                  
                   
                     
                     while (set.next()) {
                        String r[] = { set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getString(9), set.getString(10),set.getString(11)};
                        dm.addRow(r);
                    }

                    

                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
               /////////////////////////panel 2
               ///////// delete2 button delete from table1"reserve"
                if (e.getSource().equals(delete2)) {

                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                        PreparedStatement st = con.prepareStatement("delete from reserve where Reserve_number=?");

                        st.setString(1, Reservenumber1.getText());
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Delete");
                    } catch (SQLException ex) {

                     System.out.println("Error");

                }
////////////////////////show data which sava in sql in table2"reserve"
                try {
                     for (int i = dm2.getRowCount()-1; i >0; i--) {
                   dm2.removeRow(i);
               }

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    PreparedStatement stmt = con.prepareStatement("select  Reserve_number,name,from_where,to_where,price,Coach,seats,class,train_number,Date,leave_time,Ticket_availability from reserve");
                    ResultSet set = stmt.executeQuery();

                    while (set.next()) {
                        String r[] = {set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getString(9), set.getString(10), set.getString(11), set.getString(12)};
                        
                        dm2.addRow(r);
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }

            }
                  
        }
    }


    LogIn() {
        
        pan.setBackground(color);
        egypttrain.setForeground(Color.white);
        egypttrain.setFont(new Font("SansSerif", Font.BOLD, 40));
        pan.add(egypttrain);
        
        setLayout(new BorderLayout());

        ///////////   code of train data
       
      
        
        class2.addItem("first");
        class2.addItem("second");
        
        speed.addItem("160 km/h");
        speed.addItem("105 km/h");
        
        from1.addItem("Benha");
        from1.addItem("Qena");
        from1.addItem("Matruh");
        from1.addItem("Damanhour");
        from1.addItem("Zagazig");
        from1.addItem("Damietta");
        from1.addItem("Ismailia");
        from1.addItem("Mansoura");
        from1.addItem("Port Said");
        from1.addItem("Suez");
        from1.addItem("Tanta");
        from1.addItem("Minya");
        from1.addItem("Assiut");
        from1.addItem("Giza");
        from1.addItem("Luxor");
        from1.addItem("Aswan");
        from1.addItem("Cairo");
        from1.addItem("Beni Suef ");
        from1.addItem("Sohag");
        from1.addItem("Alexandria");

        to1.addItem("Alexandria");
        to1.addItem("Sohag");
        to1.addItem("Beni Suef ");
        to1.addItem("Cairo");
        to1.addItem("Aswan");
        to1.addItem("Luxor");
        to1.addItem("Giza");
        to1.addItem("Assiut");
        to1.addItem("Minya");
        to1.addItem("Benha");
        to1.addItem("Qena");
        to1.addItem("Matruh");
        to1.addItem("Damanhour");
        to1.addItem("Zagazig");
        to1.addItem("Damietta");
        to1.addItem("Ismailia");
        to1.addItem("Mansoura");
        to1.addItem("Port Said");
        to1.addItem("Suez");
        to1.addItem("Tanta");

        leavetime1.addItem("07:00");
        leavetime1.addItem("07:30");
        leavetime1.addItem("08:00");
        leavetime1.addItem("08:30");
        leavetime1.addItem("09:00");
        leavetime1.addItem("09:30");
        leavetime1.addItem("10:00");
        leavetime1.addItem("10:30");
        leavetime1.addItem("11:00");
        leavetime1.addItem("11:30");
        leavetime1.addItem("12:00");
        leavetime1.addItem("12:30");
        leavetime1.addItem("01:00");
        leavetime1.addItem("01:30");
        leavetime1.addItem("02:00");
        leavetime1.addItem("02:30");
        leavetime1.addItem("03:00");
        leavetime1.addItem("03:30");
        leavetime1.addItem("04:00");
        leavetime1.addItem("04:30");

        reatchtime1.addItem("12:00");
        reatchtime1.addItem("12:30");
        reatchtime1.addItem("01:00");
        reatchtime1.addItem("01:30");
        reatchtime1.addItem("02:00");
        reatchtime1.addItem("02:30");
        reatchtime1.addItem("03:00");
        reatchtime1.addItem("03:30");
        reatchtime1.addItem("04:00");
        reatchtime1.addItem("04:30");
        reatchtime1.addItem("05:00");
        reatchtime1.addItem("06:30");
        reatchtime1.addItem("07:00");
        reatchtime1.addItem("07:30");
        reatchtime1.addItem("08:00");
        reatchtime1.addItem("09:30");
        reatchtime1.addItem("11:00");
        reatchtime1.addItem("11:30");
        
        
        
        dm.addColumn("Trip Number");
        dm.addColumn("Train");
        dm.addColumn("From");
        dm.addColumn("To");
        dm.addColumn("Leave");
        dm.addColumn("Reach");
        dm.addColumn("Price");
        dm.addColumn("Class");
        dm.addColumn("Coach");
        dm.addColumn("Seats");
        dm.addColumn("AllSeats");
         
        dm2.addColumn("reserved number");
        dm2.addColumn("name");
        dm2.addColumn("from ");
        dm2.addColumn("to ");
        dm2.addColumn("Price");
        dm2.addColumn("coach");
        dm2.addColumn("Seat");
        dm2.addColumn("class");
        dm2.addColumn("Train ");
        dm2.addColumn("date");
        dm2.addColumn("leave");
        dm2.addColumn("Ticket_availability ");
        
        
        

        search.setHorizontalTextPosition(SwingConstants.LEFT); 
        search.setBackground(Color.white);
        
        home.setBackground(Color.white);

        exit.setBackground(Color.white);
        
        
        
        pan1.setLayout(new FlowLayout(1, 5, 20));
        //pan1.setBackground(Color.white);
        //trainnumber.setForeground(Color.white);
        tripnumber.setFont(font1);
        pan1.add(tripnumber);
        pan1.add(tripnumber1);
        pan1.add(search);
        pan1.add(delete);
        
        pan2.setLayout(new GridLayout(6, 4,20,30));
        pan2.add(train);
        pan2.add(train1);
        pan2.add(class1);
        pan2.add(class2);
        pan2.add(coach);
        pan2.add(coach1);
        pan2.add(seat);
        pan2.add(seat1);
        pan2.add(from);
        pan2.add(from1);
        pan2.add(to);
        pan2.add(to1);
        pan2.add(leavetime);
        pan2.add(leavetime1);
        pan2.add(reatchtime);
        pan2.add(reatchtime1);
        pan2.add(time);
        pan2.add(speed);
        pan2.add(price);
        pan2.add(price1);
        pan2.setSize(200, 100);
        pan2.add(save);
        pan2.add(modify);
       
        
        
        pan3.add(table);
        
        pan4.setLayout(new FlowLayout(1, 100, 30));
        pan4.add(home);
        pan4.add(exit);
        
        pan5.setLayout(new BorderLayout(5,5));
        pan5.add(pan2,BorderLayout.WEST);
        pan5.add(pan3);
        
        pan6.setLayout(new BorderLayout());
        pan6.add(pan1,BorderLayout.NORTH);
        pan6.add(pan4,BorderLayout.SOUTH);
        pan6.add(pan5,BorderLayout.CENTER);
        
        
        /////////////////////////////////  code of Passanger Reservation
        
        home2.setBackground(Color.white);
        exit2.setBackground(Color.white);
        
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();
        model1.addRow(new Object[]{"Trip Num","Train", "From", "To","Leave", "Reach","Price","Class","Coach","Seats","AllSeats"});
        table.setSelectionBackground(color);
        table.setSelectionForeground(Color.WHITE);
        
        
        DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
        model2.addRow(new Object[]{"Reserve Num","Name", "From", "To","Price", "Coach","Seat","Class","Train","Date","Leave","Availability"});
        table2.setSelectionBackground(color);
        table2.setSelectionForeground(Color.WHITE);
        
        pan2_2.setLayout(new BorderLayout(50,5));
        
        pan3_2.setLayout(new FlowLayout(1, 10, 50));
        pan3_2.add(Reservenumber);
        pan3_2.add(Reservenumber1); 
        pan3_2.add(delete2);
        
        pan3_3.add(table2);
        
        pan3_4.setLayout(new FlowLayout(1, 200, 60));
        pan3_4.add(home2);
        pan3_4.add(exit2);
        
        pan2_2.add(pan3_2,BorderLayout.NORTH);
        pan2_2.add(pan3_3,BorderLayout.CENTER);
        pan2_2.add(pan3_4,BorderLayout.SOUTH);
        
        ////////////////////
        
        tabbedpane.add(pan6,"Train Data");
        tabbedpane.add(pan2_2,"Passenger Reservation");
        add(pan,BorderLayout.NORTH);
        add(tabbedpane);
        home.addActionListener(new BListener());        
        exit.addActionListener(new BListener());
        home2.addActionListener(new BListener());        
        exit2.addActionListener(new BListener());
        delete2.addActionListener(new BListener());
        save.addActionListener(new BListener());
        delete.addActionListener(new BListener());
        modify.addActionListener(new BListener());
        search.addActionListener(new BListener());
        
        table.addMouseListener(new mListener());

       
        ///////////frame
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        

        
        
       
    } 
    String r[];
    public void result_search(){
        
                    
                        dm2.addRow(r);
        
             
         } 
    
}


