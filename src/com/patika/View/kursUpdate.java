package com.patika.View;

import com.patika.Helper.*;
import com.patika.Model.*;

import javax.swing.*;

//1-extends
public class kursUpdate extends JFrame {
    private JPanel panel1_kurs;
    private JTextField txt_kurs_adi_update;
    private JButton btn_update_kurs;
    private JTextField txt_kurs_id;
    private kurs kurs1; //3

    //2- constructor
    public kursUpdate(kurs kurs2) {
        //4
        this.kurs1 = kurs2;
        add(panel1_kurs);
        setSize(300, 150);

        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(configSabitler.PROJE_TITLE);
        setVisible(true);

        txt_kurs_adi_update.setText(kurs1.getName());

        btn_update_kurs.addActionListener(e -> {
            if (Helper.txtBosmu(txt_kurs_adi_update)) {
                Helper.mesajPenceresi("Boş alan bıraktınız", "Hata", "Tamam");
            } else {
                if (kurs.update(txt_kurs_adi_update.getText(),kurs1.getId())){
                    Helper.mesajPenceresi("Liste Güncellendi", "SUCCESS", "Tamam");
                }
                dispose();//pencereyi kapat
            }
        });
    }


}
