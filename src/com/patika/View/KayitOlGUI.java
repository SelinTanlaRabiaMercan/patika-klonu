package com.patika.View;

import com.patika.Helper.Helper;
import com.patika.Model.usertable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KayitOlGUI extends JFrame {
    private JPanel kayitOlpanel;
    private JButton kayıtOlButton;
    private JTextField txt_kayitOl_isim;
    private JTextField txt_kayitOl_kullaniciAdi;
    private JTextField txt_kayitOl_sifre;
    private JButton btn_kayitOl_giris;

    KayitOlGUI() {
        add(kayitOlpanel);
        setSize(400, 300);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        // Helper.setLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setVisible(true);
        kayıtOlButton.addActionListener(e -> {
            if (Helper.txtBosmu(txt_kayitOl_isim) || Helper.txtBosmu(txt_kayitOl_sifre) || Helper.txtBosmu(txt_kayitOl_kullaniciAdi)) {
                Helper.mesajPenceresi("Boş Alan Bırakmayın", "HATA", "Tamam");
            } else {
                if (usertable.add(txt_kayitOl_isim.getText(), txt_kayitOl_kullaniciAdi.getText(), txt_kayitOl_sifre.getText(), "student")) {
                    Helper.mesajPenceresi("Başarılı", "Başarılı", "Tamam");
                    txt_kayitOl_isim.setText(null);
                    txt_kayitOl_sifre.setText(null);
                    txt_kayitOl_kullaniciAdi.setText(null);
                    LoginGUI loginGUI = new LoginGUI();
                    dispose();
                }
            }
        });
        btn_kayitOl_giris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose();
            }
        });
    }
}
