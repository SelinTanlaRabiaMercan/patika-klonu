package com.patika.View;

import com.patika.Helper.*;
import com.patika.Model.educator;
import com.patika.Model.operator;
import com.patika.Model.usertable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel panel1;
    private JPanel panelalt;
    private JPanel paneltop;
    private JTextField txt_giris_kullanici_adi;

    private JButton btnGirisYap;
    private JTextField textField1;
    private JButton btn_kayit_ol;
    private JButton btn_ogr_hiz;
    private JButton btn_operator_hiz;

    public LoginGUI() {
        add(panel1);
        Helper.setLayout();
        setSize(400, 500);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(configSabitler.PROJE_TITLE);
        setResizable(false);//boyutu değiştirilemesin
        setVisible(true);

        btnGirisYap.addActionListener(e -> {
            if (Helper.txtBosmu(textField1) || Helper.txtBosmu(txt_giris_kullanici_adi)) {
                Helper.mesajPenceresi("Boş alan bırakmayın", "HATA", "Tamam");
            } else {
                usertable u =  usertable.getfecgirisicin(txt_giris_kullanici_adi.getText(), textField1.getText());
                if (u == null) {
                    Helper.mesajPenceresi("Kullanıcı Bulunamadı", "HATA", "Tamam");
                    textField1.setText(null);
                    txt_giris_kullanici_adi.setText(null);
                    System.out.println("HATA");
                } else {
                    System.out.println(txt_giris_kullanici_adi.getText());
                    System.out.println(textField1.getText());
                    switch (u.getKullanici_turu()) {
                        case "operator":
                            operatorGUI operatorGUI = new operatorGUI((operator) u);
                            break;
                        case "educator":
                            educatorGUI educatorGUI = new educatorGUI((educator) u);
                            break;
                        case "student":
                            StudentGUI studentGUI = new StudentGUI();
                            break;
                    }
                    dispose();
                }
            }
        });
        btn_kayit_ol.addActionListener(e -> {
           if( Helper.mesajPenceresicokluCevap("Sadece Öğrenciler Kayıt Oluşturabilir\nÖğrencimisiniz","UYARI")){
               KayitOlGUI kayitOlGUI=new KayitOlGUI();
               dispose();
           }

        });
        btn_ogr_hiz.addActionListener(e -> {
            txt_giris_kullanici_adi.setText("1");
            textField1.setText("1");
        });
        btn_operator_hiz.addActionListener(e -> {
            txt_giris_kullanici_adi.setText("SelinTANLA");
            textField1.setText("123");
        });
    }




}
