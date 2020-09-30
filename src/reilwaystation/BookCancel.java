/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reilwaystation;


import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author AmiraRabea
 */
public class BookCancel extends JFrame{
            
        ImageIcon icon=new ImageIcon("image/Home.PNG");
        ImageIcon icon1=new ImageIcon("image/exit.PNG");
        ImageIcon icon2 = new ImageIcon("image/Search1.PNG");
        
        Color color= new Color(153,0,0);
        Font font1 = new Font("SansSerif", Font.BOLD, 30);
        Font font2 = new Font("Arial", Font.BOLD , 16);
        
        
        JLabel name= new JLabel("Name");
        JLabel date= new JLabel("Date");
        JLabel leavetime= new JLabel("Leave Time");
        JLabel class1= new JLabel("Class");
        JLabel price= new JLabel("Price");
        JLabel from= new JLabel("From");
        JLabel to= new JLabel("To");
        JLabel train= new JLabel("Train");
        JLabel coach= new JLabel("Coach");
        JLabel seat= new JLabel("Seat");
        JLabel reservenumber= new JLabel("Reserve Num");
        JLabel reservenumber1= new JLabel();
        JLabel Reservenumber= new JLabel("Reserve Number");
        JLabel egypttrain= new JLabel(" Egypt Train ");
        JLabel Reserve= new JLabel(" Reserve ");
        JLabel Cancel= new JLabel(" Cancel ");
        JLabel Ticket_availability1= new JLabel("NULL");
        JLabel Ticket_availability= new JLabel("Availability");
       
        JTextField name1= new JTextField(5);
        JTextField price1= new JTextField(5);
        JTextField Reservenumber1= new JTextField(10);
        
        JComboBox class2 = new JComboBox();
        JComboBox train1 = new JComboBox();
        JComboBox leavetime1 = new JComboBox();
        JComboBox from1 = new JComboBox();
        JComboBox to1 = new JComboBox();
        JSpinner coach1 = new JSpinner();
        JSpinner seat1 = new JSpinner();
        
        JDateChooser date1 = new com.toedter.calendar.JDateChooser("yyyy/MM/dd","####/##/##",'_');

        JButton reserve= new JButton("Reserve");
        JButton home= new JButton("Home",icon);
        JButton search= new JButton("Search",icon2);
        JButton cancel= new JButton("Cancel");
        JButton exit= new JButton("Exit",icon1);
        
        Date d3 = new Date();
        SpinnerDateModel sm3 =new SpinnerDateModel(d3, null, null, Calendar.HOUR_OF_DAY);
        

        JPanel pan1= new JPanel();
        JPanel pan2= new JPanel();
        JPanel pan3= new JPanel();
        JPanel pan4= new JPanel();
        JPanel pan5= new JPanel();
        JPanel pan6= new JPanel();
        JPanel pan7= new JPanel();
        JPanel pan8= new JPanel();
        JPanel pan9= new JPanel();
        JPanel pan10= new JPanel();
        JPanel pan11= new JPanel();

    BookCancel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
       class BListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ////////////////////home button backe frame"Search" 
            if (e.getSource().equals(home)) {
                Search search = new Search();
                search.setVisible(true);
                setVisible(false);
                search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } 
            ///////////////exit button (Close programe)
            else if (e.getSource().equals(exit)) {
                System.exit(0);
            }  
            
            //////////////////////reserve button save data in passenger Reservation
            else if (e.getSource().equals(reserve)) {
                     
         try {
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
             PreparedStatement s = conn.prepareStatement("insert  into reserve(name,from_where,to_where,price,Coach,seats,class,train_number,leave_time,Ticket_availability,date) values(?,?,?,?,?,?,?,?,?,?,?)");
             
                    s.setString(1, name1.getText());
                    s.setString(2, from1.getSelectedItem().toString());
                    s.setString(3, to1.getSelectedItem().toString());
                    s.setString(4, price1.getText());
                    s.setString(5, coach1.getValue().toString());
                    s.setString(6, seat1.getValue().toString());
                    s.setString(7, class2.getSelectedItem().toString());
                    s.setString(8, train1.getSelectedItem().toString());
                    s.setString(9, leavetime1.getSelectedItem().toString());
                    s.setString(10, "NULL");
                    s.setString(11, ((JTextField)date1.getDateEditor().getUiComponent()).getText());
                    s.executeUpdate();
                    } catch (SQLException ex) {
              System.out.println(ex.toString());
                    }
                    
                         ////////////  show  Reserve_number for ticket
            try {
                     JOptionPane.showMessageDialog(null, "reserve");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                        PreparedStatement stmt = con.prepareStatement("select  Reserve_number from reserve");
                        ResultSet set = stmt.executeQuery();
                        
                if (set.last()) {

                    reservenumber1.setText(set.getString("Reserve_number"));

                }}catch (SQLException ex) {
                        System.out.println(ex.toString());
                    }
            /////////////////
                        
                    
                   }
            //////////////cancel button chanage  Ticket_availability=NOt Null
             if (e.getSource().equals(cancel)){  
                  try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
            PreparedStatement s2 = con.prepareStatement("Update reserve set Ticket_availability = 'NOT NULL' where Reserve_number = '"+Reservenumber1.getText()+"'");
            s2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cancel");

        } catch (SQLException ex) {
            System.out.println("Error");
        }
                    }
            //////////////////  searsh button backe data with Reserve_number
            if (e.getSource().equals(search)) {

                try {

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwaydata", "root", "root");
                    PreparedStatement stmt = con.prepareStatement("select Reserve_number,name,from_where,to_where,price,Coach,seats,class,train_number,Date,leave_time,Ticket_availability from reserve where Reserve_number=" + Reservenumber1.getText() + "");
                    ResultSet set = stmt.executeQuery();

                    if (set.first()) {
                        name1.setText(set.getString("name"));
                        price1.setText(set.getString("price"));
                        train1.setSelectedItem(set.getString("train_number"));
                        from1.setSelectedItem(set.getString("from_where"));
                        to1.setSelectedItem(set.getString("to_where"));
                        date1.setDate(set.getDate("Date"));
                        leavetime1.setSelectedItem(set.getString("leave_time"));
                        coach1.setValue(set.getInt("Coach"));
                        seat1.setValue(set.getInt("seats"));
                        class2.setSelectedItem(set.getString("class"));
                        reservenumber1.setText(set.getString("Reserve_number"));
                        Ticket_availability1.setText(set.getString("Ticket_availability"));
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Not found");
                    }

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }

            }

        }
    }
    
    BookCancel( ArrayList<Integer> numbers){
        setLayout(new BorderLayout(5,5));
        
        date1.setDate(d3);
        
        class2.addItem("first");
        class2.addItem("second");
        
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
        leavetime1.addItem("12:30");
        leavetime1.addItem("01:00");
        leavetime1.addItem("01:30");
        leavetime1.addItem("02:00");
        leavetime1.addItem("02:30");
        leavetime1.addItem("03:00");
        leavetime1.addItem("03:30");
        leavetime1.addItem("04:00");
        leavetime1.addItem("04:30");
        leavetime1.addItem("05:00");
        leavetime1.addItem("06:30");
        leavetime1.addItem("07:00");
        leavetime1.addItem("09:30");
        


        for (int i = 0; i < numbers.size(); i++) {
            train1.addItem(numbers.get(i).toString());
        }
        
        
        search.setHorizontalTextPosition(SwingConstants.LEFT); 
        search.setBackground(Color.white);
        
        Reserve.setFont(font1);
        Cancel.setFont(font1);
        Reservenumber.setFont(font2);
        
        egypttrain.setForeground(Color.WHITE);
        egypttrain.setFont(font1);
        pan1.add(egypttrain);
        pan1.setBackground(color);
        
        pan2.setLayout(new GridLayout(1, 2,10,10));
        //pan2.setBackground(color);
        pan2.add(pan4);
        pan2.add(pan8);
        
        home.setBackground(Color.white);
        exit.setBackground(Color.white);
        //pan3.setBackground(color);
        pan3.setLayout(new FlowLayout(1,70,40));
        pan3.add(home);
        pan3.add(exit);
        
        pan4.setLayout(new BorderLayout(10,15));
        pan4.add(pan5,BorderLayout.NORTH);
        pan4.add(pan6,BorderLayout.CENTER);
        pan4.add(pan7,BorderLayout.SOUTH);
        //pan4.setBackground(color);
        
        pan5.add(Reserve);
        pan5.setLayout(new FlowLayout(1,10,0));
        Reserve.setForeground(Color.black);
        pan5.setBackground(Color.white);
        pan5.setLayout(new FlowLayout(1));
        
        
        pan6.setLayout(new GridLayout(6, 4,5,20));
        pan6.setBackground(Color.white);
        pan6.add(name);
        pan6.add(name1);
        pan6.add(date);
        pan6.add(date1);
        pan6.add(from);
        pan6.add(from1);
        pan6.add(to);
        pan6.add(to1);
        pan6.add(train);
        pan6.add(train1);
        pan6.add(class1);
        pan6.add(class2);
        pan6.add(leavetime);
        pan6.add(leavetime1);
        pan6.add(seat);
        pan6.add(seat1);
        pan6.add(coach);
        pan6.add(coach1);
        pan6.add(price);
        pan6.add(price1);
        pan6.add(reservenumber);
        pan6.add(reservenumber1);
        pan6.add(Ticket_availability);
        pan6.add(Ticket_availability1);
        
        pan7.add(reserve);
        //pan7.setBackground(color);
        
        
        pan8.setLayout(new BorderLayout(10,15));
        pan8.add(pan9,BorderLayout.NORTH);
        pan8.add(pan10);
        pan8.add(pan11,BorderLayout.SOUTH);
        //pan8.setBackground(color);
        
        pan9.add(Cancel);
        Cancel.setForeground(Color.white);
        pan9.setBackground(Color.black);
        pan9.setLayout(new FlowLayout());
        
        pan10.add(Reservenumber);
        pan10.add(Reservenumber1);
        pan10.setLayout(new FlowLayout(0,30,40));
        pan10.add(search);
        pan10.setBackground(Color.black);
        Reservenumber.setForeground(Color.white);
        
        pan11.add(cancel);
        //pan11.setBackground(color);
         
        add(pan1,BorderLayout.NORTH);
        add(pan2,BorderLayout.CENTER);
        add(pan3,BorderLayout.SOUTH);
        home.addActionListener(new BListener());
        exit.addActionListener(new BListener());
        reserve.addActionListener(new BListener());
        cancel.addActionListener(new BListener());
        search.addActionListener(new BListener());

        
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        
    }}