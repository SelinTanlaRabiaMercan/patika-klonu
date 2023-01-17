package com.patika.View;

import com.patika.Helper.Helper;

import javax.swing.*;

public class StudentGUI extends JFrame {
    private JPanel panel1;
    private JTabbedPane tblogrenci;
    private JButton button1;

    StudentGUI(){
        add(panel1);
        setSize(700,500);


        int x= Helper.screenCenter("x",(getSize()));
        int y=Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
