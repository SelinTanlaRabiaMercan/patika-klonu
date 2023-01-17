package com.patika.View;

import com.patika.Helper.Helper;
import com.patika.Helper.Item;
import com.patika.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class educatorGUI extends JFrame {
    private JPanel panel1;
    private JLabel lbl_educator;
    private JPanel panel_tab;
    private JTabbedPane tabbedPane1;
    private JTable tbl_egitim_list;
    private JLabel lbl_isim;
    private JTable tbl_icerik_list;
    private JComboBox cmb_patika_adi;
    private JTextField txt_baslik;
    private JTextField txt_aciklama;
    private JTextField txt_youtube_Link;
    private JRadioButton rdbtn_quiz;
    private JButton btn_quiz_olustur;
    private JButton btn_devam;
    private JLabel lbl_quiz;
    private JTextArea txt_soru;
    private JTextField txt_A;
    private JTextField txt_B;
    private JTextField txt_C;
    private JTextField txt_D;
    private JPanel pnl_quiz;
    private JPanel pnl_icerik_listele;
    private JPanel pnl_icerik_ekle_sil;
    private JPanel pnl_egitimler;
    private JPanel pnl_quiz_disabled;
    private JTextField txt_quiz_Adi;
    private JLabel lbl_quiz_Adi;
    private JLabel lbl_ders_adi;
    private JButton btnkaydet;
    private JComboBox cmb_cevap;
    private JButton btn_yeni;
    private JLabel lbl_soru;
    private JButton btn_cevap_kontrol;
    private JLabel lbl_egitim_not;
    private JComboBox cmb_ders_adi;
    private JButton btn_cks;
    private JComboBox cmb_quiz_adi;
    private JComboBox cmb_ders_ad;
    private JLabel lbl;
    private JLabel lblders;
    private Object[] row_icerik_list;
    private DefaultTableModel mdl_icerik_List;
    private Object[] row_egitim_list;
    private DefaultTableModel mdl_egitim_list;
    private int sayi1 = 0;

    private final educator educator;

    educatorGUI(com.patika.Model.educator educator) {
        this.educator = educator;
        add(panel1);
        setSize(700, 500);


        int x = Helper.screenCenter("x", (getSize()));
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lbl_isim.setText(educator.getName());


        mdl_egitim_list = new DefaultTableModel();
        Object[] col_egitim_list = {"id", "patika", "ders adı"};
        mdl_egitim_list.setColumnIdentifiers(col_egitim_list);
        row_egitim_list = new Object[col_egitim_list.length];

        tbl_egitim_list.setModel(mdl_egitim_list);
        tbl_egitim_list.getTableHeader().setReorderingAllowed(false);//

        mdl_icerik_List = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //id sutununa çift tıklayınca düzenleme şeyi açık olmasın
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        mdl_icerik_List = new DefaultTableModel();
        Object[] col_icerik_list = {"id", "baslik","aciklama","quiz","ytlink", "ders adı"};
        mdl_icerik_List.setColumnIdentifiers(col_icerik_list);
        row_icerik_list = new Object[col_icerik_list.length];
        tabloyenileicerik();

        tbl_icerik_list.setModel(mdl_icerik_List);
        tbl_icerik_list.getTableHeader().setReorderingAllowed(false);//
        tabloYenileeeegitim();

        comboboxIcerigidersAdi();
        comboboxIcerigi();
        lbl_egitim_not.setText("öğretmen " + educator.getName() + " için sonuçlar");

        pnl_quiz_disabled.setVisible(false);

        rdbtn_quiz.addActionListener(e -> {
            if (rdbtn_quiz.isSelected()) {
                pnl_quiz_disabled.setVisible(true);
            } else {
                pnl_quiz_disabled.setVisible(false);
            }
        });
        btn_quiz_olustur.addActionListener(e -> {
            if (Helper.txtBosmu(txt_quiz_Adi)) {
                Helper.mesajPenceresi("Lütfen quiz adı girin", "Eksik işlem", "TAMAM");
            } else {
                //lbl_quiz_Adi.setText(txt_quiz_Adi.getText());
                //   txt_quiz_Adi.setText(null);
              //  lbl_ders_adi.setText(cmb_ders_adi.getSelectedItem().toString());
                //txt_ders_adi.setText(null);
            }
        });
        btn_devam.addActionListener(e -> {
            comboboxIcerigidersAdi();
            comboboxIcerigi();
            if (Helper.txtBosmu(txt_quiz_Adi)) {
                Helper.mesajPenceresi("Lütfen quiz adı girin", "Eksik işlem", "TAMAM");
            }else{
                if (quiz5.add(lblders.getText(),lbl.getText(),
                        txt_soru.getText(),txt_A.getText(),txt_B.getText(),txt_C.getText(),txt_D.getText(),cmb_cevap.getSelectedItem().toString())){
                    Helper.mesajPenceresi("Başarılı","Başarılı","Tamam");
                    //temizle();
                }
            }
            //deneme();
        });
        btnkaydet.addActionListener(e -> {
            if (Helper.txtBosmu(txt_baslik) || Helper.txtBosmu(txt_aciklama) || Helper.txtBosmu(txt_quiz_Adi)) {
                Helper.mesajPenceresi("Boş Alan Bırakmayınız", "HATA", "Tamam");
            } else {
                if (dersicerigi.add(txt_baslik.getText(), txt_aciklama.getText(), txt_quiz_Adi.getText(), txt_youtube_Link.getText(), cmb_ders_adi.getSelectedItem().toString())) {
                    Helper.mesajPenceresi("Eklendi", "SUCCESS", "Tamam");
                  //  tabloYenileeeegitim();
                    tabloyenileicerik();
                    comboboxIcerigidersAdi();
                    comboboxIcerigi();
                    lbl.setText(txt_quiz_Adi.getText());
                    lblders.setText(cmb_ders_adi.getSelectedItem().toString());
                }
            }

        });
        btn_cks.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });

        btn_yeni.addActionListener(e -> {
            temizle();
        });
    }

    public void deneme() {
        if (txt_soru.getText() != null && Helper.txtBosmu(txt_A) && Helper.txtBosmu(txt_B) && Helper.txtBosmu(txt_C) && Helper.txtBosmu(txt_D)) {
            Helper.mesajPenceresi("boş alan bırakmayın", "HATA", "TAMAM");
        } else {
            Helper.mesajPenceresi("Eklendi","Eklendi","Tamam");
            temizle();
        }
    }

    public void temizle() {
        txt_soru.setText(null);
        txt_A.setText(null);
        txt_B.setText(null);
        txt_C.setText(null);
        txt_D.setText(null);
    }

    public void tabloyenileicerik(){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_icerik_list.getModel();
        clearModel.setRowCount(0);
        for(dersicerigi obj:dersicerigi.getList()){
            int i=0;
            row_icerik_list[i++]=obj.getId();
            row_icerik_list[i++]=obj.getBaslik();
            row_icerik_list[i++]=obj.getAciklama();
            row_icerik_list[i++]=obj.getQuiz();
            row_icerik_list[i++]=obj.getYtlink();
            row_icerik_list[i++]=obj.getDersadi();
            mdl_icerik_List.addRow(row_icerik_list);
        }
    }
    public void tabloYenileeeegitim() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_egitim_list.getModel();
        clearModel.setRowCount(0);
        int eduid = educator.getId();

        if (educator.getName().equals(usertable.getList().get(49).getName())) {
            //Tabloyu doldurmak
            for (dersler obj : dersler.getlisteducatoricin1(64)) {
                int i = 0;
                row_egitim_list[i++] = obj.getId();
                row_egitim_list[i++] = obj.getKurs12().getName();
                row_egitim_list[i++] = obj.getName();
                mdl_egitim_list.addRow(row_egitim_list);
            }
        }
    }


    public void comboboxIcerigi() {
        cmb_patika_adi.removeAllItems();
        comboboxIcerigidersAdi();
        for (kurs obj : kurs.getList()) {
            cmb_patika_adi.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void comboboxIcerigidersAdi() {
        cmb_ders_adi.removeAllItems();
       // cmb_ders_ad.removeAllItems();
        tabloYenileeeegitim();
        for (dersler obj : dersler.getlisteducatoricin1(64)) {
            cmb_ders_adi.addItem(new Item(obj.getId(), obj.getName()));
     //       cmb_ders_ad.addItem(new Item(obj.getId(),obj.getName()));
        }
    }
}



