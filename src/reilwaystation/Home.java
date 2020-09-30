/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reilwaystation;

import java.awt.BorderLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author AmiraRabea
 */
public class Home extends JFrame{
    
        Color color = new Color(153, 0, 0);
        
        ImageIcon icon=new ImageIcon("image/train.JPG");
        
        JButton photo = new JButton(icon);
        JLabel egypttrain=new JLabel("Egypt Train");
        
        JPanel pan1=new JPanel();
        JPanel pan2=new JPanel();

        
        
         /////////////////////// go to  frame"search" 
         

class BListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(photo)) {
                Search search = new Search();
                search.setVisible(true);
                setVisible(false);
                search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}}
}

    Home(){
        setLayout(new BorderLayout());
        //////lable photo
        add(photo);
        //////label egypttrain
        egypttrain.setForeground(Color.white);
        egypttrain.setFont(new Font("SansSerif", Font.BOLD, 40));
        /////panel 1
        pan1.setBackground(color);
        pan1.add(egypttrain);
        add(pan1,BorderLayout.NORTH);
        
        photo.addActionListener(new BListener());
        ///////////frame
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
         
    }

       
}
